package com.hexagonalkun.acgassistant_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class A0102 extends AppCompatActivity
{

    private TextView txt01,txt02;
    private ImageView imgV05;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0102);
        /*返回鍵*/
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        /*返回鍵*/
        setupViewcompoent();
    }

    private void setupViewcompoent()
    {
        Intent intent = getIntent();
        String a0102_txt01 = intent.getStringExtra("a0102_txt01");
        String a0102_txt02 = intent.getStringExtra("a0102_txt02");
        String photo = intent.getStringExtra("a0102_img");

        int imageRes = getResources().getIdentifier(photo,"drawable","com.hexagonalkun.acgassistant_v2");//0308**********************

        txt01=(TextView)findViewById(R.id.a0102_txt01);
        txt01.setText(a0102_txt01);
        txt02=(TextView)findViewById(R.id.a0102_txt02);
        txt02.setText(a0102_txt02);
        imgV05=(ImageView)findViewById(R.id.a0102_imgV05);
        imgV05.setImageResource(imageRes);



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
