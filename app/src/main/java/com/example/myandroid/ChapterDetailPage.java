package com.example.myandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Documented;
import java.net.HttpURLConnection;
import java.net.URL;

public class ChapterDetailPage extends AppCompatActivity {
    private TextView text;

    private PDFView text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chapter_detail_page);
        text = findViewById(R.id.chapter_text_2);
        text2 = findViewById(R.id.chapter_content);

        Bundle getExtra = getIntent().getExtras();
        {
            if(getExtra != null){
                text.setText(getExtra.getString("book_chapter"));
            }
        }

        String url = getExtra.getString("book_chapter_content");

        new RetirevePdfStream().execute(url);
    }

    class RetirevePdfStream extends AsyncTask<String,Void,InputStream>{

        @Override
        protected InputStream doInBackground(String... strings) {
            InputStream inputStream = null;
            try{
                    URL url = new URL(strings[0]);
                    HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    if(connection.getResponseCode() == 200){
                        inputStream = new BufferedInputStream(connection.getInputStream());
                    }

            }catch (Exception ex){ex.printStackTrace();}
            return inputStream;
        }

        @Override
        protected void onPostExecute(InputStream inputStream) {
            text2.fromStream(inputStream).load();
        }
    }
}