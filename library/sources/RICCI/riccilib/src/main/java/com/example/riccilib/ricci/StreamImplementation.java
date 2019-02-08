package com.example.riccilib.ricci;

/**
 * Created by brenocruz on 1/27/17.
 */

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.provider.OpenableColumns;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import com.example.riccilib.ricci.Utils.StreamService;

/**
 * Created by newuser on 12/1/16.
 */

public class StreamImplementation implements StreamService {

    private Intent data;

    private Context context;

    public Intent getData() {
        return data;
    }

    @Override
    public void setData(Intent data) {
        this.data = data;
    }

    @Override
    public void setContext(Context context){this.context = context;}

    @Override
    public String getFileExtension() {

        /*
        System.out.println("getFileExtension:");

        String extension = this.getData().getData().getPath();

        System.out.println("extension: " + extension);

        //return FilenameUtils.getExtension(extension);
        */
        ContentResolver contentResolver = this.context.getContentResolver();
        Cursor returnCursor = contentResolver.query(this.getData().getData(), null, null, null, null);
        assert returnCursor != null;
        int nameIndex = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
        returnCursor.moveToFirst();
        String name = returnCursor.getString(nameIndex);
        returnCursor.close();

        if(name.contains(".")){

            String extension = name.substring(name.indexOf("."), name.length());
            return extension;
        }

        return "";

    }

    @Override
    public byte[] loadData() {

        try {

            ContentResolver contentResolver = this.context.getContentResolver();
            InputStream input = contentResolver.openInputStream(this.getData().getData());
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[8192];

            for(int length = 0; (length = input.read(buffer)) > 0;){

                outputStream.write(buffer, 0, length);

            }

            byte[] bytes = outputStream.toByteArray();

            return bytes;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;

    }
}


