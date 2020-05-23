package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("How many players?");
        Scanner in = new Scanner(System.in);
        int numPlayers = in.nextInt();
        if (numPlayers > 2 && numPlayers <= 6) {
            Match match = new Match(numPlayers);
            match.play();
        } else {
            System.out.println("invalid number of players");
        }
    }
}
