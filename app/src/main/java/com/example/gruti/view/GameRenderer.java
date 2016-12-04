package com.example.gruti.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.gruti.com.example.gruti.logic.GameLogic;
import com.example.gruti.frogger21.R;

import java.util.HashMap;
import java.util.Map;


public class GameRenderer {

    private GameLogic gameLogic;
    private Point dimensions;
    private Paint backgroundPaint;
    int size=102;
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

        //vykreslení lvlu

        //první lajna
        canvas.drawBitmap(mapa[0],null,new Rect(0,0,size*7,size),backgroundPaint);
        canvas.drawBitmap(mapa[1],null,new Rect(0,0,size,size),backgroundPaint);
        canvas.drawBitmap(mapa[1],null,new Rect(size,0,size*2,size),backgroundPaint);
        canvas.drawBitmap(mapa[9],null,new Rect(size*2,0,size*3,size),backgroundPaint);
        canvas.drawBitmap(mapa[5],null,new Rect(size*2,0,size*3,size),backgroundPaint);//moucha
        canvas.drawBitmap(mapa[1],null,new Rect(size*3,0,size*4,size),backgroundPaint);
        canvas.drawBitmap(mapa[9],null,new Rect(size*4,0,size*5,size),backgroundPaint);
        canvas.drawBitmap(mapa[9],null,new Rect(size*5,0,size*6,size),backgroundPaint);
        canvas.drawBitmap(mapa[1],null,new Rect(size*6,0,size*7,size),backgroundPaint);

        //voda nahoře
        canvas.drawBitmap(mapa[3],null,new Rect(0,size,size*7,size*3),backgroundPaint);


        //kameny na vodě uprostřed
        canvas.drawBitmap(mapa[3],null,new Rect(0,size*3,size,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[3],null,new Rect(size,size*3,size*2,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[3],null,new Rect(size*2,size*3,size*3,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[3],null,new Rect(size*3,size*3,size*4,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[3],null,new Rect(size*4,size*3,size*5,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[3],null,new Rect(size*5,size*3,size*6,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[3],null,new Rect(size*6,size*3,size*7,size*4),backgroundPaint);


        canvas.drawBitmap(mapa[9],null,new Rect(0,size*3,size,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[9],null,new Rect(size,size*3,size*2,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[9],null,new Rect(size*2,size*3,size*3,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[9],null,new Rect(size*3,size*3,size*4,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[9],null,new Rect(size*4,size*3,size*5,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[9],null,new Rect(size*5,size*3,size*6,size*4),backgroundPaint);
        canvas.drawBitmap(mapa[9],null,new Rect(size*6,size*3,size*7,size*4),backgroundPaint);

        //voda dole
        canvas.drawBitmap(mapa[3],null,new Rect(0,size*4,size*7,size*6),backgroundPaint);
        //trava před vodou
        canvas.drawBitmap(mapa[0],null,new Rect(0,size*6,size*7,size*7),backgroundPaint);
        //cesta
        canvas.drawBitmap(mapa[6],null,new Rect(0,size*7,size*7,size*9),backgroundPaint);
        //start
        canvas.drawBitmap(mapa[0],null,new Rect(0,size*9,size*7,size*11),backgroundPaint);

        if(gameLogic.isUp) {
            //canvas.drawBitmap(mapa[1],0, 0, backgroundPaint);
            canvas.drawBitmap(mapa[4],null,new Rect(180,180,280,280),backgroundPaint);

        }
        }

    }
