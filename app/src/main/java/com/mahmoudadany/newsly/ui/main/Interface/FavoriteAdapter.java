package com.mahmoudadany.newsly.ui.main.Interface;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mahmoudadany.newsly.R;
import com.mahmoudadany.newsly.data.NewsDatabase;
import com.mahmoudadany.newsly.pojo.Data;
import com.mahmoudadany.newsly.ui.present.PresentNews;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.TestViewHolder> {
    private List<Data> list = null;
    Context context;

    public void setList(List<Data> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext().getApplicationContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_item, null, false);
        TestViewHolder holder = new TestViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, @SuppressLint("RecyclerView") int position) {
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

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsDatabase database = NewsDatabase.getInstance(v.getContext());
                database.getDao().deleteItem(list.get(position).getId())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onComplete() {
                                list.remove(position);
                                notifyDataSetChanged();
                                Toast.makeText(context, "complete to remove item", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {

                            }
                        });
            }
        });

    }

    @Override
    public int getItemCount() {
        if (list == null) return 0;
        return list.size();
    }

    class TestViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        ImageView view, delete;

        public TestViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.favorite_item_tv_title);
            view = itemView.findViewById(R.id.favorite_item_iv_image);
            delete = itemView.findViewById(R.id.favorite_item_iv_delete);
        }
    }
}
