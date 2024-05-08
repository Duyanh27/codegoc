package model;

import java.util.Set;
import model.services.Service;

public class Place {
    private final int x;
    private final int y;
    private Set<Service> services;

    public Place(int x, int y, Set<Service> services) {
        this.x = x;
        this.y = y;
        this.services = services;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }
}
