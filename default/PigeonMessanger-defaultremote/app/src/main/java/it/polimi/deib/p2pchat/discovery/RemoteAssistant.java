package it.polimi.deib.p2pchat.discovery;

/**
 * Created by brenocruz on 1/8/18.
 */

public class RemoteAssistant {

    private boolean getDataActualSize;

    private boolean getDataSize;

    private boolean getDataToString;

    private boolean getDataClass;

    private boolean isDataNull;

    private boolean getDataHashCode;

    private boolean isDataEquals;

    private boolean isDataDeepEquals;

    private boolean isDataNonNull;

    private boolean getAction;

    private boolean hasBundle;

    private boolean getFileFromPath;

    /*private boolean getMethodsFromObjectInBundle;

    private boolean getParametersTypeFromMethodInObjectInBundle;

    private boolean getClassFromObjectInBundle;

    private boolean getReturnTypeFromMethodInObjectInBundle;

    private boolean invokeMethodInObjectInBundle;

    private boolean getMethods;

    private boolean getFields;

    private boolean getAnnotations;

    private boolean getUri;

    private boolean getBundle;

    private boolean getBundleKeys;

    private boolean getObjectFromBundleKey;

    private boolean getObjectsFromBundle;

    private boolean Clone;

    private String keyGetMethodsFromObjectInBundle;

    private String keyGetParametersTypeFromMethodInObjectInBundle;

    private String methodNameGetParametersTypeFromMethodInObjectInBundle;

    private String keyGetClassFromObjectInBundle;

    private String keyGetReturnTypeFromMethodInObjectInBundle;

    private String methodNameGetReturnTypeFromMethodInObjectInBundle;

    private String keyInvokeMethodInObjectInBundle;

    private String methodNameInvokeMethodInObjectInBundle;

    private Set<Object> parametersInvokeMethodInObjectInBundle;

    private String keyGetObjectFromBundleKey;

*/

    public RemoteAssistant(){

        this.getDataActualSize = false;
        this.getDataSize = false;
        this.getDataToString = false;
        this.getDataClass = false;
        this.isDataNull = false;
        this.getDataHashCode = false;
        this.isDataEquals = false;
        this.isDataDeepEquals = false;
        this.isDataNonNull = false;
        this.getFileFromPath = false;
        this.getAction = false;
        this.hasBundle = false;

        /*this.getMethodsFromObjectInBundle = false;
        //this.getParametersTypeFromMethodInObjectInBundle = false;
        this.getClassFromObjectInBundle = false;
        this.getReturnTypeFromMethodInObjectInBundle = false;
        this.invokeMethodInObjectInBundle = false;
        this.getMethods = false;
        this.getFields = false;
        this.getAnnotations = false;
        this.getUri = false;

        this.getBundle = false;
        this.getBundleKeys = false;
        this.getObjectFromBundleKey = false;
        this.getObjectsFromBundle = false;
        this.Clone = false;*/

    }

    public boolean isGetAction() {
        return getAction;
    }

    public void setGetAction(boolean getAction) {
        this.getAction = getAction;
    }

    public boolean isHasBundle() {
        return hasBundle;
    }

    public void setHasBundle(boolean hasBundle) {
        this.hasBundle = hasBundle;
    }

    public boolean isGetDataActualSize() {
        return getDataActualSize;
    }

    public void setGetDataActualSize(boolean getDataActualSize) {
        this.getDataActualSize = getDataActualSize;
    }

    public boolean isGetDataSize() {
        return getDataSize;
    }

    public void setGetDataSize(boolean getDataSize) {
        this.getDataSize = getDataSize;
    }

    public boolean isGetDataToString() {
        return getDataToString;
    }

    public void setGetDataToString(boolean getDataToString) {
        this.getDataToString = getDataToString;
    }

    public boolean isGetDataClass() {
        return getDataClass;
    }

    public void setGetDataClass(boolean getDataClass) {
        this.getDataClass = getDataClass;
    }

    public boolean isDataNull() {
        return isDataNull;
    }

    public void setDataNull(boolean dataNull) {
        isDataNull = dataNull;
    }

    public boolean isGetDataHashCode() {
        return getDataHashCode;
    }

    public void setGetDataHashCode(boolean getDataHashCode) {
        this.getDataHashCode = getDataHashCode;
    }

    public boolean isDataEquals() {
        return isDataEquals;
    }

    public void setDataEquals(boolean dataEquals) {
        isDataEquals = dataEquals;
    }

    public boolean isDataDeepEquals() {
        return isDataDeepEquals;
    }

    public void setDataDeepEquals(boolean dataDeepEquals) {
        isDataDeepEquals = dataDeepEquals;
    }

