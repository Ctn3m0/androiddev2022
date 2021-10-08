package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WeatherActivity extends AppCompatActivity {
    String msg = "Device is ";
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    MediaPlayer player;
    private static final String FILE_NAME = "./res/raw/music.mp3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        mViewPager = findViewById(R.id.view_pager);
        mTabLayout = findViewById(R.id.tab_layout);

        mViewPagerAdapter = new ViewPagerAdapter(this);
        mViewPager.setAdapter(mViewPagerAdapter);

        new TabLayoutMediator(mTabLayout, mViewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Hanoi");
                    break;
                case 1:
                    tab.setText("France");
                    break;
                case 2:
                    tab.setText("Toulous");
                    break;
                case 3:
                    tab.setText("Chill");
                    break;
            }
        }).attach();

        Log.i(msg, "onCreate() event");

//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
//
//        if(fragment == null){
//            fragment = new ForecastFragment();
//            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
//        }
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
        stopPlayer();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i(msg, "onDestroy() event");
    }

    public void play(View v){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.music);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }
    public void pause(View v){
        if(player == null){
            player.pause();
        }
    }
    public void stop(View v){
        stopPlayer();
    }
    public void stopPlayer(){
        if(player == null){
            player.release();
            player = null;
            Toast.makeText(this,"Media player realease",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.nav_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        when (item.itemID){
//            R.id.nav_delete -> Toast.makeText(this, "Refresh the page", Toast.LENGTH_LONG).show();
//            R.id.new_activity -> Toast.makeText(this, "Get to the new activity", Toast.LENGTH_LONG).show();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}