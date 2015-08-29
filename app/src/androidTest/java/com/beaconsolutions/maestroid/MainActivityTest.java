package com.beaconsolutions.maestroid;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.LinearLayout;

import com.beaconsolutions.maestroid.Activities.MusicId;

/**
 * Created by Chamil Prabodha on 23/08/2015.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MusicId>{

    MusicId activity;

    public MainActivityTest(){
        super(MusicId.class);
    }

    @Override
    public void setUp()throws Exception{
        super.setUp();
        activity = getActivity();
    }

    @SmallTest
    public void testButtonNextNotnull(){
        Button button = (Button)activity.findViewById(R.id.btnNext);
        assertNotNull(button);
    }

    @SmallTest
    public void testBackgroundImageNotnull(){
        LinearLayout layout = (LinearLayout)activity.findViewById(R.id.home_layout);
        assertNotNull(layout.getBackground());
    }
}
