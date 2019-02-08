package com.example.riccilib.ricci.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.example.riccilib.ricci.RemoteIntent;
import com.example.riccilib.ricci.Utils.RemoteInterface;
import com.example.riccilib.ricci.Utils.RemoteUtils;
import com.example.riccilib.ricci.Utils.StreamService;
import com.example.riccilib.ricci.Utils.StreamingUtils;
import com.example.riccilib.ricci.Utils.Util;
import com.example.riccilib.ricci.handlers.ReceiveRemoteHandler;

import static android.content.ContentValues.TAG;
import static com.example.riccilib.ricci.Utils.Util.ACTION_RESP;
import static com.example.riccilib.ricci.constants.Transfer.COPY;
import static com.example.riccilib.ricci.constants.Transfer.COPY_REPLY;
import static com.example.riccilib.ricci.constants.Transfer.REMOTE_REPLY;
import static com.example.riccilib.ricci.constants.Transfer.STREAM_FILE_REPLY;
import static com.example.riccilib.ricci.constants.Transfer.STREAM_REPLY;
import static com.example.riccilib.ricci.constants.UtilityConstants.*;

/**
 * Created by brenocruz on 1/27/17.
 */

public class BasicIntentServiceSample extends IntentService {

    public BasicIntentServiceSample(){super("BasicIntentService");}

