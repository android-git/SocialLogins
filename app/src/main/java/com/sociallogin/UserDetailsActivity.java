package com.sociallogin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.social.facebook.FacebookHelper;
import com.social.facebook.FacebookHelperCallback;
import com.social.facebook.FacebookUser;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserDetailsActivity extends AppCompatActivity implements FacebookHelperCallback {

    public static String ARG_FACEBOOKUSER = "arg_facebookuser";

    @BindView(R.id.imgFBUserProfileImage)
    ImageView imgFBUserProfileImage;
    @BindView(R.id.txtFBUserName)
    TextView txtFBUserName;
    @BindView(R.id.txtFBUserEmail)
    TextView txtFBUserEmail;
    @BindView(R.id.signout_button)
    Button signout_button;

    FacebookUser facebookUser;
    String fbUser;

    private FacebookHelper mFacebookHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);

        fbUser = getIntent().getStringExtra(ARG_FACEBOOKUSER);
        facebookUser = new Gson().fromJson(fbUser, FacebookUser.class);

        mFacebookHelper = new FacebookHelper(this, getString(R.string.facebook_app_id), this);

        if (facebookUser != null) {
            txtFBUserName.setText(facebookUser.getName());
            txtFBUserEmail.setText(facebookUser.getEmail());

            Picasso.get().load("https://graph.facebook.com/"+facebookUser.getId()+"/picture?type=normal").into(imgFBUserProfileImage);
        }
    }

    @OnClick(R.id.signout_button)
    public void onSignOutButtonClick(View view) {
        mFacebookHelper.performSignOut();
    }

    @Override
    public void onFacebookSignInSuccess(String authToken, String userId) {

    }

    @Override
    public void onFacebookUserData(FacebookUser facebookUser) {

    }

    @Override
    public void onFacebookSignInFail(String errorMessage) {

    }

    @Override
    public void onFacebookSignOut() {
        Toast.makeText(getApplicationContext(), R.string.user_signout, Toast.LENGTH_SHORT).show();
        finish();
    }
}
