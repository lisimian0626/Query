package com.beidousat.querydata.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.beidousat.querydata.MainActivity;
import com.beidousat.querydata.R;
import com.beidousat.querydata.base.BaseActivity;
import com.beidousat.querydata.base.WidgetPage;
import com.beidousat.querydata.buss.RechargeConstract;
import com.beidousat.querydata.buss.RechargePresenter;
import com.beidousat.querydata.buss.StationConstract;
import com.beidousat.querydata.buss.StationPresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.common.GlobalDataUtil;
import com.beidousat.querydata.model.ReCharge;
import com.beidousat.querydata.model.SelectConfig;
import com.beidousat.querydata.model.Station;
import com.beidousat.querydata.utils.L;
import com.beidousat.querydata.utils.datepicker.CustomDatePicker;
import com.beidousat.querydata.utils.datepicker.DateFormatUtils;
import com.beidousat.querydata.utils.datepicker.DoubleDatePickerDialog;
import com.beidousat.querydata.widget.WidgetRechargePager;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RechargeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, StationConstract.View, RechargeConstract.View {
    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private WidgetPage mWidgetPage;
    private WidgetRechargePager mWidgetRechargePager;
    private ImageView icon_back;
    private TextView mTvStartTime, mTvEndTime, mTvTitle, query;
    private Spinner spinner;
    private CustomDatePicker mStartTimerPicker, mEndTimerPicker;
    private StationPresenter stationPresenter;
    private RechargePresenter rechargePresenter;
    private Map<String, String> requestParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stationPresenter = new StationPresenter(this);
        rechargePresenter = new RechargePresenter(this);

    }

    @Override
    public void setupToolbar() {
        super.setupToolbar();
    }

    @Override
    public int getContentView() {
        return R.layout.recharge_main;
    }

    @Override
    public void initViews() {
        mDrawerLayout = findViewById(R.id.layout_draw);
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.layout_navigation);
        mTvStartTime = findViewById(R.id.tv_selected_start_time);
        mTvEndTime = findViewById(R.id.tv_selected_endtime);
        mTvTitle = findViewById(R.id.recharge_title);
        icon_back = findViewById(R.id.recharge_ic_back);
        mWidgetRechargePager = findViewById(R.id.rechargePaper);
        mWidgetPage = findViewById(R.id.w_page);
        spinner = findViewById(R.id.recharge_spinner);
        query = findViewById(R.id.recharge_query);
        mTvTitle.setText("ALL");
        //设置toolbar
        SelectConfig selectConfig = new SelectConfig();
        selectConfig.setStationName("ALL");
        selectConfig.setSelect_text("ALL");
        GlobalDataUtil.getInstance().setSelectConfig(selectConfig);
        intiToolbar();
        initTimerPicker();
    }

    private void intiToolbar() {
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置左上角图标是否可点击
            actionBar.setHomeButtonEnabled(false);
            //左上角加上一个返回图标
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setTitle("");

        }
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(false);
//        mDrawerToggle.syncState();
//        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void setListener() {
        findViewById(R.id.ll_end_time).setOnClickListener(this);
        findViewById(R.id.ll_start_time).setOnClickListener(this);
        mTvTitle.setOnClickListener(this);
        icon_back.setOnClickListener(this);
        query.setOnClickListener(this);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                GlobalDataUtil.getInstance().getSelectConfig().setSelect_index(i);
                GlobalDataUtil.getInstance().getSelectConfig().setSelect_text(adapterView.getItemAtPosition(i).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void initData() {


    }

    @Override
    public void loadDataWhenOnResume() {
        stationPresenter.getStationList(Constant.Key);
        requestRecharge();
    }

    private void requestRecharge() {
        requestParams = new HashMap<>();
        requestParams.put("arg0", Constant.Key);
        requestParams.put("arg1", GlobalDataUtil.getInstance().getSelectConfig().getStationName());
        requestParams.put("arg2", GlobalDataUtil.getInstance().getSelectConfig().getStart_time());
        requestParams.put("arg3", GlobalDataUtil.getInstance().getSelectConfig().getEnd_time());
        requestParams.put("arg4", String.valueOf(GlobalDataUtil.getInstance().getSelectConfig().getSelect_index()));
        requestParams.put("arg5", String.valueOf(GlobalDataUtil.getInstance().getSelectConfig().getSelect_text()));
        rechargePresenter.getRechargeList(requestParams, 0, 10);
    }

    @Override
    public void cancelLoadingRequest() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        L.test("onNavigationItemSelected");
        String title = (String) menuItem.getTitle();
//        String mString = menuItem.getItemId().;
        mTvTitle.setText(title);
        GlobalDataUtil.getInstance().getSelectConfig().setStationName(title);
        mDrawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        mDrawerToggle.onOptionsItemSelected(item);
//        if (mDrawerToggle.onOptionsItemSelected(item)) {
//            return true;
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_start_time:
                mStartTimerPicker.show(mTvStartTime.getText().toString());
                break;
            case R.id.ll_end_time:
                mStartTimerPicker.show(mTvEndTime.getText().toString());
                break;
            case R.id.recharge_ic_back:
                finish();
                break;
            case R.id.recharge_title:
                toggle();
                break;
            case R.id.recharge_query:
                requestRecharge();
                break;
        }
    }

