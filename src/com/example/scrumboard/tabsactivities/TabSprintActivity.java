package com.example.scrumboard.tabsactivities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.scrumboard.AddSprintActivity;
import com.example.scrumboard.AddUserstoryActivity;
import com.example.scrumboard.R;
import com.example.scrumboard.db.DataSource;

public class TabSprintActivity extends Fragment{

    private FragmentTabHost mTabHost;
    private Button buttonTabToAddUserstory;
    private Button buttonMainToAddSprint;

	DataSource ds;

	ListView sprintsListView;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ds = new DataSource(getActivity());
      
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	View v = inflater.inflate(R.layout.tab_sprint_layout, container, false);
        
        buttonTabToAddUserstory = (Button) v.findViewById(R.id.button_main_to_add_userstory);

        buttonTabToAddUserstory.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), AddUserstoryActivity.class);
                startActivity(intent);

            }
        });
        
        buttonMainToAddSprint = (Button) v.findViewById(R.id.button_main_to_add_sprint);

        buttonMainToAddSprint.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), AddSprintActivity.class);
                startActivity(intent);

            }
        });
        mTabHost =  (FragmentTabHost) v.findViewById(R.id.tab_sprint_tabhost);
        mTabHost.setup(getActivity().getApplicationContext(), getChildFragmentManager(),R.id.tab_sprint_realtabcontent);
        
        mTabHost.addTab(mTabHost.newTabSpec("usersstories").setIndicator("User Stories"),
                TabUserStoriesActivity.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("sprints").setIndicator("Sprints"),
                TabShowSprintsActivity.class, null);
        
        View vv = inflater.inflate(R.layout.tab_show_sprints_layout, container, false);
        return v;
    }
    
}
