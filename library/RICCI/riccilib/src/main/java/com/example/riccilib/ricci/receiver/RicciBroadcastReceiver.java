package com.example.riccilib.ricci.receiver;

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


import com.example.riccilib.ricci.ChatManager;
import com.example.riccilib.ricci.RemoteIntent;
import com.example.riccilib.ricci.Utils.CopyUtils;
import com.example.riccilib.ricci.Utils.RemoteAssistant;
import com.example.riccilib.ricci.Utils.RemoteResultsHolder;
import com.example.riccilib.ricci.Utils.RemoteUtils;
import com.example.riccilib.ricci.Utils.StreamingUtils;
import com.example.riccilib.ricci.Utils.Util;
import com.example.riccilib.ricci.constants.Transfer;

import static com.example.riccilib.ricci.Utils.Util.REQUEST_COPY_TRANSMISSION;
import static com.example.riccilib.ricci.Utils.Util.REQUEST_REMOTE_TRANSMISSION;
import static com.example.riccilib.ricci.Utils.Util.REQUEST_STREAM_FILE_TRANSMISSION;
import static com.example.riccilib.ricci.Utils.Util.REQUEST_STREAM_TRANSMISSION;
import static com.example.riccilib.ricci.constants.UtilityConstants.*;

/**
 * Created by brenocruz on 2/8/17.
 */

public abstract class RicciBroadcastReceiver extends BroadcastReceiver {

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
        RicciBroadcastReceiver.chatManager = chatManager;
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

    public void handleStreamFileAccessResponse(Intent intent, Context context){

        String absoluteFilePath = intent.getExtras().getString(IN_STREAM_FILE_ACCESS_RESPONSE);
        File from = new File(absoluteFilePath);
        String extension = Util.getFileExtension(from.getAbsolutePath());
        File to = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"/Music/music"+extension);

        try {
            Util.copyDirectoryOneLocationToAnotherLocation(from, to);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //from.renameTo(to);

        File file = to;
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.fromFile(file), Util.openFile(file));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);

    }

    public void handleStreamAccessResponse(Intent intent, Context context){

        String absoluteFilePath = intent.getExtras().getString(IN_STREAM_ACCESS_RESPONSE);

        try{

            File from = new File(absoluteFilePath);
            from.deleteOnExit();

            MediaPlayer mediaPlayer = new MediaPlayer();
            FileInputStream myFile = new FileInputStream(from);
            mediaPlayer.setDataSource(myFile.getFD());

            mediaPlayer.prepare();
            mediaPlayer.start();

        } catch(IOException e){

            String s = e.toString();
            e.printStackTrace();

        }

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getExtras() != null) {

            if (intent.hasExtra(OUT_REMOTE_REPLY_MSG)) {

                RemoteIntent remoteIntent = new RemoteIntent(intent.getExtras().getString(OUT_REMOTE_REPLY_MSG));
                writerHandler(remoteIntent.getByteArray());

            } else if (intent.hasExtra(OUT_COPY_REPLY_MSG)) {

                RemoteIntent remoteIntent = new RemoteIntent(intent.getExtras().getString(OUT_COPY_REPLY_MSG));
                writerHandler(remoteIntent.getByteArray());

            } else if (intent.hasExtra(OUT_STREAM_FILE_REPLY_MSG)) {

                RemoteIntent remoteIntent = new RemoteIntent(intent.getExtras().getString(OUT_STREAM_FILE_REPLY_MSG));
                writerHandler(remoteIntent.getByteArray());

            } else if (intent.hasExtra(OUT_STREAM_REPLY_MSG)) {

                RemoteIntent remoteIntent = new RemoteIntent(intent.getExtras().getString(OUT_STREAM_REPLY_MSG));
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

            } else if(intent.hasExtra(IN_STREAM_FILE_ACCESS_RESPONSE)) {

                handleStreamFileAccessResponse(intent, context);

            } else if(intent.hasExtra(IN_STREAM_ACCESS_RESPONSE)) {

                handleStreamAccessResponse(intent, context);

            }

        } else {

            Log.d(TAG, "Received intent with null extras");
        }
    }
}
