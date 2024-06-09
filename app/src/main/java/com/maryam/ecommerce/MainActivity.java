package com.maryam.ecommerce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.homeIcon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new HomeFragment()).commit();
                        return true;
                    case R.id.browseIcon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new BrowseFragment()).commit();
                        return true;
                    case R.id.heartIcon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FavouritesFragment()).commit();
                        return true;
                    case R.id.cartIcon:
                        getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new BasketFragment()).commit();
                        return true;
                }
                return false;
            }
        });

        // Set default fragment
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.homeIcon);
        }

    }

//    HomeFragment homeFragment = new HomeFragment();
//    BrowseFragment browseFragment = new BrowseFragment();
//    FavouritesFragment favouritesFragment = new FavouritesFragment();
//    BasketFragment basketFragment = new BasketFragment();
//
//    @Override
//    public boolean onNavigationItemSelected(@NonNull MenuItem item){
//        switch(item.getItemId()){
//            case R.id.homeIcon:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, homeFragment).commit();
//                return true;
//            case R.id.browseIcon:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, browseFragment).commit();
//                return true;
//            case R.id.heartIcon:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, favouritesFragment).commit();
//                return true;
//            case R.id.cartIcon:
//                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, basketFragment).commit();
//                return true;
//        }
//        return false;
//    }
}