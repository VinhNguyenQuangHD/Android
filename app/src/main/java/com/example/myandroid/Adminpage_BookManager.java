package com.example.myandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Adminpage_BookManager extends AppCompatActivity {

    Button new_book_btn;
    private ArrayList<Book_overal> list;
    private RecyclerView recycleview;
    private Adminpage_option_update_delete_adapter adapter;
    private Adminpage_option_update_delete_adapter.RecyclerviewClick click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_adminpage_book_manager);

        new_book_btn = findViewById(R.id.add_new_book_addbook_btn);
        recycleview = findViewById(R.id.list_of_curent_book);


        new_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Adminpage_BookManager.this,Adminpage_BookManager_Addnewbook.class));
            }
        });

        recycleview.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();


        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("sach");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Book_overal overal = dataSnapshot.getValue(Book_overal.class);
                    list.add(overal);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        SetOnDeleteClick();
        adapter = new Adminpage_option_update_delete_adapter(click, list);
        recycleview.setAdapter(adapter);
    }

    private void SetOnDeleteClick() {
        click = new Adminpage_option_update_delete_adapter.RecyclerviewClick() {
            @Override
            public void onClickDelete(View v, int position) {
                String key = list.get(position).getBook_name().toString();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                Query query = databaseReference.child("sach").orderByChild("book_name").equalTo(key);

                Query detail_query = databaseReference.child("chitietsach").orderByChild("book_name").equalTo(key);

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            dataSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                detail_query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                            dataSnapshot.getRef().removeValue();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onClickUpdate(View v, int position) {
                Intent intent = new Intent(Adminpage_BookManager.this,Adminpage_BookManager_edit_book.class);
                intent.putExtra("book_name",list.get(position).getBook_name());
                startActivity(intent);
            }
        };
    }


}