package com.company;

import ui.Panel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class Match {
    private ArrayList<Player> players;
    private static Board board;
    private Scanner in = new Scanner(System.in);
    private ArrayList<Player> playersLost = new ArrayList<Player>();
    private Panel panel;
    private Player currPlayer;

    public Match(int numPlayers) throws Exception {
        makePlayers(numPlayers);
        distributeArmiesInitial();
        board = new Board();
        shufflePlayers();
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
                    System.out.print(currPlayer.getColor());
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
        panel.setPhase("Initial place armies:");
        for (Player player : players) {
            panel.setPlayer(player);
            placeArmies(player);
        }
    }

    private void placeArmies(Player player) {
        while (player.hasArmies()){
            String explanation = "You have " + player.getArmiesAvailable() + " armies available";
            panel.setExplanation(explanation);
            panel.setQuestion("Where to place armies?");
            printPlayerTerritories(player);
            String territoryName;
            // territoryName = in.next();
            territoryName = getInput();
            //territoryName = territoryName.replaceAll("_", " ");
            if (board.playerIsOwner(player, territoryName)){
                panel.setQuestion("How many armies do you want to place at that territory?");
                String sNumArmies = getInput();
                int numArmies;
                // numArmies = in.nextInt();
                try{
                    numArmies = Integer.parseInt(sNumArmies);
                    board.getTerritory(territoryName).placeArmies(numArmies);
                    panel.refresh();
                } catch (Exception e) {
                    System.out.println("Not a Number entered");
                }
            } else {
                System.out.println("Territory not available");
            }
        }
    }

    private String getInput() {
        panel.temporaryBreak();
        return panel.getInput();
    }

    private void printPlayerTerritories(Player player) {
        Collection<Territory> territories = board.getPlayerTerritories(player);
        for (Territory territory : territories) {
            System.out.println(territory.getName());
        }
    }

    private void shufflePlayers() {
        int numPlayers = players.size();
        double doublePlayer = Math.random()*numPlayers;
        int firstplayer = (int) doublePlayer;
        ArrayList<Player> newPlayers = new ArrayList<Player>();
        for (int i = 0; i < numPlayers; i++){
            newPlayers.add(players.get((firstplayer + i) % numPlayers));
        }
        players = newPlayers;
    }

    public ArrayList<Player> getPlayers(){
        return players;
    }

    public static Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayersLost() {
        return playersLost;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }
}
