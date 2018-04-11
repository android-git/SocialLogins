package com.social.facebook;

public interface FacebookHelperCallback {

    void onFacebookSignInSuccess(String authToken, String userId);

    void onFacebookUserData(FacebookUser facebookUser);

    void onFacebookSignInFail(String errorMessage);

    void onFacebookSignOut();
}
