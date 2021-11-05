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
            if (!(n == x || m == y || n == x + 1 || n == x - 1 || n == x - 2 || n == x + 2 || m == y + 1 || m == y - 1 || m == y - 2 || m == y + 2)) {
                l.add(x);
                l.add(y);
            } else {
                x = randomInt(1, 10);
                y = randomInt(1, 10);
            }
        }
        return l;
    }


    public GridPane setBoard(GridPane grid2) {
        ButtonExtractor buttonEx = new ButtonExtractor();
        List<Node> excluded = new ArrayList<>();
        int temp = randomInt(1, 10);
        int temp2 = randomInt(1, 10);
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

        temps.removeAll(temps);
        temps = nextRandom(excluded);
        int temp5 = temps.get(0);
        int temp6 = temps.get(1);
        Node n5 = buttonEx.getNodeByRowCol(grid2, temp5, temp6);
        Node n6 = buttonEx.getNodeByRowCol(grid2, temp5, temp6+1);
        n5.setUserData("x");
        n6.setUserData("x");
        excluded.add(n5);
        excluded.add(n6);

        temps.removeAll(temps);
        temps = nextRandom(excluded);
        int temp7 = temps.get(0);
        int temp8 = temps.get(1);
        Node n7 = buttonEx.getNodeByRowCol(grid2, temp7, temp8);
        Node n8 = buttonEx.getNodeByRowCol(grid2, temp7, temp8+1);
        n7.setUserData("x");
        n8.setUserData("x");
        excluded.add(n7);
        excluded.add(n8);


        return grid2;

    }
}

