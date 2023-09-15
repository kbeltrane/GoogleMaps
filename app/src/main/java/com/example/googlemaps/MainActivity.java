package com.example.googlemaps;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMapClickListener {

    private GoogleMap Mapas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        Mapas = googleMap;
        initializeMap();
    }

    @Override
    public void onMapClick(@NonNull LatLng latLng) {

    }

    private void initializeMap() {
        LatLng ltUteq = new LatLng(-1.024267389863566, -79.46621960993701);
        CameraUpdate camUpdUteq = CameraUpdateFactory.newLatLngZoom(ltUteq, 15);
        Mapas.moveCamera(camUpdUteq);
        Mapas.getUiSettings().setZoomControlsEnabled(true);

        addMarker(new LatLng(-1.0242929847081381, -79.46703649905092), "CHINISSES FOOD EXPRESS", "Restaurante", R.mipmap.iconcomido2);
        addMarker(new LatLng(-1.023203577288594, -79.46571976799041), "SAN ANTONIO (Café-Restaurant)", "Restaurante", R.mipmap.iconcomido2);
        addMarker(new LatLng(-1.0365430348451965, -79.46830950888014), "La Tuka Moros y Asados", "Restaurante", R.mipmap.iconcomido2);
        addMarker(new LatLng(-1.0355775974808004, -79.47114192172303),"Cevichería El Picudo Blanco Internacional","Restaurante", R.mipmap.iconcomido2);
        Mapas.setInfoWindowAdapter(new MyInfoWindowAdapter(this));
    }

    private void addMarker(LatLng position, String title, String snippet, int iconResId) {
        MarkerOptions markerOptions = new MarkerOptions()
                .position(position)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(iconResId));
        Mapas.addMarker(markerOptions);
    }
}


