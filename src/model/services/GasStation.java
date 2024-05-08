package model.services;

import model.map.Map2D;

public class GasStation extends Service{
    public GasStation() {
        super();
    }

    public GasStation(Map2D pos,String name) {
        super(pos,name);
    }
}
