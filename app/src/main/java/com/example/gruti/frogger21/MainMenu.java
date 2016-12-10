package com.example.gruti.frogger21;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {

    ScoreFragment sFR;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //exit apky
        Button bExit = (Button) findViewById(R.id.bExit);
        bExit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        //exit apky
        Button bF = (Button) findViewById(R.id.fragmentBtn);
        bF.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                FragmentManager fM=getFragmentManager();
                FragmentTransaction fT=fM.beginTransaction();
                sFR=new ScoreFragment();
                fT.add(R.id.activity_main_menu,sFR);
                fT.commit();
            }
        });

    }

    //Vytvoření nové hry
    public void ClickNewGame(View view) {
        Intent LunchNewGame=new Intent(this,com.example.gruti.frogger21.LevelPick.class);
        startActivity(LunchNewGame);
    }

    //zobrazeni skore
    public void ClickScore(View view) {
        Intent ShowScore=new Intent(this,Score.class);
        startActivity(ShowScore);

    }









}
