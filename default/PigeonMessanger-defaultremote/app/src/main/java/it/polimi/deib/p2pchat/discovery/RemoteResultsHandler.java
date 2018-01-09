package it.polimi.deib.p2pchat.discovery;

import android.content.Intent;
import android.util.Log;

import java.io.File;

import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getDataActualSize;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getDataClass;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getDataHashCode;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getDataSize;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getDataToString;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getFileFromPath;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_isDataDeepEquals;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_isDataEquals;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_isDataNull;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_isGetAction;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_isHasBundle;

/**
 * Created by brenocruz on 1/8/18.
 */

public class RemoteResultsHandler {

    private RemoteResultsHandler(){}
    private static String TAG = "REMOTE results";
    public static RemoteResultsHolder handlerRemoteIntent(Intent intent){

        RemoteResultsHolder remoteResultsHolder = new RemoteResultsHolder();

        if(intent.hasExtra(REMOTE_getDataClass)){

            String dataClass = ((String)intent.getExtras().get(REMOTE_getDataClass));
            remoteResultsHolder.setGetDataClass(dataClass);
        }

        if(intent.hasExtra(REMOTE_getDataActualSize)){

            long actualSize = Long.parseLong((String) intent.getExtras().get(REMOTE_getDataActualSize));
            remoteResultsHolder.setGetDataActualSize(actualSize);

        }

        if(intent.hasExtra(REMOTE_getDataToString)){

            String dataToString = (String)intent.getExtras().get(REMOTE_getDataToString);
            remoteResultsHolder.setGetDataToString(dataToString);

        }

        if(intent.hasExtra(REMOTE_getDataHashCode)){

            int hashCode = (Integer)intent.getExtras().get(REMOTE_getDataHashCode);
            remoteResultsHolder.setGetDataHashCode(hashCode);

        }

        if(intent.hasExtra(REMOTE_getFileFromPath)){

            File filePath = (File) intent.getExtras().get(REMOTE_getFileFromPath);
            remoteResultsHolder.setGetFileFromPath(filePath);

        }

        if(intent.hasExtra(REMOTE_getDataSize)){

            long dataSize = Long.parseLong((String) intent.getExtras().get(REMOTE_getDataSize));
            remoteResultsHolder.setGetDataSize(dataSize);


        }

        if(intent.hasExtra(REMOTE_isDataEquals)){

            boolean isDeepEquals = Boolean.parseBoolean((String)intent.getExtras().get(REMOTE_isDataEquals));
            remoteResultsHolder.setDataDeepEquals(isDeepEquals);

        }

        if(intent.hasExtra(REMOTE_isDataDeepEquals)){

            boolean isDeepEquals = Boolean.parseBoolean((String)intent.getExtras().get(REMOTE_isDataDeepEquals));
            remoteResultsHolder.setDataEquals(isDeepEquals);

        }

        if(intent.hasExtra(REMOTE_isDataNull)){

            boolean isNull = Boolean.parseBoolean((String)intent.getExtras().get(REMOTE_isDataNull));
            remoteResultsHolder.setDataNull(isNull);

        }
        /*
        //check
        if(intent.hasExtra(REMOTE_getMethodsFromObjectInBundle)){

            Log.d(TAG, "setGetMethodsFromObjectInBundle");
            String json = (String)intent.getExtras().get(REMOTE_getMethodsFromObjectInBundle);
            Method[] methods = new Gson().fromJson(json, Method[].class);
            remoteResultsHolder.setGetMethodsFromObjectInBundle(methods);

        }
        //check
        if(intent.hasExtra(REMOTE_isGetParametersTypeFromMethodInObjectInBundle)){

            Log.d(TAG, "setGetParametersTypeFromMethodInObjectInBundle");
            String json = (String)intent.getExtras().get(REMOTE_isGetParametersTypeFromMethodInObjectInBundle);
            Class[] classes = new Gson().fromJson(json, Class[].class);
            remoteResultsHolder.setGetParametersTypeFromMethodInObjectInBundle(classes);

        }
        //check
        if(intent.hasExtra(REMOTE_isGetClassFromObjectInBundle)){

            Log.d(TAG, "setGetClassFromObjectInBundle");
            String json = (String)intent.getExtras().get(REMOTE_isGetClassFromObjectInBundle);
            Class _class = new Gson().fromJson(json, Class.class);
            remoteResultsHolder.setGetClassFromObjectInBundle(_class);

        }
        //check
        if(intent.hasExtra(REMOTE_isGetReturnTypeFromMethodInObjectInBundle)){

            Log.d(TAG, "setGetReturnTypeFromMethodInObjectInBundle");
            String json = (String)intent.getExtras().get(REMOTE_isGetReturnTypeFromMethodInObjectInBundle);
            Class returnedClass = new Gson().fromJson(json, Class.class);
            remoteResultsHolder.setGetReturnTypeFromMethodInObjectInBundle(returnedClass);

        }
        //check
        if(intent.hasExtra(REMOTE_isInvokeMethodInObjectInBundle)){

            Log.d(TAG, "setInvokeMethodInObjectInBundle");
            String json = (String)intent.getExtras().get(REMOTE_isInvokeMethodInObjectInBundle);
            Object object = new Gson().fromJson(json, Object.class);
            remoteResultsHolder.setInvokeMethodInObjectInBundle(object);

        }
        //check
        if(intent.hasExtra(REMOTE_isGetMethods)){

            Log.d(TAG, "setGetMethods");
            String json = (String)intent.getExtras().get(REMOTE_isGetMethods);
            Method[] methods = new Gson().fromJson(json, Method[].class);
            remoteResultsHolder.setGetMethods(methods);

        }
        //check
        if(intent.hasExtra(REMOTE_isGetFields)){

            Log.d(TAG, "setGetFields");
            String json = (String)intent.getExtras().get(REMOTE_isGetFields);
            Field[] field = new Gson().fromJson(json, Field[].class);
            remoteResultsHolder.setGetFields(field);
        }
        //check
        if(intent.hasExtra(REMOTE_isGetAnnotations)){

            Log.d(TAG, "setGetAnnotations");
            String json = (String)intent.getExtras().get(REMOTE_isGetAnnotations);
            Annotation[] annotations = new Gson().fromJson(json, Annotation[].class);
            remoteResultsHolder.setGetAnnotations(annotations);

        }
        //check
        if(intent.hasExtra(REMOTE_isGetUri)){

            Log.d(TAG, "setGetUri");
            String json = (String)intent.getExtras().get(REMOTE_isGetUri);
            Uri uri = new Gson().fromJson(json, Uri.class);
            remoteResultsHolder.setGetUri(uri);

        }
        */ //check
        if(intent.hasExtra(REMOTE_isGetAction)){

            Log.d(TAG, "setGetAction");
            remoteResultsHolder.setGetAction((String)intent.getExtras().get(REMOTE_isGetAction));

        }
        //check
        if(intent.hasExtra(REMOTE_isHasBundle)){
            Log.d(TAG, "setHasBundle");
            remoteResultsHolder.setHasBundle((boolean)intent.getExtras().get(REMOTE_isHasBundle));

        }
        /*
        //check
        if(intent.hasExtra(REMOTE_isGetBundle)){
            Log.d(TAG, "setGetBundle");
            String json = (String)intent.getExtras().get(REMOTE_isGetBundle);
            Bundle bundle = new Gson().fromJson(json, Bundle.class);
            remoteResultsHolder.setGetBundle(bundle);

        }
        //check
        if(intent.hasExtra(REMOTE_isGetBundleKeys)){
            Log.d(TAG, "setGetBundleKeys");
            String json = (String)intent.getExtras().get(REMOTE_isGetBundleKeys);
            String[] temp = new Gson().fromJson(json, String[].class);
            Set<String> keys = new HashSet<String>(Arrays.asList(temp));
            remoteResultsHolder.setGetBundleKeys(keys);
        }
        //check
        if(intent.hasExtra(REMOTE_isGetObjectFromBundleKey)){

            Log.d(TAG, "setGetObjectFromBundleKey");
            String json = (String)intent.getExtras().get(REMOTE_isGetObjectFromBundleKey);
            Object object = new Gson().fromJson(json, Object.class);
            remoteResultsHolder.setGetObjectFromBundleKey(object);
        }
        //check
        if(intent.hasExtra(REMOTE_isGetObjectsFromBundle)){

            Log.d(TAG, "setGetObjectsFromBundle");
            String json = (String)intent.getExtras().get(REMOTE_isGetObjectsFromBundle);
            Object[] temp = new Gson().fromJson(json, Object[].class);
            Set<Object> objects  = new HashSet<Object>(Arrays.asList(temp));
            remoteResultsHolder.setGetObjectsFromBundle(objects);
        }
        //check
        if(intent.hasExtra(REMOTE_isClone)){

            Log.d(TAG, "setClone");
            String json = (String)intent.getExtras().get(REMOTE_isClone);
            Object object = new Gson().fromJson(json, Object.class);
            remoteResultsHolder.setClone(object);

        }*/


        return remoteResultsHolder;
    }

}
