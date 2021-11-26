package com.example.myandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class Adminpage_BookManager_edit_book extends AppCompatActivity {

    EditText book_name,book_author,book_date,book_type,book_point,book_watch;
    TextView img_link, file_src_link;
    Button confirm_update_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage_book_manager_edit_book);

        book_name = findViewById(R.id.edit_book_booktitle_text);
        book_author = findViewById(R.id.edit_book_author_text);
        book_date = findViewById(R.id.edit_book_date_text);
        book_type = findViewById(R.id.edit_book_types_text);
        book_point = findViewById(R.id.edit_book_price_text);
        book_watch = findViewById(R.id.edit_book_view_text);

        img_link = findViewById(R.id.edit_book_image_file_txt);
        file_src_link = findViewById(R.id.add_new_book_src_file_txt);

        confirm_update_btn = findViewById(R.id.edit_book_button);

        Bundle getExtra = getIntent().getExtras();
        if(getExtra != null){
            book_name.setText(getExtra.getString("book_name"));
        }

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("chitietsach");
        databaseReference.orderByChild("book_name")
                .equalTo(book_name.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Book_detail detail = dataSnapshot.getValue(Book_detail.class);
                    book_author.setText(detail.getBook_author());
                    book_date.setText(detail.getBook_datetime());
                    book_type.setText(detail.getBook_type());
                    book_point.setText(detail.getBook_point());
                    book_watch.setText(detail.getBook_view());

                    img_link.setText(detail.getBook_img());
                    file_src_link.setText(detail.getBook_src_link());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        confirm_update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SetDataOnTheList();
            }
        });

    }

    private void SetDataOnTheList(){
        HashMap detail_change = new HashMap();
        detail_change.put("book_name",book_name.getText().toString());
        detail_change.put("book_author",book_author.getText().toString());
        detail_change.put("book_type",book_type.getText().toString());
        detail_change.put("book_datetime",book_date.getText().toString());
        detail_change.put("book_point",book_point.getText().toString());
        detail_change.put("book_view",book_watch.getText().toString());
        detail_change.put("book_img",img_link.getText().toString());
        detail_change.put("book_src_link",file_src_link.getText().toString());

        DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                .getReference("chitietsach");
        databaseReference.child(book_name.getText().toString())
                .updateChildren(detail_change).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    Toast.makeText(Adminpage_BookManager_edit_book.this, "Đầu sách đã được cập nhật", Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(Adminpage_BookManager_edit_book.this, "Không thể cập nhật đầu sách", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}