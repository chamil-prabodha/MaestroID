package com.beaconsolutions.maestroid.Activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TableRow;

import com.beaconsolutions.maestroid.FileHandler.XMLParser;
import com.beaconsolutions.maestroid.R;
import com.beaconsolutions.maestroid.TaskManager.Level;
import com.beaconsolutions.maestroid.Utilities.BlurBuilder;
import com.beaconsolutions.maestroid.Utilities.CustomAdapter;
import com.beaconsolutions.maestroid.Utilities.Utils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;


public class LevelActivity extends ActionBarActivity {

    LinearLayout linearLayout;
    ListView listView;
    ListAdapter listAdapter;
    TableRow tb;
    ArrayList<Button> buttons = null;
    public ArrayList<Level> levels = null;
    int width = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity__level);

        final Context context = this;

        //linearLayout = (LinearLayout)findViewById(R.id.level_layout);
        //Bitmap blurredbitmap = BlurBuilder.blur(this, BitmapFactory.decodeResource(getResources(), R.drawable.back));
        //linearLayout.setBackground(new BitmapDrawable(getResources(),blurredbitmap));
        listView = (ListView)findViewById(R.id.Level_list);

        try{
            levels = XMLParser.getInstance().parseXML(this);
            if(levels!=null){

                Level[] levels_array = Utils.toArray(levels);

                listAdapter = CustomAdapter.getInstance(this, levels_array);
                listView = (ListView)findViewById(R.id.Level_list);
                listView.setAdapter(listAdapter);
                listView.setDivider(null);
            }
        }

        catch(IOException e){

        }

        catch (XmlPullParserException e){

        }


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(LevelActivity.this,TaskActivity.class);

                intent.putExtra("Levelid",position);

                startActivity(intent);
            }

        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_level, menu);
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

    @Override
    public void onWindowFocusChanged(boolean hasfocus){
        super.onWindowFocusChanged(hasfocus);
        //width = findViewById(R.id.row_1).getWidth();


    }




}
