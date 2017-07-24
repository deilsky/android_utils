package com.deilsky.mvc.model.update_app;

import com.deilsky.model.Apps;
import com.deilsky.mvc.model.Contract;
import com.deilsky.network.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by 帷幕 on 2017/7/12.
 */

public class AppContractImpl extends Contract implements AppContract {
    private AppService service = null;
    public static AppContractImpl instance;
    private AppContractImpl(){
        service = retrofit.create(AppService.class);
    }
    public static AppContractImpl getInstance(){
        instance = new AppContractImpl();
        return instance;
    }
    @Override
    public void check(final AppListener listener) {
        Call<Result<Apps>> call = service.check();
        call.enqueue(new Callback<Result<Apps>>() {
            @Override
            public void onResponse(Call<Result<Apps>> call, Response<Result<Apps>> response) {
                if (response.isSuccessful() && response.code() == Contract.SUCCESS) {
                    listener.onSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Result<Apps>> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        });
    }

    @Override
    public void download(String path, final DownloadListener listener) {
        Call<ResponseBody> call = service.download(path);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                listener.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
