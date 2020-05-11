package com.company;

import java.util.HashSet;

public class Continent {
    private String name;
    private HashSet<Territory> territories;

    public Continent(String name){
        this.name = name;
        territories = new HashSet<Territory>();
    }

    public void addTerritory(Territory territory) {
        territories.add(territory);
    }
}
