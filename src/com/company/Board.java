package com.company;

import java.io.FileReader;
import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Board {

    private static HashMap<String,Territory> territories;

    public Board() {
        territories = addTerritories();
    }

    private static HashMap addTerritories() {
        HashMap<String,Territory>  territories = new HashMap<>();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("/Users/vitorporto/IdeaProjects/Risk/src/map/Map.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.

            JSONObject jsonObject = (JSONObject) obj;

            JSONArray terrs = (JSONArray) jsonObject.get("territories");

            Iterator<JSONObject> iterator = terrs.iterator();

            while (iterator.hasNext()) {
                JSONObject jsObj = iterator.next();
                String objName = (String) jsObj.get("name");
                String objSCont = (String) jsObj.get("continent");
                Continent objCont = objCont(objSCont);
                territories.put(objName, new Territory(objName,objCont));
            }

            Iterator<JSONObject> iterator2 = terrs.iterator();

            while (iterator2.hasNext()) {
                JSONObject jsObj = iterator2.next();
                String objName = (String) jsObj.get("name");
                JSONArray neighbours = (JSONArray) jsObj.get("neighbours");

                Iterator<JSONObject> iterator3 = neighbours.iterator();

                while (iterator3.hasNext()) {
                    territories.get(objName).addNeighbour(territories.get(iterator3.next()));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return territories;
    }

    private static Continent objCont(String objSCont) {
        switch (objSCont) {
            case "North America":
                return Continent.NorthAmerica;
            case "South America":
                return Continent.SouthAmerica;
            case "Europe":
                return Continent.Europe;
            case "Africa":
                return Continent.Africa;
            case "Asia":
                return Continent.Asia;
            default:
                return Continent.Australia;
        }
    }

    public void distributeTerritories(ArrayList<Player> players) {
        ArrayList<Territory> arrayTerritories = new ArrayList<>();
        for (Territory terr : territories.values()){
            arrayTerritories.add(terr);
        }
        Collections.shuffle(arrayTerritories);
        for (int i = 0; i < arrayTerritories.size(); i++){
            arrayTerritories.get(i).setOwner(players.get(i % players.size()));
        }
    }
}
