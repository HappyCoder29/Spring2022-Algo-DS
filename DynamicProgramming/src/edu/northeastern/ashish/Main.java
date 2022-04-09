package edu.northeastern.ashish;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        //System.out.println(fibTabulization(400));

//        int[] arr = {-2,-3, 4, -1, -2, 1, 5, -3};
//        System.out.println(kadane(arr));

//        int[] arr =  {10,9,2,5,3,7,101,18};
//        System.out.println(Arrays.toString(longestIncreasingSubSequence(arr)) );

//        int[] arr = {1, 11, 2, 10, 4, 5, 2, 1};
//        System.out.println( longestBitTonicSubSequence(arr));

//        int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};
//        System.out.println(minJumpsToReachEnd(arr));


        int[][] matrix = {
                {4,7,8,6,4},
                {6,7,3,9,2},
                {3,8,1,2,4},
                {7,1,7,3,7},
                {2,9,8,9,3}

        };

        System.out.println(getMinCostMatrix(matrix));


    }

    private static  int fibRecursive(int n){
        if(n < 0){
            return Integer.MIN_VALUE;
        }
        if(n <= 1){
            return n;
        }
        return  fibRecursive(n-1) + fibRecursive(n-2);
    }

    // O(n)
    // Space O(n) we take hashmap of size n
    private static long fibMemoization(int n){
        if(n <= 1){
            return n;
        }
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(0, 0L);
        map.put(1, 1L);
        return fibMemoization(map, n);
    }

    private static long fibMemoization(HashMap<Integer, Long> map, int n){
        if( !map.containsKey(n) ){
            long val = fibMemoization(map, n-1) + fibMemoization(map, n-2);
            map.put(n, val);
        }
        return map.get(n);
    }

    // O(n)
    // Space O(n)
    private static long fibTabulization(int n){
        if(n <= 1){
            return n;
        }

        long[]  arr = new long[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        for(int i = 2; i < arr.length; i ++){
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }


    private static long tribonacciSequence(int n){
        if(n <=1){
            return 0L;
        }
        if(n == 2){
            return 1L;
        }

        return tribonacciSequence(n-1) + tribonacciSequence(n-2)+ tribonacciSequence(n-3);
    }

    private static long tribonacciSequenceTabulization(int n){
        if(n <=1){
            return 0L;
        }
        if(n == 2){
            return 1L;
        }

        long[] arr = new long[n+1];
        arr[0] = 0;
        arr[1] = 0;
        arr[2] = 1;

        for(int i = 3; i < arr.length; i ++){
            arr[i] = arr[i-1] + arr[i-2] + arr[i-3];
        }
        return arr[n];

    }

    // O(n^3)
    private static int kadaneBruteForce(int[] arr){
        int maxSum = arr[0];
        for(int i = 1 ; i < arr.length; i ++){
            for(int j = 0; j < i; j ++){
                int sum = 0;
                for(int k = j; k < i ; k ++){
                    sum += arr[k];
                }
                if(maxSum < sum){
                    maxSum = sum;
                }
            }
        }
        return maxSum;
    }

    private static int kadane(int[] arr){
        int maxSum = arr[0];
        int sum = 0;
        int startIndex = 0 ;
        int endIndex = 0;

        for(int i = 0 ; i< arr.length; i ++){
            sum += arr[i];
            if(maxSum < sum){
                maxSum = sum;
                endIndex = i;
            }
            if(sum <0){
                sum = 0;
                startIndex = i + 1;
            }
        }

        System.out.println("Start index = " + startIndex);
        System.out.println("End index = " + endIndex);

        return maxSum;
    }

    private static int[] longestIncreasingSubSequence(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        if(arr.length == 1){
            return arr;
        }

        int[] lis = new int[arr.length];
        int[] indexes = new int[arr.length];

        for(int i = 0 ; i < arr.length; i ++){
            lis[i] = 1;
            indexes[i] = i;
        }
        int maxIndex = 0;
        int maxValue = 0;

        for(int i = 1; i < arr.length; i ++){
            for(int j = 0; j < i; j ++){
                if(arr[i] > arr[j]){ // There can be a increasing subsequence
                    if(lis[i] < lis[j] + 1){
                        lis[i] = lis[j] + 1; // Update LIS value
                        indexes[i] = j;
                    }
                    if(maxValue < lis[i]){
                        maxValue = lis[i];
                        maxIndex = i;
                    }
                }
            }// end of for loop for j
        }// end of for loop for i

        int[] values = new int[maxValue];
        for(int i = maxValue -1 ; i >=  0; i --){
            values[i] = arr [maxIndex];
            //System.out.println(arr [maxIndex]);
            maxIndex = indexes[maxIndex];
        }

        return  values;
    }


    private static int longestBitTonicSubSequence(int[] arr){

        int[] lis = longestIncreasingSubSequenceValues(arr);
        reverseArray(arr);
        int[] lds = longestIncreasingSubSequenceValues(arr);
        reverseArray(lds);
        reverseArray(arr);

        int maxValue = 0;
        for(int i = 0 ;i < lis.length; i ++){
            if(maxValue < lis[i] + lds[i] -1){
                maxValue = lis[i] + lds[i] -1;
            }
        }

        return maxValue;
    }


    private static void reverseArray(int[] arr){
        if(arr == null || arr.length <= 1){
            return;
        }
        int start = 0;
        int end = arr.length -1;

        while(start < end ){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start ++;
            end--;
        }
    }

    private static int[] longestIncreasingSubSequenceValues(int[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        if(arr.length == 1){
            return arr;
        }

        int[] lis = new int[arr.length];

        for(int i = 0 ; i < arr.length; i ++){
            lis[i] = 1;
        }
        int maxValue = 0;

        for(int i = 1; i < arr.length; i ++){
            for(int j = 0; j < i; j ++){
                if(arr[i] > arr[j]){ // There can be a increasing subsequence
                    if(lis[i] < lis[j] + 1){
                        lis[i] = lis[j] + 1; // Update LIS value
                    }
                    if(maxValue < lis[i]){
                        maxValue = lis[i];
                    }
                }
            }// end of for loop for j
        }// end of for loop for i


        return  lis;
    }

    private static int minJumpsToReachEnd(int[] arr){
        if(arr == null || arr.length <= 1){
            return 1;
        }
        int[] jumps = new int[arr.length];
        Arrays.fill(jumps, Integer.MAX_VALUE);
        jumps[0] = 0;
        int[] indexes = new int[arr.length];
        Arrays.fill(indexes, -1);
        indexes[0] = 0;

        for(int i = 1; i < arr.length; i ++){
            for(int j = 0; j < i ; j ++){
                if( i <= j + arr[j]){ // We can make a jump

                    if( jumps[i] > jumps[j] + 1){
                        jumps[i] = jumps[j] + 1;
                        indexes[i] = j;
                    }
                }
            }
        }

        return jumps[jumps.length -1];

    }

    private static int getMinCostMatrix(int[][] matrix){

        int rows = matrix.length;
        int cols = matrix[0].length;

        int[][] cost = new int[rows][cols];

        int sum = 0;
        for(int i = 0 ; i < rows; i ++){
            sum += matrix[i][0];
            cost[i][0] = sum;
        }

        sum = 0;
        for(int i = 0 ; i < cols; i ++){
            sum += matrix[0][i];
            cost[0][i] += sum;
        }

        for(int i = 1; i < rows; i ++){
            for(int j = 1; j < cols; j ++){
                cost[i][j] = Math.min(cost[i-1][j], cost[i][j-1]) + matrix[i][j];
            }
        }

        int minValue = cost[rows-1][cols-1];
        int row = matrix.length -1;
        int col = matrix[0].length -1;
        Stack<Integer> stack  = new Stack<>();
        while(row != 0 || col != 0 ){
           // System.out.println(matrix[row][col]);
            stack.push(matrix[row][col]);
            if(row == 0 ){
                col --;
                continue;
            }
            if(col == 0 ){
                row --;
                continue;
            }
            if(cost[row][col] - matrix[row][col] == cost[row-1][col]){// we came from above
                row --;
            }
            else {
                col --;
            }
        }

        System.out.printf(matrix[0][0] + " -> ");
        while(!stack.isEmpty()){
            System.out.printf(stack.pop() + " -> ");
        }
        System.out.println("End");


        return minValue;

    }



}
