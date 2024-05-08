package model.util;
import model.map.Map2D;
import model.services.Service;
import org.jetbrains.annotations.NotNull;

import java.io.FileWriter;
import java.io.IOException;

public class System {

    public int findMin(int x, int y) {
        if(x < y) {
            return x;
        } else {
            return y;
        }
    }

    /*@brief: find the most compatible between 2 geohash */
    public int compare(@NotNull String destination, @NotNull String src) {
        if (destination == null || src == null) {
            throw new IllegalArgumentException("Neither 'destination' nor 'src' can be null.");
        }

        int minLength = Math.min(destination.length(), src.length());
        int differences = 0;

        // Compare each character in both strings up to the minimum length
        for (int i = 0; i < minLength; i++) {
            if (destination.charAt(i) != src.charAt(i)) {
                differences++;
            }
        }

        // Add the difference of the remaining characters, if the lengths differ
        differences += Math.abs(destination.length() - src.length());

        return differences;
    }

    public int toInt(String x) {
        return Integer.parseInt(x);
    }

    public<T extends Service> HashMap<Integer, T> findNearestPoint(@NotNull String center, @NotNull HashMap<String, T> list, int number) {
        HashMap<Integer, T> topval = new HashMap<>();
        java.lang.System.out.println(list.size());
        for(HashMap.Entry<String, T> entry : list.entryList()) {
            int distance = compare(center, entry.key);
            topval.put(distance, entry.value);
        }

        java.lang.System.out.println(topval.size());
//        int i = 0;
//        for(HashMap.Entry<Integer, T> entry : topval.entryList()) {
//            java.lang.System.out.println(topval.get(i).getName());
//            i++;
//        }

        // continute sort here
        sortByKey(topval);
        HashMap<Integer, T> returnVal = new HashMap<Integer, T>();
        int counter = 0;
        while(counter < number) {
            returnVal.put(topval.entryList().get(counter).key, topval.entryList().get(counter).value);
            counter++;
        } // return the number of poi around the desired number

        return returnVal;
    }

    public<T extends Service> void swap(@NotNull ArrayList<HashMap.Entry<Integer, T>> list, int i, int j) {
        HashMap.Entry<Integer, T> temp = list.get(i);
        list.insertAt(i, list.get(j));
        list.insertAt(j, temp);
    }

    public<T extends Service> void sortByKey(@NotNull HashMap<Integer, T> tmp) {
        int i = 0;
        int min = tmp.entryList().get(0).key; // the key integer of first element in the map is assumed min
        for(HashMap.Entry<Integer, T> s : tmp.entryList()) {
            if(i == tmp.size() - 1) {
                break;
            }
            if(tmp.entryList().get(i).key >= tmp.entryList().get(i +1).key) {
                // swap
                swap(tmp.entryList(),i, i+1);
            }
        }
    }

    public<T extends Service> void printAllData(@NotNull HashMap<Integer, T> data) {
        java.lang.System.out.printf("%-20s%n", "Name");
        java.lang.System.out.println("-----------------------");
        for (HashMap.Entry<Integer, T> entry : data.entryList()) {
            T service = entry.value;
            java.lang.System.out.printf("%-20s%n", service.getName());
        }
    }
}