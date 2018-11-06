package IPP.Flyweight;

public abstract class AbstractUnit implements IUnit {
    Integer health;
    UnitAppearance appearance;

    AbstractUnit(UnitAppearance appearance) {
        this.appearance = appearance;
        setHealth();
        if (this.health == null) {
            throw new IllegalArgumentException("Health is not set for objects of type ## " + this.getClass());
        }
    }

    /**
     * Should assign a value to 'health' variable
     */
    abstract void setHealth();

    @Override
    public boolean isAlive() {
        return health > 0;
    }

}
