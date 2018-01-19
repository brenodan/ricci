
package com.example.riccilib.ricci.handlers;

import android.content.Intent;
import static com.example.riccilib.ricci.Utils.Util.ACTION_RESP;
import com.example.riccilib.ricci.Utils.RemoteInterface;
import static com.example.riccilib.ricci.constants.UtilityConstants.*;

public class ReceiveRemoteHandler {

	private ReceiveRemoteHandler(){}

	public static Intent receiveRemoteHandler(RemoteInterface remoteObject, Intent intent){

		boolean flag = false;
		Intent returnIntent = new Intent();

		if(intent.hasExtra(REMOTE_Getequals)){

			returnIntent.putExtra(REMOTE_Getequals, remoteObject.equals((Object)intent.getExtras().get(REMOTE_GetObjectarg0equals0)));
			flag = true;

		}

		if(intent.hasExtra(REMOTE_GettoString)){

			returnIntent.putExtra(REMOTE_GettoString, remoteObject.toString());
			flag = true;

		}

		if(intent.hasExtra(REMOTE_GethashCode)){

			returnIntent.putExtra(REMOTE_GethashCode, remoteObject.hashCode());
			flag = true;

		}
		returnIntent.putExtra(IN_REMOTE_ACCESS_RESPONSE, String.valueOf(flag));
		returnIntent.setAction(ACTION_RESP);
		returnIntent.addCategory(Intent.CATEGORY_DEFAULT);

		return returnIntent;
	}
}