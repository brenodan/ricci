package it.polimi.deib.p2pchat.discovery;

import android.content.Intent;

import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getDataActualSize;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getDataClass;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getDataHashCode;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getDataToString;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_getFileFromPath;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_isDataNonNull;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_isDataNull;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_isGetAction;
import static it.polimi.deib.p2pchat.discovery.Configuration.REMOTE_isHasBundle;

/**
 * Created by brenocruz on 1/8/18.
 */

public class HandleRemoteAssistant {

    private HandleRemoteAssistant(){}

    public static Intent analyzeRemoteAssistant(RemoteAssistant remoteAssistant){

        Intent intent = new Intent();

        if(remoteAssistant.isGetDataActualSize()){

            intent.putExtra(REMOTE_getDataActualSize, true);

        }

        if(remoteAssistant.isDataNull()){

            intent.putExtra(REMOTE_isDataNull, true);

        }

        if(remoteAssistant.isDataNonNull()){

            intent.putExtra(REMOTE_isDataNonNull, true);

        }

        if(remoteAssistant.isGetDataToString()){

            intent.putExtra(REMOTE_getDataToString, true);

        }

        if(remoteAssistant.isGetDataClass()){

            intent.putExtra(REMOTE_getDataClass, true);

        }

        if(remoteAssistant.isGetFileFromPath()){

            intent.putExtra(REMOTE_getFileFromPath, true);

        }

        if(remoteAssistant.isGetDataHashCode()){

            intent.putExtra(REMOTE_getDataHashCode, true);

        }


        /*
            if(remoteAssistant.isGetMethodsFromObjectInBundle()){
                if(remoteAssistant.getKeyGetMethodsFromObjectInBundle()!= null){

                    intent.putExtra(REMOTE_getMethodsFromObjectInBundle, true);
                    intent.putExtra(REMOTE_getMethodsFromObjectInBundleKey, remoteAssistant.getKeyGetMethodsFromObjectInBundle());

                }
            }

            if(remoteAssistant.isGetParametersTypeFromMethodInObjectInBundle()){
                if((remoteAssistant.getKeyGetParametersTypeFromMethodInObjectInBundle() != null) &&
                        (remoteAssistant.getMethodNameGetParametersTypeFromMethodInObjectInBundle()!= null)){

                    intent.putExtra(REMOTE_isGetParametersTypeFromMethodInObjectInBundle, true);
                    intent.putExtra(REMOTE_keyGetParametersTypeFromMethodInObjectInBundle, remoteAssistant.getKeyGetParametersTypeFromMethodInObjectInBundle());
                    intent.putExtra(REMOTE_methodNameGetParametersTypeFromMethodInObjectInBundle, remoteAssistant.getMethodNameGetParametersTypeFromMethodInObjectInBundle());

                }
            }

            if(remoteAssistant.isGetClassFromObjectInBundle()){
                if(remoteAssistant.getKeyGetClassFromObjectInBundle() != null){

                    intent.putExtra(REMOTE_isGetClassFromObjectInBundle, true);
                    intent.putExtra(REMOTE_keyGetClassFromObjectInBundle, remoteAssistant.getKeyGetClassFromObjectInBundle());
                }
            }

            if(remoteAssistant.isGetReturnTypeFromMethodInObjectInBundle()){
                if((remoteAssistant.getKeyGetReturnTypeFromMethodInObjectInBundle() != null) &&
                        remoteAssistant.getMethodNameGetReturnTypeFromMethodInObjectInBundle() != null){

                    intent.putExtra(REMOTE_isGetReturnTypeFromMethodInObjectInBundle, true);
                    intent.putExtra(REMOTE_keyGetReturnTypeFromMethodInObjectInBundle, remoteAssistant.getKeyGetReturnTypeFromMethodInObjectInBundle());
                    intent.putExtra(REMOTE_methodNameGetReturnTypeFromMethodInObjectInBundle, remoteAssistant.getMethodNameGetReturnTypeFromMethodInObjectInBundle());

                }
            }

            if(remoteAssistant.isInvokeMethodInObjectInBundle()){
                if((remoteAssistant.getKeyInvokeMethodInObjectInBundle() != null) &&
                        (remoteAssistant.getMethodNameInvokeMethodInObjectInBundle() != null) &&
                            (remoteAssistant.getParametersInvokeMethodInObjectInBundle() != null)){

                    intent.putExtra(REMOTE_isInvokeMethodInObjectInBundle, true);
                    intent.putExtra(REMOTE_keyInvokeMethodInObjectInBundle, remoteAssistant.getKeyInvokeMethodInObjectInBundle());
                    intent.putExtra(REMOTE_methodNameInvokeMethodInObjectInBundle, remoteAssistant.getMethodNameInvokeMethodInObjectInBundle());
                    Object[] temp = remoteAssistant.getParametersInvokeMethodInObjectInBundle().toArray(new Object[remoteAssistant.getParametersInvokeMethodInObjectInBundle().size()]);
                    intent.putExtra(REMOTE_parametersInvokeMethodInObjectInBundle, new Gson().toJson(temp));

                }
            }

            if(remoteAssistant.isGetMethods()){

                intent.putExtra(REMOTE_isGetMethods, true);

            }

            if(remoteAssistant.isGetFields()){

                intent.putExtra(REMOTE_isGetFields, true);

            }

            if(remoteAssistant.isGetAnnotations()){

                intent.putExtra(REMOTE_isGetAnnotations, true);

            }

            if(remoteAssistant.isGetUri()){

                intent.putExtra(REMOTE_isGetUri, true);

            }
            */
        if(remoteAssistant.isGetAction()){

            intent.putExtra(REMOTE_isGetAction, true);

        }

        if(remoteAssistant.isHasBundle()){

            intent.putExtra(REMOTE_isHasBundle, true);

        }
            /*
            if(remoteAssistant.isGetBundle()){

                intent.putExtra(REMOTE_isGetBundle, true);

            }

            if(remoteAssistant.isGetBundleKeys()){

                intent.putExtra(REMOTE_isGetBundleKeys, true);

            }

            if(remoteAssistant.isGetObjectFromBundleKey()){

                if(remoteAssistant.getKeyGetObjectFromBundleKey() != null){

                    intent.putExtra(REMOTE_isGetObjectFromBundleKey, true);
                    intent.putExtra(REMOTE_keyGetObjectFromBundleKey, remoteAssistant.getKeyGetObjectFromBundleKey());
                }
            }

            if(remoteAssistant.isGetObjectsFromBundle()){

                intent.putExtra(REMOTE_isGetObjectsFromBundle, true);

            }

            if(remoteAssistant.isClone()){

                intent.putExtra(REMOTE_isClone, true);

            }
            */

        return intent;
    }

}
