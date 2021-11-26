package com.example.myandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BookDetailPage extends AppCompatActivity{

    private ArrayList<Book_chapter> chapter_lists;
    private ArrayList<Ranking> rank_list;

    private RecyclerView recycleview;
    private RecyclerView commend_view;

    BookDetail_chapter_list_adapter.RecycleViewListenner listenner;

    TextView textView,textView2,textView3, rank_point_view;
    EditText editText;
    Button send_comment, send_report, close_report, buy_book;
    ImageButton report_book_btn, favorite_library_btn;

    ImageView book_img_view;
    RelativeLayout report_frame;
    Spinner spinner, spinner2;

    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail_page);

        //ImageView
        book_img_view = findViewById(R.id.image_book_display);

        //Recycler View
        recycleview = findViewById(R.id.chapter_list_view);
        commend_view = findViewById(R.id.comment_section_area);

        //TextView
        textView = findViewById(R.id.detail_book_name);
        textView2 = findViewById(R.id.detail_author_name);
        textView3 = findViewById(R.id.detail_type_txt);

        //Button
        send_comment = findViewById(R.id.comment_add_btn);
        send_report = findViewById(R.id.report_send_btn);
        buy_book = findViewById(R.id.spend_point_btn);
        report_book_btn = findViewById(R.id.bookdetailpage_report_btn);
        close_report = findViewById(R.id.report_send_close_btn);

        //Spinner
        spinner = findViewById(R.id.select_rank);
        spinner2 = findViewById(R.id.bookdetail_report_choose);

        ///ImageView
        report_frame = findViewById(R.id.report_frame);
        rank_point_view = findViewById(R.id.bookdetailpage_total_rank_point);
        favorite_library_btn = findViewById(R.id.bookdetailpage_favorite_list);

        //Edit text
        editText = findViewById(R.id.bookdetail_book_commend);

        //Mo bao cao dau sach
        report_book_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycleview.setVisibility(View.GONE);
                report_frame.setVisibility(View.VISIBLE);
            }
        });


        //Gui bao cao dau sach
        ArrayAdapter<CharSequence> char_adapter2 = ArrayAdapter.createFromResource(getApplicationContext(),R.array.bookdetail_spiner_report,android.R.layout.simple_spinner_item);
        char_adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        send_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                spinner.setAdapter(char_adapter2);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        String report_content = parent.getItemAtPosition(position).toString();

                        DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("baocao");
                        databaseRef.child(textView.getText().toString()).setValue(new Comment_report(user,textView.getText().toString(),report_content,"2"));

                        Toast.makeText(BookDetailPage.this, "Cảm ơn bạn đã báo cáo", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }
        });


        //Dong bao cao dau sach
        close_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycleview.setVisibility(View.VISIBLE);
                report_frame.setVisibility(View.GONE);
            }
        });

        //Mo bang chon diem
        ArrayAdapter<CharSequence> char_adapter = ArrayAdapter.createFromResource(this,R.array.bookdetail_spiner,android.R.layout.simple_spinner_item);
        char_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(char_adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                /*String rank_point = parent.getItemAtPosition(position).toString();

                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("danhgia");
                databaseRef.push().setValue(new Ranking(user,textView.getText().toString(),rank_point));

                Toast.makeText(BookDetailPage.this, "Cảm ơn bạn đã đánh giá", Toast.LENGTH_SHORT).show();*/
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Lay thong tin sach
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("binhluan");

        Bundle getExtra = getIntent().getExtras();
        textView.setText(getExtra.getString("book_name"));
        textView2.setText(getExtra.getString("book_author"));
        textView3.setText(getExtra.getString("book_watch"));
        user = getExtra.getString("username");
        String img_links = getExtra.getString("book_img");


        Glide.with(book_img_view.getContext()).load(img_links).into(book_img_view);

        //Lay so diem can mua

        DatabaseReference set_point_ref = FirebaseDatabase.getInstance().getReference().child("chitietsach");
        set_point_ref.orderByChild("book_name").equalTo(textView.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Book_detail detail = dataSnapshot.getValue(Book_detail.class);
                    buy_book.setText(detail.getBook_point());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //Binh luan
        send_comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.push().setValue(new Comment_report(user,textView.getText().toString(),editText.getText().toString(),"1"));
                Toast.makeText(getApplicationContext(), "Bạn đã bình luận", Toast.LENGTH_SHORT).show();
            }
        });

        //Them vao thu vien yeu thich
        favorite_library_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("uathich");
                databaseReference.push().setValue(new Favorite_book(user,textView.getText().toString(),textView2.getText().toString(),textView3.getText().toString()));
                Toast.makeText(getApplicationContext(), "Bạn đã thêm vào danh sách ưa thích", Toast.LENGTH_SHORT).show();
            }
        });

        buy_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int book_price = Integer.parseInt(buy_book.getText().toString());
                int user_point = Integer.parseInt(getExtra.getString("point"));

                if(book_price > user_point){
                    Toast.makeText(BookDetailPage.this, "Bạn không đủ điểm để mua", Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        chapter_lists = new ArrayList<>();
        setAdapter();
        SetCommentArea();
    }


    //Load cac chuong sach
    private void setAdapter() {
        setOnClickListenner();
        BookDetail_chapter_list_adapter adapter = new BookDetail_chapter_list_adapter(chapter_lists ,listenner);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("chuong");

        databaseReference.orderByChild("book_name")
                .equalTo(textView.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Book_chapter chapter = dataSnapshot.getValue(Book_chapter.class);
                    chapter_lists.add(chapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recycleview.setLayoutManager(manager);
        recycleview.setItemAnimator(new DefaultItemAnimator());
        recycleview.setAdapter(adapter);
    }

    //Load noi dung sau khi bam vao chuong sach
    private void setOnClickListenner() {
        listenner = new BookDetail_chapter_list_adapter.RecycleViewListenner() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), ChapterDetailPage.class);
                intent.putExtra("book_chapter",chapter_lists.get(position).getBook_chapter());
                intent.putExtra("book_chapter_content",chapter_lists.get(position).getBook_chapter_content());
                startActivity(intent);
            }
        };
    }

    //Load toan bo binh luan ve dau sach nay
    public void SetCommentArea(){
        ArrayList<Comment_report> comment_list = new ArrayList<>();
        BookDetailPage_commend_adapter comment_adapter = new BookDetailPage_commend_adapter(comment_list);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("binhluan");
        databaseReference.orderByChild("book_name").equalTo(textView.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                   Comment_report comment_class = dataSnapshot.getValue(Comment_report.class);
                   comment_list.add(comment_class);
                   comment_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        commend_view.setLayoutManager(new LinearLayoutManager(this));
        commend_view.setItemAnimator(new DefaultItemAnimator());
        commend_view.setAdapter(comment_adapter);

    }

}