package com.example.gruti.com.example.gruti.logic;


import android.content.Context;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.gruti.Objects.Car;
import com.example.gruti.Objects.Hero;
import com.example.gruti.view.GameView;

import java.util.Iterator;
import java.util.List;

public class GameLogic {


    Event event;
    //pozice žaby
    private int posX=102*3;
    private int posY=102*9;
    public Hero hero= new Hero(posX,posY,3);

    //list aut
    public List<Car> cars;
    private int leftCarPosX=102*6;
    private int lefCarPosY=102*8;

    public GameLogic(Event event)
    {
        this.event=event;
    }


    public boolean update(int gameTime) {

        hero.getPosX();
        hero.getPosY();
        spawmCar(gameTime);
        carLogic();
        if(checkWater() && checkBounds()&& checkHit())
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
            event.onDeathListener("Spadl jsi z platformy");
        }
        return ok;
    }


    public boolean checkWater()
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
            event.onDeathListener("Neumíš plavat");
        }
        else
            ok=true;

        return ok;
    }
    public boolean checkHit()
    {
        boolean ok=true;


        for(Iterator<Car> carsIterator = cars.iterator(); carsIterator.hasNext();) {
            Car car = (Car) carsIterator.next();

            if ((hero.getPosX() >= car.getPosX() && hero.getPosX() <= car.getPosX() + 50) && hero.getPosY() == car.getPosY()) {
                ok = false;
                event.onDeathListener("Srazilo tě auto");
            }
        }
        return ok;
    }


    public void spawmCar(int time)
    {
        if(time%10==0)//vykreslovani aut
        {
            int randCar=1 + (int)(Math.random()*5);
            switch(randCar)
            {
                case 1:
                    cars.add(new Car(leftCarPosX,lefCarPosY));
                    break;

            }
        }
    }

    public void carLogic()
    {
        for(Iterator<Car> carsIterator = cars.iterator(); carsIterator.hasNext();)
        {
            Car car = (Car) carsIterator.next();
            if(car.getPosX()<-50)
            {
                carsIterator.remove();
            }
            car.moveCarLeft();
        }
    }



}
