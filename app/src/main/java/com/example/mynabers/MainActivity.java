package com.example.mynabers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.SearchView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager myViewPager;
    private Button summary;
    private Button delete;
    private SearchView mSearchView;

    private ollNeighbors fals = ollNeighbors.newInstansOllNeighbors(false);
    private ollNeighbors tru = ollNeighbors.newInstansOllNeighbors(true);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSummaryDelete();
        createViewPage();
    }

    private void buttonSummaryDelete() {
        summary = findViewById(R.id.AM_Buton_Summary);
        summary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, Summary.class);
                startActivity(myIntent);
            }
        });
        delete = findViewById(R.id.AM_Buton_Delete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDB.getIns(MainActivity.this).daoNaber().deleteAll();
//                fals.setData();
//                tru.setData();
                createViewPage();
            }
        });

        mSearchView = findViewById(R.id.AM_SearchView);
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                tru.getAdapter().getFilter().filter(newText);
                fals.getAdapter().getFilter().filter(newText);
                return true;
            }
        });

    }

    private void createViewPage() {
        myViewPager = findViewById(R.id.AM_vP);
        myViewPager.setAdapter(new VP_adapter(getSupportFragmentManager(), makeFragments()));
        TabLayout tabLayout = findViewById(R.id.am_tab_layout);
        tabLayout.setupWithViewPager(myViewPager);
    }

    private ArrayList<ollNeighbors> makeFragments() {
        ArrayList<ollNeighbors> arrayFragment = new ArrayList<ollNeighbors>();
        arrayFragment.add(fals);
        arrayFragment.add(tru);
        return arrayFragment;
    }
}
