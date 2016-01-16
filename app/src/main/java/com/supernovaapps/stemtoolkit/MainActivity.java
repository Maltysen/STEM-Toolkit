package com.supernovaapps.stemtoolkit;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.logging.Level;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }

    public void startTool(View v) {
        Class<? extends Activity> new_activity;

        switch (v.getId()) {
            case (R.id.protractor):
                new_activity = ProtractorMainActivity.class;
                break;

            case (R.id.level):
                new_activity = BallLevel.class;
                break;

            case (R.id.environment):
                new_activity = Environment.class;
                break;

            case (R.id.constants):
                new_activity = Constants.class;
                break;

            case (R.id.ruler):
                new_activity = Ruler.class;
                break;

            case (R.id.equations):
                new_activity = EquationSolver.class;
                break;

            default:
                return;
        }

        startActivity(new Intent(this, new_activity));

    }
}
