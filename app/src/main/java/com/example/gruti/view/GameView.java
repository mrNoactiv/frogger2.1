package com.example.gruti.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.gruti.Objects.Board;
import com.example.gruti.Objects.Car;
import com.example.gruti.com.example.gruti.logic.Event;
import com.example.gruti.com.example.gruti.logic.GameLogic;
import com.example.gruti.frogger21.R;

import java.util.ArrayList;

public class GameView extends SurfaceView implements Runnable, SurfaceHolder.Callback
{
    private final SurfaceHolder holder;
    private boolean isRunning;
    private final Thread drawingThread;
    private GameLogic logic;
    private GameRenderer renderer;
    private boolean canDraw;
    private Bitmap[] bmp;
    private int gameTime;



    Event e=new Event() {
        @Override
        public void onDeathListener(final String message) {
            post(new Runnable() {
                @Override
                public void run() {
                    Context context = getContext();
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context,message , duration);
                    toast.show();

                }
            });
        }

        @Override
        public void onSoundTrigger(int a ) {
            MediaPlayer mp=MediaPlayer.create(getContext(),a);
            mp.start();
            mp.
        }
    };





    public GameView(Context context, DisplayMetrics metrics) {
        super(context);
        init(context);
        drawingThread = new Thread(this);
        logic = new GameLogic(e);
        logic.cars=new ArrayList<Car>();
        logic.leftBoards=new ArrayList<Board>();
        logic.rightBoards=new ArrayList<Board>();
        renderer = new GameRenderer(metrics.widthPixels, metrics.heightPixels, logic,bmp);
        holder = getHolder();


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

    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();
        // you may need the x/y location
        int x = (int)event.getX();
        int y = (int)event.getY();

        // put your code in here to handle the event
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:

                if(y<=408)
                {
                    logic.hero.turnUp();
                }
                else if(y>=715)
                {
                    logic.hero.turnDown();
                }
                else if(x<=357)
                {
                    logic.hero.turnLeft();
                }
                else if(x>=358)
                {
                    logic.hero.turnRight();
                }
                break;
        }
        return true;
    }



    public void start(){
        isRunning = true;
        drawingThread.start();
    }


    @Override
    public void run() {

        while(this.isRunning){
            gameTime++;
            if(holder.getSurface().isValid()) {
                if (logic.update(gameTime)!=true)//přepočet)
                {

                    Restart();
                }
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
    public void Restart()
    {
        isRunning=false;
        int TEMP=logic.hero.getLifes();
        logic.hero.setLife(TEMP-1);
        logic.hero.setPosX(102*3);
        logic.hero.setPosY(102*9);
        isRunning=true;

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


}
