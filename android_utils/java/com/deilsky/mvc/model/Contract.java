package com.deilsky.mvc.model;


import com.deilsky.network.RetrofitUtils;

import retrofit2.Retrofit;

/**
 * Created by 帷幕 on 2017/6/15.
 */

public class Contract {
    protected Retrofit retrofit = RetrofitUtils.getInstance();
    protected final static int SUCCESS = 200;

}
