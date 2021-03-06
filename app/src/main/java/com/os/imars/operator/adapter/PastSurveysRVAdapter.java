package com.os.imars.operator.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.os.imars.R;
import com.os.imars.comman.activity.ChatActivity;
import com.os.imars.dao.mySurvey.SurveyData;
import com.os.imars.dao.mySurveyors.PastItem;
import com.os.imars.operator.activity.OperatorHomeActivity;
import com.os.imars.operator.activity.PastSurveyDetailsActivity;
import com.os.imars.utility.Util;
import com.os.imars.views.BaseView.Env;

import java.util.ArrayList;
import java.util.List;

public class PastSurveysRVAdapter extends RecyclerView.Adapter<PastSurveysRVAdapter.AgentsViewHolders> {

    public Context context;
    private View.OnClickListener onClickListener;
    private DisplayImageOptions options;
    private List<PastItem> surveyorDataList;


    public PastSurveysRVAdapter(Context context, List<PastItem> surveyorDataList, View.OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
        this.surveyorDataList = surveyorDataList;
        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .build();
    }

    @Override
    public PastSurveysRVAdapter.AgentsViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_past_survey_row_items, null);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        itemLayoutView.setLayoutParams(lp);
        PastSurveysRVAdapter.AgentsViewHolders viewHolder = new PastSurveysRVAdapter.AgentsViewHolders(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PastSurveysRVAdapter.AgentsViewHolders holder, int position) {
        //ImageLoader.getInstance().displayImage(data.get(position).getProvider_profile_image(), holder.imvDriverProfile, options);

        final PastItem surveyData = surveyorDataList.get(position);
        holder.tvName.setText(surveyData.getSurveytypeName());
        holder.tvCity.setText(surveyData.getPort());
        String startDate = surveyData.getStartDate();
        String endDate = surveyData.getEndDate()/*.replace("-", "/")*/;
        holder.tvDate.setText(Util.parseDateToddMMyyyy(startDate) + "-" + Util.parseDateToddMMyyyy(endDate));
        holder.tvVesselName.setText(surveyData.getVesselsname());


        switch (surveyData.getStatus()) {
            case "4":
                holder.tvStatus.setText("Unpaid");
                holder.imvChatStatus.setVisibility(View.GONE);
                break;
            case "5":
                holder.tvStatus.setText("Paid");
                holder.imvChatStatus.setVisibility(View.GONE);
                break;

            case "6":
                holder.tvStatus.setText("Paid");
                holder.imvChatStatus.setVisibility(View.GONE);
                break;

            case "2":
                holder.tvStatus.setText("Cancelled");
                holder.imvChatStatus.setVisibility(View.VISIBLE);
                break;

        }


        holder.rlDetailsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Env.currentActivity, PastSurveyDetailsActivity.class);
                intent.putExtra("id", surveyorDataList.get(position).getId());
                intent.putExtra("surveyor_id", surveyorDataList.get(position).getSurveyorId());
                ((OperatorHomeActivity) context).startActivityForResult(intent, 105);
            }
        });

        holder.imvChatStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PastItem surveyorData1 = surveyorDataList.get(position);
                Intent intent = new Intent(Env.currentActivity, ChatActivity.class);
                intent.putExtra("receiver_id", surveyorData1.getSurveyorId());
                intent.putExtra("receiver_name", surveyorData1.getSurveyorsName());
                intent.putExtra("surveyId", surveyorData1.getId());
                intent.putExtra("status", surveyorData1.getStatus());
                intent.putExtra("deviceId", surveyorData1.getDevice_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return surveyorDataList.size();
    }

/*    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                Log.d("1234", "performFiltering: " + charSequence);
                String filterData = charSequence.toString();
                String str[] = filterData.split("/");
                String charString = str[0];
                String type = str[1];
                surveyorDataList = surveyorDataList;
                if (charString.isEmpty()) {
                    surveyorDataList = surveyorDataList;
                } else {
                    List<PastItem> filteredList = new ArrayList<>();
                    for (PastItem row : surveyorDataList) {
                        if (type.equals("filter")) {
                            if (row.getStatus().contains(charString)) {
                                Log.d("1234", "performFiltering: ");
                                filteredList.add(row);
                            }
                        }  else {
                            if (row.getSurvey_number().contains(charString) || row.getPort().toLowerCase().contains(charString.toLowerCase()) || row.getVesslesName().contains(charString.toLowerCase())) {
                                Log.d("1234", "performFiltering: else if 3 ");
                                filteredList.add(row);
                            } else if (charString.equals("All Users")) {
                                Log.d("1234", "performFiltering: else if 4 ");
                                filteredList.add(row);
                            }
                        }
                    }

                    surveyorDataList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = surveyorDataList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                surveyorDataList = (ArrayList<SurveyData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }*/

    public class AgentsViewHolders extends RecyclerView.ViewHolder {

        private RelativeLayout rlDetailsView;
        private TextView tvName, tvCity, tvDate, tvStatus,tvVesselName;
        private LinearLayout llStatus;
        private ImageView imvChatStatus;

        public AgentsViewHolders(View itemView) {
            super(itemView);
            rlDetailsView = itemView.findViewById(R.id.rlDetailsView);
            tvName = itemView.findViewById(R.id.tvName);
            tvVesselName = itemView.findViewById(R.id.tvVesselName);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            llStatus = itemView.findViewById(R.id.llStatus);
            imvChatStatus = itemView.findViewById(R.id.imvChatStatus);
        }
    }
}
