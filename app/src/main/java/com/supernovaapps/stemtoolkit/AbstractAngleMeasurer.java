package com.supernovaapps.stemtoolkit;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class AbstractAngleMeasurer extends AppCompatActivity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mSensorRot;
    private float azimuth;
    float[] orientation = new float[3];
    float[] rMat = new float[9];
    private boolean measuring = false;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mSensorRot = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }

    protected void onResume() {
        super.onResume();

        mSensorManager.registerListener(this, mSensorRot, SensorManager.SENSOR_DELAY_GAME);
    }

    protected void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(this);
    }

    public float getAngle() {
        return azimuth;
    }

    public float[] getOr() {
        return orientation;
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        measuring = accuracy >= SensorManager.SENSOR_STATUS_ACCURACY_HIGH;
    }

    public void onSensorChanged( SensorEvent event ) {
        if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR && measuring) {
            // calculate th rotation matrix
            SensorManager.getRotationMatrixFromVector( rMat, event.values );

            // get the azimuth value (orientation[0]) in degree
            azimuth = (float) ( Math.toDegrees( SensorManager.getOrientation( rMat, orientation )[1] ) + 360 ) % 360;
            for (int i=0;i<3;i++) {
                orientation[i] = (float) ((Math.toDegrees(orientation[i]) + 360) % 360);
            }
        }
    }

    public static float calcAverage(float[] nums)
    {
        float total = (float) 0.0;

        float average = (float) 0.0;

        for (int i = 0; i < nums.length; i++){
            total += nums[i];

        }

        average = (total/nums.length);

        return average;
    }

}