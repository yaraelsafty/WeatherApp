package com.example.yara.weatherapp.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.widget.RemoteViews;

import com.example.yara.weatherapp.MainActivity;
import com.example.yara.weatherapp.R;
import com.example.yara.weatherapp.ViewModel.WeatherViewModel;

/**
 * Implementation of App Widget functionality.
 */
public class WeatherAppWidget extends AppWidgetProvider {


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
       SharedPreferences  preferences=context.getSharedPreferences("MyPref", 0);

        CharSequence widgetText =String.valueOf(preferences.getInt("temp", -1)+"°C");

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.weather_app_widget);
        views.setTextViewText(R.id.tv_widget_temp, widgetText);

        Intent intent=new Intent(context,MainActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(context,0,intent,0);

        views.setOnClickPendingIntent(R.id.iv_widget_icon,pendingIntent);
        views.setOnClickPendingIntent(R.id.tv_widget_temp,pendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);


    }
    public static void updateAppWidgets(Context context, AppWidgetManager appWidgetManager,int[] appWidgetIds){

        for (int appWidgetId :appWidgetIds){
            updateAppWidget(context,appWidgetManager,appWidgetId);
            UpdateWidgetService.startActionWeather(context);
        }
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

