package com.example.googlemaps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;


import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationRequest;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;


public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    public GoogleMap mMap;
    public FusedLocationProviderClient mFusedLocationClient;
    public LocationCallback mLocationCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
                    fetchNearbyPlaces(currentLocation);
                }
                mFusedLocationClient.removeLocationUpdates(mLocationCallback); // Obtiene la ubicación una sola vez
            }
        };
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getLayoutInflater().inflate(R.layout.custom_info_window, null);

                TextView name = v.findViewById(R.id.place_name);
                TextView address = v.findViewById(R.id.place_address);
                ImageView logo = v.findViewById(R.id.logo);

                PlaceInfo placeInfo = (PlaceInfo) marker.getTag();

                name.setText(placeInfo.getName());
                address.setText(placeInfo.getAddress());
                logo.setImageResource(placeInfo.getLogoResourceId());

                return v;
            }
        });

        LocationRequest locationRequest = LocationRequest.CREATOR(new );
        locationRequest.setInterval(10000); // Por ejemplo, cada 10 segundos
        locationRequest.setFastestInterval(5000); // Intervalo más rápido: 5 segundos
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mFusedLocationClient.requestLocationUpdates(locationRequest, mLocationCallback, Looper.getMainLooper());
        } else {
            // Solicita el permiso de ubicación si no ha sido concedido
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }
    }

    private void fetchNearbyPlaces(LatLng currentLocation) {
        // Aquí, debes hacer la llamada a la API de Google Places para obtener lugares cercanos
        // y agregarlos como marcadores en el mapa.
    }
}

