package com.hexagonalkun.acgassistant_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Vector;

public class A0303 extends AppCompatActivity implements OnMapReadyCallback
{
    private ImageView imgV;
    private TextView txt01,txt02,txt03,txt07;
    RecyclerView recyclerView;
    Vector<YouTubeVideos> youtubeVideos = new Vector<YouTubeVideos>();
    private GoogleMap mMap;
    private String latlng;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0303);
        setupViewCompoent();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.a0303_map);
        mapFragment.getMapAsync(this);
    }

    private void setupViewCompoent()
    {
        Intent intent = getIntent();
        String a0303_txt01 = intent.getStringExtra("a0302_txt01");
        String a0303_txt02 = intent.getStringExtra("a0302_txt02");
        String a0303_txt03 = intent.getStringExtra("a0302_txt03");
        String a0303_txt07 = intent.getStringExtra("a0302_txt07");
        latlng = intent.getStringExtra("latlng");
        String id = intent.getStringExtra("a0302_txt04");
        String photo = intent.getStringExtra("a0302_imgView");

       int imageRes = getResources().getIdentifier(photo,"drawable","com.hexagonalkun.a03");

        imgV= (ImageView)findViewById(R.id.a0303_imgv01);
        imgV.setImageResource(imageRes);
        txt01 = (TextView)findViewById(R.id.a0303_txt01);
        txt01.setText(a0303_txt01);
        txt02 = (TextView)findViewById(R.id.a0303_txt02);
        txt02.setText(a0303_txt02);
        txt03 = (TextView)findViewById(R.id.a0303_txt03);
        txt03.setText(a0303_txt03);
        txt07 = (TextView)findViewById(R.id.a0303_txt07);
        txt07.setText(a0303_txt07);


////        YouTubeVideos
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager( new LinearLayoutManager(this));
//
//        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+id+"\" frameborder=\"0\" allowfullscreen></iframe>") );
//
//        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);
//
//        recyclerView.setAdapter(videoAdapter);
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;
        String[] eveLatLng = latlng.split(",");
        double dLat = Double.parseDouble(eveLatLng[0]);    // 南北緯
        double dLon = Double.parseDouble(eveLatLng[1]);    // 東西經

        // Add a marker in Sydney and move the camera
        LatLng eveLocation = new LatLng(dLat, dLon);
        mMap.addMarker(new MarkerOptions().position(eveLocation).title("活動地點"));
        //放大倍率
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eveLocation , 15 )  );  //後面放地圖放大倍率
    }
}
