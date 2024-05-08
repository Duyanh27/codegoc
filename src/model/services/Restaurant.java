package model.services;

import model.map.Map2D;

public class Restaurant extends Service {
    public Restaurant() {
        super();
    }

    public Restaurant(Map2D pos, String name) {
        super(pos,name);
    }
}
