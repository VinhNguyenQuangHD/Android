package com.example.myandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Leaderboardpage_top_3_best_adapter extends RecyclerView.Adapter<Leaderboardpage_top_3_best_adapter.MyViewHolder> {

    ArrayList<Book_overal> list;

    public Leaderboardpage_top_3_best_adapter(ArrayList<Book_overal> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_leaderboardpage_top_3_best_board,parent,false);

        return new Leaderboardpage_top_3_best_adapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.book_author.setText(list.get(position).getBook_author());
        holder.book_name.setText(list.get(position).getBook_name());
        holder.rank.setText("No."+(position+1));
        holder.book_view.setText(list.get(position).getBook_watch());
        //holder.rank.setText(list.get(position).getBook_watch());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView rank,book_name,book_author,book_view;
        //ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_name = itemView.findViewById(R.id.leaderboardpage_top_3_best_book_name);
            book_author = itemView.findViewById(R.id.leaderboardpage_top_3_best_book_author);
            rank = itemView.findViewById(R.id.leaderboardpage_top_3_best_rank);
            book_view = itemView.findViewById(R.id.leaderboardpage_top_3_best_book_watch);
            //img = itemView.findViewById(R.id.leaderboardpage_top_3_best_book_image);
        }

    }

    public interface RecyclerviewClick{
        void onClick(View v, int position);
    }
}
