package com.example.myandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignInPage extends AppCompatActivity implements View.OnClickListener{

    EditText username,email,password,confirmpass;
    Button signupbtn;
    ProgressBar progressBar;
    private FirebaseAuth db_auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_sign_in_page);

        db_auth = FirebaseAuth.getInstance();

        username = (EditText) findViewById(R.id.edittext_signup_username);
        email = (EditText) findViewById(R.id.edittext_signup_email);
        password = (EditText) findViewById(R.id.edittext_signup_password);
        confirmpass = (EditText) findViewById(R.id.edittext_signup_rewrite_password);
        signupbtn = findViewById(R.id.signin_button);
        progressBar = (ProgressBar) findViewById(R.id.progress_stage);
        signupbtn.setOnClickListener(this);
    }

    private void RegisterProgress() {
        String get_email = email.getText().toString().trim();
        String get_username = username.getText().toString().trim();
        String get_passwords = password.getText().toString().trim();
        String get_re_write_password = confirmpass.getText().toString().trim();
        if(get_email.isEmpty() ){
            email.setError("Email không được trống");
            email.requestFocus();
            return;
        }else if(get_username.isEmpty() ){
            username.setError("Tên đăng nhập không được trống");
            username.requestFocus();
            return;
        } else if(get_passwords.isEmpty()){
            password.setError("Mật khẩu không được trống");
            password.requestFocus();
            return;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(get_email).matches()){
            email.setError("Email phải hợp lệ");
            email.requestFocus();
            return;
        }
        else if(!get_re_write_password.equals(get_passwords)){
            confirmpass.setError("Mật khẩu nhập lại không khớp");
            confirmpass.requestFocus();
            return;
        }else if(get_passwords.length() < 6){
            password.setError("Độ dài mật khẩu không dưới 6 ký tự");
            password.requestFocus();
            return;
        }else if(get_username.length() >= 21){
            username.setError("Tên đăng nhập quá dài");
            username.requestFocus();
            return;
        }
            progressBar.setVisibility(View.VISIBLE);
            db_auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Account account = new Account(get_username,get_email,get_passwords);
                            FirebaseDatabase.getInstance().getReference("taikhoan").
                            child(get_email.replace(".",""))
                            .setValue(account).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("thongtintaikhoan");
                                        Account_infor infor = new Account_infor(get_username,"0",get_email,"null","0","descriop");
                                        databaseReference.child(get_email.replace(".","")).setValue(infor);

                                        startActivity(new Intent(SignInPage.this,MainHomePage.class));

                                        progressBar.setVisibility(View.VISIBLE);
                                        Toast.makeText(SignInPage.this, "Tài khoản đã được đăng ký thành công",Toast.LENGTH_LONG).show();

                                    }else{
                                        Toast.makeText(SignInPage.this, "Đăng ký thất bại,vui lòng thử lại",Toast.LENGTH_LONG).show();
                                        progressBar.setVisibility(View.GONE);
                                    }

                                }
                            });
                        }else
                        {
                            Toast.makeText(SignInPage.this, "Không thể đăng ký",Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signin_button:
                RegisterProgress();
                break;

        }
    }

    public void ToLogin(View view){
        startActivity(new Intent(this,LoginPage.class));
    }
}