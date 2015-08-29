package com.beaconsolutions.maestroid.Utilities;

import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by Chamil Prabodha on 21/08/2015.
 */
public class ProgressbarAnimation extends Animation {

    private DonutProgress progress;
    private long timeElapsedAtPause = 0;
    private boolean isPaused = false;
    private float start;
    private float end;

    public ProgressbarAnimation(DonutProgress progress, float start, float end){
        super();
        this.progress = progress;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t){
        super.applyTransformation(interpolatedTime,t);
        float value = start + (end-start)*interpolatedTime;
        progress.setProgress((int)value);
    }

    @Override
    public boolean getTransformation(long currenttime, Transformation t){
        if(isPaused && timeElapsedAtPause == 0)
            timeElapsedAtPause = currenttime - getStartTime();

        if(isPaused)
            setStartTime(currenttime-timeElapsedAtPause);

        return super.getTransformation(currenttime,t);
    }

    public void pause(){
        timeElapsedAtPause = 0;
        isPaused = true;
    }

    public void resume(){
        isPaused = false;
    }
}
