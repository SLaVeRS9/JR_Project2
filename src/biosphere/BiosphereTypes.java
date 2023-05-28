package biosphere;

public enum BiosphereTypes {
    WOLF ("\uD83D\uDC3A", "Predator"),
    FOX ("\uD83E\uDD8A", "Predator"),
    SNAKE("\uD83D\uDC0D", "Predator"),
    BEAR("\uD83D\uDC3B", "Predator"),
    EAGLE("\uD83E\uDD85", "Predator"),
    HORSE("\uD83D\uDC0E", "Herbivore"),
    DEER("\uD83E\uDD8C", "Herbivore"),
    RABBIT("\uD83D\uDC07", "Herbivore"),
    MOUSE("\uD83D\uDC01", "Herbivore"),
    GOAT("\uD83D\uDC10", "Herbivore"),
    SHEEP("\uD83D\uDC11", "Herbivore"),
    BOAR("\uD83D\uDC17", "Herbivore"),
    BUFFALO("\uD83D\uDC02", "Herbivore"),
    DUCK("\uD83E\uDD86", "Herbivore"),
    CATERPILLAR("\uD83D\uDC1B", "Herbivore"),
    BUSH("\uD83C\uDF3E", "Plant"),
    GRASS("\uD83C\uDF3F", "Plant");

    private final String UNICODE;

    BiosphereTypes(String unicode, String classification) {
        UNICODE = unicode;
    }

    public String getUnicode(){
        return UNICODE;
    }
}
