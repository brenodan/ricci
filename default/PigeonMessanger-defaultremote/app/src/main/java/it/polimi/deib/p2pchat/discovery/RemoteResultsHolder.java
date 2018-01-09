package it.polimi.deib.p2pchat.discovery;

import android.os.Bundle;

import java.io.File;
import java.util.Set;

/**
 * Created by brenocruz on 1/8/18.
 */

public class RemoteResultsHolder {

    private long getDataActualSize;

    private long getDataSize;

    private String getDataToString;

    private String getDataClass;

    private boolean isDataNull;

    private int getDataHashCode;

    private boolean isDataEquals;

    private boolean isDataDeepEquals;

    private boolean isDataNonNull;

    private File getFileFromPath;

    private String getAction;

    private boolean hasBundle;

    public String getGetAction() {
        return getAction;
    }

    public void setGetAction(String getAction) {
        this.getAction = getAction;
    }

    public boolean isHasBundle() {
        return hasBundle;
    }

    public void setHasBundle(boolean hasBundle) {
        this.hasBundle = hasBundle;
    }

    /*private Method[] getMethodsFromObjectInBundle;

    private Class[] getParametersTypeFromMethodInObjectInBundle;

    private Class getClassFromObjectInBundle;

    private Class getReturnTypeFromMethodInObjectInBundle;

    private Object invokeMethodInObjectInBundle;

    private Method[] getMethods;

    private Field[] getFields;

    private Annotation[] getAnnotations;

    private Uri getUri;
    */


    private Bundle getBundle;

    private Set<String> getBundleKeys;

    private Object getObjectFromBundleKey;

    private Set<Object> getObjectsFromBundle;

    private Object Clone;

    /*public Method[] getGetMethodsFromObjectInBundle() {
        return getMethodsFromObjectInBundle;
    }

    public void setGetMethodsFromObjectInBundle(Method[] getMethodsFromObjectInBundle) {
        this.getMethodsFromObjectInBundle = getMethodsFromObjectInBundle;
    }

    public Class[] getGetParametersTypeFromMethodInObjectInBundle() {
        return getParametersTypeFromMethodInObjectInBundle;
    }

    public void setGetParametersTypeFromMethodInObjectInBundle(Class[] getParametersTypeFromMethodInObjectInBundle) {
        this.getParametersTypeFromMethodInObjectInBundle = getParametersTypeFromMethodInObjectInBundle;
    }

    public Class getGetClassFromObjectInBundle() {
        return getClassFromObjectInBundle;
    }

    public void setGetClassFromObjectInBundle(Class getClassFromObjectInBundle) {
        this.getClassFromObjectInBundle = getClassFromObjectInBundle;
    }

    public Class getGetReturnTypeFromMethodInObjectInBundle() {
        return getReturnTypeFromMethodInObjectInBundle;
    }

    public void setGetReturnTypeFromMethodInObjectInBundle(Class getReturnTypeFromMethodInObjectInBundle) {
        this.getReturnTypeFromMethodInObjectInBundle = getReturnTypeFromMethodInObjectInBundle;
    }

    public Object getInvokeMethodInObjectInBundle() {
        return invokeMethodInObjectInBundle;
    }

    public void setInvokeMethodInObjectInBundle(Object invokeMethodInObjectInBundle) {
        this.invokeMethodInObjectInBundle = invokeMethodInObjectInBundle;
    }

    public Method[] getGetMethods() {
        return getMethods;
    }

    public void setGetMethods(Method[] getMethods) {
        this.getMethods = getMethods;
    }

    public Field[] getGetFields() {
        return getFields;
    }

    public void setGetFields(Field[] getFields) {
        this.getFields = getFields;
    }

    public Annotation[] getGetAnnotations() {
        return getAnnotations;
    }

    public void setGetAnnotations(Annotation[] getAnnotations) {
        this.getAnnotations = getAnnotations;
    }

    public Uri getGetUri() {
        return getUri;
    }

    public void setGetUri(Uri getUri) {
        this.getUri = getUri;
    }

    */

    public Bundle getGetBundle() {
        return getBundle;
    }

    public void setGetBundle(Bundle getBundle) {
        this.getBundle = getBundle;
    }

    public Set<String> getGetBundleKeys() {
        return getBundleKeys;
    }

    public void setGetBundleKeys(Set<String> getBundleKeys) {
        this.getBundleKeys = getBundleKeys;
    }

    public Object getGetObjectFromBundleKey() {
        return getObjectFromBundleKey;
    }

    public void setGetObjectFromBundleKey(Object getObjectFromBundleKey) {
        this.getObjectFromBundleKey = getObjectFromBundleKey;
    }

    public Set<Object> getGetObjectsFromBundle() {
        return getObjectsFromBundle;
    }

    public void setGetObjectsFromBundle(Set<Object> getObjectsFromBundle) {
        this.getObjectsFromBundle = getObjectsFromBundle;
    }

    public Object getClone() {
        return Clone;
    }

    public void setClone(Object clone) {
        Clone = clone;
    }



    public long getGetDataActualSize() {
        return getDataActualSize;
    }

    public void setGetDataActualSize(long getDataActualSize) {
        this.getDataActualSize = getDataActualSize;
    }

    public long getGetDataSize() {
        return getDataSize;
    }

    public void setGetDataSize(long getDataSize) {
        this.getDataSize = getDataSize;
    }

    public String getGetDataToString() {
        return getDataToString;
    }

    public void setGetDataToString(String getDataToString) {
        this.getDataToString = getDataToString;
    }

    public String getGetDataClass() {
        return getDataClass;
    }

    public void setGetDataClass(String getDataClass) {
        this.getDataClass = getDataClass;
    }

    public boolean isDataNull() {
        return isDataNull;
    }

    public void setDataNull(boolean dataNull) {
        isDataNull = dataNull;
    }

    public int getGetDataHashCode() {
        return getDataHashCode;
    }

    public void setGetDataHashCode(int getDataHashCode) {
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

    public File getGetFileFromPath() {
        return getFileFromPath;
    }

    public void setGetFileFromPath(File getFileFromPath) {
        this.getFileFromPath = getFileFromPath;
    }


}