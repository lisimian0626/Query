package com.beidousat.querydata;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.beidousat.querydata.activity.CashActivity;
import com.beidousat.querydata.activity.ChangDutyActivity;
import com.beidousat.querydata.activity.GasActivity;
import com.beidousat.querydata.activity.RechargeActivity;
import com.beidousat.querydata.base.BaseActivity;

public class MainActivity extends BaseActivity implements View.OnClickListener {
    private Button recharge, changeDuty, cash;
    private Button gas, store, backup;

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initViews() {
        recharge = findViewById(R.id.main_recharge);
        recharge.setOnClickListener(this);
        changeDuty = findViewById(R.id.main_change_duty);
        changeDuty.setOnClickListener(this);
        cash = findViewById(R.id.main_cash);
        cash.setOnClickListener(this);
        gas = findViewById(R.id.main_gas);
        gas.setOnClickListener(this);
        store = findViewById(R.id.main_store);
        store.setOnClickListener(this);
    }

    @Override
    public void setListener() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadDataWhenOnResume() {

    }

    @Override
    public void cancelLoadingRequest() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_recharge:
                startActivity(new Intent(this, RechargeActivity.class));
                break;
            case R.id.main_change_duty:
                startActivity(new Intent(this, ChangDutyActivity.class));
                break;
            case R.id.main_cash:
                startActivity(new Intent(this, CashActivity.class));
                break;
            case R.id.main_gas:
                startActivity(new Intent(this, GasActivity.class));
                break;
        }
    }
}
