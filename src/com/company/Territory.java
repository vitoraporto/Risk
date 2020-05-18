package com.company;

import java.util.HashSet;
import java.util.Set;

public class Territory {
    private String name;
    private Continent continent;
    private Set<Territory> neighbours = new HashSet<Territory>();

    public Territory(String name, Continent continent){
        this.name = name;
        this.continent = continent;
    }

    public void addNeighbour(Territory neighbour){
        if (!neighbours.contains(neighbour)) {
            neighbours.add(neighbour);
            neighbour.addNeighbour(this);
        }
    }
}
