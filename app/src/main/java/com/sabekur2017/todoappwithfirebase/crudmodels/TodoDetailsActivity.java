package com.sabekur2017.todoappwithfirebase.crudmodels;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.sabekur2017.todoappwithfirebase.R;
import com.sabekur2017.todoappwithfirebase.models.Todo;

public class TodoDetailsActivity extends AppCompatActivity {
    private TextView title,description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_details);
        title=findViewById(R.id.title_details);
        description=findViewById(R.id.description_details);
        Todo todo=getIntent().getParcelableExtra("detailstodo");
        title.setText(todo.getTitle());
        description.setText(todo.getDescription());
    }
}
