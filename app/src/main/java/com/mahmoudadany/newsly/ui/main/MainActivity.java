package com.mahmoudadany.newsly.ui.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.mahmoudadany.newsly.R;
import com.mahmoudadany.newsly.databinding.ActivityMainBinding;
import com.mahmoudadany.newsly.ui.main.Interface.FavoriteFragment;
import com.mahmoudadany.newsly.ui.main.Interface.HomeFragment;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);

        binding.mainBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            int lastId;
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        if(item.getItemId()==lastId) break;
                        lastId=item.getItemId();
                        HomeFragment home=HomeFragment.newInstance(getSupportFragmentManager());
                        break;
                    case R.id.favorite:
                        if(item.getItemId()==lastId) break;
                        lastId=item.getItemId();
                        Fragment favoritefragmet= FavoriteFragment.newInstance(getSupportFragmentManager());
                        break;
                }

                return true;
            }
        });

        binding.mainBottomNavigationView.setSelectedItemId(R.id.home);


    }
}