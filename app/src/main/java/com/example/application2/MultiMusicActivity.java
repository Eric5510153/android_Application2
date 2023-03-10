package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class MultiMusicActivity extends AppCompatActivity implements  MediaPlayer.OnCompletionListener{






    private Spinner spinner_music_list;
    private MediaPlayer player;
    private Button botton_pause, botton_stop, botton_start;
    private TextView textview_status;
    private String[] arrayMusicList;
    private String stringSelectMusicName;
    private int[] arrayMusicId;
    private int i,intSelectMusicId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_music);

//--------------↓↓↓↓↓↓↓↓↓↓↓↓↓↓-------------------


        // player=MediaPlayer.create(MultiMusicActivity.this,intSelectMusicId);
        // player.setOnCompletionListener((MediaPlayer.OnCompletionListener) this);
        botton_pause=findViewById(R.id.botton_pause);
        botton_stop=findViewById(R.id.botton_stop);
        botton_start=findViewById(R.id.botton_start);
        textview_status=findViewById(R.id.textview_status);

//------------------↑↑↑↑↑↑↑↑↑↑↑↑------------------

        spinner_music_list =findViewById(R.id.spinner_music_list);
        arrayMusicList = getResources().getStringArray(R.array.multi_music_list);



        arrayMusicId = new int[arrayMusicList.length];
        for( i = 0; i<arrayMusicList.length;i++){

            arrayMusicId[i]=getResources().getIdentifier(arrayMusicList[i],"raw",getPackageName() );



        }
        spinner_music_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int selected_index, long l) {
                stringSelectMusicName=arrayMusicList[selected_index];
                intSelectMusicId=arrayMusicId[selected_index];
                Log.d("MultiMusic","選單"+stringSelectMusicName+intSelectMusicId);
                textview_status.setText("播放曲目"+stringSelectMusicName+"歌曲編號"+String.valueOf(intSelectMusicId));
                createPlayer(intSelectMusicId);

            }



            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });
        Log.d("MultiMusic","外部"+stringSelectMusicName+intSelectMusicId);
        createPlayer(arrayMusicId[0]);
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



    }

    @Override
    protected void onPause() {
        super.onPause();
        if(player!=null){
            //當活動畫面切換到背景時自動暫停播放
            if(player.isPlaying()) player.pause();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player!=null){
            //畫面切換回前景則從暫停位置接續撥放
            if(player.getCurrentPosition()>0) player.start();
        }
    }
    public void createPlayer(int musicId){
        if(player!=null){
            if(player.isPlaying()) player.stop();
            player.release();
            player=null;
        }
        player=MediaPlayer.create(MultiMusicActivity.this,musicId) ;
        player.setOnCompletionListener(this);
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        Log.d("MultiMusic","音樂播放完畢"+stringSelectMusicName+intSelectMusicId);
        textview_status.setText("播放狀態 : "+stringSelectMusicName+" 音樂播放完畢");
        player.seekTo(0);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(player!=null){
            if(player.isPlaying()) player.stop();
            player.release(); //釋放MediaPlayer物件佔用的資源
            player=null;
        }
    }



}