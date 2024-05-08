package model.services;

import model.map.Map2D;

public class Hospital extends Service{
    public Hospital() {
        super();
    }

    public Hospital(Map2D pos, String name) {
        super(pos,name);
    }
}