//    private DoubleDatePickerDialog initDoubleDate() {
//        Calendar c = Calendar.getInstance();
//        DoubleDatePickerDialog doubleDatePickerDialog = new DoubleDatePickerDialog(RechargeActivity.this, 0, new DoubleDatePickerDialog.OnDateSetListener() {
//
//            @Override
//            public void onDateSet(DatePicker startDatePicker, int startYear, int startMonthOfYear,
//                                  int startDayOfMonth, DatePicker endDatePicker, int endYear, int endMonthOfYear,
//                                  int endDayOfMonth) {
//
//            }
//        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE), false);
//        return doubleDatePickerDialog;
//    }

    private void initTimerPicker() {

        String beginTime = DateFormatUtils.getBeginTimeforCurrent(new Date(System.currentTimeMillis()));
        String startTime = DateFormatUtils.getStartTimeforCurrent(new Date(System.currentTimeMillis()));
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), false);

        mTvStartTime.setText(startTime);
        GlobalDataUtil.getInstance().getSelectConfig().setStart_time(startTime);
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mStartTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvStartTime.setText(DateFormatUtils.long2Str(timestamp, false));
                GlobalDataUtil.getInstance().getSelectConfig().setStart_time(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mStartTimerPicker.setCancelable(true);
        // 显示时和分
        mStartTimerPicker.setCanShowPreciseTime(false);
        // 允许循环滚动
        mStartTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mStartTimerPicker.setCanShowAnim(true);

        mTvEndTime.setText(endTime);
        GlobalDataUtil.getInstance().getSelectConfig().setEnd_time(endTime);
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mEndTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvEndTime.setText(DateFormatUtils.long2Str(timestamp, false));
                GlobalDataUtil.getInstance().getSelectConfig().setEnd_time(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mEndTimerPicker.setCancelable(true);
        // 显示时和分
        mEndTimerPicker.setCanShowPreciseTime(false);
        // 允许循环滚动
        mEndTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mEndTimerPicker.setCanShowAnim(true);
    }

    @Override
    public void OnRequestData(Station station) {
        List<Station.RootBean.DataBean> dataBeanList = station.getRoot().getData();
        if (dataBeanList != null && dataBeanList.size() > 0 && !mNavigationView.getMenu().hasVisibleItems()) {
            for (int i = 0; i < dataBeanList.size(); i++) {
                mNavigationView.getMenu().add(i, i, 1, dataBeanList.get(i).getStationName());
            }
            mNavigationView.getMenu().add(dataBeanList.size(), dataBeanList.size(), 0, "ALL");
            //设置toolbar标题文本
//            mToolbar.setTitle(dataBeanList.get(0).getStationName());
        } else {
            //设置toolbar标题文本
//            mToolbar.setTitle("首页");
        }
    }

    @Override
    public void showLoading(String msg) {
        showNormalDialog(msg);
    }

    @Override
    public void hideLoading() {
        closeNormalDialog();
    }

    @Override
    public void onFeedBack(boolean success, String key, Object data) {
        if (!success) {
            Toast.makeText(this, getText(R.string.text_data_error), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void OnRequestData(ReCharge reCharge) {
        if (reCharge != null && reCharge.getRoot() != null && reCharge.getRoot().getData() != null && reCharge.getRoot().getData().size() > 0) {
//            initRechargePager(Integer.valueOf(reCharge.getRoot().getTotal()), reCharge.getRoot().getData(), mRequestParam);
        }
    }

    private void toggle() {
        int drawerLockMode = mDrawerLayout.getDrawerLockMode(GravityCompat.START);
        if (mDrawerLayout.isDrawerVisible(GravityCompat.START)
                && (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_OPEN)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    public void initRechargePager(int totalPage, List<ReCharge.RootBean.DataBean> dataBeanList, Map<String, String> params) {
        L.i(getClass().getSimpleName(), "Current total page:" + totalPage);
        mWidgetPage.setPageCurrent(0);
        mWidgetPage.setPageTotal(totalPage);
        mWidgetRechargePager.initPager(totalPage, dataBeanList, params);
    }
}
