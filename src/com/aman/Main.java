package com.aman;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String myLocations= """
                lake, on the edge,E:ocean,W:forest,S:cliff
                ocean,Wonderful view,E:lake ,W:Well
                """;



        AdventureGame game = new AdventureGame(myLocations);
        game.play("lake");

        Scanner scanner=new Scanner(System.in);
        while (true){
            String direction=scanner.nextLine().trim().toUpperCase().substring(0,1);
            if(direction.equals("Q")) break;
            game.move(direction);        }


    }
}
