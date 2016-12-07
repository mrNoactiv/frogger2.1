package com.example.gruti.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.gruti.com.example.gruti.logic.GameLogic;

public class GameActivity extends AppCompatActivity {

    private GameView gameView;

    //private TextView score;
    ///private Button b;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int level=getIntent().getIntExtra("level",1);
        gameView = new GameView(this, metrics,level);
        //FrameLayout game = new FrameLayout(this);
        //LinearLayout gameWidgets = new LinearLayout (this);

       // score=new TextView(this);
        //b=new Button(this);
        //b.setText("k");
        //score.setText("Score:"+gameView.getLogic().getScore());
       // score.size

        //gameWidgets.addView(score);//widgety linearni lay
       // gameWidgets.addView(b);//widgety linearni lay

        //game.addView(gameWidgets);//game frame layout
        //game.addView(gameView);//game frame layout

        setContentView(gameView);//game frame layout
        gameView.start();
    }

    @Override
    protected void onPause() {
        gameView.stop();
        super.onPause();
    }
}
