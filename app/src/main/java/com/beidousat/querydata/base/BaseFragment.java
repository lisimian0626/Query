package com.beidousat.querydata.base;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnAttachStateChangeListener;
import android.view.View.OnClickListener;

import com.beidousat.querydata.net.HolderViewStateHelper;
import com.beidousat.querydata.net.ILoadingView;
import com.beidousat.querydata.net.INetworkListener;
import com.beidousat.querydata.evenbus.NetworkEventSubscriber;
import com.beidousat.querydata.net.StateEntity;
import com.beidousat.querydata.net.ViewState;
import com.beidousat.querydata.widget.CommonStateView;


public abstract class BaseFragment extends Fragment implements ILoadingView, INetworkListener {
    protected View mRootView;
    protected BaseActivity mAttachedAct;
    protected NetworkEventSubscriber mEventSubscriber;
    private StateEntity mStateEntity;
    private CommonStateView mCommonView;
    private boolean isCommonStateViewAttached;
    private boolean isFirstLoading = true;

    public BaseFragment() {
    }

    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.adapterStatusBar();
        if (this.mRootView == null) {
            this.mRootView = inflater.inflate(this.getContentView(), container, false);
            this.initViews();
            this.setListeners();
            this.initData();
        }

        ViewGroup parent = (ViewGroup)this.mRootView.getParent();
        if (parent != null) {
            parent.removeView(this.mRootView);
        }

        return this.mRootView;
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.onInit();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mAttachedAct = (BaseActivity)context;
    }

    public void onDetach() {
        super.onDetach();
        this.mAttachedAct = null;
    }

    public void onResume() {
        super.onResume();
        this.loadDataWhenOnResume();
        this.mEventSubscriber.onResume();
    }

    public void onPause() {
        super.onPause();
        this.cancelRequestWhenOnPause();
        this.mEventSubscriber.onPause();
    }

    public void onDestroyView() {
        super.onDestroyView();
        this.mEventSubscriber.onDestroy();
    }

    private void onInit() {
        this.onInitNetworkSubscribe();
        this.mEventSubscriber.onCreate();
        this.initCommonStateView();
    }

    private void initCommonStateView() {
        try {
            this.mStateEntity = HolderViewStateHelper.parseHolderViewState(this.getContext(), (ViewGroup)this.mRootView, this.getClass());
            if (this.mStateEntity.getHolderId() != -1) {
                this.mCommonView = new CommonStateView(this.getContext());
                this.mStateEntity.setStateView(this.mCommonView);
                this.mCommonView.addOnAttachStateChangeListener(new OnAttachStateChangeListener() {
                    public void onViewAttachedToWindow(View v) {
                        BaseFragment.this.isCommonStateViewAttached = true;
                    }

                    public void onViewDetachedFromWindow(View v) {
                        BaseFragment.this.isCommonStateViewAttached = false;
                    }
                });
                this.mCommonView.setOnRetryListener(new OnClickListener() {
                    public void onClick(View v) {
                        BaseFragment.this.onNetWorkRetry();
                    }
                });
            }
        } catch (Exception var2) {
            Log.e("HolderViewState", var2.toString());
        }

    }

    private void setGoneViews(ViewState state, boolean gone) {
        if (this.mStateEntity.getGoneViewMap().containsKey(state)) {
            int[] var3 = (int[])this.mStateEntity.getGoneViewMap().get(state);
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                int id = var3[var5];
                this.mRootView.findViewById(id).setVisibility(gone ? View.GONE : View.VISIBLE);
            }
        }

    }

    public void onInitNetworkSubscribe() {
        this.mEventSubscriber = new NetworkEventSubscriber(false, this);
    }

    public void showLoading(String msg) {
        if (this.mStateEntity.getHolderView() != null) {
            this.mCommonView.showLoading();
            if (this.isFirstLoading) {
                if (this.mCommonView.getParent() == null) {
                    ((ViewGroup)this.mStateEntity.getHolderView().getParent()).addView(this.mCommonView, this.mStateEntity.getHolderViewIndex(), this.mStateEntity.getHolderLayoutParams());
                }

                if (this.mStateEntity.getHolderView().getParent() != null) {
                    ((ViewGroup)this.mStateEntity.getHolderView().getParent()).removeView(this.mStateEntity.getHolderView());
                }
            }
        }

        this.isFirstLoading = false;
    }

    public void hideLoading() {
        if (this.mStateEntity.getHolderView() != null) {
            this.mCommonView.hideLoading();
            if (this.mStateEntity.getHolderView().getParent() == null && this.mCommonView.hasNoneState()) {
                ((ViewGroup)this.mCommonView.getParent()).addView(this.mStateEntity.getHolderView(), this.mStateEntity.getHolderViewIndex(), this.mStateEntity.getHolderLayoutParams());
            }

            if (this.mCommonView.getParent() != null && this.mCommonView.hasNoneState()) {
                ((ViewGroup)this.mCommonView.getParent()).removeView(this.mCommonView);
            }
        }

    }

    public void showNetWorkError(boolean isNetworkOk, int code) {
        if (!isNetworkOk && this.mStateEntity.getHolderView() != null) {
            this.setGoneViews(ViewState.NETWORK_ERROR, true);
            this.mCommonView.setupNetworkView(isNetworkOk, code);
            if (this.mCommonView.getParent() == null) {
                ((ViewGroup)this.mStateEntity.getHolderView().getParent()).addView(this.mCommonView, this.mStateEntity.getHolderViewIndex(), this.mStateEntity.getHolderLayoutParams());
            }

            if (this.mStateEntity.getHolderView().getParent() != null) {
                ((ViewGroup)this.mStateEntity.getHolderView().getParent()).removeView(this.mStateEntity.getHolderView());
            }
        } else if (isNetworkOk && this.mStateEntity.getHolderView() != null) {
            this.setGoneViews(ViewState.NETWORK_ERROR, false);
            this.mCommonView.setupNetworkView(isNetworkOk, code);
            if (this.mStateEntity.getHolderView().getParent() == null) {
                ((ViewGroup)this.mCommonView.getParent()).addView(this.mStateEntity.getHolderView(), this.mStateEntity.getHolderViewIndex(), this.mStateEntity.getHolderLayoutParams());
            }

            if (this.mCommonView.getParent() != null && this.mCommonView.hasNoneState()) {
                ((ViewGroup)this.mCommonView.getParent()).removeView(this.mCommonView);
            }
        }

    }

    public void onNetWorkRetry() {
    }

    public View getRootView() {
        return this.mRootView;
    }

    @LayoutRes
    public abstract int getContentView();

    public abstract void initViews();

    public abstract void setListeners();

    public abstract void initData();

    public abstract void loadDataWhenOnResume();

    public void cancelLoading(boolean cancelRequest) {
        this.hideLoading();
        if (cancelRequest) {
            this.cancelRequestWhenOnPause();
        }

    }

    public void adapterStatusBar() {
    }

    public abstract void cancelRequestWhenOnPause();
}

