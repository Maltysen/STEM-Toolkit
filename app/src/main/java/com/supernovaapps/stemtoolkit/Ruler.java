package com.supernovaapps.stemtoolkit;

import android.os.Build;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.ImageView;

public class Ruler extends AppCompatActivity {

    ImageView line;
    AbsoluteLayout space;
    DimensionalDisplayView disp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ruler);

        line = (ImageView) findViewById(R.id.line2);
        space = (AbsoluteLayout) findViewById(R.id.space);
        disp = (DimensionalDisplayView) findViewById(R.id.disp);

        space.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent ev) {
                final int action = MotionEventCompat.getActionMasked(ev);

                        line.setLayoutParams(new AbsoluteLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 7, 0, (int) ev.getY()));

                        DisplayMetrics metrics = new DisplayMetrics();

                        if (Build.VERSION.SDK_INT >= 17) {
                            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
                        }

                        disp.setNumber((float) 2.54 * Math.max(0, ev.getY()) / metrics.ydpi);

               return true;
            }
        });
    }
}
