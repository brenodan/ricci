package it.polimi.deib.p2pchat.discovery.socketmanagers;

/**
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
 *
 **/

import android.os.Handler;
import android.util.Log;

import com.example.riccilib.ricci.Utils.RicciChatManager;

import java.net.Socket;

/**
 * Created by Breno D. Cruz
 */
public class ChatManager extends RicciChatManager {

    private String TAG = "ChatManager";
    /**
     * Constructor of the class
     * @param socket Represents the {@link java.net.Socket} required in order to communicate
     * @param handler Represents the Handler required in order to communicate
     */
    public ChatManager(Socket socket, Handler handler) {
        super(handler, socket);
        Log.d(TAG, "Created ChatManager");
    }

}
