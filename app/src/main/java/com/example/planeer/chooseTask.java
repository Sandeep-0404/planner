package com.example.planeer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;


public class chooseTask extends BottomSheetDialogFragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    Button save;
    EditText desc, title;
    String category;
    int catInt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_choose_task, container, false);


        spinner = v.findViewById(R.id.category);
        desc = v.findViewById(R.id.desc);
        title = v.findViewById(R.id.title);
        save = v.findViewById(R.id.save);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String title1=title.getText().toString().trim();
                String desc1=desc.getText().toString().trim();

                if(title1.isEmpty()){
                    title.setError("Can't be empty");
                    title.requestFocus();
                    return;

                }




                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                String uid = user.getUid();

                if (category.equals("Work")) catInt = 0;
                else if (category.equals("study")) catInt = 1;
                else if (category.equals("Gym")) catInt = 2;
                else if (category.equals("Games")) catInt = 3;
                else catInt = -1;

                DatabaseReference db = FirebaseDatabase.getInstance().getReference("task").child(uid);
                model mp = new model();
                mp.setCatInt(catInt);
                mp.setCategory(category);
                mp.setUid(uid);
                mp.setTitle(title.getText().toString().trim());
                mp.setDesc(desc.getText().toString().trim());
                db.push().setValue(mp);
                Toast.makeText(getContext(), "Task Successfully added", Toast.LENGTH_SHORT).show();

                title.setText("");
                desc.setText("");

            }
        });


        List<String> category = new ArrayList<>();
        category.add("Work");
        category.add("study");
        category.add("Gym");
        category.add("Games");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, category);
        spinner.setAdapter(arrayAdapter);

        spinner.setOnItemSelectedListener(this);


        return v;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        category = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}