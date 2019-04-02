package com.example.sample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import static android.text.TextUtils.isEmpty;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String[] dept = {"Department","Computer Science and Engineering","Information Technology","Mechanical Engineering","Civil Engineering",
            "Electronics and communication engineering","Electrical and Electronics Engineering"," Electronics and Instrumentation Engineering"};
    String[] yearr={"Year","1","2","3","4"};
    String sname, sroll, spasswr, semail, sdeptt, syear;
    EditText name, roll, passwr, email;
    Spinner deptt,year;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name =(EditText)findViewById(R.id.edit_text_name);
        roll =(EditText)findViewById(R.id.edit_text_rollno);
        passwr =(EditText)findViewById(R.id.edit_text_pass);
        email =(EditText)findViewById(R.id.edit_text_email);
        deptt =(Spinner) findViewById(R.id.spinner_dept);
        year =(Spinner) findViewById(R.id.spinner_year);
        submit = (Button) findViewById(R.id.button_reg);

        deptt.setPrompt("Department");
        year.setPrompt("Year");
        deptt.setOnItemSelectedListener( this);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,dept);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        deptt.setAdapter(aa);

        year.setOnItemSelectedListener(this);
        ArrayAdapter ab = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,yearr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        year.setAdapter(ab);


        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                sname = name.getText().toString();
                sroll = roll.getText().toString();
                spasswr = passwr.getText().toString();
                semail = email.getText().toString();
                sdeptt = ((Spinner) findViewById(R.id.spinner_dept)).getSelectedItem().toString();
                syear = ((Spinner) findViewById(R.id.spinner_year)).getSelectedItem().toString();
                if(TextUtils.isEmpty(sname))
                {
                    name.setError("Enter the Name");
                    //Toast.makeText(getApplicationContext(),"Enter the Name",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(sroll))
                {
                    roll.setError("Enter the Roll No");
                    //Toast.makeText(getApplicationContext(),"Enter the Roll No",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(spasswr))
                {
                    passwr.setError("Enter the Password");
                    //Toast.makeText(getApplicationContext(),"Enter the Password",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(semail))
                {
                    email.setError("Enter the Email");
                    //Toast.makeText(getApplicationContext(),"Enter the E-Mail",Toast.LENGTH_SHORT).show();
                }
                else if(sdeptt.equals("Department"))
                {
                    Toast.makeText(getApplicationContext(),"Enter the Department",Toast.LENGTH_SHORT).show();
                }
                else if(syear.equals("Year"))
                {
                    Toast.makeText(getApplicationContext(),"Enter the Year",Toast.LENGTH_SHORT).show();
                }
                else {

                    backgroundregister backgroundWorker = new backgroundregister(RegisterActivity.this);
                    backgroundWorker.execute(sname, sroll, spasswr, semail, sdeptt, syear);

                }} });

    }
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
        switch(arg1.getId())
        {
           /* case R.id.spinner_dept:
                Spinner spinner_1 = (Spinner) findViewById(R.id.spinner_dept);
                sdeptt = deptt.getSelectedItem().toString();
                //Toast.makeText(RegisterActivity.this,spinner_1.getChildAt(arg2).toString()+" 1",Toast.LENGTH_LONG).show();
                break;
            case R.id.spinner_year:
                Spinner spinner_2 = (Spinner) findViewById(R.id.spinner_year);
                syear = year.getSelectedItem().toString();
                //Toast.makeText(RegisterActivity.this,spinner_2.getChildAt(arg2).toString()+" 2",Toast.LENGTH_LONG).show();
                break;*/
        }

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        Toast.makeText(getApplicationContext(),"Empty credentials",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
