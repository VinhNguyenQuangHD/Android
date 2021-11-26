package com.example.myandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.paperdb.Paper;

public class LoginPage extends AppCompatActivity implements View.OnClickListener {

    EditText textView,textView2;
    Button button;
    ImageButton google_login_btn, facebook_login_btn;
    CheckBox remember_me;
    TextView forgot_passwords;

    private FirebaseAuth db_auth;
    private GoogleSignInClient googleSignInClient;

    private static final int RC_SIGN_IN = 9001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login_page);

        google_login_btn = (ImageButton) findViewById(R.id.google_login_btn);
        facebook_login_btn = (ImageButton) findViewById(R.id.facebook_login_btn);

        textView = (EditText) findViewById(R.id.edittext_username);
        textView2 = (EditText) findViewById(R.id.edittext_password);

        forgot_passwords = (TextView) findViewById(R.id.forgot_password);
        forgot_passwords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(LoginPage.this,ForgotPasswordsPage.class);
                    i.putExtra("emails",textView.getText().toString());
                    startActivity(i);
            }
        });

        button = findViewById(R.id.login_button);
        button.setOnClickListener(this);

        remember_me = findViewById(R.id.remember_pass);
        Paper.init(this);

        RequestGoogleSignIn();
        db_auth = FirebaseAuth.getInstance();

        google_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignInProgress();
            }
        });
    }

    public void Change_to_SignUp(View view){
        Intent intent = new Intent(this,SignInPage.class);
        startActivity(intent);
    }

    public void Change_to_mainpage(){

        String get_email = textView.getText().toString().trim();
        String get_passwords = textView2.getText().toString().trim();


        if(get_email.isEmpty() ){
            textView.setError("Email không được trống");
            textView.requestFocus();
            return;}
        if(get_passwords.isEmpty()){
            textView2.setError("Mật khẩu không được trống");
            textView2.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(get_email).matches()){
            textView.setError("Email phải hợp lệ");
            textView.requestFocus();
            return;
        }
        db_auth.signInWithEmailAndPassword(textView.getText().toString(),textView2.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            if(remember_me.isChecked()){
                                Paper.book().write(Account_remember.remember_username,get_email);
                                Paper.book().write(Account_remember.remember_password,get_passwords);
                            }

                            Intent intent = new Intent(LoginPage.this,MainHomePage.class);
                            intent.putExtra("email",get_email);
                            startActivity(intent);
                        }else{
                            Toast.makeText(LoginPage.this, "Đăng nhập thất bại,vui lòng thử lại",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login_button:
                Change_to_mainpage();
                break;
        }
    }

    private void RequestGoogleSignIn(){
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("292441433380-od7aq0l7urtifn6avqpet2vfg3jbclj5.apps.googleusercontent.com")
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this,gso);
    }

    private void SignInProgress(){
        Intent signinIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signinIntent,RC_SIGN_IN);
    }

}