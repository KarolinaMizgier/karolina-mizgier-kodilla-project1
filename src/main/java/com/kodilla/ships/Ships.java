package com.kodilla.ships;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.awt.event.MouseEvent;
import java.util.Objects;

import static javafx.scene.paint.Color.*;

public class Ships extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    Player computer = new Player();
    Player player = new Player();
    @Override
    public void start(Stage primaryStage) throws Exception {


        GridPane grid1 = new GridPane();
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

        grid1.add(createButton("A"), 1, 0);
        grid1.add(createButton("B"), 2, 0);
        grid1.add(createButton("C"), 3, 0);
        grid1.add(createButton("D"), 4, 0);
        grid1.add(createButton("E"), 5, 0);
        grid1.add(createButton("F"), 6, 0);
        grid1.add(createButton("G"), 7, 0);
        grid1.add(createButton("H"), 8, 0);
        grid1.add(createButton("I"), 9, 0);
        grid1.add(createButton("J"), 10, 0);

        grid1.add(createButton("1"), 0, 1);
        grid1.add(createButton("2"), 0, 2);
        grid1.add(createButton("3"), 0, 3);
        grid1.add(createButton("4"), 0, 4);
        grid1.add(createButton("5"), 0, 5);
        grid1.add(createButton("6"), 0, 6);
        grid1.add(createButton("7"), 0, 7);
        grid1.add(createButton("8"), 0, 8);
        grid1.add(createButton("9"), 0, 9);
        grid1.add(createButton("10"), 0, 10);

        for (int i = 1; i < 11; i++) {
            for (int n = 1; n < 11; n++) {
                Button button = createButton("");
                grid1.add(button, i, n);
            }
        }

        GridPane grid2 = new GridPane();
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
        grid2.add(createButton("A"), 1, 0);
        grid2.add(createButton("B"), 2, 0);
        grid2.add(createButton("C"), 3, 0);
        grid2.add(createButton("D"), 4, 0);
        grid2.add(createButton("E"), 5, 0);
        grid2.add(createButton("F"), 6, 0);
        grid2.add(createButton("G"), 7, 0);
        grid2.add(createButton("H"), 8, 0);
        grid2.add(createButton("I"), 9, 0);
        grid2.add(createButton("J"), 10, 0);

        Button button_1 = createButton("1");
        button_1.setDisable(true);
        grid2.add(button_1, 0, 1);
        grid2.add(createButton("2"), 0, 2);
        grid2.add(createButton("3"), 0, 3);
        grid2.add(createButton("4"), 0, 4);
        grid2.add(createButton("5"), 0, 5);
        grid2.add(createButton("6"), 0, 6);
        grid2.add(createButton("7"), 0, 7);
        grid2.add(createButton("8"), 0, 8);
        grid2.add(createButton("9"), 0, 9);
        grid2.add(createButton("10"), 0, 10);


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

        Rectangle r = new Rectangle();
        r.setHeight(400);
        r.setWidth(100);
        r.setFill(GRAY);


        GridPane mainGrid = new GridPane();
        mainGrid.add(grid1,0,0);
        mainGrid.add(r,2,0);
        mainGrid.add(grid2,3,0);

        Scene scene = new Scene(mainGrid,900, 400, BLACK);
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();


        primaryStage.setTitle("Statki");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setPrefHeight(100);
        button.setPrefWidth(100);
        button.setOnMouseClicked(new EventHandler<javafx.scene.input.MouseEvent>() {
            @Override
            public void handle(javafx.scene.input.MouseEvent event) {
                if(event.getButton().equals(MouseButton.PRIMARY)){
                    if(event.getClickCount() == 2){
                        button.setText("");
                    }if(event.getClickCount()==1){
                        button.setText("x");
                    }
                }
            }
        });
         System.out.println(player.getScore());
        return button;
    }

    public Button createButton2() {
        Button button = new Button();
        button.setPrefHeight(100);
        button.setPrefWidth(100);
        button.setOnAction(e -> {
            //TODO shoot
            computer.shoot(button);
        });

        System.out.println(computer.getScore());
        return button;
    }

}
