package com.company;

import java.util.HashMap;

public class Board {

    private static HashMap<String,Continent> continents = setUpContinents();

    private static HashMap<String, Continent> setUpContinents() {
        HashMap<String, Continent> map = createContinents();
        setUpTerritories(map);
        return map;
    }

    private static void setUpTerritories(HashMap<String, Continent> map) {
        //TODO
    }

    private static HashMap<String, Continent> createContinents() {
        HashMap<String, Continent> map = new HashMap<String, Continent>();
        addContinent("South America",map);
        addContinent("North America",map);
        addContinent("Europe",map);
        addContinent("Africa",map);
        addContinent("Asia",map);
        addContinent("Australia",map);
        return map;
    }

    private static void addContinent(String name, HashMap<String, Continent> map) {
        map.put(name, new Continent(name));
    }
}
