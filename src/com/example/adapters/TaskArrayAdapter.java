package com.example.adapters;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.opengl.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.scrumboard.MainTabActivity;
import com.example.scrumboard.R;
import com.example.scrumboard.db.DataSource;
import com.example.scrumboard.model.Task;

public class TaskArrayAdapter extends ArrayAdapter <Task>{

	private DataSource db;
	Context context;
	ArrayList <Task> data;
	int resource;
	
	
	
	public TaskArrayAdapter(Context context, int resource, ArrayList<Task> data) {
		super(context, resource, data);
		this.context = context;
		this.data = data;
		this.resource = resource;	
	}
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        TaskHolder holder = null;
        db = new DataSource(context);
        final int finalPosition = position;
        if(row == null)
        {
        	 LayoutInflater inflater = (LayoutInflater) context
        		        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(resource, parent, false);
            
            holder = new TaskHolder();
            holder.name = (TextView)row.findViewById(R.id.tname);
            holder.id = (TextView)row.findViewById(R.id.tid);
            holder.userStoryId = (TextView)row.findViewById(R.id.userStoryId);
            holder.memberId = (TextView)row.findViewById(R.id.memberId);
            holder.comment = (TextView)row.findViewById(R.id.tcomment);
            holder.time = (TextView)row.findViewById(R.id.ttime);
            holder.status = (TextView)row.findViewById(R.id.tstatus);
            holder.deadline = (TextView)row.findViewById(R.id.deadline);
            holder.change_status = (Button)row.findViewById(R.id.change_status);
         
            row.setTag(holder);
        }
        else
        {
            holder = (TaskHolder)row.getTag();
        }
        System.out.println(data);
        System.out.println(holder.name);       
        Task t = data.get(position);
        System.out.println(t);
        //System.out.println(t.getName());
        
        holder.id.setText(String.valueOf(t.getId()) + ")");       
        holder.userStoryId.setText("Userstory id: " + t.getUserstoryId());
        holder.memberId.setText("Member id: " + String.valueOf(t.getMemberId()));  
        holder.comment.setText(t.getComment());
        holder.time.setText("Time: " + String.valueOf(t.getEstimatedTime()));  
        holder.deadline.setText("Sprint: " + String.valueOf(t.getDeadline()));  
        holder.name.setText(t.getName());
        holder.status.setText("Status: " + t.getStatus());
        final int taskId = t.getId();
        if (t.getStatus().equals("ToDo"))
        {
        	holder.change_status.setText(context.getString(R.string.changeStatusToDo));
        	holder.change_status.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                	db.open();
            		long id = db.updateStatus(taskId, "InProgress");
            		db.close();
            		remove(getItem(finalPosition));
            		notifyDataSetChanged();
                }
            });
        }
        else if (t.getStatus().equals("InProgress"))
        {
        	holder.change_status.setText(context.getString(R.string.changeStatusInProgress));
        	holder.change_status.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                	db.open();
            		long id = db.updateStatus(taskId, "Completed");
            		db.close();
            		remove(getItem(finalPosition));
            		notifyDataSetChanged();
                }
            });
        }
        else
        {
        	holder.change_status.setVisibility(View.GONE);
        }

        
        return row;
    }
	
	static class TaskHolder
	{
		TextView name;
		TextView id;
		TextView userStoryId;
		TextView memberId;
		TextView comment;
		TextView time;
		TextView status;
		TextView deadline;
		Button change_status;
		
	}
	
	public void updateTaskList(ArrayList<Task> newlist) {
	    data.clear();
	    data.addAll(newlist);
	    this.notifyDataSetChanged();
	}
}