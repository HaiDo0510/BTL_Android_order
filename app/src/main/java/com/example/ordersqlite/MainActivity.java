package com.example.ordersqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private BottomNavigationView bottomnav;
    private FragmentBottomAdapter adapter1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        bottomnav = findViewById(R.id.bottom_navigation);
        adapter1 = new FragmentBottomAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        bottomnav.getMenu().findItem(R.id.nav_order).setChecked(true);
                        break;
                    case 1:
                        bottomnav.getMenu().findItem(R.id.nav_message).setChecked(true);
                        break;
                    case 2:
                        bottomnav.getMenu().findItem(R.id.nav_news).setChecked(true);
                        break;
                    case 3:
                        bottomnav.getMenu().findItem(R.id.nav_more).setChecked(true);
                        break;
                    case 4:
                        bottomnav.getMenu().findItem(R.id.nav_info).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_order:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_message:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_news:
                        viewPager.setCurrentItem(2);
                        break;
                    case R.id.nav_more:
                        viewPager.setCurrentItem(3);
                        break;
                    case R.id.nav_info:
                        viewPager.setCurrentItem(4);
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_nagivation,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_order:
                viewPager.setCurrentItem(0);
                break;
            case R.id.nav_message:
                viewPager.setCurrentItem(1);
                break;
            case R.id.nav_news:
                viewPager.setCurrentItem(2);
                break;
            case R.id.nav_more:
                viewPager.setCurrentItem(3);
                break;
            case R.id.nav_info:
                viewPager.setCurrentItem(4);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}