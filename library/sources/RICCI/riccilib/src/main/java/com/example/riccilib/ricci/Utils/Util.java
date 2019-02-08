package com.example.riccilib.ricci.Utils;

import android.content.Intent;
import android.util.Log;

import com.example.riccilib.ricci.RemoteIntent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

/**
 * Created by brenocruz on 1/27/17.
 */

public final class Util {

    public static final int REQUEST_COPY_TRANSMISSION = 32;
    public static final int REQUEST_STREAM_TRANSMISSION = 64;
    public static final int REQUEST_REMOTE_TRANSMISSION = 128;
    public static final int REQUEST_STREAM_FILE_TRANSMISSION = 256;
    public static final String ACTION_RESP = "com.example.intent.action.MESSAGE_PROCESSED";
    public static String jsoStringProcessing(String json){

        String processedString = json;

        processedString = processedString.replace("\\u003d", "=");
        processedString.replace("\\\"","");
        processedString.replace("\\", "");

        while(processedString.contains("\\")){

            int begin = processedString.indexOf("\\");
            String temp = processedString.substring(0, begin);
            String temp2 = processedString.substring(begin+1, processedString.length());
            temp = temp+"";
            processedString = temp + temp2;

        }

        processedString.replace("\"\"","");

        return processedString;
    }

    public static String bundleStringProcessing(String bundleContents){


        while(bundleContents.contains("\"json\":\"")){

            String param = "\"json\":\"";
            int b = bundleContents.indexOf(param) + param.length();
            //String temp1 = bundleContents.substring(0, b);
            String temp1 = bundleContents.substring(b, bundleContents.length());

            System.out.println("@@ TEMP : " + temp1);

            param = "\"}";
            b = temp1.lastIndexOf(param);
            temp1 = temp1.substring(0, b);
            //temp2 = temp.substring(b+1, temp.length());

            bundleContents = temp1;

        }

        try {

            if(bundleContents.charAt(bundleContents.length() - 1) != '}'){

                try {

                    if(bundleContents.charAt(bundleContents.length() - 1) == '\"'){

                        bundleContents = bundleContents + "}";

                    } else {

                        bundleContents = bundleContents + "\"}";
                    }
                } catch(IndexOutOfBoundsException e){

                    Log.d("Bundle Contents", "Bad Bundle String - key char not found");
                }
            }

        } catch (IndexOutOfBoundsException e) {

            Log.d("Bundle Contents", "Bad Bundle String - key char not found");

        }


        while(bundleContents.contains("\", \"")){

            String param = "\", \"";
            int b = bundleContents.indexOf(param);
            String temp1 = bundleContents.substring(0, b);
            String temp2 = bundleContents.substring(b + param.length(), bundleContents.length());

            bundleContents = temp1 +", " + temp2;

        }

        while(bundleContents.contains("\"\"")){

            String param = "\"\"";
            int b = bundleContents.indexOf(param);
            String temp1 = bundleContents.substring(0, b);
            String temp2 = bundleContents.substring(b + param.length(), bundleContents.length());

            bundleContents = temp1 +"\"" + temp2;

        }

        return bundleContents;

    }


    public static RemoteIntent intentToRemoteIntent(Intent intent){

        RemoteIntent remoteIntent = new RemoteIntent();

        if(intent.getAction() != null){

            remoteIntent.setAction(intent.getAction());

        }

        if(intent.getExtras() != null){

            remoteIntent.putExtras(intent.getExtras());

        }

        if(intent.getData() != null){

            remoteIntent.setData(intent.getData());

        }

        if(intent.getType() != null){

            remoteIntent.setType(intent.getType());

        }

        return remoteIntent;
    }

    public static String getFileExtension(String name) {

        if(name.contains(".")){

            String extension = name.substring(name.lastIndexOf("."), name.length());
            return extension;
        }

        return "";

    }

    public static void copyDirectoryOneLocationToAnotherLocation(File sourceLocation, File targetLocation)
            throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                targetLocation.mkdir();
            }

            String[] children = sourceLocation.list();
            for (int i = 0; i < sourceLocation.listFiles().length; i++) {

                copyDirectoryOneLocationToAnotherLocation(new File(sourceLocation, children[i]),
                        new File(targetLocation, children[i]));
            }

        } else {

            InputStream in = new FileInputStream(sourceLocation);

            OutputStream out = new FileOutputStream(targetLocation);

            // Copy the bits from instream to outstream
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
        }

    }

    public static long getFolderSize(File f) {
        long size = 0;
        if (f.isDirectory()) {
            for (File file : f.listFiles()) {
                size += getFolderSize(file);
            }
        } else {
            size=f.length();
        }
        return size;
    }

    public static String openFile(File url) {

        String extensionType;
        if (url.toString().contains(".doc") || url.toString().contains(".docx")) {
            // Word document
            extensionType = "application/msword";
        } else if (url.toString().contains(".pdf")) {
            // PDF file
            extensionType = "application/pdf";
        } else if (url.toString().contains(".ppt") || url.toString().contains(".pptx")) {
            // Powerpoint file
            extensionType = "application/vnd.ms-powerpoint";
        } else if (url.toString().contains(".xls") || url.toString().contains(".xlsx")) {
            // Excel file
            extensionType = "application/vnd.ms-excel";
        } else if (url.toString().contains(".zip") || url.toString().contains(".rar")) {
            // WAV audio file
            extensionType = "application/x-wav";
        } else if (url.toString().contains(".rtf")) {
            // RTF file
            extensionType = "application/rtf";
        } else if (url.toString().contains(".wav") || url.toString().contains(".mp3")) {
            // WAV audio file
            extensionType = "audio/*";
        } else if (url.toString().contains(".gif")) {
            // GIF file
            extensionType = "image/gif";
        } else if (url.toString().contains(".jpg") || url.toString().contains(".jpeg") || url.toString().contains(".png")) {
            // JPG file
            extensionType = "image/jpeg";
        } else if (url.toString().contains(".txt")) {
            // Text file
            extensionType = "text/plain";
        } else if (url.toString().contains(".3gp") || url.toString().contains(".mpg") || url.toString().contains(".mpeg") || url.toString().contains(".mpe") || url.toString().contains(".mp4") || url.toString().contains(".avi")) {
            // Video files
            extensionType = "video/*";
        } else {
            //if you want you can also define the intent type for any other file
            //additionally use else clause below, to manage other unknown extensions
            //in this case, Android will show all applications installed on the device
            //so you can choose which application to use
            extensionType = "*/*";
        }

        return extensionType;

    }

    public static String getIPAddress(boolean useIPv4) {

        try {

            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {

                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());

                for (InetAddress addr : addrs) {

                    if (!addr.isLoopbackAddress()) {

                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {

                            if (isIPv4)
                                return sAddr;

                        } else {

                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();

                            }
                        }
                    }
                }
            }

        } catch (Exception ex) { }
        return "";
    }
}
