package com.kodilla.ships;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import static javafx.scene.paint.Color.BLACK;
import static javafx.scene.paint.Color.GRAY;

public class Ships extends Application {
    private Player computer = new Player();
    private Player player = new Player();
    private GridPane grid2 = new GridPane();
    private GridPane grid1 = new GridPane();
    private GridPane mainGrid = new GridPane();
    private ButtonExtractor extractor = new ButtonExtractor();
    private boolean playersTurn = true;
    private static final String yourBoard = "YOUR BOARD";
    private static final String opponentBoard = "YOUR OPPONENT'S BOARD";
    private static final String instructions1 = "Mark your ships on your board by clicking on selected fields.";
    private static final String instructions2 = "You start the game by shooting on your opponent's board.";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        int numRows = 11;
        int numColumns = 11;
        for (int row = 0; row < numRows; row++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid1.getRowConstraints().add(rc);
        }
        for (int col = 0; col < numColumns; col++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid1.getColumnConstraints().add(cc);
        }

        String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
        for (int i = 0; i < 10; i++) {
            Button button = new Button(letters[i]);
            button.setPrefHeight(100);
            button.setPrefWidth(100);
            button.setDisable(true);
            grid1.add(button, i + 1, 0);
        }
        for (int i = 0; i < 10; i++) {
            Button button = new Button(String.valueOf(i + 1));
            button.setPrefHeight(100);
            button.setPrefWidth(100);
            button.setDisable(true);
            grid1.add(button, 0, i + 1);
        }


        for (int i = 1; i < 11; i++) {
            for (int n = 1; n < 11; n++) {
                Button button = createButton("");
                grid1.add(button, i, n);
            }
        }

        for (int row = 0; row < numRows; row++) {
            RowConstraints rc = new RowConstraints();
            rc.setFillHeight(true);
            rc.setVgrow(Priority.ALWAYS);
            grid2.getRowConstraints().add(rc);
        }
        for (int col = 0; col < numColumns; col++) {
            ColumnConstraints cc = new ColumnConstraints();
            cc.setFillWidth(true);
            cc.setHgrow(Priority.ALWAYS);
            grid2.getColumnConstraints().add(cc);
        }

        for (int i = 0; i < 10; i++) {
            Button button = new Button(letters[i]);
            button.setPrefHeight(100);
            button.setPrefWidth(100);
            button.setDisable(true);
            grid2.add(button, i + 1, 0);
        }

        for (int i = 0; i < 10; i++) {
            Button button = new Button(String.valueOf(i + 1));
            button.setPrefHeight(100);
            button.setPrefWidth(100);
            button.setDisable(true);
            grid2.add(button, 0, i + 1);
        }

        for (int i = 1; i < 11; i++) {
            for (int n = 1; n < 11; n++) {
                Button button = createButton2();
                grid2.add(button, i, n);
            }
        }


        ComputerBoard board = new ComputerBoard();
        board.setBoard(grid2);

        grid1.setAlignment(Pos.BASELINE_LEFT);
        grid2.setAlignment(Pos.BASELINE_RIGHT);

        // CREATING RECTANGLE TO SEPARATE GRIDS
        Rectangle middleOfTheWindow = new Rectangle();
        middleOfTheWindow.setHeight(550);
        middleOfTheWindow.setWidth(100);
        middleOfTheWindow.setFill(GRAY);
        // CREATING LABELS FOR GAME BOARDS
        TextField playerLabel = new TextField(yourBoard);
        playerLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        playerLabel.setDisable(true);

        TextField commputerLabel = new TextField(opponentBoard);
        commputerLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
        commputerLabel.setDisable(true);
        // CREATING TEXT BOXES WITH GAME INSTRUCTIONS
        TextField gameInfo = new TextField(instructions1);
        gameInfo.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        gameInfo.setDisable(true);

        TextField gameInfo2 = new TextField(instructions2);
        gameInfo2.setFont(Font.font("Helvetica", FontWeight.BOLD, 14));
        gameInfo2.setDisable(true);


        mainGrid.add(grid1, 0, 1);
        mainGrid.add(middleOfTheWindow, 1, 1);
        mainGrid.add(grid2, 2, 1);
        mainGrid.add(playerLabel, 0, 0);
        mainGrid.add(commputerLabel, 2, 0);
        mainGrid.add(gameInfo, 0, 2);
        mainGrid.add(gameInfo2, 2, 2);

        Scene scene = new Scene(mainGrid, 1000, 620, BLACK);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


        primaryStage.setTitle("Statki");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // METHOD THAT CREATES BUTTONS ON COMPUTER BOARD
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefHeight(100);
        button.setPrefWidth(100);
        button.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY)) {
                if (event.getClickCount() == 2) {   //DOUBLE CLICK DELETES ASSIGNED SHIP
                    button.setText("");
                    button.setUserData("");
                }
                if (event.getClickCount() == 1) {   // ONE CLICK ASSIGNS SHIP
                    button.setText("x");
                    button.setUserData("x");
                }
            }
        });

        System.out.println(player.getScore());
        return button;
    }

    // METHOD THAT CREATES BUTTONS ON PLAYER'S BOARD
    public Button createButton2() {
        Button button = new Button();
        button.setPrefHeight(100);
        button.setPrefWidth(100);
        button.setOnAction(e -> {
            boolean playersTurn = player.shoot(button, grid2);
            if (!playersTurn) {
                while (computer.computerShoot(grid1)) {
                }
            }

            int scoreComp = computer.getScore();
            int scorePlayer = player.getScore();
            if (scoreComp == 20) {
                System.out.println(scoreComp);
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("End of game");
                alert.setHeaderText("YOU LOST");
                alert.setContentText("Sorry!");
                alert.showAndWait();
            } else if (scorePlayer == 20) {
                System.out.println(scorePlayer);
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("End of game");
                alert2.setHeaderText("YOU WON");
                alert2.setContentText("Congratulations!");
                alert2.showAndWait();
            } else {
                System.out.println(scoreComp);
                System.out.println(scorePlayer);
            }
        });
        System.out.println(player.getScore());
        return button;
    }

}
//TODO generator tablicy statkow
