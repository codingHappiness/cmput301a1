package com.masonstrong.mstrong_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class mstrong_reflex extends AppCompatActivity {
    //SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPref", 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mstrong_reflex);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        SingleStats stats = SingleStats.getInstance();
        multiStats gStats = multiStats.getInstance();
        stats.loadValues();
        gStats.loadValues();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_mstrong_reflex, menu);
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

    public void start_singleplayer(View view){
        Intent intent = new Intent(mstrong_reflex.this, SinglePlayer.class);
        startActivity(intent);
    }

    public void showStats(View view){
        Intent intent = new Intent(mstrong_reflex.this, displayStats.class);
        startActivity(intent);
    }

    public void multiPlayer(View view){
        Intent intent = new Intent(mstrong_reflex.this, GameshowPicker.class);
        startActivity(intent);
    }


}
