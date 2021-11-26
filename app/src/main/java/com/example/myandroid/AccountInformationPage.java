package com.example.myandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class AccountInformationPage extends AppCompatActivity {

    TextView username,email,type,description_text;
    Button custom_profile;

    String emailss;

    RecyclerView all_account_list,friend_list;

    CircleImageView user_img_display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_information_page);

        username = findViewById(R.id.accountpage_text_username);
        email = findViewById(R.id.accountpage_email_username);
        type = findViewById(R.id.accountpage_type_username);
        custom_profile = findViewById(R.id.edit_profile);
        description_text = findViewById(R.id.accountpage_description_username);

        all_account_list = findViewById(R.id.username_recommend_list);
        friend_list = findViewById(R.id.friend_list);

        user_img_display = findViewById(R.id.accountpage_img_username);

        Bundle getEmail_text = getIntent().getExtras();
        emailss = getEmail_text.getString("email");
        email.setText(emailss);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("thongtintaikhoan");
        databaseReference.orderByChild("email").equalTo(email.getText().toString()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Account_infor infor = dataSnapshot.getValue(Account_infor.class);
                    username.setText(infor.getUsername());
                    if(infor.getAcc_type().equals("0")){
                        type.setText("Người dùng thông thường");
                    }
                    description_text.setText(infor.getDescription());
                    Glide.with(user_img_display.getContext()).load(infor.getImgsrc()).into(user_img_display);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        custom_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogSetUp(Gravity.CENTER);
            }
        });

        LoadAllAccount();
    }

    private void CustomDialogSetUp(int gravity) {
        final Dialog custom_dialog = new Dialog(this);
        custom_dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        custom_dialog.setContentView(R.layout.account_informationpage_edit_dialog);

        Window window = custom_dialog.getWindow();
        if(window == null){
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        WindowManager.LayoutParams win_attr = window.getAttributes();
        win_attr.gravity = gravity;
        window.setAttributes(win_attr);

        if(Gravity.BOTTOM == gravity){
            custom_dialog.setCancelable(true);
        }else{
            custom_dialog.setCancelable(false);
        }
        EditText username_name = custom_dialog.findViewById(R.id.editprofile_username_text);
        EditText username_description = custom_dialog.findViewById(R.id.editprofile_descriptions_text);

        Button got_cancel = custom_dialog.findViewById(R.id.edit_dialog_cancel);
        Button got_edit = custom_dialog.findViewById(R.id.edit_dialog_confirm);

        DatabaseReference account_infor_Reference = FirebaseDatabase.getInstance().getReference("thongtintaikhoan");

        account_infor_Reference.orderByChild("username").equalTo(username.getText().toString())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                            Account_infor acc = dataSnapshot.getValue(Account_infor.class);
                            username_description.setText(acc.getDescription());
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        got_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                custom_dialog.dismiss();
            }
        });

        got_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap detail_change = new HashMap();
                detail_change.put("description",username_description.getText().toString());
                detail_change.put("acc_type","0");
                detail_change.put("point","0");
                detail_change.put("username",username.getText().toString());
                detail_change.put("imgsrc","null");
                detail_change.put("email",email.getText().toString());

                String get_email = email.getText().toString();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("thongtintaikhoan");
                databaseReference.child(get_email.replace(".","")).updateChildren(detail_change).addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()){
                            Toast.makeText(AccountInformationPage.this, "Thông tin đã được thay đổi !!", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(AccountInformationPage.this, "Thông tin không thể thay đổi !!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        custom_dialog.show();
    }


    private void LoadAllAccount(){
        ArrayList<Account_infor> acc_list = new ArrayList<>();

        AccountInformationPage_all_account_adapter all_account_adapter = new AccountInformationPage_all_account_adapter(acc_list);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("thongtintaikhoan");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Account_infor infor = dataSnapshot.getValue(Account_infor.class);
                    acc_list.add(infor);
                    all_account_adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        all_account_list.setLayoutManager(new LinearLayoutManager(this));
        all_account_list.setItemAnimator(new DefaultItemAnimator());
        all_account_list.setAdapter(all_account_adapter);
    }

}