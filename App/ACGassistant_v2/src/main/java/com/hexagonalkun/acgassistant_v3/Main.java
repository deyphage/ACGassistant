package com.hexagonalkun.acgassistant_v3;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener
{

    //-----------Fragment參數宣告--------------
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    //-----------Menu 切換頁面用----------------
    private Intent it;
    //----------------------------------------
    //-----------權限申請----------------------
    //所需要申請的權限數組
    private static final String[] permissionsArray = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.ACCESS_WIFI_STATE,
            Manifest.permission.INTERNET,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    private List<String> permissionsList = new ArrayList<String>();

    //申請權限後的返回碼
    private static final int REQUEST_CODE_ASK_PERMISSIONS = 1;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private ImageView GoogleIcon;
    private GoogleSignInClient mGoogleSignInClient;
    private Boolean isLogIn=false;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //確認大頭貼
        GoogleIcon=(ImageView)findViewById(R.id.imageView);
        checkIfGoogleIcon();
        //申請權限
        checkRequiredPermission(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //buttom navigation
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.bottomnavigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // 製作fragment管理
        // 參考資料https://blog.csdn.net/HuaKaiBuXiangLi/article/details/79201944
        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content,new Fragment_A01());
        transaction.commit();
    }

    private void checkIfGoogleIcon()
    {
        try{
            Bundle bundle=this.getIntent().getExtras();
            Bitmap PersonIcon = bundle.getParcelable("PersonImg");
            if (PersonIcon!=null)
            {
                GoogleIcon.setImageBitmap(PersonIcon);
            }
        }catch (Exception e){}

    }

    private void checkRequiredPermission(final Activity activity){
        for (String permission : permissionsArray) {
            if (ContextCompat.checkSelfPermission(activity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionsList.add(permission);
            }
        }
        if(permissionsList.size()!=0){
            ActivityCompat.requestPermissions(activity,permissionsList.toArray(new
                    String[permissionsList.size()]),REQUEST_CODE_ASK_PERMISSIONS);
        }
    }


    @Override
    public void onBackPressed()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        } else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        Bundle bundle=new Bundle();
        bundle=this.getIntent().getExtras();
        if (bundle!=null){
            isLogIn = bundle.getBoolean("isLogIn");
        }
        if (isLogIn)
        {
            getMenuInflater().inflate(R.menu.a0501_islogin, menu);
        }else {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.a0501, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId()) {
            case R.id.nav_avatar:
                it=new Intent();
                it.setClass(Main.this, A0502.class);
                startActivity(it);
                break;
            case R.id.Google_LogIn:
                it=new Intent();
                it.setClass(Main.this, A0504.class);
                startActivity(it);
                break;
            case R.id.nav_settings:
                it=new Intent();
                it.setClass(Main.this, A0503.class);
                startActivity(it);
                break;
            case R.id.nav_logout:
                //登出功能
//                mGoogleSignInClient.signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera)
        {
            // Handle the camera action
            showAlertDialog(R.layout.dialog_negative_layout);//施工中dialog--------------
        } else if (id == R.id.nav_gallery)
        {
            showAlertDialog(R.layout.dialog_negative_layout);
        } else if (id == R.id.nav_slideshow)
        {
            showAlertDialog(R.layout.dialog_negative_layout);
        } else if (id == R.id.nav_manage)
        {
            showAlertDialog(R.layout.dialog_negative_layout);
        } else if (id == R.id.nav_share)
        {
            showAlertDialog(R.layout.dialog_negative_layout);
        } else if (id == R.id.nav_send)
        {
            showAlertDialog(R.layout.dialog_negative_layout);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
//施工中dialog-----------------------------------------------------------------------------------------------------------------------------------------------------
    private void showAlertDialog(int layout) {
        dialogBuilder = new AlertDialog.Builder(Main.this);
        View layoutView = getLayoutInflater().inflate(layout, null);
        Button dialogButton = layoutView.findViewById(R.id.btnDialog);
        dialogBuilder.setView(layoutView);
        alertDialog = dialogBuilder.create();
        alertDialog.show();
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
    }
    //施工中dialog-------------------------------------------------------------------------------------------------------------------------------------------------------
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener()
    {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item)
        {
            fragmentManager = getSupportFragmentManager();
            transaction = fragmentManager.beginTransaction();
            switch (item.getItemId())
            {
                case R.id.navigation_news:
                    transaction.replace(R.id.content,new Fragment_A01());
                    transaction.commit();
                    return true;
                case R.id.navigation_animation:
                    transaction.replace(R.id.content,new Fragment_A02());
                    transaction.commit();
                    return true;
                case R.id.navigation_event:
                    transaction.replace(R.id.content,new Fragment_A03());
                    transaction.commit();
                    return true;
                case R.id.navigation_myActivity:
                    transaction.replace(R.id.content,new Fragment_A04());
                    transaction.commit();
                    return true;
            }
            return false;
        }
    };





}
