package appewtc.masterung.wherebsru;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    private int intMapType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }   //onCreate

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
