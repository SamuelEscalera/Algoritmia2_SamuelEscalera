package org.example.StringMatching;

import java.util.ArrayList;
import java.util.List;

public class Exercise2 {

    public static List<Integer> search(char[] pat, char[] txt) {
        List<Integer> occurrences = new ArrayList<>();

        int prime = 101;
        int d = 256; //(alfabeto)

        int m = pat.length;
        int n = txt.length;

        int p = 0; //(hash pattern)
        int t = 0; //(hash texto)
        int h = 1;

        for (int i = 0; i < m - 1; i++)
            h = (h * d) % prime;

        for (int i = 0; i < m; i++) {
            p = (d * p + pat[i]) % prime;
            t = (d * t + txt[i]) % prime;
        }

        for (int i = 0; i <= n - m; i++) {

            if (p == t) {
                int j;

                for (j = 0; j < m; j++) {
                    if (txt[i + j] != pat[j])
                        break;
                }

                if (j == m)
                    occurrences.add(i);
            }


            if (i < n - m) {
                t = (d * (t - txt[i] * h) + txt[i + m]) % prime;

                if (t < 0)
                    t = (t + prime);
            }
        }

        return occurrences;
    }

    public static void main(String[] args) {
        char[] txt = "AABAACAADAABAABA".toCharArray();
        char[] pat = "AABA".toCharArray();
        List<Integer> occurrences = search(pat, txt);
        if (occurrences.isEmpty()) {
            System.out.println("No se encontraron coincidencias.");
        } else {
            System.out.println("Las ocurrencias del patrón están en las posiciones:");
            for (int index : occurrences) {
                System.out.println(index);
            }
        }
    }
}
