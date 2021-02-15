package com.example.letstour.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class CommonTask {
    public static final String USER_KEY="user_key";
    public static final String AGENCY_NAME="agency_name";
    public static final String USER_NAME="user_name";
    public static final String USER_EMAIL="user_email";
    public static final String USER_IMAGE="user_image";
    public static final String USER_GENDER="user_gender";
    public static final String USER_PRI_NUMBER="user_pri_num";
    public static final String USER_NUMBER1="user_num1";
    public static final String USER_NUMBER2="user_num2";

    public static void addDataIntoSharedPreference(Context context,String key,String value){
        SharedPreferences sharedPreferences=context.getSharedPreferences("APP",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getDataFromSharedPreference(Context context,String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences("APP",Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,"");
    }
}
