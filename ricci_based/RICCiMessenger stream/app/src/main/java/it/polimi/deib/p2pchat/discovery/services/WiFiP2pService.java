
package it.polimi.deib.p2pchat.discovery.services;

/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2015-2016 Stefano Cappa
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.net.wifi.p2p.WifiP2pDevice;


/**
 * A structure to hold service information.
 */
public class WiFiP2pService {

    private WifiP2pDevice device;
    private String instanceName = null;
    private String serviceRegistrationType = null;

    public WifiP2pDevice getDevice() {
        return device;
    }

    public void setDevice(WifiP2pDevice device) {
        this.device = device;
    }

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getServiceRegistrationType() {
        return serviceRegistrationType;
    }

    public void setServiceRegistrationType(String serviceRegistrationType) {
        this.serviceRegistrationType = serviceRegistrationType;
    }
}
