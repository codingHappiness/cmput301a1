package com.masonstrong.mstrong_reflex;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class SinglePlayer extends AppCompatActivity {
    boolean clickable = false;
    long start_time = System.currentTimeMillis();
    TextView go_text;
    TextView timer_text;
    SingleStats stats = SingleStats.getInstance();
    Handler myHandler = new Handler();
    Runnable r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_player);
        go_text = (TextView) this.findViewById(R.id.goText);
        timer_text = (TextView) this.findViewById(R.id.timerText);
        startTimer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_single_player, menu);
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

    public void setClickable(boolean value)
    {
        this.clickable = value;
    }
    public void startTimer () {
        Random random = new Random();
        int delay = random.nextInt(1980) + 20;

        //http://stackoverflow.com/a/9166354
        r = new Runnable() {
            @Override
            public void run() {
                setClickable(true);
                go_text.setText("GO!");
            }};

        myHandler.postDelayed(r, delay);

        //fix timing for getTime
        start_time += delay;
    }

    public void getTime(View view)
    {
        if (this.clickable) {
            long delta = System.currentTimeMillis() - this.start_time;
            stats.addStat((double)delta);
            stats.storeValues();
            timer_text.setText(String.valueOf(delta)+" ms");
        }

        else
        {
            start_time = System.currentTimeMillis();
            go_text.setText("");
            //kill previously existing timer delay if present
            try {
                myHandler.removeCallbacks(r);
            }
            catch (Exception e) {
                System.out.println(e);
            }
            finally {
                startTimer();
            }
        }
    }


}
