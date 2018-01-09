package it.polimi.deib.p2pchat.discovery;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import it.polimi.deib.p2pchat.discovery.utilities.CopyUtils;
import it.polimi.deib.p2pchat.discovery.utilities.IntentSerialization;

/**
 * Created by brenocruz on 3/6/17.
 */

public class SerializeDeserialize {

    private SerializeDeserialize(){}

    public static String SerializeIntent(Intent intent){

        try{

            if(intent.getExtras() != null) {

                intent.replaceExtras(IntentSerialization.prepareJsonBundle(intent.getExtras()));
                System.out.println("EXTRAS IN INTENT : " + intent.getExtras());
            }

            String json = IntentSerialization.serializeIntentGson(intent);
            System.out.println("json ==>" + json);
            return json;

        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static Intent DeserializeIntent(String json){

        Intent tempIntent;

        try {

            Intent intent = new Intent();

            if (json.contains("Bundle[")) {
                Log.d("REMOTE INTENT", "bundle detected: " + json);
                tempIntent = IntentSerialization.deserializeIntentGsonBundle(json);

            } else {
                Log.d("REMOTE INTENT", "no bundle detected: " + json);
                tempIntent = IntentSerialization.deserializeIntentGsonURI(json);

            }

            Log.d("REMOTE INTENT", json);

            if (tempIntent.getAction() != null) {
                intent.setAction(tempIntent.getAction());
            }

            if (tempIntent.getData() != null) {
                intent.setData(tempIntent.getData());
            }

            if (tempIntent.getType() != null) {
                intent.setType(tempIntent.getType());
            }

            if (tempIntent.getExtras() != null) {
                intent.putExtras(tempIntent.getExtras());
            }

            return intent;

        } catch(Exception e) {

            e.printStackTrace();
        }

        return null;

    }
}
