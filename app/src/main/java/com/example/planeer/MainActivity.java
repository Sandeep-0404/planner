package com.example.planeer;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton actionButton;
    RecyclerView revView;
    model model;
    RecAdapter recAdapter;
    ArrayList<model> data;
    NavigationView nav;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nav = findViewById(R.id.navmenu);
        drawerLayout = findViewById(R.id.drawer);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ll);
        getSupportActionBar().setTitle(" ");


        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {

                    case R.id.myprofile:

                        Intent intent1 = new Intent(getApplicationContext(), myProfile.class);
                        startActivity(intent1);

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.logout:
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(getApplicationContext(), login.class);
                        startActivity(intent);
                        finish();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_share:
                        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody = "App made by sandeep ghosh";
                        String shareSubject = "Made by sandeep ghosh";

                        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSubject);

                        startActivity(Intent.createChooser(sharingIntent, "Share Using"));

                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.add_task:
                        chooseTask chooseTask = new chooseTask();
                        chooseTask.show(getSupportFragmentManager(), chooseTask.getTag());
                        drawerLayout.closeDrawer(GravityCompat.START);

                        break;

                }


                return true;
            }
        });


        actionButton = findViewById(R.id.fab);
        revView = findViewById(R.id.recView);
        revView.setLayoutManager(new LinearLayoutManager(this));
        data = new ArrayList<>();

        recAdapter = new RecAdapter(data, this);
        revView.setAdapter(recAdapter);
        getData();


        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                chooseTask chooseTask = new chooseTask();
                chooseTask.show(getSupportFragmentManager(), chooseTask.getTag());

            }
        });
    }

    private void getData() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("task").child(uid);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data.clear();
                for (DataSnapshot s : snapshot.getChildren()) {


                    model model = s.getValue(com.example.planeer.model.class);
                    model md = new model();
                    md.setCatInt(model.getCatInt());
                    md.setTitle(model.getTitle());
                    md.setCategory(model.getCategory());
                    data.add(md);

                }
                recAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    public void logout(View view) {

        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(getApplicationContext(), login.class);
        startActivity(intent);
        finish();

    }
}