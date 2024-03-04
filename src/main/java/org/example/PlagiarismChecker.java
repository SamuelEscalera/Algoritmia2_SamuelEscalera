package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlagiarismChecker {

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


    public static double calculateSimilarity(String text1, String text2) {
        int distance = levenshteinDistance(text1, text2);
        int maxLength = Math.max(text1.length(), text2.length());
        return 100.0 - (distance / (double) maxLength) * 100.0;
    }


    public static void findMisspelledWords(String text1, String text2) {
        String[] words1 = text1.split("\\s+");
        String[] words2 = text2.split("\\s+");

        List<String> misspelledWords = new ArrayList<>();

        for (String word2 : words2) {
            boolean found = false;
            for (String word1 : words1) {
                double similarity = calculateSimilarity(word1, word2);
                if (similarity >= 55.0) {
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

        double similarity = calculateSimilarity(text1, text2);
        System.out.println("Porcentaje de similitud: " + similarity + "%");

        findMisspelledWords(text1, text2);
    }


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