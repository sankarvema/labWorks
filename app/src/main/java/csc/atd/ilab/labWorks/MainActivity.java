package csc.atd.ilab.labWorks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import csc.atd.ilab.labWorks.core.SpeechPlayer;


public class MainActivity extends Activity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ImageView image = (ImageView) findViewById(R.id.img_banner);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onClick (View view) {
        Toast.makeText(this, "Button 1 pressed",
                Toast.LENGTH_LONG).show();

        TextView banner = (TextView) findViewById(R.id.text_banner);
        banner.setText(R.string.tagline);

        SpeechPlayer.getInstance(getApplicationContext()).play("Welcome to CSC TechLabs");

        Intent intent = new Intent(this, PosterScanOcrActivity.class);
        startActivity(intent);


    }
}
