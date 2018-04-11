package com.social.facebook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Arrays;

public class FacebookHelper {

    private FacebookHelperCallback mCallback;
    private CallbackManager mCallBackManager;
    Context mContext;

    public FacebookHelper(Context context, String appId, FacebookHelperCallback facebookHelperCallback) {
        mContext = context;
        mCallback = facebookHelperCallback;
        FacebookSdk.setApplicationId(appId);
        FacebookSdk.sdkInitialize(context);
        mCallBackManager = CallbackManager.Factory.create();
        FacebookCallback<LoginResult> mCallBack = new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                mCallback.onFacebookSignInSuccess(loginResult.getAccessToken().getToken(),
                        loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                mCallback.onFacebookSignInFail("User cancelled operation");
            }

            @Override
            public void onError(FacebookException e) {
                mCallback.onFacebookSignInFail(e.getMessage());
            }
        };
        LoginManager.getInstance().registerCallback(mCallBackManager, mCallBack);
    }

    @NonNull
    @CheckResult
    public CallbackManager getCallbackManager() {
        return mCallBackManager;
    }

    public void performSignIn(Activity activity) {
        LoginManager.getInstance()
                .logInWithReadPermissions(activity,
                        Arrays.asList("public_profile", "user_friends", "email"));
    }

    public void performSignIn(Fragment fragment) {
        LoginManager.getInstance()
                .logInWithReadPermissions(fragment,
                        Arrays.asList("public_profile", "user_friends", "email"));
    }

    public void getUserDetails() {
        GraphRequest request = GraphRequest.newMeRequest(
                AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        if (response.getError() == null) {
                            FacebookUser facebookUser = new GsonBuilder().create().fromJson(object.toString(), FacebookUser.class);
                            mCallback.onFacebookUserData(facebookUser);
                        } else {
                            mCallback.onFacebookSignInFail(response.getError().getErrorMessage());
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link,locale,timezone,updated_time,verified,first_name,last_name,email,gender,birthday,picture.width(150).height(150)");
        request.setParameters(parameters);
        request.executeAsync();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallBackManager.onActivityResult(requestCode, resultCode, data);
    }

    public void performSignOut() {
        LoginManager.getInstance().logOut();
        mCallback.onFacebookSignOut();
    }
}
