package com.example.planeer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RecAdapter extends RecyclerView.Adapter {
    ArrayList<model> data;
    Context context;
    int ps;

    public RecAdapter(ArrayList<model> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        return data.get(position).getCatInt();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new mvh0(LayoutInflater.from(context).inflate(R.layout.sr0, parent, false));
        } else if (viewType == 1) {

            return new mvh1(LayoutInflater.from(context).inflate(R.layout.sr1, parent, false));
        } else if (viewType == 2) {

            return new mvh2(LayoutInflater.from(context).inflate(R.layout.sr2, parent, false));
        } else {
            return new mvh3(LayoutInflater.from(context).inflate(R.layout.sr3, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {


        if (holder instanceof mvh0) {
            ((mvh0) holder).title.setText(data.get(getItemCount() - position - 1).getTitle());
            ((mvh0) holder).category.setText(data.get(getItemCount() - position - 1).getCategory());

            ((mvh0) holder).card0.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Task Manager here..");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Edit Task",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(context, editTask.class);
                                    intent.putExtra("title",data.get(position).getTitle());
                                    intent.putExtra("category",data.get(position).getCategory());
                                    intent.putExtra("catInt",data.get(position).getCatInt());
                                    intent.putExtra("uid",data.get(position).getUid());
                                    intent.putExtra("desc",data.get(position).getDesc());

                                    context.startActivity(intent);

                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "Delete Task",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("task").child(user.getUid()).child(data.get(position).getTitle());
                                    ref1.removeValue();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                }
            });


        } else if (holder instanceof mvh1) {

            ((mvh1) holder).title1.setText(data.get(getItemCount() - position - 1).getTitle());
            ((mvh1) holder).category1.setText(data.get(getItemCount() - position - 1).getCategory());


            ((mvh1) holder).card1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Task Manager here..");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Edit Task",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(context, editTask.class);
                                    intent.putExtra("title",data.get(position).getTitle());
                                    intent.putExtra("category",data.get(position).getCategory());
                                    intent.putExtra("catInt",data.get(position).getCatInt());
                                    intent.putExtra("uid",data.get(position).getUid());
                                    intent.putExtra("desc",data.get(position).getDesc());

                                    context.startActivity(intent);

                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "Delete Task",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("task").child(user.getUid()).child(data.get(position).getTitle());
                                    ref1.removeValue();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();


                }
            });


        } else if (holder instanceof mvh2) {

            ((mvh2) holder).title2.setText(data.get(getItemCount() - position - 1).getTitle());
            ((mvh2) holder).category2.setText(data.get(getItemCount() - position - 1).getCategory());


            ((mvh2) holder).card2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Task Manager here..");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Edit Task",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(context, editTask.class);
                                    intent.putExtra("title",data.get(position).getTitle());
                                    intent.putExtra("category",data.get(position).getCategory());
                                    intent.putExtra("catInt",data.get(position).getCatInt());
                                    intent.putExtra("uid",data.get(position).getUid());
                                    intent.putExtra("desc",data.get(position).getDesc());

                                    context.startActivity(intent);

                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "Delete Task",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("task").child(user.getUid()).child(data.get(position).getTitle());
                                    ref1.removeValue();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            });


        } else {
            ((mvh3) holder).title3.setText(data.get(getItemCount() - position - 1).getTitle());
            ((mvh3) holder).category3.setText(data.get(getItemCount() - position - 1).getCategory());


            ((mvh3) holder).card3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                    builder1.setMessage("Task Manager here..");
                    builder1.setCancelable(true);

                    builder1.setPositiveButton(
                            "Edit Task",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    Intent intent = new Intent(context, editTask.class);
                                    intent.putExtra("title",data.get(position).getTitle());
                                    intent.putExtra("category",data.get(position).getCategory());
                                    intent.putExtra("catInt",data.get(position).getCatInt());
                                    intent.putExtra("uid",data.get(position).getUid());
                                    intent.putExtra("desc",data.get(position).getDesc());

                                    context.startActivity(intent);

                                    dialog.cancel();
                                }
                            });

                    builder1.setNegativeButton(
                            "Delete Task",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("task").child(user.getUid()).child(data.get(position).getTitle());
                                    ref1.removeValue();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }
            });

        }

    }

    private void editData(int ps) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Task Manager here..");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Edit Task",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        Intent intent = new Intent(context, editTask.class);
                        context.startActivity(intent);

                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "Delete Task",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference("njnvjnjnjnj");
                        //   ref1.child(data.get(position)./* jisko delete krna h i mean jis key value k andar wale ko delete krna h*/).removeValue();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public class mvh0 extends RecyclerView.ViewHolder {

        TextView title, category;
        CardView card0;


        public mvh0(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.Title);
            category = itemView.findViewById(R.id.cat);
            card0 = itemView.findViewById(R.id.card0);
        }
    }

    public class mvh1 extends RecyclerView.ViewHolder {

        TextView title1, category1;
        CardView card1;

        public mvh1(@NonNull View itemView) {
            super(itemView);

            title1 = itemView.findViewById(R.id.Title1);
            category1 = itemView.findViewById(R.id.cat1);
            card1 = itemView.findViewById(R.id.card1);
        }
    }

    public class mvh2 extends RecyclerView.ViewHolder {

        TextView title2, category2;
        CardView card2;

        public mvh2(@NonNull View itemView) {
            super(itemView);

            title2 = itemView.findViewById(R.id.Title2);
            category2 = itemView.findViewById(R.id.cat2);
            card2 = itemView.findViewById(R.id.card2);
        }
    }

    public class mvh3 extends RecyclerView.ViewHolder {

        TextView title3, category3;
        CardView card3;

        public mvh3(@NonNull View itemView) {
            super(itemView);

            title3 = itemView.findViewById(R.id.Title3);
            category3 = itemView.findViewById(R.id.cat3);
            card3 = itemView.findViewById(R.id.card3);
        }
    }

}
