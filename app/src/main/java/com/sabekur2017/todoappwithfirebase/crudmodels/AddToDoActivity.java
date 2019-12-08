package com.sabekur2017.todoappwithfirebase.crudmodels;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sabekur2017.todoappwithfirebase.R;
import com.sabekur2017.todoappwithfirebase.models.Todo;

public class AddToDoActivity extends AppCompatActivity {
   // private FirebaseUser currentUser;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    String Database_Path = "AllTodoList";
    private EditText title,description;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_to_do);
        mAuth = FirebaseAuth.getInstance();
        //Assign FirebaseDatabase instance with Root database name
        mDatabase= FirebaseDatabase.getInstance().getReference(Database_Path);
        ////////////// alll view //////////////////////////
        title=findViewById(R.id.title_edt);
        description=findViewById(R.id.description_edt);
        saveBtn=findViewById(R.id.save_btn);
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitTodoToCurrentUser();
            }
        });
    }
    public void submitTodoToCurrentUser(){
        String pushidOfTodo=mDatabase.push().getKey();
        String userCurrentUid=mAuth.getCurrentUser().getUid();
        if(validateFrom()){
            String title_data=title.getText().toString().trim();
            String description_data=description.getText().toString().trim();
            Todo todo=new Todo();
            todo.setTitle(title_data);
            todo.setDescription(description_data);
            todo.setCurrentUserId(userCurrentUid);
            todo.setCurrentPushId(pushidOfTodo);
            if(mAuth.getCurrentUser() !=null){
                mDatabase.child(userCurrentUid).child(pushidOfTodo).setValue(todo, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                        if(databaseError ==null){
                            Toast.makeText(AddToDoActivity.this,"Data saved successfully",Toast.LENGTH_LONG).show();
                            finish();
                        }
                    }
                });
            }
        }
    }
    public  boolean validateFrom(){
        boolean TodoData=true;
        String title_data=title.getText().toString().trim();
        String description_data=description.getText().toString().trim();
        if(TextUtils.isEmpty(title_data)){
            title.setError("Enter title");
            return false;
        }else {
            TodoData=true;
            title.setError(null);

        }
        if(TextUtils.isEmpty(description_data)){
            description.setError("Enter description");
            return false;
        }else {
            TodoData=true;
            description.setError(null);
        }
        return TodoData;
    }
}
