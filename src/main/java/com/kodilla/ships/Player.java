package com.kodilla.ships;

import javafx.scene.control.Button;

public class Player {
    private int score;

    public int getScore() {
        return score;
    }

    public int shoot(Button button){
        if("x".equals(button.getUserData())){
            button.setText("x");
           return score++;
        }else{
            button.setText("*");
            return score;
        }
    }
}
