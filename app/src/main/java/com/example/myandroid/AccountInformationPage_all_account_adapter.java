package com.example.myandroid;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AccountInformationPage_all_account_adapter extends RecyclerView.Adapter<AccountInformationPage_all_account_adapter.MyViewHolder> {

    ArrayList<Account_infor> acc_list;

    public AccountInformationPage_all_account_adapter(ArrayList<Account_infor> acc_list) {
        this.acc_list = acc_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_accountinformationpage_all_account,parent,false);
        return new AccountInformationPage_all_account_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.card_all.setBackgroundColor(Color.TRANSPARENT);
        holder.layout.setBackgroundColor(Color.TRANSPARENT);
        holder.username_name.setTextColor(Color.parseColor("#ffffff"));

        holder.username_name.setText(acc_list.get(position).getUsername());
    }

    @Override
    public int getItemCount() {
        return acc_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView username_name;
        private CardView card_all;
        private RelativeLayout layout;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            username_name = itemView.findViewById(R.id.account_name_load);
            //img = itemView.findViewById(R.id.account_name_load);
            card_all = itemView.findViewById(R.id.card_all_account);
            layout = itemView.findViewById(R.id.all_account_frame);
        }
    }
}
