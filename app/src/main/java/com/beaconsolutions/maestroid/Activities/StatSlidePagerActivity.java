package com.beaconsolutions.maestroid.Activities;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.beaconsolutions.maestroid.FileHandler.XMLParser;
import com.beaconsolutions.maestroid.R;
import com.beaconsolutions.maestroid.TaskManager.Level;
import com.beaconsolutions.maestroid.Utilities.StatsLevelListAdapter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

public class StatSlidePagerActivity extends FragmentActivity {

    private static final int NUM_PAGES = 2;

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private ArrayList<Level> levels;
    private StatsLevelListAdapter listAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_slide_pager);

        mPager = (ViewPager)findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mPagerAdapter);

        try{
            levels = XMLParser.getInstance().parseXML(this);
            /*if(levels!=null){

                Level[] levels_array = Utils.toArray(levels);

                LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.stats_level_chart, null);
                listAdapter = StatsListAdapter.getInstance(this, levels_array);
                listView = (ListView)view.findViewById(R.id.stats_list);
                listView.setAdapter(listAdapter);
                listView.setDivider(null);
            }*/
        }

        catch(IOException e){

        }

        catch (XmlPullParserException e){

        }

        /*if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new  StatSlidePagerFragment())
                    .commit();
        }*/
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_stat_slide_pager, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class StatSlidePagerFragment extends Fragment {

        public StatSlidePagerFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.activity_stat_slide_pager, container, false);


            return rootView;
        }
    }

    public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(FragmentManager fm){
            super(fm);
        }

        @Override
        public Fragment getItem(int position){

            return FragmentLevelChart.newInstance(levels);
        }

        @Override
        public int getCount(){
            return NUM_PAGES;
        }

    }
}
