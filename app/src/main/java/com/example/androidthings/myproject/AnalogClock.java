package com.example.androidthings.myproject;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Date;

public class AnalogClock extends View {

    private int diameter = 0;

    private float hour;
    private float minute;
    private Paint paint;

    private final int   thickness;
    private final Paint fillPaint;

    public AnalogClock(Context context, AttributeSet attrs) {
        super(context, attrs);

        thickness = 2; //getResources().getDimensionPixelSize(R.dimen.clock_thickness);

        paint = new Paint();
        paint.setStrokeWidth(thickness);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);

        fillPaint = new Paint();
        fillPaint.setStrokeWidth(thickness);
        fillPaint.setStyle(Paint.Style.FILL);
        fillPaint.setColor(Color.WHITE);
        fillPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        diameter = w < h ? w : h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int radius = diameter / 2;

        canvas.drawCircle(radius, radius, radius - thickness, paint);

        canvas.save();
        canvas.rotate((hour + (minute / 60f)) / 12.0f * 360.0f, radius, radius);
        canvas.drawLine(radius, radius + (thickness / 2), radius, thickness * 3f, fillPaint);
        canvas.restore();

        canvas.save();
        canvas.rotate(minute / 60.0f * 360.0f, radius, radius);
        canvas.drawLine(radius, radius + (thickness / 2), radius, thickness * 2f, fillPaint);
        canvas.restore();
    }

    public void setClock(long unixTimestamp) {
        Date date = new Date(unixTimestamp);
        this.hour = date.getHours();
        this.minute = date.getMinutes();
        postInvalidate();
    }
}
