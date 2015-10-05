package com.masonstrong.mstrong_reflex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class displayStats extends AppCompatActivity {
    TextView results;
    SingleStats stats = SingleStats.getInstance();
    multiStats gStats = multiStats.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_stats);
        results = (TextView) this.findViewById(R.id.theResults);
        setText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_stats, menu);
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

    public String getStats(){
        String results = "Lowest time: \n(10 rounds): " + stats.min(10).toString()+"ms\n";
        results+="(100 rounds): "+stats.min(100).toString()+"ms\n";
        results+="(all rounds): "+stats.min(stats.getSize()).toString() + "ms\n\n";
        results+="Max time: \n(10 rounds): " + stats.max(10).toString()+"ms\n";
        results+="(100 rounds): "+stats.max(100).toString()+"ms\n";
        results+="(all rounds): "+stats.max(stats.getSize()).toString() + "ms\n\n";
        results+="Average time: \n(10 rounds): " + stats.average(10).toString()+"ms\n";
        results+="(100 rounds): "+stats.average(100).toString()+"ms\n";
        results+="(all rounds): "+stats.average(stats.getSize()).toString() + "ms\n\n";
        results+="Median time: \n(10 rounds): " + stats.median(10).toString()+"ms\n";
        results+="(100 rounds): "+stats.median(100).toString()+"ms\n";
        results+="(all rounds): "+stats.median(stats.getSize()).toString() + "ms\n\n";

        results+="2 player: \nPlayer 1 buzzes: " + gStats.getCount(0,0);
        results+= " Player 2 buzzes: " + gStats.getCount(0,1)+"\n";
        results+="3 player: \nPlayer 1 buzzes: " + gStats.getCount(1,0);
        results+= " Player 2 buzzes: " + gStats.getCount(1,1);
        results+= " Player 3 buzzes: " + gStats.getCount(1,2)+"\n";
        results+="4 player: \nPlayer 1 buzzes: " + gStats.getCount(2,0);
        results+= " Player 2 buzzes: " + gStats.getCount(2,1);
        results+= " Player 3 buzzes: " + gStats.getCount(2,2);
        results+= " Player 4 buzzes: " + gStats.getCount(2,3);

        return results;
    }
    public void setText(){
        results.setText(getStats());
    }

    public void setClearStats(View view){
        stats.clearStats();
        stats.storeValues();
        gStats.clearStats();
        gStats.storeValues();
        setText();
    }

    public void sendMail(View view){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_TEXT, getStats());
        try {
            startActivity(Intent.createChooser(intent, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(displayStats.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
