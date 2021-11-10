package com.kodilla.ships;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private boolean playersTurn;
    private int score;
    private List<Button> hitButtons;
    private int a;
    private int b;
    private ButtonExtractor buttonExtractor = new ButtonExtractor();

    public int getScore() {
        return score;
    }

    public boolean shoot(Button button) {

        if ("x".equals(button.getUserData())) {
            System.out.println("Wynik:" + score);
            score++;
            System.out.println("Po dodaniu: " + score);
            button.setText("X");
            button.setStyle("-fx-background-color: #ff0000; ");
            button.setDisable(true);
            playersTurn = true;
        } else {
            button.setText("*");
            button.setStyle("-fx-background-color: #ff9d00; ");
            button.setDisable(true);
            playersTurn = false;
        }
        return playersTurn;
    }

    public int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }


    public boolean computerShoot(GridPane grid1) {
        Button button;
        a = getRandomNumberUsingNextInt(1, 11);
        b = getRandomNumberUsingNextInt(1, 11);

        Object text = "";

        button = (Button) buttonExtractor.getNodeByRowCol(grid1, a, b);
        text = button.getText();


        if (text == "x" && !button.isDisabled()) {
            button.setText("X");
            button.setStyle("-fx-background-color: #ff0000; ");
            button.setDisable(true);
            playersTurn = true;
            score++;
        } else if (button.isDisabled()) {
            playersTurn = true;
        } else {
            button.setText("*");
            button.setStyle("-fx-background-color: #ff9d00; ");
            button.setDisable(true);
            playersTurn = false;
        }
        return playersTurn;
    }
}

