package it.polimi.deib.p2pchat.discovery;

import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.riccilib.ricci.RemoteIntent;
import com.example.riccilib.ricci.constants.Transfer;


/**
 * This class contains a set of methods that return
 * @ricci.RemoteIntents objects. These objects will be used for testing purposes.
 * Created by Breno Cruz on 1/30/17.
 */

public class RicciTestCases {

    public static final String TAG = "RICCITEST";

    /**
    public static RemoteIntent getCopyRemoteIntent(){

        RemoteIntent remoteIntent = new RemoteIntent(Intent.ACTION_PICK, Transfer.COPY);
        remoteIntent.setData(ContactsContract.Contacts.CONTENT_URI);
        remoteIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        Log.d(TAG, "returning remote intent : " + remoteIntent.toString());
        return remoteIntent;
    }
     */

    public static RemoteIntent getStreamIntent(){

        RemoteIntent remoteIntent = new RemoteIntent(Intent.ACTION_GET_CONTENT, Transfer.STREAM);
        remoteIntent.addCategory(Intent.CATEGORY_OPENABLE);
        remoteIntent.setType("*/*");
        return remoteIntent;

    }
}
