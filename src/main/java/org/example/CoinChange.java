package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CoinChange {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while ((line = reader.readLine()) != null) {
            int amount = Integer.parseInt(line);
            long[] ways = calculateWays(amount);
            System.out.println(ways[amount]);
        }
        reader.close();
    }

    private static long[] calculateWays(int amount) {
        int[] coins = {50, 25, 10, 5, 1};
        long[] ways = new long[amount + 1];
        ways[0] = 1;

        for (int i = 0; i < coins.length; i++) {
            for (int j = coins[i]; j <= amount; j++) {
                ways[j] += ways[j - coins[i]];
            }
        }
        return ways;
    }
}
