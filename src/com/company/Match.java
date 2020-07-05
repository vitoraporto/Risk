package com.company;

import ui.Panel;

import java.util.LinkedList;
import java.util.Scanner;

public class Match {
    private LinkedList<Player> players;
    private static Board board;
    private Scanner in = new Scanner(System.in);
    private Panel panel;
    private Player currPlayer;
    private Phase phase;
    private Territory selectedTerritory1;

    public Match(int numPlayers) throws Exception {
        makePlayers(numPlayers);
        distributeArmiesInitial();
        board = new Board();
        shufflePlayers();
        board.distributeTerritories(players);
        setCurrPlayer(players.get(0));
        setPhase(Phase.initialPlaceArmiesCountry);
    }

    public void start() {
        panel.setAllLabels(phase,currPlayer);
    }

    public void processInput(String text) {
        if (phase == Phase.initialPlaceArmiesCountry){
            ProcessInitialPlaceArmiesCountry(text);
        } else if (phase == Phase.initialPlaceArmiesNumber){
            ProcessInitialPlaceArmiesNumber(text);
        } else if (phase == Phase.PlaceArmiesCountry){
            ProcessPlaceArmiesCountry(text);
        } else if (phase == Phase.PlaceArmiesNumber){
            ProcessPlaceArmiesNumber(text);
        } else if (phase == Phase.AttackingFrom){
            ProcessAttackingFrom(text);
        } else if (phase == Phase.AttackingNo){
            //todo
        } else if (phase == Phase.AttackingTo){
            //todo
        } else if (phase == Phase.FortifyingFrom){
            //todo
        } else if (phase == Phase.FortifyingNo){
            //todo
        } else if (phase == Phase.FortifyingTo){
            //todo
        }
        panel.setAllLabels(phase,currPlayer);
    }

    private void ProcessAttackingFrom(String text) {
        //todo
    }

    private void ProcessPlaceArmiesNumber(String text) {
        try{
            int numArmies = Integer.parseInt(text);
            selectedTerritory1.placeArmies(numArmies);
            panel.refresh();
            if (currPlayer.hasArmies()){
                phase = Phase.PlaceArmiesCountry;
            } else {
                phase = Phase.AttackingFrom;
            }
        } catch (NotEnoughArmiesException e){
            System.out.println("Don't have enough armies");
        }
        catch (Exception e) {
            System.out.println("Not a Number entered");
        }
    }

    private void ProcessPlaceArmiesCountry(String text) {
        if (board.playerIsOwner(currPlayer, text)){
            selectedTerritory1 = board.getTerritory(text);
            phase = Phase.PlaceArmiesNumber;
        } else {
            System.out.println("Player don't have this country");
        }
    }

    private void ProcessInitialPlaceArmiesNumber(String text) {
        try{
            int numArmies = Integer.parseInt(text);
            selectedTerritory1.placeArmies(numArmies);
            panel.refresh();
            if (currPlayer.hasArmies()){
                phase = Phase.initialPlaceArmiesCountry;
            } else {
                if (players.getLast() == currPlayer){
                    phase = Phase.PlaceArmiesCountry;
                    currPlayer = players.getFirst();
                    setArmiesAvailable();
                } else {
                    phase = Phase.initialPlaceArmiesCountry;
                    currPlayer = players.get(players.indexOf(currPlayer)+1);
                }
            }
        } catch (NotEnoughArmiesException e){
            System.out.println("Don't have enough armies");
        }
        catch (Exception e) {
            System.out.println("Not a Number entered");
        }
    }

    private void setArmiesAvailable() {
        int newArmies = getTerritoryArmies(currPlayer);
        newArmies = newArmies + getContinentArmies(currPlayer);
        currPlayer.setArmiesAvailable(newArmies);
    }

    private void ProcessInitialPlaceArmiesCountry(String text) {
        if (board.playerIsOwner(currPlayer, text)){
            selectedTerritory1 = board.getTerritory(text);
            phase = Phase.initialPlaceArmiesNumber;
        } else {
            System.out.println("Player don't have this country");
        }
    }

    private void setPhase(Phase phase) {
        this.phase = phase;
    }

    private void setCurrPlayer(Player player) {
        currPlayer = player;
    }

    private void makePlayers(int numPlayers) {
        players = new LinkedList<Player>();
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

    private void shufflePlayers() {
        int numPlayers = players.size();
        double doublePlayer = Math.random()*numPlayers;
        int firstplayer = (int) doublePlayer;
        LinkedList<Player> newPlayers = new LinkedList<Player>();
        for (int i = 0; i < numPlayers; i++){
            newPlayers.add(players.get((firstplayer + i) % numPlayers));
        }
        players = newPlayers;
    }

    public LinkedList<Player> getPlayers(){
        return players;
    }

    public static Board getBoard() {
        return board;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public Player getCurrPlayer(){
        return currPlayer;
    }
}
