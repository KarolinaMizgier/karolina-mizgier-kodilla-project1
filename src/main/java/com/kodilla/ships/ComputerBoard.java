package com.kodilla.ships;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ComputerBoard {

    public int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public List<Integer> nextRandom(List<Node> excluded) {
        int x = randomInt(1, 10);
        int y = randomInt(1, 10);
        List<Integer> l = new ArrayList<>();
        for (Node node : excluded) {
            int n= GridPane.getRowIndex(node);
            int m = GridPane.getColumnIndex(node);
            if ((n == x) || (m == y) || (n == (x + 1)) || (n == (x - 1)) || (n == (x - 2)) || (n == (x + 2)) || (m == (y + 1)) || (m == (y - 1)) || (m == (y - 2)) || (m == (y + 2))) {
                nextRandom(excluded);
            } else {
                l.add(x);
                l.add(y);
            }
        }
        return l;
    }

    public GridPane setBoard(GridPane grid2) {
        ButtonExtractor buttonEx = new ButtonExtractor();
        List<Node> excluded = new ArrayList<>();
        int temp = randomInt(1,10);
        int temp2 = randomInt(1,10);
        Node n1 = buttonEx.getNodeByRowCol(grid2, temp, temp2);
        Node n2 = buttonEx.getNodeByRowCol(grid2, temp + 1, temp2);
        n1.setUserData("x");
        n2.setUserData("x");
        excluded.add(n1);
        excluded.add(n2);

        List<Integer> temps = nextRandom(excluded);
        int temp3 = temps.get(0);
        int temp4 = temps.get(1);
        Node n3 = buttonEx.getNodeByRowCol(grid2, temp3, temp4);
        Node n4 = buttonEx.getNodeByRowCol(grid2, temp3 + 1, temp4);
        n3.setUserData("x");
        n4.setUserData("x");
        excluded.add(n3);
        excluded.add(n4);




        return grid2;
    }
}

