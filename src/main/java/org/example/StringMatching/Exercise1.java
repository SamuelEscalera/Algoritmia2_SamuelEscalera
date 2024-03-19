package org.example.StringMatching;

public class Exercise1 {
    public static void naiveStringMatcher(String texto, String patron) {
        int n = texto.length();
        int m = patron.length();

        for (int i = 0; i <= n - m; i++) {
            int j;
            for (j = 0; j < m; j++) {
                if (texto.charAt(i + j) != patron.charAt(j)) {
                    break;
                }
            }
            if (j == m) {
                System.out.println("Pattern found at index " + i);
            }
        }
    }

    public static void main(String[] args) {
        String texto = "000010001010001";
        String patron = "0001";

        naiveStringMatcher(texto, patron);
    }

}
