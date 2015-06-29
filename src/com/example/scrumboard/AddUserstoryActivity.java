package com.example.scrumboard;


import com.example.scrumboard.db.DataSource;
import com.example.scrumboard.model.Status;
import com.example.scrumboard.model.UserStory;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddUserstoryActivity extends ActionBarActivity {

	private DataSource db;

	private EditText name;
	private EditText details;
	private Spinner status;
	private EditText priority;
	private EditText estimatedTime;
	private EditText comments;
	private Button addBtn;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.userstory);

		db = new DataSource(getApplicationContext());

		name = (EditText) findViewById(R.id.edittext_name);
		details = (EditText) findViewById(R.id.edittext_details);
		status = (Spinner) findViewById(R.id.spinner_status);
		status.setAdapter(new ArrayAdapter<Status>(this, R.layout.status_item, Status.values()));
		priority = (EditText) findViewById(R.id.edittext_priority);
		estimatedTime = (EditText) findViewById(R.id.edittext_estimated_time);
		comments = (EditText) findViewById(R.id.edittext_comments);

		addBtn = (Button) findViewById(R.id.button_add_userstory);
		addBtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {

				handleUserstoryAddition();
				Toast.makeText(getApplicationContext(), "Succesfully added!", Toast.LENGTH_LONG).show();
				onBackPressed();
			}
		});
	}

	protected void handleUserstoryAddition() {

		UserStory userstoryNew = new UserStory();

		if (name.getText().length()<1 || details.getText().length()<1 || status.getSelectedItem().toString().length()<1)
		{
			Toast.makeText(getApplicationContext(), "Fill out necessary fields!", Toast.LENGTH_LONG).show();
			return;
		}
		else
		{
			userstoryNew.setName(name.getText().toString());
			userstoryNew.setDetails(details.getText().toString());
			userstoryNew.setStatus(status.getSelectedItem().toString());
			userstoryNew.setPriority(Integer.parseInt(priority.getText().toString()));
			userstoryNew.setEstimatedTime(Integer.parseInt(estimatedTime.getText().toString()));
			userstoryNew.setComment(comments.getText().toString());
			db.open();
			long id = db.insertUserStory(userstoryNew);
			//Toast.makeText(this, String.valueOf(id), Toast.LENGTH_LONG).show();
			db.close();
		}
	}
}
