package agh.ics.oop.model;

public class GrassField implements WorldMap{
    private static final Vector2d LOWER_LEFT = new Vector2d(0,0);
    private final int grassNumber;
    // moze lower left nie bedzie final, zalezy co bedzie dalej

    public GrassField(int grassNumber) {
        this.grassNumber = grassNumber;
    }

    private void placeGrass(){

    }
}
