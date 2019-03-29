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
import com.beidousat.querydata.model.Cash;
import com.beidousat.querydata.model.ReCharge;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J Wong on 2015/12/17 08:54.
 */
public class AdtCash extends RecyclerView.Adapter<AdtCash.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Cash.RootBean.DataBean> mData = new ArrayList<>();
    public AdtCash(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    public void setData(List<Cash.RootBean.DataBean> data) {
        data.add(0,new Cash.RootBean.DataBean("班次","接班时间","实收现金","现金支付","现金充值","退卡金额","补卡金额","补卡数量","发卡数量","收款员"));
        this.mData = data;
    }
    public void setSum(Cash.RootBean.SumBean sum){
        if(sum!=null){
            mData.add(1,sum.toDataBean());
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dutyID;
        public TextView startTime;
        public TextView sellCash;
        public TextView sellMoney;
        public TextView chargeMoney1;
        public TextView refundMoney;
        public TextView reissueMoney;
        public TextView reissueCount;
        public TextView cardCount;
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
        View view = mInflater.inflate(R.layout.list_item_cash, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.dutyID = view.findViewById(R.id.item_cash_dutyID);
        viewHolder.startTime = view.findViewById(R.id.item_cash_startTime);
        viewHolder.sellCash = view.findViewById(R.id.item_cash_sellCash);
        viewHolder.sellMoney = view.findViewById(R.id.item_cash_sellMoney);
        viewHolder.chargeMoney1 = view.findViewById(R.id.item_cash_chargeMoney1);
        viewHolder.refundMoney = view.findViewById(R.id.item_cash_refundMoney);
        viewHolder.reissueMoney = view.findViewById(R.id.item_cash_reissueMoney);
        viewHolder.reissueCount = view.findViewById(R.id.item_cash_reissueCount);
        viewHolder.cardCount = view.findViewById(R.id.item_cash_cardCount);
        viewHolder.userName = view.findViewById(R.id.item_cash_userName);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Cash.RootBean.DataBean dataBean = mData.get(position);
        if(position==0){
            holder.sellCash.setGravity(Gravity.CENTER);
            holder.sellMoney.setGravity(Gravity.CENTER);
            holder.chargeMoney1.setGravity(Gravity.CENTER);
            holder.refundMoney.setGravity(Gravity.CENTER);
            holder.reissueMoney.setGravity(Gravity.CENTER);
        }
        if(position==1){
            holder.dutyID.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.startTime.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.sellCash.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.sellMoney.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.chargeMoney1.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.refundMoney.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.reissueMoney.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.reissueCount.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.cardCount.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.userName.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
        }
        holder.dutyID.setText(dataBean.getDutyID());
        holder.startTime.setText(dataBean.getStartTime());
        holder.sellCash.setText(dataBean.getSellCash());
        holder.sellMoney.setText(dataBean.getSellMoney());
        holder.chargeMoney1.setText(dataBean.getChargeMoney1());
        holder.refundMoney.setText(dataBean.getRefundMoney());
        holder.reissueMoney.setText(dataBean.getReissueMoney());
        holder.reissueCount.setText(dataBean.getReissueCount());
        holder.cardCount.setText(dataBean.getCardCount());
        holder.userName.setText(dataBean.getUserName());
    }

}
