package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThingsTest {

    Character myCharacter=new Character();
    Things oneThing=new Things();

    @Test
    public void Things_cannot_be_healed() {
        int amountOfHealing=70;

        oneThing.setHealth(100);
        int oldHealth=oneThing.getHealth();

        myCharacter.PerformHealing(amountOfHealing, oneThing);

        assertNotEquals(oneThing.getHealth(), oldHealth+amountOfHealing);
    }

    @Test
    public void Things_do_not_deal_damage(){
        int oldHealth=myCharacter.getHealth();
        oneThing.DealDamage(80, myCharacter, 0);

        assertEquals(myCharacter.getHealth(), oldHealth);
    }

    @Test
    public void Things_do_not_belong_to_Factions(){
        oneThing.JoinFaction("The Hutts");

        assertTrue(oneThing.getMyFactionList().size()==0);
    }

    @Test
    public void Things_get_destroyed_when_health_to_0(){
        oneThing.setHealth(0);

        assertFalse(oneThing.getAlive());
    }

    @Test
    public void Create_a_tree(){
        Things tree=new Things("Tree", 2000);

        assertTrue(tree!=null);
    }



}