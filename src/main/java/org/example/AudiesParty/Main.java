package org.example.AudiesParty;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GraphCreated graphCreated = new GraphCreated();
        Graph<String, DefaultWeightedEdge> graph = graphCreated.createGraph("C:\\Users\\Samuel Escalera\\Documents\\Algoritmia2_SamuelEscalera\\src\\main\\java\\org\\example\\AudiesParty\\input.txt");

        GuestList guestList = new GuestList(graph);

        System.out.print("x = ");
        double x = scanner.nextDouble();
        List<String> guestListString = guestList.findMaximalGuestList(x);

        System.out.println("Gust list:");
        System.out.println(guestListString.toString());
    }
}
