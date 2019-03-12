package com.beidousat.querydata.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.beidousat.querydata.R;
import com.beidousat.querydata.base.BaseFragment;
import com.beidousat.querydata.buss.RechargeConstract;
import com.beidousat.querydata.buss.RechargePresenter;
import com.beidousat.querydata.model.ReCharge;

public class RechargeFragment extends BaseFragment implements RechargeConstract.View{
    private String  title;
    private String content="{\"root\":{\"data\": {\"row\":[{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-29 15:56:53.647\"},{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-29 15:57:47.7\"},{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 16:21:59.0\"},{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 16:25:20.53\"},{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 16:40:03.473\"},{\"platenumber\":\"ATR123\",\"owner\":\"王达\",\"company\":\"邯郸站\",\"addmoney\":\"10.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 16:40:31.43\"},{\"platenumber\":\"HTM521\",\"owner\":\"加气\",\"company\":\"北站站\",\"addmoney\":\"5.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 17:15:13.197\"},{\"platenumber\":\"HTM852\",\"owner\":\"问问\",\"company\":\"北站站\",\"addmoney\":\"200.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 17:22:43.217\"},{\"platenumber\":\"THR656\",\"owner\":\"娃娃\",\"company\":\"邯郸站\",\"addmoney\":\"200.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-01-30 17:33:34.527\"},{\"platenumber\":\"ATR123\",\"owner\":\"王飞\",\"company\":\"北站站\",\"addmoney\":\"1000.00\",\"zengmoney\":\"0.00\",\"staff\":\"super\",\"addtime\":\"2019-02-19 12:00:17.41\"}]},\"response\":{\"errcode\":\"0\"}}}";
    private RechargePresenter rechargePresenter;
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
        rechargePresenter=new RechargePresenter(this);
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
      rechargePresenter.getRechargeList("SKThd2019","北站站","2019-01-01","2019-03-11",0,"",2,10);
    }

    @Override
    public void cancelRequestWhenOnPause() {

    }

    @Override
    public void OnRequestData(ReCharge station) {

    }

    @Override
    public void onFeedBack(boolean success, String key, Object data) {
        if(!success){
            Toast.makeText(getActivity(),getActivity().getText(R.string.text_data_error),Toast.LENGTH_SHORT).show();
        }
    }
}
