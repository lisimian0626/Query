package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.BasePresenter;
import com.beidousat.querydata.base.BaseView;

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
        void requestData();

    }

}
