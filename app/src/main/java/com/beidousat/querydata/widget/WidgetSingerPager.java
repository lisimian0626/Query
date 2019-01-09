//package com.beidousat.querydata.widget;
//
//import android.content.Context;
//import android.support.v4.view.PagerAdapter;
//import android.util.AttributeSet;
//import android.util.SparseArray;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.LinearLayout;
//
//import com.beidousat.bnslibs.model.vod.Singer;
//import com.beidousat.widgetlibs.viewpager.Flip3DTransform;
//import com.beidousat.widgetlibs.viewpager.TransformUtil;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * Created by J Wong on 2015/12/18 08:42.
// */
//public class WidgetSingerPager extends WidgetBasePager {
//
//
//    private SingerPagerAdapter mAdapter;
//    private Map<String, String> mRequestParams = new HashMap<String, String>();
//    private Map<Integer, List<Singer>> mIndexPage;
//
//
//    public WidgetSingerPager(Context context) {
//        super(context);
//        TransformUtil.reverse(this, new Flip3DTransform());
//    }
//
//    public WidgetSingerPager(Context context, AttributeSet attrs) {
//        super(context, attrs);
//        TransformUtil.reverse(this, new Flip3DTransform());
//    }
//
//
//    public void initPager(int totalPage, List<Singer> firstPageSinger, Map<String, String> requestParams) {
//        mIndexPage = new HashMap<Integer, List<Singer>>();
//        mIndexPage.put(1, firstPageSinger);
//        mRequestParams = requestParams;
//        mAdapter = new SingerPagerAdapter(mContext, totalPage);
//        setOffscreenPageLimit(3);
//        setAdapter(mAdapter);
//        runLayoutAnimation();
//    }
//
//    public void runLayoutAnimation() {
//        if (mAdapter != null) {
//            WidgetSingerPage view = mAdapter.getCurrentView();
//            if (view != null)
//                view.runLayoutAnimation();
//        }
//    }
//
//
//    private class SingerPagerAdapter extends PagerAdapter {
//
//        private Context mContext;
//        private SparseArray<WidgetSingerPage> sparseArray = new SparseArray<WidgetSingerPage>();
//        private int mPageCount;
//
//
//        public SingerPagerAdapter(Context context, int pageCount) {
//            mContext = context;
//            this.mPageCount = pageCount;
//        }
//
//        public WidgetSingerPage getCurrentView() {
//            return sparseArray.get(WidgetSingerPager.this.getCurrentItem());
//        }
//
//
//        @Override
//        public int getCount() {
//            return mPageCount;
//        }
//
//        @Override
//        public boolean isViewFromObject(View view, Object obj) {
//            return view == obj;
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, final int position) {
//            WidgetSingerPage page;
//            if (sparseArray.get(position) == null) {
//                page = new WidgetSingerPage(mContext);
//                page.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
//                if (mIndexPage.containsKey(position + 1)) {
//                    page.setSinger(mIndexPage.get(position + 1));
//                } else {
//                    page.loadSinger(position + 1, mRequestParams);
//                }
//                sparseArray.put(position, page);
//            } else {
//                page = sparseArray.get(position);
//            }
//            ViewGroup parent = (ViewGroup) page.getParent();
//            if (parent != null) {
//                parent.removeAllViews();
//            }
//            container.addView(page);
//            return page;
//        }
//
//        @Override
//        public void setPrimaryItem(ViewGroup container, int position, Object object) {
//            super.setPrimaryItem(container, position, object);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            WidgetSingerPage view = (WidgetSingerPage) object;
//            container.removeView(view);
////            sparseArray.put(position, view);
//            sparseArray.delete(position);
//        }
//    }
//}