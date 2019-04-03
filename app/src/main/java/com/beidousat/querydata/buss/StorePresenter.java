package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.CommonPresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.http.exception.AbsExceptionEngine;
import com.beidousat.querydata.ksoap2.transport.SoapHelper;
import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.Store;
import com.beidousat.querydata.utils.L;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StorePresenter extends CommonPresenter<StoreConstract.View> implements StoreConstract.StorePresenter {
    private Map<String,String> soapHeaderMap;
    private String mBody;

    public StorePresenter(StoreConstract.View view) {
        super(view);
    }

    @Override
    public void getStoreList(Map<String, String> requestMap, int cur_page, int pre_page) {
        final String method = getMethodName();
        HashMap<String, Object> properties = new HashMap<String, Object>();
        for (Map.Entry<String, String> entry : requestMap.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }
        properties.put("arg6", cur_page);
        properties.put("arg7", pre_page);
        L.test("properties:"+properties.toString());
        List<Object> getParamters =  SoapHelper.getInstance().getParams(Constant.GETEQUIPMENTLIST,Constant.nameSpace,properties);
        if(getParamters!=null){
            soapHeaderMap = (Map<String, String>) getParamters.get(0);
            mBody = new String((byte[]) getParamters.get(1));
        }
        Disposable disposable = mApiService.getEquipmentList(soapHeaderMap,mBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading("正在加载中，请稍等...");
                    }
                }).subscribe(new Consumer<Store>() {
                    @Override
                    public void accept(Store store) throws Exception {
                        mView.hideLoading();
                        mView.OnRequestData(store);
                    }
                }, new AbsExceptionEngine() {
                    @Override
                    public void handMessage(String message) {
                        mView.hideLoading();
                        mView.onFeedBack(false, method, message);
                    }
                });

        addDispose(disposable);
    }
}
