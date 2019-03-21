package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.BasePresenter;
import com.beidousat.querydata.base.BaseView;
import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.Gas;

import java.util.Map;

/**
 * author: Hanson
 * date:   2016/8/31
 * describe:
 */
public interface GasConstract {
    interface View extends BaseView {
        void OnRequestData(Gas gas);
    }

    interface GasPresenter extends BasePresenter {
        void getGasList(Map<String, String> requestMap, int cur_page, int pre_page);

    }

}
