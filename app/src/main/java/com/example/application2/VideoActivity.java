package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoPlayer;
    private MediaController mController;
    private String videoFileName ="point21";
    private String uriResourceTitle ="android.resource://";
    private int intVideoResourceId;
    private String stringVideoResourceURI;
    private Uri videoResourceURI;
    private TextView textview_title;
    private int intPauseTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoPlayer = findViewById(R.id.videoview_main);
        textview_title = findViewById(R.id.textview_title);
        intVideoResourceId =getResources().getIdentifier(videoFileName,"raw",getPackageName());
        stringVideoResourceURI = uriResourceTitle + getPackageName()+"/"+intVideoResourceId;
        videoResourceURI= Uri.parse(stringVideoResourceURI);
        videoPlayer.setVideoURI(videoResourceURI);

       //videoPlayer.setVideoURI(Uri.parse(uriResourceTitle+getPackageName()+"/"+intVideoResourceId));

        mController = new MediaController(VideoActivity.this);
        videoPlayer.setMediaController(mController);
        videoPlayer.start();

    }


    @Override
    protected void onPause() {
        super.onPause();
        if(videoPlayer != null){
            if(videoPlayer.isPlaying()){
                intPauseTime = videoPlayer.getCurrentPosition();
                videoPlayer.pause();
            }
        }
        textview_title.setText("功能-影片撥放器 : 暫停播放於["+ String.valueOf(intPauseTime) +"]");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(videoPlayer != null){

            videoPlayer.seekTo(intPauseTime);
            videoPlayer.start();
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(videoPlayer !=null){
            if(videoPlayer.isPlaying()){

                videoPlayer.stopPlayback();
            }
            videoPlayer=null;
            mController=null;

        }

    }























}