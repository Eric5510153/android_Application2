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

public class GPSDecodeActivity extends AppCompatActivity {


    private EditText editText_Latitude,editText_Longitude;
    private TextView text_view_Latitude,text_view_Longitude,textview_result;
    private Button button_query_address;
    private double lat ,lng;
    private Geocoder geo;
    private List<Address> list_address;

    private final  int MAX_RESULT =3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpsdecode);

        editText_Latitude= findViewById(R.id.editText_Latitude);
        editText_Longitude= findViewById(R.id.editText_Longitude);
        text_view_Latitude= findViewById(R.id.text_view_Latitude);
        text_view_Longitude= findViewById(R.id.text_view_Longitude);
        textview_result= findViewById(R.id.textview_result);
        button_query_address=findViewById(R.id.button_query_address);

        geo =new Geocoder(GPSDecodeActivity.this, Locale.TAIWAN);
        button_query_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string_Output = "";
                int i, j;

                lat = Double.parseDouble(editText_Latitude.getText().toString());
                lng = Double.parseDouble(editText_Longitude.getText().toString());

                try {

                    list_address = geo.getFromLocation(lat, lng, MAX_RESULT);

                    if (list_address != null) {
                        if (!(list_address.isEmpty())) {
                            for (i = 0; i < list_address.size(); i++) {
                                Address a = list_address.get(i);
                                string_Output += (i + 1) + ":";
                                for (j = 0; j <= a.getMaxAddressLineIndex(); j++) {
                                    string_Output += a.getAddressLine(j) + ",";
                                }
                                string_Output += "\n";
                            }
                        } else {
                            string_Output = "Address List  輸入的座標沒有符合的地址資料";
                        }


                        textview_result.setText("查詢結果: \n" + string_Output);
                    } else {

                        textview_result.setText("查詢結果: \n Address List =Null 輸入的座標沒有符合的地址資料");
                    }

                } catch (IOException ex) {
                    textview_result.setText("查詢結果:例外狀況發生-" + ex.toString());
                }


            }
        });


        }
}