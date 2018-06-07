package com.example.riccilib.ricci.services;

import android.app.IntentService;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.example.riccilib.ricci.RemoteIntent;
import com.example.riccilib.ricci.Utils.RemoteInterface;
import com.example.riccilib.ricci.Utils.RemoteUtils;
import com.example.riccilib.ricci.Utils.StreamService;
import com.example.riccilib.ricci.Utils.StreamingUtils;
import com.example.riccilib.ricci.Utils.Util;
import com.example.riccilib.ricci.constants.ErrorMessages;
import com.example.riccilib.ricci.handlers.ReceiveRemoteHandler;
import com.google.gson.Gson;

import static android.content.ContentValues.TAG;
import static com.example.riccilib.ricci.Utils.Util.ACTION_RESP;
import static com.example.riccilib.ricci.constants.Transfer.COPY;
import static com.example.riccilib.ricci.constants.Transfer.COPY_REPLY;
import static com.example.riccilib.ricci.constants.Transfer.REMOTE_REPLY;
import static com.example.riccilib.ricci.constants.Transfer.STREAM_FILE_REPLY;
import static com.example.riccilib.ricci.constants.Transfer.STREAM_REPLY;
import static com.example.riccilib.ricci.constants.UtilityConstants.*;

/**
 * Created by brenocruz on 2/3/17.
 */

public abstract class RicciIntentService extends IntentService {


    public RicciIntentService(){super("RicciIntentService");}

    public Intent handleInMessage(Intent intent){

        String msg = intent.getStringExtra(IN_MSG);
        RemoteIntent remoteIntent = new RemoteIntent(msg);
        Log.d("INCOMING", "Handling msg: " + msg);
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        Log.d("INCOMING", "Json : " + remoteIntent.getJson());
        broadcastIntent.putExtra(IN_MSG, remoteIntent.getJson());

        return broadcastIntent;
    }

    public RemoteIntent handleOutMessage(Intent intent){

        Bundle extras = intent.getExtras();
        RemoteIntent remoteIntent = (RemoteIntent)extras.get(OUT_MSG);
        remoteIntent.addCategory(Intent.CATEGORY_DEFAULT);
        remoteIntent.setTransferMethod(COPY);
        Log.d("OUTGOING", remoteIntent.getJson());
        remoteIntent.putExtra(OUT_MSG, remoteIntent.getJson());//add contents here
        Log.d("OUTGOING", "Handling msg: " + remoteIntent.getJson());

        return remoteIntent;
    }

    public RemoteIntent handleOutCopyMessage(Intent intent){

        Bundle extras = intent.getExtras();
        RemoteIntent remoteIntent = (RemoteIntent)extras.get(OUT_COPY_MSG);
        remoteIntent.addCategory(Intent.CATEGORY_DEFAULT);
        remoteIntent.setTransferMethod(COPY_REPLY);
        Log.d("OUTGOING", remoteIntent.getJson());
        remoteIntent.putExtra(OUT_COPY_MSG, remoteIntent.getJson());//add contents here
        Log.d("OUTGOING", "Handling msg: " + remoteIntent.getJson());
        return remoteIntent;
    }

    public Intent handleOutCopyReplyMessage(Intent intent){

        Intent data = (Intent) intent.getExtras().get(OUT_COPY_REPLY_MSG);
        RemoteIntent remoteIntent = Util.intentToRemoteIntent(data);
        remoteIntent.setTransferMethod(COPY_REPLY);
        String json = remoteIntent.getJson();
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        broadcastIntent.putExtra(OUT_COPY_REPLY_MSG, json);
        Log.d("OUTGOING", "Handling msg: " + remoteIntent.getJson());
        return broadcastIntent;
    }

    public Intent handleStreamFileReplyMessage(Intent intent){

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
            Log.d("OUTGOING", "Handling msg: " + "data is not null " + remoteIntent.getJson());

        } else {

            remoteIntent.putExtra(OUT_STREAM_FILE_REPLY_MSG, "false");
            broadcastIntent.putExtra(OUT_STREAM_FILE_REPLY_MSG, remoteIntent.getJson());
            Log.d("OUTGOING", "Handling msg: " + remoteIntent.getJson());
        }

