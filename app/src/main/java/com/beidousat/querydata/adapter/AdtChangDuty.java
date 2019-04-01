package com.beidousat.querydata.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beidousat.querydata.R;
import com.beidousat.querydata.model.Banci;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J Wong on 2015/12/17 08:54.
 */
public class AdtChangDuty extends RecyclerView.Adapter<AdtChangDuty.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Banci.RootBean.DataBean> mData = new ArrayList<>();
    public static interface ChangDutyListener {
        void getDetail(Banci.RootBean.DataBean dataBean);

    }
    private ChangDutyListener changDutyListener;
    public AdtChangDuty(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    public void setData(List<Banci.RootBean.DataBean> data) {
        data.add(0, new Banci.RootBean.DataBean("站点", "班次", "接班时间", "交班时间", "理论", "实际", "现金", "IC卡", "差额", "班长", "收款员"));
        this.mData = data;
    }

    public void setSum(Banci.RootBean.SumBean sum) {
        if (sum != null) {
            mData.add(1, sum.toDataBean());
        }
    }

    public void setChangDutyListener(ChangDutyListener changDutyListener) {
        this.changDutyListener = changDutyListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView station;
        public TextView dutyID;
        public TextView startTime;
        public TextView endTime;
        public TextView theoryQuantity;
        public TextView sumQuantity;
        public TextView sellQuantity;
        public TextView cardQuantity;
        public TextView diffQuantity;
        public TextView dutyName;
        public TextView userName;

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
        View view = mInflater.inflate(R.layout.list_item_changduty, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.station = view.findViewById(R.id.item_duty_station);
        viewHolder.dutyID = view.findViewById(R.id.item_duty_dutyID);
        viewHolder.startTime = view.findViewById(R.id.item_duty_startTime);
        viewHolder.endTime = view.findViewById(R.id.item_duty_endTime);
        viewHolder.theoryQuantity = view.findViewById(R.id.item_duty_theoryQuantity);
        viewHolder.sumQuantity = view.findViewById(R.id.item_duty_sumQuantity);
        viewHolder.sellQuantity = view.findViewById(R.id.item_duty_sellQuantity);
        viewHolder.cardQuantity = view.findViewById(R.id.item_duty_cardQuantity);
        viewHolder.diffQuantity = view.findViewById(R.id.item_duty_diffQuantity);
        viewHolder.dutyName = view.findViewById(R.id.item_duty_dutyName);
        viewHolder.userName = view.findViewById(R.id.item_duty_userName);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Banci.RootBean.DataBean dataBean = mData.get(position);
        if(position==0){
            holder.theoryQuantity.setGravity(Gravity.CENTER);
            holder.sumQuantity.setGravity(Gravity.CENTER);
            holder.sellQuantity.setGravity(Gravity.CENTER);
            holder.cardQuantity.setGravity(Gravity.CENTER);
            holder.diffQuantity.setGravity(Gravity.CENTER);
        }else if (position == 1) {
            holder.station.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.dutyID.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.startTime.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.endTime.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.theoryQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.sumQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.sellQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.cardQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.diffQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.dutyName.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.userName.setBackgroundResource(R.drawable.text_bg_stroke_yellow);

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(changDutyListener!=null){
                    changDutyListener.getDetail(dataBean);
                }
            }
        });
        holder.station.setText(dataBean.getStationName());
        holder.dutyID.setText(dataBean.getBanci());
        holder.startTime.setText(dataBean.getStartTime());
        holder.endTime.setText(dataBean.getEndTime());
        holder.theoryQuantity.setText(dataBean.getTheoryQuantity());
        holder.sumQuantity.setText(dataBean.getSumQuantity());
        holder.sellQuantity.setText(dataBean.getSellQuantity());
        holder.cardQuantity.setText(dataBean.getCardQuantity());
        holder.diffQuantity.setText(dataBean.getDiffQuantity());
        holder.dutyName.setText(dataBean.getDutyName());
        holder.userName.setText(dataBean.getUserName());
    }

}