    public boolean isDataNonNull() {
        return isDataNonNull;
    }

    public void setDataNonNull(boolean dataNonNull) {
        isDataNonNull = dataNonNull;
    }

    public boolean isGetFileFromPath() {
        return getFileFromPath;
    }

    public void setGetFileFromPath(boolean getFileFromPath) {
        this.getFileFromPath = getFileFromPath;
    }
/*
    public boolean isGetMethodsFromObjectInBundle() {
        return getMethodsFromObjectInBundle;
    }

    public void setGetMethodsFromObjectInBundle(boolean getMethodsFromObjectInBundle, String keyGetMethodsFromObjectInBundle) {
        this.getMethodsFromObjectInBundle = getMethodsFromObjectInBundle;
        setKeyGetMethodsFromObjectInBundle(keyGetMethodsFromObjectInBundle);
    }

    public boolean isGetParametersTypeFromMethodInObjectInBundle() {
        return getParametersTypeFromMethodInObjectInBundle;
    }

    public void setGetParametersTypeFromMethodInObjectInBundle(boolean getParametersTypeFromMethodInObjectInBundle,
                                                               String keyGetParametersTypeFromMethodInObjectInBundle,
                                                               String methodNameGetParametersTypeFromMethodInObjectInBundle
                                                               ) {
        this.getParametersTypeFromMethodInObjectInBundle = getParametersTypeFromMethodInObjectInBundle;
        setKeyGetParametersTypeFromMethodInObjectInBundle(keyGetParametersTypeFromMethodInObjectInBundle);
        setMethodNameGetParametersTypeFromMethodInObjectInBundle(methodNameGetParametersTypeFromMethodInObjectInBundle);
    }

    public boolean isGetClassFromObjectInBundle() {
        return getClassFromObjectInBundle;
    }

    public void setGetClassFromObjectInBundle(boolean getClassFromObjectInBundle, String keyGetClassFromObjectInBundle) {
        this.getClassFromObjectInBundle = getClassFromObjectInBundle;
        setKeyGetClassFromObjectInBundle(keyGetClassFromObjectInBundle);
    }

    public boolean isGetReturnTypeFromMethodInObjectInBundle() {
        return getReturnTypeFromMethodInObjectInBundle;
    }

    public void setGetReturnTypeFromMethodInObjectInBundle(boolean getReturnTypeFromMethodInObjectInBundle,
                                                           String keyGetReturnTypeFromMethodInObjectInBundle,
                                                           String methodNameGetReturnTypeFromMethodInObjectInBundle) {
        this.getReturnTypeFromMethodInObjectInBundle = getReturnTypeFromMethodInObjectInBundle;
        setKeyGetReturnTypeFromMethodInObjectInBundle(keyGetReturnTypeFromMethodInObjectInBundle);
        setMethodNameGetParametersTypeFromMethodInObjectInBundle(methodNameGetReturnTypeFromMethodInObjectInBundle);

    }

    public boolean isInvokeMethodInObjectInBundle() {
        return invokeMethodInObjectInBundle;
    }

    public void setInvokeMethodInObjectInBundle(boolean invokeMethodInObjectInBundle,
                                                String keyInvokeMethodInObjectInBundle,
                                                String methodNameInvokeMethodInObjectInBundle,
                                                Set<Object> parametersInvokeMethodInObjectInBundle) {
        this.invokeMethodInObjectInBundle = invokeMethodInObjectInBundle;
        setKeyInvokeMethodInObjectInBundle(keyInvokeMethodInObjectInBundle);
        setMethodNameGetParametersTypeFromMethodInObjectInBundle(methodNameInvokeMethodInObjectInBundle);
        setParametersInvokeMethodInObjectInBundle(parametersInvokeMethodInObjectInBundle);
    }

    public boolean isGetMethods() {
        return getMethods;
    }

    public void setGetMethods(boolean getMethods) {
        this.getMethods = getMethods;
    }

    public boolean isGetFields() {
        return getFields;
    }

    public void setGetFields(boolean getFields) {
        this.getFields = getFields;
    }

    public boolean isGetAnnotations() {
        return getAnnotations;
    }

    public void setGetAnnotations(boolean getAnnotations) {
        this.getAnnotations = getAnnotations;
    }

    public boolean isGetUri() {
        return getUri;
    }

    public void setGetUri(boolean getUri) {
        this.getUri = getUri;
    }

    */


