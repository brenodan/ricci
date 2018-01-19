package com.example.brenocruz.riccicodegenerator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {


    public static boolean writeToFile(String string, String fileName){

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName + ".java"));
            out.write(string);  //Replace with the string
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
            return false;

        }
        return true;
    }

    public static boolean writeToFilepath(String string, String fileName, String pathToDir){


        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(pathToDir + fileName + ".java"));
            out.write(string);  //Replace with the string
            out.close();
        } catch (IOException e) {

            e.printStackTrace();
            return false;

        }
        return true;
    }

    public void writer(ObjectDescription objectDescription){

        System.out.println(objectDescription.toString());
        System.out.println(GenerateInterfaceFromObject.createInterfaceFromObject(objectDescription));
        writeToFile(GenerateInterfaceFromObject.createInterfaceFromObject(objectDescription), "RemoteInterface");

        System.out.println(GenerateRemoteImplementationFromObject.createRemoteImplementationFromObject(objectDescription));
        writeToFile(GenerateRemoteImplementationFromObject.createRemoteImplementationFromObject(objectDescription), "RemoteImplementation");
        writeToFilepath(GenerateRemoteImplementationFromObject.createRemoteImplementationFromObject(objectDescription), "RemoteImplementation", "./RICCI/riccilib/src/main/java/com/example/riccilib/ricci/Utils/");

        System.out.println(GenerateRemoteAssistantFromObject.createRemoteAssistantFromObject(objectDescription));
        writeToFile(GenerateRemoteAssistantFromObject.createRemoteAssistantFromObject(objectDescription), "RemoteAssistant");
        writeToFilepath(GenerateRemoteAssistantFromObject.createRemoteAssistantFromObject(objectDescription), "RemoteAssistant", "./RICCI/riccilib/src/main/java/com/example/riccilib/ricci/Utils/");

        System.out.println(GenerateRemoteResultsHolderFromObject.createRemoteResultsHolderFromObject(objectDescription));
        writeToFile(GenerateRemoteResultsHolderFromObject.createRemoteResultsHolderFromObject(objectDescription), "RemoteResultsHolder");
        writeToFilepath(GenerateRemoteResultsHolderFromObject.createRemoteResultsHolderFromObject(objectDescription), "RemoteResultsHolder", "./RICCI/riccilib/src/main/java/com/example/riccilib/ricci/Utils/");

        System.out.println(GenerateUtilityConstants.createUtilityConstants(objectDescription));
        writeToFile(GenerateUtilityConstants.createUtilityConstants(objectDescription), "UtilityConstants");
        writeToFilepath(GenerateUtilityConstants.createUtilityConstants(objectDescription), "UtilityConstants", "./RICCI/riccilib/src/main/java/com/example/riccilib/ricci/constants/");

        System.out.println(GenerateRemoteResultsHandler.createRemoteResultsHandler(objectDescription));
        writeToFile(GenerateRemoteResultsHandler.createRemoteResultsHandler(objectDescription), "RemoteResultsHandler");
        writeToFilepath(GenerateRemoteResultsHandler.createRemoteResultsHandler(objectDescription), "RemoteResultsHandler", "./RICCI/riccilib/src/main/java/com/example/riccilib/ricci/receiver/");

        System.out.println(GenerateReceiveRemoteHandler.createReceiveRemoteHandler(objectDescription));
        writeToFile(GenerateReceiveRemoteHandler.createReceiveRemoteHandler(objectDescription), "ReceiveRemoteHandler");
        writeToFilepath(GenerateReceiveRemoteHandler.createReceiveRemoteHandler(objectDescription), "ReceiveRemoteHandler", "./RICCI/riccilib/src/main/java/com/example/riccilib/ricci/handlers/");

        System.out.println(GenerateHandleRemoteAssistant.createHandleRemoteAssistant(objectDescription));
        writeToFile(GenerateHandleRemoteAssistant.createHandleRemoteAssistant(objectDescription), "HandleRemoteAssistant");
        writeToFilepath(GenerateHandleRemoteAssistant.createHandleRemoteAssistant(objectDescription), "HandleRemoteAssistant", "./RICCI/riccilib/src/main/java/com/example/riccilib/ricci/handlers/");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ObjectDescription objectDescription = new ObjectDescription(/*YOUR OBJECT GOES HERE*/new Object());
        writer(objectDescription);

    }


}
