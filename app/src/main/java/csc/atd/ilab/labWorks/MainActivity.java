package csc.atd.ilab.labWorks;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

import java.util.Collection;

import csc.atd.ilab.labWorks.core.Params;
import csc.atd.ilab.labWorks.core.SpeechPlayer;

public class MainActivity extends Activity implements BeaconConsumer {

    private BeaconManager beaconManager = null;
    Intent posterIntent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        ImageView image = (ImageView) findViewById(R.id.img_banner);

        beaconManager = BeaconManager.getInstanceForApplication(this);
        //beaconController.verifyBeacon();
        beaconManager.bind(this);

        posterIntent = new Intent(this, PosterScanOcrActivity.class);

        EditText editText = (EditText) findViewById(R.id.rangingText);
        editText.setText("Hai there");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.unbind(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (beaconManager.isBound(this)) beaconManager.setBackgroundMode(false);
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
        initWelcomeFunc();
    }

    @Override
    public void onBeaconServiceConnect() {
        beaconManager.setRangeNotifier(new RangeNotifier() {
            @Override
            public void didRangeBeaconsInRegion(Collection<Beacon> beacons, Region region) {
                if (beacons.size() > 0) {
                    Beacon firstBeacon = beacons.iterator().next();

                    beaconDisplay(firstBeacon.toString(), Double.toString(firstBeacon.getDistance()));

                    if(firstBeacon.getDistance() < Params.Beacon_Proximity_Zone)
                        initWelcomeFunc();

                }
            }

        });

        try {
            beaconManager.startRangingBeaconsInRegion(new Region("myRangingUniqueId", null, null, null));
        } catch (RemoteException e) {   }
    }

    private void beaconDisplay(final String id, final String distance) {
        runOnUiThread(new Runnable() {
            public void run() {
                EditText editText = (EditText)  findViewById(R.id.rangingText);
                editText.setText(distance);
            }
        });
    }

    private void initWelcomeFunc()
    {
        runOnUiThread(new Runnable() {
            public void run() {
                SpeechPlayer.getInstance(getApplicationContext()).play("Welcome to CSC TechLabs");
                startActivity(posterIntent);
            }
        });
    }
}
