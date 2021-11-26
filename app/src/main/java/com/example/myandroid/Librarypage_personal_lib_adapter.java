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

public class Librarypage_personal_lib_adapter extends RecyclerView.Adapter<Librarypage_personal_lib_adapter.MyViewHolder>{

    private ArrayList<Librarypage_personal_lib> personal_libs;

    public Librarypage_personal_lib_adapter(ArrayList<Librarypage_personal_lib> personal_libs) {
        this.personal_libs = personal_libs;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView text,text2,text3;
        private ImageView img,img2,img3,img4;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            text = itemView.findViewById(R.id.lib_name);
            text2 = itemView.findViewById(R.id.lib_des);
            text3 = itemView.findViewById(R.id.detail_btn);
            img = itemView.findViewById(R.id.first_picture);
            img2 = itemView.findViewById(R.id.second_picture);
            img3= itemView.findViewById(R.id.third_picture);
            img4= itemView.findViewById(R.id.fourth_picture);
        }
    }

    @NonNull
    @Override
    public Librarypage_personal_lib_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycleview_librarypage_list_personal_library,parent,false);
        return new Librarypage_personal_lib_adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Librarypage_personal_lib_adapter.MyViewHolder holder, int position) {
        String text1 = personal_libs.get(position).getText1(),
                text2 = personal_libs.get(position).getText2(),
                text3 = personal_libs.get(position).getText3();
        holder.text.setText(text1);
        holder.text2.setText(text2);
        holder.text3.setText(text3);
        int img = personal_libs.get(position).getPic(),
                img2 = personal_libs.get(position).getPic2(),
                img3 = personal_libs.get(position).getPic3(),
                img4 = personal_libs.get(position).getPic4();
        holder.img.setImageResource(img);
        holder.img2.setImageResource(img2);
        holder.img3.setImageResource(img3);
        holder.img4.setImageResource(img4);
    }

    @Override
    public int getItemCount() {
        return personal_libs.size();
    }
}
