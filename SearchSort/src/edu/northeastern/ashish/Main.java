package edu.northeastern.ashish;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {

//        int[] arr = {6,5,3,1,8,7,2,4};
//        findkThSmallest(arr, 3);
//        int[] count = {2,2,1,1,1,3,0,0,1,8,8,3,4,6};
//        countSort(count, 10);
//        System.out.println(Arrays.toString(count));
//       // System.out.println(Arrays.toString(arr) );
//
//        int[] dutch = {01,1,1,2,2,0,1,2,0,1,1};
//        dutchFlag(dutch, 1);
//        System.out.println(Arrays.toString(dutch));

//        int[] arr = {1,1,1,0,0,1,2,2,2,3,1,1,1};
//        System.out.println(majorityElement(arr));

        int[] arr1 = {1,3,5,7};
        int[] arr2 = {2,4,6,8};
        System.out.println(Arrays.toString( mergeSortedArrays(arr1, arr2)) );

    }

    private static void swap(int[] arr, int i , int j){
        if(arr == null || i < 0 || j < 0 || i >= arr.length || j >= arr.length || i == j){
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // O(n^2)
    private static void bubbleSort(int[] arr ){
        for(int i = 0 ; i < arr.length; i ++){
            for(int j = 0 ; j < arr.length -1; j ++){
                if(arr[j] > arr[i] ){
                    swap(arr, i, j);
                }
            }
        }
    }

    // O(n^2)
    private static void selectionSort(int[] arr){
        for(int i = 0 ; i < arr.length -1; i ++){
            int minIndex = i;
            for(int j = i + 1 ; j < arr.length ; j ++){
                if(arr[minIndex] > arr[j]){
                    minIndex = j;
                }
            }
            if( minIndex != i){
                swap(arr, minIndex, i);
            }
        }
    }

    // O( Log n * n) = O(nLogn)

    private static void mergeSort(int[] arr){
        mergeSort(arr, 0, arr.length -1);
    }


    private static void mergeSort(int[] arr, int low, int high){

        if(low >= high){
            return;
        }

        // O(log(n) )
        int mid = (low + high)/2;
        mergeSort(arr, low, mid);
        mergeSort(arr, mid+1, high);

        merge(arr, low, high);

    }

    // O(n)
    private static void merge(int[] arr, int low, int high){
        int mid = (low + high)/2;
        int[] temp = new int[high -low +1];
        int i = low;
        int j = mid +1;
        int index = 0;

        // O(n)
        while( i <= mid && j <= high){
            if(arr[i] < arr[j]){
                temp[index++] = arr[i];
                i ++;
            }else {
                temp[index++] = arr[j];
                j++;
            }
        }
        //O(n)
        while(i <= mid){
            temp[index++] = arr[i++];
        }
        //O(n)
        while(j <= high){
            temp[index++] = arr[j++];
        }

        i = low;
        for(int value : temp){
            arr[i++] = value;
        }
    }

    private static void quickSort(int[] arr){
        quickSort(arr, 0, arr.length-1);
    }


    //O(logn * n) = O(nLogn)
    private static void quickSort(int[] arr, int low, int high){
        if(low < high){
            int position = partition(arr, low, high);
            quickSort(arr, low, position -1);
            quickSort(arr, position +1, high);
        }
    }


    // O(n)
    private static int partition(int[] arr, int low, int high){
        int pivot = arr[high];
        int wall = low -1;
        for(int i = low ; i < high; i ++){
            if(arr[i] < pivot){
                wall ++;
                swap(arr, i, wall);
            }
        }
        wall++;
        swap(arr, wall, high);
        return wall;
    }

    private static void insertionSort(int[] arr){
        for(int i = 0 ; i < arr.length -1; i ++){
            if(arr[i] > arr[i+1]){
                int index = findPosition(arr, i);
                moveElements(arr, index, i +1);
            }
        }
    }

    private static int findPosition(int[] arr, int index){
        for(int i = 0 ; i < index -1; i ++){
            if(arr[i] < arr[index] && arr[index] > arr[index -1]){
                return i;
            }
        }
        return index;
    }

    private static void moveElements( int[] arr, int from,  int to){
        int temp = arr[to];
        for(int i = to ; i > from; i --){
            swap(arr, i -1, i  );
        }
        arr[from] = temp;
    }


    public static void findkThLargest(int[] arr, int k){
        findkThLargest(arr, 0, arr.length -1, k);
    }

    public static void findkThLargest(int[] arr, int low, int high, int k){
        if(low <= high){
            int position = partition(arr, low, high);
            if(position == arr.length  - k){
                System.out.println("Kth largest value = " + arr[position]);
                return;
            }

            findkThLargest(arr, low, position -1, k);
            findkThLargest(arr, position +1, high, k);
        }
    }


    public static void findkThSmallest(int[] arr, int k){
        findkThSmallest(arr, 0, arr.length -1, k);
    }

    public static void findkThSmallest(int[] arr, int low, int high, int k){
        if(low <= high){
            int position = partition(arr, low, high);
            if(position == k - 1){
                System.out.println("Kth Smallest value = " + arr[position]);
                return;
            }

            findkThSmallest(arr, low, position -1, k);
            findkThSmallest(arr, position +1, high, k);
        }
    }

    // O(n) provided range is small
    private static void countSort(int[] arr, int RANGE){
        int[] countArr = new int[RANGE];
        for(int j : arr){
            countArr[j] ++;
        }
        int index = 0 ;

        for(int i = 0 ; i < RANGE; i ++){
            while(countArr[i] > 0){
                arr[index] = i;
                index ++;
                countArr[i] --;
            }
        }
    }

    private static void dutchFlag(int[] arr, int pivot){
        int low = 0;
        int high = arr.length -1;
        int mid = 0;

        while(mid <= high){
            if(arr[mid] < pivot){
                swap(arr, mid, low);
                mid ++;
                low ++;
            }
            else if (arr[mid] == pivot){
                mid ++;
            }else{
                swap(arr, mid, high);
                high --;
            }
        }
    }

    public static int majorityElement(int[] arr){
        int majority = findMajority(arr);
        int count = 0;
        for(int i = 0 ; i < arr.length ; i ++){
            if(arr[i] == majority){
                count ++;
            }
        }
        if(count >= arr.length/2){
            return  majority;
        }

        return  Integer.MIN_VALUE;
    }

    private static int findMajority(int[] arr){
        int majorityElement = arr[0];
        int count = 1;
        for(int i = 1; i < arr.length; i ++){
            if(arr[i] == majorityElement){
                count++;
            }else{
                count --;
                if(count == 0){
                    majorityElement = arr[i];
                    count = 1;
                }
            }
        }
        return majorityElement;
    }

    //O(nLogn)
    private static void wiggleSort(int[] arr){
        Arrays.sort(arr);
        for(int i = 0 ; i < arr.length; i = i +2){
            swap(arr, i , i +1);
        }
    }

    // O(n)
    private static void wiggleSortOn(int[] arr){
        boolean bValue = true;
        for(int i = 0 ; i < arr.length -1; i ++){
            if(bValue && arr[i] < arr[i+1]){
                swap(arr, i , i +1);
            }else if (!bValue && arr[i] > arr[i+1]){
                swap(arr, i , i +1);
            }
            bValue = !bValue;
        }
    }

    private static int[]  mergeSortedArrays(int[] arr1, int[] arr2){
        if(arr1 == null){
            return arr2;
        }
        if(arr2 == null){
            return arr1;
        }
        int[] merged = new int[arr1.length + arr2.length];
        int ptr1 =0;
        int ptr2 = 0 ;
        int index = 0;

        while(ptr1 < arr1.length && ptr2 < arr2.length){
            if(arr1[ptr1] < arr2[ptr2]){
                merged[index++] = arr1[ptr1++];
            }else{
                merged[index++] = arr2[ptr2++];
            }
        }
        while(ptr1 < arr1.length){
            merged[index++] = arr1[ptr1++];
        }

        while(ptr2 < arr2.length){
            merged[index++] = arr2[ptr2++];
        }

        return merged;
    }

    private static int[] mergeKSortedArrays(ArrayList<int[]> listOfArrays){
        int count = 0;
        for (int[] arr : listOfArrays) {
            count += arr.length;
        }
        int[] merged = new int[count];
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int[] arr : listOfArrays) {
            for (int i : arr) {
                queue.add(i);
            }
        }
        for(int i = 0 ; i < merged.length; i ++){
            merged[i] = queue.remove();
        }

        return merged;
    }

















}
