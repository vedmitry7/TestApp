package com.vedmitryapps.testapp.view.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.vedmitryapps.testapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.vedmitryapps.testapp.Constants.KEY_ADDRESS;
import static com.vedmitryapps.testapp.Constants.KEY_DESCRIPTION;
import static com.vedmitryapps.testapp.Constants.KEY_IMAGE_URL;
import static com.vedmitryapps.testapp.Constants.KEY_LATITUDE;
import static com.vedmitryapps.testapp.Constants.KEY_LONGITUDE;
import static com.vedmitryapps.testapp.Constants.KEY_NAME;

public class InfoActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @BindView(R.id.textViewName)
    TextView textViewName;

    @BindView(R.id.textViewAddress)
    TextView textViewAddress;

    @BindView(R.id.textViewDescription)
    TextView textViewDescription;

    @BindView(R.id.imageView)
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        ButterKnife.bind(this);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        textViewName.setText(getIntent().getStringExtra(KEY_NAME));
        textViewAddress.setText(getIntent().getStringExtra(KEY_ADDRESS));
        textViewDescription.setText(getIntent().getStringExtra(KEY_DESCRIPTION));

        Glide.with(this)
                .load(getIntent().getStringExtra(KEY_IMAGE_URL)).fitCenter()
                .into(imageView);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        double latitude = Double.parseDouble(getIntent().getStringExtra(KEY_LATITUDE));
        double longitude = Double.parseDouble(getIntent().getStringExtra(KEY_LONGITUDE));

        LatLng latLng = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(latLng).title(getIntent().getStringExtra(KEY_NAME)));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng , 17.0f) );
    }
}
