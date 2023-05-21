package Biosphere;

import Island.Island;

public abstract class Biosphere {
    protected final BiosphereTypes TYPE;
    protected final Integer MAX_COUNT_IN_CELL;
    protected final Double START_WEIGHT;
    protected final Integer MAX_MULTIPLYING;
    protected Double weight;
    protected Island.Cell.Position currentPosition;

    protected Biosphere (BiosphereTypes type, Integer maxCountInCell, Double startWeight, Integer maxMultiplying) {
        TYPE = type;
        MAX_COUNT_IN_CELL = maxCountInCell;
        START_WEIGHT = startWeight;
        weight = START_WEIGHT;
        MAX_MULTIPLYING = maxMultiplying;
    }

    public synchronized BiosphereTypes getType(){
        return TYPE;
    }

    public Double getWeight(){
        return weight;
    }
    public void setPosition(Island.Cell.Position position){
        this.currentPosition = position;
    }
    public Island.Cell.Position getCurrentPosition(){
        return currentPosition;
    }
    public Integer getMaxCountInCell(){
        return MAX_COUNT_IN_CELL;
    }
    public Integer getMaxMultiplying(){
        return MAX_MULTIPLYING;
    }
    public Island.Cell getCurrentCell() {
        Island.Cell.Position currentPosition = getCurrentPosition();
        return Island.getCell(currentPosition);
    }
}
