package IPP.Flyweight;

import java.util.Random;

public class Warrior extends AbstractUnit {
    private final static String weapon = "SPEAR";
    private static Random random = new Random();

    public Warrior(UnitAppearance appearance) {
        super(appearance);
        this.health = 450;
    }

    @Override
    void setHealth() {
        this.health = 1000;
    }

    @Override
    public int hit(IUnit unit) {
        if (!this.isAlive()) {
            return 0;
        }
        int damage = random.nextInt(100);
        System.out.println(String.format("A warrior waved his spear dealing %d damage to an enemy %s"
                , damage, unit.getType()));
        unit.receiveDamage(damage);
        return damage;
    }

    @Override
    public boolean receiveDamage(int damage) {
        health -= damage;
        if (health > 0) {
            return false;
        }
        else {
            System.out.println(String.format("A warrior in %s has died with honor", appearance));
            return true;
        }
    }

    @Override
    public String getType() {
        return "warrior";
    }
}
