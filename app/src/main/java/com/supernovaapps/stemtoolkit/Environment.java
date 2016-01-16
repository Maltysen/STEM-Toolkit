package com.supernovaapps.stemtoolkit;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

public class Environment extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    int[] sensorsTypes = new int[] {Sensor.TYPE_AMBIENT_TEMPERATURE, Sensor.TYPE_PRESSURE, Sensor.TYPE_RELATIVE_HUMIDITY, Sensor.TYPE_LIGHT};
    int[] sensorDispIds = new int[] {R.id.temp, R.id.pressure, R.id.humidity, R.id.light};
    HashMap<Sensor, DimensionalDisplayView> sensors = new HashMap<Sensor, DimensionalDisplayView>();
    Sensor sensor;
    DimensionalDisplayView display;

    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.environment);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        for (int i=0; i<sensorsTypes.length; i++) {
            sensor = mSensorManager.getDefaultSensor(sensorsTypes[i]);
            display = (DimensionalDisplayView) findViewById(sensorDispIds[i]);
            if (Build.VERSION.SDK_INT < 23) {
                display.disp.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Large);
            } else {
                display.disp.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
            }

            if (sensor != null) {
                sensors.put(sensor, display);
            }

            else {
                display.setText("Sensor not available");
            }
        }
    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        if(event.accuracy >= SensorManager.SENSOR_STATUS_ACCURACY_HIGH) {
            sensors.get(event.sensor).setNumber(event.values[0]);
        }

    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();

        for (Sensor s : sensors.keySet()) {
            mSensorManager.registerListener(this, s, SensorManager.SENSOR_DELAY_GAME);
        }
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();

        for (Sensor s : sensors.keySet()) {
            mSensorManager.unregisterListener(this, s);
        }
    }
}