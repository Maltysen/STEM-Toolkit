package com.supernovaapps.stemtoolkit;

import android.content.Intent;
import android.hardware.SensorEvent;
import android.os.Bundle;
import android.view.View;

public class ProtractorMainActivity extends AbstractAngleMeasurer {

    public static final String cur_angle_indentifier="com.patelme.StudentsProtractorAngel.CUR_ANGLE";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.protractor_main);
    }

    public void onSensorChanged(SensorEvent event) {
        super.onSensorChanged(event);
    }

    public void done(View view) {
        float cur_angle=getAngle();
        Intent intent=new Intent(this, ProtractorMeasureAngle.class);
        intent.putExtra(cur_angle_indentifier, cur_angle);
        startActivity(intent);
    }

}