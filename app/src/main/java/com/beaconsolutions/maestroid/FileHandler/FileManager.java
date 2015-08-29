package com.beaconsolutions.maestroid.FileHandler;

/**
 * Created by Chamil Prabodha on 26/07/2015.
 */

import android.content.Context;
import android.os.Environment;

import com.beaconsolutions.maestroid.MidiLib.MidiFile;

import java.io.File;
import java.io.IOException;

public class FileManager {

    private static FileManager manager = null;


    public static FileManager getInstance(){
        if(manager==null)
            manager = new FileManager();

        return manager;
    }

    public boolean writeMidiFile(MidiFile midi,String filename,Context context){
        try {
            File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath().toString()+"/Android/data/com.beaconsolutions.maestroid/files/"+filename);
            System.out.println(file.delete());

            midi.writeToFile(filename,context);
            System.out.println("Success");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Fail");
            return false;
        }
    }


}
