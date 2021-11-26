package com.example.myandroid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Adminpage_BookManager_Addnewbook extends AppCompatActivity {

    EditText bookname,author,datetime,pricepoint,view,chapter,chapter_content,types;
    TextView imglink,srclink;
    Button button, imglink_btn, add_chapter_btn, src_link_btn;
    ImageButton refresh_btn;
    ImageView imageView;

    private final int FILE_FROM_DEVICE = 1001;

    StorageReference storageRe, pdfstorageRef;
    Uri imagelink,pdflink;

    DatabaseReference book_overal_data, book_detail_data, book_chapter_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminpage_book_manager_addnewbook);
        //Edit text
        bookname = findViewById(R.id.add_new_book_booktitle_text);
        author = findViewById(R.id.add_new_book_author_text);
        datetime = findViewById(R.id.add_new_book_date_text);
        pricepoint = findViewById(R.id.add_new_book_price_text);
        view = findViewById(R.id.add_new_book_view_text);
        chapter = findViewById(R.id.add_new_chapter_book_text);
        chapter_content = findViewById(R.id.add_new_chapter_book_description_text);
        types = findViewById(R.id.add_new_book_types_text);

        //Text view
        imglink = findViewById(R.id.add_new_book_image_file_txt);
        srclink = findViewById(R.id.add_new_book_src_file_txt);

        //Button
        imglink_btn = findViewById(R.id.add_new_book_file_directory_button);
        refresh_btn = findViewById(R.id.add_new_chapter_reset_button);
        button = findViewById(R.id.add_new_book_button);
        src_link_btn = findViewById(R.id.add_new_book_src_file_directory_button);
        add_chapter_btn = findViewById(R.id.add_new_chapter_button);

        //ImageView
        imageView = findViewById(R.id.add_new_book_book_image_uri);

        book_overal_data = FirebaseDatabase.getInstance().getReference().child("sach");
        book_chapter_data = FirebaseDatabase.getInstance().getReference().child("chuong");
        book_detail_data = FirebaseDatabase.getInstance().getReference().child("chitietsach");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBookData();
            }
        });

        src_link_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertBookSource();
            }
        });

        add_chapter_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                storageRe = FirebaseStorage.getInstance().getReference().child("images/" + bookname.getText().toString()+"_images");
                storageRe.putFile(imagelink).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Adminpage_BookManager_Addnewbook.this,"Tai anh thanh cong",Toast.LENGTH_LONG).show();

                        }else
                        {
                            Toast.makeText(Adminpage_BookManager_Addnewbook.this,"Tai anh that bai",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                pdfstorageRef = FirebaseStorage.getInstance().getReference().child("pdf_file/" + bookname.getText().toString() + "_src");
                pdfstorageRef.putFile(pdflink).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Adminpage_BookManager_Addnewbook.this,"Tai tep thanh cong",Toast.LENGTH_LONG).show();

                        }else
                        {
                            Toast.makeText(Adminpage_BookManager_Addnewbook.this,"Tai tep that bai",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                insertChapterBook();
            }
        });

        refresh_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chapter.setText("");
                chapter_content.setText("");
            }
        });

        imglink_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFileLink();
            }
        });

    }

    private void insertBookSource() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("application/pdf");
        startActivityForResult(intent,FILE_FROM_DEVICE);
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
                    imagelink = data.getData();
                    imageView.setImageURI(imagelink);
                    imglink.setText(imagelink.getPath().toString());

                }
                break;
            case FILE_FROM_DEVICE:
                if(requestCode == FILE_FROM_DEVICE && resultCode==RESULT_OK){
                    pdflink = data.getData();
                    srclink.setText(data.getData().getPath().toString());
                }
                break;
        }
    }

    private void insertChapterBook() {
        String new_book_name = bookname.getText().toString();
        String new_book_chapter = chapter.getText().toString();
        String new_book_chapter_content = chapter_content.getText().toString();

        if(new_book_name.isEmpty()){
            bookname.setError("Tên sách không được trống");
            bookname.requestFocus();
            return;
        }

        if(new_book_chapter.isEmpty()){
            chapter.setError("Tên chương không được trống");
            chapter.requestFocus();
            return;
        }

        if(new_book_chapter_content.isEmpty()){
            chapter_content.setError("Thời gian không được trống");
            chapter_content.requestFocus();
            return;
        }

            Book_chapter chapter = new Book_chapter(new_book_chapter,new_book_chapter_content,new_book_name);
            book_chapter_data.push().setValue(chapter);

        Toast.makeText(Adminpage_BookManager_Addnewbook.this,"Đã thêm chương sách thành công",Toast.LENGTH_LONG).show();
    }

    private void insertBookData() {
        String new_book_name = bookname.getText().toString();
        String new_book_author = author.getText().toString();
        String new_book_date = datetime.getText().toString();
        String new_book_price = pricepoint.getText().toString();
        String new_book_view = view.getText().toString();
        String new_book_imglink = imglink.getText().toString();
        String new_book_srcfile = srclink.getText().toString();
        String new_book_types = types.getText().toString();

        if(new_book_name.isEmpty()){
            bookname.setError("Tên sách không được trống");
            bookname.requestFocus();
            return;
        }

        if(new_book_author.isEmpty()){
            author.setError("Tên tác giả không được trống");
            author.requestFocus();
            return;
        }

        if(new_book_date.isEmpty()){
            datetime.setError("Thời gian không được trống");
            datetime.requestFocus();
            return;
        }

        if(new_book_view.isEmpty()){
            view.setError("Lượt đọc không được trống");
            view.requestFocus();
            return;
        }

            Book_overal book = new Book_overal(new_book_author,new_book_name,new_book_view
                    ,new_book_types,new_book_price,new_book_date, new_book_imglink);
            Book_detail detail = new Book_detail(new_book_name,new_book_author,new_book_view
                    ,new_book_price,new_book_date,new_book_imglink,new_book_srcfile,new_book_types);


            book_overal_data.child(new_book_name).setValue(book);
            book_detail_data.child(new_book_name).setValue(detail);

            author.setText("");
            view.setText("");
            datetime.setText("");

            Toast.makeText(Adminpage_BookManager_Addnewbook.this,"Đã thêm thành công",Toast.LENGTH_LONG).show();

    }
}