package com.pujit.max_profit.ui.mainactivity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pujit.max_profit.R;
import com.pujit.max_profit.ui.mainactivity.model.DataModel;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.TransactionViewHolder> {

    private final Context context;
    private ArrayList<DataModel> profitModels;
    private DataModel data;

    public DataAdapter(Context context, ArrayList<DataModel> profitModels) {
        this.context = context;
        this.profitModels = profitModels;
    }


    @NonNull
    @Override
    public TransactionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_transaction_adapter, parent, false);
        return new TransactionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionViewHolder holder, int position) {
        data = profitModels.get(position);
        if (data!=null){
            holder.tvBuy.setText(String.valueOf(data.getBuy()));
            holder.tvSell.setText(String.valueOf(data.getSell()));
            holder.tvProfit.setText(String.valueOf(data.getProfit()));
            holder.tvShare.setText(String.valueOf(data.getShareName()));
        }

    }

    @Override
    public int getItemCount() {
        return profitModels.size();
    }


    static class TransactionViewHolder extends RecyclerView.ViewHolder {

        private TextView tvBuy;
        private TextView tvSell;
        private TextView tvShare;
        private TextView tvProfit;

        TransactionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvBuy=itemView.findViewById(R.id.tvBuy);
            tvSell=itemView.findViewById(R.id.tvSell);
            tvShare=itemView.findViewById(R.id.tvShare);
            tvProfit=itemView.findViewById(R.id.tvProfit);




        }
    }
}
