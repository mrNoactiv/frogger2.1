package com.example.gruti.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.example.gruti.com.example.gruti.logic.GameLogic;


public class GameRenderer {

    private GameLogic gameLogic;
    private Point dimensions;
    private Paint backgroundPaint;

    public GameRenderer(int width, int height, GameLogic gameLogic){
        this.gameLogic = gameLogic;
        dimensions = new Point(width, height);
        backgroundPaint = new Paint();
        backgroundPaint.setStyle(Paint.Style.FILL);
        backgroundPaint.setColor(Color.MAGENTA);
    }


    public void draw(Canvas canvas){
        backgroundPaint.setColor(Color.MAGENTA);
        canvas.drawRect(0,0, dimensions.x, dimensions.y, backgroundPaint);
        if(gameLogic.isUp){
            backgroundPaint.setColor(Color.BLUE);
            canvas.drawRect(0,0, dimensions.x / 2, dimensions.y / 2, backgroundPaint);
        }

    }
}
