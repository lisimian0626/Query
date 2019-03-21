package com.beidousat.querydata.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.widget.Toast;

import com.beidousat.querydata.R;
import com.beidousat.querydata.adapter.AdtCash;
import com.beidousat.querydata.adapter.AdtGas;
import com.beidousat.querydata.buss.CashConstract;
import com.beidousat.querydata.buss.CashPresenter;
import com.beidousat.querydata.buss.GasConstract;
import com.beidousat.querydata.buss.GasPresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.Gas;
import com.beidousat.widgetlibs.recycler.GridRecyclerView;
import com.beidousat.widgetlibs.recycler.HorizontalDividerItemDecoration;
import com.beidousat.widgetlibs.recycler.VerticalDividerItemDecoration;

import java.util.List;
import java.util.Map;


/**
 * Created by J Wong on 2015/12/17 18:01.
 */
public class WidgetGasPage extends GridRecyclerView  implements GasConstract.View{

    private AdtGas mAdapter;
    private GasPresenter gasPresenter;
    public WidgetGasPage(Context context) {
        super(context);
        init();
    }

    public WidgetGasPage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WidgetGasPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

//        private AdtSinger mAdapter;

    private void init() {
        gasPresenter=new GasPresenter(this);
        setOverScrollMode(OVER_SCROLL_NEVER);

        HorizontalDividerItemDecoration horDivider = new HorizontalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        VerticalDividerItemDecoration verDivider = new VerticalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        setLayoutManager(new GridLayoutManager(getContext(), 1));

        addItemDecoration(horDivider);
        addItemDecoration(verDivider);

        mAdapter = new AdtGas(getContext());
        setVerticalScrollBarEnabled(false);
        setAdapter(mAdapter);
    }
    public void setGasList(List<Gas.RootBean.DataBean> dataBeanList, Gas.RootBean.SumBean sumBean) {
        mAdapter.setData(dataBeanList);
        mAdapter.setSum(sumBean);
    }

    public void loadGas(int page, Map<String, String> map) {
        requestGas(page, map);
    }

    private void requestGas(int page, Map<String, String> map) {
        gasPresenter.getGasList(map,page, Constant.per_pager);
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
    public void OnRequestData(Gas gas) {
        if(gas!=null&&gas.getRoot()!=null&&gas.getRoot().getData()!=null){
            List<Gas.RootBean.DataBean> dataBeanList=gas.getRoot().getData();
            mAdapter.setData(dataBeanList);
            if(gas.getRoot().getSum()!=null){
                mAdapter.setSum(gas.getRoot().getSum());
            }
            mAdapter.notifyDataSetChanged();
        }
    }
}
