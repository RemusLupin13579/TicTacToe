package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Welcome extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tv=(TextView)findViewById(R.id.tvDisplay);
        Intent intent=getIntent();
        String name = intent.getExtras().getString("name");
        tv.setText("welcone " + name);
    }
}
