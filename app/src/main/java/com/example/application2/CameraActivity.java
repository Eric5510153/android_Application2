package com.example.application2;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class CameraActivity extends AppCompatActivity {

    private ImageView image_photo;
    private Button button_start_camera;
    private static  final int REQUEST_IMAGE=100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        image_photo=findViewById(R.id.image_photo);
        button_start_camera=findViewById(R.id.button_start_camera);
        button_start_camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i,REQUEST_IMAGE);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode,resultCode,data);


        if(requestCode == REQUEST_IMAGE){
            if(resultCode == RESULT_OK){
                Bitmap bitmapImage =(Bitmap)data.getExtras().get("data");
                image_photo.setImageBitmap(bitmapImage);


            }



        }




    }










}