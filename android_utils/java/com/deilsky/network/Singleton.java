package com.deilsky.network;

import okhttp3.OkHttpClient;

public class Singleton {
    private volatile static OkHttpClient client;
    private Singleton() {
    }
    public static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (OkHttpClient.class) {
                if (client == null) {
                    OkHttpClient.Builder build = new OkHttpClient.Builder();
                    client = build.build();
                }
            }
        }
        return client;
    }
}
