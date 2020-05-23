package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Match {
    private ArrayList<Player> players;
    private static Board board;
    private Scanner in = new Scanner(System.in);
    private ArrayList<Player> playersLost = new ArrayList<Player>();

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
        int currPlayerIdx = 0;
        Player currPlayer;
        while (true){
            currPlayer = players.get(currPlayerIdx);
            if (!playersLost.contains(currPlayer)){
                getAndPlaceArmies(currPlayer);
                attacking(currPlayer);
                if (board.won(currPlayer)){
                    System.out.print(currPlayer.getColour());
                    System.out.println(" won the game!");
                    break;
                }
                fortifying(currPlayer);
            }
            currPlayerIdx = (currPlayerIdx + 1) % players.size();
        }
    }

    private void fortifying(Player currPlayer) {
        //TODO
    }

    private void attacking(Player currPlayer) {
        //Todo
        //todo: remember to check if some player lost
    }

    private void getAndPlaceArmies(Player player) {
        int newArmies = getTerritoryArmies(player);
        newArmies = newArmies + getContinentArmies(player);
        player.setArmiesAvailable(newArmies);
        placeArmies(player);
    }

    private int getContinentArmies(Player player) {
        int armies = 0;
        if (board.controlContinent(player, Continent.NorthAmerica)){
            armies = armies + 5;
        }
        if (board.controlContinent(player, Continent.Australia)){
            armies = armies + 2;
        }
        if (board.controlContinent(player, Continent.Asia)){
            armies = armies + 7;
        }
        if (board.controlContinent(player, Continent.Africa)){
            armies = armies + 3;
        }
        if (board.controlContinent(player, Continent.Europe)){
            armies = armies + 5;
        }
        if (board.controlContinent(player, Continent.SouthAmerica)){
            armies = armies + 2;
        }
        return armies;
    }

    private int getTerritoryArmies(Player player) {
        int countTerr = board.countTerritories(player);
        int newArmies = countTerr/3;
        if (newArmies < 3) {
            newArmies = 3;
        }
        return newArmies;
    }

    private void initialPlaceArmies() {
        for (Player player : players) {
            System.out.print(player.getColour());
            System.out.println(" initial place armies:");
            placeArmies(player);
        }
    }

    private void placeArmies(Player player) {
        while (player.hasArmies()){
            System.out.print("you have ");
            System.out.print(player.getArmiesAvailable());
            System.out.println(" armies available");
            System.out.println("Where to place armies?");
            printPlayerTerritories(player);
            String territoryName = in.next();
            territoryName = territoryName.replaceAll("_", " ");
            if (board.playerIsOwner(player, territoryName)){
                System.out.println("How many armies do you want to place at that territory?");
                int numArmies = in.nextInt();
                board.getTerritory(territoryName).placeArmies(numArmies);
            } else {
                System.out.println("Territory not available");
            }
        }
    }

    private void printPlayerTerritories(Player player) {
        Collection<Territory> territories = board.getPlayerTerritories(player);
        for (Territory territory : territories) {
            System.out.println(territory.getName());
        }
    }

    private void shuflePlayers() {
        int numPlayers = players.size();
        double doublePlayer = Math.random()*numPlayers;
        int firstplayer = (int) doublePlayer;
        ArrayList<Player> newPlayers = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++){
            newPlayers.add(players.get((firstplayer + i) % numPlayers));
        }
        players = newPlayers;
    }
}
