package com.mischaboldy.mischa.rehapp;

public final class Application extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FontsOverride.setDefaultFont(this, "MONOSPACE", "fonts/KeepCalm-Medium.ttf");
    }
}