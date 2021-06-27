package com.primasis.project.materiandroidpertemuan8.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * created by Irfan Assidiq
 * email : assidiq.irfan@gmail.com
 **/
public class PrefsHelper {
    SharedPreferences prefs;
    Context ctx;

    private final String APPNAME = "LATIHAN8";
    private final String NAME = "username";
    private final String LOGINSTAT = "statuslogin";

    private static PrefsHelper instance;
    public static PrefsHelper sharedInstance(Context ctx){
        if(instance == null){
            instance = new PrefsHelper(ctx);
        }
        return instance;
    }

    private PrefsHelper(Context ctx){
        this.ctx = ctx;
        this.prefs = ctx.getSharedPreferences(APPNAME, Context.MODE_PRIVATE);
    }

    public void setUsername(String username){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(NAME, username);
        editor.apply();
    }

    public String getUsername(){
        return prefs.getString(NAME, "");
    }

    //handling login
    public void setStatusLogin(boolean status){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(LOGINSTAT, status);
        editor.apply();
    }

    public boolean getStatusLogin(){
        return prefs.getBoolean(LOGINSTAT, false);
    }
}
