package com.example.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.scrumboard.R;
import com.example.scrumboard.model.Sprint;

public class SprintArrayAdapter extends ArrayAdapter <Sprint>{

	Context context;
	ArrayList <Sprint> data;
	int resource;
	
	
	
	public SprintArrayAdapter(Context context, int resource, ArrayList<Sprint> data) {
		super(context, resource, data);
		this.context = context;
		this.data = data;
		this.resource = resource;	
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        SprintHolder holder = null;
       
        if(row == null)
        {
        	 LayoutInflater inflater = (LayoutInflater) context
        		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(resource, parent, false);
            
            holder = new SprintHolder();
            holder.projectId = (TextView)row.findViewById(R.id.pid);
            holder.id = (TextView)row.findViewById(R.id.sid);
            holder.startDate = (TextView)row.findViewById(R.id.startDate);
            holder.endDate= (TextView)row.findViewById(R.id.endDate);
           
         
            row.setTag(holder);
        }
        else
        {
            holder = (SprintHolder)row.getTag();
        }
         
        Sprint s = data.get(position);
       
        holder.id.setText(String.valueOf(s.getId()) + ")");       
        holder.projectId.setText("Project id:" + String.valueOf(s.getProjectId()));
        holder.startDate.setText("Start:" + String.valueOf(s.getStartDate()));  
        holder.endDate.setText("End:" + String.valueOf(s.getEndDate()));  
       
        return row;
    }
	
	static class SprintHolder
	{
		
		TextView id;
		TextView projectId;
		TextView startDate;
		TextView endDate;
	
		
	}
	
	public void updateTaskList(ArrayList<Sprint> newlist) {
	    data.clear();
	    data.addAll(newlist);
	    this.notifyDataSetChanged();
	}
}