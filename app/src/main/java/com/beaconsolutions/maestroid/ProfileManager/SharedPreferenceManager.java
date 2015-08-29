package com.beaconsolutions.maestroid.ProfileManager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Chamil Prabodha on 26/08/2015.
 */
public class SharedPreferenceManager {

    private static final String TIME_SPENT_ON_LEVEL = "time_spent_on_level";
    private static SharedPreferenceManager instance = null;

    private SharedPreferences sharedPreferences = null;
    private Context context;

    public static SharedPreferenceManager getInstance(Context context){
        if(instance == null)
            instance = new SharedPreferenceManager(context);

        return instance;
    }

    public SharedPreferenceManager(Context context){
        this.context = context;
    }

    public SharedPreferences getSharedPreferenceManager(){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("SaveState",Context.MODE_PRIVATE);

        return sharedPreferences;
    }

    public void setTimeSpentOnLevel(long time){
        getSharedPreferenceManager().edit().putLong(TIME_SPENT_ON_LEVEL,time).commit();
    }

    public long getTimeSpentOnLevel(){
        return getSharedPreferenceManager().getLong(TIME_SPENT_ON_LEVEL,0);
    }
}

