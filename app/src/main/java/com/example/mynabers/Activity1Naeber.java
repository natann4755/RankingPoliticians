package com.example.mynabers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.example.mynabers.ollNeighbors.MY_NEIGHBORS;
import static com.example.mynabers.ollNeighbors.keySharedPreferences;

public class Activity1Naeber extends AppCompatActivity implements View.OnClickListener {

    private ImageView myImageView;
    private ImageView addRating;
    private ImageView lasRating;
    private TextView firstName;
    private TextView lasttName;
    private TextView age;
    private TextView rating;
    private Button addToFaivorit;
    private Button removeFaivorit;
    private neighbor myNeighbor1;
    ArrayList<neighbor> myNeighbors = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity1_naeber);
        getArreyDB();

        Intent intent = getIntent();
        myNeighbor1 = intent.getParcelableExtra("naiber");
        intVeiws();

    }

    private void getArreyDB() {

        my

        settings = getApplicationContext().getSharedPreferences(keySharedPreferences, 0);
        String fromSharedPreferences = settings.getString(MY_NEIGHBORS, null);
        myNeighbors = gson.fromJson(fromSharedPreferences,new TypeToken<List<neighbor>>(){}.getType());

    }

    private void intVeiws() {
        myImageView = findViewById(R.id.activity2_imeg_v);
        firstName = findViewById(R.id.activity2_Text_FN);
        lasttName = findViewById(R.id.activity2_Text_LasN);
        age = findViewById(R.id.activity2_Text_age);
        addToFaivorit = findViewById(R.id.activity2_button_add);
        removeFaivorit = findViewById(R.id.activity2_button_remov);
        rating = findViewById(R.id.activity2_text_Rating);
        addRating = findViewById(R.id.activity2_add_Rating);
        lasRating = findViewById(R.id.activity2_lass_Rating);

        String url = myNeighbor1.getUrl();
        Picasso.get().load(url).into(myImageView);
        firstName.setText(myNeighbor1.getFirstName());
        lasttName.setText(myNeighbor1.getLastName());
        age.setText(String.valueOf(myNeighbor1.getAge()));
        rating.setText(String.valueOf(myNeighbor1.getRating()));


        addToFaivorit.setOnClickListener(this);
        removeFaivorit.setOnClickListener(this);
        addRating.setOnClickListener(this);
        lasRating.setOnClickListener(this);

        if (myNeighbor1.isFaivorit()){
            addToFaivorit.setVisibility(View.GONE);
            removeFaivorit.setVisibility(View.VISIBLE);
        }else {
            removeFaivorit.setVisibility(View.GONE);
            addToFaivorit.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onClick(View v) {

        int posishnMyNaiber = 0;
        for (int i = 0; i <myNeighbors.size() ; i++) {
            if(myNeighbors.get(i).getLastName().equals(myNeighbor1.getLastName())){
                posishnMyNaiber=i;
            }
        }
        if (v.getId()==R.id.activity2_button_add){
            myNeighbors.get(posishnMyNaiber).setFaivorit(true);


        }
        if (v.getId()==R.id.activity2_button_remov){
            myNeighbors.get(posishnMyNaiber).setFaivorit(false);
        }
        if (v.getId()==R.id.activity2_add_Rating) {
            int Rating = myNeighbors.get(posishnMyNaiber).getRating();
            myNeighbors.get(posishnMyNaiber).setRating(++Rating);
        }
        if (v.getId()==R.id.activity2_lass_Rating) {
            int Rating = myNeighbors.get(posishnMyNaiber).getRating();
            myNeighbors.get(posishnMyNaiber).setRating(--Rating);
        }
        myNeighbor1 = myNeighbors.get(posishnMyNaiber);
        intVeiws();

//        SharedPreferences.Editor edt = settings.edit();
//        String json = gson.toJson(myNeighbors);
//        edt.putString(MY_NEIGHBORS, json);
//        edt.apply();
    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor edt = settings.edit();
        String json = gson.toJson(myNeighbors);
        edt.putString(MY_NEIGHBORS, json);
        edt.apply();
    }

}
