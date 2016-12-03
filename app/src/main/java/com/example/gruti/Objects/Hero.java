package com.example.gruti.Objects;

/**
 * Created by gruti on 03.12.2016.
 */

public class Hero{




    private int posX;
    private int posY;


    public Hero(int posX,int posY)
    {
        this.posX=posX;
        this.posY=posY;
    }

    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }

    public void setPosX(int posX)
    {
        this.posX=posX;

    }

    public void setPosY(int posY)
    {
        this.posY=posY;

    }


    void turnRight() {
        posX+=50;
        System.out.println("aktualni x: "+posX);
    }

    void turnLeft() {
        posX-=50;
        System.out.println("aktualni x: "+posX);
    }

    void turnUp() {
        posY-=50;
        System.out.println("aktualni y: "+posY);
    }

    void turnDown() {
        posY+=50;
        System.out.println("aktualni y: "+posY);
    }
}
