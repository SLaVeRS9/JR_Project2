package Animals;

public abstract class Animal {
    protected Double weight;
    protected Integer maxCountInCell;
    protected Integer maxMoveDistance;
    protected Double maxFoodToSatiate;

    public abstract void eat ();
    public abstract void multiply();
    public abstract void move();
    public abstract void chooseMove(); //TODO Проверить на сужение доступа в наследниках
    public abstract void die();
}
