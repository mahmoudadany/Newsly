package com.mahmoudadany.newsly.ui.present;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.mahmoudadany.newsly.R;
import com.mahmoudadany.newsly.data.NewsDatabase;
import com.mahmoudadany.newsly.databinding.ActivityPresentNewsBinding;
import com.mahmoudadany.newsly.pojo.Data;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PresentNews extends AppCompatActivity {
    NewsDatabase database;
    Data data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPresentNewsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_present_news);
        inflat();

        Glide.with(this)
                .load(Uri.parse(data.getImage()))
                .centerCrop()
                .into(binding.presentnewsIvPhoto);
        binding.presentnewsTvAuther.setText(data.getAuthor());
        binding.presentnewsTvData.setText(data.getPublished_at());
        binding.presentnewsTvTitle.setText(data.getTitle());
        binding.presentnewsTvDescription.setText(data.getDescription());

        binding.presentnewsIvGoback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.presentnewsIvSavenews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInRoom(data);
            }
        });


        binding.openbrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Intent.ACTION_VIEW);
                intent1.setData(Uri.parse(data.getUrl()));
                startActivity(intent1);
            }
        });
    }

    private void inflat() {
        database = NewsDatabase.getInstance(this);
        Intent intent = getIntent();
        data = (Data) intent.getSerializableExtra("item");
    }


    public void addInRoom(Data data) {
        database.getDao().insertNewData(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        Toast.makeText(PresentNews.this, data.getTitle(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }
                });
    }


}