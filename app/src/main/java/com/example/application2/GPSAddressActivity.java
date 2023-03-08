package com.example.application2;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GPSAddressActivity extends AppCompatActivity {


    private EditText editText_address;
    private TextView text_view_address,textview_result_address;
    private Button button_query_address;
    private String addressName;
    private double lat ,lng;
    private Geocoder geo;
    private List<Address> gpsAddressList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsaddress);

        text_view_address= findViewById(R.id.text_view_address);
        textview_result_address=findViewById(R.id.textview_result_address);
        editText_address= findViewById(R.id.editText_address);
        button_query_address= findViewById(R.id.button_query_address);


        geo=new Geocoder(GPSAddressActivity.this, Locale.TAIWAN);

        button_query_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addressName=editText_address.getText().toString();
                try{

                    gpsAddressList=geo.getFromLocationName(addressName,1);

                    if (gpsAddressList!=null){

                        if(!(gpsAddressList.isEmpty())){

                            lat=gpsAddressList.get(0).getLatitude();
                            lng=gpsAddressList.get(0).getLongitude();
                            textview_result_address.setText("GPS座標查詢結果:\n 緯度:"+lat+"經度"+lng);

                        }else{
                        textview_result_address.setText("GPS座標尋結果:\n" +
                                "Address List 為空,輸入的地址資料沒有符合的GPS座標位置");
                        }

                    }else{
                        textview_result_address.setText("GPS座標尋結果:\n Address List=NUll,無法取得座標資訊");

                    }

                }catch (IOException ex){
                    textview_result_address.setText("GPS座標尋結果:\n 例外狀況發生 -"+ex.toString());


                }
            }
        });



    }
}