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
import com.beidousat.querydata.model.Gas;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by J Wong on 2015/12/17 08:54.
 */
public class AdtGas extends RecyclerView.Adapter<AdtGas.ViewHolder> {

    private Context mContext;
    private LayoutInflater mInflater;
    private List<Gas.RootBean.DataBean> mData = new ArrayList<>();
    public AdtGas(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }


    public void setData(List<Gas.RootBean.DataBean> data) {
        data.add(0,new Gas.RootBean.DataBean("班次","开始时间","车牌号","枪号","加气量","单价","金额","卡类型","加气工","上传时间"));
        this.mData = data;
    }
    public void setSum(Gas.RootBean.SumBean sum){
        if(sum!=null){
            mData.add(1,sum.toDataBean());
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView dutyID;
        public TextView startTime;
        public TextView carNo;
        public TextView gasNo;
        public TextView quantity;
        public TextView price;
        public TextView money;
        public TextView cardType;
        public TextView workerName;
        public TextView uploadTime;
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
        View view = mInflater.inflate(R.layout.list_item_gas, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.dutyID = view.findViewById(R.id.item_gas_dutyID);
        viewHolder.startTime = view.findViewById(R.id.item_gas_startTime);
        viewHolder.carNo = view.findViewById(R.id.item_gas_carNo);
        viewHolder.gasNo = view.findViewById(R.id.item_gas_gasNo);
        viewHolder.quantity = view.findViewById(R.id.item_gas_quantity);
        viewHolder.price = view.findViewById(R.id.item_gas_price);
        viewHolder.money = view.findViewById(R.id.item_gas_money);
        viewHolder.cardType = view.findViewById(R.id.item_gas_cardType);
        viewHolder.workerName = view.findViewById(R.id.item_gas_workerName);
        viewHolder.uploadTime = view.findViewById(R.id.item_gas_uploadTime);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Gas.RootBean.DataBean dataBean = mData.get(position);
        if(position==0){
            holder.quantity.setGravity(Gravity.CENTER);
            holder.price.setGravity(Gravity.CENTER);
            holder.money.setGravity(Gravity.CENTER);
        }else if(position==1){
            holder.dutyID.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.startTime.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.carNo.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.gasNo.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.quantity.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.price.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.money.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.cardType.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.workerName.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
            holder.uploadTime.setBackgroundResource(R.drawable.text_bg_stroke_yellow);
        }
        holder.dutyID.setText(dataBean.getDutyID());
        holder.startTime.setText(dataBean.getStartTime());
        holder.carNo.setText(dataBean.getCarNo());
        holder.gasNo.setText(dataBean.getGasNo());
        holder.quantity.setText(dataBean.getQuantity());
        holder.price.setText(dataBean.getPrice());
        holder.money.setText(dataBean.getMoney());
        holder.cardType.setText(dataBean.getCardType());
        holder.workerName.setText(dataBean.getWorkerName());
        holder.uploadTime.setText(dataBean.getUploadTime());
    }

}
