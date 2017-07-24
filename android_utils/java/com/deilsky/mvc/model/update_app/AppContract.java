package com.deilsky.mvc.model.update_app;

/**
 * Created by 帷幕 on 2017/7/12.
 */

public interface AppContract {
    void check(AppListener listener);
    void download(String path,DownloadListener listener);
}
