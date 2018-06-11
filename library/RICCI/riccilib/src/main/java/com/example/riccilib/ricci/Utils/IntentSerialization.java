package com.example.riccilib.ricci.Utils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.Set;

import com.example.riccilib.ricci.RemoteIntent;

/**
 * Created by brenocruz on 1/27/17.
 */

public class IntentSerialization {

    private final String TAG = "IntentSerialization";

    public String serializeIntent(Intent intent){

        Bundle extras = intent.getExtras();

        if (extras != null){

            Intent temp = new Intent();
            temp.setAction(intent.getAction());
            temp.setData(intent.getData());
            temp.setType(intent.getType());
            temp.putExtras(prepareJsonBundle(extras));

            return serializeIntentGson(temp);

        } else {

            return serializeIntentGson(intent);

        }
    }

    public Intent deserializeIntentGson(String message) {

        if(message.contains("\\u003d")) {
            while (message.contains("\\u003d")) {

                int begin = message.indexOf("\\u003d");
                String temp = message.substring(0, begin);
                String temp2 = message.substring(begin + 6, message.length());
                temp = temp + "=";
                message = temp + temp2;

            }
        }

        //Gson gson = new GsonBuilder().registerTypeAdapter(Uri.class, new UriDeserializer()).create();
        Gson gson = new Gson();

        Intent intent = gson.fromJson(message, Intent.class);
        Log.d(TAG, "Received the message " + intent.toString());

        return intent;

    }

    public Intent deserializeIntentGsonURI(String message) {

        while(message.contains("\\u003d")){

            int begin = message.indexOf("\\u003d");
            String temp = message.substring(0, begin);
            String temp2 = message.substring(begin+6, message.length());
            temp = temp+"=";
            message = temp + temp2;

        }

        if(message.contains("\"mData\":")){

            String param1 = ("\"mData\":");
            String param2 = (",\"mFlags\":");
            int begin = message.indexOf(param1);
            String temp1 = message.substring(0, begin);
            String temp2 = message.substring(begin, message.indexOf(param2));
            String temp3 = message.substring(message.indexOf(param2)+1, message.length());

            message = temp1 + temp3;

            Log.d(TAG, "message : " + message);
            Log.d(TAG, "URI : " + temp2);

            begin = temp2.indexOf(param1)+param1.length();
            temp2 = temp2.substring(begin, temp2.length());

            Gson gson = new GsonBuilder().registerTypeAdapter(Uri.class, new UriDeserializer()).create();
            Uri uri = gson.fromJson(temp2, Uri.class);
            Log.d(TAG, uri.toString());

        }

        Gson gson = new GsonBuilder().registerTypeAdapter(Uri.class, new UriDeserializer()).create();
        Log.d(TAG, "received json: " + message);
        Intent intent = gson.fromJson(message, Intent.class);

        return intent;

    }

    //Deserialize the bundle inside an intent
    public Intent deserializeIntentGsonBundle(String message) {

        message = Util.jsoStringProcessing(message);
        Log.d(TAG, message);
        String temp = "Bundle[{json=";
        String bundleContents = "";

        int begin = 0;
        int end = 0;

        try {

            begin = message.indexOf(temp);
            end = message.indexOf("}]");

        } catch (IndexOutOfBoundsException e){

            Log.d(TAG, "BUNDLE CONSTRUCTION : " + e.getStackTrace());

        }

        bundleContents = message.substring(begin + temp.length(), end);
        bundleContents = Util.bundleStringProcessing(bundleContents);
        Log.d(TAG, bundleContents);

        try {

            begin = message.indexOf("mExtras")-2;
            end = message.indexOf("}}]") + 4;

        } catch (IndexOutOfBoundsException e) {

            Log.d(TAG, "BUNDLE CONSTRUCTION : " + e.getStackTrace());

        }

        temp = message.substring(0, begin) + message.substring(end, message.length());
        temp = temp.replace("\"intentSerialization\":{},", "");

        if(temp.contains("transferMethod")){

            temp = temp.replace("\"transferMethod\":", "");

            if(temp.contains("\"COPY_REPLY\",")){

                temp = temp.replace("\"COPY_REPLY\",", "");

            } else if(temp.contains("\"REMOTE_REPLY\",")){

                temp = temp.replace("\"REMOTE_REPLY\",", "");

            } else if(temp.contains("\"STREAM_FILE_REPLY\",")){

                temp = temp.replace("\"STREAM_FILE_REPLY\",", "");

            } else if(temp.contains("\"STREAM_REPLY\",")){

                temp = temp.replace("\"STREAM_REPLY\",", "");

            } else if(temp.contains("\"COPY\",")){

                temp = temp.replace("\"COPY\",", "");

            } else if(temp.contains("\"REMOTE\",")){

                temp = temp.replace("\"REMOTE\",", "");

            } else if(temp.contains("\"STREAM_FILE\",")){

                temp = temp.replace("\"STREAM_FILE\",", "");

            } else if(temp.contains("\"STREAM\",")){

                temp = temp.replace("\"STREAM\",", "");

            }
        }

        Bundle extras = jsonStringToBundle(bundleContents);
        Intent intent = deserializeIntentGsonURI(temp);
        intent.putExtras(prepareJsonBundle(extras));

        return intent;

    }

