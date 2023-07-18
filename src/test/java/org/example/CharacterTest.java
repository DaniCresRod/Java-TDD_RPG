package org.example;

import org.junit.jupiter.api.Test;

import static java.util.Objects.isNull;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    Character myCharacter=new Character();
    int distance=2;

    /*
    All Characters, when created, have:
        Health, starting at 1000
        Level, starting at 1
        May be Alive or Dead, starting Alive (Alive may be a true/false)
     */

    @Test
    public void Health_should_start_in_1000(){
        assertTrue(myCharacter.getHealth()>=1000);
    }

    @Test
    public void Level_should_start_at_1(){
        assertEquals(1, myCharacter.getLevel());
    }

    @Test
    public void Character_should_start_alive(){
        assertTrue(myCharacter.getAlive());
    }

    /*
    Characters can Deal Damage to Characters.
        Damage is subtracted from Health
        When damage received exceeds current Health, Health becomes 0 and the character dies
     */

    Character myCharacter1=new Character();
    Character myCharacter2=new Character();

    @Test
    public void Character_can_deal_damage_to_other_character(){
        int damage=40;

        myCharacter1.DealDamage(40, myCharacter2, distance);
        assertEquals(myCharacter2.getHealth(), 1000-damage);

    }

    @Test
    public void Character_dies_when_damage_is_greater_that_its_life(){
        myCharacter1.DealDamage(1001, myCharacter2, 2);
        assertFalse(myCharacter2.getAlive());
    }

    /*
    A Character can Heal a Character.
        Dead characters cannot be healed
        Healing cannot raise health above 1000
     */

//    @Test
//    public void Character_can_heal_character(){
//        int amountOfHealing=30;
//        int amountOfDamage=50;
//
//        myCharacter1.DealDamage(amountOfDamage, myCharacter2);
//        int oldHealth=myCharacter2.getHealth();
//
//        myCharacter1.PerformHealing(amountOfHealing, myCharacter2);
//
//        assertEquals(myCharacter2.getHealth(), oldHealth+amountOfHealing);
//    }

    @Test
    public void Dead_Character_can_not_be_healed(){
        int amountOfHealing=30;
        int amountOfDamage=5000;

        myCharacter1.DealDamage(amountOfDamage, myCharacter2, distance);

        myCharacter1.PerformHealing(amountOfHealing, myCharacter2);

        assertEquals(myCharacter2.getHealth(), 0);
    }

