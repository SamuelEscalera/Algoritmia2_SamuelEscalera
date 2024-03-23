package org.example.AudiesParty;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents a utility for finding a maximal guest list in a weighted graph.
 * The maximal guest list is determined by the following criteria:
 * - All guests in the list get along with each other with a weight greater than a specified threshold.
 * - All guests in the list are connected.
 * - The list is as large as possible.
 * - If multiple solutions exist, the one with the greatest sum of friendship weights is chosen.
 * If there are still ties, any one of them may be chosen.
 *
 * @author Samuel Escalera
 */
public class GuestList {


    private final Graph<String, DefaultWeightedEdge> graph;

    /**
     * Constructs a new GuestList utility with the given graph.
     *
     * @param graph The graph representing relationships between guests.
     */
    public GuestList(Graph<String, DefaultWeightedEdge> graph) {
        this.graph = graph;
    }

    /**
     * Finds a maximal guest list where all guests get along with each other with a weight greater than the specified threshold.
     *
     * @param x The threshold value for friendship weights.
     * @return The maximal guest list meeting the specified criteria.
     */
    public List<String> findMaximalGuestList(double x) {
        List<String> maximalGuestList = new ArrayList<>();
        Set<String> guestsAdded = new HashSet<>();

        for (String vertex : graph.vertexSet()) {
            if (!guestsAdded.contains(vertex)) {
                List<String> currentGuestList = new ArrayList<>();
                Set<String> visited = new HashSet<>();
                visitNeighbors(vertex, x, currentGuestList, visited);
                double currentSum = calculateSum(currentGuestList);
                double maximalSum = calculateSum(maximalGuestList);

                if (currentGuestList.size() > maximalGuestList.size() ||
                        (currentGuestList.size() == maximalGuestList.size() && currentSum > maximalSum)) {
                    maximalGuestList = currentGuestList;
                }

                guestsAdded.addAll(currentGuestList);
            }
        }

        return maximalGuestList;
    }

    /**
     * Recursively visits neighbors of a given vertex, adding them to the current guest list if their friendship weight exceeds the threshold.
     *
     * @param vertex          The current vertex being visited.
     * @param x               The threshold value for friendship weights.
     * @param currentGuestList The list of guests being constructed.
     * @param visited         A set to track visited vertices.
     */
    private void visitNeighbors(String vertex, double x, List<String> currentGuestList, Set<String> visited) {
        visited.add(vertex);
        currentGuestList.add(vertex);

        for (String neighbor : Graphs.neighborListOf(graph, vertex)) {
            if (!visited.contains(neighbor) && graph.getEdgeWeight(graph.getEdge(vertex, neighbor)) > x) {
                visitNeighbors(neighbor, x, currentGuestList, visited);
            }
        }
    }

    /**
     * Calculates the sum of friendship weights between guests in the given list.
     *
     * @param guestList The list of guests for which to calculate the sum of friendship weights.
     * @return The sum of friendship weights between the guests in the list.
     */
    private double calculateSum(List<String> guestList) {
        double sum = 0;
        for (String guest : guestList) {
            for (DefaultWeightedEdge edge : graph.edgesOf(guest)) {
                if (guestList.contains(graph.getEdgeTarget(edge))) {
                    sum += graph.getEdgeWeight(edge);
                }
            }
        }
        return sum;
    }
}