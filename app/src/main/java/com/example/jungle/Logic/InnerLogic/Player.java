package com.example.jungle.Logic.InnerLogic;

import java.io.Serializable;
import java.util.Random;

public class Player implements Serializable {

    //private String name;
    private int hp;
    private int heal;
    private int fireball;
    private int shield;
    private int doubleShot;
    private int treasures;
    private int daysSinceTreasure;
    private int gold;

    public Player(int setHp, int setSpecials) {
        //name = setName;
        hp = setHp;
        heal = setSpecials;
        fireball = setSpecials;
        shield = setSpecials;
        doubleShot = setSpecials;
        treasures = 0;
        daysSinceTreasure = 0;
        gold = 0;
    }
    //public String getName() { return name; }
    public void setHp(int sethp) {
        hp = sethp;
    }
    public int getHp() {
        return hp;
    }
    public void setHeal() {
        heal += -1;
    }
    public int getHeal() {
        return heal;
    }
    public void setFireball() {
        fireball += -1;
    }
    public int getFireball() {
        return fireball;
    }
    public void setShield() {
        shield += -1;
    }
    public int getShield() {
        return shield;
    }
    public void setDoubleShot() {
        doubleShot += -1;
    }
    public int getDoubleShot() {
        return doubleShot;
    }
    public void goToInn() {
        hp = 20;
        if (heal == 0) {
            heal += 1;
        }
        if (fireball == 0) {
            fireball += 1;
        }
        if (shield < 2) {
            shield += 1;
        }
        if (doubleShot < 2) {
            doubleShot += 1;
        }
        if (treasures == 0) {
            daysSinceTreasure = 0;
        } else {
            daysSinceTreasure += 1;
        }
        if (gold > 100) {
            int factor = gold / 100;
            treasures += factor;
            gold = gold - factor * 100;
        }
    }
    public int getTreasures() {
        return treasures;
    }
    public void newTreasure() {
        treasures += 1;
    }
    public void getGold(int value) {
        gold += value;
    }
    public int getGoldCount() {
        return gold;
    }
    public int getDaysSinceTreasure() {
        return daysSinceTreasure;
    }
    public void shaman() {
        heal += 1;
        fireball += 1;
        shield += 1;
        doubleShot += 1;
    }
    public void damage(int setDamage) {
        hp -= setDamage;
    }
    public void addRandomSpecial() {
        int random = new Random().nextInt(4);
        if (random == 0) { heal += 1; }
        if (random == 1) { fireball += 1; }
        if (random == 2) { shield += 1; }
        if (random == 3) { doubleShot += 1; }
    }
    public void removeRandomSpecial() {
        int random = new Random().nextInt(4);
        if (random == 0) { heal -= 1; }
        if (random == 1) { fireball -= 1; }
        if (random == 2) { shield -= 1; }
        if (random == 3) { doubleShot -= 1; }
    }
    public void heal() {
        hp += 5;
        heal -= 1;
    }


}
