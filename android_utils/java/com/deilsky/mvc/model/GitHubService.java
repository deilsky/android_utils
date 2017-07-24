package com.deilsky.mvc.model;


import com.deilsky.model.Apps;
import com.deilsky.network.Result;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by deilsky on 2016/8/16.
 */
public interface GitHubService {
    @Multipart
    @POST("mobile/upload")
    Call<Result<Apps>> upolad(@Part ArrayList<MultipartBody.Part> files);
}
