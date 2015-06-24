package com.example.scrumboard;

import java.util.Calendar;

import com.example.scrumboard.db.DataSource;
import com.example.scrumboard.model.Sprint;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import java.sql.Date;

public class AddSprintActivity extends AppCompatActivity {

	private DataSource db;


	private EditText startDate;
	private EditText endDate;
	private Button addBtn;

	private Date selectedStartDate;
	private Date selectedEndDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.sprint);

		db = new DataSource(getApplicationContext());


		startDate = (EditText) findViewById(R.id.edittext_start_date);
		endDate = (EditText) findViewById(R.id.edittext_end_date);

		addBtn = (Button) findViewById(R.id.button_add_sprint);
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				handleSprintAddition();
				finish();
			}
		});

		startDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Calendar mcurrentDate=Calendar.getInstance();
				int mYear = mcurrentDate.get(Calendar.YEAR);
				int mMonth = mcurrentDate.get(Calendar.MONTH);
				int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog mDatePicker=new DatePickerDialog(AddSprintActivity.this, new OnDateSetListener() {                  
					public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
						int correctmonth = selectedmonth+1;

						startDate.setText(selectedday + "-" + correctmonth + "-" + selectedyear);
					}
				},mYear, mMonth, mDay);
				selectedStartDate = new java.sql.Date(mcurrentDate.getTime().getTime());
				mDatePicker.setTitle("Select start date");                
				mDatePicker.show();  }
		});

		endDate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Calendar mcurrentDate=Calendar.getInstance();
				int mYear = mcurrentDate.get(Calendar.YEAR);
				int mMonth = mcurrentDate.get(Calendar.MONTH);
				int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

				DatePickerDialog mDatePicker=new DatePickerDialog(AddSprintActivity.this, new OnDateSetListener() {                  
					public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
						int correctmonth = selectedmonth+1;

						endDate.setText(selectedday + "-" + correctmonth + "-" + selectedyear);
					}
				},mYear, mMonth, mDay);
				selectedEndDate = new java.sql.Date(mcurrentDate.getTime().getTime());
				mDatePicker.setTitle("Select end date");                
				mDatePicker.show();  }
		});

	}

	protected void handleSprintAddition() {

		Sprint sprintNew = new Sprint();

		if (startDate.getText().length()<1 || endDate.getText().length()<1 )
		{
			Toast.makeText(getApplicationContext(), "Fill out necessary fields!", Toast.LENGTH_LONG).show();
			return;
		}
		else
		{
			sprintNew.setStartDate(selectedStartDate);
			sprintNew.setEndDate(selectedEndDate);
			db.open();
			long id = db.insertSprint(sprintNew);

			db.close();
		}
	}

}
