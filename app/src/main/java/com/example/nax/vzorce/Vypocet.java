package com.example.nax.vzorce;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Vypocet extends Fragment {
    View view;
    Button btn;
    EditText value_a, value_b, value_c, value_d;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        btn = view.findViewById(R.id.btn);
        value_a = view.findViewById(R.id.value_a);
        value_b = view.findViewById(R.id.value_b);
        value_c = view.findViewById(R.id.value_c);

        init();

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "IDE", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return view = inflater.inflate(R.layout.vypocet_fragment, container, false);
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
    }

    public void kvadraticka(){
        value_a.setEnabled(true);
        value_b.setEnabled(true);
        value_c.setEnabled(true);
        value_d.setEnabled(false);
    }

    public void kubicka(){
        value_a.setEnabled(true);
        value_b.setEnabled(true);
        value_c.setEnabled(true);
        value_d.setEnabled(true);
    }
}
