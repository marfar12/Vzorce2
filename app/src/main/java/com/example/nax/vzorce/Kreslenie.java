package com.example.nax.vzorce;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;


public class Kreslenie extends View {
    private Paint paint, paint2, paintLinearna, paintKvadraticka, paintGoniometricka;
    private float bodX, bodY;
    private float[] bodyY = new float[1450];
    private float[] bodyX = new float[1450];

    public Kreslenie(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setPaint();
    }

    public void lf(float bodX, float bodY){
        this.bodX = bodX;
        this.bodY = bodY;

        setPaint();

        paintLinearna = new Paint();
        paintKvadraticka = null;
        paintGoniometricka = null;

        paintLinearna.setColor(Color.RED);
        paintLinearna.setAntiAlias(true);
        paintLinearna.setStyle(Paint.Style.STROKE);
        paintLinearna.setStrokeJoin(Paint.Join.ROUND);
        paintLinearna.setStrokeWidth(4f);
    }

    public void kf(float[] bodyY, float[] bodyX){
        this.bodyX = bodyX;
        this.bodyY = bodyY;

        setPaint();

        paintKvadraticka = new Paint();
        paintLinearna = null;
        paintGoniometricka = null;

        paintKvadraticka.setColor(Color.RED);
        paintKvadraticka.setAntiAlias(true);
        paintKvadraticka.setStyle(Paint.Style.STROKE);
        paintKvadraticka.setStrokeJoin(Paint.Join.ROUND);
        paintKvadraticka.setStrokeWidth(4f);
    }

    public void gf(float[] bodyX, float[] bodyY){
        this.bodyX = bodyX;
        this.bodyY = bodyY;

        paint2 = new Paint();
        paint2.setAntiAlias(true);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setColor(Color.BLACK);
        paint2.setStrokeJoin(Paint.Join.ROUND);
        paint2.setStrokeWidth(4f);
        paint2.setTextSize(30);
        paint2.setTextAlign(Paint.Align.RIGHT);

        paintGoniometricka = new Paint();
        paint = null;
        paintLinearna = null;
        paintKvadraticka = null;

        paintGoniometricka.setColor(Color.RED);
        paintGoniometricka.setAntiAlias(true);
        paintGoniometricka.setStyle(Paint.Style.STROKE);
        paintGoniometricka.setStrokeJoin(Paint.Join.ROUND);
        paintGoniometricka.setStrokeWidth(4f);
    }

