package org.example.Test2;

import java.util.Arrays;

public class Q4 {

    /*
    Input: N, followed by n numbers that represent the number of presents to be repacked
    The next two lines, separated by space, are given the second line is the repacking time
    of every present by Alice, and the third line is delivering time by Delivery service.
    Output: The shortest possible delivery time for all presents.


    Eg 2
    input2:
    5
    4 4 30 6 2
    5 1 4 30 3

    output2:
    47
    */

    public static int minDeliveryTime(int n, int[] repackTime, int[] deliveryTime) {

        int totalRepackTime = 0;
        int maxDeliveryTime = 0;

        int[][] times = new int[n][2];

        for (int i = 0; i < n; i++) {
            times[i][0] = repackTime[i];
            times[i][1] = deliveryTime[i];
        }

        Arrays.sort(times, (a, b) -> b[1] - a[1]);

        for (int i = 0; i < n; i++) {
            totalRepackTime += times[i][0];
            maxDeliveryTime = Math.max(maxDeliveryTime, totalRepackTime + times[i][1]);
        }

        return maxDeliveryTime;
    }


    public static void main(String[] args) {

        //Caso1(en teroia este caso esta mal en el examen)
            /*int n = 4;
            int[] repackTime = {2, 3, 10, 5};
            int[] deliveryTime = {1, 7, 12, 6};*/

        // Caso2
        int n = 5;
        int[] repackTime = {4, 4, 30, 6, 2};
        int[] deliveryTime = {5, 1, 4, 30, 3};

        // Caso3
            /*int n = 6;
            int[] repackTime = {12, 6, 4, 8, 11, 9};
            int[] deliveryTime = {11, 5, 2, 3, 8, 1};*/

        System.out.println(minDeliveryTime(n, repackTime, deliveryTime));
    }

}
