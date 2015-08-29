package com.beaconsolutions.maestroid.Utilities;

import android.content.res.Resources;

import com.beaconsolutions.maestroid.TaskManager.Level;

import java.util.ArrayList;

/**
 * Created by bruce on 14-11-6.
 */
public class Utils {
    public static float dp2px(Resources resources, float dp) {
        final float scale = resources.getDisplayMetrics().density;
        return  dp * scale + 0.5f;
    }

    public static float sp2px(Resources resources, float sp){
        final float scale = resources.getDisplayMetrics().scaledDensity;
        return sp * scale;
    }

    public static Level[] toArray(ArrayList<Level> levels){

        Level[] array = new Level[levels.size()];

        for(int i=0;i<levels.size();i++){
            array[i] = levels.get(i);
        }

        return array;
    }
}
