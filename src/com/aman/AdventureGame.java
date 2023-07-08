package com.aman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

//using Map and HashMap
public class AdventureGame {
    private static final String GAME_LOCATIONS = """
            road, at the end of the road,W: well house ,S:valley ,N:forest 
            hill ,on top of the hill with a view to all directions ,N:forest ,E:road 
            well, house inside a well house for a small spring ,W:road,N:Lake ,S:stream 
            valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:Stream
                        
            """;

    private enum Compass {
        E, N, S, W;

        private static final String[] direction = {
                "East", "North", "South", "West"
        };

        public String getString() {
            return direction[this.ordinal()];
        }


    }

    private record Location(String description, Map<Compass, String> nextPlaces) {
    }

    private String lastPlace;

    private Map<String, Location> adventureMap = new HashMap<>();

    public AdventureGame() {
        this(null);

    }

    public AdventureGame(String customLocation) {
        loadLocation(GAME_LOCATIONS);
        if (customLocation != null) {
            loadLocation(customLocation);//Custom location
        }
    }

    private void loadLocation(String data) {
        for (String s : data.split("\\R")) {
            String[] parts = s.split(",", 3);
            Arrays.asList(parts).replaceAll(String::trim);
            Map<Compass, String> nextPlaces = loadDirection(parts[2]);
            Location location = new Location(parts[1], nextPlaces);
            adventureMap.put(parts[0], location);

        }
        //adventureMap.forEach((k, v) -> System.out.printf("%s:%s%n", k, v));
    }

    private Map<Compass, String> loadDirection(String nextPlaces) {
        Map<Compass, String> directions = new HashMap<>();
        List<String> nextSteps = Arrays.asList(nextPlaces.split(","));
        nextSteps.replaceAll(String::trim);
        for (String nextPlace : nextSteps) {
            String[] splits = nextPlace.split(":");
            Compass compass = Compass.valueOf(splits[0].trim());
            String destination = splits[1].trim();
            directions.put(compass, destination);
        }
        return directions;


    }

    private void visit(Location location) {
        System.out.printf("*** You are standing %s ***%n", location.description);
        System.out.println("From Here you can see:");
        location.nextPlaces.forEach((k, v) -> {
            System.out.printf("\t - A %s to the %s (%S) %n", v, k.getString(), k);
        });
        System.out.println("Select your compass (Q to quit) >> ");
    }


    public void move(String direction) {
        var nextPlaces = adventureMap.get(lastPlace).nextPlaces;//next places map from the last place the player was
        String nextPlace = null;
        if ("ENSW".contains(direction)) {
            nextPlace = nextPlaces.get(Compass.valueOf(direction));
            if (nextPlace != null) {
                play(nextPlace);
            }
        } else {
            System.out.println("!! Invalid direction , try again");
        }
    }

    public void play(String location) {
        if (adventureMap.containsKey(location)) {
             Location next=adventureMap.get(location);
             lastPlace=location;
             visit(next);
        } else {
            System.out.println(location + " Is not valid location");
        }


    }


}
