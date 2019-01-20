package com.example.yara.weatherapp;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.yara.weatherapp.Adapters.DaysAdapter;
import com.example.yara.weatherapp.Adapters.SavedDataAdapter;
import com.example.yara.weatherapp.DataBase.WeatherEntry;
import com.example.yara.weatherapp.Model.Weather;
import com.example.yara.weatherapp.ViewModel.DBViewModel;
import com.example.yara.weatherapp.ViewModel.WeatherViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class SavedDataFragment extends Fragment {

    RecyclerView recyclerView;
    SavedDataAdapter mAdapter;
    ImageButton delete;


    public SavedDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_saved_data, container, false);
        recyclerView=view.findViewById(R.id.rv_saved);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        delete=view.findViewById(R.id.btn_delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAllData();
            }
        });
        getData();
        return view;
    }

    private void deleteAllData() {
        DBViewModel viewModel= ViewModelProviders.of(this).get(DBViewModel.class);
        viewModel.delete();

    }

    private void getData() {
        DBViewModel viewModel= ViewModelProviders.of(this).get(DBViewModel.class);
        viewModel.getAllMovies().observe(this, new Observer<List<WeatherEntry>>() {
            @Override
            public void onChanged(@Nullable List<WeatherEntry> weatherEntries) {
                Log.d("SavedData"," from ViewModel  favorite list size  "+ weatherEntries.size() );
                mAdapter=new SavedDataAdapter(getContext(),weatherEntries);
                recyclerView.setAdapter(mAdapter);

            }
        });

    }

}
