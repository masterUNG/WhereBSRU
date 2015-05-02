package appewtc.masterung.wherebsru;

import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    private int intMapType;
    private TextView txtLat, txtLng;
    private LocationManager objLocationManager;
    private Criteria objCriteria;
    private boolean bolGPS, bolNetwork;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Bind Widget
        bindWidget();

        //Open Service
        openService();

    }   //onCreate


    @Override
    protected void onStart() {
        super.onStart();

        bolGPS = objLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        if (!bolGPS) {
            bolNetwork = objLocationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if (!bolNetwork) {
                Intent objIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(objIntent);
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        setupAll();

    }

    private void setupAll() {

        objLocationManager.removeUpdates(objLocationListener);
        String strLat = "Unknow";
        String strLng = "Unknow";

        Location objNetworkLocation = requesUpdateFromProvider(LocationManager.NETWORK_PROVIDER, "Network not Connected");
        if (objNetworkLocation != null) {
            strLat = String.format("%.7f", objNetworkLocation.getLatitude());
            strLng = String.format("%.7f", objNetworkLocation.getLongitude());
        }

        Location objGPSLocation = requesUpdateFromProvider(LocationManager.GPS_PROVIDER, "GPS not Work");
        if (objGPSLocation != null) {
            strLat = String.format("%.7f", objGPSLocation.getLatitude());
            strLng = String.format("%.7f", objGPSLocation.getLongitude());
        }

        txtLat.setText(strLat);
        txtLng.setText(strLng);
    }

    @Override
    protected void onStop() {
        super.onStop();

        objLocationManager.removeUpdates(objLocationListener);

    }

    public Location requesUpdateFromProvider(final String strProvider, String strError) {

        Location objLocation = null;
        if (objLocationManager.isProviderEnabled(strProvider)) {

            objLocationManager.requestLocationUpdates(strProvider, 1000, 10, objLocationListener);
            objLocation = objLocationManager.getLastKnownLocation(strProvider);

        } else {
            Log.d("bsru", "Error = " + strError);
        }

        return objLocation;
    }



    public final LocationListener objLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            txtLat.setText(String.format("%.7f", location.getLatitude()));
            txtLng.setText(String.format("%.7f", location.getLongitude()));

        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };



    private void openService() {
        objLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        objCriteria = new Criteria();
        objCriteria.setAltitudeRequired(false);
        objCriteria.setBearingRequired(false);
    }

    private void bindWidget() {
        txtLat = (TextView) findViewById(R.id.txtLat);
        txtLng = (TextView) findViewById(R.id.txtLong);
    }

    public void clickBSRU(View view) {
        myIntent(13.732566, 100.489392);
    }

    private void myIntent(double douLat, double douLng) {

        //Intent to MapsActivity
        Intent objIntent = new Intent(MainActivity.this, MapsActivity.class);
        objIntent.putExtra("Lat", douLat);
        objIntent.putExtra("Lng", douLng);
        objIntent.putExtra("MapType", intMapType);
        startActivity(objIntent);
    }

    public void clickWhereAreYou(View view) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.itemNormal:
                intMapType = 1;
                break;
            case R.id.itemSatelite:
                intMapType = 2;
                break;
            case R.id.itemTerrain:
                intMapType = 3;
                break;
            case R.id.itemHybrid:
                intMapType = 4;
                break;
            default:
                intMapType = 1;
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}   // Main Class
