package com.example.scrumboard;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;

import com.example.scrumboard.tabsactivities.TabSprintActivity;
import com.example.scrumboard.tabsactivities.TabTasksActivity;

public class MainTabActivity extends FragmentActivity {

    private FragmentTabHost mTabHost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tab_layout);
        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("sprints").setIndicator("Product Backlog & Sprints"),
                TabSprintActivity.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("tasks").setIndicator("Task Board"),
                TabTasksActivity.class, null);
    }


}
