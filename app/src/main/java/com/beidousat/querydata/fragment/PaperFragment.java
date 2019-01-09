package com.beidousat.querydata.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.beidousat.querydata.R;
import com.beidousat.querydata.base.BaseFragment;

public class PaperFragment extends BaseFragment{
    private String  title;
    public static PaperFragment newInstance(String title){
        PaperFragment pf=new PaperFragment();
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
