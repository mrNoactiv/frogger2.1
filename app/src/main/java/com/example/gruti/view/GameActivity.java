package com.example.gruti.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.gruti.com.example.gruti.logic.GameLogic;
import com.example.gruti.frogger21.R;
import com.example.gruti.frogger21.ScoreFragment;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;
    FrameLayout game;
    RelativeLayout gameWidgets;
    boolean gameEnded=false;


    viewEvents viewEvents=new viewEvents() {
        @Override
        public void onZeroLifes(final int score) {

            gameEnded=true;

            gameWidgets.setId(R.id.fragment);

            FragmentManager fM=getFragmentManager();
            FragmentTransaction fT=fM.beginTransaction();

            final ScoreFragment sFR;
            sFR=new ScoreFragment();

            fT.add(gameWidgets.getId(),sFR);
            fT.commit();

            sFR.setScore(score);


            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    sFR.setTextViewText("Score: "+score );

                }
            });
        }
    };






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int level=getIntent().getIntExtra("level",1);
        gameView = new GameView(this, metrics,level,viewEvents);
        game = new FrameLayout(this);


        //LinearLayout gameWidgets = new LinearLayout (this);
        gameWidgets=new RelativeLayout(this);
        gameWidgets.addView(gameView);//widgety linearni lay


       // gameWidgets.addView(b);//widgety linearni lay

        //game.addView(gameWidgets);//game frame layout
        //game.addView(gameView);//game frame layout

        //setContentView(gameView);//game frame layout
        setContentView(gameWidgets);
        gameView.start();
    }

    @Override
    protected void onPause() {
        gameView.stop();
        super.onPause();
    }
}
