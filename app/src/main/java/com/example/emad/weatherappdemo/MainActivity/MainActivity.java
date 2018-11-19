package com.example.emad.weatherappdemo.MainActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.emad.weatherappdemo.R;

import androidx.navigation.Navigation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setBackgroundDrawable(null);

    }



    @Override
    public boolean onSupportNavigateUp() {
        return Navigation.findNavController(this, R.id.nav_host_fragment).navigateUp();
    }

}
