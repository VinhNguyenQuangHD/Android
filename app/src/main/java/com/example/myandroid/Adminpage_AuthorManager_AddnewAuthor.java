package com.example.myandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Adminpage_AuthorManager_AddnewAuthor extends AppCompatActivity {

    EditText authorname,author_description;
    Button button;
    DatabaseReference author_data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage_author_manager_addnew_author);

        authorname = findViewById(R.id.add_new_author_authortitle_text);
        author_description = findViewById(R.id.add_new_author_author_description_text);
        button = findViewById(R.id.add_new_author_button);

        author_data = FirebaseDatabase.getInstance().getReference().child("tacgia");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertAuthorData();
            }
        });
    }

    private void insertAuthorData() {
            String new_author_name = authorname.getText().toString();
            String new_author_description = author_description.getText().toString();

            if(new_author_name.isEmpty()){
                authorname.setError("Tên tác giả không được trống");
                authorname.requestFocus();
                return;
            }

            if(new_author_description.isEmpty()){
                author_description.setError("Mô tả tác giả không được trống");
                author_description.requestFocus();
                return;
            }

            Author author = new Author(new_author_name,new_author_description,"null");

            author_data.child(new_author_name.replace("."," ")).setValue(author);

            Toast.makeText(Adminpage_AuthorManager_AddnewAuthor.this,"Đã thêm tác giả thành công",Toast.LENGTH_LONG).show();

    }
}