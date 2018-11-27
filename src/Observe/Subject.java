package Observe;

import java.util.ArrayList;
import java.util.List;


public class Subject {
    private List<MyObserver> observers = new ArrayList<>();

    public void addObserver(MyObserver observer){
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    public void notifyObservers(MyObserver observer){
        for (MyObserver o : observers){
            o.update(observer);

        }
    }
}
