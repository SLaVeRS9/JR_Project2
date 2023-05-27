package simulatorProperties;

import biosphere.BiosphereTypes;
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
        foxChancesToEat.put(BiosphereTypes.FOX, 0);
        foxChancesToEat.put(BiosphereTypes.SNAKE, 0);
        foxChancesToEat.put(BiosphereTypes.BEAR, 0);
        foxChancesToEat.put(BiosphereTypes.EAGLE, 0);
        foxChancesToEat.put(BiosphereTypes.DEER, 0);
        foxChancesToEat.put(BiosphereTypes.RABBIT, 60);
        foxChancesToEat.put(BiosphereTypes.MOUSE, 80);
        foxChancesToEat.put(BiosphereTypes.HORSE, 0);
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
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 0);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 0);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 0);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Snake
    private static final Map<BiosphereTypes, Integer> snakeChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableSnakeChancesToEat = Collections.unmodifiableMap(snakeChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.SNAKE, unmodifiableSnakeChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 15);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 20);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 40);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 10);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Bear
    private static final Map<BiosphereTypes, Integer> bearChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableBearChancesToEat = Collections.unmodifiableMap(bearChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.BEAR, unmodifiableBearChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 80);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 80);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 80);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 90);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 40);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 70);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 70);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 50);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 20);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 10);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Eagle
    private static final Map<BiosphereTypes, Integer> eagleChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableEagleChancesToEat = Collections.unmodifiableMap(eagleChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.EAGLE, unmodifiableEagleChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 10);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 90);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 90);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 80);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Horse
    private static final Map<BiosphereTypes, Integer> horseChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableHorseChancesToEat = Collections.unmodifiableMap(horseChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.HORSE, unmodifiableHorseChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 0);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 0);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 0);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Deer
    private static final Map<BiosphereTypes, Integer> deerChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableDeerChancesToEat = Collections.unmodifiableMap(deerChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.DEER, unmodifiableDeerChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 0);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 0);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 0);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Rabbit
    private static final Map<BiosphereTypes, Integer> rabbitChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableRabbitChancesToEat = Collections.unmodifiableMap(rabbitChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.RABBIT, unmodifiableRabbitChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 0);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 0);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 0);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Mouse
    private static final Map<BiosphereTypes, Integer> mouseChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableMouseChancesToEat = Collections.unmodifiableMap(mouseChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.MOUSE, unmodifiableMouseChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 0);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 0);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 0);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 90);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Goat
    private static final Map<BiosphereTypes, Integer> goatChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableGoatChancesToEat = Collections.unmodifiableMap(goatChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.GOAT, unmodifiableGoatChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 0);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 0);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 0);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Boar
    private static final Map<BiosphereTypes, Integer> boarChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableBoarChancesToEat = Collections.unmodifiableMap(boarChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.BOAR, unmodifiableBoarChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 0);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 0);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 0);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 90);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Buffalo
    private static final Map<BiosphereTypes, Integer> buffaloChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableBuffaloChancesToEat = Collections.unmodifiableMap(buffaloChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.BUFFALO, unmodifiableBuffaloChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 0);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 0);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 0);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Duck
    private static final Map<BiosphereTypes, Integer> duckChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableDuckChancesToEat = Collections.unmodifiableMap(duckChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.DUCK, unmodifiableDuckChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 0);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 0);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 90);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    //Data for Caterpillar
    private static final Map<BiosphereTypes, Integer> caterpillarChancesToEat = new HashMap<>();
    private static final Map<BiosphereTypes, Integer> unmodifiableCaterpillarChancesToEat = Collections.unmodifiableMap(caterpillarChancesToEat);

    static {
        chancesToEat.put(BiosphereTypes.CATERPILLAR, unmodifiableCaterpillarChancesToEat);
        sheepChancesToEat.put(BiosphereTypes.WOLF, 0);
        sheepChancesToEat.put(BiosphereTypes.FOX, 0);
        sheepChancesToEat.put(BiosphereTypes.SNAKE, 0);
        sheepChancesToEat.put(BiosphereTypes.BEAR, 0);
        sheepChancesToEat.put(BiosphereTypes.EAGLE, 0);
        sheepChancesToEat.put(BiosphereTypes.DEER, 0);
        sheepChancesToEat.put(BiosphereTypes.RABBIT, 0);
        sheepChancesToEat.put(BiosphereTypes.MOUSE, 0);
        sheepChancesToEat.put(BiosphereTypes.HORSE, 0);
        sheepChancesToEat.put(BiosphereTypes.GOAT, 0);
        sheepChancesToEat.put(BiosphereTypes.SHEEP, 0);
        sheepChancesToEat.put(BiosphereTypes.BOAR, 0);
        sheepChancesToEat.put(BiosphereTypes.BUFFALO, 0);
        sheepChancesToEat.put(BiosphereTypes.DUCK, 0);
        sheepChancesToEat.put(BiosphereTypes.CATERPILLAR, 0);
        sheepChancesToEat.put(BiosphereTypes.GRASS, 0);
        sheepChancesToEat.put(BiosphereTypes.BUSH, 0);
    }

    public static Integer getChanceToEat(BiosphereTypes predator, BiosphereTypes victim){
        return unmodifiableChancesToEat.get(predator).get(victim);
    }
}
