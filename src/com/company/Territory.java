package com.company;

import java.util.ArrayList;
import java.util.HashSet;

public class Territory {
    private String name;
    private Continent continent;
    private HashSet<Territory> neighbours = new HashSet<Territory>();

    public Territory(String name, Continent continent){
        this.name = name;
        addContinent(continent);
    }

    private void addContinent(Continent continent) {
        this.continent = continent;
        continent.addTerritory(this);
    }

    public void addNeighbour(Territory neighbour){
        if (!neighbours.contains(neighbour)) {
            neighbours.add(neighbour);
            neighbour.addNeighbour(this);
        }
    }
}