    @Override
    protected void onHandleIntent(Intent intent) {

        if(intent.hasExtra(IN_MSG)){

            String msg = intent.getStringExtra(IN_MSG);
            RemoteIntent remoteIntent = new RemoteIntent(msg);
            Log.d("Incoming", "Handling msg: " + msg);
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            Log.d("Incoming", "Json : " + remoteIntent.getJson());
            broadcastIntent.putExtra(IN_MSG, remoteIntent.getJson());
            sendBroadcast(broadcastIntent);

        } else if (intent.hasExtra(OUT_MSG)) {

            Bundle extras = intent.getExtras();
            RemoteIntent remoteIntent = (RemoteIntent)extras.get(OUT_MSG);
            remoteIntent.addCategory(Intent.CATEGORY_DEFAULT);
            remoteIntent.setTransferMethod(COPY);
            System.out.println("@@ OUT MESSAGE : " + remoteIntent.getJson());
            remoteIntent.putExtra(OUT_MSG, remoteIntent.getJson());//add contents here
            Log.d("Outgoing", "Handling msg: " + remoteIntent.getJson());
            sendBroadcast(remoteIntent);

        } else if (intent.hasExtra(OUT_COPY_MSG)) {

            Bundle extras = intent.getExtras();
            RemoteIntent remoteIntent = (RemoteIntent)extras.get(OUT_COPY_MSG);
            remoteIntent.addCategory(Intent.CATEGORY_DEFAULT);
            remoteIntent.setTransferMethod(COPY_REPLY);
            remoteIntent.putExtra(OUT_COPY_MSG, remoteIntent.getJson());//add contents here
            Log.d("Outgoing", "Handling msg: " + remoteIntent.getJson());
            sendBroadcast(remoteIntent);

        } else if (intent.hasExtra(OUT_COPY_REPLY_MSG)) {

            Intent data = (Intent) intent.getExtras().get(OUT_COPY_REPLY_MSG);
            RemoteIntent remoteIntent = Util.intentToRemoteIntent(data);
            remoteIntent.setTransferMethod(COPY_REPLY);
            String json = remoteIntent.getJson();
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            broadcastIntent.putExtra(OUT_COPY_REPLY_MSG, json);
            Log.d("Outgoing", "Handling msg: " + remoteIntent.getJson());
            sendBroadcast(broadcastIntent);

        } else if (intent.hasExtra(OUT_STREAM_FILE_REPLY_MSG)) {

            Intent data;
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            RemoteIntent remoteIntent = new RemoteIntent();

            data = (Intent) intent.getExtras().get(OUT_STREAM_FILE_REPLY_MSG);
            remoteIntent.setTransferMethod(STREAM_FILE_REPLY);

            if(data != null){

                StreamingUtils streamUtils = new StreamingUtils();
                remoteIntent.putExtra(OUT_STREAM_FILE_REPLY_MSG, streamUtils.getIPAddress(true));
                streamUtils.streamServerHandler(data, getApplicationContext());
                broadcastIntent.putExtra(OUT_STREAM_FILE_REPLY_MSG, remoteIntent.getJson());
                Log.d("Outgoing", "Handling msg: " + "data is not null " + remoteIntent.getJson());

            } else {

                remoteIntent.putExtra(OUT_STREAM_FILE_REPLY_MSG, "false");
                broadcastIntent.putExtra(OUT_STREAM_FILE_REPLY_MSG, remoteIntent.getJson());
                Log.d("Outgoing", "Handling msg: " + remoteIntent.getJson());
            }

            sendBroadcast(broadcastIntent);

        } else if (intent.hasExtra(OUT_STREAM_REPLY_MSG)) {

            Intent data;
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            RemoteIntent remoteIntent = new RemoteIntent();

            data = (Intent) intent.getExtras().get(OUT_STREAM_REPLY_MSG);
            remoteIntent.setTransferMethod(STREAM_REPLY);

            if(data != null){

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

            sendBroadcast(broadcastIntent);

        } else if (intent.hasExtra(OUT_REMOTE_REPLY_MSG)) {

            Intent data;
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            RemoteIntent remoteIntent = new RemoteIntent();
            data = (Intent) intent.getExtras().get(OUT_REMOTE_REPLY_MSG);
            remoteIntent.setTransferMethod(REMOTE_REPLY);

            if(data != null){

                RemoteUtils remoteUtils = new RemoteUtils();
                remoteIntent.putExtra(OUT_REMOTE_REPLY_MSG, remoteUtils.getIPAddress(true));
                remoteUtils.remoteServerHandler(data);
                broadcastIntent.putExtra(OUT_REMOTE_REPLY_MSG, remoteIntent.getJson());

                Log.d("Outgoing", "Handling msg: " + "data is not null " + remoteIntent.getJson());

            } else {

                remoteIntent.putExtra(OUT_REMOTE_REPLY_MSG, "false");
                broadcastIntent.putExtra(OUT_REMOTE_REPLY_MSG, remoteIntent.getJson());

                Log.d("Outgoing", "Handling msg: " + remoteIntent.getJson());
            }

            sendBroadcast(broadcastIntent);

        } else if (intent.hasExtra(OUT_STREAM_FILE_CLIENT_MSG)) {

            String serverIp = intent.getExtras().getString(OUT_STREAM_FILE_CLIENT_MSG);
            StreamingUtils streamingUtils = new StreamingUtils();
            Log.d(TAG, OUT_STREAM_FILE_CLIENT_MSG);

            try {

                Log.d(TAG, "accessing remote object");
                StreamService remoteObject = streamingUtils.remoteClientHandler(serverIp);
                Intent broadcastIntent = new Intent();
                String extension = remoteObject.getFileExtension();
                System.out.println("Temp file extension: " + extension);
                File temp = File.createTempFile("tmp", extension);
                System.out.println("Temp file : " + temp.getAbsolutePath());
                FileOutputStream fileOutputStream = new FileOutputStream(temp);

                fileOutputStream.write(remoteObject.loadData());
                fileOutputStream.close();

                broadcastIntent.putExtra(IN_STREAM_FILE_ACCESS_RESPONSE, temp.getAbsolutePath());
                broadcastIntent.setAction(ACTION_RESP);
                broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
                sendBroadcast(broadcastIntent);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_STREAM_CLIENT_MSG)) {

            String serverIp = intent.getExtras().getString(OUT_STREAM_CLIENT_MSG);
            StreamingUtils streamingUtils = new StreamingUtils();
            Log.d(TAG, OUT_STREAM_CLIENT_MSG);

            try {

                Log.d(TAG, "accessing remote file");
                StreamService remoteObject = streamingUtils.remoteClientHandler(serverIp);
                Intent broadcastIntent = new Intent();

                String extension = remoteObject.getFileExtension();
                System.out.println("Temp file extension: " + extension);
                File temp = File.createTempFile("tmp", extension);
                System.out.println("Temp file : " + temp.getAbsolutePath());
                FileOutputStream fileOutputStream = new FileOutputStream(temp);
                fileOutputStream.write(remoteObject.loadData());
                fileOutputStream.close();

                broadcastIntent.putExtra(IN_STREAM_ACCESS_RESPONSE, temp.getAbsolutePath());
                broadcastIntent.setAction(ACTION_RESP);
                broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
                sendBroadcast(broadcastIntent);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_REMOTE_CLIENT_MSG)) {

            String serverIp = intent.getExtras().getString(OUT_REMOTE_CLIENT_MSG);
            RemoteUtils remoteUtils = new RemoteUtils();
            Log.d(TAG, OUT_REMOTE_CLIENT_MSG);

            try {

                Log.d(TAG, "accessing remote object");
                RemoteInterface remoteObject = remoteUtils.remoteClientHandler(serverIp);
                Intent broadcastIntent = ReceiveRemoteHandler.receiveRemoteHandler(remoteObject, intent);
                sendBroadcast(broadcastIntent);

            } catch (IOException e) {

                e.printStackTrace();
            }

        } else {

            System.out.println("Does not contains extra");
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            sendBroadcast(broadcastIntent);

        }
    }
}
