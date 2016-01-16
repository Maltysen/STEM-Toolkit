package com.supernovaapps.stemtoolkit;

import android.app.Activity;
import android.os.Build;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solver extends Activity {

    LinearLayout equation;
    EditText[] variable_disps;
    ArrayList<Float> variables;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_solver);

        Bundle extras = getIntent().getExtras();

        final int idx = extras.getInt("EXTRA_FORMULA_IDX");
        String[] formula = EquationSolver.formulas[idx];

        ((TextView) findViewById(R.id.formula_title)).setText(EquationSolver.names[idx] );
        equation = (LinearLayout) findViewById(R.id.formula);
        variable_disps = new EditText[formula.length/2];

        for(int i=0; i<formula.length-2; i+=2) {
            final EditText variable = new EditText(this);
            TextView constant = new TextView(this);

            constant.setText(formula[i]);
            if (Build.VERSION.SDK_INT < 23) {
                constant.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Large);
            } else {
                constant.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
            }
            variable.setHint(formula[i + 1]);
            variable.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);
            variable.setImeOptions(EditorInfo.IME_ACTION_NEXT);
            variable.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                    variables = new ArrayList<Float>();

                    for (int i = 0; i < variable_disps.length; i++) {
                        try {
                            variables.add(Float.parseFloat(variable_disps[i].getText().toString()));
                        } catch (Exception e) {
                            variables.add(null);
                        }
                    }

                    if (Collections.frequency(variables, null) == 1) {
                        float answer;

                        switch (idx) {
                            case (0):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) * variables.get(2);
                                        break;
                                    case (1):
                                        answer = variables.get(0) / variables.get(2);
                                        break;
                                    case (2):
                                        answer = variables.get(0) / variables.get(1);
                                        break;

                                    default:
                                        answer = variables.get(1) * variables.get(2);
                                        break;
                                }
                                break;

                            case (1):
                                float g = 9.81f;
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) * g * variables.get(2);
                                        break;

                                    case (1):
                                        answer = variables.get(0) / g / variables.get(2);
                                        break;

                                    case (2):
                                        answer = variables.get(0) / g / variables.get(1);
                                        break;

                                    default:
                                        answer = variables.get(1) * g * variables.get(2);
                                        break;
                                }
                                break;

                            case (2):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) * (float) Math.pow(variables.get(2), 2) / 2;
                                        break;

                                    case (1):
                                        answer = 2 * variables.get(0) / (float) Math.pow(variables.get(2), 2);
                                        break;

                                    case(2):
                                        answer = (float) Math.sqrt(2 * variables.get(0) / variables.get(1));
                                        break;

                                    default:
                                        answer = variables.get(1) * (float) Math.pow(variables.get(2), 2) / 2;
                                        break;
                                }
                                break;

                            case (3):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) * (float) Math.pow(variables.get(2), 2) / 2;
                                        break;

                                    case (1):
                                        answer = 2 * variables.get(0) / (float) Math.pow(variables.get(2), 2);
                                        break;

                                    case(2):
                                        answer = (float) Math.sqrt(2 * variables.get(0) / variables.get(1));
                                        break;

                                    default:
                                        answer = variables.get(1) * (float) Math.pow(variables.get(2), 2) / 2;
                                        break;
                                }
                                break;

                            case (4):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) * variables.get(2);
                                        break;
                                    case (1):
                                        answer = variables.get(0) / variables.get(2);
                                        break;
                                    case (2):
                                        answer = variables.get(0) / variables.get(1);
                                        break;

                                    default:
                                        answer = variables.get(1) * variables.get(2);
                                        break;
                                }
                                break;

                            case (5):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) * variables.get(2) * (float) Math.cos(Math.toRadians(variables.get(3)));
                                        break;
                                    case (1):
                                        answer = variables.get(0) / variables.get(2) / (float) Math.cos(Math.toRadians(variables.get(3)));
                                        break;
                                    case (2):
                                        answer = variables.get(0) / variables.get(1) / (float) Math.cos(Math.toRadians(variables.get(3)));
                                        break;
                                    case (3):
                                        answer = (float) Math.toDegrees(Math.acos(variables.get(0) / variables.get(1) / variables.get(2)));
                                        break;
                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            case (6):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) / variables.get(2);
                                        break;
                                    case (1):
                                        answer = variables.get(0) * variables.get(2);
                                        break;
                                    case (2):
                                        answer = variables.get(1) / variables.get(0);
                                        break;
                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            case (7):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) / variables.get(2);
                                        break;
                                    case (1):
                                        answer = variables.get(0) * variables.get(2);
                                        break;
                                    case (2):
                                        answer = variables.get(1) / variables.get(0);
                                        break;
                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            case (8):
                                float G = -6.674e-11f;

                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = G * variables.get(1) * variables.get(2) / variables.get(3) / variables.get(3);
                                        break;
                                    case (1):
                                        answer = variables.get(0) / G / variables.get(2) * variables.get(3) * variables.get(3);
                                        break;
                                    case (2):
                                        answer = variables.get(0) / G / variables.get(1) * variables.get(3) * variables.get(3);
                                        break;
                                    case (3):
                                        answer = (float) Math.sqrt(G * variables.get(1) * variables.get(2) / variables.get(0));
                                        break;
                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            case (9):
                                float k = 8.987551e9f;

                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = k * variables.get(1) * variables.get(2) / variables.get(3) / variables.get(3);
                                        break;
                                    case (1):
                                        answer = variables.get(0) / k / variables.get(2) * variables.get(3) * variables.get(3);
                                        break;
                                    case (2):
                                        answer = variables.get(0) / k / variables.get(1) * variables.get(3) * variables.get(3);
                                        break;
                                    case (3):
                                        answer = (float) Math.sqrt(k * variables.get(1) * variables.get(2) / variables.get(0));
                                        break;
                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            case (10):
                                float R = 8.314f;
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(2) * R * variables.get(3) / variables.get(1);
                                        break;

                                    case (1):
                                        answer = variables.get(2) * R * variables.get(3) / variables.get(0);
                                        break;

                                    case (2):
                                        answer = variables.get(0) * variables.get(1) / R / variables.get(3);
                                        break;

                                    case (3):
                                        answer = variables.get(0) * variables.get(1) / R / variables.get(2);
                                        break;

                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            case (11):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) / variables.get(2);
                                        break;
                                    case (1):
                                        answer = variables.get(0) * variables.get(2);
                                        break;
                                    case (2):
                                        answer = variables.get(1) / variables.get(0);
                                        break;
                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            case (12):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) / variables.get(2);
                                        break;
                                    case (1):
                                        answer = variables.get(0) * variables.get(2);
                                        break;
                                    case (2):
                                        answer = variables.get(1) / variables.get(0);
                                        break;
                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            case (13):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = variables.get(1) * (float) Math.pow(variables.get(2), 2);
                                        break;

                                    case (1):
                                        answer = variables.get(0) / (float) Math.pow(variables.get(2), 2);
                                        break;

                                    case(2):
                                        answer = (float) Math.sqrt(variables.get(0) / variables.get(1));
                                        break;

                                    default:
                                        answer = variables.get(1) * (float) Math.pow(variables.get(2), 2);
                                        break;
                                }
                                break;

                            case (14):
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = 1.0f / variables.get(1);
                                        break;

                                    case (1):
                                        answer = 1.0f / variables.get(0);
                                        break;

                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            case (15):
                                float c = 299792458;
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = c / variables.get(1);
                                        break;
                                    case (1):
                                        answer = c / variables.get(0);
                                        break;
                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            case (16):
                                float d = 299792458;
                                switch (variables.indexOf(null)) {
                                    case (0):
                                        answer = d / variables.get(1);
                                        break;
                                    case (1):
                                        answer = d / variables.get(0);
                                        break;
                                    default:
                                        answer = 0;
                                        break;
                                }
                                break;

                            //end
                            default:
                                answer = 0;
                                break;

                        }

                        ((DimensionalDisplayView) findViewById(R.id.result)).setNumber(answer);
                        variable_disps[variables.indexOf(null)].setFocusable(false);
                    } else {
                        for (EditText disp : variable_disps) {
                            disp.setFocusableInTouchMode(true);
                        }
                    }

                }
            });

            equation.addView(constant);
            equation.addView(variable);

            variable_disps[i/2] = variable;
        }

        TextView constant = new TextView(this);

        constant.setText(formula[formula.length - 1]);
        if (Build.VERSION.SDK_INT < 23) {
            constant.setTextAppearance(this, android.R.style.TextAppearance_DeviceDefault_Large);
        } else {
            constant.setTextAppearance(android.R.style.TextAppearance_DeviceDefault_Large);
        }

        equation.addView(constant);
    }
}