
package com.example.admin.week4mondayhw;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.admin.week4mondayhw.model.openweatherapp.List;
import com.example.admin.week4mondayhw.model.openweatherapp.Response;
import com.example.admin.week4mondayhw.model.openweatherapp.Weather;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;




public class MainActivity extends AppCompatActivity implements WeatherFragment.OnFragmentInteractionListener {
    final java.util.List weatherFragment = new ArrayList<>();
    String WEATHER_KEY = "cfcf895c3e3cb655115ab6a68ea386d2";
MyPageAdapter myPageAdapter;
    private static final String TAG ="mainTAG" ;
    private OkHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        a();
    }

public void a() {


    final retrofit2.Call<Response> callRepos = RetrofitHelper.createCallWeather("atlanta",WEATHER_KEY);


    //create a thread to make the rest call on a separate thread
    new Thread(new Runnable() {
        @Override
        public void run() {

            try {
                final Response repoName = callRepos.execute().body();


                java.util.List<List> weatherData = repoName.getList();
                Log.d(TAG, "weatherData: "+repoName.getCity().getName()+" "
                +repoName.getCity().getCountry()+"  "
                +repoName.getList().get(0).getMain().getSeaLevel()+" ICON  "
                +repoName.getList().get(0).getWeather().get(0).getIcon()+ " SIZE "
                + weatherData.size()+"  DTTXT "
                +repoName.getList().get(0).getDtTxt().toString()+ "    DT:  "
                +repoName.getList().get(0).getDt());




                Log.d(TAG, "weatherData1: " + weatherData.get(0).getWeather().get(0).toString());



                for (int i = 0; i < weatherData.size(); i++) {
                    weatherFragment.add(WeatherFragment.newInstance(weatherData.get(i),repoName.getCity().getName().toString(),repoName.getCity().getCountry().toString()));
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        myPageAdapter = new MyPageAdapter(getSupportFragmentManager(),weatherFragment);
                        ViewPager pager = (ViewPager) findViewById(R.id.viewPager);
                        pager.setAdapter(myPageAdapter);
                    }
                });



            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }).start();


}

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}