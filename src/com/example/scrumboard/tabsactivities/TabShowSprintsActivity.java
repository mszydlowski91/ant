package com.example.scrumboard.tabsactivities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adapters.SprintArrayAdapter;
import com.example.adapters.TaskArrayAdapter;
import com.example.scrumboard.R;
import com.example.scrumboard.db.DataSource;
import com.example.scrumboard.model.Sprint;

public class TabShowSprintsActivity extends Fragment{

	ArrayList <Sprint> sprints;
	DataSource ds;
	SprintArrayAdapter adapter;
	ListView sprintListView;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ds = new DataSource(getActivity());
        sprints = new ArrayList <Sprint> ();
        
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	fetchSprints();
        View v = inflater.inflate(R.layout.tab_show_sprints_layout, container, false);        
        adapter = new SprintArrayAdapter(v.getContext(), R.layout.sprint_list_item, sprints);
      
 		sprintListView = (ListView) v.findViewById (R.id.sprintsList);		
 		System.out.println(sprintListView);
 		sprintListView.setAdapter(adapter); 		
        return v;
    }
    
    
    
    @Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fetchSprints();
		adapter.updateTaskList(sprints);
		
	}

	public void fetchSprints()
    {
    	ds.open();
    	sprints = ds.selectAllSprints(); 
    	System.out.println(sprints.size());
    	Toast.makeText(getActivity(), String.valueOf(sprints.size()), Toast.LENGTH_LONG).show();
    	ds.close();
    }
}
