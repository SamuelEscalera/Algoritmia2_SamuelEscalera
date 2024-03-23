package org.example.AudiesParty;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.*;

public class GroupFormation {

    private final Graph<String, DefaultWeightedEdge> graph;
    private final List<String> guestList;

    public GroupFormation(Graph<String, DefaultWeightedEdge> graph, List<String> guestList) {
        this.graph = graph;
        this.guestList = guestList;
    }

    public void formGroups(int k) {
        List<List<String>> groups = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            groups.add(new ArrayList<>());
        }


        List<String> centroids = new ArrayList<>(guestList.subList(0, k));


        for (String guest : guestList) {
            double minDistance = Double.MAX_VALUE;
            int nearestGroup = -1;

            for (int i = 0; i < k; i++) {
                double distance = calculateDistance(guest, centroids.get(i));
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestGroup = i;
                }
            }

            groups.get(nearestGroup).add(guest);
        }


        for (int i = 0; i < k; i++) {
            centroids.set(i, findStrongestRelation(groups.get(i)));
        }


        for (int i = 0; i < k; i++) {
            System.out.println("Group " + (i + 1) + ": " + groups.get(i));
        }


        String strongestGroup = findStrongestGroup(groups);
        System.out.println("Group with strongest relation: " + strongestGroup);


        String weakestGroup = findWeakestGroup(groups);
        System.out.println("Group with weakest relation: " + weakestGroup);
    }

    private double calculateDistance(String guest, String centroid) {
        double distance = 0;
        for (String neighbor : Graphs.neighborListOf(graph, guest)) {
            if (!centroid.equals(neighbor)) {
                distance += graph.getEdgeWeight(graph.getEdge(guest, neighbor));
            }
        }
        return distance;
    }

    private String findStrongestRelation(List<String> group) {
        double maxWeight = Double.MIN_VALUE;
        String strongestRelation = null;
        for (String guest : group) {
            for (String neighbor : Graphs.neighborListOf(graph, guest)) {
                double weight = graph.getEdgeWeight(graph.getEdge(guest, neighbor));
                if (weight > maxWeight && !group.contains(neighbor)) {
                    maxWeight = weight;
                    strongestRelation = neighbor;
                }
            }
        }
        return strongestRelation;
    }

    private String findStrongestGroup(List<List<String>> groups) {
        double maxWeightSum = Double.MIN_VALUE;
        String strongestGroup = null;
        for (List<String> group : groups) {
            double weightSum = calculateGroupWeightSum(group);
            if (weightSum > maxWeightSum) {
                maxWeightSum = weightSum;
                strongestGroup = group.toString();
            }
        }
        return strongestGroup;
    }

    private String findWeakestGroup(List<List<String>> groups) {
        double minWeightSum = Double.MAX_VALUE;
        String weakestGroup = null;
        for (List<String> group : groups) {
            double weightSum = calculateGroupWeightSum(group);
            if (weightSum < minWeightSum) {
                minWeightSum = weightSum;
                weakestGroup = group.toString();
            }
        }
        return weakestGroup;
    }

    private double calculateGroupWeightSum(List<String> group) {
        double sum = 0;
        for (String guest : group) {
            for (String neighbor : Graphs.neighborListOf(graph, guest)) {
                if (group.contains(neighbor)) {
                    sum += graph.getEdgeWeight(graph.getEdge(guest, neighbor));
                }
            }
        }
        return sum;
    }
}
