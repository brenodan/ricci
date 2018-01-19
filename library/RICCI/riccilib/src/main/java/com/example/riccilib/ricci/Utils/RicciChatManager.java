package com.example.riccilib.ricci.Utils;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.example.riccilib.ricci.constants.Configuration;

/**
 * Created by brenocruz on 2/9/17.
 */

public abstract class RicciChatManager implements Runnable {

    private static final String TAG = "ChatManager";

    private Socket socket = null;
    private final Handler handler;
    private boolean disable = false;

    public boolean isDisable() {
        return disable;
    }
    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    private InputStream iStream;
    private OutputStream oStream;


    public RicciChatManager(Handler handler, Socket socket){
        this.handler = handler;
        this.socket = socket;

    }

    @Override
    public void run() {

        Log.d(TAG,"ChatManager started");

        try {

            iStream = socket.getInputStream();
            oStream = socket.getOutputStream();
            byte[] buffer = new byte[1024];
            int bytes;

            //this method's call is used to call handleMessage's case Configuration.FIRSTMESSAGEXCHANGE in the MainActivity.
            handler.obtainMessage(Configuration.FIRSTMESSAGEXCHANGE, this).sendToTarget();

            while (!disable) { //...if enabled

                try {
                    // Read from the InputStream
                    if(iStream != null) {
                        bytes = iStream.read(buffer);
                        if (bytes == -1) {
                            break;
                        }

                        //this method's call is used to call handleMessage's case Configuration.MESSAGE_READ in the MainActivity.
                        handler.obtainMessage(Configuration.MESSAGE_READ, bytes, -1, buffer).sendToTarget();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "disconnected", e);
                }
            }
        } catch (IOException e) {
            Log.e(TAG,"Exception : " + e.toString());
        } finally {
            try {
                iStream.close();
                socket.close();
            } catch (IOException e) {
                Log.e(TAG,"Exception during close socket or isStream",  e);
            }
        }
    }

    /**
     * Method to write a byte array (that can be a message) on the output stream.
     * @param buffer byte[] array that represents data to write. For example, a String converted in byte[] with ".getBytes();"
     */
    public void write(byte[] buffer) {
        try {
            oStream.write(buffer);
        } catch (IOException e) {
            Log.e(TAG, "Exception during write", e);
        }
    }

}
