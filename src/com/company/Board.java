package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {

    private static HashMap<String,Territory> territories;

    public Board() {
        territories = addTerritories();
        setNeighbours();
    }

    private static HashMap addTerritories() {
        HashMap<String,Territory>  territories = new HashMap<>();
        //addNorthAmerica(territories);
        //addSouthAmerica(territories);
        //addEurope(territories);
        //addAfrica(territories);
        //addAsia(territories);
        //addAustralia(territories);
        return territories;
    }

    private static void addAustralia(HashMap<String, Territory> territories) {
        territories.put("Eastern Australia", new Territory("Eastern Australia",Continent.Australia));
        territories.put("Indonesia", new Territory("Indonesia",Continent.Australia));
        territories.put("New Guinea", new Territory("New Guinea",Continent.Australia));
        territories.put("Western Australia", new Territory("Western Australia",Continent.Australia));
    }

    private static void addAsia(HashMap<String, Territory> territories) {
        territories.put("Afghanistan", new Territory("Afghanistan",Continent.Asia));
        territories.put("China", new Territory("China",Continent.Asia));
        territories.put("India", new Territory("India",Continent.Asia));
        territories.put("Irkutsk", new Territory("Irkutsk",Continent.Asia));
        territories.put("Japan", new Territory("Japan",Continent.Asia));
        territories.put("Kamchatka", new Territory("Kamchatka",Continent.Asia));
        territories.put("Middle East", new Territory("Middle East",Continent.Asia));
        territories.put("Mongolia", new Territory("Mongolia",Continent.Asia));
        territories.put("Siam", new Territory("Siam",Continent.Asia));
        territories.put("Siberia", new Territory("Siberia",Continent.Asia));
        territories.put("Ural", new Territory("Ural",Continent.Asia));
        territories.put("Yakutsk", new Territory("Yakutsk",Continent.Asia));
    }

    private static void addAfrica(HashMap<String, Territory> territories) {
        territories.put("Congo", new Territory("Congo",Continent.Africa));
        territories.put("East Africa", new Territory("East Africa",Continent.Africa));
        territories.put("Egypt", new Territory("Egypt",Continent.Africa));
        territories.put("Madagascar", new Territory("Madagascar",Continent.Africa));
        territories.put("North Africa", new Territory("North Africa",Continent.Africa));
        territories.put("South Africa", new Territory("South Africa",Continent.Africa));

    }

    private static void addEurope(HashMap<String, Territory> territories) {
        territories.put("Great Britain", new Territory("Great Britain",Continent.Europe));
        territories.put("Iceland", new Territory("Iceland",Continent.Europe));
        territories.put("Northern Europe", new Territory("Northern Europe",Continent.Europe));
        territories.put("Scandinavia", new Territory("Scandinavia",Continent.Europe));
        territories.put("Southern Europe", new Territory("Southern Europe",Continent.Europe));
        territories.put("Ukraine", new Territory("Ukraine",Continent.Europe));
        territories.put("Western Europe", new Territory("Western Europe",Continent.Europe));
    }

    private static void addSouthAmerica(HashMap<String, Territory> territories) {
        territories.put("Venezuela", new Territory("Venezuela",Continent.SouthAmerica));
        territories.put("Peru", new Territory("Peru",Continent.SouthAmerica));
        territories.put("Brazil", new Territory("Brazil",Continent.SouthAmerica));
        territories.put("Argentina", new Territory("Argentina",Continent.SouthAmerica));
    }

    private static void addNorthAmerica(HashMap<String, Territory> territories) {
        territories.put("Alaska", new Territory("Alaska",Continent.NorthAmerica));
        territories.put("Alberta", new Territory("Alberta",Continent.NorthAmerica));
        territories.put("Central America", new Territory("Central America",Continent.NorthAmerica));
        territories.put("Eastern United States", new Territory("Eastern United States",Continent.NorthAmerica));
        territories.put("Greenland", new Territory("Greenland",Continent.NorthAmerica));
        territories.put("Northwest Territory", new Territory("Northwest Territory",Continent.NorthAmerica));
        territories.put("Ontario", new Territory("Ontario",Continent.NorthAmerica));
        territories.put("Quebec", new Territory("Quebec",Continent.NorthAmerica));
        territories.put("Western United States", new Territory("Western United States",Continent.NorthAmerica));
    }

    private static void setNeighbours() {
        //setNeighboursNorthAmerica();
        //setNeighboursSouthAmerica();
        //setNeighboursEurope();
        //setNeighboursAfrica();
        //setNeighboursAsia();
        //setNeighboursAustralia();
    }

    private static void setNeighboursAustralia() {
        //TODO
    }

    private static void setNeighboursAsia() {
        //TODO
    }

    private static void setNeighboursAfrica() {
        //TODO
    }

    private static void setNeighboursEurope() {
        //TODO
    }

    private static void setNeighboursSouthAmerica() {
        //TODO
    }

    private static void setNeighboursNorthAmerica() {
        ArrayList<String> neighbours = new ArrayList<String>();
        neighbours.add("Alberta");
        neighbours.add("Northwest Territory");
        neighbours.add("Kamchatka");
        setNeighbours("Alaska", neighbours);
        //TODO
    }

    private static void setNeighbours(String main, ArrayList<String> neighbours) {
        for (String neighbour : neighbours) {
            territories.get(main).addNeighbour(territories.get(neighbour));
        }
    }
}
