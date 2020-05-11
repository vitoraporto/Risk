package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Match {
    private ArrayList<Player> players;
    private static Board board = new Board();

    public Match(int numPlayers) {
        players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(i));
        }
    }

    public void play() {
        //TODO
    }
}
