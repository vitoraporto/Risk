package com.company;

import java.awt.*;

public class Player {
    private Color color;
    private int armiesAvailable;


    public Player(int colour) {
        switch (colour){
            case 0:
                this.color = Color.black;
                break;
            case 1:
                this.color = Color.white;
                break;
            case 2:
                this.color = Color.red;
                break;
            case 3:
                this.color = Color.blue;
                break;
            case 4:
                this.color = Color.green;
                break;
            default:
                this.color = Color.yellow;
                break;
        }

    }

    public void setArmiesAvailable(int armies){
        armiesAvailable = armies;
    }

    public int getArmiesAvailable(){
        return armiesAvailable;
    }

    public void decreaseArmiesAvailable(int i) {
        armiesAvailable = armiesAvailable - i;
    }

    public Color getColor() {return color;}

    public boolean hasArmies() {
        return armiesAvailable > 0;
    }
}
