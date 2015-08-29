package com.beaconsolutions.maestroid.Activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.beaconsolutions.maestroid.FileHandler.XMLParser;
import com.beaconsolutions.maestroid.R;
import com.beaconsolutions.maestroid.Utilities.BlurBuilder;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class MusicId extends Activity {

    Button btnPlay,next,stats;
    LinearLayout linearLayout;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);

        Typeface typeFace = Typeface.createFromAsset(getAssets(), getString(R.string.font));

        linearLayout = (LinearLayout)findViewById(R.id.home_layout);
        btnPlay = (Button)findViewById(R.id.btnPlay);
        next = (Button)findViewById(R.id.btnNext);
        stats = (Button)findViewById(R.id.stats);

        btnPlay.setTypeface(typeFace);
        next.setTypeface(typeFace);
        stats.setTypeface(typeFace);

        //Bitmap blurredbitmap = BlurBuilder.blur(this, BitmapFactory.decodeResource(getResources(), R.drawable.back));
        //linearLayout.setBackground(new BitmapDrawable(getResources(),blurredbitmap));
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {

                    XMLParser.getInstance().parseXML(MusicId.this).get(0).getTasks().get(0).execute(MusicId.this);
                    Animation animation = AnimationUtils.loadAnimation(MusicId.this,R.anim.vibrate);
                    next.startAnimation(animation);

                }

                catch(XmlPullParserException e){

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        next.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){

                startActivity(new Intent(getApplicationContext(),LevelActivity.class));

            }
        });

        stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), LevelStatsActivity.class));
            }
        });



    }

    public void buttonOnClick(View v){
        Button button = next;
        startActivity(new Intent(getApplicationContext(),LevelActivity.class));
    }



}
