package com.beaconsolutions.maestroid.Utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beaconsolutions.maestroid.DBHandler.AppSQLiteHelper;
import com.beaconsolutions.maestroid.R;
import com.beaconsolutions.maestroid.TaskManager.Level;

import java.util.ArrayList;

/**
 * Created by Chamil Prabodha on 16/08/2015.
 */
public class CustomAdapter extends ArrayAdapter {

    private static CustomAdapter customAdapter = null;
    private Context context;

    public CustomAdapter(Context context, Level[] levels){
        super(context, R.layout.level,levels);
        this.context = context;
    }

    public static CustomAdapter getInstance(Context context, Level[] levels){
        if(customAdapter == null)
            customAdapter  = new CustomAdapter(context, levels);

        return customAdapter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.level, parent, false);

        Level level = (Level)getItem(position);

        Long time = AppSQLiteHelper.getInstance(context).getLastTime(position);

        level.setElapsedTime(time);
        TextView textView_Level = (TextView)view.findViewById(R.id.LevelName);
        TextView textView_desc = (TextView)view.findViewById(R.id.Description);
        TextView textView_progress = (TextView)view.findViewById(R.id.Level_Progress_Values);
        TextView textView_time = (TextView)view.findViewById(R.id.Level_Time);
        ImageView imageView = (ImageView)view.findViewById(R.id.level_image);
        ProgressBar progressBar = (ProgressBar)view.findViewById(R.id.Level_Progress);

        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.font));


        switch (position){
            case 0:
                imageView.setImageResource(R.drawable.notes_image);
                break;
            case 1:
                imageView.setImageResource(R.drawable.interva_image);
                break;
            case 2:
                imageView.setImageResource(R.drawable.chords_image);
                break;
            case 3:
                imageView.setImageResource(R.drawable.scale_image);
                break;
        }


        textView_Level.setText(level.getLevelType());
        textView_Level.setTypeface(typeFace);

        textView_desc.setText(level.getLevelDescription());
        textView_desc.setTypeface(typeFace);

        textView_progress.setText("1/"+level.getTasks().size());
        textView_progress.setTypeface(typeFace);

        textView_time.setText("Time: "+level.getElapsed_time());
        textView_time.setTypeface(typeFace);

        progressBar.setProgress(1 * 100 / level.getTasks().size());

        return view;
    }


}
