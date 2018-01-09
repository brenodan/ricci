package it.polimi.deib.p2pchat.discovery;

import android.content.Intent;
import android.provider.ContactsContract;
import android.util.Log;

/**
 * Created by brenocruz on 3/6/17.
 */

public class TestClass {

    private TestClass(){}

    public static String sendCopyIntent(){

        Intent remoteIntent = new Intent(Intent.ACTION_PICK);
        remoteIntent.setData(ContactsContract.Contacts.CONTENT_URI);
        remoteIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE);
        Log.d("TestClass", "returning remote intent : " + remoteIntent.toString());
        return SerializeDeserialize.SerializeIntent(remoteIntent);
    }

}
