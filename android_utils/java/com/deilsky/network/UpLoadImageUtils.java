package com.deilsky.network;

import java.io.File;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by deilsky on 2016/8/16.
 */
public class UpLoadImageUtils {
    public static ArrayList<MultipartBody.Part> addFiles(ArrayList<String> path) {
        ArrayList<MultipartBody.Part> list = new ArrayList<MultipartBody.Part>();
        for (String key : path) {
            File file = new File(key);
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            MultipartBody.Part part = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
            list.add(part);
        }
        return list;
    }
}
