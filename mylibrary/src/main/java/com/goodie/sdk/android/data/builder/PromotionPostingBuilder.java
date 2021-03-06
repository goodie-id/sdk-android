package com.goodie.sdk.android.data.builder;

import android.content.Context;

import com.goodie.sdk.android.data.api.GoodieApis;
import com.goodie.sdk.android.data.bean.BasicRulesReq;
import com.goodie.sdk.android.data.bean.CustomRulesReq;
import com.goodie.sdk.android.data.listener.SetPromotionPostingListener;
import com.goodie.sdk.android.data.response.PromotionPostingResponse;
import java.util.List;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Goodie on 18/02/2019.
 */

public class PromotionPostingBuilder {


    private String authToken;
    private String deviceUniqId;
    private String memberId;
    private String merchantId;
    private String storeId;
    private BasicRulesReq basicRulesReqs;
    private List<CustomRulesReq> customRulesReqs;

    public PromotionPostingBuilder(String authToken, String deviceUniqId, String memberId, String merchantId, String storeId,
                                   BasicRulesReq basicRulesReqs, List<CustomRulesReq> customRulesReqs){
        this.deviceUniqId = deviceUniqId;
        this.authToken = authToken;
        this.memberId = memberId;
        this.merchantId = merchantId;
        this.storeId = storeId;
        this.basicRulesReqs = basicRulesReqs;
        this.customRulesReqs = customRulesReqs;
    }


    public void promotionPostingGoodie(Context context, SetPromotionPostingListener listener){
        promoPostingObserv(authToken, deviceUniqId, memberId, merchantId, storeId, basicRulesReqs, customRulesReqs, context)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(listener::onSuccess, listener::onError);
    }


    public Observable<PromotionPostingResponse> promoPostingObserv(String authToken, String deviceUniqId, String memberId, String merchantId, String storeId,
                                                                   BasicRulesReq basicRulesReq, List<CustomRulesReq> customRulesReqs, Context context){
        return GoodieApis.getInstance().doPromotionPosting(authToken, deviceUniqId, memberId, merchantId, storeId, basicRulesReq, customRulesReqs, context);
    }


}
