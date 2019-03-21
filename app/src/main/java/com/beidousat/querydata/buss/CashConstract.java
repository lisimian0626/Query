package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.BasePresenter;
import com.beidousat.querydata.base.BaseView;
import com.beidousat.querydata.model.Banci;
import com.beidousat.querydata.model.Cash;

import java.util.Map;

/**
 * author: Hanson
 * date:   2016/8/31
 * describe:
 */
public interface CashConstract {
    interface View extends BaseView {
        void OnRequestData(Cash cash);
    }

    interface CashPresenter extends BasePresenter {
        void getCashList(Map<String, String> requestMap, int cur_page, int pre_page);

    }

}
