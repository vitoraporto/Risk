package com.company;

import ui.Panel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

//todo: test and correct for negative inputs
//todo: da erro sempre quando bota input invalido
public class Match {
    private LinkedList<Player> players;
    private static Board board;
    private Panel panel;
    private Player currPlayer;
    private Phase phase;
    private Territory selectedTerritory1;
    private Territory selectedTerritory2;

    public Match(int numPlayers) throws Exception {
        makePlayers(numPlayers);
        distributeArmiesInitial();
        board = new Board();
        shufflePlayers();
        board.distributeTerritories(players);
        setCurrPlayer(players.get(0));
        phase = Phase.initialPlaceArmiesCountry;
    }

    public void start() {
        panel.setAllLabels(phase,currPlayer);
    }

    public void processInput(String text) {
        if (phase == Phase.initialPlaceArmiesCountry){
            ProcessInitialPlaceArmiesCountry(text);
        } else if (phase == Phase.initialPlaceArmiesNumber){
            try{
                ProcessInitialPlaceArmiesNumber(text);
            } catch (NotEnoughArmiesException e){
                System.out.println("Don't have enough armies");
            } catch (Exception e) {
                System.out.println("Not a Number entered");
            }
        } else if (phase == Phase.PlaceArmiesCountry){
            ProcessPlaceArmiesCountry(text);
        } else if (phase == Phase.PlaceArmiesNumber){
            try{
                ProcessPlaceArmiesNumber(text);
            } catch (NotEnoughArmiesException e){
                System.out.println("Don't have enough armies");
            } catch (Exception e) {
                System.out.println("Not a Number entered");
            }
        } else if (phase == Phase.AttackingFrom){
            ProcessAttackingFrom(text);
        } else if (phase == Phase.AttackingTo){
            ProcessAttackingTo(text);
        } else if (phase == Phase.AttackingNo){
            try{
                ProcessAttackingNo(text);
            } catch (Exception e){
                System.out.println("Not a Number entered");
            }
        } else if (phase == Phase.FortifyingFrom){
            //todo
        } else if (phase == Phase.FortifyingTo){
            //todo
        } else if (phase == Phase.FortifyingNo){
            //todo
        }
        panel.setAllLabels(phase,currPlayer);
    }

    private void ProcessAttackingNo(String text) {
        int numArmies = Integer.parseInt(text);
        if (numArmies > 3 || numArmies < 1){
            System.out.println("Over the maximum number of 3 attacking armies or not positive");
        } else {
            if (selectedTerritory1.getArmies() > numArmies){
                attack(selectedTerritory1, selectedTerritory2, numArmies);
                phase = Phase.AttackingFrom;
            } else {
                System.out.println("don't have this number of armies to attack");
            }
        }
    }

    private void attack(Territory attacker, Territory defender, int armiesAttacking) {
        int armiesDefending = Math.min(3,defender.getArmies());
        ArrayList<Integer> attackingDices = rollDicesSorted(armiesAttacking);
        ArrayList<Integer> defendingDices = rollDicesSorted(armiesDefending);
        int minSize = Math.min(armiesAttacking,armiesDefending);
        int attackLost = 0;
        int defenseLost = 0;
        for (int i = 0; i < minSize; i++){
            if (attackingDices.get(attackingDices.size() - 1 - i) > defendingDices.get(defendingDices.size() - 1 - i)){
                defenseLost++;
            } else {
                attackLost++;
            }
        }
        if (defenseLost < defender.getArmies()){
            defender.reduceArmies(defenseLost);
            attacker.reduceArmies(attackLost);
        } else {
            Player looser = defender.getOwner();
            attacker.reduceArmies(armiesAttacking);
            defender.setOwner(attacker.getOwner());
            defender.setArmies(armiesAttacking-attackLost);
            if(board.getPlayerTerritories(looser).size() == 0){
                players.remove(looser);
            }
        }
    }

    private ArrayList<Integer> rollDicesSorted(int numberOfDices) {
        ArrayList<Integer> answer = new ArrayList<>();
        for (int i = 0; i<numberOfDices; i++){
            int dice = (int) Math.ceil(Math.random()*6);
            answer.add(dice);
        }
        Collections.sort(answer);
        System.out.println(answer); //todo: delete
        return answer;
    }

    private void ProcessAttackingTo(String text) {
        selectedTerritory2 = board.getTerritory(text);
        if (selectedTerritory2 != null){
            if (selectedTerritory1.isNeighbour(selectedTerritory2)){
                if (!board.playerIsOwner(currPlayer,text)){
                    phase = Phase.AttackingNo;
                } else {
                    System.out.println("player already owns the territory he wants to attack");
                }
            } else {
                System.out.println("player they don't have frontier");
            }
        } else {
            System.out.println("territory doesn't exist");
        }
    }

    private void ProcessAttackingFrom(String text) {
        if (text.equals("")){
            phase = Phase.FortifyingFrom;
            return;
        }
        if (board.playerIsOwner(currPlayer, text)){
            selectedTerritory1 = board.getTerritory(text);
            if (selectedTerritory1.getArmies() > 1){
            phase = Phase.AttackingTo;
            } else {
                System.out.println("Territory have only one army. You can not attack from it");
            }
        } else {
            System.out.println("Player don't have this country");
        }
    }

    private void ProcessPlaceArmiesNumber(String text) throws NotEnoughArmiesException {
        int numArmies = Integer.parseInt(text);
        selectedTerritory1.placeArmies(numArmies);
        if (currPlayer.hasArmies()){
            phase = Phase.PlaceArmiesCountry;
        } else {
            phase = Phase.AttackingFrom;
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

    private void ProcessInitialPlaceArmiesNumber(String text) throws NotEnoughArmiesException {
        int numArmies = Integer.parseInt(text);
        selectedTerritory1.placeArmies(numArmies);
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

    private void setCurrPlayer(Player player) {
        currPlayer = player;
    }

    private void makePlayers(int numPlayers) {
        players = new LinkedList<>();
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
        LinkedList<Player> newPlayers = new LinkedList<>();
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
