package com.example.riccilib.ricci;

import android.content.Intent;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import com.example.riccilib.ricci.Utils.CompressionUtils;
import com.example.riccilib.ricci.Utils.IntentSerialization;
import com.example.riccilib.ricci.constants.Transfer;

import static com.example.riccilib.ricci.constants.Transfer.COPY;
import static com.example.riccilib.ricci.constants.Transfer.COPY_REPLY;
import static com.example.riccilib.ricci.constants.Transfer.REMOTE;
import static com.example.riccilib.ricci.constants.Transfer.REMOTE_REPLY;
import static com.example.riccilib.ricci.constants.Transfer.STREAM;
import static com.example.riccilib.ricci.constants.Transfer.STREAM_FILE;
import static com.example.riccilib.ricci.constants.Transfer.STREAM_FILE_REPLY;
import static com.example.riccilib.ricci.constants.Transfer.STREAM_REPLY;

/**
 * Created by brenocruz on 1/27/17.
 */

public class RemoteIntent extends Intent {

    private Transfer transferMethod;

    public static final String TAG = "RemoteIntent";

    public String getJson() {

        IntentSerialization intentSerialization = new IntentSerialization();

        if(this.getExtras() != null) {

            this.replaceExtras(intentSerialization.prepareJsonBundle(this.getExtras()));
            Log.d(TAG, "EXTRAS IN INTENT : " + this.getExtras());

        }

        return intentSerialization.serializeIntentGson(this);

    }

    public byte[] getByteArray(){

        IntentSerialization intentSerialization = new IntentSerialization();
        return intentSerialization.returnByteArrayFromString(this.getJson());

    }

    public Transfer getTransferMethod() {

        return this.transferMethod;

    }

    public void setTransferMethod(Transfer transferMethod) {

        this.transferMethod = transferMethod;
    }

    public RemoteIntent(){}

    public RemoteIntent(byte[] inputBytes){

        inputBytes = CompressionUtils.DecompressArray(inputBytes);
        ByteArrayInputStream bis = new ByteArrayInputStream(inputBytes);
        ObjectInput in = null;
        Log.d(TAG, "check for remote intent");

        try {

            in = new ObjectInputStream(bis);
            String json = (String) in.readObject();



            Log.d(TAG,"UTIL -- RemoteIntent from ByteArray: " + inputBytes);
            Log.d(TAG, "UTIL -- RemoteIntent from ByteArray: " + json);

            IntentSerialization intentSerialization = new IntentSerialization();
            Intent tempIntent;

            if(json.contains("Bundle[")){
                Log.d(TAG, "bundle detected: " + json);
                tempIntent = intentSerialization.deserializeIntentGsonBundle(json);

            } else {
                Log.d(TAG, "no bundle detected: " +json);
                tempIntent = intentSerialization.deserializeIntentGsonURI(json);
            }

            Log.d(TAG, json);

            if(json.contains("transferMethod")){

                Log.d(TAG, "transfer method detected");
                if(json.contains("COPY")){
                    Log.d(TAG, "copy transfer method");
                    this.setTransferMethod(COPY);

                } if(json.contains("REMOTE")){

                    this.setTransferMethod(REMOTE);

                } if(json.contains("STREAM")){

                    this.setTransferMethod(STREAM);

                } if(json.contains("STREAM_FILE")){

                    this.setTransferMethod(STREAM_FILE);

                } if(json.contains("COPY_REPLY")){

                    this.setTransferMethod(COPY_REPLY);

                } if (json.contains("REMOTE_REPLY")){

                    this.setTransferMethod(REMOTE_REPLY);

                } if(json.contains("STREAM_FILE_REPLY")){

                    this.setTransferMethod(STREAM_FILE_REPLY);

                } else if(json.contains("STREAM_REPLY")){

                    this.setTransferMethod(STREAM_REPLY);

                }
            }

            if(tempIntent.getAction() != null) {
                this.setAction(tempIntent.getAction());
            }

            if(tempIntent.getData() != null) {
                this.setData(tempIntent.getData());
            }

            if(tempIntent.getType() != null) {
                this.setType(tempIntent.getType());
            }

            if(tempIntent.getExtras() != null) {
                this.putExtras(tempIntent.getExtras());
            }

        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {

                if(in != null) {

                    in.close();

                }

            } catch (IOException ex) {
                // ignore close exception
            }
        }
    }


    public RemoteIntent(String json){

        IntentSerialization intentSerialization = new IntentSerialization();

        Intent tempIntent;

        if(json.contains("Bundle[")){
            Log.d(TAG, "bundle detected: " + json);
            tempIntent = intentSerialization.deserializeIntentGsonBundle(json);

        } else {
            Log.d(TAG, "no bundle detected: " +json);
            tempIntent = intentSerialization.deserializeIntentGsonURI(json);

        }

        Log.d(TAG, json);
        if(json.contains("transferMethod")){


            if(json.contains("COPY")){

                this.setTransferMethod(COPY);

            } if(json.contains("REMOTE")){

                this.setTransferMethod(REMOTE);

            } if(json.contains("STREAM")){

                this.setTransferMethod(STREAM);

            } if(json.contains("STREAM_FILE")){

                this.setTransferMethod(STREAM_FILE);

            } if(json.contains("COPY_REPLY")){

                this.setTransferMethod(COPY_REPLY);

            } if (json.contains("REMOTE_REPLY")){

                this.setTransferMethod(REMOTE_REPLY);

            } if(json.contains("STREAM_FILE_REPLY")){

                this.setTransferMethod(STREAM_FILE_REPLY);

            } else if(json.contains("STREAM_REPLY")){

                this.setTransferMethod(STREAM_REPLY);

            }
        }

        if(tempIntent.getAction() != null) {
            this.setAction(tempIntent.getAction());
        }

        if(tempIntent.getData() != null) {
            this.setData(tempIntent.getData());
        }

        if(tempIntent.getType() != null) {
            this.setType(tempIntent.getType());
        }

        if(tempIntent.getExtras() != null) {
            this.putExtras(tempIntent.getExtras());
        }
    }

    public RemoteIntent(String action, Transfer transferMethod){

        this.setAction(action);
        this.setTransferMethod(transferMethod);

    }
}
