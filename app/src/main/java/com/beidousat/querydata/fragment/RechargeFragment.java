package com.beidousat.querydata.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.beidousat.querydata.R;
import com.beidousat.querydata.base.BaseFragment;
import com.beidousat.task.GetDataTask;

import java.util.HashMap;
import java.util.Map;

public class RechargeFragment extends BaseFragment{
    private String  title;
    private String content="{\"root\":{\"data\": {\"row\":[{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-29 15:56:53.647\"},{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-29 15:57:47.7\"},{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 16:21:59.0\"},{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 16:25:20.53\"},{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 16:40:03.473\"},{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"10.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 16:40:31.43\"},{\"platenumber\":\"HTM521\",\"owner\":\"加气\",\"company\":\"北站站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 17:15:13.197\"},{\"platenumber\":\"HTM852\",\"owner\":\"问问\",\"company\":\"北站站\",\"addmoney\":\"200.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 17:22:43.217\"},{\"platenumber\":\"THR656\",\"owner\":\"娃娃\",\"company\":\"邯郸站\",\"addmoney\":\"200.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 17:33:34.527\"},{\"platenumber\":\"ATR123\",\"owner\":\"王飞\",\"company\":\"北站站\",\"addmoney\":\"1000.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-02-19 12:00:17.41\"}]},\"response\":{\"errcode\":\"0\"}}}";
    public static RechargeFragment newInstance(String title){
        RechargeFragment pf=new RechargeFragment();
        Bundle bundle= new Bundle();
        bundle.putString("title",title);
        //向fragment中传值，使用此方法，不用使用有参构造方法
        pf.setArguments(bundle);
        return pf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //取出保存的值
        this.title=getArguments().getString("title");

    }

    @Override
    public int getContentView() {
        return R.layout.contenlayout;
    }

    @Override
    public void initViews() {

    }

    @Override
    public void setListeners() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void loadDataWhenOnResume() {

    }

    @Override
    public void cancelRequestWhenOnPause() {

    }
}
