package com.example.projet_olaya_youssefi;

import static com.example.projet_olaya_youssefi.MainActivity.result;
import static com.example.projet_olaya_youssefi.MainActivity.score;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // on a utilisé findViewById pour rechercher la vue enfant ayant l’ID qu'on a fourni dans l’argument.
        TextView scoreTxtView = (TextView) findViewById(R.id.score);
        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar1);
        ImageView img = (ImageView)findViewById(R.id.img1);

/*on a utilisé ici SharedPreferences car elle est  par nature persistant (permanent), c’est-à-dire qu’il stocke
les données de cette application afin que les données restent dans la mémoire même
lorsque vous quittez l’application et ouvrez à nouveau l’application*/
        SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
        int score1 = sharedPreferences.getInt(result, score);


        /*récupérer les résultats sous forme d'étoile et de nombre*/
        ratingBar.setRating(score1);
        scoreTxtView.setText(String.valueOf(score1));

        /*il faut que les Emojis s'affiche  de  sort que chaqun exprime un score différent à l'autre
    (il y a 6 Emojis)*/
        if(score1 == 0){
            img.setImageResource(R.drawable.score_0);
        }else if(score1 == 1){
            img.setImageResource(R.drawable.score_1);
        }else if(score1 == 2){
            img.setImageResource(R.drawable.score_2);
        }else if(score1 == 3){
            img.setImageResource(R.drawable.score_3);
        }else if(score1 == 4){
            img.setImageResource(R.drawable.score_4);
        }else if(score1 == 5){
            img.setImageResource(R.drawable.score_5);
        }
    }
}