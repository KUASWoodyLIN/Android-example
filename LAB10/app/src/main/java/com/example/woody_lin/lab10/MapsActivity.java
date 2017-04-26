package com.example.woody_lin.lab10;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    int Maptype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Maptype = getIntent().getExtras().getInt("Type");

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED) &&
            (ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)){
            Toast.makeText(MapsActivity.this,"取得位置權限",Toast.LENGTH_SHORT).show();
            mMap.setMyLocationEnabled(true);
        } else {
            Toast.makeText(MapsActivity.this,"無法取得位置權限",Toast.LENGTH_SHORT).show();
        }

        mMap.setMapType(Maptype);

        LatLng taipei101 = new LatLng(25.033611 , 121.565000); //台北101
        LatLng taipeitrain = new LatLng(25.047924,121.517081);  //台北火車站
        mMap.addMarker(new MarkerOptions().position(taipei101).title("台北101").draggable(true));
        mMap.addMarker(new MarkerOptions().position(taipeitrain).title("台北火車站").draggable(true));

        PolylineOptions polygonOptions = new PolylineOptions();
        polygonOptions.add(new LatLng(25.033611, 121.565000));
        polygonOptions.add(new LatLng(25.032728, 121.565137));
        polygonOptions.add(new LatLng(25.033739, 121.527886));
        polygonOptions.add(new LatLng(25.038716, 121.517758));
        polygonOptions.add(new LatLng(25.045656, 121.519636));
        polygonOptions.add(new LatLng(25.046200, 121.517533));
        polygonOptions.color(Color.BLUE);
        Polyline polyline = googleMap.addPolyline(polygonOptions);
        polyline.setWidth(10);

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(25.033739,121.527886),13) );
    }
}
