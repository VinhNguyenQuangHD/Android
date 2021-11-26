package com.example.myandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class OptionPage extends AppCompatActivity {

    ImageButton admin_mode_btn, account_btn, notification_btn, author_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_page);

        Bundle get_email_string = getIntent().getExtras();
        String email = get_email_string.getString("emails");

        admin_mode_btn = findViewById(R.id.admin_modes_btn);
        admin_mode_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionPage.this,AdminModepage.class));
            }
        });

        account_btn = findViewById(R.id.account_option_btn);
        account_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OptionPage.this,AccountInformationPage.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        notification_btn = findViewById(R.id.personal_option_btn);
        notification_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(OptionPage.this,NotificationPage.class);
                i.putExtra("email",email);
                startActivity(i);
            }
        });

        author_btn = findViewById(R.id.author_list_btn);
        author_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OptionPage.this,AuthorPage.class));
            }
        });
    }
}