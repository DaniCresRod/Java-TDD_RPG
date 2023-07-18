package org.example;


import java.util.ArrayList;
import java.util.List;

public class Character {

    Faction factionList=new Faction();

    private int health=1000;
    private int level=1;
    private boolean alive=true;
    private ArrayList<String> myFactionList=null;
    private characterRange characterClass;

    public enum characterRange{
        Melee(2), Ranged(20);

        private int range;

        characterRange(int range){
            this.range=range;
        }
    };

    public Character(){
        this.characterClass=characterRange.Melee;
        myFactionList=new ArrayList<String>();
    };

    public Character(characterRange thisCharTypeClass){

        switch(thisCharTypeClass){
            case Melee:
                this.characterClass=characterRange.Melee;
                break;
            case Ranged:
                this.characterClass=characterRange.Ranged;
                break;
        }

        myFactionList=new ArrayList<String>();
    }

//    public Character(int healthArg, int levelArg){
//        this.health=healthArg;
//        this.level=levelArg;
//    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if(this.alive){
            this.health = health;

            if(this.health>1000){
                this.health=1000;
            } else if (this.health<=0) {
                this.health=0;
                setAlive(false);
            }
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void DealDamage(int damage, Character targetCharacter, double distance){
        if(!IsAlly(targetCharacter)){
            if(distance<=this.characterClass.range){
                if(this!=targetCharacter){
                    if(this.level >= targetCharacter.level+5){
                        damage = (int) Math.floor(damage*1.5);
                    }
                    else if(this.level <= targetCharacter.level-5){
                        damage = (int) Math.floor(damage*0.5);
                    }

                    targetCharacter.GetDamage(damage);
                }
            }
        }
    }

    public void GetDamage(int damage){
        setHealth(this.health-damage);
    }

    public void PerformHealing(int amountOfHealing, Character myCharacter2) {
        if((this==myCharacter2) || this.IsAlly(myCharacter2)){
            myCharacter2.receiveHealing(amountOfHealing);
        }
    }

    protected void receiveHealing(int amountOfHealing) {
        setHealth(this.health+amountOfHealing);
    }

    public int getMaxRange() {
        return this.characterClass.range;
    }

    public void setMaxRange(characterRange characterClass) {
        this.characterClass = characterClass;
    }

    public ArrayList<String> getMyFactionList() {
        return myFactionList;
    }

    public void JoinFaction(String newFaction) {

        if(!this.myFactionList.contains(newFaction)){
            myFactionList.add(newFaction);
            if(!factionList.FactionList.contains(newFaction)){
                factionList.addFactionToList(newFaction);
            }
        }
    }

    public void LeaveFaction(String theFaction) {
        if(myFactionList.contains(theFaction)){
            myFactionList.remove(theFaction);
        }
    }

    public boolean IsAlly(Character targetCharacter){
        boolean charIsAlly=false;

        for(String eachFaction : myFactionList){
            if(targetCharacter.getMyFactionList().contains(eachFaction)){
                charIsAlly=true;
                break;
            }
        }
        return charIsAlly;
    }




}
