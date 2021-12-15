package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    SharedPreferences sp;

    Dialog d;
    EditText etUserName;
    EditText etPass;
    Button btnCustomLog;
    Button btnLog;

    Button btnAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLog = findViewById(R.id.btnLog);
        btnLog.setOnClickListener(this);
        sp = getSharedPreferences("dialog", 0);

        btnAlert = (Button) findViewById(R.id.btnAlert);
        btnAlert.setOnClickListener(this);
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

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Alert");
            builder.setMessage("Click 'I agree' if we can say you ate\nall the cookies.");
            builder.setCancelable(true);
            builder.setPositiveButton("I agree", new HandleAlertDialogListener());
            builder.setNegativeButton("No, i dont agree", new HandleAlertDialogListener());
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public  class  HandleAlertDialogListener implements DialogInterface.OnClickListener
    {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(MainActivity.this,"You selected "+which ,Toast.LENGTH_LONG).show();
        }
    }
}