    //Receives a string in json and converts it to Bundle
    public Bundle bundleStringProcessing(String message){

        while(message.contains("\\u003d")){

            int begin = message.indexOf("\\u003d");
            String temp = message.substring(0, begin);
            String temp2 = message.substring(begin+6, message.length());
            temp = temp+"=";
            message = temp + temp2;

        }

        int begin = message.indexOf("Bundle[");
        int end = message.indexOf("}]");

        String bundleString = message.substring(begin, end);

        if(message.contains("mExtras")) {
            begin = message.indexOf("mExtras");
            end = message.indexOf("}]\"");

            message = message.substring(0, begin) + message.substring(end + 4, message.length());
        }

        Log.d(TAG, "BUNDLE PROCESSING :" + message);
        Log.d(TAG, "BUNDLE STRING :" + bundleString);

        Bundle extras = jsonStringToBundle(bundleString);
        return extras;
    }

    //Serializes an Intent to a String. This method uses GSON to serialize the INTENT
    public String serializeIntentGson(Intent myObject) {

        Gson gson = new GsonBuilder().registerTypeAdapter(Bundle.class, new BundleSerializer()).create();
        String json = gson.toJson(myObject);
        Log.d(TAG, json);
        return json;

    }

    public class UriSerializer implements JsonSerializer<Uri> {
        public JsonElement serialize(Uri src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

    public class UriDeserializer implements JsonDeserializer<Uri> {
        @Override
        public Uri deserialize(final JsonElement src, final Type srcType,
                               final JsonDeserializationContext context) throws JsonParseException {
            return Uri.parse(src.getAsString());
        }
    }

    public class BundleSerializer implements JsonSerializer<Bundle> {
        public JsonElement serialize(Bundle src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

    public class BundleDeserializer implements JsonDeserializer<Bundle> {
        @Override
        public Bundle deserialize(final JsonElement src, final Type srcType,
                                  final JsonDeserializationContext context) throws JsonParseException {
            return bundleStringProcessing(src.getAsString());
        }
    }

    public Bundle jsonStringToBundle(String jsonString){
        try {
            JSONObject jsonObject = toJsonObject(jsonString);
            return jsonToBundle(jsonObject);
        } catch (JSONException ignored) {

        }
        return null;
    }

    public JSONObject toJsonObject(String jsonString) throws JSONException {
        return new JSONObject(jsonString);
    }

    public Bundle jsonToBundle(JSONObject jsonObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator iterator = jsonObject.keys();
        while(iterator.hasNext()){
            String key = (String)iterator.next();
            String value = jsonObject.getString(key);
            bundle.putString(key,value);
        }
        return bundle;
    }

    public Bundle prepareJsonBundle(Bundle bundle){

        JSONObject json = new JSONObject();
        Set<String> keys = bundle.keySet();
        for (String key : keys) {
            try {
                json.put(key, JSONObject.wrap(bundle.get(key)));
            } catch(JSONException e) {
                Log.d(TAG, e.toString());
            }
        }

        Bundle extras = new Bundle();
        extras.putSerializable("json", json.toString());
        Log.d(TAG, json.toString());

        return extras;
    }

    public byte[] returnByteArrayFromString(String inputString){

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutput out = null;
        byte[] yourBytes = null;

        try {

            out = new ObjectOutputStream(bos);
            out.writeObject(inputString);
            out.flush();
            yourBytes = bos.toByteArray();

        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            try {
                bos.close();

            } catch (IOException ex) {
                // ignore close exception
            }
        }

        return CompressionUtils.CompressArray(yourBytes);

    }

    public RemoteIntent returnRemoteIntentFromByteArray(byte[] inputBytes){


        inputBytes = CompressionUtils.DecompressArray(inputBytes);

        ByteArrayInputStream bis = new ByteArrayInputStream(inputBytes);
        ObjectInput in = null;
        RemoteIntent remoteIntent = new RemoteIntent();

        try {

            in = new ObjectInputStream(bis);
            String json = (String) in.readObject();
            Log.d(TAG, "RemoteIntent from ByteArray: " + json);
            remoteIntent = new RemoteIntent(json);

        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            try {

                if(in != null) {

                    in.close();

                }

            } catch (IOException ex) {
                Log.d(TAG, ex.toString());
            }
        }

        return remoteIntent;

    }
}

