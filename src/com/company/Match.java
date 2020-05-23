package com.company;

import java.util.ArrayList;

public class Match {
    private ArrayList<Player> players;
    private static Board board;

    public Match(int numPlayers) {
        players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(i));
        }
        board = new Board();
        board.distributeTerritories(players);
    }

    public void play() {
        //TODO
    }
}
