package com.example.sample;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

public class dateActivity extends AppCompatActivity  {

    String sdate;
    EditText input_date;
    Button button_search;
    //ImageButton datepicker;
    int year_y,day_d,month_m;
    static final int Dialog_ID = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);
        input_date=(EditText)findViewById(R.id.edittext_input_date);
        button_search=(Button)findViewById(R.id.button_idate);
    }



    public void searchdate(View view)
    {
         //String vdate = sdate.toString();
        sdate = input_date.getText().toString();

          if(TextUtils.isEmpty(sdate))
          {
              input_date.setError("Enter the Date");
          }

          backalldate ba = new backalldate(this);
          ba.execute(sdate);

    }


}
