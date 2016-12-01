package com.example.gruti.frogger21;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.gruti.frogger21.Objects.Car;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class GameView extends View implements Runnable {

    Bitmap[] bmp;



    int lx = 11;
    int ly = 10;
    boolean run=false;


    int width;
    int height;
    int previous=0;
    int next;
    private int heroX = 6;
    private int heroY = 4;
    private int posHero =104;
    private int posCarLeft;
    private int posCarRight;
    private List<Car> cars;



    private int level[] = {

            1,9,1,9,9,1,1,1,9,1,
            3,3,3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,3,3,
            3,3,3,3,3,3,3,3,3,3,
            0,0,0,0,0,0,0,0,0,0,
            6,6,6,6,6,6,6,6,6,6,
            6,6,6,6,6,6,6,6,6,6,
            0,0,0,0,0,0,0,0,0,0,
            6,6,6,6,6,6,6,6,6,6,
            6,6,6,6,6,6,6,6,6,6,
            0,0,0,0,0,0,0,0,0,0
    };




    public GameView(Context context) {
        super(context);
        init(context);

    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);

        cars=new ArrayList<Car>();

    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);



    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    void init(Context context) {
        bmp = new Bitmap[10];




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
    }


    public void startGame()
    {
      Thread t = new Thread(this);
        run=true;
      t.start();



        level[posHero]=4;

        Car a=new Car(94);
        cars.add(a);

    }

    public void up()
    {
        if(run==true) {
            previous = next;
            next = level[posHero - 10];
            posHero -= 10;
            level[posHero] = 4;
            level[posHero + 10] = previous;
        }
    }
    public void down() {
        if(run==true) {
            previous = next;
            next = level[posHero + 10];
            posHero += 10;
            level[posHero] = 4;
            level[posHero - 10] = previous;
        }
    }
    public void left() {
        if(run==true) {
            previous = next;
            next = level[posHero - 1];
            posHero -= 1;
            level[posHero] = 4;
            level[posHero + 1] = previous;
        }
    }
    public void right() {
        if(run==true) {
            previous = next;
            next = level[posHero + 1];
            posHero += 1;
            level[posHero] = 4;
            level[posHero - 1] = previous;
        }
    }

    @Override
    public void run() {
        while(run) {
            level[45]=8;
            Log.d("moje", "runin: ");
            postInvalidate ();
        }
    }

    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();

        // you may need the x/y location
        int x = (int)event.getX();
        int y = (int)event.getY();

        // put your code in here to handle the event
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                //Log.d("Moje2","action down x: "+x);
                //Log.d("Moje2","action down y: "+y);
                startGame();
                if(y<=100)
                {
                    up();
                    Log.d("Moje2", "up ");
                }
                else if(y>=600)
                {
                    down();
                    Log.d("Moje2", "down ");
                }
                else if(x<=200)
                {
                    left();
                    Log.d("Moje2", "left");
                }
                else if(x>=400)
                {
                    right();
                    Log.d("Moje2", "right");
                }
                break;

        }

        Rect selRect = new Rect();
        selRect.left = (heroX-2)*52;
        selRect.top = (heroY-2)*52;
        selRect.right = (heroX+2)*52;
        selRect.bottom = (heroY+2)*52;

        // invalidate();
        return true;

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w / ly;
        height = h / lx;
        super.onSizeChanged(w, h, oldw, oldh);
    }


    @Override
    protected void onDraw(Canvas canvas) {

        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[level[i*10 + j]], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
            }
            invalidate();
            run=true;
        }


        /*Paint tPaint = new Paint();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                // TODO
                // pozor na rozliseni telefonu, toto vykreslovani s nim nepocita
                canvas.drawBitmap(bmp[level[i*10+j]], j*52, i*52, tPaint);

            }*/

    }



}
