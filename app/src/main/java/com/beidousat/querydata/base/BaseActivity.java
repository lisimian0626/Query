package com.beidousat.querydata.base;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.beidousat.querydata.R;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public abstract class BaseActivity extends AppCompatActivity {
    private String Tag = "BaseActivity";
    private ScheduledExecutorService mScheduledExecutorService;
    protected Dialog mProgressDlg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getContentView() != View.NO_ID) {
            setContentView(getContentView());
        }
//        EventBusHelper.register(this);
        initViews();
        setupToolbar();
        setListener();
        initData();
//        startScreenTimer();
    }

    public abstract @LayoutRes
    int getContentView();

    /**
     * 初始化视图
     */
    public abstract void initViews();

    /**
     * 设置监听器
     */
    public abstract void setListener();

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 加载数据(一般用于加载网络数据)
     */
    public abstract void loadDataWhenOnResume();

    /**
     * 取消loading时的操作
     */
    public abstract void cancelLoadingRequest();

    public void setupToolbar() {

    }

    public void showNormalDialog(String string) {
        if(mProgressDlg!=null)
            return;
        mProgressDlg = MyPorgressDialog.createLoadingDialog(this,string);
        mProgressDlg.setCancelable(true);
        mProgressDlg.show();

    }

    public void closeNormalDialog() {
        if (mProgressDlg != null) {
            mProgressDlg.dismiss();
        }
    }
    protected void startScreenTimer() {
        if (mScheduledExecutorService != null && !mScheduledExecutorService.isShutdown())
            return;
        mScheduledExecutorService = java.util.concurrent.Executors.newScheduledThreadPool(1);
        scheduleAtFixedRate(mScheduledExecutorService);

    }

    private void stopScreenTimer() {
        if (mScheduledExecutorService != null) {
            mScheduledExecutorService.shutdownNow();
            mScheduledExecutorService = null;
        }
    }

    private void scheduleAtFixedRate(ScheduledExecutorService service) {
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

            }
        }, 1, 1, TimeUnit.SECONDS);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopScreenTimer();
//        EventBusHelper.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataWhenOnResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        cancelLoadingRequest();
    }
}
