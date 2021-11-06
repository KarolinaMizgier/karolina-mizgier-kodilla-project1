package com.kodilla.ships;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ComputerBoard {

    public InputStream getBoard() {
        Random random = new Random();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is;
        int number = random.nextInt(5);
        switch (number) {
            case 1:
                return classloader.getResourceAsStream("board1.txt");
            case 2:
                return classloader.getResourceAsStream("board2.txt");
            case 3:
                return classloader.getResourceAsStream("board3.txt");
            case 4:
                return classloader.getResourceAsStream("board4.txt");
            case 5:
                return classloader.getResourceAsStream("board5.txt");
        }
        return null;
    }


    public GridPane setBoard(GridPane grid2) {
        ButtonExtractor buttonExtractor = new ButtonExtractor();
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        Random random = new Random();
        InputStream is = getBoard();
        String result = new BufferedReader(new InputStreamReader(is))
                .lines().collect(Collectors.joining("\n"));
        System.out.println(result);
        char[] chars = result.toCharArray();

        System.out.println("Begin");
        for (int i = 0; i < chars.length; i++) {
            System.out.print(chars[i]);
        }
        System.out.println("End");

        char[][] tab = new char[11][11];
        for (int i = 0, n = 0, m = 0; i < chars.length; i++) {
            char temp = chars[i];
            if (temp != '\n') {
                tab[n][m] = temp;
                n++;
            } else {
                n = 0;
                m++;
            }
        }

        for (int n = 1; n < 11; n++) {
            for (int m = 1; m < 11; m++) {
                char temp = tab[n][m];
                if (temp == 'x') {
                    buttonExtractor.getNodeByRowCol(grid2, n, m).setUserData("x");
                } else {
                    buttonExtractor.getNodeByRowCol(grid2, n, m).setUserData("*");
                }
            }
        }

        return grid2;
    }
}

