package com.example.planeer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class testAdapter extends RecyclerView.Adapter<testAdapter.vh> {

    ArrayList<model> data;

    public testAdapter(ArrayList<model> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.sr0,parent,false);
        return  new testAdapter.vh(v);
    }

    @Override
    public void onBindViewHolder(@NonNull vh holder, int position) {

        holder.tView.setText(data.get(position).getTitle());

    }

    @Override
    public int getItemCount() {

        return data.size();
    }

    public class vh extends RecyclerView.ViewHolder {
TextView tView;

        public vh(@NonNull View itemView) {
            super(itemView);
            tView=itemView.findViewById(R.id.Title);
        }
    }
}
