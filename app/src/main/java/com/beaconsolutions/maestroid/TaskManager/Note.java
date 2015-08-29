package com.beaconsolutions.maestroid.TaskManager;

import com.beaconsolutions.maestroid.MidiLib.MidiFile;

import java.io.Serializable;

/**
 * Created by Chamil Prabodha on 27/07/2015.
 */
public class Note implements Serializable{

    private int note_duration = MidiFile.CROTCHET;
    private int note_value;
    private int note_velocity = 127;

    public int getNote_duration(){
        return note_duration;
    }

    public int getNote_value(){
        return note_value;
    }

    public int getNote_velocity(){
        return note_velocity;
    }

    public void setNote_value(int value){
        this.note_value = value;
    }
}
