package com.masonstrong.mstrong_reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GameshowTwo extends AppCompatActivity {
    multiStats gStats = multiStats.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshow_two);
        gStats.setNumPlayers("2");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gameshow_two, menu);
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

    public void p1hit(View view){
        gStats.incrementCount(0,0);
        gStats.setLastPlayer("1");
        gStats.storeValues();
        showResults();
    }

    public void p2hit(View view){
        gStats.incrementCount(0, 1);
        gStats.setLastPlayer("2");
        gStats.storeValues();
        showResults();
    }

    public void showResults(){
        AlertDialog.Builder build = new AlertDialog.Builder(this);
        build.setTitle("The Results!");
        String result_message = "The winner was Player " + gStats.getLastPlayer();
        build.setMessage(result_message);
        build.setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog results = build.create();
        results.show();
    }
}
