package com.beaconsolutions.maestroid.Activities;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beaconsolutions.maestroid.DBHandler.AppSQLiteHelper;
import com.beaconsolutions.maestroid.FileHandler.MediaManager;
import com.beaconsolutions.maestroid.FileHandler.XMLParser;
import com.beaconsolutions.maestroid.ProfileManager.SharedPreferenceManager;
import com.beaconsolutions.maestroid.R;
import com.beaconsolutions.maestroid.TaskManager.Level;
import com.beaconsolutions.maestroid.Utilities.Chronometer;
import com.beaconsolutions.maestroid.Utilities.RippleBackground;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class TaskActivity extends ActionBarActivity {


    private ImageButton play_button, pause_button, replay_button;
    private Button[] answer_buttons = new Button[5];
    private RippleBackground rippleBackground;
    private MediaManager mediaManager;
    private int level;
    private Level currentLevel;
    private int taskNo = 0;
    private Animation vibrate;
    private LinearLayout layout;
    private Chronometer chronometer;
    private long time = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        level = getIntent().getExtras().getInt("Levelid");

        mediaManager = MediaManager.getMediaManager(TaskActivity.this);
        vibrate = AnimationUtils.loadAnimation(this,R.anim.vibrate);

        final Typeface typeFace = Typeface.createFromAsset(getAssets(), getString(R.string.font));

        try{

            currentLevel = XMLParser.getInstance().parseXML(TaskActivity.this).get(level);
            currentLevel.getTasks().get(taskNo).execute(TaskActivity.this);
        }

        catch(IOException e){

        }catch (XmlPullParserException ex){
            ex.printStackTrace();
        }

        layout = (LinearLayout)findViewById(R.id.panel);
        rippleBackground = (RippleBackground)findViewById(R.id.ripple_back);
        play_button = (ImageButton)findViewById(R.id.play_button);
        pause_button = (ImageButton)findViewById(R.id.pause_button);
        replay_button = (ImageButton)findViewById(R.id.replay_button);
        chronometer = (Chronometer)findViewById(R.id.Chronometer);

        answer_buttons[0] = (Button)findViewById(R.id.button_1);
        answer_buttons[1] = (Button)findViewById(R.id.button_2);
        answer_buttons[2] = (Button)findViewById(R.id.button_3);
        answer_buttons[3] = (Button)findViewById(R.id.button_4);
        answer_buttons[4] = (Button)findViewById(R.id.button_5);

        loadTask(0);

        for(final Button button:answer_buttons){

            final String answer = button.getText().toString();

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(answer.equals(currentLevel.getTasks().get(0).getCorrect_answer())){
                        chronometer.stop();
                        if(currentLevel.getTasks().size()!= ++taskNo) {
                            time += chronometer.getTimeElapsed();
                            loadTask(taskNo);

                        }
                        else {
                            currentLevel.setElapsedTime(time);
                            AppSQLiteHelper.getInstance(TaskActivity.this).addTime(level,time);

                            LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                            View view = layoutInflater.inflate(R.layout.level, null);
                            TextView textView_time = (TextView)view.findViewById(R.id.Level_Time);
                            textView_time.setText("Time: " + currentLevel.getElapsed_time());
                            textView_time.setTypeface(typeFace);
                            finish();
                        }
                    }

                    else{
                        changeColour();
                        layout.startAnimation(vibrate);
                    }

                }
            });
        }

        //progressbarAnimation = new ProgressbarAnimation(donutProgress,0,100);
        play_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mediaManager.Play();
                rippleBackground.startRippleAnimation();
                //progressbarAnimation.setDuration(mediaManager.getMediaPlayer().getDuration());
                //donutProgress.startAnimation(progressbarAnimation);
            }
        });

        pause_button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                mediaManager.Pause();
                rippleBackground.stopRippleAnimation();
                //donutProgress.clearAnimation();

            }
        });

        replay_button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mediaManager.Replay();
                rippleBackground.startRippleAnimation();
               // progressbarAnimation.setDuration(mediaManager.getMediaPlayer().getDuration());
                //donutProgress.startAnimation(progressbarAnimation);
            }
        });


        mediaManager.setMediaPlayerListener(new MediaManager.MediaPlayerListner() {
            @Override
            public void onPlaybackCompletion(boolean completed) {
                if(completed)
                    rippleBackground.stopRippleAnimation();
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        chronometer.setBase(SystemClock.elapsedRealtime()+SharedPreferenceManager.getInstance(TaskActivity.this).getTimeSpentOnLevel());
        chronometer.resume();
    }

    @Override
    public void onPause(){

        super.onPause();
        long timeOnStop = chronometer.getBase() - SystemClock.elapsedRealtime();
        SharedPreferenceManager.getInstance(TaskActivity.this).setTimeSpentOnLevel(timeOnStop);
        chronometer.stop();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadTask(int taskNo){

        SharedPreferenceManager.getInstance(TaskActivity.this).getSharedPreferenceManager().edit().clear().commit();
        chronometer.start();
        currentLevel.getTasks().get(taskNo).execute(TaskActivity.this);
        answer_buttons[0].setText(currentLevel.getTasks().get(taskNo).getCorrect_answer());
        answer_buttons[1].setText(currentLevel.getTasks().get(taskNo).getAnswers().get(0));
        answer_buttons[2].setText(currentLevel.getTasks().get(taskNo).getAnswers().get(1));
        answer_buttons[3].setText(currentLevel.getTasks().get(taskNo).getAnswers().get(2));
        answer_buttons[4].setText(currentLevel.getTasks().get(taskNo).getAnswers().get(3));
    }

    private void changeColour(){

        ObjectAnimator objectAnimator = ObjectAnimator.ofObject(layout,"backgroundColor",new ArgbEvaluator(),Color.parseColor("#F79E9E"),Color.parseColor("#00FFFFFF"));
        objectAnimator.setDuration(500);
        objectAnimator.start();


    }


}
