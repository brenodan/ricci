package com.example.riccilib.ricci.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.util.Log;

/**
 * Created by brenocruz on 2/10/17.
 */

public abstract class RicciWifiP2pBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "P2pBroadcastReceiver";

    private final WifiP2pManager.Channel channel;
    private final Activity activity;
    private final WifiP2pManager manager;

    /**
     * Constructor to set some parameters.
     * @param manager WifiP2pManager system service
     * @param channel Wifi p2p channel
     * @param activity Activity associated with the receiver
     */
    public RicciWifiP2pBroadcastReceiver(WifiP2pManager manager, WifiP2pManager.Channel channel, Activity activity) {
        super();
        this.manager = manager;
        this.channel = channel;
        this.activity = activity;
    }

    public WifiP2pManager.Channel getChannel() {
        return channel;
    }
    public Activity getActivity() {
        return activity;
    }
    public WifiP2pManager getManager() {
        return manager;
    }

    public void handleNetworkConnection(){

        manager.requestConnectionInfo(channel, (WifiP2pManager.ConnectionInfoListener) activity);
        //((MainActivity)activity).setConnected(true);
    }

    public void handleNetworkDisconnection(){

        //if manualItemMenuDisconnectAndStartDiscovery() is not activated by the user
        /*if(!((MainActivity)activity).isBlockForcedDiscoveryInBroadcastReceiver()) {

            //force stop discovery process
            ((MainActivity) activity).forceDiscoveryStop();
            ((MainActivity) activity).restartDiscovery();
        }

        //disable all chatmanagers
        ((MainActivity)activity).setDisableAllChatManagers();
        ((MainActivity)activity).setConnected(false);
        */
        //to be sure that the ip address inside the local device cardview is removed
    }

    public void handleLocalDevice(Intent intent){

        //if is not a disconnect, neither a connect, set the local device
        WifiP2pDevice device = intent.getParcelableExtra(WifiP2pManager.EXTRA_WIFI_P2P_DEVICE);
        Log.d(TAG, "Local Device status -" + device.status);

    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        Log.d(TAG, action);

        if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION.equals(action)) {

            if (manager == null) {
                return;
            }

            NetworkInfo networkInfo = intent.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

            if (networkInfo.isConnected()) {

                // we are connected with the other device, request connection
                // info to find group owner IP
                Log.d(TAG,"Connected to p2p network. Requesting network details");
                handleNetworkConnection();

            } else {
                // It's a disconnect, i need to restart the discovery phase
                Log.d(TAG, "Disconnect. Restarting discovery");
                handleNetworkDisconnection();

            }

        } else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION.equals(action)) {

            Log.d(TAG, "neither connect or disconnect");
            handleLocalDevice(intent);

        }
    }

}
