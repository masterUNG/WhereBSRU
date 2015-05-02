package appewtc.masterung.wherebsru;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private LatLng centerLatLng, greatMomumentLatLng,
                    btsWongLatLng, btsPonimitLat, bsruLatLng, userLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //Create Center Map
        createCenterMap();

        //Create LatLng
        createLatLng();



        setUpMapIfNeeded();
    }   // onCreate

    private void createLatLng() {

        greatMomumentLatLng = new LatLng(13.726111, 100.493100);
        btsWongLatLng = new LatLng(13.721046, 100.495310);
        btsPonimitLat = new LatLng(13.719232, 100.486040);
        bsruLatLng = new LatLng(13.732566, 100.489392);

    }

    private void selectMapType() {

        int intMyMapType = getIntent().getExtras().getInt("MapType");
        switch (intMyMapType) {
            case 1:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
            case 2:
                mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                break;
            case 3:
                mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                break;
            case 4:
                mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                break;
            default:
                mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                break;
        }

    }

    private void createCenterMap() {

        double douLat = getIntent().getExtras().getDouble("Lat");
        double douLng = getIntent().getExtras().getDouble("Lng");
        centerLatLng = new LatLng(douLat, douLng);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {

        //Create Map
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(centerLatLng, 15));

        //Select MapType
        selectMapType();

        //Create Maker
        createMaker();

        //Create Polyline
        createPolyline();

        //Create Polygon
        createPolygon();

        //Click on Map
        clickOnMap();

    }   // setUpMap

    private void clickOnMap() {
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions()
                        .position(latLng)
                        .title("Latitude = " + Double.toString(latLng.latitude))
                        .snippet("Longtitude = " + Double.toString(latLng.longitude) + "\n" + "Test"));
            }
        });
    }

    private void createPolygon() {
        PolygonOptions objPolygonOptions = new PolygonOptions();
        objPolygonOptions.add(btsWongLatLng)
                .add(greatMomumentLatLng)
                .add(bsruLatLng)
                .add(btsPonimitLat)
                .add(btsWongLatLng)
                .strokeWidth(5)
                .strokeColor(Color.MAGENTA)
                .fillColor(Color.argb(70, 142, 192, 67))
                .zIndex(15);
        mMap.addPolygon(objPolygonOptions);
    }

    private void createPolyline() {
        PolylineOptions objPolylineOptions = new PolylineOptions();
        objPolylineOptions.add(centerLatLng)
                .add(btsWongLatLng).add(greatMomumentLatLng)
                .add(bsruLatLng).width(5).color(Color.BLUE)
                .zIndex(16);
        mMap.addPolyline(objPolylineOptions);
    }

    private void createMaker() {

        mMap.addMarker(new MarkerOptions().position(bsruLatLng).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA)).title("ราชภัฎบ้านสมเด็จเจ้าพระยา").snippet("คือสถาบันการการศึกษา ของชาติ"));
        mMap.addMarker(new MarkerOptions().position(greatMomumentLatLng).title("อนุสาวรี พระเจ้าตากสิก มหาราช").snippet("วงเวียนใหญ่"));
        mMap.addMarker(new MarkerOptions().position(btsWongLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.build4)));
        mMap.addMarker(new MarkerOptions().position(btsPonimitLat));
        mMap.addMarker(new MarkerOptions().position(centerLatLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.friend)));

    }
}
