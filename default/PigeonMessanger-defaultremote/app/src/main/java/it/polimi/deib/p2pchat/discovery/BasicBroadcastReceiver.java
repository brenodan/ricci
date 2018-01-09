package it.polimi.deib.p2pchat.discovery;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

import it.polimi.deib.p2pchat.discovery.socketmanagers.ChatManager;
import it.polimi.deib.p2pchat.discovery.utilities.CopyUtils;
import it.polimi.deib.p2pchat.discovery.utilities.RemoteIntent;
import it.polimi.deib.p2pchat.discovery.utilities.RemoteUtils;
import it.polimi.deib.p2pchat.discovery.utilities.StreamingUtils;
import it.polimi.deib.p2pchat.discovery.utilities.Transfer;
import it.polimi.deib.p2pchat.discovery.utilities.Util;

import static it.polimi.deib.p2pchat.discovery.Configuration.IN_MSG;
import static it.polimi.deib.p2pchat.discovery.Configuration.IN_REMOTE_ACCESS_RESPONSE;
import static it.polimi.deib.p2pchat.discovery.Configuration.OUT_REMOTE_REPLY_MSG;
import static it.polimi.deib.p2pchat.discovery.utilities.Util.REQUEST_COPY_TRANSMISSION;
import static it.polimi.deib.p2pchat.discovery.utilities.Util.REQUEST_REMOTE_TRANSMISSION;
import static it.polimi.deib.p2pchat.discovery.utilities.Util.REQUEST_STREAM_FILE_TRANSMISSION;
import static it.polimi.deib.p2pchat.discovery.utilities.Util.REQUEST_STREAM_TRANSMISSION;

/**
 * Created by brenocruz on 1/8/18.
 */

public class BasicBroadcastReceiver extends BroadcastReceiver {

    public static boolean copyFlag;
    public static Intent copyReply;

    final String TAG = "RicciBroadcastReceiver";

    private static ChatManager chatManager;
    public RemoteResultsHolder remoteResultsHolder = null;
    private RemoteAssistant remoteAssistant = null;
    private Object remoteSerializableObject = null;

    public RemoteResultsHolder getRemoteResultsHolder() {
        return remoteResultsHolder;
    }
    public void setRemoteResultsHolder(RemoteResultsHolder remoteResultsHolder) {
        this.remoteResultsHolder = remoteResultsHolder;
    }
    public RemoteAssistant getRemoteAssistant() {
        return remoteAssistant;
    }
    public void setRemoteAssistant(RemoteAssistant remoteAssistant) {
        this.remoteAssistant = remoteAssistant;
    }
    public void setRemoteSerializableObject(Object object){

        this.remoteSerializableObject = object;

    }
    public Object getRemoteSerializableObject(){

        return this.remoteSerializableObject;

    }

    public static ChatManager getChatManager() {
        return chatManager;
    }
    public static void setChatManager(ChatManager chatManager) {
        BasicBroadcastReceiver.chatManager = chatManager;
    }


    public void writerHandler(byte[] buffer){

        if(chatManager != null){

            getChatManager().write(buffer);

        } else {

            Log.d(TAG, "either ChatManager is null.");

        }
    }

    public void handleCopyReply(RemoteIntent remoteIntent){

        CopyUtils copyUtils = new CopyUtils();
        copyUtils.receiveCopy(remoteIntent);
        copyFlag = true;
        copyReply = copyUtils.receiveCopy(remoteIntent);

    }

    public void handleStreamReply(RemoteIntent remoteIntent, Context context){

        StreamingUtils streamingUtils = new StreamingUtils();
        streamingUtils.receiveStream(remoteIntent, context);

    }

    public void handleStreamFileReply(RemoteIntent remoteIntent, Context context){

        StreamingUtils streamingUtils = new StreamingUtils();
        streamingUtils.receiveStream(remoteIntent, context);

    }

    public void handleRemoteReply(RemoteIntent remoteIntent, Context context){

        RemoteUtils remoteUtils = new RemoteUtils(remoteSerializableObject, remoteAssistant);
        remoteUtils.receiveRemote(remoteIntent, context);

    }

    public void handleRemoteAccessResponse(Intent intent){

        remoteResultsHolder = RemoteResultsHandler.handlerRemoteIntent(intent);

    }

    public static String getCurrentTimeStamp(){

        Date date = new Date();
        return Long.toString(date.getTime());

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getExtras() != null) {

            if (intent.hasExtra(OUT_REMOTE_REPLY_MSG)) {

                RemoteIntent remoteIntent = new RemoteIntent(intent.getExtras().getString(OUT_REMOTE_REPLY_MSG));
                writerHandler(remoteIntent.getByteArray());

            } else if (intent.hasExtra(IN_MSG)) {

                RemoteIntent remoteIntent = new RemoteIntent(intent.getExtras().getString(IN_MSG));
                Transfer transfer = remoteIntent.getTransferMethod();

                switch (transfer) {

                    case COPY:{

                        if (context instanceof Activity) {

                            ((Activity) context).startActivityForResult(remoteIntent, REQUEST_COPY_TRANSMISSION);

                        } else {

                            Log.d(TAG, "error casting context to activity n COPY");
                        }
                    }
                    break;

                    case STREAM_FILE: {

                        if (context instanceof Activity) {

                            ((Activity) context).startActivityForResult(remoteIntent, REQUEST_STREAM_FILE_TRANSMISSION);

                        } else {

                            Log.d(TAG, "error casting context to activity in STREAM_FILE");

                        }
                    }
                    break;

                    case STREAM:{

                        if(context instanceof Activity) {

                            ((Activity) context).startActivityForResult(remoteIntent, REQUEST_STREAM_TRANSMISSION);

                        } else {

                            Log.d(TAG, "error casting context to activity in STREAM");

                        }
                    }
                    break;

                    case REMOTE:{

                        if(context instanceof Activity){

                            ((Activity) context).startActivityForResult(remoteIntent, REQUEST_REMOTE_TRANSMISSION);

                        } else {

                            Log.d(TAG, "error casting context to activity in REMOTE");

                        }
                    }
                    break;

                    case COPY_REPLY:

                        handleCopyReply(remoteIntent);
                        break;

                    case STREAM_FILE_REPLY: {

                        handleStreamFileReply(remoteIntent, context);

                    } break;

                    case STREAM_REPLY: {

                        handleStreamReply(remoteIntent, context);

                    } break;

                    case REMOTE_REPLY: {

                        handleRemoteReply(remoteIntent, context);

                    } break;
                    default:
                        break;

                }

            } else if(intent.hasExtra(IN_REMOTE_ACCESS_RESPONSE)) {

                Boolean flag = Boolean.parseBoolean((String)intent.getExtras().get(IN_REMOTE_ACCESS_RESPONSE));

                if(flag){

                    handleRemoteAccessResponse(intent);

                } else {

                    Log.d("Broadcast Receiver", "missing key components for remote actions");

                }

            }

        } else {

            Log.d(TAG, "Received intent with null extras");
        }
    }
}
