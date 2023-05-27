package biosphere;

public enum BiosphereTypes {
    WOLF ("\uD83D\uDC3A"),
    FOX ("\uD83E\uDD8A"),
    SNAKE("\uD83D\uDC0D"),
    BEAR("\uD83D\uDC3B"),
    EAGLE("\uD83E\uDD85"),
    HORSE("\uD83D\uDC0E"),
    DEER("\uD83E\uDD8C"),
    RABBIT("\uD83D\uDC07"),
    MOUSE("\uD83D\uDC01"),
    GOAT("\uD83D\uDC10"),
    SHEEP("\uD83D\uDC11"),
    BOAR("\uD83D\uDC17"),
    BUFFALO("\uD83D\uDC02"),
    DUCK("\uD83E\uDD86"),
    CATERPILLAR("\uD83D\uDC1B"),
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