    public void setPaint(){
        paint = new Paint();

        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeWidth(4f);
        paint.setTextSize(30);
        paint.setTextAlign(Paint.Align.RIGHT);

        paintLinearna = null;
        paintKvadraticka = null;
        paintGoniometricka = null;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setWillNotDraw(false);

        if (paint != null) {
            //y os
            canvas.drawLine(getWidth() / 24 * 12, getHeight() / 24, getWidth() / 24 * 12, getHeight() / 24 * 23, paint);
            //x os
            canvas.drawLine(getWidth() / 24, getHeight() / 24 * 12, getWidth() / 24 * 23, getHeight() / 24 * 12, paint);

            canvas.drawText("0", getWidth() / 12 * 6 - 5, getHeight() / 12 * 6 + 30, paint);

            //dieliky na y osi
            for (int i = 1; i < 24; i++) {
                canvas.drawLine(getWidth() / 2 - 10, getHeight() / 24 * i, getWidth() / 2 + 10, getHeight() / 24 * i, paint);
            }

            //dieliky na x osi
            for (int i = 1; i < 24; i++) {
                canvas.drawLine(getWidth() / 24 * i, getHeight() / 24 * 12 - 10, getWidth() / 24 * i, getHeight() / 24 * 12 + 10, paint);
            }

            //cisla na hornej y osi
            for (int i = 1; i < 6; i++) {
                canvas.drawText(String.valueOf((6 - i) * 4), getWidth() / 12 * 6 - 20, getHeight() / 12 * i + 10, paint);
            }

            //cisla na dolnej y osi
            for (int i = 1; i < 6; i++) {
                canvas.drawText("-" + i * 4, getWidth() / 12 * 6 - 20, getHeight() / 12 * (i + 6), paint);
            }

            //cisla na lavej x osi
            for (int i = 1; i < 6; i++) {
                canvas.drawText("-" + ((6 - i) * 4), getWidth() / 12 * i + 15, getHeight() / 12 * 6 + 40, paint);
            }

            //cisla na pravej x osi
            for (int i = 1; i < 6; i++) {
                canvas.drawText(String.valueOf(i * 4), getWidth() / 12 * (i + 6) + 15, getHeight() / 12 * 6 + 40, paint);
            }

            if (paintLinearna != null) {
                //kreslenie linearnej funkcie
                canvas.drawLine(getWidth() / 24, getHeight() / 24 * bodY, getWidth() / 24 * 23, getHeight() / 24 * bodX, paintLinearna);
            }

            if (paintKvadraticka != null) {
                //kreslenie kvadratickej funkcie
                if (bodyY.length > 1) {
                    for (int i = 0; i < 60; i++) {
                        canvas.drawLine(getWidth() / 24 * bodyX[i], getHeight() / 24 * bodyY[i], getWidth() / 24 * bodyX[i + 1], getHeight() / 24 * bodyY[i + 1], paintKvadraticka);
                    }
                }
            }
        }
        else {
            //y os
            canvas.drawLine(getWidth() / 24 * 12, getHeight() / 24, getWidth() / 24 * 12, getHeight() / 24 * 23, paint2);
            //x os
            canvas.drawLine(getWidth() / 24, getHeight() / 24 * 12, getWidth() / 24 * 23, getHeight() / 24 * 12, paint2);
            //bod 0
            canvas.drawText("0", getWidth() / 24 * 12 - 5, getHeight() / 24 * 12 + 30, paint2);

            //dieliky na y osi
            for (float i = 1; i < 24; i++) {
                canvas.drawLine(getWidth() / 2 - 10, getHeight() / 24 * i, getWidth() / 2 + 10, getHeight() / 24 * i, paint2);
            }

            //dieliky na x osi
            for (float i = 1; i < 24; i++) {
                canvas.drawLine(getWidth() / 24 * i, getHeight() / 24 * 12 - 10, getWidth() / 24 * i, getHeight() / 24 * 12 + 10, paint2);
            }

            //cisla na hornej y osi
            for (int i = 1; i < 6; i++) {
                canvas.drawText(String.valueOf((6 - i) * 2), getWidth() / 12 * 6 - 20, getHeight() / 12 * i + 10, paint2);
            }

            //cisla na dolnej y osi
            for (int i = 1; i < 6; i++) {
                canvas.drawText("-" + i * 2, getWidth() / 12 * 6 - 20, getHeight() / 12 * (i + 6) + 10, paint2);
            }

            //cisla na lavej x osi
            for (int i = 1; i < 12; i++) {
                if (i%2 == 0) {
                    canvas.drawText("-" + (12 - i), getWidth() / 24 * i + 15, getHeight() / 24 * 12 + 40, paint2);
                }
            }

            //cisla na pravej x osi
            for (int i = 1; i < 12; i++) {
                if (i%2 == 0) {
                    canvas.drawText(String.valueOf(i), getWidth() / 24 * (i + 12) + 15, getHeight() / 24 * 12 + 40, paint2);
                }
            }

            if (paintGoniometricka != null){
                if (bodyY.length > 1){
                    for (int i = 0; i < 1440; i++) {
                        canvas.drawLine(getWidth() / 24 * bodyX[i], getHeight() / 24 * bodyY[i], getWidth() / 24 * bodyX[i + 1], getHeight() / 24 * bodyY[i + 1], paintGoniometricka);
                    }
                }
            }
        }
    }
}
