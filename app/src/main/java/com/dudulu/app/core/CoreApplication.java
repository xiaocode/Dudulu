package com.dudulu.app.core;

import android.app.Application;

import com.dudulu.app.network.VolleyPool;

/**
 * Created by Vincent on 4/16/14.
 */
public class CoreApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    private void init() {
        VolleyPool.init(this);
    }
}
