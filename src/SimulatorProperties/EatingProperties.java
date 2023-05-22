package SimulatorProperties;

import Biosphere.BiosphereTypes;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EatingProperties {
    private static final Map<BiosphereTypes, Map<BiosphereTypes, Integer>> chancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Map<BiosphereTypes, Integer>> unmodifiableChancesToEat = Collections.unmodifiableMap(chancesToEat);

    //Data for Wolf
    private static final Map<BiosphereTypes, Integer> wolfChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableWolfChancesToEat = Collections.unmodifiableMap(wolfChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.WOLF, unmodifiableWolfChancesToEat);
        wolfChancesToEat.put(BiosphereTypes.WOLF, 0);
        wolfChancesToEat.put(BiosphereTypes.FOX, 80);
        wolfChancesToEat.put(BiosphereTypes.SNAKE, 0);
        wolfChancesToEat.put(BiosphereTypes.BEAR, 0);
        wolfChancesToEat.put(BiosphereTypes.EAGLE, 0);
        wolfChancesToEat.put(BiosphereTypes.DEER, 15);
        wolfChancesToEat.put(BiosphereTypes.RABBIT, 60);
        wolfChancesToEat.put(BiosphereTypes.MOUSE, 80);
        wolfChancesToEat.put(BiosphereTypes.HORSE, 10);
        wolfChancesToEat.put(BiosphereTypes.GOAT, 60);
        wolfChancesToEat.put(BiosphereTypes.SHEEP, 70);
        wolfChancesToEat.put(BiosphereTypes.BOAR, 15);
        wolfChancesToEat.put(BiosphereTypes.BUFFALO, 10);
        wolfChancesToEat.put(BiosphereTypes.DUCK, 40);
        wolfChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        wolfChancesToEat.put(BiosphereTypes.GRASS, 0);
        wolfChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Fox
    private static final Map<BiosphereTypes, Integer> foxChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableFoxChancesToEat = Collections.unmodifiableMap(foxChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.FOX, unmodifiableFoxChancesToEat);
        foxChancesToEat.put(BiosphereTypes.WOLF, 0);
        foxChancesToEat.put(BiosphereTypes.FOX, 80);
        foxChancesToEat.put(BiosphereTypes.SNAKE, 0);
        foxChancesToEat.put(BiosphereTypes.BEAR, 0);
        foxChancesToEat.put(BiosphereTypes.EAGLE, 0);
        foxChancesToEat.put(BiosphereTypes.DEER, 15);
        foxChancesToEat.put(BiosphereTypes.RABBIT, 60);
        foxChancesToEat.put(BiosphereTypes.MOUSE, 80);
        foxChancesToEat.put(BiosphereTypes.HORSE, 10);
        foxChancesToEat.put(BiosphereTypes.GOAT, 60);
        foxChancesToEat.put(BiosphereTypes.SHEEP, 70);
        foxChancesToEat.put(BiosphereTypes.BOAR, 15);
        foxChancesToEat.put(BiosphereTypes.BUFFALO, 10);
        foxChancesToEat.put(BiosphereTypes.DUCK, 40);
        foxChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        foxChancesToEat.put(BiosphereTypes.GRASS, 0);
        foxChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Sheep
    private static final Map<BiosphereTypes, Integer> sheepChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableSheepChancesToEat = Collections.unmodifiableMap(sheepChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.SHEEP, unmodifiableSheepChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 80);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 15);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 60);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 80);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 10);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 60);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 70);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 15);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 10);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 40);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }
    public static Integer getChanceToEat(BiosphereTypes predator, BiosphereTypes victim){
        return unmodifiableChancesToEat.get(predator).get(victim);
    }
}
