package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private final Vector2d upperRightMapCorner = new Vector2d(4,4);
    private final Vector2d lowerLeftMapCorner = new Vector2d(0,0);

    public Animal() {
        orientation = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    public Animal(Vector2d position){
        this();
        this.position = position;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    public MapDirection getOrientation() {
        return this.orientation;
    }

    @Override
    public String toString(){
        return "kierunek: %s, pozycja: %s".formatted(orientation.toString(), position.toString());
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD ->{
                position = position.add(orientation.toUnitVector());
                position = position.lowerLeft(upperRightMapCorner).upperRight(lowerLeftMapCorner);
            }
            case BACKWARD ->{
                position = position.subtract(orientation.toUnitVector());
                position = position.lowerLeft(upperRightMapCorner).upperRight(lowerLeftMapCorner);
            }
        }
    }
}
