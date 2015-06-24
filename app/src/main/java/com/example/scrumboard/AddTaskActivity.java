package com.example.scrumboard;


import java.util.Calendar;
import java.util.Date;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.scrumboard.db.DataSource;
import com.example.scrumboard.model.Status;
import com.example.scrumboard.model.Task;

public class AddTaskActivity extends ActionBarActivity{

    private DataSource db;
    
    private EditText name;
    private EditText estimatedTime;
    private EditText deadline;
    private Spinner status;
    private EditText comments;
    private Button addBtn;
    
    private Date selectedDate;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.task);
        
        db = new DataSource(getApplicationContext());
        
        name = (EditText) findViewById(R.id.edittext_name);
        estimatedTime = (EditText) findViewById(R.id.edittext_estimated_time);
        deadline = (EditText) findViewById(R.id.edittext_deadline);
        status = (Spinner) findViewById(R.id.spinner_status);
        status.setAdapter(new ArrayAdapter<Status>(this, R.layout.status_item, Status.values()));

        comments = (EditText) findViewById(R.id.edittext_comments);
        
        addBtn = (Button) findViewById(R.id.button_add_task);
        addBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                
                handleTaskAddition();
                finish();
                
            }
        });
        
        deadline.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {

                Calendar mcurrentDate=Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(AddTaskActivity.this, new OnDateSetListener() {                  
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                    	int correctmonth = selectedmonth+1;
                    	
                    	deadline.setText(selectedday + "-" + correctmonth + "-" + selectedyear);
                    }
                },mYear, mMonth, mDay);
                selectedDate = new java.sql.Date(mcurrentDate.getTime().getTime());
                mDatePicker.setTitle("Select deadline");                
                mDatePicker.show();  }
        });
        
    }

    protected void handleTaskAddition() {
    	
    	Task taskNew = new Task();
    	
    	if (name.getText().length()<1 || deadline.getText().length()<1 || status.getSelectedItem().toString().length()<1)
    	{
    		Toast.makeText(getApplicationContext(), "Fill out necessary fields!", Toast.LENGTH_LONG).show();
          return;
    	}
    	else
    	{  
    	    db.open();
    		taskNew.setName(name.getText().toString());
    		taskNew.setEstimatedTime(Integer.parseInt(estimatedTime.getText().toString()));
    		taskNew.setDeadline(selectedDate);
    		taskNew.setStatus(status.getSelectedItem().toString());
    		taskNew.setComment(comments.getText().toString());

    		long id = db.insertTask(taskNew);
    		Toast.makeText(getApplicationContext(), String.valueOf(id), Toast.LENGTH_LONG).show();
    		db.close();
    	}
    }
}
