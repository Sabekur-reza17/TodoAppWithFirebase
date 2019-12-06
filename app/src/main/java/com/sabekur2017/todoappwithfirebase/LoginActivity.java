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

public class LoginActivity extends AppCompatActivity {
    private EditText email_edt,pass_edt;
    private Button loginBtn,resetBtn,singUpBtn;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email_edt=findViewById(R.id.mail_edt);
        pass_edt=findViewById(R.id.pass_edt);
        loginBtn=findViewById(R.id.login_btn);
        resetBtn=findViewById(R.id.reset_btn);
        singUpBtn=findViewById(R.id.sign_up_btn);
        progressBar=findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        //move to main activity if user already sign in
        if (firebaseAuth.getCurrentUser() != null) {
            // User is logged in
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
        singUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this,SignupActivity.class));
            }
        });
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.this.startActivity(new Intent(LoginActivity.this,ResetActivity.class));
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String useremail = email_edt.getText().toString();
                final String userpassword = pass_edt.getText().toString();
                if (TextUtils.isEmpty(useremail)) {
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(userpassword)) {
                    Toast.makeText(LoginActivity.this.getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                // user login
                firebaseAuth.signInWithEmailAndPassword(useremail,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if(!task.isSuccessful()){
                            if(userpassword.length() <6){
                                pass_edt.setError(LoginActivity.this.getString(R.string.minimum_password));
                            }else {
                                Toast.makeText(LoginActivity.this, LoginActivity.this.getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            LoginActivity.this.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            LoginActivity.this.finish();
                        }
                    }
                });

            }
        });

    }
}
