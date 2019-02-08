
package com.example.riccilib.ricci.handlers;

import android.content.Intent;
import com.example.riccilib.ricci.Utils.RemoteAssistant;
import com.google.gson.Gson;
import static com.example.riccilib.ricci.constants.UtilityConstants.*;

public class HandleRemoteAssistant {

    private HandleRemoteAssistant(){}

    public static Intent analyzeRemoteAssistant(RemoteAssistant remoteAssistant){

        Intent intent = new Intent();

        if(remoteAssistant.isGetequals()){
            intent.putExtra(REMOTE_Getequals, true);
        }

        if(remoteAssistant.getGetObjectarg0equals0() != null){
            intent.putExtra(REMOTE_GetObjectarg0equals0, new Gson().toJson(remoteAssistant.getGetObjectarg0equals0()));
        }

        if(remoteAssistant.isGettoString()){
            intent.putExtra(REMOTE_GettoString, true);
        }

        if(remoteAssistant.isGethashCode()){
            intent.putExtra(REMOTE_GethashCode, true);
        }

        return intent;
    }
}