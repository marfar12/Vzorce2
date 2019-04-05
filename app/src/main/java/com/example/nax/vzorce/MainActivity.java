package com.example.nax.vzorce;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements Vzorce.OnRadioButtonSelected {
    ViewPager viewPager;

    @Override
    public void selectedRadioButton(int id) {
        Graf graf = (Graf) getSupportFragmentManager().findFragmentById(R.id.viewPager);

        if(graf != null) {
            switch (id){
                case 1: graf.linearna();
                        Toast.makeText(getApplicationContext(), "Lineárna funkcia", Toast.LENGTH_SHORT).show();
                        break;
                case 2: graf.kvadraticka();
                        Toast.makeText(getApplicationContext(), "Kvadratická funkcia", Toast.LENGTH_SHORT).show();
                        break;
                case 3: graf.goniometricka();
                        Toast.makeText(getApplicationContext(), "Goniometrická funkcia", Toast.LENGTH_SHORT).show();
            }
        }
        viewPager.setCurrentItem(1, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewPager = findViewById(R.id.viewPager);
        FragmentPagerAdapter fragmentPagerAdapter = new ScreenSlideActivity(getSupportFragmentManager());
        viewPager.setAdapter(fragmentPagerAdapter);
    }
}
