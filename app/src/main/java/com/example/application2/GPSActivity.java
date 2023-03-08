package com.example.application2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.location.Location;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;


public class GPSActivity extends AppCompatActivity implements LocationListener {

    private TextView twxtview_gps_informantion ;
    private LocationManager lc ;
    private Location currentLocation;
    private Button button_set_gps, button_start_gps,button_show_map;
    private String bestProvider;
    private static  final int PERMISSION_REQUEST_GPS =101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsactivity);

        twxtview_gps_informantion = findViewById(R.id.twxtview_gps_informantion );
        button_set_gps=findViewById(R.id.button_set_gps);
        button_start_gps=findViewById(R.id.button_start_gps);
        button_show_map=findViewById(R.id.button_show_map);

        lc =(LocationManager)  getSystemService(LOCATION_SERVICE);
        if(!lc.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            AlertDialog.Builder builder =new AlertDialog.Builder(GPSActivity.this);
            builder.setTitle("GPS 硬體功能設定");
            builder.setMessage("GPS 硬體裝置尚未啟用!! \n 請先啟用GPS裝置 ,否則無法接收位置資料" );
            builder.create();
            builder.show();

        }

         if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_GPS);



                }


         }

        button_set_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        });

        button_start_gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat,lng;
                String stringOutput;
                bestProvider = lc.getBestProvider(new Criteria(),true);

                try{
                if(bestProvider != null) {

                    lc.requestLocationUpdates(bestProvider, 1000, 1.0f, GPSActivity.this);
                    currentLocation=lc.getLastKnownLocation(bestProvider);
                    if(currentLocation !=null){
                        lat=currentLocation.getLatitude();
                        lng=currentLocation.getLongitude();
                        stringOutput  = "啟動GPS自動更新成功!! 定位資料提供者:" +bestProvider+"\n 緯度資料:"+lat+"經度資料:"+lng  ;
                    }
                    else{
                            stringOutput="啟動GPS自動更新功能失敗... currentLocation=null";
                        }
                    }
                    else{
                            stringOutput="啟動GPS自動更新功能失敗... bestProvider=null";
                         }
                    twxtview_gps_informantion.setText(stringOutput);
                }catch(SecurityException ex){

                    stringOutput= "注意!! 啟動GPS自動更新失敗... 發生SecurityException ,操作權限不足";
                    twxtview_gps_informantion.setText(stringOutput);
                }
            }
        });

        button_show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double lat, lng;
                String label = "目前位置";
                String geoString, queryString, uriString;

                lat = currentLocation.getLatitude();
                lng = currentLocation.getLongitude();
                geoString = "geo:" + lat + "," + lng;
                queryString = lat + "," + lng + "(" + label + ")";
                uriString = Uri.encode(queryString);
                geoString = geoString + "?q" + uriString;

                Intent geoIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoString));
                startActivity(geoIntent);
                     }
            });

    }








    @Override
    protected void onPause(){
        super.onPause();
        lc.removeUpdates(GPSActivity.this);

        Log.d("GPS-","目前GPS資訊:GPS自動更新功能已關閉!!");

    }
    @Override
    protected  void onResume(){
        super.onResume();
        int minReflashTime=5000;
        float minDistance=5.0f;
        String stringOutput;
        bestProvider=lc.getBestProvider(new Criteria(),true);
        try{
            if(bestProvider != null) {

                lc.requestLocationUpdates(bestProvider, minReflashTime, minDistance, GPSActivity.this);
                stringOutput="啟動GPS 自動更新成功!! 訂位資料提供者:"+bestProvider;

            }else{
                stringOutput="啟動GPS自動更新功能失敗... bestProvider=null";
            }
            Log.d("GPS-" ,stringOutput);
        }catch(SecurityException ex){

            stringOutput= "注意!! 啟動GPS自動更新失敗... 發生SecurityException ,操作權限不足";

            Log.d("GPS-",stringOutput);
        }




    }




    @Override
    public void onLocationChanged(Location location){
        double lat ,lng;
        String stringOutput;
        if (location !=null) {
            currentLocation = location;
            lat = currentLocation.getLatitude();
            lng = currentLocation.getLongitude();
            stringOutput = "GPS 座標更新成功!! 訂位資料提供者 :"+bestProvider+"\n 緯度資料:"+lat+"經度資料:"+lng  ;
        }else{

            stringOutput = "GPS 座標更新失敗... location=null";;

        }
        twxtview_gps_informantion.setText(stringOutput);

        }


        @Override
         public void onRequestPermissionsResult(int requestCode,String[] permissions,int[]grantResults){

        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if(requestCode==PERMISSION_REQUEST_GPS){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){

            twxtview_gps_informantion.setText("目前GPS資訊:要求GPS裝置存取權限成功");

        }else {

                twxtview_gps_informantion.setText("目前GPS資訊:要求GPS裝置存取權限失敗");

            }


        }



    }




}