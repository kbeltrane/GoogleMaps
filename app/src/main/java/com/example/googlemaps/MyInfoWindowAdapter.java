package com.example.googlemaps;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class MyInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {
    private final View mWindow;

    public MyInfoWindowAdapter(Context context) {
        mWindow = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null);
    }

    @Override
    public View getInfoWindow(Marker marker) {
        TextView title = mWindow.findViewById(R.id.title3);
        TextView snippet = mWindow.findViewById(R.id.snippet4);
        title.setText(marker.getTitle());
        snippet.setText(marker.getSnippet());
        return mWindow;
    }


    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }


}

