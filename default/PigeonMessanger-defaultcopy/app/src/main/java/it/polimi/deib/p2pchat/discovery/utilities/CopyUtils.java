package it.polimi.deib.p2pchat.discovery.utilities;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by brenocruz on 3/7/17.
 */

public class CopyUtils {

    public static Intent receiveCopy(Intent intent){

        Bundle extras = intent.getExtras();
        String json = (String) extras.get("json");
        extras = IntentSerialization.jsonStringToBundle(json);

        Intent responseIntent = new Intent();
        responseIntent.putExtras(extras);

        return responseIntent;

    }

    public static Intent processDataForTransmission(Cursor cursor){

        String[] columnNames = cursor.getColumnNames();
        cursor.moveToFirst();

        Bundle extras = new Bundle();

        for(int i = 0; i < columnNames.length; i++){

            int index = cursor.getColumnIndex(columnNames[i]);
            String dataString = cursor.getString(index);

            if(dataString != null) {
                Log.d("SEND COPY REPLY", " COLUMN NAME " + columnNames[i] + " : data string : " + dataString );
                extras.putSerializable(columnNames[i], dataString);
            }
        }

        Intent sendIntent = new Intent();
        sendIntent.putExtras(IntentSerialization.prepareJsonBundle(extras));

        return sendIntent;

    }
}
