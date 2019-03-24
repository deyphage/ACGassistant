package com.hexagonalkun.acgassistant_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class A0502 extends AppCompatActivity
{

    private Intent it;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0502);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.a0501, menu);
        menu.findItem(R.id.nav_avatar).setVisible(false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_avatar:
                it=new Intent();
                it.setClass(A0502.this, A0502.class);
                startActivity(it);
                break;
            case R.id.Google_LogIn:
                it=new Intent();
                it.setClass(A0502.this, A0504.class);
                startActivity(it);
                break;
            case R.id.nav_settings:
                it=new Intent();
                it.setClass(A0502.this, A0503.class);
                startActivity(it);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
