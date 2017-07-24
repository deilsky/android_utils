package com.deilsky.network;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import okhttp3.ResponseBody;

/**
 * Created by deilsky on 2016/8/16.
 */
public class DownLoadUtils {
    private static final String TAG = "DownLoadUtils";
    private static String APK_CONTENTTYPE = "application/vnd.android.package-archive;charset=UTF-8";

    private static String PNG_CONTENTTYPE = "image/png";

    private static String JPG_CONTENTTYPE = "image/jpg";

    private static String fileSuffix = "";
    public interface DownLoadFinashListener{
        public void onFinashed(String path);
        public void onError();
        public void onProgress(int progress);
        public void onReady(int max);
    }

    @SuppressLint("UseValueOf")
    public static boolean writeResponseBodyToDisk(Context context, ResponseBody body, String name, DownLoadFinashListener listener) {

        Log.d(TAG, "contentType:>>>>" + body.contentType().toString());
        String type = body.contentType().toString();
        if (type.equals(APK_CONTENTTYPE)) {
            fileSuffix = ".apk";
        } else if (type.equals(PNG_CONTENTTYPE)) {
            fileSuffix = ".png";
        } else if (type.equals(JPG_CONTENTTYPE)) {
            fileSuffix = ".jpg";
        }
        String path = context.getExternalFilesDir(null) + File.separator + name;
        Log.d(TAG, "path:>>>>" + path);
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(path);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                listener.onReady(new Long(fileSize / 1024).intValue());
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + fileSize);
                    listener.onProgress(new Long(fileSizeDownloaded / 1024).intValue());
                }
                outputStream.flush();
                listener.onFinashed(path);
                return true;
            } catch (IOException e) {
                listener.onError();
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            listener.onError();
            return false;
        }
    }
}
