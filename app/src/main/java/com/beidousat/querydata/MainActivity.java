package com.beidousat.querydata;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.beidousat.querydata.adapter.MyAdapter;
import com.beidousat.querydata.base.BaseActivity;
import com.beidousat.querydata.fragment.CashFragment;
import com.beidousat.querydata.fragment.ChangeDutyFragment;
import com.beidousat.querydata.fragment.GasFragment;
import com.beidousat.querydata.fragment.PaperFragment;
import com.beidousat.querydata.fragment.RechargeFragment;
import com.beidousat.querydata.utils.datepicker.CustomDatePicker;
import com.beidousat.querydata.utils.datepicker.DateFormatUtils;
import com.beidousat.querydata.widget.TablayoutHelper;
import com.beidousat.task.GetDataTask;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener{
    DrawerLayout mDrawerLayout;
    Toolbar mToolbar;
    NavigationView mNavigationView;
    private ViewPager vp;
    private TabLayout tabLayout;
    private ActionBarDrawerToggle mDrawerToggle;

    private TextView mTvStartTime, mTvEndTime;
    private CustomDatePicker mStartTimerPicker,mEndTimerPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setupToolbar() {
        super.setupToolbar();
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        mDrawerLayout=findViewById(R.id.layout_draw);
        mToolbar=findViewById(R.id.toolbar);
        mNavigationView=findViewById(R.id.layout_navigation);


        mTvStartTime = findViewById(R.id.tv_selected_start_time);
        mTvEndTime = findViewById(R.id.tv_selected_endtime);
        for(int i=0;i<9;i++){
            mNavigationView.getMenu().add(i,i,i,"menu"+i);
        }

        vp = findViewById(R.id.vp);
        tabLayout=findViewById(R.id.tab);

        //设置toolbar标题文本
        mToolbar.setTitle("首页");
        //设置toolbar
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //设置左上角图标是否可点击
            actionBar.setHomeButtonEnabled(true);
            //左上角加上一个返回图标
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    @Override
    public void setListener() {
        findViewById(R.id.ll_end_time).setOnClickListener(this);
        findViewById(R.id.ll_start_time).setOnClickListener(this);
    }

    @Override
    public void initData() {
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
        mDrawerToggle.syncState();
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mNavigationView.setNavigationItemSelectedListener(this);
        //实例化适配器
        MyAdapter adapter=new MyAdapter(getSupportFragmentManager());
        adapter.add(RechargeFragment.newInstance(getString(R.string.recharge)));
        adapter.add(ChangeDutyFragment.newInstance(getString(R.string.changeDuty)));
        adapter.add(CashFragment.newInstance(getString(R.string.cash)));
        adapter.add(GasFragment.newInstance(getString(R.string.gas)));
//viewpager设置适配器
        vp.setAdapter(adapter);
        //设置预加载的数量当然这个值越小越好
        vp.setOffscreenPageLimit(2);
        //给TabLayout设置ViewPager
        tabLayout.setupWithViewPager(vp);
        //设置Mode样式
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        TablayoutHelper.setTabLine(tabLayout,15,15,this);
        initTimerPicker();
    }

    @Override
    public void loadDataWhenOnResume() {

    }

    @Override
    public void cancelLoadingRequest() {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        String title = (String) menuItem.getTitle();
//        String mString = menuItem.getItemId().;
        mToolbar.setTitle(title);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        Map<String,Object> map=new HashMap<>();
        map.put("arg0","SKThd2019");
        GetDataTask getDataTask=new GetDataTask(map);
        getDataTask.execute();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initTabLayout() {
//        tabLayout.addOnTabSelectedListener(this);
        TablayoutHelper.setTabLine(tabLayout,15,15,this);
        tabLayout.getTabAt(1).select();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_start_time:
                // 日期格式为yyyy-MM-dd HH:mm
                mStartTimerPicker.show(mTvStartTime.getText().toString());
                break;
            case R.id.ll_end_time:
                mStartTimerPicker.show(mTvEndTime.getText().toString());
                break;
        }
    }


    private void initTimerPicker() {

        String beginTime = DateFormatUtils.getStartTimeforCurrent(new Date(System.currentTimeMillis()));
        Log.i("Main","time:"+beginTime);
        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);

        mTvStartTime.setText(endTime);

        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mStartTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvStartTime.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mStartTimerPicker.setCancelable(true);
        // 显示时和分
        mStartTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mStartTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mStartTimerPicker.setCanShowAnim(true);

        mTvEndTime.setText(endTime);

        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mEndTimerPicker = new CustomDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                mTvEndTime.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        }, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mEndTimerPicker.setCancelable(true);
        // 显示时和分
        mEndTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mEndTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mEndTimerPicker.setCanShowAnim(true);
    }
}
