package com.deilsky.mvc.model.update_app;

import com.deilsky.model.Apps;
import com.deilsky.network.Result;

/**
 * Created by 帷幕 on 2017/7/12.
 */

public interface AppListener {
    void onSuccess(Result<Apps> result);
    void onError(String msg);
}
