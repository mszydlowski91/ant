package com.example.scrumboard.tabsactivities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.TaskArrayAdapter;
import com.example.scrumboard.R;
import com.example.scrumboard.db.DataSource;
import com.example.scrumboard.model.Task;

public class TabToDoActivity extends Fragment{
    
	ArrayList <Task> tasks;
	DataSource ds;
	TaskArrayAdapter adapter;
	ListView taskListView;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ds = new DataSource(getActivity());
        tasks = new ArrayList <Task> ();
       
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	fetchTasks();
        View v = inflater.inflate(R.layout.tab_to_do_layout, container, false);        
        adapter = new TaskArrayAdapter(v.getContext(), R.layout.task_list_item, tasks);    
 		taskListView = (ListView) v.findViewById (R.id.taskList);		
 		taskListView.setAdapter(adapter); 		
        return v;
    }
    
    
    
    @Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fetchTasks();
		adapter.updateTaskList(tasks);
		
	}

	public void fetchTasks()
    {
    	ds.open();
    	tasks = ds.selectAllToDoTasks() ;
    	ds.close();
    }
}