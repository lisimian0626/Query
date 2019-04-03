package com.beidousat.querydata.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.widget.Toast;

import com.beidousat.querydata.R;
import com.beidousat.querydata.adapter.AdtCash;
import com.beidousat.querydata.adapter.AdtStore;
import com.beidousat.querydata.buss.CashConstract;
import com.beidousat.querydata.buss.CashPresenter;
import com.beidousat.querydata.buss.StoreConstract;
import com.beidousat.querydata.buss.StorePresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.Store;
import com.beidousat.widgetlibs.recycler.GridRecyclerView;
import com.beidousat.widgetlibs.recycler.HorizontalDividerItemDecoration;
import com.beidousat.widgetlibs.recycler.VerticalDividerItemDecoration;

import java.util.List;
import java.util.Map;


/**
 * Created by J Wong on 2015/12/17 18:01.
 */
public class WidgetStorePage extends GridRecyclerView  implements StoreConstract.View{

    private AdtStore mAdapter;
    private StorePresenter storePresenter;
    public WidgetStorePage(Context context) {
        super(context);
        init();
    }

    public WidgetStorePage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WidgetStorePage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

//        private AdtSinger mAdapter;

    private void init() {
        storePresenter=new StorePresenter(this);
        setOverScrollMode(OVER_SCROLL_NEVER);

        HorizontalDividerItemDecoration horDivider = new HorizontalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        VerticalDividerItemDecoration verDivider = new VerticalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        setLayoutManager(new GridLayoutManager(getContext(), 1));

        addItemDecoration(horDivider);
        addItemDecoration(verDivider);

        mAdapter = new AdtStore(getContext());
        setVerticalScrollBarEnabled(false);
        setAdapter(mAdapter);
    }
    public void setStore(List<Store.RootBean.DataBean> dataBeanList) {
        mAdapter.setData(dataBeanList);
    }

    public void loadStore(int page, Map<String, String> map) {
        requestStore(page, map);
    }

    private void requestStore(int page, Map<String, String> map) {
        storePresenter.getStoreList(map,page, Constant.per_pager);
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
    public void OnRequestData(Store store) {
        if(store!=null&&store.getRoot()!=null&&store.getRoot().getData()!=null){
            List<Store.RootBean.DataBean> dataBeanList=store.getRoot().getData();
            mAdapter.setData(dataBeanList);
            mAdapter.notifyDataSetChanged();
        }
    }
}
