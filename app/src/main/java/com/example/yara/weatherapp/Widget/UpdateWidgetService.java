package com.example.yara.weatherapp.Widget;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by Yara on 20-Jan-19.
 */

public class UpdateWidgetService extends IntentService {

    public static final String ACTION_WEATHER =
            "com.example.yara.weatherapp.action.water_plants";

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
    }

    public static void startActionWeather(Context context) {
        Intent intent = new Intent(context, UpdateWidgetService.class);
        intent.setAction(ACTION_WEATHER);
        context.startService(intent);
    }
}
