package model.map;

import model.Place;
import model.services.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class Map2D {
    private int longtitude;
    private int latitude;

    public Map2D() {
        longtitude = 0;
        latitude = 0;
    }

    public Map2D(int x, int y) {
        this.longtitude = x;
        this.latitude = y;
    }

    public void setLongtitude(int x) {
        this.longtitude = x;
    }

    public void setLatitude(int y) {
        this.latitude = y;
    }
    public int getLongtitude() {
        return this.longtitude;
    }

    public int getLatitude() {
        return this.latitude;
    }
}
