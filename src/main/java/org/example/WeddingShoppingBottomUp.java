package org.example;

import java.util.Scanner;

public class WeddingShoppingBottomUp {

    static final int MAX_gm = 30;
    static final int MAX_M = 210;
    static int[][] price = new int[MAX_gm][MAX_gm];
    static boolean[][] reachable = new boolean[MAX_gm][MAX_M];

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int TC = scanner.nextInt();
        while (TC-- > 0) {
            int M = scanner.nextInt();
            int C = scanner.nextInt();
            for (int g = 0; g < C; ++g) {
                price[g][0] = scanner.nextInt();
                for (int k = 1; k <= price[g][0]; ++k)
                    price[g][k] = scanner.nextInt();
            }
            for (boolean[] row : reachable)
                java.util.Arrays.fill(row, false);
            for (int k = 1; k <= price[0][0]; ++k)
                if (M - price[0][k] >= 0)
                    reachable[0][M - price[0][k]] = true;
            int money;
            for (int g = 1; g < C; ++g)
                for (money = 0; money < M; money++)
                    if (reachable[g - 1][money])
                        for (int k = 1; k <= price[g][0]; ++k)
                            if (money - price[g][k] >= 0)
                                reachable[g][money - price[g][k]] = true;
            for (money = 0; money <= M && !reachable[C - 1][money]; ++money){

            };
            if (money == M + 1)
                System.out.println("no solution");
            else
                System.out.println(M - money);
        }
    }
}
