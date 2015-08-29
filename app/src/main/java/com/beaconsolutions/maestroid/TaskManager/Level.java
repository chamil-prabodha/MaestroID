package com.beaconsolutions.maestroid.TaskManager;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Chamil Prabodha on 28/07/2015.
 */
public class Level implements Serializable{

    private String LevelType;
    private String LevelDescription;
    ArrayList<Task> tasks;
    private long elapsed_time = 0;

    public void init(){
        tasks = new ArrayList<Task>();
    }

    public void setLevelType(String type){
        LevelType = type;
    }

    public String getLevelType(){
        return LevelType;
    }

    public void setLevelDescription(String description){
        LevelDescription = description;
    }

    public String getLevelDescription(){
        return LevelDescription;
    }

    public ArrayList<Task> getTasks(){
        return tasks;
    }

    public void setElapsedTime(long time){
        elapsed_time = time;
    }

    public long getElapsed_time(){
        return elapsed_time;
    }
}
