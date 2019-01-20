package com.example.yara.weatherapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yara.weatherapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by Yara on 18-Jan-19.
 */

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.DaysViewHolder> {

    Context context;
     List<com.example.yara.weatherapp.Model.List> mlist;

    public DaysAdapter(Context context, List<com.example.yara.weatherapp.Model.List> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public DaysAdapter.DaysViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context= parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.rv_days_row, parent, false);
        return new DaysViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DaysAdapter.DaysViewHolder holder, int position) {

        final com.example.yara.weatherapp.Model.List model =mlist.get(position);
        String currentString = model.getDtTxt();
        String[] separated = currentString.split(" ");
        holder.date.setText(separated[0].trim());
        Integer Max=(int)(model.getMain().getTempMax()-273.15);
        Integer Min=(int)(model.getMain().getTempMin()-273.15);
        holder.max_temp.setText(String.valueOf(Max)+"°C");
        holder.min_temp.setText(String.valueOf(Min)+"°C");
        Picasso.with(context)
                .load("http://openweathermap.org/img/w/"+model.getWeather().get(0).getIcon()+".png")
                .into(holder.day_temp);
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class DaysViewHolder extends RecyclerView.ViewHolder {
        TextView date, min_temp,max_temp;
        ImageView day_temp;
        public DaysViewHolder(View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.tv_day_date);
            min_temp=itemView.findViewById(R.id.tv_day_min_temp);
            max_temp=itemView.findViewById(R.id.tv_day_max_temp);
            day_temp=itemView.findViewById(R.id.iv_day_icon);
        }
    }
}
