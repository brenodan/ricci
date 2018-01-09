package it.polimi.deib.p2pchat.discovery.utilities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.util.Collections;
import java.util.List;

import it.polimi.deib.p2pchat.discovery.StreamImplementation;
import it.polimi.deib.p2pchat.discovery.services.BasicIntentService;
import it.polimi.deib.p2pchat.discovery.services.StreamService;
import lipermi.exception.LipeRMIException;
import lipermi.handler.CallHandler;
import lipermi.net.Client;
import lipermi.net.IServerListener;
import lipermi.net.Server;

import static it.polimi.deib.p2pchat.discovery.Configuration.OUT_STREAM_CLIENT_MSG;
import static it.polimi.deib.p2pchat.discovery.Configuration.OUT_STREAM_FILE_CLIENT_MSG;
import static it.polimi.deib.p2pchat.discovery.Configuration.OUT_STREAM_FILE_REPLY_MSG;
import static it.polimi.deib.p2pchat.discovery.Configuration.OUT_STREAM_REPLY_MSG;

/**
 * Created by brenocruz on 1/5/18.
 */

public class StreamingUtils {

    private String TAG = "STREAMING UTILS";

    public StreamingUtils() {
    }

    public StreamService remoteClientHandler(String serverIp) throws IOException {

        CallHandler callHandler = new CallHandler();
        String remoteHost = serverIp;
        int portWasBinded = 4455;

        Client client = new Client(remoteHost, portWasBinded, callHandler);
        StreamService streamObject;
        streamObject = (StreamService) client.getGlobal(StreamService.class);

        return streamObject;

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
                                return sAddr;//getCurrentIP();

                        } else {

                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();

                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
        }
        return "";
    }

    public void streamServerHandler(Intent data, Context context) {

        CallHandler callHandler = new CallHandler();
        StreamService interfaceImplementation = new StreamImplementation();
        interfaceImplementation.setData(data);
        interfaceImplementation.setContext(context);


        try {

            callHandler.registerGlobal(StreamService.class, interfaceImplementation);
            Server server = new Server();
            int thePortIWantToBind = 4455;
            server.bind(thePortIWantToBind, callHandler);
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

    public void receiveStream(RemoteIntent remoteIntent, Context context) {


        Bundle extras = IntentSerialization.jsonStringToBundle((String) remoteIntent.getExtras().get("json"));
        Log.d(TAG, extras.toString());

        String serverIpAddress = null;
        boolean flag = false;
        if (extras.get(OUT_STREAM_FILE_REPLY_MSG) != null) {
            flag = true;
            serverIpAddress = (String) extras.get(OUT_STREAM_FILE_REPLY_MSG);

        } else if (extras.get(OUT_STREAM_REPLY_MSG) != null) {

            serverIpAddress = (String) extras.get(OUT_STREAM_REPLY_MSG);

        } else {

            Log.d(TAG, "SOMETHING IS WRONG!");

        }

        if (!serverIpAddress.equals("false")) {

            Log.d(TAG, "received valid stream response");

            Intent intent = new Intent(context, BasicIntentService.class);

            if (flag) {

                intent.putExtra(OUT_STREAM_FILE_CLIENT_MSG, serverIpAddress);

            } else {

                intent.putExtra(OUT_STREAM_CLIENT_MSG, serverIpAddress);

            }

            context.startService(intent);

        } else {

            Log.d(TAG, "received not valid remote response");

        }

    }
}