package com.mischaboldy.mischa.rehapp;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ProgressBar;

/**
 * Created by mischa on 20/08/16.
 */
public class TextProgressBar extends ProgressBar{
    private Paint textPaint;

    public TextProgressBar(Context context) {
        super(context);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
    }

    public TextProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        setMax(30);
        setProgress(12);
        setSecondaryProgress(20);
    }

}

