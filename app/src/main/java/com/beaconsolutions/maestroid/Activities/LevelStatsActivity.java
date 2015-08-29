package com.beaconsolutions.maestroid.Activities;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.beaconsolutions.maestroid.FileHandler.XMLParser;
import com.beaconsolutions.maestroid.R;
import com.beaconsolutions.maestroid.TaskManager.Level;
import com.beaconsolutions.maestroid.Utilities.StatsLevelListAdapter;
import com.beaconsolutions.maestroid.Utilities.Utils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;


public class LevelStatsActivity extends ActionBarActivity {

    ArrayList<Level> levels;
    ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_stats);

        Context context = this;

        ListView listView = (ListView)findViewById(R.id.stats_level_list);

        try{
            levels = XMLParser.getInstance().parseXML(context);

            if(levels!=null){

                Level[] level_array = Utils.toArray(levels);
                listAdapter = StatsLevelListAdapter.getInstance(this,level_array);
                listView.setAdapter(listAdapter);
                listView.setDivider(null);
            }
        }

        catch (XmlPullParserException|IOException e){

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level_stats, menu);
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
}
