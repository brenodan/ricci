
package com.example.riccilib.ricci.receiver;

import android.content.Intent;
import com.example.riccilib.ricci.Utils.RemoteResultsHolder;
import static com.example.riccilib.ricci.constants.UtilityConstants.*;

public class RemoteResultsHandler {

    private RemoteResultsHandler(){}
    public static RemoteResultsHolder handlerRemoteIntent(Intent intent){

        RemoteResultsHolder remoteResultsHolder = new RemoteResultsHolder();

        if(intent.hasExtra(REMOTE_Getequals)){

            boolean data = ((boolean)intent.getExtras().get(REMOTE_Getequals));

            remoteResultsHolder.setGetequals(data);

        }

        if(intent.hasExtra(REMOTE_GettoString)){

            String data = ((String)intent.getExtras().get(REMOTE_GettoString));

            remoteResultsHolder.setGettoString(data);

        }

        if(intent.hasExtra(REMOTE_GethashCode)){

            int data = ((int)intent.getExtras().get(REMOTE_GethashCode));

            remoteResultsHolder.setGethashCode(data);

        }

        return remoteResultsHolder;
    }
}