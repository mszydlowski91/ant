package com.example.scrumboard.tabsactivities;

import com.example.scrumboard.AddTaskActivity;
import com.example.scrumboard.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TabTasksActivity extends Fragment {

    private FragmentTabHost mTabHost;
    private Button buttonMainToAddTask;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.tab_tasks_layout, container, false);

        buttonMainToAddTask = (Button) v.findViewById(R.id.button_tab_tasks_to_add_task);

        buttonMainToAddTask.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity().getApplicationContext(), AddTaskActivity.class);
                startActivity(intent);

            }
        });

        mTabHost = (FragmentTabHost) v.findViewById(R.id.tab_tasks_tabhost);
        mTabHost.setup(getActivity().getApplicationContext(), getChildFragmentManager(), R.id.tab_tasks_realtabcontent);

        mTabHost.addTab(mTabHost.newTabSpec("todo").setIndicator("To Do"), TabToDoActivity.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("inprogress").setIndicator("In Progress"), TabInProgressActivity.class,
                null);
        mTabHost.addTab(mTabHost.newTabSpec("done").setIndicator("Done"), TabDoneActivity.class,
                null);
        return v;
    }
}
