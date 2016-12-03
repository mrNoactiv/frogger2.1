package com.example.gruti.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import com.example.gruti.com.example.gruti.logic.GameLogic;
import com.example.gruti.frogger21.R;

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback, View.OnTouchListener
{
    private final SurfaceHolder holder;
    private boolean isRunning;
    private final Thread drawingThread;
    private GameLogic logic;
    private GameRenderer renderer;
    private boolean canDraw;
    Bitmap[] bmp;



    public GameView(Context context, DisplayMetrics metrics) {
        super(context);
        init(context);
        drawingThread = new Thread(this);
        logic = new GameLogic();
        renderer = new GameRenderer(metrics.widthPixels, metrics.heightPixels, logic,bmp);
        holder = getHolder();
        setOnTouchListener(this);

    }

    void init(Context context) {
        bmp = new Bitmap[11];
        bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.green);
        bmp[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fire);
        bmp[2] = BitmapFactory.decodeResource(getResources(), R.drawable.wood);
        bmp[3] = BitmapFactory.decodeResource(getResources(), R.drawable.water);
        bmp[4] = BitmapFactory.decodeResource(getResources(), R.drawable.frog);
        bmp[5] = BitmapFactory.decodeResource(getResources(), R.drawable.fly);
        bmp[6] = BitmapFactory.decodeResource(getResources(), R.drawable.grey);
        bmp[7] = BitmapFactory.decodeResource(getResources(), R.drawable.car_left);
        bmp[8] = BitmapFactory.decodeResource(getResources(), R.drawable.car_right);
        bmp[9] = BitmapFactory.decodeResource(getResources(), R.drawable.stone);
        bmp[10] = BitmapFactory.decodeResource(getResources(), R.drawable.etin);

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
