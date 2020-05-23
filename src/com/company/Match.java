package com.company;

import java.util.ArrayList;

public class Match {
    private ArrayList<Player> players;
    private static Board board;

    public Match(int numPlayers) throws Exception {
        makePlayers(numPlayers);
        distributeArmiesInitial();
        board = new Board();
        shuflePlayers();
        board.distributeTerritories(players);
    }

    private void makePlayers(int numPlayers) {
        players = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++) {
            players.add(new Player(i));
        }
    }

    private void distributeArmiesInitial() throws Exception {
        int armies;
        switch (players.size()){
            case 3:
                armies = 35;
                break;
            case 4:
                armies = 30;
                break;
            case 5:
                armies = 25;
                break;
            case 6:
                armies = 20;
                break;
            default:
                throw new Exception("invalid number of players");
        }
        for (Player player : players) {
            player.setArmiesAvailable(armies);
        }
    }

    public void play() {
        initialPlaceArmies();
        //TODO
    }

    private void initialPlaceArmies() {
        for (Player player : players) {
            placeArmies(player);
        }
    }

    private void placeArmies(Player player) {
        System.out.print("you have ");
        System.out.print(player.getArmiesAvailable());
        System.out.println(" armies available");
        //TODO
    }

    private void shuflePlayers() {
        int numPlayers = players.size();
        int firstplayer = (int) Math.random()*numPlayers;
        ArrayList<Player> newPlayers = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++){
            newPlayers.add(players.get((firstplayer + i) % numPlayers));
        }
        players = newPlayers;
    }
}
