package com.beidousat.querydata.base;

/**
 * author: Hanson
 * date:   2016/8/30
 * describe:
 */
public interface BaseView<T> {
    /**
     * 显示Loading视图
     * @param msg
     */
    void showLoading(String msg);

    /**
     * 隐藏Loading视图
     */
    void hideLoading();
    void onFeedBack(boolean success, String key, Object data);
}
