package agh.ics.oop.model;

import java.util.ArrayList;
import java.util.List;

public class TextMap<T,P extends Number> implements WorldNumberPositionMap<T,P> {
    private final List<T> tMap;

    public TextMap() {
        this.tMap = new ArrayList<>() {
        };
    }

    @Override
    public boolean canMoveTo(P position) {
        int positionInt = position.intValue();
        return positionInt >= 0 && positionInt < tMap.size();
    }

    @Override
    public boolean place(T text) {
        if (!tMap.contains(text)) {
            tMap.add(text);
            return true;
        }
        else return false;
    }

    @Override
    public void move(T text, MoveDirection direction) {
        int startingIndex = tMap.indexOf(text);
        if (direction == MoveDirection.FORWARD || direction == MoveDirection.RIGHT) {
            if (startingIndex < tMap.size()-1) {
                T storage = tMap.get(startingIndex+1);
                tMap.set(startingIndex+1, text);
                tMap.set(startingIndex, storage);
            }
        }
        else if (direction == MoveDirection.BACKWARD || direction == MoveDirection.LEFT) {
            if (startingIndex > 0) {
                T storage = tMap.get(startingIndex-1);
                tMap.set(startingIndex-1, text);
                tMap.set(startingIndex, storage);
            }
        }
    }

    @Override
    public boolean isOccupied(P position) {
        int positionInt = position.intValue();
        return positionInt >= 0 && positionInt < tMap.size();
    }

    @Override
    public T objectAt(P position) {
        if (isOccupied(position)) {
            int positionInt = position.intValue();
            return tMap.get(positionInt);}
        else return null;
    }

    public String toString(){
        return tMap.toString();
    }
}
