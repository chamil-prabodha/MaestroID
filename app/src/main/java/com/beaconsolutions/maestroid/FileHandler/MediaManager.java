package com.beaconsolutions.maestroid.FileHandler;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;

import java.io.File;

/**
 * Created by Chamil Prabodha on 17/08/2015.
 */
public class MediaManager {

    private static MediaManager mediaManager = null;
    private MediaPlayer mediaPlayer = null;
    private AudioManager audioManager = null;
    private  Context context;
    private boolean isPaused = false;
    private MediaPlayerListner listener;

    public MediaManager(Context context){

        this.context = context;

        audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(this.context, Uri.fromFile(new File(Environment.getExternalStorageDirectory().toString() + "/Android/data/com.beaconsolutions.maestroid/files/Track.mid")));
    }

    public void setMediaPlayerListener(MediaPlayerListner listener){
        this.listener = listener;
    }

    public static MediaManager getMediaManager(Context context){
        if(mediaManager == null)
            mediaManager = new MediaManager(context);
        return mediaManager;
    }

    public MediaPlayer getMediaPlayer(){
        return mediaPlayer;
    }

    public void Play(){
        System.out.println("Duration: "+mediaPlayer.getDuration());

        if(mediaPlayer != null){

            System.out.println(mediaPlayer.isPlaying());
            if(!mediaPlayer.isPlaying() && !audioManager.isMusicActive() && isPaused){
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        listener.onPlaybackCompletion(true);
                    }
                });
                isPaused = false;



            }

            else if(!mediaPlayer.isPlaying() && !audioManager.isMusicActive() && !isPaused){

                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this.context, Uri.fromFile(new File(Environment.getExternalStorageDirectory().toString() + "/Android/data/com.beaconsolutions.maestroid/files/Track.mid")));
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        listener.onPlaybackCompletion(true);
                    }
                });

                isPaused = false;
            }
        }

        else
            System.out.println("File Not Found!!!");
    }

    public void Pause(){
        System.out.println(mediaPlayer.isPlaying());
        if(mediaPlayer != null){

            if(mediaPlayer.isPlaying() && audioManager.isMusicActive() ){
                mediaPlayer.pause();
                isPaused = true;


            }
        }
    }

    public void Replay(){
        System.out.println(isPaused);
        if(mediaPlayer != null){


            if(!mediaPlayer.isPlaying()){
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this.context, Uri.fromFile(new File(Environment.getExternalStorageDirectory().toString() + "/Android/data/com.beaconsolutions.maestroid/files/Track.mid")));
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        listener.onPlaybackCompletion(true);
                    }
                });
                isPaused = false;
            }

            if(!mediaPlayer.isPlaying() && isPaused){
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this.context, Uri.fromFile(new File(Environment.getExternalStorageDirectory().toString() + "/Android/data/com.beaconsolutions.maestroid/files/Track.mid")));
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        listener.onPlaybackCompletion(true);
                    }
                });
                isPaused = false;
            }

            else if(mediaPlayer.isPlaying()){
                mediaPlayer.reset();
                mediaPlayer = MediaPlayer.create(this.context, Uri.fromFile(new File(Environment.getExternalStorageDirectory().toString() + "/Android/data/com.beaconsolutions.maestroid/files/Track.mid")));
                mediaPlayer.start();

                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        listener.onPlaybackCompletion(true);
                    }
                });
                isPaused = false;
            }
        }
    }

    public interface MediaPlayerListner{

        public void onPlaybackCompletion(boolean completed);
    }


}
