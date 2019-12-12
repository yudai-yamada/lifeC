package com.example.lifecounter;

public class History {
    protected int change;
    protected int point;


    public History(int change,int point){
        this.change = change;
        this.point = point;

    }
    public int getPoint() {return point;}
    public int getChange() {return change;}
}

