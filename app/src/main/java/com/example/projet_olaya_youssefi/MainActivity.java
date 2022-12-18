package com.example.projet_olaya_youssefi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
//import android.widget.Toolbar;
import androidx.appcompat.widget.Toolbar;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //mettre le id de la question dans une liste
    List<Question> questionList;

    int quid =0;
    Question currentQuestion;

    /* pour la zone de la question*/
    TextView txtQuestion;

    /*pour la zone des choix*/
    RadioButton rda,rdb,rdc;

    /*c'est le bouton qui permet de permuter vers une autre question*/
    Button butNext;

    public static String result="highscore";
    static  int score = 0;

    /*pour le score à chaque modification effectuée*/
    TextView scoreno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // on a utilisé findViewById pour rechercher la vue enfant ayant l’ID qu'on a fourni dans l’argument.
        scoreno=(TextView)findViewById(R.id.score);


        //get all question from db
        DbHelper dbHelper = new DbHelper(this);
        questionList = dbHelper.getAllQuestions();

        /*la Méthode shuffle(list): est utilisé ici pour mélanger au hasard les éléments
         de la liste spécifiés en utilisant la méthode Random par défaut.*/
        Collections.shuffle(questionList);
        currentQuestion = questionList.get(quid);


        // on a utilisé findViewById pour rechercher la vue enfant ayant l’ID qu'on a fourni dans l’argument.
        txtQuestion = (TextView)findViewById(R.id.question);
        rda = (RadioButton)findViewById(R.id.radio0);
        rdb = (RadioButton)findViewById(R.id.radio1);
        rdc = (RadioButton)findViewById(R.id.radio2);
        butNext = (Button)findViewById(R.id.button1);
        setQuestionView();
    }
    private void setQuestionView(){
        txtQuestion.setText(currentQuestion.getQuestion());
        rda.setText(currentQuestion.getOptA());
        rdb.setText(currentQuestion.getOptB());
        rdc.setText(currentQuestion.getOptC());
        quid++;
    }


    public void btClick(View view){
        RadioGroup grp = (RadioGroup)findViewById(R.id.radioGroup1);
        RadioButton answer = (RadioButton)findViewById(grp.getCheckedRadioButtonId());


        /*Si la réponse défini en base de donnée de la question choisi au hasard est la meme choisie par l'utilisateur
        le score va augmenter */
        if(currentQuestion.getAnswer().equals(answer.getText())){
            score++;
            Log.d("Score", "Your score: "+score);
            scoreno.setText(" "+score);
        }

        /*si le numéro de la question est inférieure à 5 alors on va passer à une autre question en incrémentant
        la valeur du quid*/
        if(quid<5){
            currentQuestion = questionList.get(quid);
            setQuestionView();
        }


        /*sinon la page résultat va s'afficher en appuyant sur "next"*/
        else{
            final SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
            final SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(result,score);
            editor.commit();
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            startActivity(intent);
            finish();
        }


    }

/*le clique sur le bouton "highscore" permet d'afficher le résultat du meilleur score effectué par
les utilisateurs*/
    public void highscore(View v){
        Intent intent = new Intent(MainActivity.this, ResultActivity.class);
        startActivity(intent);
        finish();
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

 */
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

}