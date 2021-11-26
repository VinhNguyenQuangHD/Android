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

public class BookDetailPage_commend_adapter extends RecyclerView.Adapter<BookDetailPage_commend_adapter.MyViewHolder> {

    ArrayList<Comment_report> list;

    public BookDetailPage_commend_adapter(ArrayList<Comment_report> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_bookdetailpage_comment,parent,false);
        return new BookDetailPage_commend_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.card.setBackgroundColor(Color.TRANSPARENT);
        holder.layout.setBackgroundColor(Color.TRANSPARENT);
        holder.username.setTextColor(Color.parseColor("#ffffff"));
        holder.username_comment.setTextColor(Color.parseColor("#ffffff"));

        holder.username.setText(list.get(position).getUsername());
        holder.username_comment.setText(list.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView username,username_comment;
        private CardView card;
        private RelativeLayout layout;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            username = itemView.findViewById(R.id.username_display_name);
            username_comment = itemView.findViewById(R.id.username_comment_got);
            card = itemView.findViewById(R.id.card_comment);
            layout = itemView.findViewById(R.id.username_layout_commend);
            //img = itemView.findViewById(R.id.recycleview_same_type_image);
        }
    }
}
