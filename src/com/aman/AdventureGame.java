package com.aman;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdventureGame {
    private static final String GAME_LOCATIONS= """
            road, at the end of the road,W: well house ,S:valley ,N:forest 
            hill on top of the hill with a view to all directions ,N:forest ,E:road 
            well house,inside a well house for a small spring ,W:road,N:Lake ,S:stream 
            valley,in a forest valley beside a tumbling stream,N:road,W:hill,E:Stream
            
            """;

    private enum Compass{
        E,N,S,W;

        private static final String [] direction={
                "East","North","South","West"
        };

        public String getDirection(){
            return direction[this.ordinal()];
        }
    }

    private record Location(String description, Map<Compass,String> nextPlaces){}

    private String lastPlace;

    private Map<String,Location> adventureMap=new HashMap<>();

    public AdventureGame(){

    }

    public AdventureGame(String customeLocation) {

    }

    private void loadLocation(String data){
        for(String s:data.split("\\R")){
            String[] parts= s.split(",",3);
            Arrays.asList(parts).replaceAll(String::trim);
            Map<Compass,String> nextPlaces=loadDirection(parts[2]);
            Location location=new Location(parts[1],nextPlaces);
            adventureMap.put(parts[0],location );

        }
        adventureMap.forEach((k,v)-> System.out.printf("%s:%s%n",k,v));
    }

    private Map<Compass, String> loadDirection(String nextPlaces) {
        Map<Compass,String> directions=new HashMap<>();
        List<String> nextSteps=Arrays.asList(nextPlaces.split(","));
        nextSteps.replaceAll(String::trim);
        for (String nextPlace:nextSteps){
            String[] splits= nextPlace.split(":");
            Compass compass=Compass.valueOf(splits[0].trim());
            String destination=splits[1].trim();
            directions.put(compass,destination);
        }
        return directions;


    }


}
