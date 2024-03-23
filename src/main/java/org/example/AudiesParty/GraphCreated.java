package org.example.AudiesParty;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.*;
import java.io.*;


public class GraphCreated {

    public static void main(String[] args) {
        GraphCreated graph = new GraphCreated();
        graph.createGraph("C:\\Users\\Samuel Escalera\\Documents\\Algoritmia2_SamuelEscalera\\src\\main\\java\\org\\example\\AudiesParty\\input.txt");
    }

    public void createGraph(String filePath){

        Graph<String, DefaultWeightedEdge> graph = buildGraphFromFile(filePath);

        System.out.println("Grafo dirigido ponderado:");
        System.out.println(graph);
    }
    private static Graph<String, DefaultWeightedEdge> buildGraphFromFile(String filePath) {
        Graph<String, DefaultWeightedEdge> graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean verticesDone = false;

            while ((line = br.readLine()) != null) {
                if (!line.isEmpty()) {
                    String[] parts = line.split(" ");
                    if (parts.length > 1) {
                        verticesDone = true;
                    }
                    if (!verticesDone) {
                        // Agregar vértices al grafo
                        for (String part : parts) {
                            graph.addVertex(part);
                        }
                    } else {
                        // Agregar conexiones ponderadas al grafo
                        String sourceVertex = parts[0];
                        String targetVertex = parts[1];
                        double weight = Double.parseDouble(parts[2]);
                        Graphs.addEdgeWithVertices(graph, sourceVertex, targetVertex, weight);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return graph;
    }
}
