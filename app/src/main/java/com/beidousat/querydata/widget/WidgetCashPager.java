package com.beidousat.querydata.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.ReCharge;
import com.beidousat.widgetlibs.viewpager.Flip3DTransform;
import com.beidousat.widgetlibs.viewpager.TransformUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by J Wong on 2015/12/18 08:42.
 */
public class WidgetCashPager extends WidgetBasePager {


    private CashPagerAdapter mAdapter;
    private Map<String, String> mRequestParams = new HashMap<String, String>();
    private Map<Integer, List<Cash.RootBean.DataBean>> mIndexPage;
    private Cash.RootBean.SumBean sumBean;

    public WidgetCashPager(Context context) {
        super(context);
        TransformUtil.reverse(this, new Flip3DTransform());
    }

    public WidgetCashPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TransformUtil.reverse(this, new Flip3DTransform());
    }


    public void initPager(int totalPage, List<Cash.RootBean.DataBean> firstPageSinger, Map<String, String> requestParams) {
        mIndexPage = new HashMap<Integer, List<Cash.RootBean.DataBean>>();
        mIndexPage.put(1, firstPageSinger);
        mRequestParams = requestParams;
        mAdapter = new CashPagerAdapter(mContext, totalPage);
        setOffscreenPageLimit(3);
        setAdapter(mAdapter);
        runLayoutAnimation();
    }

    public Cash.RootBean.SumBean getSumBean() {
        return sumBean;
    }

    public void setSumBean(Cash.RootBean.SumBean sumBean) {
        this.sumBean = sumBean;
    }

    public void runLayoutAnimation() {
        if (mAdapter != null) {
            WidgetCashPage view = mAdapter.getCurrentView();
            if (view != null)
                view.runLayoutAnimation();
        }
    }


    private class CashPagerAdapter extends PagerAdapter {

        private Context mContext;
        private SparseArray<WidgetCashPage> sparseArray = new SparseArray<WidgetCashPage>();
        private int mPageCount;


        public CashPagerAdapter(Context context, int pageCount) {
            mContext = context;
            this.mPageCount = pageCount;
        }

        public WidgetCashPage getCurrentView() {
            return sparseArray.get(WidgetCashPager.this.getCurrentItem());
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
            WidgetCashPage page;
            if (sparseArray.get(position) == null) {
                page = new WidgetCashPage(mContext);
                page.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                if (mIndexPage.containsKey(position + 1)) {
                    page.setCash(mIndexPage.get(position + 1),sumBean);
                }
                else {
                    page.loadCash(position + 1, mRequestParams);
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
            WidgetCashPage view = (WidgetCashPage) object;
            container.removeView(view);
//            sparseArray.put(position, view);
            sparseArray.delete(position);
        }
    }
}