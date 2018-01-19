package com.example.brenocruz.riccicodegenerator;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.UUID;

public class GenerateUtilityConstants {

	private GenerateUtilityConstants(){}
	
	public static String createUtilityConstants(ObjectDescription object){
		
		String string = "";
		
		string += "package com.example.riccilib.ricci.constants;";
		
		//pre-established part
		string += "\n\npublic class " + "UtilityConstants" +" {\n";
		string += "\n\tprivate UtilityConstants() {}";
		string += "\n\n\tpublic static final String IN_MSG" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String OUT_MSG" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String OUT_COPY_MSG" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String OUT_STREAM_FILE_CLIENT_MSG" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String OUT_STREAM_CLIENT_MSG" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String OUT_REMOTE_CLIENT_MSG" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String OUT_COPY_REPLY_MSG" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String OUT_STREAM_FILE_REPLY_MSG" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String OUT_STREAM_REPLY_MSG" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String OUT_REMOTE_REPLY_MSG" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String IN_STREAM_ACCESS_RESPONSE" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String IN_STREAM_FILE_ACCESS_RESPONSE" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		string += "\n\tpublic static final String IN_REMOTE_ACCESS_RESPONSE" + " = " +"\""+ UUID.randomUUID().toString() + "\";";
		//string += "\n\tpublic static final String REMOTE_getDataClass = \"getDataClass\";";
		//string += "\n\tpublic static final String REMOTE_getDataActualSize = \"getDataActualSize\";";
		//string += "\n\tpublic static final String REMOTE_getDataToString = \"getDataToString\";";
		//string += "\n\tpublic static final String REMOTE_getDataHashCode = \"getDataHashCode\";";
		//string += "\n\tpublic static final String REMOTE_getFileFromPath = \"getFileFromPath\";";
		//string += "\n\tpublic static final String REMOTE_getDataSize = \"getDataSize\";";
		//string += "\n\tpublic static final String REMOTE_isDataEquals = \"isDataEquals\";";
		//string += "\n\tpublic static final String REMOTE_isDataDeepEquals = \"isDataDeepEquals\";";
		//string += "\n\tpublic static final String REMOTE_isDataNull = \"isDataNull\";";
		//string += "\n\tpublic static final String REMOTE_isDataNonNull = \"isDataNonNull\";";
		//string += "\n\tpublic static final String REMOTE_isGetUri = \"iasasGetUri\";";
		//string += "\n\tpublic static final String REMOTE_isGetAction = \"iaesdfsGetAction\";";
		//string += "\n\tpublic static final String REMOTE_isHasBundle = \"iasdfdfsHasBundle\";";
		//dynamic part
		string +="\n";
		
		int counter = 0;
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
						string += "\n\t" + "public static final String" + " REMOTE_Get" + method.getName() + " = " +"\""+ UUID.randomUUID().toString() + "\";";
					} else {
						string += "\n\t" + "public static final String" + " REMOTE_Get" + method.getName() + counter + " = " +"\""+ UUID.randomUUID().toString() + "\";";
					}
				} else {
						string += "\n\t" + "public static final String" + " REMOTE_Get" + method.getName() + " = " +"\""+ UUID.randomUUID().toString() + "\";";
				}
				if(method.getParameterCount() > 0){
					
					int parameters = method.getParameterCount();
					for(int j = 0; j < parameters; j++){	
						string += "\n\t" + "public static final String" + " REMOTE_Get" + method.getParameterTypes()[j].getSimpleName().replaceAll("[\\[\\]]","") + "arg" + j + method.getName() + counter + " = " +"\""+ UUID.randomUUID().toString() + "\";";				
					}				
				}
			}
		}
	
		
		string += "\n\n}"; 
		return string;
	}
	
}
