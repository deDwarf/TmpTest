package IPP.Flyweight;

import IPP.Flyweight.Archer;
import IPP.Flyweight.IUnit;
import IPP.Flyweight.UnitAppearance;
import IPP.Flyweight.Warrior;

public class FlyweightMain {

    public static void main(String[] args) {
        UnitAppearance archerAppearance = UnitAppearance.getAppearance(
                "red pants"
                , "legendary boots"
                , UnitAppearance.DEFAULT_ARMOR
                , UnitAppearance.DEFAULT_HELMET);

        UnitAppearance warriorAppearance = UnitAppearance.getAppearance(
                "black pants"
                , UnitAppearance.DEFAULT_BOOTS
                , "steal armor"
                , "steal helmet");

        IUnit archer = new Archer(archerAppearance);
        IUnit archer1 = new Archer(archerAppearance);
        IUnit warrior = new Warrior(warriorAppearance);
        IUnit warrior1 = new Warrior(warriorAppearance);

        System.out.println("#### ROUND 1");
        while (warrior.isAlive() && archer.isAlive()){
            archer.hit(warrior);
            warrior.hit(archer);
        }
        System.out.println("#### ROUND 2");
        while (archer1.isAlive() && warrior1.isAlive()){
            warrior1.hit(archer1);
            archer1.hit(warrior1);
        }

        IUnit winner1 = warrior.isAlive() ? warrior : archer;
        IUnit winner2 = warrior1.isAlive() ? warrior1 : archer1;

        System.out.println("#### FINAL");
        while (winner1.isAlive() && winner2.isAlive()){
            winner1.hit(winner2);
            winner2.hit(winner1);
        }
    }
}
