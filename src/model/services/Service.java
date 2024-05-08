package model.services;

import model.map.Map2D;

public class Service {
    private final Map2D coordinate;
    private String name;

    public Service() {
        coordinate = new Map2D();
        name = "";
    }

    public Service(Map2D pos, String label) {
        coordinate = pos;
        name = label;
    }

    public Map2D getCoordinate() {
        return coordinate;
    }

    public String getName() {
        return name;
    }
}