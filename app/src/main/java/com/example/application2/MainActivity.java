package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button button_MusicActivity,button_VideoActivity,button_GPSActivity,button_CameraActivity,button_MultiMusicActivity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_MusicActivity=findViewById(R.id.button_MusicActivity);
        button_VideoActivity=findViewById(R.id.button_VideoActivity);
        button_GPSActivity=findViewById(R.id.button_GPSActivity);
        button_CameraActivity=findViewById(R.id.button_CameraActivity);
        button_MultiMusicActivity=findViewById(R.id.button_MultiMusicActivity);





        button_MultiMusicActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,MultiMusicActivity.class);
                startActivity(i);
            }
        });

        button_MusicActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,MusicActivity.class);
                startActivity(i);
            }
        });

        button_VideoActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,VideoActivity.class);
                startActivity(i);
            }
        });

        button_GPSActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,GPSActivity.class);
                startActivity(i);
            }
        });

        button_CameraActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(MainActivity.this,CameraActivity.class);
                startActivity(i);
            }
        });


    }
}