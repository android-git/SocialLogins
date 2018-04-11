package com.social.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.social.R;
import com.social.facebook.FacebookHelper;
import com.social.facebook.FacebookHelperCallback;
import com.social.facebook.FacebookUser;
import com.social.google.GoogleHelper;
import com.social.google.GoogleHelperCallback;

public class SocialActivity extends AppCompatActivity implements FacebookHelperCallback, GoogleHelperCallback {

    public static String ARG_FACEBOOK_BUTTON = "arg_facebook_button";
    public static String ARG_GOOGLE_BUTTON = "arg_google_button";
    public static String ARG_FACEBOOK_APP_ID = "arg_fb_app_id";
    public static String ARG_RESULT ="arg_result";

    Button mFacebookButton;
    Button mGoogleButton;

    private FacebookHelper mFacebookHelper;
    private GoogleHelper mGoogleHelper;
    private boolean isFacebook, isGoogle;
    private String facebookAppId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social);

        isFacebook = getIntent().getBooleanExtra(ARG_FACEBOOK_BUTTON, false);
        isGoogle = getIntent().getBooleanExtra(ARG_GOOGLE_BUTTON, false);
        facebookAppId = getIntent().getStringExtra(ARG_FACEBOOK_APP_ID);

        mFacebookButton = (Button) findViewById(R.id.fb_button);
        mGoogleButton = (Button) findViewById(R.id.gle_button);

        mFacebookButton.setVisibility(isFacebook ? View.VISIBLE : View.GONE);
        mGoogleButton.setVisibility(isGoogle ? View.VISIBLE : View.GONE);

        if (facebookAppId != null && !facebookAppId.isEmpty()) {
            mFacebookHelper = new FacebookHelper(this, facebookAppId, this);
        }

        mGoogleHelper = new GoogleHelper(this, this, null);

        mFacebookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (facebookAppId != null && !facebookAppId.isEmpty()) {
                    mFacebookHelper.performSignIn(SocialActivity.this);
                } else {
                    Toast.makeText(getApplicationContext(), "Facebook app id not correct", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGoogleHelper.performSignIn(SocialActivity.this);
            }
        });
    }


    @Override
    public void onFacebookSignInSuccess(String authToken, String userId) {
        Toast.makeText(getApplicationContext(), "Login with Facebook Success", Toast.LENGTH_SHORT).show();
        mFacebookHelper.getUserDetails();
    }

    @Override
    public void onFacebookUserData(FacebookUser facebookUser) {
        if (facebookUser != null) {
            Intent intent = new Intent();
            intent.putExtra(ARG_RESULT, new Gson().toJson(facebookUser));
            setResult(1717, intent);
            finish();
        }
    }

    @Override
    public void onFacebookSignInFail(String errorMessage) {
        Toast.makeText(getApplicationContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFacebookSignOut() {

    }

    @Override
    public void onGoogleSignInSuccess(String authToken, String userId) {
        Toast.makeText(getApplicationContext(), "Login with Google Success", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleSignInFail(String errorMessage) {
        Toast.makeText(getApplicationContext(), "Error: " + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGoogleSignOut() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mFacebookHelper.onActivityResult(requestCode, resultCode, data);
        mGoogleHelper.onActivityResult(requestCode, resultCode, data);
    }
}
