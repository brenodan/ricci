package it.polimi.deib.p2pchat.discovery;

import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;

import it.polimi.deib.p2pchat.discovery.utilities.RemoteIntent;
import it.polimi.deib.p2pchat.discovery.utilities.Transfer;

/**
 * Created by brenocruz on 3/6/17.
 */

public class TestClass {

    private TestClass(){}

    public static String sendCopyIntent(){

        RemoteIntent remoteIntent = new RemoteIntent(Intent.ACTION_GET_CONTENT, Transfer.STREAM);
        remoteIntent.addCategory(Intent.CATEGORY_OPENABLE);
        remoteIntent.setType("*/*");
        Log.d("TestClass", "returning remote intent : " + remoteIntent.toString());
        return SerializeDeserialize.SerializeIntent(remoteIntent);
    }

}
