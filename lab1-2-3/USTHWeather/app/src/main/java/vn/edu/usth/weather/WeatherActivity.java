package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;

public class WeatherActivity extends AppCompatActivity {
    String msg = "Device is ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i(msg, "onCreate() event");

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);

        if(fragment == null){
            fragment = new ForecastFragment();
            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i(msg, "onStart() event");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i(msg, "onResume() event");
    }

    @Override
    protected  void onPause(){
        super.onPause();
        Log.i(msg, "onPause() event");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i(msg, "onStop() event");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(msg, "onDestroy() event");
    }


}