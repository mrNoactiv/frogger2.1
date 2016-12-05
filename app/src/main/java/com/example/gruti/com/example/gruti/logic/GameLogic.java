package com.example.gruti.com.example.gruti.logic;


import android.content.Context;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import com.example.gruti.Objects.Board;
import com.example.gruti.Objects.Car;
import com.example.gruti.Objects.Fly;
import com.example.gruti.Objects.Hero;
import com.example.gruti.frogger21.R;
import com.example.gruti.view.GameView;

import java.util.Iterator;
import java.util.List;

public class GameLogic {


    Event event;
    MediaPlayer mediaPlayer;

    int level=2;
    int gameScore=0;
    int lifes=3;

    //pozice žaby
    private int posX=102*3;
    private int posY=102*9;
    public Hero hero= new Hero(posX,posY,3);

    //list aut
    public List<Car> cars;
    private int leftCarPosX=102*6;
    private int lefCarPosY=102*8;

    //moucha
    public Fly fly=new Fly(102*2,0);


    //dřeva
    public List<Board> leftBoards;
    public List<Board> rightBoards;

    public GameLogic(Event event)
    {
        this.event=event;
    }


    public boolean update(int gameTime) {

        hero.getPosX();
        hero.getPosY();
        carLogic();
        boardLogic();
        spawmCar(gameTime);
        spawmFly(gameTime);
        spawnBoard(gameTime);
        checkFly();


        if(checkWater() && checkBounds()&& checkHit()&& checkFire())
        {
            return true;
        }
            else
        {
            lifes--;
            return false;
        }

    }


    public boolean checkBounds()
    {
        boolean ok=true;

        if(hero.getPosX()>612 || hero.getPosX()<0)
        {
            ok=false;
            event.onDeathListener("Spadl jsi z platformy.");
            event.onSoundTrigger(R.raw.sound_frogger_squash);
        }
        return ok;
    }


    public boolean checkWater()
    {
        boolean ok=true;
        if(hero.getPosY()<=510 && hero.getPosY()>306 || hero.getPosY()<=204 && hero.getPosY()>0  )
        {
            if(onLeftBoard() || onRightBoard())
            {
                ok=true;
            }
            else
            {
                event.onDeathListener("Neumíš plavat");
                ok=false;
            }

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

    public boolean checkFly()
    {
        boolean hit=false;
        if( (hero.getPosX()>=fly.getPosX() && hero.getPosX()<=fly.getPosX()+50 ) && hero.getPosY()==fly.getPosY())
        {
            hit=true;
            gameScore++;
            hero.setPosX(posX);
            hero.setPosY(posY);
        }
        return hit;
    }
    public boolean checkFire()
    {
        boolean ok;
        int x=hero.getPosX();
        int y=hero.getPosY();

        if(((x >=0 && x<=102+30)||    (x>=(102*3-30)&& x<=(102*3+30))     ||     (x>=(102*6-30)&& x<=(102*6+30))    )&& y==0)
        {
            event.onDeathListener("Hoříš");
            ok=false;
        }
        else
            ok=true;

        return ok;
    }


    public void spawmCar(int time)
    {
        if(time%(5*level)==0)//vykreslovani aut
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

    public void spawmFly(int time)
    {
        if(time%50==0)
        {
            int randFly=1 + (int)(Math.random()*3);
            switch(randFly)
            {
                case 1:
                    fly.setPosX(102*2);
                    fly.setPosY(0);
                    fly.setPosition(1);

                    break;
                case 2:
                    fly.setPosX(102*4);
                    fly.setPosY(0);
                    fly.setPosition(2);
                    break;

                case 3:
                    fly.setPosX(102*5);
                    fly.setPosY(0);
                    fly.setPosition(3);
                    break;
            }


        }
    }

    public void spawnBoard(int time)
    {
        if(time%(40*level)==0)
        {
            int randBoard=1 + (int)(Math.random()*4);//vykreslovani dřev
            switch(randBoard)
            {
                case 1:
                    leftBoards.add(new Board(102*6,102*2));

                    break;
                case 2:
                    rightBoards.add(new Board(0,102));
                    break;

                case 3:
                    leftBoards.add(new Board(102*6,102*4));
                    break;
                case 4:
                    rightBoards.add(new Board(0,102*5));
                    break;
            }
        }


    }
    public void boardLogic()
    {
        for(Iterator<Board> leftBoardIteratorMove = leftBoards.iterator(); leftBoardIteratorMove.hasNext();)
        {
            Board board = (Board) leftBoardIteratorMove.next();
            if(board.getPosX()<-102)
            {
                leftBoardIteratorMove.remove();
            }

            if( (hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY())
            {
                hero.setPosX(hero.getPosX()-(board.getSpeed()));
            }
            board.moveBoardLeft();
        }


        for(Iterator<Board> rightBoardIteratorMove = rightBoards.iterator(); rightBoardIteratorMove.hasNext();)
        {
            Board board = (Board) rightBoardIteratorMove.next();

            if(board.getPosX()>800)
            {
                rightBoardIteratorMove.remove();
            }

            if( (hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY())
            {
                hero.setPosX(hero.getPosX()+board.getSpeed());
            }
            board.moveBoardRight();
        }
    }

    public Board getActualLeft()
    {
        Board actual=null;
        for(Iterator<Board> onBoardIterator = leftBoards.iterator(); onBoardIterator.hasNext();)
        {
            Board board = (Board) onBoardIterator.next();

            if(( (hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY()))
            {
                actual=board;
                //System.out.println("dostal jsi actual");
                break;
            }
            else
            {
                actual=null;
                //System.out.println("0");
            }
        }
        return actual;
    }

    public Board getActualRight()
    {
        Board actual=null;
        for(Iterator<Board> onBoardIterator = rightBoards.iterator(); onBoardIterator.hasNext();)
        {
            Board board = (Board) onBoardIterator.next();

            if(( (hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY()))
            {
                actual=board;
                //System.out.println("dostal jsi actual");
                break;
            }
            else
            {
                actual=null;
                //System.out.println("0");
            }
        }
        return actual;
    }




    public boolean onLeftBoard()
    {
        boolean onboard=true;
        Board board;

        board=getActualLeft();
        if(board!=null && ((hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY()))
        {
            onboard=true;
        }
        else
            onboard=false;

        return onboard;
    }

    public boolean onRightBoard()
    {
        boolean onboard=true;
        Board board;

        board=getActualRight();


        if(board!=null && ((hero.getPosX()>=board.getPosX() && hero.getPosX()<=board.getPosX()+204 ) && hero.getPosY()==board.getPosY()))
        {
            onboard=true;
        }
        else
            onboard=false;


        return onboard;

    }




}
