package com.example.gruti.view;


import android.content.Context;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.gruti.com.example.gruti.logic.GameLogic;

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback, View.OnTouchListener
{
    private final SurfaceHolder holder;
    private boolean isRunning;
    private final Thread drawingThread;
    private GameLogic logic;
    private GameRenderer renderer;
    private boolean canDraw;

    public GameView(Context context, DisplayMetrics metrics) {
        super(context);
        drawingThread = new Thread(this);
        logic = new GameLogic();
        renderer = new GameRenderer(metrics.widthPixels, metrics.heightPixels, logic);
        holder = getHolder();
        setOnTouchListener(this);

    }


    public void start(){
        isRunning = true;
        drawingThread.start();
    }


    @Override
    public void run() {
        while(this.isRunning){
            if(holder.getSurface().isValid()) {
                logic.update();//p5epo4tz
                Canvas canvas = holder.lockCanvas();
                renderer.draw(canvas);
                holder.unlockCanvasAndPost(canvas);
            }
        }
    }


    public void stop(){
        isRunning = false;
        try {
            drawingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            //handle it
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        canDraw = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        stop();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        logic.up();
        return false;
    }
}
