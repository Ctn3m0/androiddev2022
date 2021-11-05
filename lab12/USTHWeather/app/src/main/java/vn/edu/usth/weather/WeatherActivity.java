package vn.edu.usth.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherActivity extends AppCompatActivity {
    String msg = "Device is ";
    private TabLayout mTabLayout;
    private ViewPager2 mViewPager;
    private ViewPagerAdapter mViewPagerAdapter;
    MediaPlayer player;
    private static final String FILE_NAME = "./res/raw/music.mp3";

    Bitmap bitmap;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        View v = findViewById(R.id.fragmentContainer);


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


        //lab 13
//        final Handler handler = new Handler(Looper.getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                // This method is executed in main thread
//                String content = msg.getData().getString("server_response");
//                Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
//            }
//        };
//        Thread t = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                // this method is run in a worker thread
//                try {
//                // wait for 5 seconds to simulate a long network access
//                    Thread.sleep(5000);
//                }
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                // Assume that we got our data from server
//                Bundle bundle = new Bundle();
//                bundle.putString("server_response", "some sample json here");
//                // notify main thread
//                Message msg = new Message();
//                msg.setData(bundle);
//                handler.sendMessage(msg);
//            }
//        });
//        t.start();

//        FragmentManager fm = getSupportFragmentManager();
//        Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
//
//        if(fragment == null){
//            fragment = new ForecastFragment();
//            fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
//        }
        Toast.makeText(this,"Hello",Toast.LENGTH_SHORT).show();
        AsyncTask<String, Integer, Message> task = new AsyncTask<String, Integer, Message>() {
            @Override
            protected void onPreExecute() {
                // do some preparation here, if needed
            }
            @Override
            protected Message doInBackground(String... params) {
                try {
                    // wait for 5 seconds to simulate a long network access
//                    Thread.sleep(5000);
//                  initialize URL
                    URL url = new URL("https://scontent.fhan5-4.fna.fbcdn.net/v/t1.6435-9/125857765_1725430770952001_1392290274241224086_n.png?_nc_cat=104&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=UcrQbMTIZTYAX_Fq994&_nc_ht=scontent.fhan5-4.fna&oh=2c27fa6fe14d067511b9d8abd1784722&oe=61A8F96F");
                    // Make a request to server
                    HttpURLConnection connection =
                            (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setDoInput(true);
                    // allow reading response code and response dataconnection.
                    connection.connect();
                    // Receive response
                    int response = connection.getResponseCode();
                    Log.i("USTHWeather", "The response is: " + response);
                    InputStream is = connection.getInputStream();
                    // Process image response
                    bitmap = BitmapFactory.decodeStream(is);
                    connection.disconnect();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                // Assume that we got our data from server
                Bundle bundle = new Bundle();
                bundle.putString("server_response", "some sample json here");
                // notify main thread
                Message msg = new Message();
                msg.setData(bundle);
                return msg;
            }
            @Override
            protected void onProgressUpdate(Integer... values) {
                // This method is called in the main thread, so it's possible
                // to update UI to reflect the worker thread progress here.
                // In a network access task, this should update a progress bar
                // to reflect how many percent of data has been retrieved
//                logo = (ImageView) findViewById(R.id.logo);
//                logo.setImageBitmap(bitmap);

            }
            @Override
            protected void onPostExecute(Message msg) {
                v.setBackground(new BitmapDrawable(bitmap));
                String content = msg.getData().getString("server_response");
                Toast.makeText(WeatherActivity.this, content, Toast.LENGTH_SHORT).show();
            }
        };
        task.execute("https://scontent.fhan5-4.fna.fbcdn.net/v/t1.6435-9/125857765_1725430770952001_1392290274241224086_n.png?_nc_cat=104&ccb=1-5&_nc_sid=09cbfe&_nc_ohc=UcrQbMTIZTYAX_Fq994&_nc_ht=scontent.fhan5-4.fna&oh=2c27fa6fe14d067511b9d8abd1784722&oe=61A8F96F");
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
//        stopPlayer();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_delete:
                Toast.makeText(WeatherActivity.this, "Refreshed the page", Toast.LENGTH_LONG).show();
                return true;
            case R.id.new_activity:
//                Toast.makeText(this, "Get to the new activity", Toast.LENGTH_LONG).show();
                changeActivity();
//                onStop();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void changeActivity(){
        Intent intent = new Intent(WeatherActivity.this, PreferActivity.class);
        startActivity(intent);
    }
}