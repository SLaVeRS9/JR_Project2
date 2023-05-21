package Biosphere;

import SimulatorProperties.EatingProperties;

public enum BiosphereTypes {
    WOLF ("\uD83D\uDC3A"),
    FOX ("\uD83E\uDD8A"),
    SNAKE("\uD83D\uDC0D"),
    BEAR("\u27a1"),
    EAGLE("\u27a1"),
    HORSE("\u27a1"),
    DEER("\u27a1"),
    RABBIT("\u27a1"),
    MOUSE("\u27a1"),
    GOAT("\u27a1"),
    SHEEP("\uD83D\uDC11"),
    BOAR("\u27a1"),
    BUFFALO("\u27a1"),
    DUCK("\u27a1"),
    CATERPILLAR("\u27a1"),
    BUSH("\uD83C\uDF3E"),
    GRASS("\uD83C\uDF3F");

    private String unicode;

    /*@Override
    public Integer getDieChanceFrom(BiosphereTypes predator) {
        return EatingProperties.getEatingChances(this, predator);
    }*/

    BiosphereTypes(String unicode) {
        this.unicode = unicode;
    }

    public String getUnicode(){
        return unicode;
    }
}
