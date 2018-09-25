package com.example.user.asignment2_1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
//import com.mapbox.mapboxsdk.MapboxAccountManager;
//import com.mapquest.mapping.maps.OnMapReadyCallback;
//import com.mapquest.mapping.maps.MapboxMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mapquest.mapping.maps.MapView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static android.support.constraint.Constraints.TAG;

/**
 * Created by USER on 25-Apr-18.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap map;
    View vMap;
    Context context;

    //TextView tv_test;
    //Button button;

    public MapFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {super.onCreate(savedInstanceState);}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        vMap = inflater.inflate(R.layout.fragment_map, container, false);
        context = getActivity();

        //button = (Button) vMap.findViewById(R.id.button);
        //tv_test = (TextView) vMap.findViewById(R.id.tv_map);

        SupportMapFragment mapFragment = (SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.map1);
        mapFragment.getMapAsync(this);
        return vMap;
    }


    //


    @Override
    public void onMapReady(GoogleMap googleMap) {
        MapsInitializer.initialize(getActivity().getApplicationContext());

        map = googleMap;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        LatLng pp = new LatLng(-37.879457, 145.048988);
        MarkerOptions option = new MarkerOptions();
        option.position(pp).title("967 Dandenong Road, Your Place");

        Marker add2 = map.addMarker(new MarkerOptions().position(new LatLng(-37.787194, 144.993585)));//John Smith
        Marker add1 = map.addMarker(option);//24 hours data
        Marker add3 = map.addMarker(new MarkerOptions().position(new LatLng(-37.876071, 145.047744)));//Alli Barba


        List<Marker> markers = new ArrayList<>();
        markers.add(add1);
        markers.add(add2);
        markers.add(add3);


        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Marker marker : markers) {
            builder.include(marker.getPosition());
        }
        LatLngBounds bounds = builder.build();

        int padding = 0; // offset from edges of the map in pixels
        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);

        map.animateCamera(cu);

        //CameraPosition cameraPosition = new CameraPosition.Builder()
               // .target(pp).zoom(18).build();
       // googleMap.animateCamera(CameraUpdateFactory
              //  .newCameraPosition(cameraPosition));

    }

}
