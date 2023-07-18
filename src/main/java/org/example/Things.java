package org.example;

public class Things extends Character {

    public String name;
    public Things() {
        super();
    }

    public Things(characterRange thisCharTypeClass) {
        super(null);
    }

    public Things(String nameArg, int healthArg){
        super();
        this.name=nameArg;
        this.setHealth(healthArg);
    }

    @Override
    public void JoinFaction(String newFaction) {
        System.out.println("Doesn't join Factions");
    }

    @Override
    public void LeaveFaction(String theFaction) {
    }

    @Override
    public void DealDamage(int damage, Character targetCharacter, double distance) {
        System.out.println("Doesn't deal damage");
    }

    @Override
    protected void receiveHealing(int amountOfHealing) {

    }

    @Override
    public int getMaxRange() {
        return 0;
    }
}
