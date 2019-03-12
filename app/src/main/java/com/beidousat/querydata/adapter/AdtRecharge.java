package com.beidousat.querydata.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beidousat.querydata.R;
import com.beidousat.querydata.model.ReCharge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J Wong on 2015/12/17 08:54.
 */
public class AdtRecharge extends RecyclerView.Adapter<AdtRecharge.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<ReCharge.RootBean.DataBean> mData = new ArrayList<>();

    public AdtRecharge(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    public void setData(List<ReCharge.RootBean.DataBean> data) {
        this.mData = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView plateNum;
        public TextView owner;
        public TextView company;
        public TextView recharge_add;
        public TextView recharge_zeng;
        public TextView staff;
        public TextView time;
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
        View view = mInflater.inflate(R.layout.list_item_recharge, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.plateNum = view.findViewById(R.id.item_recharge_plateNum);
        viewHolder.owner = view.findViewById(R.id.item_recharge_owner);
        viewHolder.company = view.findViewById(R.id.item_recharge_company);
        viewHolder.recharge_add = view.findViewById(R.id.item_recharge_add);
        viewHolder.recharge_zeng = view.findViewById(R.id.item_recharge_zeng);
        viewHolder.staff = view.findViewById(R.id.item_recharge_staff);
        viewHolder.time = view.findViewById(R.id.item_recharge_time);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ReCharge.RootBean.DataBean dataBean = mData.get(position);
        holder.plateNum.setText(dataBean.getPlatenumber());
        holder.owner.setText(dataBean.getOwner());
        holder.company.setText(dataBean.getCompany());
        holder.recharge_add.setText(dataBean.getAddmoney());
        holder.recharge_zeng.setText(dataBean.getZengmoney());
        holder.staff.setText(dataBean.getStaff());
        holder.time.setText(dataBean.getAddtime());
    }

}
