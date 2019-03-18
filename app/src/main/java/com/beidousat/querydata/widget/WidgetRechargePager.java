package com.beidousat.querydata.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.beidousat.querydata.model.ReCharge;
import com.beidousat.widgetlibs.viewpager.Flip3DTransform;
import com.beidousat.widgetlibs.viewpager.TransformUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by J Wong on 2015/12/18 08:42.
 */
public class WidgetRechargePager extends WidgetBasePager {


    private RechargePagerAdapter mAdapter;
    private Map<String, String> mRequestParams = new HashMap<String, String>();
    private Map<Integer, List<ReCharge.RootBean.DataBean>> mIndexPage;
    private ReCharge.RootBean.SumBean sumBean;

    public WidgetRechargePager(Context context) {
        super(context);
        TransformUtil.reverse(this, new Flip3DTransform());
    }

    public WidgetRechargePager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TransformUtil.reverse(this, new Flip3DTransform());
    }


    public void initPager(int totalPage, List<ReCharge.RootBean.DataBean> firstPageSinger, Map<String, String> requestParams) {
        mIndexPage = new HashMap<Integer, List<ReCharge.RootBean.DataBean>>();
        mIndexPage.put(1, firstPageSinger);
        mRequestParams = requestParams;
        mAdapter = new RechargePagerAdapter(mContext, totalPage);
        setOffscreenPageLimit(3);
        setAdapter(mAdapter);
        runLayoutAnimation();
    }

    public ReCharge.RootBean.SumBean getSumBean() {
        return sumBean;
    }

    public void setSumBean(ReCharge.RootBean.SumBean sumBean) {
        this.sumBean = sumBean;
    }

    public void runLayoutAnimation() {
        if (mAdapter != null) {
            WidgetRechargePage view = mAdapter.getCurrentView();
            if (view != null)
                view.runLayoutAnimation();
        }
    }


    private class RechargePagerAdapter extends PagerAdapter {

        private Context mContext;
        private SparseArray<WidgetRechargePage> sparseArray = new SparseArray<WidgetRechargePage>();
        private int mPageCount;


        public RechargePagerAdapter(Context context, int pageCount) {
            mContext = context;
            this.mPageCount = pageCount;
        }

        public WidgetRechargePage getCurrentView() {
            return sparseArray.get(WidgetRechargePager.this.getCurrentItem());
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
            WidgetRechargePage page;
            if (sparseArray.get(position) == null) {
                page = new WidgetRechargePage(mContext);
                page.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                if (mIndexPage.containsKey(position + 1)) {
                    page.setRecharge(mIndexPage.get(position + 1),sumBean);
                }
                else {
                    page.loadRecharge(position + 1, mRequestParams);
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
            WidgetRechargePage view = (WidgetRechargePage) object;
            container.removeView(view);
//            sparseArray.put(position, view);
            sparseArray.delete(position);
        }
    }
}