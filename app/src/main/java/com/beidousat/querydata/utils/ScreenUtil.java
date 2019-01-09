package com.beidousat.querydata.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by ckr on 2016/6/28.
 */
public class ScreenUtil {

    // 界面相关数值
    public static float m_Scale;
    private static int m_ScreenWidth;
    private static int m_ScreenHeight;
    public static final int STANDARD_WIDTH = 1920;
    private static final int STANDARD_HIGHT = 1080;

    public static int[] size;
    public static int padding; // 界面中控件内部的留白

    static Context context;
    static Resources resources;

    public static void init(Context context2) {
        context = context2;
        resources = context.getResources();
        initScreen();
        size = new int[STANDARD_WIDTH];
    }

    private static void initScreen() {
        WindowManager manager = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(dm);
        m_ScreenWidth = dm.widthPixels;
        m_ScreenHeight = dm.heightPixels;

        if (m_ScreenWidth > m_ScreenHeight) {
            // 保证屏幕宽度小于高度
            int tmp = m_ScreenWidth;
            m_ScreenWidth = m_ScreenHeight;
            m_ScreenHeight = tmp;
        }
        m_Scale = context.getResources().getDisplayMetrics().density;
        Log.e("chen", "width=" + m_ScreenWidth + "=density=" + m_Scale);
    }

    //dp与px之间转换----------------------------------
    public static int px2dip(float pxValue) {
        return (int) (pxValue / m_Scale + 0.5f);
    }

    public static int dip2px(float dpValue) {
        return (int) (dpValue * m_Scale + 0.5f);
    }

    public static final int pix(int px) {
        if (px >= STANDARD_WIDTH)
            return px * m_ScreenWidth / STANDARD_WIDTH;
        else {
            if (size[px] != 0)
                return size[px];
            else
                return size[px] = px * m_ScreenWidth / STANDARD_WIDTH;
        }
    }

    public static final int pix_Height(int px, int height) {
        return px * height / STANDARD_HIGHT;
    }

    public static int getScreenWidth(Context context) {
        if (m_ScreenWidth == 0)
            init(context);
        return m_ScreenWidth;
    }

    public static int getScreenHeight(Context context) {
        if (m_ScreenHeight == 0)
            init(context);
        return m_ScreenHeight;
    }

    //动态设置背景的圆角和边框----------------------------------
    public static void drawBg(View view, int color, float r0, float r1,
                              float r2, float r3, int strokeColor, int strokeSize) {
        GradientDrawable d;
        d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadii(new float[]{r0, r0, r1, r1, r2, r2, r3, r3});
        if (strokeColor != Color.TRANSPARENT)
            d.setStroke(strokeSize, strokeColor);
        Canvas canvas = new Canvas();
        d.draw(canvas);
        view.setBackgroundDrawable(d);
    }

    //给view设置宽高、内外边距----------------------------------
    public static void setViewSize(View v, int w, int h) {
        Log.e("chen", "width=" + w);
        ViewGroup.LayoutParams lpm = v.getLayoutParams();
        if (w != 0)
            lpm.width = w;
        lpm.height = h;
        v.setLayoutParams(lpm);
    }

    public static void setViewMargin(View v, int left, int top, int right,
                                     int bottom) {
        ViewGroup.MarginLayoutParams lpm = (ViewGroup.MarginLayoutParams) v
                .getLayoutParams();
        lpm.leftMargin = left;
        lpm.rightMargin = right;
        lpm.topMargin = top;
        lpm.bottomMargin = bottom;
        v.setLayoutParams(lpm);
    }

    public static void setViewPadding(View v, int left, int top, int right,
                                      int bottom) {
        v.setPadding(left, top, right, bottom);
    }

    public static void setMinHeight(View v, int height) {
        v.setMinimumHeight(height);
    }

    //给textview设置字体大小----------------------------------
    public static void setTextSize(TextView v, float size) {
        v.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    public static void setTextSize(Button v, float size) {
        v.setTextSize(TypedValue.COMPLEX_UNIT_PX, size);
    }

    /**
     * 获得资源
     */
    public static Resources getResources() {
        return context.getResources();
    }

    /**
     * 获得上下文
     *
     * @return
     */
    public static Context getContext() {
        return context;
    }

    /**
     * findview
     *
     * @param view
     * @param viewName
     * @param <T>
     * @return
     */
    public static <T extends View> T findViewByName(View view, String viewName) {
        int id = getResources().getIdentifier(viewName, "id", getContext().getPackageName());
        T v = (T) view.findViewById(id);
        return v;
    }

    /**
     * findview
     *
     * @param activity
     * @param viewName
     * @param <T>
     * @return
     */
    public static <T extends View> T findViewByName(Activity activity, String viewName) {
        if (activity == null) {
            return null;
        }
        int id = getResources().getIdentifier(viewName, "id", getContext().getPackageName());
        T v = (T) activity.findViewById(id);
        return v;
    }

    /**
     * 根据lauout名字获得id
     *
     * @param layoutName
     * @return
     */
    public static int findLayout(String layoutName) {
        int id = getResources().getIdentifier(layoutName, "layout", getContext().getPackageName());
        return id;
    }

}
