package com.example.chris.hexclock;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends Activity {
    private static final String DATE_FORMAT_NOW = "HH:mm:ss";
    private TextView lblTime;
    private MyTimerTask mytask;
    private Timer timer;
    View root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lblTime = (TextView) findViewById(R.id.time);
        View layout = findViewById(R.id.layout);
        root = layout.getRootView();

        mytask = new MyTimerTask();
        timer = new Timer();
        timer.schedule(mytask, 0,1000);
    }

    private void updateScreen() {
        String currentTime = getTime();
        String timeAsColour = formattedTime(currentTime);
        lblTime.setText(timeAsColour);
        setBackgroundColour(timeAsColour);
    }

    private String getTime() {
        Calendar cal = Calendar.getInstance();
        cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    private String formattedTime(String time) {
        time = "#" + time;
        time = time.replace(":", "");
        return time;
    }

    private void setBackgroundColour(String colour) {
        root.setBackgroundColor(Color.parseColor(colour));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                public void run() {
                    updateScreen();
                }
            });
        }
    }
}
