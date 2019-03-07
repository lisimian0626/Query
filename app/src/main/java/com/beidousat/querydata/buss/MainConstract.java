package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.BasePresenter;
import com.beidousat.querydata.base.BaseView;
import com.beidousat.querydata.model.Station;

import java.util.List;

import okhttp3.ResponseBody;

/**
 * author: Hanson
 * date:   2016/8/31
 * describe:
 */
public interface MainConstract {
    interface MainView extends BaseView {
        void OnRequestData();
    }

    interface MainPresenter extends BasePresenter {
        void getStationList(String key);

    }

}
