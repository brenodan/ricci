package it.polimi.deib.p2pchat.discovery.utilities;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;

import static it.polimi.deib.p2pchat.discovery.utilities.Transfer.COPY;
import static it.polimi.deib.p2pchat.discovery.utilities.Transfer.COPY_REPLY;
import static it.polimi.deib.p2pchat.discovery.utilities.Transfer.REMOTE;
import static it.polimi.deib.p2pchat.discovery.utilities.Transfer.REMOTE_REPLY;
import static it.polimi.deib.p2pchat.discovery.utilities.Transfer.STREAM;
import static it.polimi.deib.p2pchat.discovery.utilities.Transfer.STREAM_FILE;
import static it.polimi.deib.p2pchat.discovery.utilities.Transfer.STREAM_FILE_REPLY;
import static it.polimi.deib.p2pchat.discovery.utilities.Transfer.STREAM_REPLY;

/**
 * Created by brenocruz on 1/5/18.
 */

public class RemoteIntent extends Intent {

    private Transfer transferMethod;

    public String getJson() {

        if(this.getExtras() != null) {

            this.replaceExtras(IntentSerialization.prepareJsonBundle(this.getExtras()));
            System.out.println("EXTRAS IN INTENT : " + this.getExtras());

        }

        return IntentSerialization.serializeIntentGson(this);

    }

    public byte[] getByteArray(){

        return IntentSerialization.returnByteArrayFromString(this.getJson());

    }

    public Transfer getTransferMethod() {

        return this.transferMethod;

    }

    public void setTransferMethod(Transfer transferMethod) {

        this.transferMethod = transferMethod;
    }

    public RemoteIntent(){}

    public RemoteIntent(String action, Uri data){

        this.setAction(action);
        this.setData(data);
        setTransferMethod(COPY);

    }

    public RemoteIntent(Transfer transferMethod){

        setTransferMethod(transferMethod);
    }

    public RemoteIntent(String action, Uri data, Transfer transferMethod){

        this.setAction(action);
        this.setData(data);
        setTransferMethod(transferMethod);

    }

    public RemoteIntent(byte[] inputBytes){

        inputBytes = CompressionUtils.DecompressArray(inputBytes);
        ByteArrayInputStream bis = new ByteArrayInputStream(inputBytes);
        ObjectInput in = null;
        Log.d("REMOTE INTENT", "check for remote intent");

        try {

            in = new ObjectInputStream(bis);
            String json = (String) in.readObject();



            Log.d("UTIL", "RemoteIntent from ByteArray: " + inputBytes);
            Log.d("UTIL", "RemoteIntent from ByteArray: " + json);


            Intent tempIntent;

            if(json.contains("Bundle[")){
                Log.d("REMOTE INTENT", "bundle detected: " + json);
                tempIntent = IntentSerialization.deserializeIntentGsonBundle(json);

            } else {
                Log.d("REMOTE INTENT", "no bundle detected: " +json);
                tempIntent = IntentSerialization.deserializeIntentGsonURI(json);
            }

            Log.d("REMOTE INTENT", json);

            if(json.contains("transferMethod")){

                Log.d("REMOTE INTENT", "transfer method detected");
                if(json.contains("COPY")){
                    Log.d("REMOTE INTENT", "copy transfer method");
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

        Intent tempIntent;

        if(json.contains("Bundle[")){
            Log.d("REMOTE INTENT", "bundle detected: " + json);
            tempIntent = IntentSerialization.deserializeIntentGsonBundle(json);

        } else {
            Log.d("REMOTE INTENT", "no bundle detected: " +json);
            tempIntent = IntentSerialization.deserializeIntentGsonURI(json);

        }

        Log.d("REMOTE INTENT", json);
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