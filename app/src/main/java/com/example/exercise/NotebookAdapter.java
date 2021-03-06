package com.example.exercise;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.RecyclerView;

import com.example.exercise.models.NotebookModel;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class NotebookAdapter extends RecyclerView.Adapter<NotebookAdapter.ViewHolder> {

    private final ArrayList<NotebookModel> notebookModels;
    private final ArrayList<NotebookAdapter> NotebookAdapter = new ArrayList<>();
    private final Context context;

    public NotebookAdapter(Context context, ArrayList<NotebookModel> notebookModels) {
        this.notebookModels = notebookModels;
        this.context = context;
    }

    @NonNull
    @Override
    public NotebookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotebookAdapter.ViewHolder viewHolder, int i) {

        final NotebookModel temp = notebookModels.get(i);
        viewHolder.title.setText(notebookModels.get(i).getTitle());
        viewHolder.desc.setText(notebookModels.get(i).getDescription());
        viewHolder.itemView.setOnClickListener(v -> {
            //Second Activity Data
            Intent intent = new Intent(context, SecondActivity.class);
            intent.putExtra("imagname", temp.getImage());
            intent.putExtra("titlename", temp.getTitle());
            intent.putExtra("desname", temp.getDescription());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        });
        //Imgs Management
        Picasso.get()
                .load(notebookModels.get(i).getImage())
                .into(viewHolder.image, new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i("imgLOAD", "SUCCESS");
                    }

                    @Override
                    public void onError(Exception e) {
                        Log.i("400", "ERROR" + e);
                        Picasso.get().load("https://seoheronews.com/403%20error%20or%20forbidden.png").into(viewHolder.image);

                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(context, SecondActivity.class);
                                intent.putExtra("imagname", "https://seoheronews.com/403%20error%20or%20forbidden.png");
                                intent.putExtra("titlename", temp.getTitle());
                                intent.putExtra("desname", temp.getDescription());
                                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        });
                    }
                });
    }

    @Override
    public int getItemCount() {
        return notebookModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView title;
        private final TextView desc;

        @SuppressLint("ResourceType")
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.iv_Img);
            title = (TextView) itemView.findViewById(R.id.tv_title);
            desc = (TextView) itemView.findViewById(R.id.tv_SubTitle);
        }
    }
}

