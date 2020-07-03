package com.company;

import java.util.HashSet;
import java.util.Set;

public class Territory {
    private String name;
    private Continent continent;
    private Set<Territory> neighbours = new HashSet<Territory>();
    private Player owner;
    private int armies = 1;
    private int lat;
    private int lon;

    public Territory(String name, Continent continent, int lat, int lon){
        this.name = name;
        this.continent = continent;
        this.lat = lat;
        this.lon = lon;
    }

    public void addNeighbour(Territory neighbour){
        if (!neighbours.contains(neighbour)) {
            neighbours.add(neighbour);
            neighbour.addNeighbour(this);
        }
    }

    public void setOwner(Player player) {
        if (player != owner){
            owner = player;
        }
    }

    public boolean isOwned(Player player) {
        return player == owner;
    }

    public String getName() {return name;}

    public void placeArmies(int numArmies) {
        if(owner.getArmiesAvailable() >= numArmies) {
            owner.decreaseArmiesAvailable(numArmies);
            increaseArmies(numArmies);
        } else {
            System.out.println("Do not have enough armies available");
        }
    }

    private void increaseArmies(int numArmies) {
        armies = armies + numArmies;
    }

    public boolean inContinent(Continent continent) {
        return this.continent == continent;
    }

    public int getLat() {
        return lat;
    }

    public int getLon() {
        return lon;
    }

    public int getArmies() {
        return armies;
    }

    public Player getOwner(){
        return owner;
    }
}
