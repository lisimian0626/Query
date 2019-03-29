package com.beidousat.querydata.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.widget.Toast;

import com.beidousat.querydata.R;
import com.beidousat.querydata.adapter.AdtChangDuty;
import com.beidousat.querydata.adapter.AdtRecharge;
import com.beidousat.querydata.buss.BanciConstract;
import com.beidousat.querydata.buss.ChangDutyPresenter;
import com.beidousat.querydata.buss.RechargeConstract;
import com.beidousat.querydata.buss.RechargePresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.fragment.FmDutyDetail;
import com.beidousat.querydata.model.Banci;
import com.beidousat.querydata.model.ReCharge;
import com.beidousat.widgetlibs.recycler.GridRecyclerView;
import com.beidousat.widgetlibs.recycler.HorizontalDividerItemDecoration;
import com.beidousat.widgetlibs.recycler.VerticalDividerItemDecoration;

import java.util.List;
import java.util.Map;


/**
 * Created by J Wong on 2015/12/17 18:01.
 */
public class WidgetChangeDutyPage extends GridRecyclerView  implements BanciConstract.View,AdtChangDuty.ChangDutyListener{
    private Context mContext;
    private AdtChangDuty mAdapter;
    private ChangDutyPresenter changDutyPresenter;
    public WidgetChangeDutyPage(Context context) {
        super(context);
        init();
    }

    public WidgetChangeDutyPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WidgetChangeDutyPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

//        private AdtSinger mAdapter;

    private void init() {
        changDutyPresenter=new ChangDutyPresenter(this);
        setOverScrollMode(OVER_SCROLL_NEVER);

        HorizontalDividerItemDecoration horDivider = new HorizontalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        VerticalDividerItemDecoration verDivider = new VerticalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        setLayoutManager(new GridLayoutManager(getContext(), 1));

        addItemDecoration(horDivider);
        addItemDecoration(verDivider);

        mAdapter = new AdtChangDuty(getContext());
        mAdapter.setChangDutyListener(this);
        setVerticalScrollBarEnabled(false);
        setAdapter(mAdapter);
    }
    public void setDutyList(List<Banci.RootBean.DataBean> dataBeanList, Banci.RootBean.SumBean sumBean) {
        mAdapter.setData(dataBeanList);
        mAdapter.setSum(sumBean);
    }

    public void loadChangeDuty(int page, Map<String, String> map) {
        requestChangeDuty(page, map);
    }

    private void requestChangeDuty(int page, Map<String, String> map) {
        changDutyPresenter.getChangeDutyList(map,page, Constant.per_pager);
    }
    public void runLayoutAnimation() {
//        if (mAdapter != null) {
//            final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.grid_layout_animation_from_bottom);
//            setLayoutAnimation(controller);
            getAdapter().notifyDataSetChanged();
//            scheduleLayoutAnimation();
//        }
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onFeedBack(boolean success, String key, Object data) {
          if(!success&&getContext()!=null){
              Toast.makeText(getContext(),getContext().getText(R.string.text_data_error),Toast.LENGTH_SHORT).show();
          }
    }

    @Override
    public void OnRequestData(Banci banci) {
        if(banci!=null&&banci.getRoot()!=null&&banci.getRoot().getData()!=null){
            List<Banci.RootBean.DataBean> dataBeanList=banci.getRoot().getData();
            mAdapter.setData(dataBeanList);
            if(banci.getRoot().getSum()!=null){
                mAdapter.setSum(banci.getRoot().getSum());
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getDetail(Banci.RootBean.DataBean dataBean) {
        FmDutyDetail fmDutyDetail=new FmDutyDetail();
        Bundle bundle=new Bundle();
        bundle.putSerializable("dataBean",dataBean);
        fmDutyDetail.setArguments(bundle);
        FragmentActivity fragmentActivity=(FragmentActivity)getContext();
        fmDutyDetail.show(fragmentActivity.getSupportFragmentManager(),"detail");
    }


//    private void init() {
//        mAdapter = new AdtSinger(getContext());
//        setNumColumns(4);
//        int spacing = getResources().getDimensionPixelSize(R.dimen.grid_spacing);
//        setHorizontalSpacing(spacing);
//        setVerticalSpacing(spacing);
//        setSelector(R.drawable.selector_list_item_song);
//        setAdapter(mAdapter);
//    }


//    public void setSinger(List<Singer> starInfos) {
//        mAdapter.setData(starInfos);
//    }
//
//    public void loadSinger(int page, Map<String, String> map) {
//        requestSingers(page, map);
//    }
//
//    @Override
//    public void OnRequestData() {
////            if (RequestMethod.VOD_SINGER.equalsIgnoreCase(method)) {
////                try {
////                    SingersV4 result = (SingersV4) object;
////                    if (result != null) {
////                        if (result.singer != null &&result.singer.data!=null&& result.singer.data.size() > 0) {
////                            mAdapter.setData(result.singer.data);
////                            mAdapter.notifyDataSetChanged();
////                        }
////                    }
////                } catch (Exception e) {
////                    Logger.e(getClass().getSimpleName(), e.toString());
////                }
////            }
//    }
//
//    @Override
//    public void showLoading(String msg) {
//
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
//
//    @Override
//    public void onFeedBack(boolean success, String key, Object data) {
//
//    }
//
//
//    @Override
//    public void OnRequestData(Station station) {
//
//    }
}
