package com.example.riccilib.ricci.Utils;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

/**
 * Created by brenocruz on 1/27/17.
 */

public class CopyUtils {

    private static final String TAG = "CopyUtils";

    public Intent receiveCopy(Intent intent){

        Bundle extras = intent.getExtras();
        String json = (String) extras.get("json");
        Log.d(TAG, json);
        IntentSerialization intentSerialization = new IntentSerialization();
        extras = intentSerialization.jsonStringToBundle(json);

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
                Log.d(TAG, "SEND COPY REPLY -- COLUMN NAME " + columnNames[i] + " : data string : " + dataString );
                extras.putSerializable(columnNames[i], dataString);
            }
        }

        Intent sendIntent = new Intent();
        IntentSerialization intentSerialization = new IntentSerialization();
        sendIntent.putExtras(intentSerialization.prepareJsonBundle(extras));

        return sendIntent;

    }
}