        return broadcastIntent;
    }

    public Intent handleStreamReplyMessage(Intent intent){

        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction(ACTION_RESP);
        broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
        RemoteIntent remoteIntent = new RemoteIntent();

        Intent data = (Intent) intent.getExtras().get(OUT_STREAM_REPLY_MSG);
        remoteIntent.setTransferMethod(STREAM_REPLY);

        if(data != null){

            StreamingUtils streamUtils = new StreamingUtils();
            remoteIntent.putExtra(OUT_STREAM_REPLY_MSG, streamUtils.getIPAddress(true));
            streamUtils.streamServerHandler(data, getApplicationContext());
            broadcastIntent.putExtra(OUT_STREAM_REPLY_MSG, remoteIntent.getJson());
            Log.d("OUTGOING", "Handling msg: " + "data is not null " + remoteIntent.getJson());


        } else {

            remoteIntent.putExtra(OUT_STREAM_REPLY_MSG, "false");
            broadcastIntent.putExtra(OUT_STREAM_REPLY_MSG, remoteIntent.getJson());
            Log.d("OUTGOING", "Handling msg: " + remoteIntent.getJson());
        }
        return broadcastIntent;
    }

    public Intent handleRemoteReplyMessage(Intent intent){

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
            Log.d("OUTGOING", "Handling msg: " + "data is not null " + remoteIntent.getJson());

        } else {

            remoteIntent.putExtra(OUT_REMOTE_REPLY_MSG, "false");
            broadcastIntent.putExtra(OUT_REMOTE_REPLY_MSG, remoteIntent.getJson());
            Log.d("OUTGOING", "Handling msg: " + remoteIntent.getJson());

        }

        return broadcastIntent;
    }

    public Intent handleStreamFileClientMessage(Intent intent){

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
            return broadcastIntent;

        } catch (IOException e) {

            e.printStackTrace();
        }

        return null;
    }

    public Intent handleStreamClientMessage(Intent intent){

        String serverIp = intent.getExtras().getString(OUT_STREAM_CLIENT_MSG);
        StreamingUtils streamingUtils = new StreamingUtils();
        Log.d(TAG, OUT_STREAM_CLIENT_MSG);

        try {

            Log.d(TAG, "accessing remote file");
            StreamService remoteObject = streamingUtils.remoteClientHandler(serverIp);
            Intent broadcastIntent = new Intent();

            String extension = remoteObject.getFileExtension();
            Log.d(TAG, "file extension: "  + extension);
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

    public Intent handleRemoteClientMessage(Intent intent) {

        String serverIp = intent.getExtras().getString(OUT_REMOTE_CLIENT_MSG);
        RemoteUtils remoteUtils = new RemoteUtils();
        Log.d(TAG, OUT_REMOTE_CLIENT_MSG);
        Log.d(TAG, "accessing remote object");

        try {

            RemoteInterface remoteObject = remoteUtils.remoteClientHandler(serverIp);
            Intent returnIntent = ReceiveRemoteHandler.receiveRemoteHandler(remoteObject, intent);
            return returnIntent;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if(intent.hasExtra(IN_MSG)){

            try {

                Intent broadcastIntent = handleInMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, ErrorMessages.errorNullPointerException + " - IN_MSG");
                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_MSG)) {

            try {

                Intent broadcastIntent = handleOutMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, ErrorMessages.errorNullPointerException + " - OUT MSG");
                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_COPY_MSG)) {

            try {

                Intent broadcastIntent = handleOutCopyMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, ErrorMessages.errorNullPointerException + " - OUT_COPY_MSG");
                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_COPY_REPLY_MSG)) {

            try {

                Intent broadcastIntent = handleOutCopyReplyMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, ErrorMessages.errorNullPointerException + " - OUT_COPY_REPLY_MSG");
                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_STREAM_FILE_REPLY_MSG)) {

            try {

                Intent broadcastIntent = handleStreamFileReplyMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, ErrorMessages.errorNullPointerException + " - OUT_STREAM_FILE_REPLY_MSG");
                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_STREAM_REPLY_MSG)) {

            try {

                Intent broadcastIntent = handleStreamReplyMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, ErrorMessages.errorNullPointerException + " - OUT_STREAM_REPLY_MSG");
                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_REMOTE_REPLY_MSG)) {

            try {

                Intent broadcastIntent = handleRemoteReplyMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, ErrorMessages.errorNullPointerException + " - OUT_REMOTE_REPLY_MSG");
                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_STREAM_FILE_CLIENT_MSG)) {

            try {

                Intent broadcastIntent = handleStreamFileClientMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, ErrorMessages.errorNullPointerException + " - OUT_STREAM_FILE_CLIENT_MSG");
                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_STREAM_CLIENT_MSG)) {

            try {

                Intent broadcastIntent = handleStreamClientMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, ErrorMessages.errorNullPointerException + " - OUT_STREAM_CLIENT_MSG");
                e.printStackTrace();
            }

        } else if (intent.hasExtra(OUT_REMOTE_CLIENT_MSG)) {

            try {

                Intent broadcastIntent = handleRemoteClientMessage(intent);
                sendBroadcast(broadcastIntent);

            } catch (NullPointerException e){

                Log.d(TAG, ErrorMessages.errorNullPointerException + " - OUT_REMOTE_CLIENT_MSG");
                e.printStackTrace();
            }

        } else {

            Log.d(TAG, ErrorMessages.errorEmptyBundle);
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ACTION_RESP);
            broadcastIntent.addCategory(Intent.CATEGORY_DEFAULT);
            sendBroadcast(broadcastIntent);

        }
    }
}
