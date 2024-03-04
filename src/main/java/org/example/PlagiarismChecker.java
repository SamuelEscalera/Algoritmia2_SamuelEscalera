package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase representa una solución para identificar la similitud entre dos textos y encontrar palabras mal escritas.
 * Utiliza el algoritmo de Levenshtein para calcular la distancia de edición entre las cadenas de texto.
 */

public class PlagiarismChecker {

    /**
     * Calcula la distancia de Levenshtein entre dos cadenas de texto.
     *
     * @param s1 La primera cadena de texto.
     * @param s2 La segunda cadena de texto.
     * @return La distancia de Levenshtein entre las dos cadenas.
     */

    public static int levenshteinDistance(String s1, String s2) {
        int m = s1.length();
        int n = s2.length();

        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                dp[i][j] = Math.min(Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1), dp[i - 1][j - 1] + cost);
            }
        }

        return dp[m][n];
    }


    /**
     * Calcula el porcentaje de similitud entre dos textos basado en la distancia de Levenshtein.
     *
     * @param text1 El primer texto.
     * @param text2 El segundo texto.
     * @return El porcentaje de similitud entre los textos.
     */

    public static double calculateSimilarity(String text1, String text2) {
        int distance = levenshteinDistance(text1, text2);
        return 100.0 - ((double) distance / (double) text1.length()) * 100.0;
    }

    /**
     * Encuentra y muestra las palabras mal escritas en el segundo texto.
     *
     * @param text1 El primer texto.
     * @param text2 El segundo texto.
     */

    public static void findMisspelledWords(String text1, String text2) {
        String[] words1 = text1.split("\\s+");
        String[] words2 = text2.split("\\s+");

        List<String> misspelledWords = new ArrayList<>();

        for (String word2 : words2) {
            boolean found = false;
            for (String word1 : words1) {
                double similarity = calculateSimilarity(word1, word2);
                if (similarity >= 95.0) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                misspelledWords.add(word2);
            }
        }

        System.out.println("Palabras mal escritas:");
        for (String word : misspelledWords) {
            System.out.println(word);
        }
    }

    public static void main(String[] args) {

        String file1 = "1.txt";
        String file2 = "2.txt";

        String text1 = readFile(file1);
        String text2 = readFile(file2);

        //Eg 1
        //String text1 = "This text should show what a printed text will look like at this place. If you read this text you will get no information.";
        // String text2 = "This paragraph should show what a printd text will look like at this place. If you read this text you will get no informaton.";

        // Eg 2
        //  String text1 = "Richard Bellman is best known as the father of dynamic programming. He was the author of many books and the recipient of many honors including the first Norbert Wiener Prize in Applied Mathematics.";
        // String text2 = "Richard Bellman was the author of many books in matematicas.";
        double similarity = calculateSimilarity(text1, text2);
        System.out.println("Porcentaje de similitud: " + similarity + "%");

        findMisspelledWords(text1, text2);
    }

    /**
     * Lee el contenido de un archivo de texto y lo devuelve como una cadena.
     *
     * @param filename El nombre del archivo a leer.
     * @return El contenido del archivo como una cadena.
     */

    public static String readFile(String filename) {
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
}