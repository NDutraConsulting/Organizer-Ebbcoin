package com.ebbcoin.organizer.organizerebbcoin.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ebbcoin.organizer.organizerebbcoin.Activities.PlannerViewActivity;
import com.ebbcoin.organizer.organizerebbcoin.Fragments.MapViewFragment;
import com.ebbcoin.organizer.organizerebbcoin.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class NewEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, OnMapReadyCallback {
    MapView mapView;

    LocationManager locationManager;
    MyLocationListener locationListener;
    LatLng currentLocation;


    SupportMapFragment mapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_event);

        locationManager = (LocationManager) getSystemService(this.LOCATION_SERVICE);
        locationListener = new MyLocationListener();


        setupSpinner(R.id.reward_type_spinner, R.array.rewards);

        // Array of choices
        //String colors[] = {"Red","Blue","White","Yellow","Black", "Green","Purple","Orange","Grey"};
        String sizes[] = {"1", "2", "3", "5", "8", "11"};

        setupSpinner(R.id.options_type_spinner, sizes);

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private void setupSpinner(int viewId, int optionsArrayResource) {
        Spinner spinner = (Spinner) findViewById(viewId);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                optionsArrayResource, R.layout.drop_down_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    private void setupSpinner(int viewId, String[] options) {

        // Selection of the spinner
        Spinner spinner = (Spinner) findViewById(viewId);

        // Application of the Array to the Spinner
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.drop_down_item, options);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);

    }


    public void saveClick(View v) {

        //this.dispatchKeyEvent(new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_BACK));

        finish();
        Log.d("R.id.planner", "saveClick");


    }

    public void launchMap(View v) {

        Toast.makeText(this, "GPS is starting", Toast.LENGTH_SHORT).show();
        mapFragment.getMapAsync(this);


    }

    public void onClick(View v) {

        //Log.d("onclick called by-", v.toString());

        //synchronized (semaphore) {
        switch (v.getId()) {
            case R.id.planner_button:
                // do something
                Log.d("R.id.planner", "planner was clicked");

                Intent planner_view = new Intent(this, PlannerViewActivity.class);

                startActivity(planner_view);

                break;

        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        Log.d("item selected", "hj");
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Another interface callback
    }

    @Override
    public void onMapReady(GoogleMap map) {
       /* map.getUiSettings().setMyLocationButtonEnabled(false);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        map.setMyLocationEnabled(true);

        // Needs to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(this);

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(43.1, -87.9), 10);
        map.animateCamera(cameraUpdate);
        */

        Log.d("onMapReady", "Called OnMap Ready");


        // Assume thisActivity is the current activity
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                com.ebbcoin.organizer.organizerebbcoin.Manifest.permission.ACCESS_FINE_LOCATION);

        if (ContextCompat.checkSelfPermission(this,
                com.ebbcoin.organizer.organizerebbcoin.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{com.ebbcoin.organizer.organizerebbcoin.Manifest.permission.ACCESS_FINE_LOCATION}, 0);

            // MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "GPS was not set correctly", Toast.LENGTH_SHORT).show();
            return;
        }




        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 0, locationListener);


        Log.d("Latitude","Lat: "+locationListener.currentLongitude);


        if( locationListener.currentLongitude != 0.0 && locationListener.currentLatitude != 0.0) {

            currentLocation = new LatLng(locationListener.currentLatitude, locationListener.currentLongitude);
            map.clear();
            locationManager.removeUpdates(locationListener);

            map.addMarker(new MarkerOptions().position(currentLocation).title("Marker"));
            // Updates the location and zoom of the MapView
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(currentLocation, 16);
            map.animateCamera(cameraUpdate);
            locationManager.removeUpdates(locationListener);
        }



    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.

                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }


    /*---------- Listener class to get coordinates ------------- */
    private class MyLocationListener implements LocationListener {

        private static final String TAG = "Location Listener";

        public double currentLatitude, currentLongitude;
        public double getLatitude(){
            return currentLatitude;
        }

        @Override
        public void onLocationChanged(Location loc) {
            //editLocation.setText("");
            //pb.setVisibility(View.INVISIBLE);

            currentLatitude = loc.getLatitude();
            currentLongitude = loc.getLongitude();


            Toast.makeText(
                    getBaseContext(),
                    "Location changed: Lat: " + loc.getLatitude() + " Lng: "
                            + loc.getLongitude(), Toast.LENGTH_SHORT).show();
            String longitude = "Longitude: " + loc.getLongitude();
            Log.v(TAG, longitude);
            String latitude = "Latitude: " + loc.getLatitude();
            Log.v(TAG, latitude);

        /*------- To get city name from coordinates --------
            String cityName = null;
            Geocoder gcd = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = gcd.getFromLocation(loc.getLatitude(),
                        loc.getLongitude(), 1);
                if (addresses.size() > 0) {
                    System.out.println(addresses.get(0).getLocality());
                    cityName = addresses.get(0).getLocality();
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            String s = longitude + "\n" + latitude + "\n\nMy Current City is: "
                    + cityName;
            //editLocation.setText(s);
            */
        }

        @Override
        public void onProviderDisabled(String provider) {


        }

        @Override
        public void onProviderEnabled(String provider) {


        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {}
    }
}
