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
import com.beidousat.querydata.model.DutyDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J Wong on 2015/12/17 08:54.
 */
public class AdtChangDutyDetail extends RecyclerView.Adapter<AdtChangDutyDetail.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<DutyDetail.RootBean.DataBean> mData = new ArrayList<>();

    public AdtChangDutyDetail(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    public void setData(List<DutyDetail.RootBean.DataBean> data) {
        data.add(0, new DutyDetail.RootBean.DataBean("气枪", "理论", "实际", "现金", "IC卡", "记账", "其他", "差额"));
        this.mData = data;
    }

    public void setSum(DutyDetail.RootBean.SumBean sum) {
        if (sum != null) {
            mData.add(1, sum.toDataBean());
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView gasNo;
        public TextView theoryQuantity;
        public TextView sumQuantity;
        public TextView sellQuantity;
        public TextView cardQuantity;
        public TextView accountQuantity;
        public TextView otherQuantity;
        public TextView diffQuantity;

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
        View view = mInflater.inflate(R.layout.list_item_dutydetail, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.gasNo = view.findViewById(R.id.item_detail_gasNo);
        viewHolder.theoryQuantity = view.findViewById(R.id.item_detail_theoryQuantity);
        viewHolder.sumQuantity = view.findViewById(R.id.item_detail_sumQuantity);
        viewHolder.sellQuantity = view.findViewById(R.id.item_detail_sellQuantity);
        viewHolder.cardQuantity = view.findViewById(R.id.item_detail_cardQuantity);
        viewHolder.accountQuantity = view.findViewById(R.id.item_detail_accountQuantity);
        viewHolder.otherQuantity = view.findViewById(R.id.item_detail_otherQuantity);
        viewHolder.diffQuantity = view.findViewById(R.id.item_detail_diffQuantity);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final DutyDetail.RootBean.DataBean dataBean = mData.get(position);
        if(position==0){
            holder.theoryQuantity.setGravity(Gravity.CENTER);
            holder.sumQuantity.setGravity(Gravity.CENTER);
            holder.sellQuantity.setGravity(Gravity.CENTER);
            holder.cardQuantity.setGravity(Gravity.CENTER);
            holder.accountQuantity.setGravity(Gravity.CENTER);
            holder.otherQuantity.setGravity(Gravity.CENTER);
            holder.diffQuantity.setGravity(Gravity.CENTER);
        }
//        else if (position == 1) {
//            holder.gasNo.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
//            holder.theoryQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
//            holder.sumQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
//            holder.sellQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
//            holder.cardQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
//            holder.accountQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
//            holder.otherQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
//            holder.diffQuantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
//        }
        holder.gasNo.setText(dataBean.getGasNo());
        holder.theoryQuantity.setText(dataBean.getTheoryQuantity());
        holder.sumQuantity.setText(dataBean.getSumQuantity());
        holder.sellQuantity.setText(dataBean.getSellQuantity());
        holder.cardQuantity.setText(dataBean.getCardQuantity());
        holder.accountQuantity.setText(dataBean.getAccountQuantity());
        holder.otherQuantity.setText(dataBean.getOtherQuantity());
        holder.diffQuantity.setText(dataBean.getDiffQuantity());
    }

}
