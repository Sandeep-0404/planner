package com.example.planeer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class editTask extends AppCompatActivity {

    EditText title2,desc2;
    String title;
    String category,uid,desc;
    int catInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        title2=findViewById(R.id.title2);
        desc2=findViewById(R.id.desc2);

        Intent intent=getIntent();
         title=intent.getStringExtra("title");
         category=intent.getStringExtra("category");
         uid=intent.getStringExtra("uid");
         desc=intent.getStringExtra("desc");

         title2.setText(title);
         desc2.setText(desc);
    }

    public void editData(View view) {

        String str= title2.getText().toString().trim();
        if(str.isEmpty()){

            title2.setError("Can't be Empty");
            title2.requestFocus();
            return;
        }


        DatabaseReference ref=FirebaseDatabase.getInstance().getReference("task").child(uid).child(title);
        ref.removeValue();


        DatabaseReference ref1=FirebaseDatabase.getInstance().getReference("task").child(uid).child(title2.getText().toString().trim());
model model=new model();
model.setUid(uid);
model.setDesc(desc2.getText().toString());
model.setCatInt(catInt);
model.setTitle(title2.getText().toString().trim());
model.setCategory(category);
ref1.setValue(model);
        Toast.makeText(getApplicationContext(),"Value Updated",Toast.LENGTH_SHORT).show();
startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
}