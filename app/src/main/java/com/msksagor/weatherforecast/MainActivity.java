package com.msksagor.weatherforecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private String tempUnit = "metric";
    private String city = "dhaka";
    private int dayCount = 7;
    TextView textView;

    private static final String BASEURL = "https://samples.openweathermap.org/data/2.5/forecast/";
    MyAPI myAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        myAPI =retrofit.create(MyAPI.class);
        Call<Example> call = myAPI.getApi();
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Log.d("s","Primary Level");
                if(response.code()==200){
                    Example example = response.body();
                    StringBuffer stringBuffer = new StringBuffer();

                    String data  ="Humidity  :"+ example.getList().get(0).getHumidity().toString();
                    String data1  ="Clouds  :"+ example.getList().get(0).getClouds().toString();
                    String data2  ="Deg  :"+ example.getList().get(0).getDeg().toString();
                    String data3  ="Pressure   :"+ example.getList().get(0).getPressure().toString();
                    String data4 = "Temp  :"+ example.getList().get(0).getTemp().toString();
                    stringBuffer.append(data+"\n");
                    stringBuffer.append(data1+"\n");
                    stringBuffer.append(data2+"\n");
                    stringBuffer.append(data3+"\n");
                    stringBuffer.append(data4+"\n");

                    textView.setText(stringBuffer.toString());


                }


            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
