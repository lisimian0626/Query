package com.beidousat.querydata.buss;



import com.beidousat.querydata.base.BasePresenter;
import com.beidousat.querydata.base.BaseView;
import com.beidousat.querydata.model.DutyDetail;
import com.beidousat.querydata.model.LoginInfo;

import java.util.Map;

/**
 * author: Hanson
 * date:   2016/8/31
 * describe:
 */
public interface DutyDetailConstract {
    interface View extends BaseView {
        void OnRequestData(DutyDetail dutyDetail);
    }

    interface DutyDetailPresenter extends BasePresenter {
        void getDutyDetail(Map<String, String> requestMap);
    }

}
