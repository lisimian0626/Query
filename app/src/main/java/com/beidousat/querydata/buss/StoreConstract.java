package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.BasePresenter;
import com.beidousat.querydata.base.BaseView;
import com.beidousat.querydata.model.Banci;
import com.beidousat.querydata.model.Store;

import java.util.Map;

/**
 * author: Hanson
 * date:   2016/8/31
 * describe:
 */
public interface StoreConstract {
    interface View extends BaseView {
        void OnRequestData(Store store);
    }

    interface StorePresenter extends BasePresenter {
        void getStoreList(Map<String, String> requestMap, int cur_page, int pre_page);

    }

}
