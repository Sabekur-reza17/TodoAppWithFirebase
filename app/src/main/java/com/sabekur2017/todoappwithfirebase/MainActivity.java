package com.sabekur2017.todoappwithfirebase;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sabekur2017.todoappwithfirebase.adapter.RecyclerAdapter;
import com.sabekur2017.todoappwithfirebase.crudmodels.AddToDoActivity;
import com.sabekur2017.todoappwithfirebase.models.Todo;

import java.util.ArrayList;
import java.util.Collections;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "main";





    private Toolbar mToolBar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private CircleImageView navProfilePicture;
    private TextView navProfileName;
    private FloatingActionButton floatingActionButton;
    private DatabaseReference mDatabase;
    private FirebaseAuth mAuth;
    private ProgressDialog progressDialog ;
    String Database_Path = "AllTodoList";
    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    ArrayList<Todo> todoArrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //////////////////// firebase ////////////////////////
        mAuth = FirebaseAuth.getInstance();
//        String pushidOfTodo=mDatabase.push().getKey();
        String userCurrentUid=mAuth.getCurrentUser().getUid();
        progressDialog=new ProgressDialog(MainActivity.this);

        mDatabase= FirebaseDatabase.getInstance().getReference(Database_Path).child(userCurrentUid);

        ////////////////// toolbar ////////////////////
        mToolBar= findViewById(R.id.main_toolbar_for_navview);
        floatingActionButton=findViewById(R.id.floatingActionButton);
        // mToolBar.setNavigationIcon(R.drawable.hambargaricon);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setTitle(R.string.app_name);
        drawerLayout=findViewById(R.id.drawer_layout);
        actionBarDrawerToggle=new ActionBarDrawerToggle(MainActivity.this,drawerLayout,R.string.drawer_open,R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        // getSupportActionBar().setHomeAsUpIndicator(R.drawable.hambargaricon);
        // works for pre lollipoop and after lollipop
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        navigationView=findViewById(R.id.naviagtion_view);
        View navViewHeader=navigationView.inflateHeaderView(R.layout.navigation_header);
        navProfilePicture= navViewHeader. findViewById(R.id.circleImageViewNavHeader);
        navProfileName=navViewHeader.findViewById(R.id.landlord_name_navHeader);
        /////////////////// recyclerview /////////////////////////////
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        progressDialog.setMessage("Data loading");
        progressDialog.setCancelable(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                todoArrayList.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Todo todo=snapshot.getValue(Todo.class);
                    todoArrayList.add(todo);
                }
                Collections.reverse(todoArrayList);
                recyclerAdapter=new RecyclerAdapter(MainActivity.this,todoArrayList);
                recyclerView.setAdapter(recyclerAdapter);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ///////////////////// floating action button add data start ///////////////////////
         floatingActionButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent=new Intent(MainActivity.this, AddToDoActivity.class);
                 startActivity(intent);
             }
         });
        ///////////////////// floating action button add data end ///////////////////////
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                UserMenuSelector(item);
                return false;
            }
        });
    }
    private void UserMenuSelector(MenuItem item) {
        switch (item.getItemId()){
            case R.id.nav_faq:

                Toast.makeText(this,"Go to faq Activity",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_about_us:

                Toast.makeText(this,"Go to about us activity Activity",Toast.LENGTH_LONG).show();
                break;
            case R.id.nav_faq_from_tenant:


                Toast.makeText(this,"Go to Faq tenant Question From Tenant Activity", Toast.LENGTH_LONG).show();
                break;

            case R.id.nav_log_out:
                FirebaseAuth.getInstance().signOut();
                sendToStart();
                // Toast.makeText(this,"Log out from main activity",Toast.LENGTH_LONG).show();
                break;


        }

    }
    private void sendToStart() {
        Intent startIntent=new Intent(MainActivity.this,LoginActivity.class);
        startActivity(startIntent);
        finish();
    }


}
