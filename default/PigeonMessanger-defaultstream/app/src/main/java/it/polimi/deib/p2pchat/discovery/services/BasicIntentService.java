package it.polimi.deib.p2pchat.discovery.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import it.polimi.deib.p2pchat.discovery.utilities.RemoteIntent;
import it.polimi.deib.p2pchat.discovery.utilities.StreamingUtils;
import it.polimi.deib.p2pchat.discovery.utilities.Util;

import static android.content.ContentValues.TAG;
import static it.polimi.deib.p2pchat.discovery.Configuration.IN_MSG;
import static it.polimi.deib.p2pchat.discovery.Configuration.IN_STREAM_ACCESS_RESPONSE;
import static it.polimi.deib.p2pchat.discovery.Configuration.OUT_MSG;
import static it.polimi.deib.p2pchat.discovery.Configuration.OUT_STREAM_CLIENT_MSG;
import static it.polimi.deib.p2pchat.discovery.Configuration.OUT_STREAM_REPLY_MSG;
import static it.polimi.deib.p2pchat.discovery.utilities.Transfer.COPY;
import static it.polimi.deib.p2pchat.discovery.utilities.Transfer.STREAM_REPLY;
import static it.polimi.deib.p2pchat.discovery.utilities.Util.ACTION_RESP;

/**
 * Created by brenocruz on 1/5/18.
 */

public class BasicIntentService extends IntentService {

    public BasicIntentService() {
        super("BasicIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if(intent.hasExtra(IN_MSG)){

            try {

                Intent broadcastIntent = handleInMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, "null pointer exception - IN_MSG");

            }

        } else if (intent.hasExtra(OUT_MSG)) {

            try {

                Intent broadcastIntent = handleOutMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, "null pointer exception - OUT MSG");

            }

        } else if (intent.hasExtra(OUT_STREAM_REPLY_MSG)) {

            try {

                Intent broadcastIntent = handleStreamReplyMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, "null pointer exception - OUT_STREAM_REPLY_MSG");

            }

        } else if (intent.hasExtra(OUT_STREAM_CLIENT_MSG)) {

            try {

                Intent broadcastIntent = handleStreamClientMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, "null pointer exception - OUT_STREAM_CLIENT_MSG");

            }

        } else {

            Log.d(TAG, "Does not contains extra");
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            sendBroadcast(broadcastIntent);

        }
    }

    public Intent receiveIncomingMessage(byte[] buffer){

        RemoteIntent remoteIntent = new RemoteIntent(buffer);
        Intent intent = new Intent(getApplicationContext(), BasicIntentService.class);
        intent.putExtra(IN_MSG, remoteIntent.getJson());
        return intent;

    }

    public Intent handleInMessage(Intent intent) {

        String msg = intent.getStringExtra(IN_MSG);
        RemoteIntent remoteIntent = new RemoteIntent(msg);
        Log.d("Incoming", "Handling msg: " + msg);
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        Log.d("Incoming", "Json : " + remoteIntent.getJson());
        broadcastIntent.putExtra(IN_MSG, remoteIntent.getJson());

        return broadcastIntent;
    }

    public RemoteIntent handleOutMessage(Intent intent) {

        Bundle extras = intent.getExtras();
        RemoteIntent remoteIntent = (RemoteIntent) extras.get(OUT_MSG);
        remoteIntent.addCategory(Intent.CATEGORY_DEFAULT);
        remoteIntent.setTransferMethod(COPY);
        System.out.println("@@ OUT MESSAGE : " + remoteIntent.getJson());
        remoteIntent.putExtra(OUT_MSG, remoteIntent.getJson());//add contents here
        Log.d("Outgoing", "Handling msg: " + remoteIntent.getJson());

        return remoteIntent;
    }

    public Intent handleStreamReplyMessage(Intent intent) {

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        RemoteIntent remoteIntent = new RemoteIntent();

        Intent data = (Intent) intent.getExtras().get(OUT_STREAM_REPLY_MSG);
        remoteIntent.setTransferMethod(STREAM_REPLY);


        if (data != null) {

            StreamingUtils streamUtils = new StreamingUtils();
            remoteIntent.putExtra(OUT_STREAM_REPLY_MSG, streamUtils.getIPAddress(true));
            streamUtils.streamServerHandler(data, getApplicationContext());
            broadcastIntent.putExtra(OUT_STREAM_REPLY_MSG, remoteIntent.getJson());
            Log.d("Outgoing", "Handling msg: " + "data is not null " + remoteIntent.getJson());


        } else {

            remoteIntent.putExtra(OUT_STREAM_REPLY_MSG, "false");
            broadcastIntent.putExtra(OUT_STREAM_REPLY_MSG, remoteIntent.getJson());
            Log.d("Outgoing", "Handling msg: " + remoteIntent.getJson());
        }
        return broadcastIntent;
    }

    public Intent handleStreamClientMessage(Intent intent) {

        String serverIp = intent.getExtras().getString(OUT_STREAM_CLIENT_MSG);
        StreamingUtils streamingUtils = new StreamingUtils();
        Log.d(TAG, OUT_STREAM_CLIENT_MSG);

        try {

            Log.d(TAG, "accessing remote file");
            StreamService remoteObject = streamingUtils.remoteClientHandler(serverIp);
            Intent broadcastIntent = new Intent();

            String extension = remoteObject.getFileExtension();
            Log.d(TAG, "file extension: " + extension);
            File temp = File.createTempFile("tmp", extension);
            Log.d(TAG, "temp file path: " + temp.getAbsolutePath());
            FileOutputStream fileOutputStream = new FileOutputStream(temp);
            fileOutputStream.write(remoteObject.loadData());
            fileOutputStream.close();

            broadcastIntent.putExtra(IN_STREAM_ACCESS_RESPONSE, temp.getAbsolutePath());
            broadcastIntent.setAction(ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            return broadcastIntent;

        } catch (IOException e) {

            e.printStackTrace();
        }
        return null;
    }

}
