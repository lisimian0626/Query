package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.BasePresenter;
import com.beidousat.querydata.base.BaseView;
import com.beidousat.querydata.model.ReCharge;
import com.beidousat.querydata.model.Station;

/**
 * author: Hanson
 * date:   2016/8/31
 * describe:
 */
public interface RechargeConstract {
    interface View extends BaseView {
        void OnRequestData(ReCharge station);
    }

    interface RechargePresenter extends BasePresenter {
        void getStationList(String key,String stationName,String startTime,String endTime,int selectKey,String slectValue,int cur_page,int pre_page);

    }

}
