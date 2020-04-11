package com.example.brainny_thebraintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Constraints;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button go;
    ArrayList ans= new ArrayList<Integer>();
    int ansloc;
    TextView correct,score;
    TextView question,timer;
    EditText seconds;
    Button button0,button1,button2,button3,playagain;
    int correctscore;
    int number;
    CountDownTimer cd;

    ConstraintLayout game;

    public void again(View v){
        timer.clearComposingText();
        startagain();
        playagain.setVisibility(View.INVISIBLE);
    }

    public void startagain(){
        button0.setClickable(true);
        button1.setClickable(true);
        button2.setClickable(true);
        button3.setClickable(true);
        correctscore=0;
        number=0;
        timer=findViewById(R.id.timer);
        correct=findViewById(R.id.correct);
        timer.setText("");
        newQuestion();
        correct.setText("");
       cd=new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timer.setText(String.valueOf(l/1000)+"s");

            }

            @Override
            public void onFinish() {
                correct.setText("Done!");
                playagain.setVisibility(View.VISIBLE);
                button0.setClickable(false);
                button1.setClickable(false);
                button2.setClickable(false);
                button3.setClickable(false);
            }
        }.start();
    }

    public void choose(View view){
        correct=findViewById(R.id.correct);
        score=findViewById(R.id.score);
        if(Integer.toString(ansloc).equals(view.getTag().toString())){
            correct.setText("CORRECT :) ");
            correct.setVisibility(View.VISIBLE);
            correctscore++;
        }
        else{
            correct.setText("WRONG :( ");
            correct.setVisibility(View.VISIBLE);
        }
        newQuestion();
        number++;
        score.setText(Integer.toString(correctscore)+"/"+Integer.toString(number));
    }


    public void goclick(View v){
        game=findViewById(R.id.game);
        go=findViewById(R.id.go);
        go.setVisibility(View.INVISIBLE);
        game.setVisibility(View.VISIBLE);
        again(go);

    }
public void newQuestion(){
        Random rand=new Random();
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        ansloc=rand.nextInt(4);
        ans.clear();
        int n=rand.nextInt(3);
        switch(n) {
            case 0:
                question.setText(Integer.toString(a)+" + "+ Integer.toString(b));
                for (int i=0;i<4;i++){
                    if(i==ansloc){
                        ans.add(a+b);
                    }
                    else{
                        int wrongans=rand.nextInt(41);
                        while(wrongans==a+b){
                            wrongans=rand.nextInt(41);
                        }
                        ans.add(wrongans);
                    }
                }
                break;
            case 1:
                question.setText(Integer.toString(a)+" - "+ Integer.toString(b));
                for (int i=0;i<4;i++){
                    if(i==ansloc){
                        ans.add(a-b);
                    }
                    else{
                        int wrongans=rand.nextInt(21);
                        while(wrongans==a-b){
                            wrongans=rand.nextInt(21);
                        }
                        ans.add(wrongans);
                    }
                }
                break;

            case 2:
                question.setText(Integer.toString(a)+" x "+ Integer.toString(b));
                for (int i=0;i<4;i++){
                    if(i==ansloc){
                        ans.add(a*b);
                    }
                    else{
                        int wrongans=rand.nextInt(500);
                        while(wrongans==a*b){
                            wrongans=rand.nextInt(500);
                        }
                        ans.add(wrongans);
                    }
                }
                break;
        }
        button0.setText(Integer.toString((Integer) ans.get(0)));
        button1.setText(Integer.toString((Integer) ans.get(1)));
        button2.setText(Integer.toString((Integer) ans.get(2)));
        button3.setText(Integer.toString((Integer) ans.get(3)));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go=findViewById(R.id.go);
        question=findViewById(R.id.question);
       button0=findViewById(R.id.button0);
       button1=findViewById(R.id.button1);
       button2=findViewById(R.id.button2);
       button3=findViewById(R.id.button3);
       playagain=findViewById(R.id.playagain);
       timer=findViewById(R.id.timer);
       go.setVisibility(View.VISIBLE);
        newQuestion();
       game=findViewById(R.id.game);
       game.setVisibility(View.INVISIBLE);


    }
}
