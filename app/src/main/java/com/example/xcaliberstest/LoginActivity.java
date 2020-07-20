package com.example.xcaliberstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Scope;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Locale;

public class LoginActivity extends AppCompatActivity implements  GoogleApiClient.OnConnectionFailedListener  {
    private final String TAG = LoginActivity.class.getName();
     //google login
    private GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;

    //facebook login
    String fname,lname,emailid,Gender,birthday,accessToken;
    CallbackManager callbackManager;
    //Shared Preferences Variables
    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Locale myLocale;
    String numbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        setContentView(R.layout.activity_login);
        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.example.xcaliberstest", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }




        //Facebook and google login
        ImageButton facebook=(ImageButton)findViewById(R.id.facebook);
        ImageButton google=(ImageButton)findViewById(R.id.google);

        sharedPreferences = getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();
                final ConnectivityManager connMgr = (ConnectivityManager)
                this.getSystemService(Context.CONNECTIVITY_SERVICE);

//facebook login
        callbackManager = CallbackManager.Factory.create();
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList( "email","public_profile" ));
                //LoginManager.getInstance().logInWithPublishPermissions(LoginActivity.this, Arrays.asList("publish_actions"));
                LoginManager.getInstance().registerCallback(callbackManager,
                        new FacebookCallback<LoginResult>()
                        {
                            @Override
                            public void onSuccess(LoginResult loginResult)
                            {
                                //Toast.makeText(LoginActivity.this,"Login Succussfully",Toast.LENGTH_SHORT).show();
                                accessToken = loginResult.getAccessToken()
                                        .getToken();
                                Log.i("accessToken", accessToken);
                                SharedPreferences pref = getApplicationContext().getSharedPreferences("Login", MODE_PRIVATE);
                                SharedPreferences.Editor editor = pref.edit();
                                editor.putString("accessToken", accessToken);
                                editor.apply();

                                getUserDetails(loginResult);
                                //RegisterClass();

                                // App code
                            }

                            @Override
                            public void onCancel()
                            {
                                // App code
                                Toast.makeText(LoginActivity.this,"cancel Succussfully",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(FacebookException exception)
                            {
                                // App code
                                Toast.makeText(LoginActivity.this,"Error occured",Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
//google sign in
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .requestScopes(new Scope(Scopes.PLUS_LOGIN))
                        .build();
                if (mGoogleApiClient == null) {
                    mGoogleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                            .enableAutoManage(LoginActivity.this, LoginActivity.this)
                            .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                            .build();
                    mGoogleApiClient.connect();
                }
                signIn();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
            Intent intent =new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);


        }
    }
    protected void getUserDetails(final LoginResult loginResult) {
        GraphRequest data_request = GraphRequest.newMeRequest(
                loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject json_object,
                            GraphResponse response) {
                        //JSONObject response = new JSONObject(json_object);
                        try {
                            Log.e(TAG, "Responce: " + response);
                            emailid=json_object.get("email").toString();
                            // AccessToken access_token = loginResult.getAccessToken();
                            // Toast.makeText(LoginActivity.this, response,Toast.LENGTH_LONG).show();

                            fname=json_object.get("first_name").toString();
                            Log.e(TAG, "first name: " + json_object.get("first_name").toString());
                            lname=json_object.get("last_name").toString();
                            Log.e(TAG, "last_name: " + json_object.get("last_name").toString());

                            String id = json_object.getString("id");
                            String profileImg = "http://graph.facebook.com/"+ id+ "/picture?type=large";

                            Log.e(TAG, "profileImg: " +profileImg);
                            // if(json_object.get("birthday").toString().equals(null)){
                            //birthday=json_object.get("birthday").toString();
                            // Log.e(TAG, "birthday: " + json_object.get("birthday").toString());
                            birthday="00-00-0000";
                            Intent intent =new Intent(LoginActivity.this,MainActivity.class);
                            startActivity(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                });
        Bundle permission_param = new Bundle();
        permission_param.putString("fields", "id,first_name,last_name,gender,birthday,email,picture.width(120).height(120)");
        data_request.setParameters(permission_param);
        data_request.executeAsync();

    }
    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    protected void onResume() {
        super.onResume();
        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onStart() {
        super.onStart();


    }
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Log.d(TAG, "onConnectionFailed:" + connectionResult);
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {

            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            fname=acct. getGivenName();
            Log.e(TAG, "first name: " + acct. getGivenName());
            lname=acct.getFamilyName();
            Log.e(TAG, "last name: " + acct. getFamilyName());

            String personName = acct.getDisplayName();
            //  String personPhotoUrl = acct.getPhotoUrl().toString();
            emailid = acct.getEmail();


        }
    }

}