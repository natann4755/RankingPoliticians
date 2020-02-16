package com.example.mynabers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager myViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createViewPage();
    }

    private void createViewPage() {
        myViewPager = findViewById(R.id.AM_vP);
        myViewPager.setAdapter(new VP_adapter(getSupportFragmentManager(), makeFragments()));
        TabLayout tabLayout = findViewById(R.id.am_tab_layout);
        tabLayout.setupWithViewPager(myViewPager);
    }

    private ArrayList<ollNeighbors> makeFragments() {
        ArrayList<ollNeighbors> arrayFragment = new ArrayList<ollNeighbors>();
        arrayFragment.add(ollNeighbors.newInstansOllNeighbors(false));
        arrayFragment.add(ollNeighbors.newInstansOllNeighbors(true));
        return arrayFragment;
    }
}
