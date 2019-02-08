package com.example.riccilib.ricci.extensible;

import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import com.example.riccilib.ricci.constants.Configuration;
import com.example.riccilib.ricci.handlers.ClientSocketHandler;
import com.example.riccilib.ricci.handlers.GroupOwnerSocketHandler;

/**
 * Class to manage reading and writing of messages with a socket. It uses a Handler
 * to send messages to the GUI, i.e. the Android's UI Thread.
 * This class is used by {@link ClientSocketHandler}
 * and {@link GroupOwnerSocketHandler}.
 * <p></p>
 * Created by Stefano Cappa on 04/02/15, based on google code samples.
 *  Modified by Breno Cruz
 */
public class ChatWifiManager implements Runnable {

    private static final String TAG = "ChatWifiManager";

    private InputStream iStream;
    private OutputStream oStream;
    private Socket socket = null;
    private final Handler handler;
    private boolean disable = false;

    public boolean isDisable() {
        return disable;
    }
    public void setDisable(boolean disable) {
        this.disable = disable;
    }

    /**
         * Constructor of the class
         * @param socket Represents the {@link java.net.Socket} required in order to communicate
         * @param handler Represents the Handler required in order to communicate
         */
    public ChatWifiManager(Socket socket, Handler handler) {
            this.socket = socket;
            this.handler = handler;
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
