package com.example.brenocruz.riccicodegenerator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GenerateRemoteAssistantFromObject {
	
	private GenerateRemoteAssistantFromObject(){}
	
	public static String createRemoteAssistantFromObject(ObjectDescription object){
		
		String string = "";
		
		string += "\npackage com.example.riccilib.ricci.Utils;";;
		
		string += "\n\nimport java.nio.charset.*;";
		string += "\nimport java.util.*;";
		
		//header
		string += "\n\npublic class RemoteAssistant {\n\n";
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
						string += "\n\t" + "private boolean" + " get" + method.getName() + ";";
					} else {
						string += "\n\t" + "private boolean" + " get" + method.getName() + counter +";";
					}
				}else{
					string += "\n\t" + "private boolean" + " get" + method.getName() + ";";
				}
				if(method.getParameterCount() > 0){
				
					int parameters = method.getParameterCount();
					for(int j = 0; j < parameters; j++){	
						string += "\n\t" + "private " + method.getParameterTypes()[j].getSimpleName() + " get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + ";";					
					}				
				}
			}
		}
		
		//constructor
		string += "\n\n\t"+"public RemoteAssistant() {\n";
		counter = 0;
		flag = false;
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
						string += "\n\t\t" + "this." + "get" + method.getName() + " = false;";
					} else {
						string += "\n\t\t" + "this." + "get" + method.getName() + counter + " = false;";
					}
				}else{
					string += "\n\t\t" + "this." + "get" + method.getName() + " = false;";
				}
				if(method.getParameterCount() > 0){
					
					int parameters = method.getParameterCount();
					for(int j = 0; j < parameters; j++){	
							
						if(!method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("int") 
								&& !method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("long") 
								&& !method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("double") 
								&& !method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("float")
								&& !method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("char")
								&& !method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").equals("boolean")
								|| method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").contains("[]")){
							
							string += "\n\t\t" + "this." + "get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + " = null;";
						
						} else if (method.getParameterTypes()[j].getSimpleName().contains("char")) {
								
							if(method.getParameterTypes()[j].getSimpleName().equals("char[]")){
								string += "\n\t\t" + "this." + "get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + " = null;";
							}else{
								string += "\n\t\t" + "this." + "get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + " = \' \';";
							}
						
						} else if (method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","").equals("boolean")) {
							string += "\n\t\t" + "this." + "get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + " = false;";
						} else {
							string += "\n\t\t" + "this." + "get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + " = -1;";
						}
					}				
				}
			}	
		}
				
		string +="\n\t}";
		
		//gets
		string += "\n";
		counter = 0;
		flag = false;
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
						string += "\n\t" + "public boolean" + " isGet" + method.getName() + "() { return get" + method.getName() +"; }";
					} else {
						string += "\n\t" + "public boolean" + " isGet" + method.getName() + counter + "() { return get" + method.getName() + counter +"; }";
					}
				} else {
					string += "\n\t" + "public boolean" + " isGet" + method.getName() + "() { return get" + method.getName() +"; }";
				}
				if(method.getParameterCount() > 0){
				
					int parameters = method.getParameterCount();
					for(int j = 0; j < parameters; j++){						
						string += "\n\t" + "public " + method.getParameterTypes()[j].getSimpleName() + " getGet" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + "() { return get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","")  + "arg" + j + method.getName() + counter + "; }";					
					}				
				}
			}
		}
		
		//sets
		string += "\n";
		counter = 0;
		flag = false;
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
						string += "\n\t" + "public void" + " setGet" + method.getName() + "(boolean get" + method.getName() + " ) { this.get" + method.getName() +" = get" + method.getName() + "; }";
					} else {
						string += "\n\t" + "public void" + " setGet" + method.getName() + counter + "(boolean get" + method.getName() + counter +" ) { this.get" + method.getName() + counter + " = get" + method.getName() + counter + "; }";
					}
				}else{
					string += "\n\t" + "public void" + " setGet" + method.getName() + "(boolean get" + method.getName() + " ) { this.get" + method.getName() +" = get" + method.getName() + "; }";
				}
				if(method.getParameterCount() > 0){
				
					int parameters = method.getParameterCount();
					for(int j = 0; j < parameters; j++){						
						string += "\n\t" + "public void" + " setGet" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + "(" 
								+ method.getParameterTypes()[j].getSimpleName() +" get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter +" ) { this.get" +
								method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + " = " +" get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + "; }";					
					}				
				}
			}
		}
		
		string += "\n\n}";
		
		return string;
	}
	

}
