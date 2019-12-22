package com.sabekur2017.todoappwithfirebase;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupActivity extends AppCompatActivity {
    private EditText sign_email_edt,sign_pass_edt;
    private Button sign_RegisterBtn;
    private ProgressBar sign_progressBar;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        sign_email_edt=findViewById(R.id.sign_up_mail_edt);
        sign_pass_edt=findViewById(R.id.sign_up_pass_edt);
        sign_RegisterBtn=findViewById(R.id.sign_up_btn);
        sign_progressBar=findViewById(R.id.sign_progressBar);
        firebaseAuth = FirebaseAuth.getInstance();

        sign_RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SignupActivity.this.registerUser();
            }
        });
    }
    private void registerUser(){
        String userEmail = sign_email_edt.getText().toString().trim();
        String userPassword = sign_pass_edt.getText().toString().trim();
        if (TextUtils.isEmpty(userEmail)) {
            showToast("Enter email address!");
            return;
        }

        if(TextUtils.isEmpty(userPassword)){
            showToast("Enter Password!");
            return;
        }

        if(userPassword.length() < 6){
            showToast("Password too short, enter minimum 6 characters");
            return;
        }

        sign_progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(SignupActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(!task.isSuccessful()){
                    SignupActivity.this.showToast("Authentication failed. " + task.getException());
                }else {
                    SignupActivity.this.startActivity(new Intent(SignupActivity.this, MainActivity.class));
                    SignupActivity.this.finish();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sign_progressBar.setVisibility(View.GONE);
    }

    public void showToast(String toastText) {
        Toast.makeText(this, toastText, Toast.LENGTH_SHORT).show();
    }
}
