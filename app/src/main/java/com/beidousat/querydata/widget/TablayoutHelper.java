package com.beidousat.querydata.widget;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.view.View;
import android.widget.LinearLayout;

import com.beidousat.querydata.utils.ScreenUtil;

import java.lang.reflect.Field;


/**
 * Created by Administrator on 2017/10/20.
 */

public  class TablayoutHelper {
    public static void setTabLine(TabLayout tab, int left, int right, Context context){
        try {
            ScreenUtil.init(context);
            Class<?> tablayout = tab.getClass();
            Field tabStrip;
            tabStrip = tablayout.getDeclaredField("mTabStrip");
            tabStrip.setAccessible(true);
            LinearLayout ll_tab= (LinearLayout) tabStrip.get(tab);
            for (int i = 0; i < ll_tab.getChildCount(); i++) {
                View child = ll_tab.getChildAt(i);
                child.setPadding(0,0,0,0);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);
                //修改两个tab的间距
                params.setMarginStart(ScreenUtil.dip2px(left));
                params.setMarginEnd(ScreenUtil.dip2px(left));
                child.setLayoutParams(params);
                child.invalidate();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
