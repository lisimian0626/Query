package com.beidousat.querydata.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.widget.Toast;

import com.beidousat.querydata.R;
import com.beidousat.querydata.adapter.AdtCash;
import com.beidousat.querydata.adapter.AdtRecharge;
import com.beidousat.querydata.buss.CashConstract;
import com.beidousat.querydata.buss.CashPresenter;
import com.beidousat.querydata.buss.RechargeConstract;
import com.beidousat.querydata.buss.RechargePresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.ReCharge;
import com.beidousat.widgetlibs.recycler.GridRecyclerView;
import com.beidousat.widgetlibs.recycler.HorizontalDividerItemDecoration;
import com.beidousat.widgetlibs.recycler.VerticalDividerItemDecoration;

import java.util.List;
import java.util.Map;


/**
 * Created by J Wong on 2015/12/17 18:01.
 */
public class WidgetCashPage extends GridRecyclerView  implements CashConstract.View{

    private AdtCash mAdapter;
    private CashPresenter cashPresenter;
    public WidgetCashPage(Context context) {
        super(context);
        init();
    }

    public WidgetCashPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WidgetCashPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

//        private AdtSinger mAdapter;

    private void init() {
        cashPresenter=new CashPresenter(this);
        setOverScrollMode(OVER_SCROLL_NEVER);

        HorizontalDividerItemDecoration horDivider = new HorizontalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        VerticalDividerItemDecoration verDivider = new VerticalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        setLayoutManager(new GridLayoutManager(getContext(), 1));

        addItemDecoration(horDivider);
        addItemDecoration(verDivider);

        mAdapter = new AdtCash(getContext());
        setVerticalScrollBarEnabled(false);
        setAdapter(mAdapter);
    }
    public void setCash(List<Cash.RootBean.DataBean> dataBeanList, Cash.RootBean.SumBean sumBean) {
        mAdapter.setData(dataBeanList);
        mAdapter.setSum(sumBean);
    }

    public void loadCash(int page, Map<String, String> map) {
        requestCash(page, map);
    }

    private void requestCash(int page, Map<String, String> map) {
        cashPresenter.getCashList(map,page, Constant.per_pager);
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
    public void OnRequestData(Cash cash) {
        if(cash!=null&&cash.getRoot()!=null&&cash.getRoot().getData()!=null){
            List<Cash.RootBean.DataBean> dataBeanList=cash.getRoot().getData();
            mAdapter.setData(dataBeanList);
            if(cash.getRoot().getSum()!=null){
                mAdapter.setSum(cash.getRoot().getSum());
            }
            mAdapter.notifyDataSetChanged();
        }
    }

}
