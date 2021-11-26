package com.example.myandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Librarypage_lib_recommend_adapter extends RecyclerView.Adapter<Librarypage_lib_recommend_adapter.MyViewHolder>{

    private ArrayList<Librarypage_lib_recommend> recommend_lib;

    public Librarypage_lib_recommend_adapter(ArrayList<Librarypage_lib_recommend> recommend_lib) {
        this.recommend_lib = recommend_lib;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text,text2,text3;
        private ImageView img,img2,img3;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            text = itemView.findViewById(R.id.lib_book_name);
            text2 = itemView.findViewById(R.id.lib_author_name);
            text3 = itemView.findViewById(R.id.lib_watch_num);
            img = itemView.findViewById(R.id.lib_book_images);
            img2 = itemView.findViewById(R.id.lib_watch_icon);
            img3= itemView.findViewById(R.id.plush_icon);
        }
    }

    @NonNull
    @Override
    public Librarypage_lib_recommend_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_librarypage_list_recommend,parent,false);
        return new Librarypage_lib_recommend_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Librarypage_lib_recommend_adapter.MyViewHolder holder, int position) {
        String text1 = recommend_lib.get(position).getTen_sach_lib(),
                text2 = recommend_lib.get(position).getTen_tac_gia_lib(),
                text3 = recommend_lib.get(position).getLuot_xem_lib();
        holder.text.setText(text1);
        holder.text2.setText(text2);
        holder.text3.setText(text3);
        int img = recommend_lib.get(position).getHinh_sach(),
                img2 = recommend_lib.get(position).getIcon_luot_xem(),
                img3 = recommend_lib.get(position).getIcon_them();
        holder.img.setImageResource(img);
        holder.img2.setImageResource(img2);
        holder.img3.setImageResource(img3);
    }

    @Override
    public int getItemCount() {
        return recommend_lib.size();
    }
}
