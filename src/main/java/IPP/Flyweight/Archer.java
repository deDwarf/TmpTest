package IPP.Flyweight;

import java.util.Random;

public class Archer extends AbstractUnit {
    private final String weapon = "BOW";
    private static Random random = new Random(432);

    public Archer(UnitAppearance appearance) {
        super(appearance);
    }

    @Override
    void setHealth() {
        this.health = 350;
    }

    @Override
    public int hit(IUnit unit) {
        if (!this.isAlive()) {
            return 0;
        }
        int damage = random.nextInt(130);
        System.out.println(String.format("An archer threw an arrow dealing %d damage to an enemy %s"
                , damage, unit.getType()));
        unit.receiveDamage(damage);
        return damage;
    }

    @Override
    public boolean receiveDamage(int damage) {
        health -= damage;
        if (health <= 0) {
            System.out.println(String.format("An archer in %s has died", appearance));
            return true;
        }
        return false;
    }

    @Override
    public String getType() {
        return "archer";
    }
}
