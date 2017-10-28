package com.melih.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
        int num1, num2,answer,answer1,answer2,answer3, answerLocation=0, correctAnswer =0, counter=0;

        TextView counterText, answersText, questionText, resultText;

        Button button1,button2,button3,button4, startButton;

    public int createWrongAnswers(){
        Random rnd = new Random();

        int add = rnd.nextInt(10-0+1)-10 ;

        while(add==0){
            add = rnd.nextInt(10-0+1)-10 ;
        }

       return answer+add;


     }


    public void createQuestion(){

        Random rnd = new Random();
        num1= rnd.nextInt(30 - 1 + 1) + 1;

        num2= rnd.nextInt(30 - 1 + 1) + 1;

        questionText.setText(num1 + " + " + num2);


        answer = num1+num2;


         answer1=createWrongAnswers();
         answer2=createWrongAnswers();
         answer3=createWrongAnswers();

        if(answer1==answer2){
            answer1=createWrongAnswers();
        }
        if(answer1==answer3){
            answer1=createWrongAnswers();
        }
        if(answer2==answer3){
            answer3=createWrongAnswers();
        }

        ArrayList<Integer> answers = new ArrayList<Integer>();
        answers.add(answer);
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        Collections.shuffle(answers);



        for(int i=0; i<answers.size(); i++){
            if(answer==answers.get(i))
                answerLocation=i+1;

        }


        button1.setText(Integer.toString(answers.get(0)));
        button2.setText(Integer.toString(answers.get(1)));
        button3.setText(Integer.toString(answers.get(2)));
        button4.setText(Integer.toString(answers.get(3)));

        Log.i("Answer Location: " ,  Integer.toString(answerLocation));

        Log.i("Answers:" , Integer.toString(correctAnswer));

        Log.i("Questions:" , Integer.toString(counter));


        answersText.setText(""+correctAnswer+"/"+counter);

    }



    public void startButton(View view){

         counterText = (TextView) findViewById(R.id.counter);
         answersText = (TextView) findViewById(R.id.answers);
         questionText = (TextView) findViewById(R.id.question);

        resultText = (TextView) findViewById(R.id.Result);

        startButton = (Button) findViewById(R.id.startButton);

         button1 = (Button) findViewById(R.id.button1);
         button2 = (Button) findViewById(R.id.button2);
         button3 = (Button) findViewById(R.id.button3);
         button4 = (Button) findViewById(R.id.button4);

        startButton.setVisibility(View.INVISIBLE);

        resultText.setVisibility(View.INVISIBLE);

        counterText.setVisibility(View.VISIBLE);
        answersText.setVisibility(View.VISIBLE);
        questionText.setVisibility(View.VISIBLE);

        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);


        correctAnswer =0;
        counter=0;

        startCounter();
        createQuestion();

    }

    public void startCounter(){

        int second = 30;

       CountDownTimer countDownTimer = new CountDownTimer((second*1000)+100,1000) {
           @Override
           public void onTick(long millisUntilFinished) {

               counterText.setText(" " + ((int)millisUntilFinished/1000) + "s");


           }

           @Override
           public void onFinish() {

               counterText.setText("0s");
               resultText.setVisibility(View.VISIBLE);
               resultText.setText(" Right Answers "+ correctAnswer+" Questions "+counter);

               button1.setVisibility(View.INVISIBLE);
               button2.setVisibility(View.INVISIBLE);
               button3.setVisibility(View.INVISIBLE);
               button4.setVisibility(View.INVISIBLE);
               answersText.setVisibility(View.INVISIBLE);
               counterText.setVisibility(View.INVISIBLE);
               questionText.setVisibility(View.INVISIBLE);

               startButton.setText("Play Again");
               startButton.setVisibility(View.VISIBLE);

           }
       }.start();
    }

    public void checkAnswer(View view){

        counter++;

        String idStr =(String) view.getTag();

        int id = Integer.parseInt(idStr);

        Log.i("ID: ", " " +id);
        if(id==answerLocation){
            correctAnswer++;
        }

        createQuestion();




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
