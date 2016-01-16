package com.supernovaapps.stemtoolkit;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;
import android.widget.ToggleButton;

public class BallLevel extends AppCompatActivity implements SensorEventListener{
    private SensorManager mSensorManager;
    private Sensor mSensorRot;
    private boolean measuring = false;
    float[] rMat = new float[9];
    float[] orientation = new float[3];
    float x = 0;
    float y = 0;
    float r = 0;
    float theta = 0;
    private ImageView liquid = null;
    private ImageView bubble;
    private boolean lit = false;
    private int orth = Configuration.ORIENTATION_LANDSCAPE;
    float[] mapped = new float[9];
    int axisX = 0;
    int axisY = 0;
    int oldOrientation = 42;
    int size = 200;
    int ball = 40;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensorRot = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);

        liquid = (ImageView) findViewById(R.id.liquid);
        bubble = (ImageView) findViewById(R.id.bubble);

        mSensorManager.registerListener(this,mSensorRot, SensorManager.SENSOR_DELAY_GAME);
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        measuring = accuracy >= SensorManager.SENSOR_STATUS_ACCURACY_MEDIUM;
    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR && measuring) {
            SensorManager.getRotationMatrixFromVector(rMat, event.values);

            int newOrientation = getWindowManager().getDefaultDisplay().getRotation();

            if (newOrientation != oldOrientation) {

                switch (newOrientation) {
                    case Surface.ROTATION_90:
                        axisX = SensorManager.AXIS_X;
                        axisY = SensorManager.AXIS_Y;
                        break;

                    case Surface.ROTATION_180:
                        axisX = SensorManager.AXIS_Y;
                        axisY = SensorManager.AXIS_MINUS_X;
                        break;

                    case Surface.ROTATION_270:
                        axisX = SensorManager.AXIS_MINUS_X;
                        axisY = SensorManager.AXIS_MINUS_Y;
                        break;

                    case Surface.ROTATION_0:
                        axisX = SensorManager.AXIS_MINUS_Y;
                        axisY = SensorManager.AXIS_X;
                        break;

                    default:
                        break;
                }

                oldOrientation = newOrientation;
            }

            SensorManager.remapCoordinateSystem(rMat, axisX, axisY, mapped);

            x = (float) Math.sin(SensorManager.getOrientation(mapped, orientation)[1]);
            y = (float) Math.sin(SensorManager.getOrientation(mapped, orientation)[2]);

            r = (float) Math.hypot(x, y);
            theta = (float) Math.atan(y / x);

            if (r < .1) {
                if (!lit) {
                    liquid.setImageResource(R.drawable.bubble_lit);
                    lit = true;
                }
            }

            else {
                if (lit) {
                    liquid.setImageResource(R.drawable.bubble_round);
                    lit = false;
                }
            }

            if (r > 1) {
                x = (float) Math.copySign(Math.cos(theta), x);
                y = (float) Math.copySign(Math.sin(theta), y);
            }

            bubble.setLayoutParams(new AbsoluteLayout.LayoutParams(ball, ball, (int) (x * -size + size - ball/2), (int) (y * -size + size - ball/2)));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

}
