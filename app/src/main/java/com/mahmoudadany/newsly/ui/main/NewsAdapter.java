package com.mahmoudadany.newsly.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mahmoudadany.newsly.R;
import com.mahmoudadany.newsly.pojo.Data;
import com.mahmoudadany.newsly.ui.present.PresentNews;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private List<Data> list = null;
    Context context;

    public void setList(List<Data> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext().getApplicationContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.newsitem, null, false);
        NewsViewHolder holder = new NewsViewHolder(view);
        return holder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        holder.textView.setText(list.get(position).getTitle());
        if (list.get(position).getImage() == null) return;
        Uri uri = Uri.parse(list.get(position).getImage());
        Glide.with(context).load(uri).centerCrop().into(holder.view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PresentNews.class);
                Data data = (Data) list.get(position);
                intent.putExtra("item", data);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView view;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.news_item_tv_title);
            view = itemView.findViewById(R.id.news_item_iv_image);
        }

    }
}
