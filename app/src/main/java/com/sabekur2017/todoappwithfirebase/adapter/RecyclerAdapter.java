package com.sabekur2017.todoappwithfirebase.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sabekur2017.todoappwithfirebase.R;
import com.sabekur2017.todoappwithfirebase.models.Todo;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyCustomViewHolder> {
    Context context;
    List<Todo> todoList;
    private OnDeleteIconClickListener deleteIconClickListener;
    private OnEditIconClickListener editIconClickListener;

    public RecyclerAdapter(Context context, List<Todo> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public MyCustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyceler_item, parent, false);
        MyCustomViewHolder viewHolder=new MyCustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyCustomViewHolder holder, int position) {
        Todo todo=todoList.get(position);
        holder.title.setText(todo.getTitle());
        holder.description.setText(todo.getDescription());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
    public void setOnDeleteIconClickListener(OnDeleteIconClickListener onDeleteIconClickListener){
        deleteIconClickListener = onDeleteIconClickListener;
    }

    public void setOnEditIconClickListener(OnEditIconClickListener onEditIconClickListener){
        editIconClickListener = onEditIconClickListener;
    }

    public interface OnDeleteIconClickListener{
        void onDeleteClick(String parentName);
    }

    public interface OnEditIconClickListener{
        void onEditClick(String parentName);
    }
    class MyCustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title;
        public TextView description;
        Button enter_this_post,edit_post,delete_post;

        public MyCustomViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title_txt);
            description=itemView.findViewById(R.id.description_txt);
            enter_this_post=itemView.findViewById(R.id.detailsActivity_btn);
            edit_post=itemView.findViewById(R.id.update_todo);
            delete_post=itemView.findViewById(R.id.delete_todo);
            enter_this_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Details Click",Toast.LENGTH_LONG).show();

                }
            });
            edit_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Edit Click",Toast.LENGTH_LONG).show();

                }
            });
            delete_post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context,"Delate  Click",Toast.LENGTH_LONG).show();


                }
            });
        }

        @Override
        public void onClick(View v) {

        }
    }
}
