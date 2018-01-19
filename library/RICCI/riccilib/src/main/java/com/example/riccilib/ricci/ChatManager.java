package com.example.riccilib.ricci;

import android.os.Handler;
import android.util.Log;

import com.example.riccilib.ricci.Utils.RicciChatManager;

import java.net.Socket;

/**
 * Created by brenocruz on 2/20/17.
 */

public class ChatManager extends RicciChatManager {

    private String TAG = "ChatManager";
    /**
     * Constructor of the class
     * @param socket Represents the {@link java.net.Socket} required in order to communicate
     * @param handler Represents the Handler required in order to communicate
     */
    public ChatManager(Socket socket, Handler handler) {
        super(handler, socket);
        Log.d(TAG, "Created ChatManager");
    }

}