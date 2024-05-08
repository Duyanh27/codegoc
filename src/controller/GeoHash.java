package controller;

import model.map.Map2D;
import model.services.*;
import model.util.ArrayList;
import model.util.HashMap;
import model.util.System;

public class GeoHash {
    private static final String BASE32 = "0123456789bcdefghjkmnpqrstuvwxyz";
    private final int precision;
    private final HashMap<String, ATM> dataBaseATM;
    private final HashMap<String, CoffeShop> dataBaseCoffeeShop;
    private final HashMap<String, GasStation> dataBaseGasStation;
    private final HashMap<String, Hospital> dataBaseHospital;
    private final HashMap<String, Restaurant> dataBaseRestaurant;

    private Map2D center;
    private String centerGeoHash;


    public GeoHash() {
        precision = 0;
        dataBaseGasStation = new HashMap<String, GasStation>();
        dataBaseATM = new HashMap<String, ATM>();
        dataBaseCoffeeShop = new HashMap<String, CoffeShop>();
        dataBaseHospital = new HashMap<String, Hospital>();
        dataBaseRestaurant = new HashMap<String, Restaurant>();
    }

    /*@brief this function to set the center of the given val*/
    public void setCenter(Map2D val) {
        center = val;
        centerGeoHash = encode(center);
    }

    /*Add method */
    public void addCoffeeShop(CoffeShop tmp) {
        dataBaseCoffeeShop.put(encode(tmp.getCoordinate()), tmp);
    }

    public void addATM(ATM tmp) {
        dataBaseATM.put(encode(tmp.getCoordinate()), tmp);
    }

    public void addGasStation(GasStation tmp) {
        dataBaseGasStation.put(encode(tmp.getCoordinate()), tmp);
    }

    public void addHospital(Hospital tmp) {
        dataBaseHospital.put(encode(tmp.getCoordinate()), tmp);
    }

    public void addRestaurant(Restaurant tmp) {
        dataBaseRestaurant.put(encode(tmp.getCoordinate()), tmp);
    }

    public String encode(Map2D point) {
        boolean isEven = true;
        int bit = 0;
        int ch = 0;
        StringBuilder geohash = new StringBuilder();

        double[] latInterval = {-90.0, 90.0};
        double[] lonInterval = {-180.0, 180.0};

        while (geohash.length() < this.precision) {
            double mid;
            if (isEven) {
                mid = (lonInterval[0] + lonInterval[1]) / 2;
                if (point.getLongtitude() > mid) {
                    ch |= (1 << (4 - bit));
                    lonInterval[0] = mid;
                } else {
                    lonInterval[1] = mid;
                }
            } else {
                mid = (latInterval[0] + latInterval[1]) / 2;
                if (point.getLatitude() > mid) {
                    ch |= (1 << (4 - bit));
                    latInterval[0] = mid;
                } else {
                    latInterval[1] = mid;
                }
            }

            isEven = !isEven;

            if (bit < 4) {
                bit++;
            } else {
                geohash.append(BASE32.charAt(ch));
                bit = 0;
                ch = 0;
            }
        }
        return geohash.toString();
    }

    //------------ Find POI function ------------ //
    public HashMap<Integer, ATM> findATMs() {
        return new System().findNearestPoint(centerGeoHash, dataBaseATM, 10);
    }

    public HashMap<Integer, CoffeShop> findCoffeeShops() {
        return new System().findNearestPoint(centerGeoHash, dataBaseCoffeeShop, 10);
    }

    public HashMap<Integer, GasStation> findGasStation() {
        return new System().findNearestPoint(centerGeoHash, dataBaseGasStation, 10);
    }

    public HashMap<Integer, Hospital> findHospital() {
        return new System().findNearestPoint(centerGeoHash, dataBaseHospital, 10);
    }

    public HashMap<Integer, Restaurant> findRestaurant() {
        return new System().findNearestPoint(centerGeoHash, dataBaseRestaurant, 10);
    }

    public<T extends Service> void printPlaces(HashMap<Integer, T> data) {
        new System().printAllData(data);
    }
}