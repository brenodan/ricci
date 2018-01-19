package com.example.brenocruz.riccicodegenerator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GenerateHandleRemoteAssistant {
	
	private GenerateHandleRemoteAssistant() {}
	
	public static String createHandleRemoteAssistant(ObjectDescription object){
		
		String string = "";
		
		string += "\npackage com.example.riccilib.ricci.handlers;";
		
		string += "\n\nimport android.content.Intent;";
		string += "\nimport com.example.riccilib.ricci.Utils.RemoteAssistant;";
		string += "\nimport com.google.gson.Gson;";
		string += "\nimport static com.example.riccilib.ricci.constants.UtilityConstants.*;";
		string += "\n\npublic class HandleRemoteAssistant {";
		string += "\n\n\tprivate HandleRemoteAssistant(){}";
		string += "\n\n\tpublic static Intent analyzeRemoteAssistant(RemoteAssistant remoteAssistant){";
		string += "\n\n\t\tIntent intent = new Intent();";
		
		int counter = 0;
		//parameters
		boolean flag = false;
		for(int i = 0; i < object.getMethods().length; i++){
			
			Method method = object.getMethods()[i];
			
			if(!Modifier.isFinal(method.getModifiers())){
				
				if(i != 0){
						
					if(method.getName() == object.getMethods()[i-1].getName()){
						flag = true;
						counter++;
					}else{
						flag = false;
						counter = 0;
					}

					if(!flag){
						
						string += "\n\n\t\t" + "if(remoteAssistant." + "isGet" + method.getName() + "()){";
						string += "\n\t\t\t" + "intent.putExtra(REMOTE_Get" + method.getName() + ", true);";
						string += "\n\t\t}";
						
					} else {
							
						string += "\n\n\t\t" + "if(remoteAssistant." + "isGet" + method.getName() + counter +"()){";
						string += "\n\t\t\t" + "intent.putExtra(REMOTE_Get" + method.getName() + counter + ", true);";
						string += "\n\t\t}";
						
					}
				
				}else{
						
					string += "\n\n\t\t" + "if(remoteAssistant." + "isGet" + method.getName() + "()){";
					string += "\n\t\t\t" + "intent.putExtra(REMOTE_Get" + method.getName() + ", true);";
					string += "\n\t\t}";
				}
				
				if(method.getParameterCount() > 0){
				int parameters = method.getParameterCount();
						
					for(int j = 0; j < parameters; j++){
							
						if(!method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("int") 
								&& !method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("long") 
								&& !method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("double") 
								&& !method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("float")){
							string += "\n\n\t\t" + "if(remoteAssistant." + "getGet" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + "() != null){";
						} else {
							string += "\n\n\t\t" + "if(remoteAssistant." + "getGet" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + "() != -1){";
						}
						if(!method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("Object") && 
								!method.getParameterTypes()[j].getSimpleName().equals("char[]")){
							
								string += "\n\t\t\t" + "intent.putExtra(REMOTE_Get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + "," + "remoteAssistant." + "getGet" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + "()"+  ");";
								
						} else {
							
							string += "\n\t\t\t" + "intent.putExtra(REMOTE_Get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + "," + " new Gson().toJson(remoteAssistant." + "getGet" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + "()"+ ")" + ");";
							
						}
					string += "\n\t\t}";
						
					}				
				}
			}
		}
		
		string += "\n\n\treturn intent;";
		string += "\n\t}";
		string += "\n}";
		
		return string;
		
	}

}
