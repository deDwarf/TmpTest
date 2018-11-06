package IPP.Flyweight;

public interface IUnit {

    /**
     * Makes random amount of damage to given unit
     * @param unit unit to hit
     * @return damage done
     */
    int hit(IUnit unit);

    /**
     * Takes damage amount and subtracts it from current health
     * @param damage damage amount to be subtracted from current health
     * @return boolean representing whether received damage was fatal or not
     */
    boolean receiveDamage(int damage);

    boolean isAlive();

    String getType();
}
