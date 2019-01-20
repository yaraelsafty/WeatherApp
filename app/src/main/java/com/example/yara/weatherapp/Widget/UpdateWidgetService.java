package com.example.yara.weatherapp.Widget;

import android.app.IntentService;
import android.app.Service;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.yara.weatherapp.ViewModel.WeatherViewModel;

/**
 * Created by Yara on 20-Jan-19.
 */

public class UpdateWidgetService extends IntentService {

    public static final String ACTION_WEATHER =
            "com.example.yara.weatherapp.action.water_plants";
  //  SharedPreferences preferences;

    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public UpdateWidgetService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_WEATHER.equals(action)) {
                handleActionWeather();
            }
        }

    }

    private void handleActionWeather() {

      //  preferences.getInt("key_name", -1);
    }

    public static void startActionWeather(Context context) {
        Intent intent = new Intent(context, UpdateWidgetService.class);
        intent.setAction(ACTION_WEATHER);
        context.startService(intent);
    }
}
