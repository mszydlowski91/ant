package com.example.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.scrumboard.R;
import com.example.scrumboard.model.UserStory;

public class UserStoryArrayAdapter extends ArrayAdapter <UserStory>{

	Context context;
	ArrayList <UserStory> data;
	int resource;
	
	
	public UserStoryArrayAdapter(Context context, int resource, ArrayList<UserStory> data) {
		super(context, resource, data);
		this.context = context;
		this.data = data;
		this.resource = resource;	
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserStoryHolder holder = null;
       
        if(row == null)
        {
        	 LayoutInflater inflater = (LayoutInflater) context
        		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(resource, parent, false);
            
            holder = new UserStoryHolder();
            holder.name = (TextView)row.findViewById(R.id.name);
            holder.id = (TextView)row.findViewById(R.id.uid);
            holder.details = (TextView)row.findViewById(R.id.details);
            holder.priority = (TextView)row.findViewById(R.id.priority);
            holder.comment = (TextView)row.findViewById(R.id.comment);
            holder.time = (TextView)row.findViewById(R.id.time);
            holder.status = (TextView)row.findViewById(R.id.status);
            holder.sprintId = (TextView)row.findViewById(R.id.sprintId);
         
            row.setTag(holder);
        }
        else
        {
            holder = (UserStoryHolder)row.getTag();
        }
        
        UserStory us = data.get(position);
        holder.name.setText(us.getName());
        holder.id.setText(String.valueOf(us.getId()) + ")");       
        holder.details.setText(us.getDetails());
        holder.priority.setText("Priority: " + String.valueOf(us.getPriority()));  
        holder.comment.setText(us.getComment());
        holder.time.setText("Time: " + String.valueOf(us.getEstimatedTime()));  
        holder.status.setText("Status: " + us.getStatus());
        holder.sprintId.setText("Sprint: " + String.valueOf(us.getId()));  
        return row;
    }
	
	static class UserStoryHolder
	{
		TextView name;
		TextView id;
		TextView details;
		TextView priority;
		TextView comment;
		TextView time;
		TextView status;
		TextView sprintId;
		
	}
	
	public void updateUserStoryList(ArrayList<UserStory> newlist) {
	    data.clear();
	    data.addAll(newlist);
	    this.notifyDataSetChanged();
	}
}