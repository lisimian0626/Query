package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.CommonPresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.http.exception.AbsExceptionEngine;
import com.beidousat.querydata.ksoap2.transport.SoapHelper;
import com.beidousat.querydata.model.Station;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StationPresenter extends CommonPresenter<StationConstract.View> implements StationConstract.StationPresenter {
    private Map<String,String> soapHeaderMap;
    private String mBody;
    public StationPresenter(StationConstract.View view) {
        super(view);
    }

    @Override
    public void getStationList(String key) {
        final String method = getMethodName();
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("arg0", key);
        List<Object> getParamters =  SoapHelper.getInstance().getParams(Constant.getStationList,Constant.nameSpace,properties);
        if(getParamters!=null){
            soapHeaderMap = (Map<String, String>) getParamters.get(0);
            mBody = new String((byte[]) getParamters.get(1));
        }
        Disposable disposable = mApiService.getStations(soapHeaderMap,mBody)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mView.showLoading("正在加载中，请稍等...");
                    }
                }).subscribe(new Consumer<Station>() {
                    @Override
                    public void accept(Station station) throws Exception {
                        mView.OnRequestData(station);
                        mView.hideLoading();
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
