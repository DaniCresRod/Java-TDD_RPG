package org.example;

import java.util.ArrayList;

public class Faction {
    public ArrayList<String> FactionList=new ArrayList<>();

    public ArrayList<String> getFactionList() {
        return FactionList;
    }

    public void addFactionToList(String newFaction) {
        FactionList.add(newFaction);
    }
}
