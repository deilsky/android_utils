package com.deilsky.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by deilsky on 2016/8/16.
 */
public class RetrofitUtils {
    private static OkHttpClient client = Singleton.getInstance();
    public static Retrofit getInstance() {
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Contract.BASE_URL).addConverterFactory(GsonConverterFactory.create()).client(client);
        Retrofit retrofit = builder.build();
        return retrofit;
    }
}
