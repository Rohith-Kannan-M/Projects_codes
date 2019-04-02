package com.example.sample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyPrefs" ;
    SharedPreferences sharedpreferences;
    EditText input_rollno,input_passw;
    Button login,register;
    String str_rollno,str_passw;

    public static final String rolll = "rollkey";
    public static final String passs = "passkey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input_rollno=(EditText)findViewById(R.id.editText_rollno);
        input_passw=(EditText)findViewById(R.id.editText_passw);
        login=(Button)findViewById(R.id.button_login);
        register=(Button)findViewById(R.id.button_register);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                str_rollno=input_rollno.getText().toString();
                str_passw=input_passw.getText().toString();

                if(TextUtils.isEmpty(str_rollno)){
                    input_rollno.setError("Enter the Roll No");
                }
                else if(TextUtils.isEmpty(str_passw)){
                    input_passw.setError("Enter the Password");
                }
                else if(str_rollno.isEmpty() && str_passw.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter the Missing Credentials",Toast.LENGTH_SHORT).show();
                }

                else {
                    background back = new background(MainActivity.this);
                    back.execute(str_rollno, str_passw);
                }

            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);

            }
        });


    }

}
