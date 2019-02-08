package com.example.riccilib.ricci.extensible;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;


import com.example.riccilib.ricci.ChatManager;
import com.example.riccilib.ricci.RemoteIntent;
import com.example.riccilib.ricci.services.BasicIntentService;

import static com.example.riccilib.ricci.Utils.CopyUtils.processDataForTransmission;
import static com.example.riccilib.ricci.constants.UtilityConstants.IN_MSG;
import static com.example.riccilib.ricci.constants.UtilityConstants.OUT_COPY_REPLY_MSG;
import static com.example.riccilib.ricci.constants.UtilityConstants.OUT_REMOTE_REPLY_MSG;
import static com.example.riccilib.ricci.constants.UtilityConstants.OUT_STREAM_FILE_REPLY_MSG;
import static com.example.riccilib.ricci.constants.UtilityConstants.OUT_STREAM_REPLY_MSG;

/**
 * Created by brenocruz on 2/2/17.
 */

public abstract class RicciAppCompatActivity extends AppCompatActivity {

    protected ChatManager chatManager;

    public ChatManager getChatManager() {
        return chatManager;
    }

    public void setChatManager(ChatManager chatManager) {
        this.chatManager = chatManager;
    }

    /**
     * Method that receives resulting intent from data access operation and returns
     * an intent with the information in a bundle ready to be transmitted to the
     * destination device
     * @param data
     * @return intent
     */
    public Intent handleCopyIntent(Intent data){

        Intent intent = new Intent(getApplicationContext(), BasicIntentService.class);
        Uri dataUri = data.getData();
        Cursor cursor = getContentResolver().query(dataUri, null, null, null, null);
        Intent sendIntent = processDataForTransmission(cursor);
        intent.putExtra(OUT_COPY_REPLY_MSG, sendIntent);
        cursor.close();
        return intent;
    }
    /**
     * Method that receives resulting intent from data access operation and returns
     * an intent with the information necessary for the transmission of stream reply
     * @param data
     * @return intent
     */
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
        chatManager.write(byteArray);
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
