package com.example.nax.vzorce;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;

public class Vzorce extends Fragment {
    OnRadioButtonSelected mCallback;

    public interface OnRadioButtonSelected{
        void selectedRadioButton(int id);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
           mCallback = (OnRadioButtonSelected) context;
        } catch (ClassCastException e){
            throw new ClassCastException(getActivity().toString() + "implement OnRadioButtonSelected");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vzorce_fragment, container, false);

        final RadioButton vzorec1 = view.findViewById(R.id.vzorec1);
        final RadioButton vzorec2 = view.findViewById(R.id.vzorec2);
        final RadioButton vzorec3 = view.findViewById(R.id.vzorec3);

        vzorec1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mCallback.selectedRadioButton(1);
                }
            }
        });

        vzorec2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mCallback.selectedRadioButton(2);
                }
            }
        });

        vzorec3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mCallback.selectedRadioButton(3);
                }
            }
        });

        return view;
    }
}
