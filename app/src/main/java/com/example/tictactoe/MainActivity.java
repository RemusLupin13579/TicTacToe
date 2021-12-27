package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import static android.os.Build.VERSION_CODES.P;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sp;
//int i = 354;x`
    Dialog d;
    EditText etUserName;
    EditText etPass;
    Button btnCustomLog;
    Button btnLog;
    Button btnAlert;
    Button btnPro;
    ProgressDialog p;
    Button btnDate;
    Button btnTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLog = findViewById(R.id.btnLog);
        btnLog.setOnClickListener(this);
        sp = getSharedPreferences("dialog", 0);

        btnAlert = (Button) findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(this);

        btnPro = findViewById(R.id.btnPro);
        btnPro.setOnClickListener(this);

        btnDate= findViewById(R.id.btnDate);
        btnDate.setOnClickListener(this);

        btnTime= findViewById(R.id.btnTime);
        btnTime.setOnClickListener(this);
    }

    public void createLoginDialog() {

        //connect to sp
        String strname = sp.getString("username", null);
        String strpass = sp.getString("pass", null);

        d = new Dialog(this);
        d.setContentView(R.layout.custom_layout);
        d.setTitle("Login");
        d.setCancelable(true);
        etUserName = (EditText) d.findViewById(R.id.etUserName);
        etPass = (EditText) d.findViewById(R.id.etPassword);

        if (strname != null && strpass != null) {
            etUserName.setText(strname);
            etPass.setText(strpass);
        }

        btnCustomLog = (Button) d.findViewById(R.id.etCustomLogIn);
        btnCustomLog.setOnClickListener(this);
        d.show();
    }

    @Override
    public void onClick(View v) {
        if (v == btnPro){
            p = ProgressDialog.show(this, "Please hold", "while we ignore you completely", true);
        p.setCancelable(true);
        p.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        p.setMessage("Loading...");
        p.show();
    }
        if (v == btnLog) {
            createLoginDialog();
        } else if (v == btnCustomLog) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", etUserName.getText().toString());
            editor.putString("pass", etPass.getText().toString());
            editor.commit();
            Toast.makeText(this, "username password saved", Toast.LENGTH_LONG).show();
            d.dismiss();
        }
        if (v == btnAlert) {
            //int a - 0;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setMessage("Click 'I agree' if we can say you ate\nall the cookies.");
            builder.setCancelable(true);
            builder.setPositiveButton("I agree", new HandleAlertDialogListener());
            builder.setNegativeButton("No, i dont agree", new HandleAlertDialogListener());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        if(v==btnDate){
            Calendar systemCalender = Calendar.getInstance();
            int year = systemCalender.get(Calendar.YEAR);
            int month = systemCalender.get(Calendar.MONTH);
            int day = systemCalender.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,new SetDate(),year,month,day);
            datePickerDialog.show();
        }
        if(v==btnTime){
            Calendar systemCalendar = Calendar.getInstance();
            int hour = systemCalendar.get(Calendar.HOUR_OF_DAY);
            int minute = systemCalendar.get(Calendar.MINUTE);
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,new SetYourTime(),hour,minute,true);
            timePickerDialog.show();
        }
    }


    public class HandleAlertDialogListener implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this,"You selected "+which ,Toast.LENGTH_LONG).show();
        }
    }
    public class SetDate implements DatePickerDialog.OnDateSetListener
    {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear = monthOfYear +1;

            String str = "You selected :" + dayOfMonth + "/" + monthOfYear +"/" + year;
            Toast.makeText(MainActivity.this,str,Toast.LENGTH_LONG).show();
            btnDate.setText(str);

        }
    }

    public class SetYourTime implements TimePickerDialog.OnTimeSetListener
    {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

            String str = "Time is :" + hourOfDay +":" + minute;
            btnTime.setText(str);
        }
    }
}