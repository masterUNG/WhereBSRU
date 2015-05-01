package appewtc.masterung.wherebsru;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

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
}   // Main Class
