package org.example.AudiesParty;

import org.jgrapht.Graph;
import org.jgrapht.graph.DefaultUndirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class is used to create and manipulate undirected weighted networks.
 *
 * @author Samuel Escalera
 */
public class GraphCreated {

    /**
     * Creates an undirected weighted network from a file.
     *
     * @param filePath The path to the file containing the network information.
     */
    public Graph<String, DefaultWeightedEdge> createGraph(String filePath) {

        Graph<String, DefaultWeightedEdge> graph = buildGraphFromFile(filePath);

        System.out.println("Graph:");
        System.out.println("Vertex: " + graph.vertexSet());
        System.out.println("Edge: " + graph.edgeSet());
        System.out.println("Weight edge:");
        for (DefaultWeightedEdge edge : graph.edgeSet()) {
            String sourceVertex = graph.getEdgeSource(edge);
            String targetVertex = graph.getEdgeTarget(edge);
            double weight = graph.getEdgeWeight(edge);
            System.out.println("(" + sourceVertex + " - " + targetVertex + ") : " + weight);
        }

        return graph;
    }

    /**
     * Constructs a weighted undirected graph from a file.
     *
     * @param filePath The path to the file containing the graph information.
     * @return The weighted undirected graph constructed from the file.
     */
    private static Graph<String, DefaultWeightedEdge> buildGraphFromFile(String filePath) {
        Graph<String, DefaultWeightedEdge> graph = new DefaultUndirectedWeightedGraph<>(DefaultWeightedEdge.class);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean verticesDone = false;

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(" ");
                    if (parts.length > 1) {
                        verticesDone = true;
                    }
                    if (!verticesDone) {

                        for (String part : parts) {
                            graph.addVertex(part);
                        }
                    } else {

                        String sourceVertex = parts[0];
                        String targetVertex = parts[1];
                        double weight = Double.parseDouble(parts[2]);
                        graph.addEdge(sourceVertex, targetVertex);
                        graph.setEdgeWeight(graph.getEdge(sourceVertex, targetVertex), weight);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
