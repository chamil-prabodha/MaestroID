package com.beaconsolutions.maestroid.Utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beaconsolutions.maestroid.DBHandler.AppSQLiteHelper;
import com.beaconsolutions.maestroid.R;
import com.beaconsolutions.maestroid.TaskManager.Level;

/**
 * Created by Chamil Prabodha on 27/08/2015.
 */
public class StatsLevelListAdapter extends ArrayAdapter {

    private static StatsLevelListAdapter statsLevelListAdapter = null;
    private Context context;


    public StatsLevelListAdapter(Context context, Level[] levels){
        super(context, R.layout.stat_item, levels);
        this.context = context;
    }

    public static StatsLevelListAdapter getInstance(Context context, Level[] levels){
        if(statsLevelListAdapter == null)
            statsLevelListAdapter = new StatsLevelListAdapter(context,levels);

        return statsLevelListAdapter;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        View view = layoutInflater.inflate(R.layout.stat_item, parent, false);

        Level level = (Level)getItem(position);

        TextView title = (TextView)view.findViewById(R.id.stat_item_title);
        TextView completed_value = (TextView)view.findViewById(R.id.stat_completed_value);
        TextView attempts_value = (TextView)view.findViewById(R.id.stat_attempts_value);
        TextView mintime_value = (TextView)view.findViewById(R.id.stat_mintime_value);
        TextView lasttime_value = (TextView)view.findViewById(R.id.stat_lasttime_value);


        int attempts = AppSQLiteHelper.getInstance(context).getAttempts(position);
        Long mintime = AppSQLiteHelper.getInstance(context).getMinTime(position);
        Long lasttime = AppSQLiteHelper.getInstance(context).getLastTime(position);

        ImageView imageView = (ImageView)view.findViewById(R.id.stat_image);

        Typeface typeFace = Typeface.createFromAsset(context.getAssets(), context.getString(R.string.font));

        switch (position){
            case 0:
                imageView.setImageResource(R.drawable.notes_image_stats);
                break;
            case 1:
                imageView.setImageResource(R.drawable.interva_image_stats);
                break;
            case 2:
                imageView.setImageResource(R.drawable.chords_image_stats);
                break;
            case 3:
                imageView.setImageResource(R.drawable.scale_image_stats);
                break;
        }

        title.setText(level.getLevelType());
        title.setTypeface(typeFace);

        attempts_value.setText(Integer.toString(attempts));
        attempts_value.setTypeface(typeFace);

        mintime_value.setText(mintime.toString());
        attempts_value.setTypeface(typeFace);

        lasttime_value.setText(lasttime.toString());
        lasttime_value.setTypeface(typeFace);


        return view;
    }

}
