package com.masonstrong.mstrong_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GameshowPicker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshow_picker);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gameshow_picker, menu);
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

    //it probably would have been better to have one dynamic view that rendered a variable
    //number of buttons and manipulated their sizes in java rather than xml.
    //Time contraints prevented this.
    public void twoPlayer(View view)
    {
        Intent intent = new Intent(GameshowPicker.this, GameshowTwo.class);
        startActivity(intent);
    }

    public void threePlayer(View view)
    {
        Intent intent = new Intent(GameshowPicker.this, GameshowThree.class);
        startActivity(intent);
    }

    public void fourPlayer(View view)
    {
        Intent intent = new Intent(GameshowPicker.this, GameshowFour.class);
        startActivity(intent);
    }
}
