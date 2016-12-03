package com.example.gruti.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.gruti.com.example.gruti.logic.GameLogic;
import com.example.gruti.frogger21.R;

import java.util.HashMap;
import java.util.Map;


public class GameRenderer {

    private GameLogic gameLogic;
    private Point dimensions;
    private Paint backgroundPaint;
    Bitmap[] mapa;
    Map<Integer, Bitmap> bitmapBank = new HashMap<>();//dis



    public GameRenderer(int width, int height, GameLogic gameLogic,Bitmap[] map){
        this.gameLogic = gameLogic;
        dimensions = new Point(width, height);
        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        mapa=map;
        //backgroundPaint.setColor(Color.MAGENTA);

       // bitmapBank.put(R.drawable.frog,map.);//dis
    }


    public void draw(Canvas canvas){
        backgroundPaint.setColor(Color.BLUE);
        canvas.drawRect(0,0, dimensions.x, dimensions.y, backgroundPaint);
       /* if(gameLogic.isUp){
            backgroundPaint.setColor(Color.RED);
            canvas.drawRect(0,0, dimensions.x / 2, dimensions.y / 2, backgroundPaint);
        }*/
        if(gameLogic.isUp) {
            canvas.drawBitmap(mapa[1],dimensions.x, dimensions.y, backgroundPaint);
        }
        }

    }
