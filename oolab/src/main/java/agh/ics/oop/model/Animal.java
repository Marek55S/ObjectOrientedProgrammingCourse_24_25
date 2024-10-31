package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    public Animal() {
        orientation = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }

    // sprawdz czy da sie lepiej zrobic te 2 konstruktory
    public Animal(MapDirection orientation, Vector2d position){
        this();
        this.orientation = orientation;
        this.position = position;
    }

    @Override
    public String toString(){
        return "orientation=%s, position=%s".formatted(orientation.toString(), position.toString());
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
// zabezpiecz przed wyjsciem za mape
    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation.next();
            case LEFT -> orientation.previous();
            case FORWARD -> position.add(orientation.toUnitVector());
            case BACKWARD -> position.subtract(orientation.toUnitVector());

        }
    }


}
