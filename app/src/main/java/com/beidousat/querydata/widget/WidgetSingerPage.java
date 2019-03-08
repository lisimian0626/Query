package com.beidousat.querydata.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;

import com.beidousat.widgetlibs.recycler.GridRecyclerView;
import com.beidousat.widgetlibs.recycler.HorizontalDividerItemDecoration;
import com.beidousat.widgetlibs.recycler.VerticalDividerItemDecoration;


/**
 * Created by J Wong on 2015/12/17 18:01.
 */
public class WidgetSingerPage extends GridRecyclerView  {
    public WidgetSingerPage(Context context) {
        super(context);
    }

    public WidgetSingerPage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public WidgetSingerPage(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

//        private AdtSinger mAdapter;

    private void init() {
        setOverScrollMode(OVER_SCROLL_NEVER);

        HorizontalDividerItemDecoration horDivider = new HorizontalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        VerticalDividerItemDecoration verDivider = new VerticalDividerItemDecoration.Builder(getContext())
                .color(Color.TRANSPARENT).size(1).build();

        setLayoutManager(new GridLayoutManager(getContext(), 1));

        addItemDecoration(horDivider);
        addItemDecoration(verDivider);

//        mAdapter = new AdtSinger(getContext());
//        mAdapter.setOnSingerClickListener(this);
//        setVerticalScrollBarEnabled(false);
//        setAdapter(mAdapter);
    }

    public void runLayoutAnimation() {
//        if (mAdapter != null) {
//            final LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(getContext(), R.anim.grid_layout_animation_from_bottom);
//            setLayoutAnimation(controller);
            getAdapter().notifyDataSetChanged();
//            scheduleLayoutAnimation();
//        }
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
