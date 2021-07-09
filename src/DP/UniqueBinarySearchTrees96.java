package DP;

public class UniqueBinarySearchTrees96 {

    // https://github.com/wisdompeak/LeetCode/tree/master/Dynamic_Programming/096.Unique-Binary-Search-Trees
    // This question has many variation;

    // Top Down;


    int[] dp = new int[20];
    public int numTrees(int n) {

        // transition function;
        // if nodes = k as root;
        // we will have the type of f(k-1) * f(n-k);

        if (n == 0) {return 1;}
        if (dp[n] != 0) {return dp[n];}
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += numTrees(i-1) * numTrees(n-i);
        }
        dp[n] = sum;
        return sum;
    }


    // Bottom up;

    public int numTrees2(int n) {

        // transition function;
        // if nodes = k as root;
        // we will have the type of f(k-1) * f(n-k);
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= i; k++) {
                dp[i] += dp[k - 1] * dp[i - k];
            }

        }
        return dp[n];
    }
}
