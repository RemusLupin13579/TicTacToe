package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Camera extends AppCompatActivity implements View.OnClickListener {

    ImageView iv;
    Button btnTakePic;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        iv=(ImageView)findViewById(R.id.iv);
        btnTakePic=(Button)findViewById(R.id.btnTakePic);
        btnTakePic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

    }
}
