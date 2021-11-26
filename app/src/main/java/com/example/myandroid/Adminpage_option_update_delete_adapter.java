package com.example.myandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adminpage_option_update_delete_adapter extends RecyclerView.Adapter<Adminpage_option_update_delete_adapter.MyViewHolder> {

    private RecyclerviewClick click;
    private ArrayList<Book_overal> list;

    public Adminpage_option_update_delete_adapter(RecyclerviewClick click, ArrayList<Book_overal> list) {
        this.click = click;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_adminpage_remove_book_on_list,parent,false);

        return new Adminpage_option_update_delete_adapter.MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Book_overal overal = list.get(position);

        holder.book_author.setText(overal.getBook_author());
        holder.book_name.setText(overal.getBook_name());
        holder.book_watch.setText(overal.getBook_watch());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView book_name,book_author,book_watch;
        ImageButton btn, update_btn;
        //ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_name = itemView.findViewById(R.id.book_name);
            book_author = itemView.findViewById(R.id.author_name);
            book_watch = itemView.findViewById(R.id.watch_num);
            btn = itemView.findViewById(R.id.adminpage_option_delete_icon);
            update_btn = itemView.findViewById(R.id.adminpage_option_edit_icon);

            update_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onClickUpdate(v,getAdapterPosition());
                }
            });
            //img = itemView.findViewById(R.id.book_images);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    click.onClickDelete(v,getAdapterPosition());
                }
            });

        }

    }

    public interface RecyclerviewClick{
        void onClickDelete(View v, int position);
        void onClickUpdate(View v, int position);
    }
}
