package com.example.gruti.com.example.gruti.logic;


import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.gruti.Objects.Car;
import com.example.gruti.Objects.Hero;
import com.example.gruti.view.GameView;

import java.util.List;

public class GameLogic {


    Event event;
    //pozice žaby
    private int posX=102*3;
    private int posY=102*9;
    public Hero hero= new Hero(posX,posY,3);

    public List<Car> carss;

    public GameLogic(Event event)
    {
        this.event=event;
    }


    boolean left;
    public boolean isUp = false;



    public boolean update() {

        hero.getPosX();
        hero.getPosY();
        if(checkWater() && checkBounds())
        {
            return true;
        }
            else
        {

            return false;
        }
    }


    public boolean checkBounds()
    {
        boolean ok=true;

        if(hero.getPosX()>612 || hero.getPosX()<0)
        {
            ok=false;
        }
        return ok;
    }

    ////voda nahoře
    //canvas.drawBitmap(mapa[3],null,new Rect(0,size,size*7,size*3),backgroundPaint);
    public boolean checkWater()//nefunguje
    {
        boolean ok=true;
        if(hero.getPosY()<=510 && hero.getPosY()>408)
        {
           /* if(onLeftBoard() || onRightBoard())
            {
                ok=true;
            }
            else*/
                ok=false;
        }
        else
            ok=true;

        return ok;

    }

}
