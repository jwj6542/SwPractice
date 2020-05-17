package com.example.locationtracking;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.overlay.LocationOverlay;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.PathOverlay;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements OnMapReadyCallback {

    private NaverMap mMap;
    LatLng prev_LOC = null;
    LatLng curr_LOC;
    Marker mk = new Marker();

    LocationManager locationManager;
    LocationListener locationListener;

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
        mMap = naverMap;
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                updateMap(location);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                alerStatus(provider);
            }

            @Override
            public void onProviderEnabled(String provider) {
                alertProvider(provider);
            }

            @Override
            public void onProviderDisabled(String provider) {
                checkProvider(provider);
            }
        };
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            return;
        }
        String locationProvider;
        locationProvider = LocationManager.GPS_PROVIDER;
        locationManager.requestLocationUpdates(locationProvider, 1, 1, locationListener);

        locationProvider = LocationManager.NETWORK_PROVIDER;
        locationManager.requestLocationUpdates(locationProvider, 1, 1, locationListener);
    }

    public void checkProvider(String provider) {
        Toast.makeText(this, provider + "에 의한 위치서비스가 꺼져 있습니다.    켜주세요", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
    }

    public void alertProvider(String provider) {
        Toast.makeText(this, provider + "서비스가 켜졌습니다!", Toast.LENGTH_SHORT).show();

    }

    public  void alerStatus(String provider){
        Toast.makeText(this,"위치서비스가"+ provider + "로 변경되었습니다!",Toast.LENGTH_SHORT).show();

    }
    public void updateMap(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        curr_LOC = new LatLng(latitude, longitude);
        if (prev_LOC == null) {
            CameraUpdate cameraUpdate = CameraUpdate.zoomTo(15);
            mMap.moveCamera(cameraUpdate);

            LocationOverlay locationOverlay = mMap.getLocationOverlay();
            locationOverlay.setVisible(true);
            locationOverlay.setPosition(curr_LOC);
            prev_LOC = curr_LOC;
        } else {
            CameraUpdate cameraUpdate1 = CameraUpdate.scrollTo(curr_LOC);
            mMap.moveCamera(cameraUpdate1);
            PathOverlay path = new PathOverlay();
            path.setCoords(Arrays.asList(
                    new LatLng(prev_LOC.latitude, prev_LOC.longitude),
                    new LatLng(curr_LOC.latitude, curr_LOC.longitude)

            ));
            path.setMap(mMap);
            path.setOutlineColor(Color.BLACK);
            path.setWidth(Color.YELLOW);
            path.setWidth(30);

            mk.setVisible(false);
            mk.setPosition(curr_LOC);
            mk.setMap(mMap);
            mk.setVisible(true);

            prev_LOC = curr_LOC;
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(locationManager != null)
            locationManager.removeUpdates(locationListener);
    }



}