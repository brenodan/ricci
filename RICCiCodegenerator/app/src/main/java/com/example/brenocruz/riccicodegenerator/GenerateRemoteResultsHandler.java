package com.example.brenocruz.riccicodegenerator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GenerateRemoteResultsHandler {
	
	private GenerateRemoteResultsHandler(){}
	
	public static String createRemoteResultsHandler(ObjectDescription object){
		
		String string = "";
		
		string += "\npackage com.example.riccilib.ricci.receiver;";
		
		//imports
		string += "\n\nimport android.content.Intent;";
		string += "\nimport com.example.riccilib.ricci.Utils.RemoteResultsHolder;";
		string += "\nimport static com.example.riccilib.ricci.constants.UtilityConstants.*;";
		
		//header
		string += "\n\npublic class RemoteResultsHandler {";
		
		//contents
		string += "\n\n\tprivate RemoteResultsHandler(){}";
		
		string += "\n\tpublic static RemoteResultsHolder handlerRemoteIntent(Intent intent){";
		string += "\n\n\t\t" + "RemoteResultsHolder remoteResultsHolder = new RemoteResultsHolder();";
	
		int counter = 0;
		boolean flag = false;
		for(int i = 0; i < object.getMethods().length; i++){
			
			Method method = object.getMethods()[i];
			if(!Modifier.isFinal(method.getModifiers())){
				if(method.getReturnType().getSimpleName() != "void"){
				
					if(i != 0){
						if(method.getName() == object.getMethods()[i-1].getName()){
							flag = true;
							counter++;
						}else{
							flag = false;
							counter = 0;
						}
						if(!flag){
							string += "\n\n\t\t" + "if(intent.hasExtra(" + "REMOTE_Get" + method.getName() + ")){";
							string += "\n\n\t\t\t" + method.getReturnType().getSimpleName().replaceAll("[\\[\\]]","") + " data = " + "((" + method.getReturnType().getSimpleName().replaceAll("[\\[\\]]","") +")" +
									"intent.getExtras().get(REMOTE_Get" + method.getName() + "));";
							string += "\n\n\t\t\tremoteResultsHolder.setGet" + method.getName() + "(data);";
							string += "\n\n\t\t}";
						
						} else {
							string += "\n\n\t\t" + "if(intent.hasExtra(" + "REMOTE_Get" + method.getName() + counter + ")){";
							string += "\n\n\t\t\t" + method.getReturnType().getSimpleName().replaceAll("[\\[\\]]","") + " data = " + "((" + method.getReturnType().getSimpleName().replaceAll("[\\[\\]]","") +")" +
									"intent.getExtras().get(REMOTE_Get" + method.getName() + counter + "));";
							string += "\n\n\t\t\tremoteResultsHolder.setGet" + method.getName() + counter + "(data);";
							string += "\n\n\t\t}";
						}
					} else {
						string += "\n\n\t\t" + "if(intent.hasExtra(" + "REMOTE_Get" + method.getName() + ")){";
						string += "\n\n\t\t\t" + method.getReturnType().getSimpleName().replaceAll("[\\[\\]]","") + " data = " + "((" + method.getReturnType().getSimpleName().replaceAll("[\\[\\]]","") +")" +
								"intent.getExtras().get(REMOTE_Get" + method.getName() + "));";
						string += "\n\n\t\t\tremoteResultsHolder.setGet" + method.getName() + "(data);";
						string += "\n\n\t\t}";
					
					}
				}
			}
		}
		
		string += "\n\n\t\treturn remoteResultsHolder;";
		
		string += "\n\t}";
		string += "\n}";
		
		return string;
	}

}
