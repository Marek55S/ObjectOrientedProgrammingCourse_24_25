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

    public Animal(MapDirection orientation, Vector2d position){
        this();
        this.position = position;
    }

    @Override
    public String toString(){
        return "orientation=%s, position=%s".formatted(orientation.toString(), position.toString());
    }

    public boolean isAt(Vector2d position){
        return this.position.equals(position);
    }
    public void move(MoveDirection direction) {
        switch (direction) {
            case RIGHT -> orientation = orientation.next();
            case LEFT -> orientation = orientation.previous();
            case FORWARD -> position.lowerLeft(lowerLeftMapCorner);
            case BACKWARD -> position.upperRight(lowerLeftMapCorner);
        }
    }


}
