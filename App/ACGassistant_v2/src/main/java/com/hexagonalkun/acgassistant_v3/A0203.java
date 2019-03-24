package com.hexagonalkun.acgassistant_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

public class A0203 extends AppCompatActivity
{
    private ImageView imgV;
    private TextView txt01,txt02,txt03;
    RecyclerView recyclerView;
    Vector<YouTubeVideos> youtubeVideos = new Vector<YouTubeVideos>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0203);
        /*返回鍵------------------------------------------------------------------------------------*/
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        /*返回鍵-----------------------------------------------------------------------------------------*/
        setupViewCompoent();


    }

    private void setupViewCompoent()
    {
        Intent intent = getIntent();
        String a0203_txt01 = intent.getStringExtra("a0202_txt01");
        String a0203_txt02 = intent.getStringExtra("a0202_txt02");
        String a0203_txt03 = intent.getStringExtra("a0202_txt03");
        String id = intent.getStringExtra("a0202_txt04");
        String photo = intent.getStringExtra("a0202_imgView");

       int imageRes = getResources().getIdentifier(photo,"drawable","com.hexagonalkun.acgassistant_v2");//0308**********************

        imgV= (ImageView)findViewById(R.id.a0203_imgv01);
        imgV.setImageResource(imageRes);
        txt01 = (TextView)findViewById(R.id.a0203_txt01);
        txt01.setText(a0203_txt01);
        txt02 = (TextView)findViewById(R.id.a0203_txt02);
        txt02.setText(a0203_txt02);
        txt03 = (TextView)findViewById(R.id.a0203_txt03);
        txt03.setText(a0203_txt03);



//        YouTubeVideos
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        youtubeVideos.add( new YouTubeVideos("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/"+id+"\" frameborder=\"0\" allowfullscreen></iframe>") );

        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);

        recyclerView.setAdapter(videoAdapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    { //右上那個元件
        getMenuInflater().inflate(R.menu.a0101, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    { //右上那個元件
        switch (item.getItemId())
        {
            case R.id.action_reg:

                break;

            case R.id.action_settings:

                break;

            case android.R.id.home:/*返回鍵*/
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
