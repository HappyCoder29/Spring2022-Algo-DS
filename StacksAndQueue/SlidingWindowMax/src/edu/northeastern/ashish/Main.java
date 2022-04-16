package edu.northeastern.ashish;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Integer[] arr = {1,3,-1,-3,5,3,6,7};

        int[] sliding =  slidingWindowMax(arr, 3);
        System.out.println(Arrays.toString(sliding));
    }


    private static int[]  slidingWindowMax(Integer[] arr, int windowSize){
        if(windowSize >= arr.length){
            int[] returnArr = new int[1];
            returnArr[0] = getMaxOfAnArray(arr);
            return returnArr;
        }
        int[] maxSliding = new int[arr.length - windowSize + 1];

        int maxValue = Integer.MIN_VALUE;
        int max = getMinValue(arr, 0, windowSize -1);
        maxSliding[0] = max;
        for(int i = 1  ; i < arr.length - windowSize +1; i ++){
            max = getMinValue(arr, i, i + windowSize);
            maxSliding[i] = max;
        }
        return maxSliding;
    }

    private static int getMinValue(Integer[] arr, int start, int end){
        int max = arr[start];
        for(int i = start; i < end; i ++){
            if(max < arr[i]){
                max = arr[i];
            }
        }
        return max;
    }


    private static int getMaxOfAnArray(Integer[] arr){
        List<Integer> list = (List<Integer>) Arrays.asList(arr);
        Integer max = list.stream().reduce(0, (a, b) -> Math.max(a, b));
        return  max;
    }

}
