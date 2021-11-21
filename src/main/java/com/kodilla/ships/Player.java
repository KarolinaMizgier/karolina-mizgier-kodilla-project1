package com.kodilla.ships;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public class Player {
    private boolean playersTurn;
    private int score;
    private ButtonExtractor buttonExtractor = new ButtonExtractor();
    private Button currentBtn;
    private Button previousBtn;
    private Button nextButton;
    boolean isHit = false;

    public int getScore() {
        return score;
    }

    public boolean shoot(Button button, GridPane grid2) {
        Node node = button;
        int a = GridPane.getRowIndex(button);
        int b = GridPane.getColumnIndex(button);
        Button button1 = (Button) buttonExtractor.getNodeByRowCol(grid2, a - 1, b);
        Button button2 = (Button) buttonExtractor.getNodeByRowCol(grid2, a, b - 1);
        Button button3 = (Button) buttonExtractor.getNodeByRowCol(grid2, a + 1, b);
        Button button4 = (Button) buttonExtractor.getNodeByRowCol(grid2, a, b + 1);

        if ("x".equals(button.getUserData())) {
            button.setText("X");
            button.setStyle("-fx-background-color: #ff0000; ");
            button.setDisable(true);
            playersTurn = true;
            score++;

            if ((button1 != null && "x".equals(button1.getUserData()) && !button1.isDisable()) ||
                    (button2 != null && "x".equals(button2.getUserData()) && !button2.isDisable()) ||
                    (button3 != null && "x".equals(button3.getUserData()) && !button3.isDisable()) ||
                    (button4 != null && "x".equals(button4.getUserData()) && !button4.isDisable())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SCORE!");
                alert.setHeaderText("THIS SHIP IS BIGGER!");
                alert.setContentText("SHOOT AGAIN NEAR THIS FIELD!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("SCORE!");
                alert.setHeaderText("YOU SHOT THE WHOLE SHIP!");
                alert.setContentText("FIELDS AROUND IT ARE EMPTY");
                alert.showAndWait();
            }


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

        if (nextButton == null) {
            currentBtn = getRandomButton(grid1);
        } else {
            currentBtn = nextButton;
        }
        Object text = currentBtn.getText();

        if ("x".equals(text)) {
            System.out.println("HITTED");
            currentBtn.setText("X");
            currentBtn.setStyle("-fx-background-color: #ff0000; ");
            currentBtn.setDisable(true);
            playersTurn = true;
            score++;
            isHit = true;
            previousBtn = currentBtn;
            nextButton = getNearButton(grid1, currentBtn);
        } else if (!currentBtn.isDisabled()) {
            currentBtn.setText("*");
            currentBtn.setStyle("-fx-background-color: #ff9d00; ");
            currentBtn.setDisable(true);
            playersTurn = false;
            if (isHit) {
                nextButton = getNearButton(grid1, previousBtn);
            } else {
                nextButton = getRandomButton(grid1);
            }
            isHit = false;
        } else {
            playersTurn = true;
            isHit = false;
            nextButton = getRandomButton(grid1);
        }
        return playersTurn;
    }

    private Button getRandomButton(GridPane grid) {
        Button button;
        do {
            int a = getRandomNumberUsingNextInt(1, 11);
            int b = getRandomNumberUsingNextInt(1, 11);
            button = (Button) buttonExtractor.getNodeByRowCol(grid, a, b);
        } while (button.isDisabled());
        return button;
    }

    private Button getNearButton(GridPane grid, Button initBtn) {
        int a = GridPane.getRowIndex(initBtn);
        int b = GridPane.getColumnIndex(initBtn);
        List<Button> buttons = new ArrayList<>();
        buttons.add((Button) buttonExtractor.getNodeByRowCol(grid, a - 1, b));
        buttons.add((Button) buttonExtractor.getNodeByRowCol(grid, a, b - 1));
        buttons.add((Button) buttonExtractor.getNodeByRowCol(grid, a + 1, b));
        buttons.add((Button) buttonExtractor.getNodeByRowCol(grid, a, b + 1));

        List<Button> selectedButtons = buttons.stream()
                .filter(Objects::nonNull)
                .filter(btn -> !btn.isDisabled())
                .collect(Collectors.toList());

        if (selectedButtons.size() > 0) {
            Button button = null;
            while (button == null || button.isDisabled()) {
                int index = getRandomNumberUsingNextInt(0, selectedButtons.size());
                button = selectedButtons.get(index);
            }
            return button;
        }
        return getRandomButton(grid);
    }
}

