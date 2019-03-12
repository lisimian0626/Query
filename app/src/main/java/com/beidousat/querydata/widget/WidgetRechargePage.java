package com.beidousat.querydata.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.widget.Toast;

import com.beidousat.querydata.R;
import com.beidousat.querydata.adapter.AdtRecharge;
import com.beidousat.querydata.buss.RechargeConstract;
import com.beidousat.querydata.buss.RechargePresenter;
import com.beidousat.querydata.model.ReCharge;
import com.beidousat.widgetlibs.recycler.GridRecyclerView;
import com.beidousat.widgetlibs.recycler.HorizontalDividerItemDecoration;
import com.beidousat.widgetlibs.recycler.VerticalDividerItemDecoration;

import java.util.List;
import java.util.Map;


/**
 * Created by J Wong on 2015/12/17 18:01.
 */
public class WidgetRechargePage extends GridRecyclerView  implements RechargeConstract.View{

    private AdtRecharge mAdapter;
    private RechargePresenter rechargePresenter;
    public WidgetRechargePage(Context context) {
        super(context);
        init();
    }

    public WidgetRechargePage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WidgetRechargePage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

//        private AdtSinger mAdapter;

    private void init() {
        rechargePresenter=new RechargePresenter(this);
        setOverScrollMode(OVER_SCROLL_NEVER);

        HorizontalDividerItemDecoration horDivider = new HorizontalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        VerticalDividerItemDecoration verDivider = new VerticalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        setLayoutManager(new GridLayoutManager(getContext(), 1));

        addItemDecoration(horDivider);
        addItemDecoration(verDivider);

        mAdapter = new AdtRecharge(getContext());
        setVerticalScrollBarEnabled(false);
        setAdapter(mAdapter);
    }
    public void setRecharge(List<ReCharge.RootBean.DataBean> dataBeanList) {
        mAdapter.setData(dataBeanList);
    }

    public void loadRecharge(int page, Map<String, String> map) {
        requestRecharges(page, map);
    }

    private void requestRecharges(int page, Map<String, String> map) {
        rechargePresenter.getRechargeList("SKThd2019","北站站","2019-01-01","2019-03-11",0,"",2,10);
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
    public void OnRequestData(ReCharge reCharge) {
          if(reCharge!=null&&reCharge.getRoot()!=null&&reCharge.getRoot().getData()!=null){
              List<ReCharge.RootBean.DataBean> dataBeanList=reCharge.getRoot().getData();
              mAdapter.setData(dataBeanList);
              mAdapter.notifyDataSetChanged();
          }
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
