package com.example.brenocruz.riccicodegenerator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GenerateRemoteImplementationFromObject {
	
	private GenerateRemoteImplementationFromObject(){}
	
	public static String createRemoteImplementationFromObject(ObjectDescription object){
		
		String string = "";
		
		string += "\npackage com.example.riccilib.ricci.Utils;";
		
		string += "\n\npublic class " + "RemoteImplementation implements" + " RemoteInterface " + "{\n";
		
		string += "\n\tprivate " + object.getClassNameSimple() + " object;";
		string += "\n\n\t@Override";
		string += "\n\tpublic " + object.getClassNameSimple() + " getObject() { return object; }";
		string += "\n\n\t@Override";
		string += "\n\tpublic void setObject(" + object.getClassNameSimple() + " object) { this.object = object; }";
		
		int counter = 0;
		boolean flag = false;
		for(int i = 0; i < object.getMethods().length; i++){
			
			Method method = object.getMethods()[i];
			
			if(!Modifier.isFinal(method.getModifiers()) && !Modifier.isPrivate(method.getModifiers())){
			
				if(i != 0){
						
					if(method.getName() == object.getMethods()[i-1].getName()){
						flag = true;
						counter++;
					} else {
						flag = false;
						counter = 0;
					}
					
					if(!flag){
							
						string += "\n\n\t@Override";
						string += "\n\tpublic " + method.getReturnType().getSimpleName() + " " + method.getName() + "(";
				
						if(method.getParameterTypes().length != 0){
					
							for(int j = 0; j < method.getParameterTypes().length; j++){
						
								string += method.getParameterTypes()[j].getSimpleName() + " arg"+j;
						
								if(j != method.getParameterTypes().length - 1){
							
									string += ", ";  
								} 
							}
						} 
				
						string += ") {\n";
							
						string += "\n\t\t" + "try {"; 
						
						if(!method.getReturnType().getSimpleName().equals("void")){	
							string += "\n\t\t\treturn this.getObject()." + method.getName() + "(";
						} else {
							string += "\n\t\t\tthis.getObject()." + method.getName() + "(";
						}
						if(method.getParameterTypes().length != 0){
					
							for(int j = 0; j < method.getParameterTypes().length; j++){
							
								string += " arg"+j;
						
								if(j != method.getParameterTypes().length - 1){
							
									string += ", ";  
							
								} 
							}
						}
					
						string += ");";
							
						string += "\n\t\t} catch (Exception e) {";
						string += "\n\t\t\te.printStackTrace();";
						string += "\n\t\t}";
						if(method.getReturnType().getSimpleName().equals("int") || (method.getReturnType().getSimpleName().equals("float")) 
								||(method.getReturnType().getSimpleName().equals("double")) || (method.getReturnType().getSimpleName().equals("long"))){
									string += "\n\treturn -1;";
								} else if(method.getReturnType().getSimpleName().equals("boolean")) {
									string += "\n\treturn false;";
								} else if(method.getReturnType().getSimpleName().equals("void")){
									string += "\n\treturn;";
								} else {
									string += "\n\treturn null;";
								}
				
						string += "\n\t}";
							
					} else {
							
						string += "\n\n\t@Override";
						string += "\n\tpublic " + method.getReturnType().getSimpleName() + " " + method.getName() + counter + "(";
				
						if(method.getParameterTypes().length != 0){
					
							for(int j = 0; j < method.getParameterTypes().length; j++){
						
								string += method.getParameterTypes()[j].getSimpleName() + " arg"+j;
						
								if(j != method.getParameterTypes().length - 1){
							
									string += ", ";  
								} 
							}
						} 
				
						string += ") {\n";
							
						string += "\n\t\t" + "try {"; 
						
						string += "\n\t\treturn this.getObject()." + method.getName() + "(";
				
						if(method.getParameterTypes().length != 0){
					
							for(int j = 0; j < method.getParameterTypes().length; j++){
							
								string += " arg"+j;
						
								if(j != method.getParameterTypes().length - 1){
							
									string += ", ";  
							
								} 
							}
						}
					
						string += ");";
						
						string += "\n\t\t} catch (Exception e) {";
						string += "\n\t\t\te.printStackTrace();";
						string += "\n\t\t}";
						if(method.getReturnType().getSimpleName().equals("int") || (method.getReturnType().getSimpleName().equals("float")) 
						||(method.getReturnType().getSimpleName().equals("double")) || (method.getReturnType().getSimpleName().equals("long"))){
							string += "\n\treturn -1;";
						} else if(method.getReturnType().getSimpleName().equals("boolean")) {
							string += "\n\treturn false;";
						} else if(method.getReturnType().getSimpleName().equals("void")){
							string += "\n\treturn;";
						} else {
							string += "\n\treturn null;";
						}
						string += "\n\t}";
							
					}
				}
			}
		}
		
		string += "\n}";
		
		return string;
		
	}
}
