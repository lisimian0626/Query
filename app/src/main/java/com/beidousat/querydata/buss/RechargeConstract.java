package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.BasePresenter;
import com.beidousat.querydata.base.BaseView;
import com.beidousat.querydata.model.ReCharge;
import com.beidousat.querydata.model.Station;

import java.util.Map;

/**
 * author: Hanson
 * date:   2016/8/31
 * describe:
 */
public interface RechargeConstract {
    interface View extends BaseView {
        void OnRequestData(ReCharge reCharge);
    }

    interface RechargePresenter extends BasePresenter {
        void getRechargeList(Map<String,String> requestMap, int cur_page, int pre_page);

    }

}
