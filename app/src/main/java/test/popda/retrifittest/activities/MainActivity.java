package test.popda.retrifittest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import test.popda.retrifittest.R;
import test.popda.retrifittest.adapter.RecycleViewAdapter;
import test.popda.retrifittest.model.WeatherDay;
import test.popda.retrifittest.model.WeatherForecast;
import test.popda.retrifittest.api.WeatherAPI;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tvTemp) TextView tvTemp;
    @BindView(R.id.ivImage) ImageView ivImage;
    @BindView(R.id.etCityName) EditText etCityName;
    @BindView(R.id.rv) RecyclerView rv;


    String TAG = "WEATHER";
    WeatherAPI.ApiInterface api;
    List<WeatherDay> items;
    String cityName;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        api = WeatherAPI.getClient().create(WeatherAPI.ApiInterface.class);
        rv.setLayoutManager(new LinearLayoutManager(this));

        items = new ArrayList<>();
        RecycleViewAdapter mAdapter = new RecycleViewAdapter(this, items);
        rv.setAdapter(mAdapter);
}

    @OnClick (R.id.getWeatherButton) public void getWeather(View v) {
        try {
             cityName = URLEncoder.encode(etCityName.getText().toString(), "UTF-8");
             key = WeatherAPI.KEY;
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
        }

        Log.d(TAG, "OK");

        // get weather for today
        Call<WeatherDay> callToday = api.getToday(cityName, key);
        callToday.enqueue(new Callback<WeatherDay>() {
            @Override
            public void onResponse(Call<WeatherDay> call, Response<WeatherDay> response) {
                Log.e(TAG, "onResponse");
                WeatherDay data = response.body();
                Log.d(TAG,response.toString());

                if (response.isSuccessful()) {
                    tvTemp.setText(data.getCity() + " " + data.getTempWithDegree());
                    Glide.with(MainActivity.this).load(data.getIconUrl()).into(ivImage);
                }
            }

            @Override
            public void onFailure(Call<WeatherDay> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, t.toString());
            }
        });

        // get weather forecast
        Call<WeatherForecast> callForecast = api.getForecast(cityName, key);
        callForecast.enqueue(new Callback<WeatherForecast>() {
            @Override
            public void onResponse(Call<WeatherForecast> call, Response<WeatherForecast> response) {
                Log.e(TAG, "onResponse");
                WeatherForecast data = response.body();
                Log.d(TAG,response.toString());
                if (data!=null){
                    items.addAll(data.getItems());
                    rv.getAdapter().notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(), "Could not find weather", Toast.LENGTH_LONG);
                    Log.e(TAG, "null items object");
                }
            }

            @Override
            public void onFailure(Call<WeatherForecast> call, Throwable t) {
                Log.e(TAG, "onFailure");
                Log.e(TAG, t.toString());
            }
        });

    }

}





