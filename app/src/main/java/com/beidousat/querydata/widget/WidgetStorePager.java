package com.beidousat.querydata.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.Store;
import com.beidousat.widgetlibs.viewpager.Flip3DTransform;
import com.beidousat.widgetlibs.viewpager.TransformUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by J Wong on 2015/12/18 08:42.
 */
public class WidgetStorePager extends WidgetBasePager {


    private StorePagerAdapter mAdapter;
    private Map<String, String> mRequestParams = new HashMap<String, String>();
    private Map<Integer, List<Store.RootBean.DataBean>> mIndexPage;

    public WidgetStorePager(Context context) {
        super(context);
        TransformUtil.reverse(this, new Flip3DTransform());
    }

    public WidgetStorePager(Context context, AttributeSet attrs) {
        super(context, attrs);
        TransformUtil.reverse(this, new Flip3DTransform());
    }


    public void initPager(int totalPage, List<Store.RootBean.DataBean> firstPageSinger, Map<String, String> requestParams) {
        mIndexPage = new HashMap<Integer, List<Store.RootBean.DataBean>>();
        mIndexPage.put(1, firstPageSinger);
        mRequestParams = requestParams;
        mAdapter = new StorePagerAdapter(mContext, totalPage);
        setOffscreenPageLimit(3);
        setAdapter(mAdapter);
        runLayoutAnimation();
    }

    public void runLayoutAnimation() {
        if (mAdapter != null) {
            WidgetStorePage view = mAdapter.getCurrentView();
            if (view != null)
                view.runLayoutAnimation();
        }
    }


    private class StorePagerAdapter extends PagerAdapter {

        private Context mContext;
        private SparseArray<WidgetStorePage> sparseArray = new SparseArray<>();
        private int mPageCount;


        public StorePagerAdapter(Context context, int pageCount) {
            mContext = context;
            this.mPageCount = pageCount;
        }

        public WidgetStorePage getCurrentView() {
            return sparseArray.get(WidgetStorePager.this.getCurrentItem());
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
            WidgetStorePage page;
            if (sparseArray.get(position) == null) {
                page = new WidgetStorePage(mContext);
                page.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                if (mIndexPage.containsKey(position + 1)) {
                    page.setStore(mIndexPage.get(position + 1));
                }
                else {
                    page.loadStore(position + 1, mRequestParams);
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
            WidgetStorePage view = (WidgetStorePage) object;
            container.removeView(view);
//            sparseArray.put(position, view);
            sparseArray.delete(position);
        }
    }
}