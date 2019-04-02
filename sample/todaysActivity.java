package com.example.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class todaysActivity extends AppCompatActivity {

    Button date_button;
    //EditText input_date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todays);

        date_button=(Button)findViewById(R.id.button_date);
        //input_date=(EditText)findViewById(R.id.edittext_today);
    }

    public void dateprocess(View view) {
        String date = "";  //input_date.getText().toString();
        //String day="";

        backtoday bt = new backtoday(this);
        bt.execute(date);
    }
}
