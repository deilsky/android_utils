package com.deilsky.mvc.model.update_app;

import okhttp3.ResponseBody;

/**
 * Created by 帷幕 on 2017/7/12.
 */

public interface DownloadListener {
    void onSuccess(ResponseBody result);
    void onError(String msg);
}
