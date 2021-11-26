package com.example.myandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Leaderboardpage_leaderboard_detail_adapter extends RecyclerView.Adapter<Leaderboardpage_leaderboard_detail_adapter.MyViewHolder> {

    private ArrayList<Book_overal> list;

    public Leaderboardpage_leaderboard_detail_adapter(ArrayList<Book_overal> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_leaderboardpage_leaderboard_detail,parent,false);

        return new Leaderboardpage_leaderboard_detail_adapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.book_author.setText(list.get(position).getBook_author());
        holder.book_name.setText(list.get(position).getBook_name());
        holder.book_watch.setText(list.get(position).getBook_watch());
        holder.rank.setText("#"+(position + 1));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView book_name,book_author,book_watch,rank;
        //ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_name = itemView.findViewById(R.id.leaderboard_detail_book_name);
            book_author = itemView.findViewById(R.id.leaderboard_detail_book_author);
            book_watch = itemView.findViewById(R.id.leaderboard_detail_book_watch);
            rank = itemView.findViewById(R.id.leaderboard_detail_rank);
            //img = itemView.findViewById(R.id.book_images);
        }

    }
}
