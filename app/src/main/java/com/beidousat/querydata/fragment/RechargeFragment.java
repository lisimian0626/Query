package com.beidousat.querydata.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.beidousat.querydata.R;
import com.beidousat.querydata.base.BaseFragment;
import com.beidousat.querydata.base.WidgetPage;
import com.beidousat.querydata.buss.RechargeConstract;
import com.beidousat.querydata.buss.RechargePresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.common.GlobalDataUtil;
import com.beidousat.querydata.model.ReCharge;
import com.beidousat.querydata.utils.L;
import com.beidousat.querydata.widget.OnPageScrollListener;
import com.beidousat.querydata.widget.WidgetRechargePager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RechargeFragment extends BaseFragment implements WidgetPage.OnPageChangedListener, OnPageScrollListener,RechargeConstract.View {
    private String  title;
    private WidgetPage mWidgetPage;
    private WidgetRechargePager mWidgetRechargePager;
    private RechargePresenter presenter;
    private Map<String,String> requestParams=new HashMap<>();
    public static RechargeFragment newInstance(String title){
        RechargeFragment pf=new RechargeFragment();
        Bundle bundle= new Bundle();
        bundle.putString("title",title);
        //向fragment中传值，使用此方法，不用使用有参构造方法
        pf.setArguments(bundle);
        return pf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取出保存的值
        this.title=getArguments().getString("title");
        presenter=new RechargePresenter(this);
    }

    @Override
    public int getContentView() {
        return R.layout.contenlayout;
    }

    @Override
    public void initViews() {
        mWidgetRechargePager = (WidgetRechargePager) mRootView.findViewById(R.id.rechargePaper);
        mWidgetPage = (WidgetPage) mRootView.findViewById(R.id.w_page);
        mWidgetPage.setOnPageChangedListener(this);
        mWidgetRechargePager.setOnPagerScrollListener(this);
    }

    @Override
    public void setListeners() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadDataWhenOnResume() {
//        requestParams.put("arg0", Constant.Key);
//        requestParams.put("arg1", GlobalDataUtil.getInstance().getSelectConfig().getStationName());

//        presenter.getRechargeList("SKThd2019","北站站","2019-01-01","2019-03-11",0,"",2,10);
    }

    @Override
    public void cancelRequestWhenOnPause() {

    }

    @Override
    public void onPrePageClick(int before, int current) {

    }

    @Override
    public void onNextPageClick(int before, int current) {

    }

    @Override
    public void onFirstPageClick(int before, int current) {

    }

    @Override
    public void onPageScrollLeft() {

    }

    @Override
    public void onPageScrollRight() {

    }

    @Override
    public void onPagerSelected(int i, boolean b) {

    }

    @Override
    public void OnRequestData(ReCharge reCharge) {
        if(reCharge!=null&&reCharge.getRoot()!=null&&reCharge.getRoot().getData()!=null&&reCharge.getRoot().getData().size()>0){

        }
    }

    @Override
    public void onFeedBack(boolean success, String key, Object data) {

    }

    public void initRechargePager(int totalPage, List<ReCharge.RootBean.DataBean> dataBeanList, Map<String, String> params) {
        L.i(getClass().getSimpleName(), "Current total page:" + totalPage);
        mWidgetPage.setPageCurrent(0);
        mWidgetPage.setPageTotal(totalPage);
        mWidgetRechargePager.initPager(totalPage, dataBeanList, params);
    }
}
