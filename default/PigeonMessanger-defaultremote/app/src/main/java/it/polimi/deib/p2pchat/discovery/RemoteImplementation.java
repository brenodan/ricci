package it.polimi.deib.p2pchat.discovery;

import android.content.Intent;
import android.os.Build;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Objects;

/**
 * Created by brenocruz on 1/8/18.
 */

public class RemoteImplementation implements RemoteInterface {


    /**
     * Reference : www.java2s.com/Code/Android/Development/Functionthatgetthesizeofanobject.html
     * With alterations
     * @param data
     * @return long size
     */

    private Intent intent;

    public Intent getIntent() {
        return intent;
    }

    public void setData(Intent intent) {
        this.intent = intent;
    }



    @Override
    public long getDataSize() {

        if (isDataNull()) return -1;

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        ObjectOutputStream objectOutputStream = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            objectOutputStream.writeObject(intent);
            objectOutputStream.flush();
            objectOutputStream.close();

            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return byteArray == null ? 0 : byteArray.length;

        } catch (IOException e) {
            e.printStackTrace();
        }

        Intent intent = new Intent();




        //in case of error
        return -1;

    }

    @Override
    public long getDataActualSize() {
        File f = new File(this.getIntent().getData().getPath());
        long size = f.length();
        return size;
    }

    @Override
    public String getDataToString() {
        return intent.toString();
    }

    @Override
    public String getDataClass() {
        return intent.getClass().toString();
    }

    @Override
    public boolean isDataNull() {
        return (intent == null);
    }

    @Override
    public int getDataHashCode() {
        return intent.hashCode();
    }

    @Override
    public boolean isDataEquals(Object dataB) {
        return this.intent.equals(dataB);
    }


    @Override
    public boolean isDataDeepEquals(Object dataB) {
        return Objects.deepEquals(this.intent, dataB);
    }

    @Override
    public boolean isDataNonNull() {
        return (!isDataNull());
    }

    @Override
    public File getFileFromPath() {

        File f = new File(this.intent.getData().getPath());
        return f;
    }

    @Override
    public String getAction(){

        return getIntent().getAction();

    }

    @Override
    public boolean hasBundle(){

        if (getIntent().getExtras()!= null) {

            return true;

        } else {

            return false;

        }
    }


}
