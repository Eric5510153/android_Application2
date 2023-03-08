package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MusicActivity extends AppCompatActivity implements MediaPlayer.OnCompletionListener {


    private MediaPlayer player;
    private Button botton_pause, botton_stop, botton_start;
    private TextView textview_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        player = MediaPlayer.create(MusicActivity.this, R.raw.nothing);
        player.setOnCompletionListener(this);

        botton_pause=findViewById(R.id.botton_pause);
        botton_stop=findViewById(R.id.botton_stop);
        botton_start=findViewById(R.id.botton_start);
        textview_status=findViewById(R.id.textview_status);



        botton_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player !=null){
                    if(player.isPlaying()!=true){
                        player.start();
                        textview_status.setText("播放狀態:音樂播放中...");
                    }
                }
            }
        });

        botton_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player !=null){
                    if(player.isPlaying()==true){
                        player.pause();
                        textview_status.setText("播放狀態:暫停");
                    }
                }
            }
        });

        botton_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(player !=null){
                      player.stop();
                      try {
                          player.prepareAsync();
                      }catch (Exception ex){
                          ex.printStackTrace();
                      }
                        textview_status.setText("播放狀態:停止");
                    }
                }
        });



    }


    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {

        textview_status.setText("播放狀態:音樂撥放完畢");
        player.seekTo(0);

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(player !=null){

            player.release();
            player=null;
        }
    }



}