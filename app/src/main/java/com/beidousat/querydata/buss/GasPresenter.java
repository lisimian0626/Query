package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.CommonPresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.http.exception.AbsExceptionEngine;
import com.beidousat.querydata.ksoap2.transport.SoapHelper;
import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.Gas;
import com.beidousat.querydata.utils.L;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class GasPresenter extends CommonPresenter<GasConstract.View> implements GasConstract.GasPresenter {
    private Map<String,String> soapHeaderMap;
    private String mBody;

    public GasPresenter(GasConstract.View view) {
        super(view);
    }

    @Override
    public void getGasList(Map<String, String> requestMap, int cur_page, int pre_page) {
        final String method = getMethodName();
        HashMap<String, Object> properties = new HashMap<String, Object>();
        for (Map.Entry<String, String> entry : requestMap.entrySet()) {
            properties.put(entry.getKey(), entry.getValue());
        }
        properties.put("arg6", cur_page);
        properties.put("arg7", pre_page);
        L.test("properties:"+properties.toString());
        List<Object> getParamters =  SoapHelper.getInstance().getParams(Constant.GETGASLIST,Constant.nameSpace,properties);
        if(getParamters!=null){
            soapHeaderMap = (Map<String, String>) getParamters.get(0);
            mBody = new String((byte[]) getParamters.get(1));
        }
        Disposable disposable = mApiService.getGasList(soapHeaderMap,mBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading("正在加载中，请稍等...");
                    }
                }).subscribe(new Consumer<Gas>() {
                    @Override
                    public void accept(Gas gas) throws Exception {
                        mView.hideLoading();
                        mView.OnRequestData(gas);
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
