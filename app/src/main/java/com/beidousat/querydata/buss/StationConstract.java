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
public interface StationConstract {
    interface View extends BaseView {
        void OnRequestData(Station station);
    }

    interface StationPresenter extends BasePresenter {
        void getStationList(String key);

    }

}
