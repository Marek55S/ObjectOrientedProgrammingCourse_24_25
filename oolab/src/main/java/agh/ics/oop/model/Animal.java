package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position;

    public Animal() {
        orientation = MapDirection.NORTH;
        position = new Vector2d(2,2);
    }



    public Animal(MapDirection orientation, Vector2d position){
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


}
