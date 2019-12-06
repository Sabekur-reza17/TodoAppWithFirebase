package com.sabekur2017.todoappwithfirebase;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sabekur2017.todoappwithfirebase.models.Todo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="main";

FirebaseAuth firebaseAuth;
FirebaseUser firebaseUser;
String database_path="Alldoto";
EditText dataPicker_edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth=FirebaseAuth.getInstance();
        firebaseUser=firebaseAuth.getCurrentUser();
        dataPicker_edt=findViewById(R.id.datePicker_edt);


        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveTodo();
                Toast.makeText(getApplicationContext(),"dataSave",Toast.LENGTH_LONG).show();
            }
        });


    }
    private void saveTodo(){
        EditText nameEdtText = findViewById(R.id.editText);
        EditText messageEditText = findViewById(R.id.editText2);

        DatePicker datePicker = findViewById(R.id.datePicker1);
        Date date = new Date();
        date.setMonth(datePicker.getMonth());
        date.setYear(datePicker.getYear());
        date.setDate(datePicker.getDayOfMonth());


        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        String dateString = format.format(date);
        //make the modal object and convert it into hasmap
        String currentUser=firebaseUser.getUid();
        Log.d("uid",currentUser);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        String key = database.getReference("todoList").child(database_path).child(currentUser).push().getKey();
        Todo todo = new Todo();
        todo.setName(nameEdtText.getText().toString());
        todo.setMessage(messageEditText.getText().toString());
        todo.setDate(dateString);
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put( key, todo.toFirebaseObject());
        database.getReference("todoList").child(database_path).child(currentUser).updateChildren(childUpdates, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseError == null) {
                    finish();
                }
            }
        });


    }
}
