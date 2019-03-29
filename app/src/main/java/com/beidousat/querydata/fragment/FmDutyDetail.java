package com.beidousat.querydata.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.beidousat.querydata.R;
import com.beidousat.querydata.adapter.AdtChangDutyDetail;
import com.beidousat.querydata.buss.DutyDetailConstract;
import com.beidousat.querydata.buss.DutyDetailPresenter;
import com.beidousat.querydata.common.Constant;
import com.beidousat.querydata.common.GlobalDataUtil;
import com.beidousat.querydata.model.Banci;
import com.beidousat.querydata.model.DutyDetail;
import com.beidousat.widgetlibs.recycler.HorizontalDividerItemDecoration;
import com.beidousat.widgetlibs.recycler.VerticalDividerItemDecoration;

import java.util.HashMap;
import java.util.Map;

public class FmDutyDetail extends DialogFragment implements DutyDetailConstract.View {
    private RecyclerView detail_content;
    private AdtChangDutyDetail adapter;
    private DutyDetailPresenter presenter;
    private TextView tv_close,tv_banci,tv_dutyName,tv_userName;
    private Banci.RootBean.DataBean dataBean;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter=new DutyDetailPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        final Window window = getDialog().getWindow();
        View view = inflater.inflate(R.layout.duty_detail_main, container, false);
//        window.findViewById(android.R.id.content);//需要用android.R.id.content这个view
//        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//注意此处
//        window.setLayout(1000, 600);//这2行,和上面的一样,注意顺序就行;
        tv_close=view.findViewById(R.id.detail_tv_close);
        tv_banci=view.findViewById(R.id.detail_banci);
        tv_dutyName=view.findViewById(R.id.detail_dutyName);
        tv_userName=view.findViewById(R.id.detail_userName);
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        detail_content=view.findViewById(R.id.detail_content);
        init();
        return view;
    }

    private void init() {
        HorizontalDividerItemDecoration horDivider = new HorizontalDividerItemDecoration.Builder(getActivity().getApplicationContext())
                .color(Color.TRANSPARENT)
                .build();
        detail_content.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        detail_content.addItemDecoration(horDivider);
        adapter = new AdtChangDutyDetail(getActivity());
        detail_content.setAdapter(adapter);
        dataBean= (Banci.RootBean.DataBean) getArguments().getSerializable("dataBean");
        if(dataBean!=null){
            tv_banci.setText(dataBean.getBanci());
            tv_dutyName.setText(dataBean.getDutyName());
            tv_userName.setText(dataBean.getUserName());
            if(!TextUtils.isEmpty(dataBean.getBanci())){
                Map<String, String> requestParams=new HashMap<>();
                requestParams.put("arg0", Constant.Key);
                requestParams.put("arg1", GlobalDataUtil.getInstance().getSelectConfig().getStationName());
                requestParams.put("arg2", dataBean.getBanci());
                presenter.getDutyDetail(requestParams);
            }
        }



    }

    @Override
    public void OnRequestData(DutyDetail dutyDetail) {
        if(dutyDetail!=null&&dutyDetail.getRoot()!=null){
            if(dutyDetail.getRoot().getData()!=null){
                adapter.setData(dutyDetail.getRoot().getData());
            }else if(dutyDetail.getRoot().getSum()!=null){
                adapter.setSum(dutyDetail.getRoot().getSum());
            }
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void showLoading(String msg) {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onFeedBack(boolean success, String key, Object data) {
        if (!success&&getActivity()!=null) {
            Toast.makeText(getActivity(), getText(R.string.text_data_error), Toast.LENGTH_SHORT).show();
        }
    }
}
