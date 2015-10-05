package com.masonstrong.mstrong_reflex;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class GameshowFour extends AppCompatActivity {
    multiStats gStats = multiStats.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameshow_four);
        gStats.setNumPlayers("2");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_gameshow_four, menu);
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

    public void p1hit4(View view){
        gStats.incrementCount(2,0);
        gStats.setLastPlayer("1");
        gStats.storeValues();
        showResults();
    }

    public void p2hit4(View view){
        gStats.incrementCount(2, 1);
        gStats.setLastPlayer("2");
        gStats.storeValues();
        showResults();
    }

    public void p3hit4(View view){
        gStats.incrementCount(2, 2);
        gStats.setLastPlayer("3");
        gStats.storeValues();
        showResults();
    }

    public void p4hit4(View view){
        gStats.incrementCount(2, 3);
        gStats.setLastPlayer("4");
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
