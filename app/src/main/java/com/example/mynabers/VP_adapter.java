package com.example.mynabers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VP_adapter extends FragmentPagerAdapter {

    private ArrayList<ollNeighbors> myollNeighbors;

    public VP_adapter(FragmentManager fm, ArrayList<ollNeighbors> fragments) {
        super(fm);
        myollNeighbors = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return myollNeighbors.get(position);
    }

    @Override
    public int getCount() {
        return myollNeighbors.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
        {
            title = "oll neighbors";
        }
        else if (position == 1)
        {
            title = "favorit neighbors";
        }
        return title;
    }
}
