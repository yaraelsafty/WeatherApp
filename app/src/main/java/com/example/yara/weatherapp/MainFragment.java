package com.example.yara.weatherapp;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yara.weatherapp.Adapters.DaysAdapter;
import com.example.yara.weatherapp.DataBase.WeatherEntry;
import com.example.yara.weatherapp.Model.Result;
import com.example.yara.weatherapp.ViewModel.DBViewModel;
import com.example.yara.weatherapp.ViewModel.WeatherViewModel;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {

    TextView temp,description,max_temp,min_temp,humidity,wind_speed,wind_deg;
    ImageView icon;
    RecyclerView recyclerView;
    DaysAdapter mAdapter;
    LinearLayout linearLayout,humidity_layout;
    ProgressBar progressBar,humidity_progressbar;
    private static final String ARG_City = "city";
    private String city_id;
    SharedPreferences preferences;

    public MainFragment() {
        // Required empty public constructor
    }
    public static MainFragment newInstance(String param1) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_City, param1);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            city_id = getArguments().getString(ARG_City);
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_main, container, false);
        temp=view.findViewById(R.id.tv_temp);
        max_temp=view.findViewById(R.id.tv_max_temp);
        min_temp=view.findViewById(R.id.tv_min_temp);
        description=view.findViewById(R.id.tv_description);
        wind_speed=view.findViewById(R.id.tv_wind_speed);
        wind_deg=view.findViewById(R.id.tv_wind_deg);
        icon=view.findViewById(R.id.iv_temp);
        recyclerView=view.findViewById(R.id.rv_days);
        linearLayout=view.findViewById(R.id.view);
        humidity_layout=view.findViewById(R.id.humidity_layout);
        progressBar=view.findViewById(R.id.progressbar);
        humidity_progressbar=view.findViewById(R.id.humidity_progressbar);
        humidity=view.findViewById(R.id.tv_humidity);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));




        getWeather();


        return view;



    }

    private void getWeather() {

        WeatherViewModel viewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);
        viewModel.id=city_id;
        viewModel.getWeather().observe(this, new Observer<Result>() {
            @Override
            public void onChanged(@Nullable Result result) {
                progressBar.setVisibility(View.GONE);
                linearLayout.setVisibility(View.VISIBLE);
                humidity_layout.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);

                Integer max=(int)(result.getList().get(0).getMain().getTempMax()-273.15);
                Integer min=(int)(result.getList().get(0).getMain().getTempMin()-273.15);
                Integer Temp=(int)(result.getList().get(0).getMain().getTemp()-273.15);

                max_temp.setText(String.valueOf((max)+"°C"));
                min_temp.setText(String.valueOf((min)+"°C"));
                temp.setText(String.valueOf((Temp)+"°C"));
                description.setText(result.getList().get(0).getWeather().get(0).getMain());
                Picasso.with(getContext())
                        .load("http://openweathermap.org/img/w/"+result.getList().get(0).getWeather().get(0).getIcon()+".png")
                        .into(icon);

                humidity_progressbar.setProgress(result.getList().get(0).getMain().getHumidity());
                humidity.setText(String.valueOf(result.getList().get(0).getMain().getHumidity())+"%");

                wind_speed.setText("Speed :"+String.valueOf(result.getList().get(0).getWind().getSpeed()));
                wind_deg.setText("Degree :"+String.valueOf(result.getList().get(0).getWind().getDeg()));


                mAdapter=new DaysAdapter(getContext(),result.getList());
                recyclerView.setAdapter(mAdapter);

                WeatherEntry weatherEntry=new WeatherEntry(result.getCity().getName(),
                        result.getList().get(0).getDtTxt(),result.getList().get(0).getWeather().get(0).getIcon(),
                        result.getList().get(0).getMain().getTempMin(),result.getList().get(0).getMain().getTempMax());

                saveData(weatherEntry);

                saveForWidget(Temp);
            }
        });
    }

    private void saveForWidget(Integer s) {
        preferences=getActivity().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("temp", s);
        editor.commit();

    }

    private void saveData(WeatherEntry Entry) {

        DBViewModel viewModel=ViewModelProviders.of(this).get(DBViewModel.class);
        viewModel.insert(Entry);
    }

}
