package com.oneteam.graduationproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Mohamed AbdelraZek on 2/17/2017.
 */

public class UserSession {

    SharedPreferences zPref;
    SharedPreferences.Editor zEditor;
    Context zContext;
    final int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "AndroidGProject";
    private static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_ID = "id";

    public UserSession(Context context) {
        this.zContext = context;
        zPref = zContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        zEditor = zPref.edit();
    }

    public void createLoginSession(String email, String password, String name, String id) {
        zEditor.putBoolean(IS_LOGIN, true);
        zEditor.putString(KEY_EMAIL, email);
        zEditor.putString(KEY_PASSWORD, password);
        zEditor.putString(KEY_NAME, name);
        zEditor.putString(KEY_ID, id);
        zEditor.commit();
    }

    /**
     * Check login method wil check user login status
     * If false it will redirect user to login page
     * Else won't do anything
     */
    public void checkLogin() {
        // Check login status
        if (this.isLoggedIn()) {
            // user is not logged in redirect him to Login Activity
            zContext.startActivity(new Intent(zContext, HomeActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }

    }


    /**
     * Get stored session data
     */
    public HashMap<String, String> getUserDetails() {
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_PASSWORD, zPref.getString(KEY_PASSWORD, null));
        user.put(KEY_EMAIL, zPref.getString(KEY_EMAIL, null));
        user.put(KEY_NAME, zPref.getString(KEY_NAME, null));
        user.put(KEY_ID, zPref.getString(KEY_ID, null));

        // return user
        return user;
    }

    /**
     * Clear session details
     */
    public void logout() {
        zEditor.clear();
        zEditor.commit();
        zContext.startActivity(new Intent(zContext, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                .setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
    }

    public boolean isLoggedIn() {
        return zPref.getBoolean(IS_LOGIN, false);
    }
}