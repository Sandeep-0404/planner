package com.example.planeer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class myProfile extends AppCompatActivity {

    TextView email;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        email=findViewById(R.id.email);
        image=findViewById(R.id.image);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        email.setText(user.getEmail());
        Glide.with(this).load(user.getPhotoUrl()).into(image);

    }
}