package com.example.gruti.Objects;

/**
 * Created by gruti on 01.12.2016.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Tomáš
 */
public class Car {

    private static final int carX = 40;
    private static final int carY = 40;

    private int posX;
    private int posY;
    private int dificulty;


    public Car(int posX, int posY,int dificulty) {
        this.posX = posX;
        this.posY = posY;
        this.dificulty=dificulty;
    }


    public void moveCarLeft() {

        posX = posX - (4*dificulty);
    }

    public void moveCarRight() {
        posX = posX + (4*dificulty);
    }



    public int getPosX()
    {
        return posX;
    }

    public int getPosY()
    {
        return posY;
    }


}
