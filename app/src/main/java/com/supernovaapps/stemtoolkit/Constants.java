package com.supernovaapps.stemtoolkit;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Constants extends AppCompatActivity {

    View line;
    TextView title;
    TextView label;
    LinearLayout con;
    DimensionalDisplayView number;

    String[] categories = new String[]{
            "Universal Constants",
            "Mathematical Constants",
            "Physics Constants",
            

    };

    String[][][] constants = new String[][][] {
            {
                    {"Speed of Light", "299792458", " m/s"},
                    {"Avogadro's Number", "6.0221e23", ""},
                    {"Gravitational Constant", "6.674e-11", " m³s²/kg"},
                    {"Coulomb's Constant", "8.987551e9", " m/F"},
                    {"Planck's Constant", "6.626e-34", "J/Hz"},
                    {"Boltzmann's Constant", "1.3806488e-23", "J/K"}
            },
            {
                    {"π", "3.14159265", ""},
                    {"e", "2.7182818", ""},
                    {"Phi", "1.618034", ""},
                    {"√2", "1.41421356", ""}
            },
            {
                    {"Acceleration of Gravity on Earth", "9.81", "m/s²"},
                    {"Speed of Sound in Air", "343", "m/s"},
                    {"Eementary Charge", "1.6e-19", "C"},
                    {"Ideal Gas Constant", "8.3144598", "J/mol K"}
            }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_constants);

        LinearLayout list = (LinearLayout) findViewById(R.id.constants_list);

        for (int i = 0;i<categories.length; i++) {
                title = new TextView(this);
                title.setText(categories[i]);

                if (Build.VERSION.SDK_INT < 23) {
                    title.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Large);
                } else {
                    title.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
                }

                list.addView(title);
                ((LinearLayout.LayoutParams) title.getLayoutParams()).setMargins(0, i == 0 ? 0 : 20, 0, 10);

                line = new View(this);
                line.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                list.addView(line);
                line.getLayoutParams().height = 5;
                ((LinearLayout.LayoutParams) line.getLayoutParams()).setMargins(0, 0, 0, 20);

            for (String[] constant : constants[i]) {
                con = new LinearLayout(this);
                con.setOrientation(LinearLayout.HORIZONTAL);

                label = new TextView(this);
                label.setText(constant[0] + ":   ");

                if (Build.VERSION.SDK_INT < 23) {
                    label.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                } else {
                    label.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Medium);
                }

                con.addView(label);
                ((LinearLayout.LayoutParams) label.getLayoutParams()).setMargins(0, 0, 0, 20);

                number = new DimensionalDisplayView(this, null);
                number.unit = constant[2];
                number.setNumber(Float.parseFloat(constant[1]));

                if (Build.VERSION.SDK_INT < 23) {
                    number.disp.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Medium);
                } else {
                    number.disp.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Medium);
                }

                con.addView(number);

                list.addView(con);
            }
        }
    }
}
