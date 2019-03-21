package com.beidousat.querydata.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.beidousat.querydata.R;
import com.beidousat.querydata.base.BaseActivity;
import com.beidousat.querydata.base.WidgetPage;
import com.beidousat.querydata.buss.BanciConstract;
import com.beidousat.querydata.buss.ChangDutyPresenter;
import com.beidousat.querydata.buss.RechargeConstract;
import com.beidousat.querydata.buss.RechargePresenter;
import com.beidousat.querydata.buss.StationConstract;
import com.beidousat.querydata.buss.StationPresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.common.GlobalDataUtil;
import com.beidousat.querydata.model.Banci;
import com.beidousat.querydata.model.ReCharge;
import com.beidousat.querydata.model.SelectConfig;
import com.beidousat.querydata.model.Station;
import com.beidousat.querydata.utils.L;
import com.beidousat.querydata.utils.datepicker.CustomDatePicker;
import com.beidousat.querydata.utils.datepicker.DateFormatUtils;
import com.beidousat.querydata.widget.OnPageScrollListener;
import com.beidousat.querydata.widget.WidgetChangeDutyPager;
import com.beidousat.querydata.widget.WidgetRechargePager;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChangDutyActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, StationConstract.View, BanciConstract.View,OnPageScrollListener, WidgetPage.OnPageChangedListener  {
    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    NavigationView mNavigationView;
    private ActionBarDrawerToggle mDrawerToggle;
    private WidgetPage mWidgetPage;
    private WidgetChangeDutyPager widgetChangeDutyPager;
    private ImageView icon_back;
    private TextView mTvStartTime, mTvEndTime, mTvTitle, query;
    private Spinner spinner;
    private CustomDatePicker mStartTimerPicker, mEndTimerPicker;
    private StationPresenter stationPresenter;
    private ChangDutyPresenter changDutyPresenter;
    private Map<String, String> requestParams;
    private EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        stationPresenter = new StationPresenter(this);
        changDutyPresenter = new ChangDutyPresenter(this);

    }

    @Override
    public void setupToolbar() {
        super.setupToolbar();
    }

    @Override
    public int getContentView() {
        return R.layout.changduty_main;
    }

    @Override
    public void initViews() {
        mDrawerLayout = findViewById(R.id.layout_draw);
        mToolbar = findViewById(R.id.toolbar);
        mNavigationView = findViewById(R.id.layout_navigation);
        mTvStartTime = findViewById(R.id.tv_selected_start_time);
        mTvEndTime = findViewById(R.id.tv_selected_endtime);
        mTvTitle = findViewById(R.id.recharge_title);
        et_search=findViewById(R.id.et_recharge_search);
        icon_back = findViewById(R.id.recharge_ic_back);
        widgetChangeDutyPager = findViewById(R.id.change_duty_pager);
        mWidgetPage = findViewById(R.id.w_page);
        spinner = findViewById(R.id.recharge_spinner);
        query = findViewById(R.id.recharge_query);
//        layout_head=findViewById(R.id.recharge_head);
        mTvTitle.setText("全部站点");
        mWidgetPage.setOnPageChangedListener(this);
        widgetChangeDutyPager.setOnPagerScrollListener(this);
        //设置toolbar
        SelectConfig selectConfig = new SelectConfig();
        selectConfig.setStationName("全部站点");
        selectConfig.setSelect_text("");
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

    }

    private void requestChangeDuty() {
        requestParams = new HashMap<>();
        requestParams.put("arg0", Constant.Key);
        requestParams.put("arg1", GlobalDataUtil.getInstance().getSelectConfig().getStationName().equals("全部站点")?"ALL":GlobalDataUtil.getInstance().getSelectConfig().getStationName());
        requestParams.put("arg2", GlobalDataUtil.getInstance().getSelectConfig().getStart_time());
        requestParams.put("arg3", GlobalDataUtil.getInstance().getSelectConfig().getEnd_time());
        requestParams.put("arg4", String.valueOf(GlobalDataUtil.getInstance().getSelectConfig().getSelect_index()));
        requestParams.put("arg5", String.valueOf(GlobalDataUtil.getInstance().getSelectConfig().getSelect_text()));
        changDutyPresenter.getChangeDutyList(requestParams, 1, Constant.per_pager);
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
                mEndTimerPicker.show(mTvEndTime.getText().toString());
                break;
            case R.id.recharge_ic_back:
                finish();
                break;
            case R.id.recharge_title:
                toggle();
                break;
            case R.id.recharge_query:
                GlobalDataUtil.getInstance().getSelectConfig().setSelect_text(et_search.getText().toString().trim());
                requestChangeDuty();
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
        String startTime = DateFormatUtils.getStartTimeforCurrent(new Date(System.currentTimeMillis()),true);
        String endTime = DateFormatUtils.getEndTimeforCurrent(new Date(System.currentTimeMillis()),true);
//        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);

        mTvStartTime.setText(DateFormatUtils.getStartTimeforCurrent(new Date(System.currentTimeMillis()),false));
        GlobalDataUtil.getInstance().getSelectConfig().setStart_time(DateFormatUtils.getStartTimeforCurrent(new Date(System.currentTimeMillis()),false));
        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mStartTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvStartTime.setText(DateFormatUtils.long2Str(timestamp, false));
                GlobalDataUtil.getInstance().getSelectConfig().setStart_time(DateFormatUtils.long2Str(timestamp, false));
            }
        }, beginTime, startTime);
        // 允许点击屏幕或物理返回键关闭
        mStartTimerPicker.setCancelable(true);
        // 显示时和分
        mStartTimerPicker.setCanShowPreciseTime(false);
        // 允许循环滚动
        mStartTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mStartTimerPicker.setCanShowAnim(true);

        mTvEndTime.setText(DateFormatUtils.getEndTimeforCurrent(new Date(System.currentTimeMillis()),false));
        GlobalDataUtil.getInstance().getSelectConfig().setEnd_time(DateFormatUtils.getEndTimeforCurrent(new Date(System.currentTimeMillis()),false));
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
        requestChangeDuty();
        List<Station.RootBean.DataBean> dataBeanList = station.getRoot().getData();
        if (dataBeanList != null && dataBeanList.size() > 0 && !mNavigationView.getMenu().hasVisibleItems()) {
            for (int i = 0; i < dataBeanList.size(); i++) {
                mNavigationView.getMenu().add(i, i, 1, dataBeanList.get(i).getStationName());
            }
            mNavigationView.getMenu().add(dataBeanList.size(), dataBeanList.size(), 0, "全部站点");
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

    private void toggle() {
        int drawerLockMode = mDrawerLayout.getDrawerLockMode(GravityCompat.START);
        if (mDrawerLayout.isDrawerVisible(GravityCompat.START)
                && (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_OPEN)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else if (drawerLockMode != DrawerLayout.LOCK_MODE_LOCKED_CLOSED) {
            mDrawerLayout.openDrawer(GravityCompat.START);
        }
    }

    public void initRechargePager(int totalPage, List<Banci.RootBean.DataBean> dataBeanList, Banci.RootBean.SumBean sumBean,Map<String, String> params) {
        L.i(getClass().getSimpleName(), "Current total page:" + totalPage);
        mWidgetPage.setPageCurrent(0);
        mWidgetPage.setPageTotal(totalPage);
        widgetChangeDutyPager.setSumBean(sumBean);
        widgetChangeDutyPager.initPager(totalPage, dataBeanList, params);
    }

    @Override
    public void onPrePageClick(int before, int current) {
        widgetChangeDutyPager.setCurrentItem(current);
    }

    @Override
    public void onNextPageClick(int before, int current) {
        widgetChangeDutyPager.setCurrentItem(current);
    }

    @Override
    public void onFirstPageClick(int before, int current) {
        widgetChangeDutyPager.setCurrentItem(current);
        mWidgetPage.setPrePressed(false);
        mWidgetPage.setNextPressed(false);
    }

    @Override
    public void onPageScrollLeft() {
        mWidgetPage.setPrePressed(true);
        mWidgetPage.setNextPressed(false);
    }

    @Override
    public void onPageScrollRight() {
        mWidgetPage.setPrePressed(false);
        mWidgetPage.setNextPressed(true);
    }

    @Override
    public void onPagerSelected(int i, boolean b) {
        widgetChangeDutyPager.runLayoutAnimation();
        mWidgetPage.setPageCurrent(i);
        mWidgetPage.setPrePressed(false);
        mWidgetPage.setNextPressed(false);
    }

    @Override
    public void OnRequestData(Banci banci) {
        if (banci != null && banci.getRoot() != null && banci.getRoot().getData() != null && banci.getRoot().getData().size() > 0) {
            initRechargePager(Integer.valueOf(banci.getRoot().getTotal()), banci.getRoot().getData(),banci.getRoot().getSum(), requestParams);
        }
    }
}
