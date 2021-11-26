package com.example.myandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BookDetail_same_type_adapter extends RecyclerView.Adapter<BookDetail_same_type_adapter.MyViewHolder>{
    private ArrayList<Librarypage_lib_recommend> same_type_list;

    public BookDetail_same_type_adapter(ArrayList<Librarypage_lib_recommend> recommend_lib) {
        this.same_type_list = recommend_lib;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text,text2;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            text = itemView.findViewById(R.id.recycleview_same_type_book_name);
            text2 = itemView.findViewById(R.id.recycleview_same_type_author_name);
            img = itemView.findViewById(R.id.recycleview_same_type_image);
        }
    }

    @NonNull
    @Override
    public BookDetail_same_type_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_bookdetail_same_type,parent,false);
        return new BookDetail_same_type_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BookDetail_same_type_adapter.MyViewHolder holder, int position) {
        String text1 = same_type_list.get(position).getTen_sach_lib(),
                text2 = same_type_list.get(position).getTen_tac_gia_lib();
        holder.text.setText(text1);
        holder.text2.setText(text2);
        int img = same_type_list.get(position).getHinh_sach();
        holder.img.setImageResource(img);
    }

    @Override
    public int getItemCount() {
        return same_type_list.size();
    }
}
