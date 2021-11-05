package com.kodilla.ships;

import javafx.scene.control.Button;

public class Player {
    int score;

    public int getScore() {
        return score;
    }

    public int shoot(Button button){
        if(button.getUserData()=="x"){
            button.setText("x");
           return score+=1;
        }else{
            button.setText("*");
            return score;
        }
    }
}
