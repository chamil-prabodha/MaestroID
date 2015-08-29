package com.beaconsolutions.maestroid.Activities;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.beaconsolutions.maestroid.R;
import com.beaconsolutions.maestroid.TaskManager.Level;
import com.beaconsolutions.maestroid.Utilities.StatsLevelListAdapter;
import com.beaconsolutions.maestroid.Utilities.Utils;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 *
 * to handle interaction events.
 * Use the {@link FragmentLevelChart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentLevelChart extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private ArrayList<Level> levels;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentLevelChart newInstance(ArrayList<Level> levels) {
        FragmentLevelChart fragment = new FragmentLevelChart();
        Bundle args = new Bundle();
        args.putSerializable("List",levels);
        fragment.setArguments(args);
        return fragment;
    }

    public FragmentLevelChart() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.stats_level_chart, container, false);
        ListView listView1 = (ListView)rootView.findViewById(R.id.stats_list);

        Level[] levels_array = Utils.toArray((ArrayList<Level>)getArguments().getSerializable("List"));
        StatsLevelListAdapter statsLevelListAdapter = StatsLevelListAdapter.getInstance(getActivity().getApplicationContext(), levels_array);

        listView1.setAdapter(statsLevelListAdapter);
        listView1.setDivider(null);
        return rootView;
    }

    // TODO: Rename method, update argument and hook method into UI event


}
