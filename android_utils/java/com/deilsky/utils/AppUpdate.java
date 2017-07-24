package com.deilsky.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import com.daimajia.numberprogressbar.NumberProgressBar;
import com.orhanobut.logger.Logger;
import com.deilsky.activies.App;
import com.deilsky.model.Apps;
import com.deilsky.mvc.model.update_app.AppContractImpl;
import com.deilsky.mvc.model.update_app.AppListener;
import com.deilsky.mvc.model.update_app.DownloadListener;
import com.deilsky.network.DownLoadUtils;
import com.deilsky.network.Result;
import com.deilsky.ui.ToastUtil;
import com.deilsky.ui.UpdateDialog;

import java.io.File;

import okhttp3.ResponseBody;

/**
 * Created by 帷幕 on 2017/7/12.
 */

public class AppUpdate {
    Context mContext = App.getInstance().appContext;
    private Activity activity;

    public AppUpdate(Activity activity) {
        this.activity = activity;
    }

    public void check() {
        AppContractImpl.getInstance().check(new AppListener() {
            @Override
            public void onSuccess(Result<Apps> result) {
                if (result.getStatus() == 200) {
                    showTips(result.getData());
                }
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    public void showTips(final Apps app) {
        final String apkName = app.getPath().substring(app.getPath().lastIndexOf("/")+1);
        String sPath = "";
        PackageInfo pi = null;
        try {
            pi = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (!app.getCode().equals(pi.versionName)) {
            ToastUtil.showToast(App.getInstance().appContext, "有新版本！");
            Logger.e(app.getCode() + "," + app.getPath());
            final UpdateDialog updateDialog = new UpdateDialog();
            updateDialog.setOnButtonClickListener(new UpdateDialog.DialogFragmentListener() {
                @Override
                public void cancel() {

                }

                @Override
                public void enter(final NumberProgressBar bar,final TextView finish) {
                    AppContractImpl.getInstance().download(app.getPath(), new DownloadListener() {
                        @Override
                        public void onSuccess(final ResponseBody result) {
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    DownLoadUtils.writeResponseBodyToDisk(mContext, result, apkName, new DownLoadUtils.DownLoadFinashListener() {
                                        @Override
                                        public void onFinashed(String path) {
                                            finish.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    finish.setVisibility(View.VISIBLE);
                                                }
                                            });
                                            updateDialog.dismiss();
                                            File apkfile = new File(path);
                                            if (!apkfile.exists()) {
                                                return;
                                            }
                                            Intent i = new Intent(Intent.ACTION_VIEW);
                                            i.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");
                                            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            mContext.startActivity(i);
                                        }

                                        @Override
                                        public void onError() {
                                            ToastUtil.showToast(mContext,"下载出错！");
                                            updateDialog.dismiss();
                                        }

                                        @Override
                                        public void onProgress(final int progress) {
                                            bar.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    bar.setProgress(progress);
                                                }
                                            });
                                        }

                                        @Override
                                        public void onReady(final int max) {
                                            bar.post(new Runnable() {
                                                @Override
                                                public void run() {
                                                    bar.setMax(max);
                                                }
                                            });
                                        }
                                    });
                                }
                            }).start();
                        }

                        @Override
                        public void onError(String msg) {

                        }
                    });
                }
            });
            updateDialog.show(activity.getFragmentManager(),"");
        }
    }

}
