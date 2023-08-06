package com.mahmoudadany.newsly.ui.main.Interface;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.mahmoudadany.newsly.R;
import com.mahmoudadany.newsly.data.Shard;
import com.mahmoudadany.newsly.pojo.NewsModel;
import com.mahmoudadany.newsly.ui.main.NewsAdapter;
import com.mahmoudadany.newsly.ui.main.NewsViewModel;
import com.mahmoudadany.newsly.ui.main.WorldNewsAdapter;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView worldnewsrecyclerView;
    RadioGroup groupp;
    RadioButton all;
    NewsAdapter adapter;
    WorldNewsAdapter worldNewsAdapter;
    NewsViewModel viewModel;
    SwitchCompat switchCompat;
    Shard shard;


    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(FragmentManager manager) {
        HomeFragment fragment = new HomeFragment();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.main_frame, fragment);
        transaction.commit();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        shard=Shard.getInstance(getContext());
        switchCompat=view.findViewById(R.id.setting_sc_darkmode);
        switchCompat.setChecked(shard.getFromShard());
        all = view.findViewById(R.id.rb_all);
        groupp = view.findViewById(R.id.testtest);
        recyclerView = view.findViewById(R.id.recycler);
        worldnewsrecyclerView = view.findViewById(R.id.worldnews_recycler);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        infalat();
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                shard.addInShard(isChecked);
            }
        });
        viewModel.WorldNews();


        viewModel.liveDataEgyptNews.observe(getActivity(), new Observer<NewsModel>() {
            @Override
            public void onChanged(NewsModel newsModel) {
                adapter.setList(newsModel.getData());
                recyclerView.setAdapter(adapter);
            }
        });
        viewModel.liveDataWorldNews.observe(getActivity(), new Observer<NewsModel>() {
            @Override
            public void onChanged(NewsModel newsModel) {

                worldNewsAdapter.setList(newsModel.getData());
                worldnewsrecyclerView.setAdapter(worldNewsAdapter);
            }
        });

//        viewModel.liveDataCategoryNews.observe(getActivity(), new Observer<NewsModel>() {
//            @Override
//            public void onChanged(NewsModel newsModel) {
//                Toast.makeText(getContext(), newsModel.getData().get(0).getTitle(), Toast.LENGTH_SHORT).show();
//                adapter.setList(newsModel.getData());
//                recyclerView.setAdapter(adapter);
//            }
//        });
        groupp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                String category = radioButton.getText().toString();
                if (category.equals("All")) {
                    viewModel.EgyptNews();
                    return;
                }
                //viewModel.CategoryNew("health ");
            }
        });
        all.setChecked(true);


    }

    private void infalat() {
        adapter = new NewsAdapter();
        worldNewsAdapter = new WorldNewsAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        worldnewsrecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        viewModel = ViewModelProviders.of(this).get(NewsViewModel.class);
    }
}