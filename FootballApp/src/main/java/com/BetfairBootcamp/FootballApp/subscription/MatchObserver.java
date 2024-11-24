package com.BetfairBootcamp.FootballApp.subscription;

import com.BetfairBootcamp.FootballApp.entities.User;

import java.util.ArrayList;
import java.util.List;

public class MatchObserver {
    private List<User> observers = new ArrayList<>();

    public void addObserver(User user) {
        observers.add(user);
    }

    public void removeObserver(User user) {
        observers.remove(user);
    }

    public void notifyObservers(String message) {
        for (User observer : observers) {
            // Logic to send notifications (e.g., via email or UI updates)
            System.out.println("Notifying " + observer.getName() + ": " + message);
        }
    }
}
