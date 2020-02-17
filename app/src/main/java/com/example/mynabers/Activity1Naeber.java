package com.example.mynabers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;



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

        myNeighbors = (ArrayList<neighbor>) AppDB.getIns(this).daoNaber().getAll();

        Intent intent = getIntent();
        myNeighbor1 = intent.getParcelableExtra("naiber");
        intVeiws();

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
            if (Rating >0) {
                myNeighbors.get(posishnMyNaiber).setRating(--Rating);
            }else {
                Toast.makeText(this, "The rating is 0 - the minimum value ",
                        Toast.LENGTH_LONG).show();
                return;
            }
        }
        myNeighbor1 = myNeighbors.get(posishnMyNaiber);
        intVeiws();

    }


    @Override
    protected void onPause() {
        super.onPause();
        AppDB.getIns(this).daoNaber().deleteAll();
        AppDB.getIns(this).daoNaber().insertAll(myNeighbors);
    }

}
