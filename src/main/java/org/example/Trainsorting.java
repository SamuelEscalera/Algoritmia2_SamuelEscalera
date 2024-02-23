package org.example;

import java.util.Arrays;
import java.util.Scanner;

public class Trainsorting {
    static int size;
    static int[] arr;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNumber = scanner.nextInt();
        StringBuilder stringBuilder = new StringBuilder();

        while (caseNumber-- > 0) {
            while (!scanner.hasNextInt()) {
                scanner.next();
            }
            size = scanner.nextInt();
            arr = new int[size];
            for (int i = 0; i < arr.length; i++) {
                arr[i] = scanner.nextInt();
            }

            int[] LIS = new int[size];
            calcularLIS(LIS);

            int maxLIS = 0;
            for (int i = 0; i < size; i++) {
                maxLIS = Math.max(maxLIS, LIS[i]);
            }

            stringBuilder.append(maxLIS + "\n");
        }

        System.out.print(stringBuilder);
    }

    static void calcularLIS(int[] lis) {
        Arrays.fill(lis, 1);
        for (int i = size - 1; i >= 0; i--) {
            int max = 0;
            for (int j = i + 1; j < size; j++) {
                if (arr[j] > arr[i]) {
                    max = Math.max(max, lis[j]);
                }
            }
            lis[i] += max;
        }
    }
}
