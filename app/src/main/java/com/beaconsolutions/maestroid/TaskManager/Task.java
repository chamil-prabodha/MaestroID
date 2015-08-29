package com.beaconsolutions.maestroid.TaskManager;

import android.content.Context;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Chamil Prabodha on 27/07/2015.
 */
public abstract class Task{

    private String name;
    private ArrayList<String> answers = new ArrayList<String>();
    private String correct_answer;
    private long time_elapsed = 0;

    public abstract void execute(Context context);

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public ArrayList<String> getAnswers(){
        return answers;
    }

    public String getCorrect_answer(){
        return correct_answer;
    }

    public void setCorrect_answer(String answer){
        this.correct_answer = answer;
    }

    public void setTimeElapsed(long time){
        time_elapsed = time;
    }

    public long getTimeElapsed(){
        return time_elapsed;
    }
}
