package com.example.gruti.Objects;

/**
 * Created by gruti on 05.12.2016.
 */

public class Board {


        private static final int boardX=150;
        private static final int boardY=50;

        private int posX;
        private int posY;
        private int dificulty;



        public Board(int posX,int posY,int dificulty)
        {
            this.posX=posX;
            this.posY=posY;
            this.dificulty=dificulty;
        }



        public void moveBoardLeft()
        {
            posX=posX-(2*dificulty);
        }
        public void moveBoardRight(){ posX=posX+(2*dificulty);}



        public int getPosX()
        {
            return posX;
        }

        public int getPosY()
        {
            return posY;
        }
        public int getSpeed(){return (2*dificulty);}



    }
