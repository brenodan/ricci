package it.polimi.deib.p2pchat.discovery;

import android.content.Intent;

import java.io.File;

/**
 * Created by brenocruz on 1/8/18.
 */

public interface RemoteInterface {

    void setData(Intent data);

    long getDataActualSize();

    long getDataSize();

    String getDataToString();

    String getDataClass();

    boolean isDataNull();

    int getDataHashCode();

    boolean isDataEquals(Object dataB);

    boolean isDataDeepEquals(Object dataB);

    boolean isDataNonNull();

    File getFileFromPath();

    String getAction();

    boolean hasBundle();

}
