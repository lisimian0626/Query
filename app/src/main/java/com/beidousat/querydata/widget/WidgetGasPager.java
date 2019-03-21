package com.beidousat.querydata.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.Gas;
import com.beidousat.widgetlibs.viewpager.Flip3DTransform;
import com.beidousat.widgetlibs.viewpager.TransformUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by J Wong on 2015/12/18 08:42.
 */
public class WidgetGasPager extends WidgetBasePager {


    private GasPagerAdapter mAdapter;
    private Map<String, String> mRequestParams = new HashMap<String, String>();
    private Map<Integer, List<Gas.RootBean.DataBean>> mIndexPage;
    private Gas.RootBean.SumBean sumBean;

    public WidgetGasPager(Context context) {
        super(context);
        TransformUtil.reverse(this, new Flip3DTransform());
    }

    public WidgetGasPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TransformUtil.reverse(this, new Flip3DTransform());
    }


    public void initPager(int totalPage, List<Gas.RootBean.DataBean> firstPageSinger, Map<String, String> requestParams) {
        mIndexPage = new HashMap<Integer, List<Gas.RootBean.DataBean>>();
        mIndexPage.put(1, firstPageSinger);
        mRequestParams = requestParams;
        mAdapter = new GasPagerAdapter(mContext, totalPage);
        setOffscreenPageLimit(3);
        setAdapter(mAdapter);
        runLayoutAnimation();
    }

    public Gas.RootBean.SumBean getSumBean() {
        return sumBean;
    }

    public void setSumBean(Gas.RootBean.SumBean sumBean) {
        this.sumBean = sumBean;
    }

    public void runLayoutAnimation() {
        if (mAdapter != null) {
            WidgetGasPage view = mAdapter.getCurrentView();
            if (view != null)
                view.runLayoutAnimation();
        }
    }


    private class GasPagerAdapter extends PagerAdapter {

        private Context mContext;
        private SparseArray<WidgetGasPage> sparseArray = new SparseArray<WidgetGasPage>();
        private int mPageCount;


        public GasPagerAdapter(Context context, int pageCount) {
            mContext = context;
            this.mPageCount = pageCount;
        }

        public WidgetGasPage getCurrentView() {
            return sparseArray.get(WidgetGasPager.this.getCurrentItem());
        }


        @Override
        public int getCount() {
            return mPageCount;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            WidgetGasPage page;
            if (sparseArray.get(position) == null) {
                page = new WidgetGasPage(mContext);
                page.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                if (mIndexPage.containsKey(position + 1)) {
                    page.setGasList(mIndexPage.get(position + 1),sumBean);
                }
                else {
                    page.loadGas(position + 1, mRequestParams);
                }
                sparseArray.put(position, page);
            } else {
                page = sparseArray.get(position);
            }
            ViewGroup parent = (ViewGroup) page.getParent();
            if (parent != null) {
                parent.removeAllViews();
            }
            container.addView(page);
            return page;
        }

        @Override
        public void setPrimaryItem(ViewGroup container, int position, Object object) {
            super.setPrimaryItem(container, position, object);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            WidgetGasPage view = (WidgetGasPage) object;
            container.removeView(view);
//            sparseArray.put(position, view);
            sparseArray.delete(position);
        }
    }
}