package com.example.ordersqlite;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class FragmentBottomAdapter extends FragmentStatePagerAdapter {

    private int numPager = 5;

    public FragmentBottomAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new OrderFragment();

            case 1:
                return new MessageFragment();

            case 2:
                return new NewsFragment();

            case 3:
                return new MoreFragment();
            case 4:
                return new InfoFragment();

            default:
                return new OrderFragment();
        }
    }

    @Override
    public int getCount() {
        return numPager;
    }
}
