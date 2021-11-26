package com.example.myandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookDetail_chapter_list_adapter extends RecyclerView.Adapter<BookDetail_chapter_list_adapter.MyViewHolder>  {

    private ArrayList<Book_chapter> chapter_page;
    RecycleViewListenner listenner;

    public BookDetail_chapter_list_adapter(ArrayList<Book_chapter> homepage_view, RecycleViewListenner listenner) {
        this.chapter_page = homepage_view;
        this.listenner = listenner;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView text,text2,text3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.book_chapter_title);
            text2 = itemView.findViewById(R.id.book_chapter_name);
            text3 = itemView.findViewById(R.id.book_chapter_content);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listenner.onClick(itemView,getAdapterPosition());
        }
    }


        @NonNull
    @Override
    public BookDetail_chapter_list_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.recycleview_bookdetailpage_chapter_list,parent,false);
            return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookDetail_chapter_list_adapter.MyViewHolder holder, int position) {
        holder.text.setText(chapter_page.get(position).getBook_chapter());
    }

    @Override
    public int getItemCount() {
        return chapter_page.size();
    }

    public interface RecycleViewListenner{
        void onClick(View v, int position);
    }
}
