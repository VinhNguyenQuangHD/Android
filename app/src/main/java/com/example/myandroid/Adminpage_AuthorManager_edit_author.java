package com.example.myandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class Adminpage_AuthorManager_edit_author extends AppCompatActivity {

    EditText author_name,author_description;
    TextView img_link;
    Button img_link_btn, author_edit_btn;
    Uri imglink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage_author_manager_edit_author);

        author_name = findViewById(R.id.edit_author_author_text);
        author_description = findViewById(R.id.edit_author_description_author_text);

        img_link = findViewById(R.id.edit_author_image_file_txt);

        img_link_btn = findViewById(R.id.edit_author_file_directory_button);
        author_edit_btn = findViewById(R.id.edit_author_button);

        Bundle getExtra = getIntent().getExtras();

        img_link.setText(getExtra.getString("author_img_src"));
        author_name.setText(getExtra.getString("author_name"));
        author_description.setText(getExtra.getString("author_description"));

        img_link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFileLink();
            }
        });
        
        author_edit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap detail_change = new HashMap();
                detail_change.put("author_description",author_description.getText().toString());
                detail_change.put("author_img",img_link.getText().toString());

                String get_author_name = author_name.getText().toString();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance()
                        .getReference("tacgia");
                databaseReference.child(get_author_name.replace("."," "))
                        .updateChildren(detail_change).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Adminpage_AuthorManager_edit_author.this, "Tac gia đã được cập nhật", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            Toast.makeText(Adminpage_AuthorManager_edit_author.this, "Không thể cập nhật tac gia", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    void getFileLink(){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent,10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 10:
                if(resultCode==RESULT_OK){
                    imglink = data.getData();
                    img_link.setText(imglink.getPath().toString());
                }
                break;
        }
    }
}