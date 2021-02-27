package com.example.pokemon;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Util.init(this);
        Stetho.initializeWithDefaults(this);


    }
}
