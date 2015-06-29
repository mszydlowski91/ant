package com.example.scrumboard;

import java.util.Calendar;

import com.example.scrumboard.db.DataSource;
import com.example.scrumboard.model.Project;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import java.sql.Date;

public class AddProjectActivity extends ActionBarActivity{

	private DataSource db;

	private EditText name;
	private EditText description;
	private EditText master;
	private EditText startDate;
	private Spinner status;
	private EditText priority;
	private EditText deadline;

	private Button addBtn;

	private Date selectedStartDate;
	private Date selectedDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.project);

		db = new DataSource(getApplicationContext());

		name = (EditText) findViewById(R.id.edittext_name);
		description = (EditText) findViewById(R.id.edittext_description);
		master = (EditText) findViewById(R.id.edittext_master);
		startDate = (EditText) findViewById(R.id.edittext_start_date);
		status = (Spinner) findViewById(R.id.spinner_status);
		priority = (EditText) findViewById(R.id.edittext_priority);
		deadline = (EditText) findViewById(R.id.edittext_deadline);

		addBtn = (Button) findViewById(R.id.button_add_project);
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				handleProjectAddition();

			}
		});
		startDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Calendar mcurrentDate=Calendar.getInstance();
				int mYear = mcurrentDate.get(Calendar.YEAR);
				int mMonth = mcurrentDate.get(Calendar.MONTH);
				int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog mDatePicker=new DatePickerDialog(AddProjectActivity.this, new OnDateSetListener() {                  
					public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
						int correctmonth = selectedmonth+1;

						startDate.setText(selectedday + "-" + correctmonth + "-" + selectedyear);
					}
				},mYear, mMonth, mDay);
				selectedStartDate = new java.sql.Date(mcurrentDate.getTime().getTime());
				mDatePicker.setTitle("Select start date");                
				mDatePicker.show();  }
		});

		deadline.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Calendar mcurrentDate=Calendar.getInstance();
				int mYear = mcurrentDate.get(Calendar.YEAR);
				int mMonth = mcurrentDate.get(Calendar.MONTH);
				int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog mDatePicker=new DatePickerDialog(AddProjectActivity.this, new OnDateSetListener() {                  
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

	protected void handleProjectAddition() {

		Project projectNew = new Project();

		if (name.getText().length()<1 || description.getText().length()<1 || startDate.getText().length()<1 || status.getSelectedItem().toString().length()<1 || deadline.getText().length()<1 )
		{
			Toast.makeText(getApplicationContext(), "Fill out necessary fields!", Toast.LENGTH_LONG).show();
			return;
		}
		else
		{
			projectNew.setName(name.getText().toString());
			projectNew.setDescription(description.getText().toString());
			projectNew.setMaster(Integer.parseInt(master.getText().toString()));
			projectNew.setStartDate(selectedStartDate);
			//projectNew.setStatus(status.getSelectedItem());
			projectNew.setPriority(Integer.parseInt(priority.getText().toString()));
			projectNew.setDeadline(selectedDate);
			db.open();
			db.insertProject(projectNew);
			db.close();
		}
	}

}
