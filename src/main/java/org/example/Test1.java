package org.example;

public class Test1 {

    private static int function(int n, int [] dp) {
        if (n == 0) {
             return 0;
             }

         if (n == 1 || n == 2) {
             return 1;
             }

         dp[n] = function(n - 1, dp) + function(n - 2, dp) + function(n - 3, dp);

         return dp[n];
    }

    public static int giftOrder(int n, int[] giftValue){

        int numChanges = 0;

        for(int i = 1; i < giftValue.length; i++){
            for (int j = 0; j < giftValue.length; j++){
                if(giftValue[j] > giftValue[i]){
                    int temp = giftValue[i];
                    giftValue[i] = giftValue[j];
                    giftValue[j] = temp;
                    numChanges++;
                }
            }
        }
        return numChanges;
    }

    public static void main(String[] args)
    {
        int n = 5;
        int[] gifts = new int[n];
        gifts[0] = 30;
        gifts[1] = 21;
        gifts[2] = 15;
        gifts[3] = 12;
        gifts[4] = 4;
        int resul = giftOrder(n,gifts);
        System.out.println(resul);
    }


}
