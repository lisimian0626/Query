package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.BasePresenter;
import com.beidousat.querydata.base.BaseView;
import com.beidousat.querydata.model.Banci;
import com.beidousat.querydata.model.ReCharge;

import java.util.Map;

/**
 * author: Hanson
 * date:   2016/8/31
 * describe:
 */
public interface BanciConstract {
    interface View extends BaseView {
        void OnRequestData(Banci banci);
    }

    interface ChangeDutyPresenter extends BasePresenter {
        void getChangeDutyList(Map<String, String> requestMap, int cur_page, int pre_page);

    }

}
