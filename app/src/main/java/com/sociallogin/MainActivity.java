package com.sociallogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.social.facebook.FacebookHelper;
import com.social.facebook.FacebookHelperCallback;
import com.social.facebook.FacebookUser;
import com.social.ui.SocialActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements FacebookHelperCallback {

    @BindView(R.id.facebook_button)
    Button mFacebookButton;

    private FacebookHelper mFacebookHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mFacebookHelper = new FacebookHelper(this, getString(R.string.facebook_app_id), this);
    }

    @OnClick(R.id.facebook_button)
    public void onFacebookButtonClick(View view) {
        mFacebookHelper.performSignIn(this);
    }

    @OnClick(R.id.social_button)
    public void onGoogleButtonClick(View view) {
        Intent intent = new Intent(this, SocialActivity.class);
        intent.putExtra(SocialActivity.ARG_FACEBOOK_BUTTON, true);
        intent.putExtra(SocialActivity.ARG_GOOGLE_BUTTON, true);
        intent.putExtra(SocialActivity.ARG_FACEBOOK_APP_ID, getString(R.string.facebook_app_id));
        startActivityForResult(intent, 1717);
    }

    @Override
    public void onFacebookSignInSuccess(String authToken, String userId) {
        Toast.makeText(getApplicationContext(), R.string.login_with_facebook_success, Toast.LENGTH_SHORT);
        mFacebookHelper.getUserDetails();
    }

    @Override
    public void onFacebookUserData(FacebookUser facebookUser) {
        if (facebookUser != null) {
            Intent intent = new Intent(this, UserDetailsActivity.class);
            intent.putExtra(UserDetailsActivity.ARG_FACEBOOKUSER, new Gson().toJson(facebookUser));
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onFacebookSignInFail(String errorMessage) {
        Toast.makeText(getApplicationContext(), getString(R.string.error) + errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFacebookSignOut() {
        Toast.makeText(getApplicationContext(), R.string.user_signout, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1717 && resultCode == 1717) {
            String fbUser = data.getStringExtra(SocialActivity.ARG_RESULT);
            Intent intent = new Intent(this, UserDetailsActivity.class);
            intent.putExtra(UserDetailsActivity.ARG_FACEBOOKUSER, fbUser);
            startActivity(intent);
            finish();
        } else {
            mFacebookHelper.onActivityResult(requestCode, resultCode, data);
        }
    }
}
