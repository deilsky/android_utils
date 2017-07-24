package com.deilsky.mvc.model.update_app;

import com.deilsky.model.Apps;
import com.deilsky.network.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * Created by 帷幕 on 2017/7/12.
 */

public interface AppService {
    @GET("default/mobile/checkApp")
    Call<Result<Apps>> check();
    @Streaming
    @GET
    Call<ResponseBody> download(@Url String path);
}
