public abstract class Animal {
    private Double weight;
    private Integer maxCountInCell;
    private Integer maxMoveDistance;
    private Double maxFoodToSatiate;

    public abstract void eat ();
    public abstract void multiply();
    public abstract void move();
    public abstract void chooseMove(); //TODO Проверить, можно ли в наследниках изменить (сузить) область видимости
}
