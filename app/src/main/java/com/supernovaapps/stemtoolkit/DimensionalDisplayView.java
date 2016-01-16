package com.supernovaapps.stemtoolkit;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.Math.floor;

public class DimensionalDisplayView extends LinearLayout {
    TextView disp;
    Button copy;
    float number;
    String unit;
    ClipboardManager clipboard;

    public DimensionalDisplayView(final Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(LinearLayout.HORIZONTAL);
        disp = new TextView(getContext());
        addView(disp);

        if (attrs != null) {
            TypedArray info = context.getTheme().obtainStyledAttributes(attrs, R.styleable.DimensionalDisplayView, 0, 0);
            unit = info.getString(R.styleable.DimensionalDisplayView_unit);

            setNumber(info.getFloat(R.styleable.DimensionalDisplayView_number, 0));
            disp.setTextSize(info.getDimension(R.styleable.DimensionalDisplayView_textSize, new TextView(context).getTextSize()));

            info.recycle();
        }

        clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);

        disp.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipData clip = ClipData.newPlainText(getNumString(), getNumString());
                clipboard.setPrimaryClip(clip);

                Toast.makeText(context, "Measurement copied to clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getNumString() {
        if (number > 1e7 || number < 1e-7) {
            return number + "";
        }

        if (number % 1 == 0) {
            return (number + "").substring((number+"").length()-2);
        }

        return number + "";

    }


    public void setNumber(float n) {
        number = n;

        setText(number + "");

    }

    public void setText(String s) {
        disp.setText(s);
    }
}
