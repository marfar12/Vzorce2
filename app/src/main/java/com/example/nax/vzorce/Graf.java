package com.example.nax.vzorce;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Graf extends Fragment {
    private EditText value_a, value_b, value_c, value_d;
    private int vyber = 0, a=0;
    private Kreslenie kreslenie;
    private float[] bodyX = new float[1450];
    private float[] bodyY = new float[1450];
    private float bodY, bodX;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        kreslenie = view.findViewById(R.id.kreslenie);

        Button btn = view.findViewById(R.id.btn);
        value_a = view.findViewById(R.id.value_a);
        value_b = view.findViewById(R.id.value_b);
        value_c = view.findViewById(R.id.value_c);
        value_d = view.findViewById(R.id.value_d);

        init();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (vyber){
                    case 1:
                        try {
                            bodX = Float.valueOf(value_a.getText().toString()) * 22 + Float.valueOf(value_b.getText().toString());
                            bodY = Float.valueOf(value_a.getText().toString()) * (-22) + Float.valueOf(value_b.getText().toString());
                            bodX = map(bodX, 22, -22, 1, 23);
                            bodY = map(bodY, 22, -22, 1, 23);
                            kreslenie.lf(bodX, bodY);
                            kreslenie.invalidate();
                        }catch (NumberFormatException e){
                            Toast.makeText(getContext(), "Zadaj hodnoty", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 2:
                        try {
                            if (Float.valueOf(value_a.getText().toString()) != 0) {
                                for (float i=-24; i<24; i=i+0.5f){
                                    bodY = (Float.valueOf(value_a.getText().toString()) * (i*i)) + (Float.valueOf(value_b.getText().toString()) * i) + Float.valueOf(value_c.getText().toString());
                                    bodY = map(bodY, 24, -24, 0, 24);
                                    bodX = map(i, -24, 24, 0, 24);
                                    bodyX[a] = bodX;
                                    bodyY[a] = bodY;
                                    a++;
                                }
                                a=0;
                                kreslenie.kf(bodyY, bodyX);
                                kreslenie.invalidate();
                            }else {Toast.makeText(getContext(), "A nesmie byť 0", Toast.LENGTH_SHORT).show();}
                        }catch (NumberFormatException e){
                            Toast.makeText(getContext(), "Zadaj hodnoty", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    case 3:
                        try {
                            if (Float.valueOf(value_a.getText().toString()) != 0 && Float.valueOf(value_b.getText().toString()) != 0){
                                for (float i=-720; i<=720; i++){
                                    bodY = Float.valueOf(value_a.getText().toString()) * ((float) Math.sin(Math.toRadians(Double.valueOf(value_b.getText().toString()) * (i + Double.valueOf(value_c.getText().toString()))))) + Float.valueOf(value_d.getText().toString());
                                    bodY = map(bodY, 12f, -12f, 0, 24);
                                    bodX = (float) (i*Math.PI/180);
                                    bodX = map(bodX, -12, 12, 0, 24);
                                    bodyX[a] = bodX;
                                    bodyY[a] = bodY;
                                    a++;
                                }
                                a=0;
                                kreslenie.gf(bodyX, bodyY);
                                kreslenie.invalidate();
                            }else {Toast.makeText(getContext(), "A a B nesmú byť 0", Toast.LENGTH_SHORT).show();}
                        }catch (NumberFormatException e){
                            Toast.makeText(getContext(), "Zadaj hodnoty", Toast.LENGTH_SHORT).show();
                        }
                        break;
                    default:
                        Toast.makeText(getContext(), "Vyber funkciu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.vypocet_fragment, container, false);
        return view;
    }

    public void init(){
        value_a.setEnabled(false);
        value_b.setEnabled(false);
        value_c.setEnabled(false);
        value_d.setEnabled(false);
    }

    public void linearna(){
        value_a.setEnabled(true);
        value_b.setEnabled(true);
        value_c.setEnabled(false);
        value_d.setEnabled(false);

        value_c.setFocusable(false);
        value_c.setText("");
        value_d.setFocusable(false);
        value_d.setText("");
        vyber = 1;
    }

    public void kvadraticka(){
        value_a.setEnabled(true);
        value_b.setEnabled(true);
        value_c.setEnabled(true);
        value_d.setEnabled(false);

        value_c.setFocusable(true);
        value_c.setFocusableInTouchMode(true);
        value_b.setNextFocusDownId(R.id.value_c);
        value_d.setFocusable(false);
        value_d.setText("");
        vyber = 2;
    }

    public void goniometricka(){
        value_a.setEnabled(true);
        value_b.setEnabled(true);
        value_c.setEnabled(true);
        value_d.setEnabled(true);

        value_c.setFocusable(true);
        value_c.setFocusableInTouchMode(true);
        value_b.setNextFocusDownId(R.id.value_c);
        value_d.setFocusable(true);
        value_d.setFocusableInTouchMode(true);
        value_c.setNextFocusDownId(R.id.value_d);
        vyber = 3;
    }

    public float map(float x, float in_min, float in_max, float out_min, float out_max){
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }
}
