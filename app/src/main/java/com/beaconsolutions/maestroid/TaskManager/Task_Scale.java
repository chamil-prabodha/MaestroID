package com.beaconsolutions.maestroid.TaskManager;

import com.beaconsolutions.maestroid.FileHandler.FileManager;
import com.beaconsolutions.maestroid.MidiLib.MidiFile;

import java.io.Serializable;
import java.util.ArrayList;

import android.content.Context;
import android.media.AudioManager;

/**
 * Created by Chamil Prabodha on 27/07/2015.
 */
public class Task_Scale extends Task implements Serializable{

    private ArrayList<Note> notes;
    private AudioManager audioManager;

    public void init(){
        notes = new ArrayList<Note>();
    }
    @Override
    public void execute(Context context) {
        MidiFile midi = new MidiFile();
        System.out.println(notes.size());
        if(notes!=null){
            for(Note note: notes){
                midi.noteOnOffNow(MidiFile.QUAVER,note.getNote_value(),127);
                System.out.println(note.getNote_value());
            }
        }

        else
            System.out.println("Not Parsed Correctly");

        FileManager.getInstance().writeMidiFile(midi,"Track.mid",context);


    }

    public ArrayList<Note> getNotes(){
        return notes;
    }
}
