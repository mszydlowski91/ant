package com.example.scrumboard.tabsactivities;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.adapters.UserStoryArrayAdapter;
import com.example.scrumboard.R;
import com.example.scrumboard.db.DataSource;
import com.example.scrumboard.model.UserStory;

public class TabUserStoriesActivity extends Fragment{
    
	ArrayList <UserStory> userStories;
	DataSource ds;
	UserStoryArrayAdapter adapter;
	ListView userStoryListView;
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ds = new DataSource(getActivity());
        userStories = new ArrayList <UserStory> ();
       
        
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	fetchUserStories();
        View v = inflater.inflate(R.layout.tab_user_stories_layout, container, false);        
        adapter = new UserStoryArrayAdapter(v.getContext(), R.layout.user_story_list_item, userStories);    
 		userStoryListView = (ListView) v.findViewById (R.id.userStoryList);		
 		userStoryListView.setAdapter(adapter); 		
        return v;
    }
    
    
    
    @Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		fetchUserStories();
		adapter.updateUserStoryList(userStories);
		
	}

	public void fetchUserStories()
    {
    	ds.open();
    	userStories = ds.selectAllUserStories();    
    	ds.close();
    }
}
