package com.os.imars.operator.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.os.imars.R;
import com.os.imars.dao.finance.PaidItem;
import com.os.imars.dao.finance.UnpaidItem;
import com.os.imars.utility.Util;

import java.util.List;

public class PaidRVAdapter extends RecyclerView.Adapter<PaidRVAdapter.AgentsViewHolders> {

    public Context context;
    private List<PaidItem> paidItemList;


    public PaidRVAdapter(Context context, List<PaidItem> paidItemList) {
        this.context = context;
        this.paidItemList = paidItemList;
    }

    @Override
    public PaidRVAdapter.AgentsViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_paid_row_items, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemLayoutView.setLayoutParams(lp);
        PaidRVAdapter.AgentsViewHolders viewHolder = new PaidRVAdapter.AgentsViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PaidRVAdapter.AgentsViewHolders holder, final int position) {
        final PaidItem paidItem = paidItemList.get(position);
        holder.tvNumber.setText(paidItem.getSurveyNumber());
        holder.tvCity.setText(paidItem.getPortName());
        String startDate = paidItem.getInvoiceDate();
        holder.tvDate.setText(Util.parseDateToAnyFormat("dd/mm/yyyy hh:mm:ss a","dd MMM yyyy",startDate));
        holder.tvVesselName.setText(paidItem.getVesselsName());
        holder.tvAmount.setText(paidItem.getInvoiceAmount() + "$");
    }

    @Override
    public int getItemCount() {
        return paidItemList.size();
    }


    public class AgentsViewHolders extends RecyclerView.ViewHolder {

        private RelativeLayout rlDetailsView;
        private TextView tvNumber, tvCity, tvDate, tvAmount, tvVesselName;

        public AgentsViewHolders(View itemView) {
            super(itemView);
            rlDetailsView = itemView.findViewById(R.id.rlDetailsView);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            tvVesselName = itemView.findViewById(R.id.tvVesselName);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvAmount = itemView.findViewById(R.id.tvAmount);
        }
    }
}
