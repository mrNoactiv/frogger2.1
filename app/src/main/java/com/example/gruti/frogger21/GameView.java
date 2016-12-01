package com.example.gruti.frogger21;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class GameView extends View implements Runnable {

    Bitmap[] bmp;

    int lx = 11;
    int ly = 10;
    boolean run=false;


    int width;
    int height;

    private int heroX = 6;
    private int heroY = 4;
    private int pos =106;

    int previous=0;
    int next;
    boolean start=true;


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
    Thread t=new Thread(this);



    public GameView(Context context) {
        super(context);
        init(context);
        t.start();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        t.start();

    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        t.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public GameView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    void init(Context context) {
        bmp = new Bitmap[10];

        bmp[0] = BitmapFactory.decodeResource(getResources(), R.drawable.trava);
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




    public void up()
    {
        if (level[pos -10] == 1 ) {
            Log.d("Moje2", "Block nahore ");
        }
        else if(level[pos -10] == 2)
        {

        }
        else {
            if (start) {
                previous = 0;
                start = false;
                next = level[pos - 10];
            } else {
                previous = next;
                next = level[pos - 10];
            }

            pos -= 10;
            level[pos] = 4;
            level[pos + 10] = previous;
        }
    }
    public void down() {
        if (level[pos + 10] == 1)  {
            Log.d("Moje2", "Block dole ");
        }
        else if(level[pos + 10] == 2)
        {

        }
        else {
            if (start) {
                previous = 0;
                start = false;
                next = level[pos + 10];
            } else {
                previous = next;
                next = level[pos + 10];
            }
            pos += 10;
            level[pos] = 4;
            level[pos - 10] = previous;
        }
    }
    public void left() {
        if (level[pos -1] == 1 ) {
            Log.d("Moje2", "Block na vlevo ");
        }
        else if( level[pos -1] == 2)
        {

        }
        else {
            if (start) {
                previous = 0;
                start = false;
                next = level[pos - 1];
            } else {
                previous = next;
                next = level[pos - 1];
            }
            pos -= 1;
            level[pos] = 4;
            level[pos + 1] = previous;
        }
    }
    public void right() {
        if (level[pos + 1] == 1 ) {
            Log.d("Moje2", "Block na pravo ");
        }
        else if(level[pos + 1] == 2)
        {

        }
        else {
            if (start) {
                previous = 0;
                start = false;
                next = level[pos + 1];
            } else {
                previous = next;
                next = level[pos + 1];
            }
            pos += 1;
            level[pos] = 4;
            level[pos - 1] = previous;
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
        run=true;
        for (int i = 0; i < lx; i++) {
            for (int j = 0; j < ly; j++) {
                canvas.drawBitmap(bmp[level[i*10 + j]], null,
                        new Rect(j*width, i*height,(j+1)*width, (i+1)*height), null);
            }
            invalidate();
        }


        /*Paint tPaint = new Paint();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {
                // TODO
                // pozor na rozliseni telefonu, toto vykreslovani s nim nepocita
                canvas.drawBitmap(bmp[level[i*10+j]], j*52, i*52, tPaint);

            }*/

    }


    @Override
    public void run() {
        while(run) {

            Log.d("moje", "runin: ");
        }
    }
}
