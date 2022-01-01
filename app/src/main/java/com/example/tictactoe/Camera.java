package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

public class Camera extends AppCompatActivity implements View.OnClickListener {

    ImageView iv;
    Button btnTakePic;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        iv = (ImageView) findViewById(R.id.iv);
        btnTakePic = (Button) findViewById(R.id.btnTakePic);
        btnTakePic.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == btnTakePic) {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE_SECURE);
            startActivityForResult(intent, 0);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0)//coming from camera
        {
            if (resultCode == RESULT_OK) {
                bitmap = (Bitmap) data.getExtras().get("data");
                iv.setImageBitmap(bitmap);
                Toast.makeText(this, "Thank you!", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Dont be ashamed of yourself", Toast.LENGTH_LONG).show();
            }
        }
    }
}
