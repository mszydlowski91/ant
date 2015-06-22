package com.example.scrumboard;

import com.example.scrumboard.db.DataSource;
import com.example.scrumboard.model.Member;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends ActionBarActivity{

    private DataSource db;
    
    private EditText nameAndSurname;
    private EditText password;
    private EditText rePassword;
    private EditText email;
    private Button registerBtn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_registration);
        
        db = new DataSource(getApplicationContext());
        
        nameAndSurname = (EditText) findViewById(R.id.edittext_name_surname);
        password = (EditText) findViewById(R.id.edittext_password);
        rePassword = (EditText) findViewById(R.id.edittext_repeat_password);
        email = (EditText) findViewById(R.id.edittext_email);
        registerBtn = (Button) findViewById(R.id.button_register);
        
        registerBtn.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View arg0) {
                
                handleRegistration();
                
            }
        });
        
    }

    protected void handleRegistration() {
        
        Member memberNew = new Member();
        
        if(password.getText().toString().equals(rePassword.getText().toString())){
            memberNew.setPassword(password.getText().toString());
        }
        else{
            Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_LONG).show();
            return;
        }
        
        String[] nameSurname = nameAndSurname.getText().toString().split(" ");
        if(nameSurname.length == 1){
            Toast.makeText(getApplicationContext(), "Both name and surname is required.", Toast.LENGTH_LONG).show();
            return;
        }
        memberNew.setName(nameSurname[0]);
        memberNew.setSurname(nameSurname[1]);
        memberNew.setMail(email.getText().toString());
        
        db.insertMember(memberNew);
    }
}
