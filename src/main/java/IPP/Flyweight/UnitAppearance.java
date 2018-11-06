package IPP.Flyweight;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UnitAppearance {
    public static final String DEFAULT_PANTS = "default pants";
    public static final String DEFAULT_BOOTS = "default boots";
    public static final String DEFAULT_ARMOR = "default armor";
    public static final String DEFAULT_HELMET = "default helmet";

    private String pants;
    private String boots;
    private String armor;
    private String helmet;

    private static List<UnitAppearance> appearences = new LinkedList<>();

    private UnitAppearance(String pants, String boots, String armor, String helmet) {
        this.pants = pants;
        this.boots = boots;
        this.armor = armor;
        this.helmet = helmet;
    }

    public static UnitAppearance getAppearance(String pants, String boots, String armor, String helmet) {
        Optional<UnitAppearance> match = appearences.stream()
                .filter(unitAppearance -> unitAppearance.pants.equalsIgnoreCase(pants))
                .filter(unitAppearance -> unitAppearance.boots.equalsIgnoreCase(boots))
                .filter(unitAppearance -> unitAppearance.armor.equalsIgnoreCase(armor))
                .filter(unitAppearance -> unitAppearance.helmet.equalsIgnoreCase(helmet))
                .findFirst();
        
        if (match.isPresent()) {
            return match.get();
        }
        else {
            UnitAppearance ua = new UnitAppearance(pants, boots, armor, helmet);
            appearences.add(ua);
            return ua;
        }
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s and %s", pants, boots, armor, helmet);
    }
}
