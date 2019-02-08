package com.example.riccilib.ricci.extensible;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.riccilib.ricci.ChatManager;

import com.example.riccilib.ricci.RemoteIntent;
import com.example.riccilib.ricci.services.BasicIntentService;

import static com.example.riccilib.ricci.constants.UtilityConstants.OUT_REMOTE_REPLY_MSG;
import static com.example.riccilib.ricci.constants.UtilityConstants.OUT_STREAM_FILE_REPLY_MSG;
import static com.example.riccilib.ricci.constants.UtilityConstants.OUT_STREAM_REPLY_MSG;
import static com.example.riccilib.ricci.constants.UtilityConstants.IN_MSG;
import static com.example.riccilib.ricci.constants.UtilityConstants.OUT_REMOTE_REPLY_MSG;
import static com.example.riccilib.ricci.constants.UtilityConstants.OUT_STREAM_FILE_REPLY_MSG;
import static com.example.riccilib.ricci.constants.UtilityConstants.OUT_STREAM_REPLY_MSG;

/**
 * Created by brenocruz on 1/27/17.
 */

public abstract class RicciAppCompatActivitySimplified extends AppCompatActivity {

    private final String TAG = "RicciAppCompat";
    protected ChatManager chatManager;

    public void setChatManager(ChatManager chatManager) { this.chatManager = chatManager; }
    public ChatManager getChatManager(){
        return this.chatManager;
    }

    // Storage Permissions
    private static final int REQUEST_PERMISSIONS = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.INTERNET

    };

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_PERMISSIONS
            );
        }
    }

    public Intent handleStreamFileIntent(Intent data){

        Intent intent = new Intent(this, BasicIntentService.class);
        intent.putExtra(OUT_STREAM_FILE_REPLY_MSG, data);
        return intent;
    }

    public Intent handleStreamIntent(Intent data){

        Intent intent = new Intent(getApplicationContext(), BasicIntentService.class);
        intent.putExtra(OUT_STREAM_REPLY_MSG, data);
        return intent;

    }

    public Intent handleRemoteIntent(Intent data){

        Intent intent = new Intent(this, BasicIntentService.class);
        intent.putExtra(OUT_REMOTE_REPLY_MSG, data);
        return intent;

    }

    private void sendByteArray(RemoteIntent remoteIntent) {

        byte[] byteArray = remoteIntent.getByteArray();

        if(chatManager != null){

            chatManager.write(byteArray);

        } else {

            Log.d(TAG, "chat manager is null");

        }
    }

    public Intent receiveIncomingMessage(byte[] buffer){

        RemoteIntent remoteIntent = new RemoteIntent(buffer);
        Intent intent = new Intent(getApplicationContext(), BasicIntentService.class);
        intent.putExtra(IN_MSG, remoteIntent.getJson());
        return intent;

    }

    public void startActivity(RemoteIntent remoteIntent){

        sendByteArray(remoteIntent);

    }

    public void sendIntentToService(RemoteIntent remoteIntent, String tag) {

        Intent intent = new Intent(this, BasicIntentService.class);
        intent.putExtra(tag, remoteIntent.getJson());
        startService(intent);

    }
}
