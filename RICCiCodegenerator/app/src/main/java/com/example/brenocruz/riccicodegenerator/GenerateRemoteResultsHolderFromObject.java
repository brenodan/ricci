package com.example.brenocruz.riccicodegenerator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class GenerateRemoteResultsHolderFromObject {

	private GenerateRemoteResultsHolderFromObject(){}
	
public static String createRemoteResultsHolderFromObject(ObjectDescription object){
		
		String string = "";
		
		string += "\npackage com.example.riccilib.ricci.Utils;";
		
		//header
		string += "\n\npublic class RemoteResultsHolder {\n\n";
		int counter = 0;
		boolean flag = false;
		//parameters
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
							string += "\n\t" + "private " + method.getReturnType().getSimpleName() + " "  + " get" + method.getName() + ";";
						} else {
							string += "\n\t" + "private " + method.getReturnType().getSimpleName() + " "  + " get" + method.getName() + counter + ";";
						}
					}else{
						string += "\n\t" + "private " + method.getReturnType().getSimpleName() + " "  + " get" + method.getName() + ";";
					}
				}	
			}
		}
		
		//gets
		string += "\n";
		counter = 0;
		flag = false;
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
							string += "\n\t" + "public " + method.getReturnType().getSimpleName() + " getGet" + method.getName() + "() { return get" + method.getName() + "; }";
						} else {
							string += "\n\t" + "public " + method.getReturnType().getSimpleName() + " getGet" + method.getName() + counter + "() { return get" + method.getName() + counter +"; }";
						}
					}else{
						string += "\n\t" + "public " + method.getReturnType().getSimpleName() + " getGet" + method.getName() + "() { return get" + method.getName() + "; }";
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
							string += "\n\t" + "public void" + " setGet" + method.getName() + "(" + method.getReturnType().getSimpleName() + " get" + method.getName() + " ) { this.get" + method.getName() + " = get" + method.getName()+"; }";
						} else {
							string += "\n\t" + "public void" + " setGet" + method.getName() + counter + "(" + method.getReturnType().getSimpleName() + " get" + method.getName() + counter + " ) { this.get" + method.getName() + counter + " = get" + method.getName() + counter +"; }";
						}
					}else{
						string += "\n\t" + "public void" + " setGet" + method.getName() + "(" + method.getReturnType().getSimpleName() + " get" + method.getName() + " ) { this.get" + method.getName() + " = get" + method.getName()+"; }";
					}
				}
			}
		}
		
		string += "\n\n}";
		
		return string;
	}
	
}