//    @Test
//    public void Healing_cannot_raise_health_over_1000(){
//        int amountOfHealing=3000;
//        int amountOfDamage=50;
//
//        myCharacter1.DealDamage(amountOfDamage, myCharacter2);
//
//        myCharacter1.PerformHealing(amountOfHealing, myCharacter2);
//
//        assertTrue(myCharacter2.getHealth()==1000);
//    }

    /////////////////////// ITERATION 2 //////////////////////////////////
    /*
    A Character cannot Deal Damage to itself.
     */

    @Test
    public void Character_cannot_Deal_Damage_over_itself(){
        int amountOfDamage=50;
        int oldHealth=myCharacter1.getHealth();

        myCharacter1.DealDamage(amountOfDamage, myCharacter1, distance);

        assertEquals(oldHealth, myCharacter1.getHealth());
    }

    /*
    A Character can only Heal itself
     */

    @Test
    public void Character_can_only_heal_itself(){
        int amountOfHealing=50;

        myCharacter2.setHealth(100);
        int oldHealth=myCharacter2.getHealth();

        myCharacter1.PerformHealing(amountOfHealing, myCharacter2);

        assertEquals(oldHealth, myCharacter2.getHealth());
    }

    @Test
    public void Character_can_heal_character(){
        int amountOfHealing=30;
        int amountOfDamage=50;

        myCharacter2.DealDamage(amountOfDamage, myCharacter1, distance);
        int oldHealth=myCharacter1.getHealth();

        myCharacter1.PerformHealing(amountOfHealing, myCharacter1);

        assertEquals(myCharacter1.getHealth(), oldHealth+amountOfHealing);
    }

    @Test
    public void Healing_cannot_raise_health_over_1000(){
        int amountOfHealing=3000;
        int amountOfDamage=50;

        myCharacter2.DealDamage(amountOfDamage, myCharacter1, distance);

        myCharacter1.PerformHealing(amountOfHealing, myCharacter1);

        assertEquals(1000, myCharacter1.getHealth());
    }

    /*
    When dealing damage:
        If the target is 5 or more Levels above the attacker, Damage is reduced by 50%
        If the target is 5 or more Levels below the attacker, Damage is increased by 50%
     */

    @Test
    public void Damage_reduced_for_plus_5_levels_foes(){
        myCharacter2.setLevel(6);
        int amountOfDamage=100;

        myCharacter1.DealDamage(amountOfDamage, myCharacter2, distance);

        assertEquals(myCharacter2.getHealth(), 1000-(amountOfDamage*0.5) );
    }

    @Test
    public void Damage_increased_for_minus_5_levels_foes(){
        myCharacter1.setLevel(6);
        int amountOfDamage=100;

        myCharacter1.DealDamage(amountOfDamage, myCharacter2, distance);

        assertEquals(myCharacter2.getHealth(), 1000-(amountOfDamage*1.5) );
    }

    /////////////////////// ITERATION 3 //////////////////////////////////

    /*
    Characters have an attack Max Range.
        Melee fighters have a range of 2 meters.
        Ranged fighters have a range of 20 meters.
        Characters must be in range to deal damage to a target.
     */

    @Test
    public void Characters_have_an_attack_Max_Range(){
        assertFalse(isNull(myCharacter.getMaxRange()));

    }

    @Test
    public void Characters_must_be_in_range_to_deal_damage(){
        int distance=5;
        myCharacter1.setMaxRange(Character.characterRange.Ranged);

        int oldHealth2=myCharacter2.getHealth();
        int oldHealth1=myCharacter1.getHealth();

        myCharacter1.DealDamage(100, myCharacter2, distance);
        assertNotEquals(oldHealth2, myCharacter2.getHealth() );

        myCharacter2.DealDamage(100, myCharacter1, distance);
        assertEquals(oldHealth1, myCharacter1.getHealth() );
    }

    /////////////////////// ITERATION 4 //////////////////////////////////
    /*
    Characters may belong to one or more Factions.
        Newly created Characters belong to no Faction.
        A Character may Join or Leave one or more Factions.
        Players belonging to the same Faction are considered Allies.
        Allies cannot Deal Damage to one another.
        Allies can Heal one another.
     */

    @Test
    public void New_characters_belong_to_no_Faction(){

        assertTrue(myCharacter1.getMyFactionList().size()==0);
    }

    @Test
    public void characters_with_no_faction_are_not_allies(){
        assertFalse(myCharacter1.IsAlly(myCharacter2));
    }

    @Test
    public void Character_can_join_one_or_more_faction(){
        myCharacter1.JoinFaction("Los Hutt");

        assertTrue(myCharacter1.getMyFactionList().size()>0);
    }

    @Test
    public void Character_can_leave_one_or_more_faction(){
        String theFaction="Los Hutt";
//            System.out.println(myCharacter1.getMyFactionList());
//        myCharacter1.JoinFaction("Los puf");
        myCharacter1.JoinFaction(theFaction);
//            System.out.println(myCharacter1.getMyFactionList());
        myCharacter1.LeaveFaction(theFaction);
//            System.out.println(myCharacter1.getMyFactionList());

        assertFalse(myCharacter1.getMyFactionList().contains(theFaction));
    }

    @Test
    public void Character_belonging_same_faction_are_allies(){
        myCharacter1.JoinFaction("The Hutts");
        myCharacter2.JoinFaction("The Hutts");

//        System.out.println(myCharacter1.getMyFactionList());
//        System.out.println(myCharacter2.getMyFactionList());

        assertTrue(myCharacter1.IsAlly(myCharacter2));
    }

    @Test
    public void characters_donnot_deal_damage_to_allies(){
        myCharacter1.JoinFaction("The Hutts");
        myCharacter2.JoinFaction("The Hutts");

        int oldHealth2=myCharacter2.getHealth();
        myCharacter1.DealDamage(50, myCharacter2, 2);

        assertEquals(myCharacter2.getHealth(), oldHealth2);
    }

    @Test
    public void Allies_can_heal_each_other(){
        int amountOfHealing=30;

        myCharacter1.JoinFaction("The Hutts");
        myCharacter2.JoinFaction("The Hutts");

        myCharacter2.setHealth(100);
        int oldHealthBefore=myCharacter2.getHealth();
        myCharacter2.setHealth(oldHealthBefore+amountOfHealing);
        int HealthIfHealed=myCharacter2.getHealth();

        myCharacter2.setHealth(100);
        myCharacter1.PerformHealing(amountOfHealing, myCharacter2);

        assertEquals(HealthIfHealed ,myCharacter2.getHealth());
    }

    /////////////////////// ITERATION 5 //////////////////////////////////

    /*
    Characters can damage non-character things (props).
        Anything that has Health may be a target
        These things cannot be Healed and they do not Deal Damage
        These things do not belong to Factions; they are neutral
        When reduced to 0 Health, things are Destroyed
        As an example, you may create a Tree with 2000 Health
     */














}