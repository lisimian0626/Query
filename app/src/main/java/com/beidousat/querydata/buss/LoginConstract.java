package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.BasePresenter;
import com.beidousat.querydata.base.BaseView;
import com.beidousat.querydata.model.Banci;
import com.beidousat.querydata.model.LoginInfo;

import java.util.Map;

/**
 * author: Hanson
 * date:   2016/8/31
 * describe:
 */
public interface LoginConstract {
    interface View extends BaseView {
        void OnRequestData(LoginInfo loginInfo);
    }

    interface LoginPresenter extends BasePresenter {
        void login(Map<String, String> requestMap);

    }

}
