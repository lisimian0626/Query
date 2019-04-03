package com.beidousat.querydata.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beidousat.querydata.R;
import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.Store;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J Wong on 2015/12/17 08:54.
 */
public class AdtStore extends RecyclerView.Adapter<AdtStore.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Store.RootBean.DataBean> mData = new ArrayList<>();
    public AdtStore(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    public void setData(List<Store.RootBean.DataBean> data) {
        data.add(0,new Store.RootBean.DataBean("站点","监测时间","L1","P1","T1","L2","P2","T2","NN","W3",""));
        this.mData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView stationName;
        public TextView checkTime;
        public TextView L1;
        public TextView P1;
        public TextView T1;
        public TextView L2;
        public TextView P2;
        public TextView T2;
        public TextView NN;
        public TextView W3;
        public ViewHolder(View view) {
            super(view);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = mInflater.inflate(R.layout.list_item_store, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.stationName = view.findViewById(R.id.item_store_stationName);
        viewHolder.checkTime = view.findViewById(R.id.item_store_checkTime);
        viewHolder.L1 = view.findViewById(R.id.item_store_L1);
        viewHolder.P1 = view.findViewById(R.id.item_store_P1);
        viewHolder.T1 = view.findViewById(R.id.item_store_T1);
        viewHolder.L2 = view.findViewById(R.id.item_store_L2);
        viewHolder.P2 = view.findViewById(R.id.item_store_P2);
        viewHolder.T2 = view.findViewById(R.id.item_store_T2);
        viewHolder.NN = view.findViewById(R.id.item_store_NN);
        viewHolder.W3 = view.findViewById(R.id.item_store_W3);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Store.RootBean.DataBean dataBean = mData.get(position);
        if(position==0){
            holder.L1.setGravity(Gravity.CENTER);
            holder.P1.setGravity(Gravity.CENTER);
            holder.T1.setGravity(Gravity.CENTER);
            holder.L2.setGravity(Gravity.CENTER);
            holder.P2.setGravity(Gravity.CENTER);
            holder.T2.setGravity(Gravity.CENTER);
            holder.NN.setGravity(Gravity.CENTER);
            holder.W3.setGravity(Gravity.CENTER);
        }
        holder.stationName.setText(dataBean.getStationName());
        holder.checkTime.setText(dataBean.getCheckTime());
        holder.L1.setText(dataBean.getL1());
        holder.P1.setText(dataBean.getP1());
        holder.T1.setText(dataBean.getT1());
        holder.L2.setText(dataBean.getL2());
        holder.P2.setText(dataBean.getP2());
        holder.T2.setText(dataBean.getT2());
        holder.NN.setText(dataBean.getNN());
        holder.W3.setText(dataBean.getW3());
    }

}
