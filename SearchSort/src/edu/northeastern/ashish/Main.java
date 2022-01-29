package edu.northeastern.ashish;

import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        int[] arr = {5,7,-3,2,1,8,2};
        System.out.println( Arrays.toString( threeValuesSumEqualToX(arr, 8) ) );
       // System.out.println(findIndexInSortedRotatedArray(arr, 7));
        ArrayList<Interval> list = new ArrayList<>();
        list.add(new Interval(1,3));
        list.add(new Interval(2,6));
        list.add(new Interval(8,10));
        list.add(new Interval(15,18));

        ArrayList<Interval> merged = mergeIntervals(list);
        for (Interval interval: merged) {
            System.out.println("[" + interval.start + "," + interval.end + "]");
        }

      //  System.out.println(merged.toString() );


    }

    /// region Class 1 Sorting


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
    /// endregion



    /// region Class 2 Searching

    // O(log(n))
    private  static  boolean binSearchIterative(int[] arr, int x){
        if(arr == null || arr.length == 0){
            return false;
        }
        int start = 0;
        int end = arr.length -1;
        while(start <= end){
            int mid = (start + end)/2;
            if(arr[mid] == x){
                return true;
            }
            else if(arr[mid] < x){
                start = mid + 1;
            }else{
                end = mid - 1;
            }
        }
        return false;
    }

    // O(log(n))
    private static boolean binSearchRecursive(int[] arr, int x){
        if(arr == null || arr.length == 0){
            return false;
        }
        return binSearchRecursive(arr, x, 0, arr.length -1);
    }
    private static boolean binSearchRecursive(int[] arr, int x, int start, int end){
        if(start > end){
            return false;
        }

        int mid = (start + end)/2;
        if(arr[mid] == x){
            return true;
        }
        else if(arr[mid] < x){
            return binSearchRecursive(arr, x, mid+1, end);
        }else{
            return binSearchRecursive(arr, x, start, mid -1);
        }
    }

    // o(log(n))
    private static int findFirstOccurance(int[] arr, int x){
        if(arr == null || arr.length == 0) {
            return -1;
        }
        return findFirstOccurance(arr, x, 0, arr.length -1 );

    }
    private static int findFirstOccurance(int[] arr, int x, int start, int end){
        if(arr[start] > x || arr[end] < x ){
            return -1;
        }
        if(arr[start] == x){
            return start;
        }
        int mid = (start + end)/2;

        if(arr[mid] == x){
            return findFirstOccurance(arr, x, start, mid);
        }
        else if(arr[mid] < x){
            return  findFirstOccurance(arr, x, mid +1, end);
        }
        else{
            return  findFirstOccurance(arr, x, start, mid -1);
        }
    }


    // O(log(n))
    private static int findLastOccurance(int[] arr, int x){
        if(arr == null || arr.length == 0) {
            return -1;
        }
        return findLastOccurance(arr, x, 0, arr.length -1 );

    }
    private static int findLastOccurance(int[] arr, int x, int start, int end){
        if(arr[start] > x || arr[end] < x ){
            return -1;
        }
        if(arr[end] == x){
            return end;
        }
        int mid = (start + end)/2;

        if(arr[mid] == x){
            return findLastOccurance(arr, x, mid, end);
        }
        else if(arr[mid] < x){
            return  findLastOccurance(arr, x, mid +1, end);
        }
        else{
            return  findLastOccurance(arr, x, start, mid -1);
        }
    }

    private static int findTotalOccurances(int[] arr, int x){
        if(arr == null || arr.length == 0) {
            return 0;
        }
        // O(log(n))
        int first = findFirstOccurance(arr, x);
        if(first == -1){
            return 0;
        }
        // O(log(n))
        int last = findLastOccurance(arr, x);

        return  (last - first + 1);
    }

    // O(log(n))
    private static int findTotalOccurancesRecursive(int[] arr, int x){
        if(arr == null || arr.length == 0) {
            return 0;
        }

        return findTotalOccurancesRecursive(arr, x, 0, arr.length -1);


    }

    private static int findTotalOccurancesRecursive(int[] arr, int x, int start, int end){
        if(arr[start] > x || arr[end] < x ){
            return 0;
        }
        if(arr[start] == x && arr[end] == x){
            return (end-start +1);
        }
        int mid = (start + end )/2;

        if(arr[mid] == x) {
            return  1 + findTotalOccurancesRecursive(arr, x, start, mid -1)+
                    findTotalOccurancesRecursive(arr, x, mid +1, end);
        }else if (arr[mid] < x){
            return  findTotalOccurancesRecursive(arr, x , mid +1, end);
        }else{
            return  findTotalOccurancesRecursive(arr, x , start, mid -1);
        }
    }


    private static void reverse(int[] arr, int start, int end){
        if(arr == null || arr.length == 0 || start >= end || start < 0 || end > arr.length -1 ){
            return;
        }

        while(start < end){
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start ++;
            end --;
        }


    }

    private static void rotateArray(int[] arr, int n){
         if(arr == null || arr.length <= 1){
             return;
         }
         n = n % arr.length;

         // Step 1 : reverse the entire array
        reverse(arr, 0, arr.length -1);
        // step 2 : reverse from 0 to n-1
        reverse(arr, 0, n-1);
        // step 3 : reverse from n to arr.length -1
        reverse(arr, n, arr.length -1);
    }

    private static int findRotatedIndex(int[] arr){
        if(arr == null || arr.length == 0){
            return -1;
        }
        if(arr.length == 1){
            return 0;
        }
        //' Array is already properly sorted and there are no duplicates
        if(arr[0] < arr[arr.length -1] ){
            return -1;
        }
        return findRotatedIndex(arr, 0, arr.length -1);
    }

    private static int findRotatedIndex(int[] arr, int start, int end){
        // No duplicates
        if(arr[start] == arr[end]){
            return start;
        }

        int mid = (start + end)/2;
        if(arr[mid] > arr[start]){
            //left half is properly sorted
            return findRotatedIndex(arr, mid, end);
        }else{
            // right half is properly sorted
            return findRotatedIndex(arr, start, mid);
        }
    }

    private static int binSearch(int[]arr, int x){
        if(arr == null || arr.length == 0 ){
            return -1;
        }
        return binSearch(arr, x, 0, arr.length -1);
    }
    private static int binSearch(int[]arr, int x, int start, int end){
        if(start > end){
            return -1;
        }
        int mid = (start + end)/2;
        if(arr[mid] == x){
            return mid;
        }else if(arr[mid] < x){
            return binSearch(arr, x, mid +1, end );
        }else{
            return binSearch(arr, x, start, mid-1);
        }
    }


    private static int findIndexInSortedRotatedArray(int[] arr, int x){
        if(arr == null || arr.length == 0){
            return  -1;
        }
        if(arr.length == 1){
            return  arr[0] == x ? 0 : -1;
        }

        int index = findRotatedIndex(arr);

        // if the array was not rotated
        if(index == -1){
            return  binSearch(arr, x);
        }

        // If the array was rotated we will look for edge conditions first
        if(arr[index] < x){
            // Largest value is smaller than x
            return -1;
        }
        if(arr[index +1] > x){
            // smallest value is bigger than x
            return  -1;
        }

        //see where should we look for the value

        if(x > arr[0]){
            return binSearch(arr, x, 0, index);
        }

        return binSearch(arr, x, index +1, arr.length -1);
    }

    private static int[] twoSum(int[] arr, int sum){
        int[] result = new int[2];
        result[0] = Integer.MIN_VALUE;
        result[1] = Integer.MIN_VALUE;
        if(arr == null || arr.length <= 1){
            return result;
        }
        Arrays.sort(arr);
        int start = 0;
        int end = arr.length -1;

        while(start < end){
            int total = arr[start] + arr[end];
            if(total == sum){
                result[0] = arr[start];
                result[1] = arr[end];
                return result;
            }else if(total < sum){
                start ++;
            }else{
                end--;
            }
        }
        return result;
    }

    private static int[] twoSum(int[] arr, int sum, int start, int end){
        int[] result = new int[2];
        result[0] = Integer.MIN_VALUE;
        result[1] = Integer.MIN_VALUE;
        if(arr == null || arr.length <= 1){
            return result;
        }

        while(start < end){
            int total = arr[start] + arr[end];
            if(total == sum){
                result[0] = arr[start];
                result[1] = arr[end];
                return result;
            }else if(total < sum){
                start ++;
            }else{
                end--;
            }
        }
        return result;
    }

    private static int[] threeValuesSumEqualToX(int[] arr, int x){
        int[] result = new int[3];
        result[0] = Integer.MIN_VALUE;
        result[1] = Integer.MIN_VALUE;
        result[2] = Integer.MIN_VALUE;

        Arrays.sort(arr);

        for(int i = 0 ; i < arr.length -2 ; i ++){
            int [] values = twoSum(arr, (x-arr[i]), i +1, arr.length -1 );
            if(values[0] == Integer.MIN_VALUE){
                continue;
            }
            result[0] = arr[i];
            result[1] = values[0];
            result[2] = values[1];
            return result;
        }
        return result;
    }


    private static ArrayList<int[]> threeValuesSumEqualToXAllValues(int[] arr, int x){
        ArrayList<int[]> list = new ArrayList<>();
        Arrays.sort(arr);

        for(int i = 0 ; i < arr.length -2 ; i ++){
            int [] values = twoSum(arr, (x-arr[i]), i +1, arr.length -1 );
            if(values[0] == Integer.MIN_VALUE){
                continue;
            }
            int[] result = new int[3];
            result[0] = arr[i];
            result[1] = values[0];
            result[2] = values[1];
            list.add(result);
        }
        return list;
    }


    private static ArrayList<Interval> mergeIntervals(ArrayList<Interval> intervals){
        if(intervals == null || intervals.size() <=1){
            return intervals;
        }

        // Sort the intervals based on start times
        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval interval1, Interval interval2 ) {
                return Integer.compare(interval1.start, interval2.start);
            }
        });

        ArrayList<Interval> list = new ArrayList<>();
        Stack<Interval> stack = new Stack<>();
        stack.push(intervals.get(0));
        for(int i = 1; i < intervals.size(); i ++){
            Interval interval = intervals.get(i);

            if(stack.peek().end >= interval.start && interval.end > stack.peek().end){
                // Intervals overlap
                Interval push = new Interval(stack.pop().start, interval.end);
                stack.push(push);
            }else if(stack.peek().end < interval.start){
                // Intervals dont overlap
                stack.push(interval);
            }

        }
        while (!stack.isEmpty()){
            list.add(0, stack.pop());
        }
        return list;

    }



    /// endregion



















}
