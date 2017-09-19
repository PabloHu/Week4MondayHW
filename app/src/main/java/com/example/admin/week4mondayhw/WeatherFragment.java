package com.example.admin.week4mondayhw;


import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.admin.week4mondayhw.model.openweatherapp.List;


public class WeatherFragment extends Fragment {

    private static final String ARG_WEATHER_DESC = "weatherDesc";
    private static final String ARG_WEATHER_DT = "dateTime";
    private static final String ARG_WEATHER_CITY = "city";
    private static final String ARG_WEATHER_COUNTRY = "Country";
    TextView tvWeatherDesc, tvWeatherDT, tvCity,tvCountry;
    ImageView ivWeather;

    private OnFragmentInteractionListener mListener;
    private String weather_desc, weather_dt, weather_city, weather_country;

    public WeatherFragment() {

    }

    public static WeatherFragment newInstance(List list, String city, String country) {
        WeatherFragment fragment = new WeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_WEATHER_DESC, list.getWeather().get(0).getDescription());
        args.putString(ARG_WEATHER_DT, list.getDtTxt());
        args.putString(ARG_WEATHER_CITY, city);
        args.putString(ARG_WEATHER_COUNTRY, country);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weather_desc = getArguments().getString(ARG_WEATHER_DESC);
            weather_dt = getArguments().getString(ARG_WEATHER_DT);
            weather_city = getArguments().getString(ARG_WEATHER_CITY);
            weather_country = getArguments().getString(ARG_WEATHER_COUNTRY);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        tvWeatherDesc = view.findViewById(R.id.tvWeatherDesc);
        tvWeatherDT = view.findViewById(R.id.tvWeatherDT);
        tvCity = view.findViewById(R.id.tvCity);
        tvCountry = view.findViewById(R.id.tvCountry);
        ivWeather = view.findViewById(R.id.ivWeatherImage);

        String desc = weather_desc;
        if (desc != null) {
            tvWeatherDesc.setText(desc);
            tvWeatherDT.setText(weather_dt);
            tvCity.setText(weather_city);
            tvCountry.setText(weather_country);

            if (desc.compareTo("clear sky") == 0) {
                ivWeather.setImageDrawable(getResources().getDrawable(R.drawable.clearsky));
                // Glide.with(this).load(getResources().getDrawable(R.drawable.annehathaway)).into(ivWeather);
            } else if (desc.compareTo("few clouds") == 0) {
                ivWeather.setImageDrawable(getResources().getDrawable(R.drawable.fewclouds));
            } else if (desc.compareTo("scattered clouds") == 0 || desc.compareTo("broken clouds") == 0) {
                ivWeather.setImageDrawable(getResources().getDrawable(R.drawable.scatteredclouds));
            } else if (desc.compareTo("light rain") == 0){
                ivWeather.setImageDrawable(getResources().getDrawable(R.drawable.rain));

            } else if (desc.compareTo("moderate rain") == 0) {
                ivWeather.setImageDrawable(getResources().getDrawable(R.drawable.rain));
            } else if (desc.compareTo("thunderstorm") == 0) {
                ivWeather.setImageDrawable(getResources().getDrawable(R.drawable.thunderstorm));
            } else if (desc.compareTo("snow") == 0) {
                ivWeather.setImageDrawable(getResources().getDrawable(R.drawable.snow));
            } else if (desc.compareTo("mist") == 0) {
                ivWeather.setImageDrawable(getResources().getDrawable(R.drawable.mist));
            }

        }


        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weather, container, false);
    }


    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}