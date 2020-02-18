package com.example.mynabers;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    private ArrayList<neighbor> myNeighbors;
    private TextView favorites;
    private TextView nameRating;
    private TextView rating;
    private TextView volt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        initAre();
        myNeighbors = (ArrayList<neighbor>) AppDB.getIns(this).daoNaber().getAll();

        calculation();
    }

    private void initAre() {
        favorites = findViewById(R.id.SAMERY_Favorites);
        nameRating = findViewById(R.id.SAMERY_Rating_name);
        rating = findViewById(R.id.SAMERY_Rating);
        volt = findViewById(R.id.SAMERY_vote);
    }

    private void calculation() {
        if (myNeighbors.size() == 0) {
            return;
        } else {
            String sFavorites = "";
            String sRatingName = "";
            String sRating = "";
            String sVolt = myNeighbors.get(0).getFirstName() + " " + myNeighbors.get(0).getLastName();
            for (int i = 0; i < myNeighbors.size(); i++) {
                if (myNeighbors.get(i).isFaivorit()) {
                    sFavorites += myNeighbors.get(i).getFirstName() + " " + myNeighbors.get(i).getLastName() + "\n";
                }
                if (myNeighbors.get(i).getRating() > 0) {
                    sRatingName += myNeighbors.get(i).getLastName() + "\n";
                    sRating += myNeighbors.get(i).getRating() + "\n";
                }
            }
            if (sRating.equals("")){
                return;
            }
            favorites.setText(sFavorites);
            nameRating.setText(sRatingName);
            rating.setText(sRating);
            volt.setText(sVolt);

        }

    }
}

