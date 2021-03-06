package com.goodie.sdk.android.data.builder;
import android.content.Context;
import com.goodie.sdk.android.data.api.GoodieApis;
import com.goodie.sdk.android.data.listener.SetLoginListener;
import com.goodie.sdk.android.data.response.LoginResponse;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Goodie on 13/02/2019.
 */

public class LoginBuilder {

    private String deviceUniqId;
    private String password;
    private String username;
    private String merchantCode;

    public LoginBuilder(String deviceUniqId, String username, String password, String merchantCode){
        this.deviceUniqId = deviceUniqId;
        this.username = username;
        this.password = password;
        this.merchantCode = merchantCode;
    }

    public void loginGoodie(Context context, SetLoginListener listener){
        loginObserv(deviceUniqId, username,  password,  merchantCode, context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onSuccess, listener::onError);
    }

    public Observable<LoginResponse> loginObserv(String deviceUniqId, String username, String password, String merchantCode, Context context){
        return GoodieApis.getInstance().doLogin(deviceUniqId, username, password, merchantCode, context);
    }

}
