package com.example.brenocruz.riccicodegenerator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GenerateReceiveRemoteHandler {

	private GenerateReceiveRemoteHandler(){}
	
	public static String createReceiveRemoteHandler(ObjectDescription object){
		
		String string = "";
		
		string += "\npackage com.example.riccilib.ricci.handlers;";
		
		//imports
		string += "\n\nimport android.content.Intent;";
		string += "\nimport static com.example.riccilib.ricci.Utils.Util.ACTION_RESP;";
		string += "\nimport com.example.riccilib.ricci.Utils.RemoteInterface;";
		string += "\nimport static com.example.riccilib.ricci.constants.UtilityConstants.*;";
		
		string += "\n\npublic class ReceiveRemoteHandler {";
		string += "\n\n\tprivate ReceiveRemoteHandler(){}";
		string += "\n\n\tpublic static Intent receiveRemoteHandler(RemoteInterface remoteObject, Intent intent){";
		string += "\n\n\t\tboolean flag = false;";
		string += "\n\t\tIntent returnIntent = new Intent();";
		
		int counter = 0;
		boolean flag = false;
		for(int i = 0; i < object.getMethods().length; i++){
			
			Method method = object.getMethods()[i];
			
			if(!Modifier.isFinal(method.getModifiers())){
				
				if(i != 0){
						
					if(method.getName() == object.getMethods()[i-1].getName()){
						flag = true;
						counter++;
					} else {
						flag = false;
						counter = 0;
					}
						
					if(!flag){
							
						if(method.getParameterCount() == 0){
								
							string += "\n\n\t\t" + "if(intent.hasExtra(" + "REMOTE_Get" + method.getName() + ")){";
							
							if(method.getReturnType().getSimpleName().equals("void")){
								
								string += "\n\n\t\t\t" + "remoteObject." + method.getName() +"());";
								
							} else {
							
								string += "\n\n\t\t\treturnIntent.putExtra(" + "REMOTE_Get" + method.getName() + ", " + "remoteObject." + method.getName() +"());";
								
							}
							string += "\n\t\t\tflag = true;";
							string += "\n\n\t\t}";
						
						} else {
						
							string += "\n\n\t\t" + "if(intent.hasExtra(" + "REMOTE_Get" + method.getName() + ")){";
							
							if(method.getReturnType().getSimpleName().equals("void")){
								
								int parameters = method.getParameterCount();
								string += "\n\n\t\t\tremoteObject." + method.getName() +"(";
								
								
								for(int j = 0; j < parameters; j++){	
									string += "(" + method.getParameterTypes()[j].getSimpleName() + ")" + "intent.getExtras().get(" + "REMOTE_Get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + 
											"arg" + j + method.getName() + counter ;
										
									if(j != parameters - 1){
										
										string += "), ";  
									
									}else{
										string += ")";
									} 				
								}	
								
								string += ");";
								string += "\n\t\t\tflag = true;";
								string += "\n\n\t\t}";
								
							} else {
							
								int parameters = method.getParameterCount();
								string += "\n\n\t\t\treturnIntent.putExtra(" + "REMOTE_Get" + method.getName() + ", " 
									+ "remoteObject." + method.getName() +"(";
								
								
								for(int j = 0; j < parameters; j++){	
									string += "(" + method.getParameterTypes()[j].getSimpleName() + ")" + "intent.getExtras().get(" + "REMOTE_Get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter ;
										
									if(j != parameters - 1){
										
										string += "), ";  
									
									}else{
										string += ")";
									} 				
								}	
								string += "));";
								string += "\n\t\t\tflag = true;";
								string += "\n\n\t\t}";
							}
								
							
						}
						
					} else {
						
						if(method.getParameterCount() == 0){
						
							string += "\n\n\t\t" + "if(intent.hasExtra(" + "REMOTE_Get" + method.getName() + counter + ")){";
							
							if(method.getReturnType().getSimpleName().equals("void")){
								
								string += "\n\n\t\t\t" + "remoteObject." + method.getName() +"());";
								
							} else { 
							
								string += "\n\n\t\t\treturnIntent.putExtra(" + "REMOTE_Get" + method.getName() + counter + ", " + "remoteObject." + method.getName() + counter +"());";
								
							}
							
							string += "\n\t\t\tflag = true;";
							string += "\n\n\t\t}";
							
						} else {
							
							string += "\n\n\t\t" + "if(intent.hasExtra(" + "REMOTE_Get" + method.getName() + counter + ")){";
							
							if(method.getReturnType().getSimpleName().equals("void")){
							
								int parameters = method.getParameterCount();
								string += "\n\n\t\t\tremoteObject." + method.getName() +"(";
								
								
								for(int j = 0; j < parameters; j++){	
									string += "(" + method.getParameterTypes()[j].getSimpleName() + ")" + "intent.getExtras().get(" + "REMOTE_Get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + 
											"arg" + j + method.getName() + counter ;
										
									if(j != parameters - 1){
										
										string += "), ";  
									
									}else{
										string += ")";
									} 				
								}	
								
								string += ");";
								string += "\n\t\t\tflag = true;";
								string += "\n\n\t\t}";
								
							} else {
							
								int parameters = method.getParameterCount();
								string += "\n\n\t\t\treturnIntent.putExtra(" + "REMOTE_Get" + method.getName() + counter + ", " 
									+ "remoteObject." + method.getName() + counter +"(";
							
								for(int j = 0; j < parameters; j++){	
									
									string += "intent.getExtras().get(" + "REMOTE_Get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter;
									if(j != parameters - 1){
									
										string += "), ";  
								
									} else {
										string += ")";
									} 				
								}	
							
								string += "));";
								string += "\n\t\t\tflag = true;";
								string += "\n\n\t\t}";
							}
						}
					}
				
				} else {
					
					if(method.getParameterCount() == 0){
						
						string += "\n\n\t\t" + "if(intent.hasExtra(" + "REMOTE_Get" + method.getName() + ")){";
						
						if(method.getReturnType().getSimpleName().equals("void")){
							
							string += "\n\n\t\t\t" + "remoteObject." + method.getName() +"());";
							
						} else {
						
							string += "\n\n\t\t\treturnIntent.putExtra(" + "REMOTE_Get" + method.getName() + ", " + "remoteObject." + method.getName() +"());";
							
						}
						string += "\n\t\t\tflag = true;";
						string += "\n\n\t\t}";
					
					} else {
					
						string += "\n\n\t\t" + "if(intent.hasExtra(" + "REMOTE_Get" + method.getName() + ")){";
						
						if(method.getReturnType().getSimpleName().equals("void")){
							
							int parameters = method.getParameterCount();
							string += "\n\n\t\t\tremoteObject." + method.getName() +"(";
							
							
							for(int j = 0; j < parameters; j++){	
								string += "(" + method.getParameterTypes()[j].getSimpleName() + ")" + "intent.getExtras().get(" + "REMOTE_Get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + 
										"arg" + j + method.getName() + counter ;
									
								if(j != parameters - 1){
									
									string += "), ";  
								
								}else{
									string += ")";
								} 				
							}	
							
						string += ");";
						string += "\n\t\t\tflag = true;";
						string += "\n\n\t\t}";
							
						} else {
						
							int parameters = method.getParameterCount();
							string += "\n\n\t\t\treturnIntent.putExtra(" + "REMOTE_Get" + method.getName() + ", " 
								+ "remoteObject." + method.getName() +"(";
							
							
							for(int j = 0; j < parameters; j++){	
								string += "intent.getExtras().get(" + "REMOTE_Get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter ;
									
								if(j != parameters - 1){
									
									string += "), ";  
								
								}else{
									string += ")";
								} 				
							}
							string += "));";
							string += "\n\t\t\tflag = true;";
							string += "\n\n\t\t}";
						}
					}
				}
			}
		}
		
		string += "\n\t\treturnIntent.putExtra(IN_REMOTE_ACCESS_RESPONSE, String.valueOf(flag));";
		string += "\n\t\treturnIntent.setAction(ACTION_RESP);";
		string += "\n\t\treturnIntent.addCategory(Intent.CATEGORY_DEFAULT);";
		
		string += "\n\n\t\treturn returnIntent;";
		
		string += "\n\t}";
		string += "\n}";
		
		return string;
	}
	
	
}
