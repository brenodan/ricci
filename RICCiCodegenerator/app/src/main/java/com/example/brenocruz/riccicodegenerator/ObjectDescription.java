package com.example.brenocruz.riccicodegenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ObjectDescription {

	private Method[] methods;
	private Class objectClass;
	private int objectModifiers;
	private Package packageInfo;
	private Class superClass;
	private Class[] interfaces;
	private Constructor[] constructors;
	private Field[] fields;
	private Annotation[] annotations;
	
	
	ObjectDescription(Object object){
		
		this.setObjectClass(object.getClass());
		this.setMethods(this.getObjectClass().getMethods());
		this.setObjectModifiers(this.getObjectClass().getModifiers());
		this.setPackageInfo(this.getObjectClass().getPackage());
		this.setSuperClass(this.getObjectClass().getSuperclass());
		this.setInterfaces(this.getObjectClass().getInterfaces());
		this.setConstructors(this.getObjectClass().getConstructors());
		this.setFields(this.getObjectClass().getFields());
		this.setAnnotations(this.getObjectClass().getAnnotations());
	}

	public String getClassName(){
		
		return this.objectClass.getName();
		
	}
	
	public String getClassNameSimple(){
		
		return this.objectClass.getSimpleName();
		
	}

	public Method[] getMethods() {
		return methods;
	}


	public void setMethods(Method[] methods) {
		this.methods = methods;
	}


	public Class getObjectClass() {
		return objectClass;
	}


	public void setObjectClass(Class objectClass) {
		this.objectClass = objectClass;
	}


	public int getObjectModifiers() {
		return objectModifiers;
	}


	public void setObjectModifiers(int objectModifiers) {
		this.objectModifiers = objectModifiers;
	}


	public Package getPackageInfo() {
		return packageInfo;
	}


	public void setPackageInfo(Package packageInfo) {
		this.packageInfo = packageInfo;
	}


	public Class getSuperClass() {
		return superClass;
	}


	public void setSuperClass(Class superClass) {
		this.superClass = superClass;
	}


	public Class[] getInterfaces() {
		return interfaces;
	}


	public void setInterfaces(Class[] interfaces) {
		this.interfaces = interfaces;
	}


	public Constructor[] getConstructors() {
		return constructors;
	}


	public void setConstructors(Constructor[] constructors) {
		this.constructors = constructors;
	}


	public Field[] getFields() {
		return fields;
	}


	public void setFields(Field[] fields) {
		this.fields = fields;
	}


	public Annotation[] getAnnotations() {
		return annotations;
	}


	public void setAnnotations(Annotation[] annotations) {
		this.annotations = annotations;
	}
	
	public String toString(){
		
		String string = "";
		
		if(this.getSuperClass()!= null){
			string += "\nsuper class : " + this.getSuperClass().toString();
		}
		if(this.getObjectClass().toString() != null){
			string += "\nclass : " + this.getObjectClass().getSimpleName();
		}
		if(this.getObjectModifiers() != -1){
			string += "\nmodifiers : " + this.getObjectModifiers();
		}
		if(this.getPackageInfo() != null){
			string += "\npackage info : " + this.getPackageInfo().toString();
		}
		if(this.getInterfaces().getClass().getName() != null){
			string += "\ninterfaces: " + this.getInterfaces().getClass().getName();
		}
		if(this.getConstructors() != null){
			string += "\nconstructors: " + this.getConstructors().toString();
		}
		if(this.getFields() != null){
			string += "\nfields: " + this.getFields().toString();
		}
		if(this.getAnnotations() != null){
			string += "\nannotations : " + this.getAnnotations().toString();
		}
		
		return string; 
	}
	
	public String printMethods(Method[] methods){
		
		String string = "";
		
		for(int i = 0; i < methods.length; i++){
			
			string += methods[i].getName() + "\n ";
			
		}
		
		return string;
	}
	
}
