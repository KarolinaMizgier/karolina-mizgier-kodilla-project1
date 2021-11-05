package com.kodilla.ships;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

public class ButtonExtractor {
    public Node getNodeByRowCol(GridPane grid, int row, int col) {

        for (Node node : grid.getChildren()) {
            if (GridPane.getRowIndex(node) == row && GridPane.getColumnIndex(node) == col)
                return node;
        }
        return null;
    }
}
