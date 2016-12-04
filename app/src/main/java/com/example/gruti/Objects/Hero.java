package com.example.gruti.Objects;

/**
 * Created by gruti on 03.12.2016.
 */

public class Hero{
    private int posX;
    private int posY;
    private int lifes;


    public Hero(int posX,int posY,int lifes)
    {
        this.posX=posX;
        this.posY=posY;
        this.lifes=lifes;
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }
    public int getLifes()
    {
        return lifes;
    }

    public void setLife(int lifes)
    {
        this.lifes=lifes;
    }

    public void setPosX(int posX)
    {
        this.posX=posX;

    }

    public void setPosY(int posY)
    {
        this.posY=posY;

    }


    public void turnRight() {
        posX+=102;
        System.out.println("aktualni x: "+posX);
    }

    public void turnLeft() {
        posX-=102;
        System.out.println("aktualni x: "+posX);
    }

    public void turnUp() {
        posY-=102;
        System.out.println("aktualni y: "+posY);
    }

    public void turnDown() {
        posY+=102;
        System.out.println("aktualni y: "+posY);
    }
}
