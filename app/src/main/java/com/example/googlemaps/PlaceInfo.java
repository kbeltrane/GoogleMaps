package com.example.googlemaps;

public class PlaceInfo {
    private String name;
    private String address;
    private int logoResourceId;  // Deberías tener logos en tu carpeta "res/drawable"

    public PlaceInfo(String name, String address, int logoResourceId) {
        this.name = name;
        this.address = address;
        this.logoResourceId = logoResourceId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getLogoResourceId() {
        return logoResourceId;
    }
}

