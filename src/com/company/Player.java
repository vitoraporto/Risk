package com.company;

public class Player {
    private String colour;

    public Player(int colour) {
        switch (colour){
            case 0:
                this.colour = "black";
                break;
            case 1:
                this.colour = "white";
                break;
            case 2:
                this.colour = "red";
                break;
            case 3:
                this.colour = "blue";
                break;
            case 4:
                this.colour = "green";
                break;
            default:
                this.colour = "yellow";
                break;
        }

    }
}
