package com.supernovaapps.stemtoolkit;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class EquationSolver extends AppCompatActivity {

    ListView formula_disp;

    static String[] names = new String[] {
            "Newton's Second Law",
            "Gravitational Potential Energy",
            "Kinetic Energy",
            "Distance Equation",
            "Momentum Equation",
            "Work Equation",
            "Power Equation",
            "Intensity",
            "Universal Gravitation",
            "Coulomb's Law",
            "Ideal Gas Law",
            "Density",
            "Pressure",
            "Ohm's Law",
            "Frequency",
            "c = λv",
            "Absolute Index of Refraction",
    };

    public static String[][] formulas = new String[][] {
            {"", "F", "=", "m", "", "a", ""},
            {"", "PE", "=", "m", "g", "h", ""},
            {"", "KE", "=½", "m", "", "v", "²"},
            {"", "s", "=½", "a", "", "t", "²"},
            {"", "p", "=", "m", "", "v",""},
            {"", "W", "=", "F", "", "s", "cos", "θ", "°"},
            {"", "P", "=", "W", "/", "t", ""},
            {"", "I", "=", "⟨P⟩", "/", "A", ""},
            {"", "F", "=-G", "m₁", "", "m₂", "/", "r", "²"},
            {"", "F", "=k", "q₁", "", "q₂", "/", "r", "²"},
            {"", "P", "", "V", "=", "n", "R", "T", ""},
            {"", "ρ", "=", "m", "/", "V", ""},
            {"", "P", "=", "F", "/", "A", ""},
            {"", "P", "=", "R", "", "I", "²"},
            {"", "f", "=1/", "T", ""},
            {"c=", "λ", "", "v", ""},
            {"", "n", "=c/", "v", ""},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equation_solver);

        formula_disp = (ListView) findViewById(R.id.forumla_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,  android.R.layout.simple_list_item_1, android.R.id.text1, names);
        formula_disp.setAdapter(adapter);

        formula_disp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int idx, long id) {
                Intent i = new Intent(EquationSolver.this, Solver.class);
                i.putExtra("EXTRA_FORMULA_IDX", idx);
                startActivity(i);
            }
        });

    }
}
