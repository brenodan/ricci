package com.example.riccilib.ricci.Utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import com.example.riccilib.lipermi.exception.LipeRMIException;
import com.example.riccilib.lipermi.handler.CallHandler;
import com.example.riccilib.lipermi.net.Client;
import com.example.riccilib.lipermi.net.IServerListener;
import com.example.riccilib.lipermi.net.Server;
import com.example.riccilib.ricci.RemoteIntent;
import com.example.riccilib.ricci.handlers.HandleRemoteAssistant;
import com.example.riccilib.ricci.services.BasicIntentService;
import com.google.gson.Gson;

import static com.example.riccilib.ricci.constants.UtilityConstants.*;
/**
 * Created by brenocruz on 1/27/17.
 */

public class RemoteUtils {

    private String TAG = "REMOTE UTILS";

    private Object remoteSerializableObject;
    private RemoteAssistant remoteAssistant;

    public RemoteUtils(){}

    public RemoteUtils(Object remoteSerializableObject, RemoteAssistant remoteAssistant){

        this.remoteSerializableObject = remoteSerializableObject;
        this.remoteAssistant = remoteAssistant;

    }

    public RemoteUtils(RemoteAssistant remoteAssistant){

        this.remoteSerializableObject = null;
        this.remoteAssistant = remoteAssistant;

    }

    public RemoteInterface remoteClientHandler(String serverIp) throws IOException {

        CallHandler callHandler = new CallHandler();
        String remoteHost = serverIp;
        int portWasBinded = 4455;

        Client client = new Client(remoteHost, portWasBinded, callHandler);
        RemoteInterface remoteObject;
        remoteObject = (RemoteInterface) client.getGlobal(RemoteInterface.class);

        return remoteObject;

    }

    public void remoteServerHandler(){

        CallHandler callhandler = new CallHandler();
        RemoteInterface interfaceImplementation = new RemoteImplementation();
        try {
            callhandler.registerGlobal(RemoteImplementation.class, interfaceImplementation);
        } catch (LipeRMIException e) {
            e.printStackTrace();
        }

        Server server = new Server();
        int thePortIWantToBind = 4455;
        try {
            server.bind(thePortIWantToBind, callhandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remoteServerHandler(Intent data){

        CallHandler callhandler = new CallHandler();
        RemoteInterface interfaceImplementation = new RemoteImplementation();
        interfaceImplementation.setObject(data);

        try {

            callhandler.registerGlobal(RemoteInterface.class, interfaceImplementation);
            Server server = new Server();
            int thePortIWantToBind = 4455;
            server.bind(thePortIWantToBind, callhandler);
            server.addServerListener(new IServerListener() {

                @Override
                public void clientConnected(Socket socket) {
                    Log.d(TAG, "Client Connected: " + socket.getInetAddress());
                }

                @Override
                public void clientDisconnected(Socket socket) {
                    Log.d(TAG, "Client Disconnected: " + socket.getInetAddress());
                }
            });

            Log.d(TAG, "Server is listening");

        } catch (LipeRMIException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getIPAddress(boolean useIPv4) {

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

    public void receiveRemote(RemoteIntent remoteIntent, Context context){

        IntentSerialization intentSerialization = new IntentSerialization();
        Bundle extras = intentSerialization.jsonStringToBundle((String)remoteIntent.getExtras().get("json"));
        Log.d(TAG, " Receive Remote : " + extras.toString());

        String serverIpAddress = (String)extras.get(OUT_REMOTE_REPLY_MSG);

        if(!serverIpAddress.equals("false")) {

            Log.d(TAG, "received valid remote response");
            Intent intent = new Intent(context, BasicIntentService.class);

            Intent temp = HandleRemoteAssistant.analyzeRemoteAssistant(remoteAssistant);
            Bundle bundle = temp.getExtras();
            intent.replaceExtras(bundle);
            intent.putExtra(OUT_REMOTE_CLIENT_MSG, serverIpAddress);


            context.startService(intent);

        } else {

            Log.d(TAG, "received not valid remote response");

        }

    }


}
