package com.social.google;

public interface GoogleHelperCallback {

    void onGoogleSignInSuccess(String authToken, String userId);

    void onGoogleSignInFail(String errorMessage);

    void onGoogleSignOut();
}
