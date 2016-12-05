package com.example.gruti.Objects;

/**
 * Created by gruti on 05.12.2016.
 */

public class Fly {

    private static final int flyX=40;
    private static final int flyY=40;



    private int posX;
    private int posY;
    private int position;

    public Fly(int posX,int posY)
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
    public int getPosition()
    {
        return position;
    }

    public void setPosX(int posX)
    {
        this.posX=posX;
    }

    public void setPosY(int posY)
    {
        this.posY=posY;
    }
    public void setPosition(int position)
    {
        this.position=position;
    }



}
