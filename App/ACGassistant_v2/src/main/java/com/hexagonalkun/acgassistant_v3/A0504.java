package com.hexagonalkun.acgassistant_v3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class A0504 extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "hexagonalkun=>";
    private static final int RC_SIGN_IN = 9001;

    private TextView mStatusTextView;
    private GoogleSignInClient mGoogleSignInClient;
    private Uri User_IMAGE;
    private CircleImgView img;

    private Bitmap PersonImg;
    private Intent it;

    //SQL相關參數
    private String insmyname;
    private  String insmygroup="";
    private String insmyaddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a0504);
        setupViewComponent();
    }

    private void setupViewComponent() {
        mStatusTextView = findViewById(R.id.status);
        // Button listeners
        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.disconnect_button).setOnClickListener(this);

        // START configure_signin
        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        //END configure_signin

        //START build_client
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        //END build_client

        // START customize_button
        // Set the dimensions of the sign-in button.
        SignInButton signInButton = findViewById(R.id.sign_in_button);
        signInButton.setSize(SignInButton.SIZE_STANDARD);
        signInButton.setColorScheme(SignInButton.COLOR_LIGHT);
        // END customize_button
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.sign_out_button:
                signOut();
                break;
            case R.id.disconnect_button:
                revokeAccess();
                break;
        }
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void signOut() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // START_EXCLUDE
                        updateUI(null);
                        // END_EXCLUDE
                    }
                });
    }
    private void revokeAccess() {
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // START_EXCLUDE
                        updateUI(null);
                        // END_EXCLUDE
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();

        // START on_start_sign_in
        // Check for existing Google Sign In account, if the user is already signed in
        // the GoogleSignInAccount will be non-null.
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        updateUI(account);
        // END on_start_sign_in
    }

    // START onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    // END onActivityResult

    // START handleSignInResult
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            updateUI(account);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.d(TAG, "signInResult:failed code=" + e.getStatusCode());
            updateUI(null);
        }
    }
    // END handleSignInResult

    private void updateUI(GoogleSignInAccount account) {
        if (account != null) {
            mStatusTextView.setText(getString(R.string.signed_in_fmt, account.getDisplayName())+
                    "\n Email:"+account.getEmail()+"\n Firstname:"+account.getGivenName()+
                    "\n Lastname:"+account.getFamilyName()+"\n ID:"+account.getId());
//            String g_DisplayName=account.getDisplayName(); //暱稱
//            String g_Email=account.getEmail();  //信箱
//            String g_GivenName=account.getGivenName(); //Firstname
//            String g_FamilyName=account.getFamilyName(); //Last name
//            String g_id=account.getId();

            //-------改變圖像--------------
            User_IMAGE = account.getPhotoUrl();
            if(User_IMAGE==null){
                return;
            }
            img = (CircleImgView) findViewById(R.id.google_icon);
            new AsyncTask<String,Void,Bitmap>() {
                @Override
                protected Bitmap doInBackground(String... params) {
                    String url = params[0];
                    return getBitmapFromURL(url);
                }
                @SuppressLint("WrongThread")
                @Override
                protected void onPostExecute(Bitmap result) {
                    img.setImageBitmap(result);
                    PersonImg=result;
                    super.onPostExecute(result);
                }
            }.execute(User_IMAGE.toString().trim());
            //-------------------------

            findViewById(R.id.sign_in_button).setVisibility(View.GONE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.VISIBLE);

            //轉跳主頁面
            Intent loginSuccess = new Intent();
            loginSuccess.setClass(A0504.this, Main.class);
            Bundle bundle=new Bundle();
            bundle.putParcelable("PersonImg",PersonImg);
            bundle.putBoolean("isLogIn",true);
            loginSuccess.putExtras(bundle);
            startActivity(loginSuccess);
        } else {
            mStatusTextView.setText(R.string.signed_out);

            findViewById(R.id.sign_in_button).setVisibility(View.VISIBLE);
            findViewById(R.id.sign_out_and_disconnect).setVisibility(View.GONE);
        }
    }
    //--------------------------------------------
    public static Bitmap getBitmapFromURL(String imageUrl) {
        try{
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            return bitmap;
        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.a0501, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.nav_avatar:
                it=new Intent();
                it.setClass(A0504.this, A0502.class);
                startActivity(it);
                break;

            case R.id.Google_LogIn:
                it=new Intent();
                it.setClass(A0504.this, A0504.class);
                startActivity(it);
                break;
            case R.id.nav_settings:
                it=new Intent();
                it.setClass(A0504.this, A0503.class);
                startActivity(it);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

