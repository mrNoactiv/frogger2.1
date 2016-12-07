package com.example.gruti.view;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.gruti.Objects.Board;
import com.example.gruti.Objects.Car;
import com.example.gruti.com.example.gruti.logic.GameLogic;


public class GameRenderer {

    private GameLogic gameLogic;
    private Point dimensions;
    private Paint backgroundPaint;
    int size=102;
    Bitmap[] mapa;


    //laout




    public GameRenderer(int width, int height, GameLogic gameLogic,Bitmap[] map){
        this.gameLogic = gameLogic;
        dimensions = new Point(width, height);
        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        mapa=map;






       // bitmapBank.put(R.drawable.frog,map.);//dis
    }


    public void draw(Canvas canvas) {
        backgroundPaint.setColor(Color.BLUE);
        canvas.drawRect(0, 0, dimensions.x, dimensions.y, backgroundPaint);

        //vykreslení lvlu

        //první lajna
        canvas.drawBitmap(mapa[0], null, new Rect(0, 0, size * 7, size), backgroundPaint);
        canvas.drawBitmap(mapa[1], null, new Rect(0, 0, size, size), backgroundPaint);
        canvas.drawBitmap(mapa[1], null, new Rect(size, 0, size * 2, size), backgroundPaint);
        canvas.drawBitmap(mapa[9], null, new Rect(size * 2, 0, size * 3, size), backgroundPaint);
        canvas.drawBitmap(mapa[1], null, new Rect(size * 3, 0, size * 4, size), backgroundPaint);
        canvas.drawBitmap(mapa[9], null, new Rect(size * 4, 0, size * 5, size), backgroundPaint);
        canvas.drawBitmap(mapa[9], null, new Rect(size * 5, 0, size * 6, size), backgroundPaint);
        canvas.drawBitmap(mapa[1], null, new Rect(size * 6, 0, size * 7, size), backgroundPaint);

        //voda nahoře
        canvas.drawBitmap(mapa[3], null, new Rect(0, size, size * 7, size * 3), backgroundPaint);


        //kameny na vodě uprostřed
        canvas.drawBitmap(mapa[3], null, new Rect(0, size * 3, size, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[3], null, new Rect(size, size * 3, size * 2, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[3], null, new Rect(size * 2, size * 3, size * 3, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[3], null, new Rect(size * 3, size * 3, size * 4, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[3], null, new Rect(size * 4, size * 3, size * 5, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[3], null, new Rect(size * 5, size * 3, size * 6, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[3], null, new Rect(size * 6, size * 3, size * 7, size * 4), backgroundPaint);


        canvas.drawBitmap(mapa[9], null, new Rect(0, size * 3, size, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[9], null, new Rect(size, size * 3, size * 2, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[9], null, new Rect(size * 2, size * 3, size * 3, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[9], null, new Rect(size * 3, size * 3, size * 4, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[9], null, new Rect(size * 4, size * 3, size * 5, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[9], null, new Rect(size * 5, size * 3, size * 6, size * 4), backgroundPaint);
        canvas.drawBitmap(mapa[9], null, new Rect(size * 6, size * 3, size * 7, size * 4), backgroundPaint);

        //voda dole
        canvas.drawBitmap(mapa[3], null, new Rect(0, size * 4, size * 7, size * 6), backgroundPaint);
        //trava před vodou
        canvas.drawBitmap(mapa[0], null, new Rect(0, size * 6, size * 7, size * 7), backgroundPaint);
        //cesta
        canvas.drawBitmap(mapa[6], null, new Rect(0, size * 7, size * 7, size * 9), backgroundPaint);
        //start
        canvas.drawBitmap(mapa[0], null, new Rect(0, size * 9, size * 7, size * 11), backgroundPaint);



        //vykreslovaní levých aut

        for (Car car : gameLogic.leftCars) {
            canvas.drawBitmap(mapa[8], null, new Rect(car.getPosX(), car.getPosY(), car.getPosX() + size, car.getPosY() + size), backgroundPaint);
        }
        for (Car car : gameLogic.rightCars) {
            canvas.drawBitmap(mapa[7], null, new Rect(car.getPosX(), car.getPosY(), car.getPosX() + size, car.getPosY() + size), backgroundPaint);
        }


        //vykreslovani mouchy
        canvas.drawBitmap(mapa[5], null, new Rect(gameLogic.fly.getPosX(), gameLogic.fly.getPosY(), gameLogic.fly.getPosX() + size, gameLogic.fly.getPosY() + size), backgroundPaint);

        //vykreslovaní dřev
        for (Board b : gameLogic.leftBoards) {
            canvas.drawBitmap(mapa[2], null, new Rect(b.getPosX(), b.getPosY(), b.getPosX() + size*2, b.getPosY() + size), backgroundPaint);
        }

        for (Board b : gameLogic.rightBoards) {
            canvas.drawBitmap(mapa[2], null, new Rect(b.getPosX(), b.getPosY(), b.getPosX() + size*2, b.getPosY() + size), backgroundPaint);
        }


        //vykreslování žaby
        canvas.drawBitmap(mapa[4], null, new Rect(gameLogic.hero.getPosX(), gameLogic.hero.getPosY(), gameLogic.hero.getPosX() + size, gameLogic.hero.getPosY() + size), backgroundPaint);
        backgroundPaint.setTextSize(40);
        backgroundPaint.setColor(Color.RED);
        canvas.drawText("Score: "+gameLogic.getScore(),550,980,backgroundPaint);
        canvas.drawText("Lifes: "+gameLogic.getLifes(),550,1020,backgroundPaint);

    }
    }
