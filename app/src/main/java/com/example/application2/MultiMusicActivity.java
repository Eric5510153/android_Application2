package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MultiMusicActivity extends AppCompatActivity {



    private Button botton_pause, botton_stop, botton_start;


    private Spinner spinner_music_list;
    private MediaPlayer player;
    private String[] arrayMusicList;
    private String stringSelectMusicName;
    private int[] arrayMusicId;
    private int i,intSelectMusicId;
    private TextView textview_status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_music);

//--------------↓↓↓↓↓↓↓↓↓↓↓↓↓↓-------------------
        player=MediaPlayer.create(MultiMusicActivity.this,intSelectMusicId);
        player.setOnCompletionListener((MediaPlayer.OnCompletionListener) this);
        botton_pause=findViewById(R.id.botton_pause);
        botton_stop=findViewById(R.id.botton_stop);
        botton_start=findViewById(R.id.botton_start);


//------------------↑↑↑↑↑↑↑↑↑↑↑↑------------------


        spinner_music_list =findViewById(R.id.spinner_music_list);
        arrayMusicList = getResources().getStringArray(R.array.multi_music_list);
        textview_status=findViewById(R.id.textview_status);


        arrayMusicId = new int[arrayMusicList.length];
        for( i = 0; i<arrayMusicList.length;i++){

            arrayMusicId[i]=getResources().getIdentifier(arrayMusicList[i],"raw",getPackageName() );



        }
//--------------↓↓↓↓↓↓↓↓↓↓↓↓↓↓---------------

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




//------------------↑↑↑↑↑↑↑↑↑↑↑↑------------------


       spinner_music_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int selected_index, long l) {
               stringSelectMusicName=arrayMusicList[selected_index];
               intSelectMusicId=arrayMusicId[selected_index];
               textview_status.setText("播放曲目"+stringSelectMusicName+"歌曲編號"+String.valueOf(intSelectMusicId));

           }



           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {


           }
       });






    }
}