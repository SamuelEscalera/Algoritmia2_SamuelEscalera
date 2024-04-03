package org.example.ChocolateBar;

public class ChocolateBar {

    private static int chocolateBar(int n) {
        int cnt = 1;
        int[][] dp = new int[n + 1][2];
        initializeDp(dp);
        fillDp(dp, n, cnt);
        return dp[n][0];
    }
    private static void updateDp(int[][] dp, int i, int cnt) {
        if (isOverlap(dp, i)) {
            dp[i][0] = dp[i - 1][0] + 1;
            dp[i][1] = dp[i - 1][1] + cnt;
        } else {
            dp[i][0] = dp[i - 1][0];
            dp[i][1] = dp[i - 1][1];
        }
    }
    private static void initializeDp(int[][] dp) {
        dp[1][0] = 1;
        dp[1][1] = 1;
    }
    private static void fillDp(int[][] dp, int n, int cnt) {
        for (int i = 2; i <= n; i++) {
            if (isOverlap(dp, i)) {
                cnt++;
            }
            updateDp(dp, i, cnt);
        }
    }
    private static boolean isOverlap(int[][] dp, int i) {
        return i - dp[i - 1][0] > dp[i - 1][1];
    }

    public static void main(String[] args) {
        int n1 = 8;
        int n2 = 12;
        System.out.println("n = " + n1 + ", max: " + chocolateBar(n1));
        System.out.println("n = " + n2 + ", max: " + chocolateBar(n2));
    }
}
