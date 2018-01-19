package com.example.brenocruz.riccicodegenerator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GenerateInterfaceFromObject {

	private GenerateInterfaceFromObject(){}

	public static String createInterfaceFromObject(ObjectDescription object){
		
		String string = "";
		
		string += "package com.example.riccilib.ricci.Utils;";
		
		string += "\n\npublic interface " + "RemoteInterface {\n";
		
		string += "\n\t" + object.getClassNameSimple() + " getObject();";
		string += "\n\tvoid setObject(" + object.getClassNameSimple() +" object);";
		
		int counter = 0;
		boolean flag = false;
		for(int i = 0; i < object.getMethods().length; i++){
			
			Method method = object.getMethods()[i];
			
			if(i != 0){
				
				if(method.getName() == object.getMethods()[i-1].getName()){
					flag = true;
					counter++;
				} else {
					flag = false;
					counter = 0;
				}
				
				if(!flag){
					
					if(!Modifier.isFinal(method.getModifiers()) && !Modifier.isPrivate(method.getModifiers())){
						
						string += "\n\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(";
					
						if(method.getParameterTypes().length != 0){
						
							for(int j = 0; j < method.getParameterTypes().length; j++){
							
								string += method.getParameterTypes()[j].getSimpleName() + " arg"+j;
							
									if(j != method.getParameterTypes().length - 1){
								
										string += ", ";  
									} 
								}
							} 
					
						string += ");";
					}
					
				} else {
					
					if(!Modifier.isFinal(method.getModifiers()) && !Modifier.isPrivate(method.getModifiers())){
						
						string += "\n\t" + method.getReturnType().getSimpleName() + " " + method.getName() + counter + "(";
					
						if(method.getParameterTypes().length != 0){
						
							for(int j = 0; j < method.getParameterTypes().length; j++){
							
								string += method.getParameterTypes()[j].getSimpleName() + " arg"+j;
						
									if(j != method.getParameterTypes().length - 1){
								
										string += ", ";  
									} 
								}
							} 
					
						string += ");";
					
					}
				}
			
			} else {
				
				if(!Modifier.isFinal(method.getModifiers()) && !Modifier.isPrivate(method.getModifiers())){
					
					string += "\n\t" + method.getReturnType().getSimpleName() + " " + method.getName() + "(";
				
					if(method.getParameterTypes().length != 0){
					
						for(int j = 0; j < method.getParameterTypes().length; j++){
						
							string += method.getParameterTypes()[j].getSimpleName() + " arg"+j;
						
								if(j != method.getParameterTypes().length - 1){
							
									string += ", ";  
								} 
							}
						} 
				
					string += ");";
				}
			}
		}
		
		string += "\n\n}";
		
		return string;
	}
}
