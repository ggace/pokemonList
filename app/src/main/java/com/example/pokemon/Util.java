package com.example.pokemon;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Util {
    private static Application application;
    private static SharedPreferences sp;
    private static SharedPreferences.Editor spEditor;

    public static void init(Application app){
        Util.application = app;

        sp = PreferenceManager.getDefaultSharedPreferences(application);
        spEditor = sp.edit();
    }

    public static void toast(String text){
        Toast.makeText(application, text, Toast.LENGTH_SHORT).show();
    }

    public static void log(String text){
        Log.d("util", text);
    }

    public static void spPut(String key, int value){
        spEditor.putInt(key, value);
    }

    public static void spPut(String key, String value){
        spEditor.putString(key, value);
    }

    public static void spPut(String key, Boolean value){
        spEditor.putBoolean(key, value);
    }

    public static void spPut(String key, Object value){
        Util.spPut(key, objToJsonString(value));
    }

    public static void spCommit(){
        spEditor.commit();
    }

    public static int spGet(String key, int defalutValue){
        return sp.getInt(key, 0);
    }

    public static String spGet(String key, String defautValue){
        return sp.getString(key, defautValue);
    }

    public static boolean spGet(String key, boolean defautValue){
        return sp.getBoolean(key, defautValue);
    }

    public static String objToJsonString(Object value) {
        ObjectMapper om = new ObjectMapper();

        try {
            return om.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            return "";
        }
        

    }

    public static <T> T spGet(String key, Class<T> cls){
        String jsonString = spGet(key, "");

        ObjectMapper om = new ObjectMapper();

        try {
            return (T)om.readValue(jsonString, cls);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static <T> T spGet(String key, TypeReference<T> cls){
        String jsonString = spGet(key, "");

        ObjectMapper om = new ObjectMapper();

        try {
            return (T)om.readValue(jsonString, cls);
        } catch (JsonProcessingException e) {
            return null;
        }
    }

    public static float dipToPixels(float borderRadius) {
        DisplayMetrics metrics = application.getResources().getDisplayMetrics();
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, borderRadius, metrics);
    }

    public static void loadImageOn(String imgUrl, ImageView imageView){
        loadImageOn(imgUrl, imageView, 0);
    }

    public static void loadImageOn(String imgUrl, ImageView imageView, int borderRadius){
        if(borderRadius > 0){
            Glide.with(application)
                    .load(imgUrl)
                    .transform(new CenterCrop(), new RoundedCorners((int)dipToPixels(borderRadius)))
                    .into(imageView);
        }
        else{
            Glide.with(application)
                    .load(imgUrl)
                    .into(imageView);
        }
    }
}
