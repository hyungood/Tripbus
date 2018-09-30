package com.example.tripbus;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainFragment extends Fragment implements OnMapReadyCallback {
    public GoogleMap mMap;
    View rootView;
    MapView mapView;

    public MainFragment() {


        }

    public LatLng getPosition(String latitude, String longtitude){
       LatLng Po = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longtitude));
       return Po;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.content_main, container, false);
        mapView = (MapView) rootView.findViewById(R.id.mapview);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(this);
        return rootView;
    }


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
//        MapsInitializer.initialize(this.getActivity());

        // Updates the location and zoom of the MapView
//        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(35.141233, 126.925594), 14);
//
//        mMap.animateCamera(cameraUpdate);
//
//        mMap.addMarker(new MarkerOptions()
//                .position(new LatLng(35.141233, 126.925594))
//                .title("루프리코리아"));
//addMarker는 맵에 마커 표시해 주기 위한 메소드.

        double myCurrentLatitude = 35.141233;
        double myCurrentLongitude = 126.925594;
        LatLng CurrentLocation = new LatLng(myCurrentLatitude, myCurrentLongitude);

        CameraPosition myCurrentLocation = CameraPosition.builder()
                .target(new LatLng(myCurrentLatitude, myCurrentLongitude))
                .zoom(12)
                .bearing(0)
                .tilt(2)
                .build();

        mMap.addMarker(new MarkerOptions().position(CurrentLocation).title("tripbus"));
        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(myCurrentLocation));
        mMap.getUiSettings().setScrollGesturesEnabled(true);
    }

    public void mapMoveTo(String lat, String lng){


         LatLng latLngs =new LatLng(Double.parseDouble(lat),Double.parseDouble(lng));


        if(mMap != null){
//            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 10);
//            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12));
//            mMap.animateCamera(cameraUpdate);

            CameraPosition myCurrentLocation = CameraPosition.builder()
                    .target(latLngs)
                    .zoom(12)
                    .bearing(0)
                    .tilt(2)
                    .build();

//            CameraUpdate center =
//                    CameraUpdateFactory.newLatLng(latLng);
            CameraUpdate zoom = CameraUpdateFactory.zoomTo(15.0f);

            // 2. 마커 생성 (마커를 나타냄)
//            mMap.addMarker(makerOptions);
//                for (int idx = 0; idx < 6; idx++) {
//                    // 1. 마커 옵션 설정 (만드는 과정)
//                    MarkerOptions makerOptions = new MarkerOptions();
//                    makerOptions // LatLng에 대한 어레이를 만들어서 이용할 수도 있다.
//                            .position(latLngs[idx])
//                            .title("마커" + idx); // 타이틀.
//
//                    // 2. 마커 생성 (마커를 나타냄)
//                    mMap.addMarker(makerOptions);
//                }
            mMap.addMarker(new MarkerOptions().position(latLngs).title("tripbus"));
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(myCurrentLocation));
            mMap.animateCamera(zoom);
        }

    }

//    public void setParkingLocation(String latitude, String longtitude, String carNumber){
//
//        LatLng parkingLocation = new LatLng( Double.parseDouble(latitude), Double.parseDouble(longtitude));
//
//        markerOptions = new MarkerOptions();
//        markerOptions.position(parkingLocation);
//        markerOptions.title(carNumber + "의 위치");
//        googleMap.addMarker(markerOptions); //오류 위치
//    }
}