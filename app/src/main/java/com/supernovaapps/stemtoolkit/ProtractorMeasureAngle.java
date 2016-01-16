package com.supernovaapps.stemtoolkit;

import android.content.Intent;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ProtractorMeasureAngle extends AbstractAngleMeasurer {

    private float cur_angle;
    private static final int ANGLES_LEGNTH=40;
    private float[] angles=new float[ANGLES_LEGNTH];

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.second_line);

        Intent intent=getIntent();

        cur_angle=intent.getFloatExtra(ProtractorMainActivity.cur_angle_indentifier, 0);

    }

    public void onSensorChanged(SensorEvent event) {
        super.onSensorChanged(event);

        for (int i=0; i<angles.length-1; i++) {
            angles[i]=angles[i+1];
        }

        angles[ANGLES_LEGNTH-1]=cur_angle-getAngle();

        //((TextView) findViewById(R.id.answer) ).setText( ( (int) (calcAverage(angles) ) ) + "°" );
        ((DimensionalDisplayView) findViewById(R.id.answer)).setNumber((int) (cur_angle - getAngle() + 360) % 360);
    }

    public void newAngle(View view) {
        onBackPressed();
    }

}