    /*public boolean isGetBundle() {
        return getBundle;
    }

    public void setGetBundle(boolean getBundle) {
        this.getBundle = getBundle;
    }

    public boolean isGetBundleKeys() {
        return getBundleKeys;
    }

    public void setGetBundleKeys(boolean getBundleKeys) {
        this.getBundleKeys = getBundleKeys;
    }

    public boolean isGetObjectFromBundleKey() {
        return getObjectFromBundleKey;
    }

    public void setGetObjectFromBundleKey(boolean getObjectFromBundleKey, String keyGetObjectFromBundleKey) {
        this.getObjectFromBundleKey = getObjectFromBundleKey;
        setKeyGetObjectFromBundleKey(keyGetObjectFromBundleKey);
    }

    public boolean isGetObjectsFromBundle() {
        return getObjectsFromBundle;
    }

    public void setGetObjectsFromBundle(boolean getObjectsFromBundle) {
        this.getObjectsFromBundle = getObjectsFromBundle;
    }

    public boolean isClone() {
        return Clone;
    }

    public void setClone(boolean clone) {
        Clone = clone;
    }

    public String getKeyGetMethodsFromObjectInBundle() {
        return keyGetMethodsFromObjectInBundle;
    }

    public void setKeyGetMethodsFromObjectInBundle(String keyGetMethodsFromObjectInBundle) {
        this.keyGetMethodsFromObjectInBundle = keyGetMethodsFromObjectInBundle;
    }

    public String getKeyGetParametersTypeFromMethodInObjectInBundle() {
        return keyGetParametersTypeFromMethodInObjectInBundle;
    }

    public void setKeyGetParametersTypeFromMethodInObjectInBundle(String keyGetParametersTypeFromMethodInObjectInBundle) {
        this.keyGetParametersTypeFromMethodInObjectInBundle = keyGetParametersTypeFromMethodInObjectInBundle;
    }

    public String getMethodNameGetParametersTypeFromMethodInObjectInBundle() {
        return methodNameGetParametersTypeFromMethodInObjectInBundle;
    }

    public void setMethodNameGetParametersTypeFromMethodInObjectInBundle(String methodNameGetParametersTypeFromMethodInObjectInBundle) {
        this.methodNameGetParametersTypeFromMethodInObjectInBundle = methodNameGetParametersTypeFromMethodInObjectInBundle;
    }

    public String getKeyGetClassFromObjectInBundle() {
        return keyGetClassFromObjectInBundle;
    }

    public void setKeyGetClassFromObjectInBundle(String keyGetClassFromObjectInBundle) {
        this.keyGetClassFromObjectInBundle = keyGetClassFromObjectInBundle;
    }

    public String getKeyGetReturnTypeFromMethodInObjectInBundle() {
        return keyGetReturnTypeFromMethodInObjectInBundle;
    }

    public void setKeyGetReturnTypeFromMethodInObjectInBundle(String keyGetReturnTypeFromMethodInObjectInBundle) {
        this.keyGetReturnTypeFromMethodInObjectInBundle = keyGetReturnTypeFromMethodInObjectInBundle;
    }

    public String getMethodNameGetReturnTypeFromMethodInObjectInBundle() {
        return methodNameGetReturnTypeFromMethodInObjectInBundle;
    }

    public void setMethodNameGetReturnTypeFromMethodInObjectInBundle(String methodNameGetReturnTypeFromMethodInObjectInBundle) {
        this.methodNameGetReturnTypeFromMethodInObjectInBundle = methodNameGetReturnTypeFromMethodInObjectInBundle;
    }

    public String getKeyInvokeMethodInObjectInBundle() {
        return keyInvokeMethodInObjectInBundle;
    }

    public void setKeyInvokeMethodInObjectInBundle(String keyInvokeMethodInObjectInBundle) {
        this.keyInvokeMethodInObjectInBundle = keyInvokeMethodInObjectInBundle;
    }

    public String getMethodNameInvokeMethodInObjectInBundle() {
        return methodNameInvokeMethodInObjectInBundle;
    }

    public void setMethodNameInvokeMethodInObjectInBundle(String methodNameInvokeMethodInObjectInBundle) {
        this.methodNameInvokeMethodInObjectInBundle = methodNameInvokeMethodInObjectInBundle;
    }

    public Set<Object> getParametersInvokeMethodInObjectInBundle() {
        return parametersInvokeMethodInObjectInBundle;
    }

    public void setParametersInvokeMethodInObjectInBundle(Set<Object> parametersInvokeMethodInObjectInBundle) {
        this.parametersInvokeMethodInObjectInBundle = parametersInvokeMethodInObjectInBundle;
    }

    public String getKeyGetObjectFromBundleKey() {
        return keyGetObjectFromBundleKey;
    }

    public void setKeyGetObjectFromBundleKey(String keyGetObjectFromBundleKey) {
        this.keyGetObjectFromBundleKey = keyGetObjectFromBundleKey;
    }

    */

}
