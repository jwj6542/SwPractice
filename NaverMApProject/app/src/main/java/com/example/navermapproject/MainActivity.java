package com.example.navermapproject;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.InfoWindow;
import com.naver.maps.map.overlay.Marker;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapFragment mapFragment = (MapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            getSupportFragmentManager().beginTransaction().add(R.id.map, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {
        naverMap.setMapType(NaverMap.MapType.Basic);
        naverMap.setSymbolScale(1.5f);

        LatLng latlng1 = new LatLng(37.515497, 127.095459);
        LatLng latLng2 = new LatLng(37.518025, 127.101950);

        CameraUpdate cameraUpdate1 = CameraUpdate.scrollTo(latlng1);
        naverMap.moveCamera(cameraUpdate1);

        CameraUpdate cameraUpdate2 = CameraUpdate.zoomTo(15);
        naverMap.moveCamera(cameraUpdate2);

        Marker marker1 = new Marker();
        marker1.setPosition(latlng1);
        marker1.setMap(naverMap);

        marker1.setSubCaptionText("집");
        marker1.setSubCaptionColor(Color.BLACK);
        marker1.setSubCaptionHaloColor(Color.BLACK);
        marker1.setSubCaptionTextSize(10);

        InfoWindow infoWindow1 = new InfoWindow();
        infoWindow1.setAdapter(new InfoWindow.DefaultTextAdapter(this) {


            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "조우진 집";
            }
        });
        infoWindow1.open(marker1);

        Marker marker2 = new Marker();
        marker2.setPosition(latLng2);
        marker2.setMap(naverMap);

        marker2.setSubCaptionText("코딩");
        marker2.setSubCaptionColor(Color.BLACK);
        marker2.setSubCaptionHaloColor(Color.BLACK);
        marker2.setSubCaptionTextSize(10);

        InfoWindow infoWindow2 = new InfoWindow();
        infoWindow2.setAdapter(new InfoWindow.DefaultTextAdapter(this) {
            @NonNull
            @Override
            public CharSequence getText(@NonNull InfoWindow infoWindow) {
                return "코딩학원";
            }
        });
        infoWindow2.open(marker2);
    }
}