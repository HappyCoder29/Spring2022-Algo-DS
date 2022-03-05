package edu.northeastern.ashish;

import java.util.Arrays;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        //generateMarySequence(3, 10);
        //generateCombinations("abcdef", 3);
        //generateAllSubsets("abc");
//        int[] coins = {7,5,3,2,1,3,6};
//        int total = 6;
//        numberOfWaysForTotal(coins, total);

       // printPermutations("abc");

       // System.out.println(isValidParenthesisNoStack("((()))()())"));

       // printValidParenthesis(3);

        int[] states = {9,3,11,6,55,9,7,3,3,29,16,4,4,20,11,
                6,6,8,8,4,10,11,16,10,6,10,3,5,6,4,14,5,29,
                15,3,18,7,7,20,4,9,3,11,38,6,3,13,12,5,10};
        printNumberOfWays(states);
    }


    // O(2^n)
    private static void generateBinarySequence(int size){
        if(size < 0){
            return;
        }

        int[] result = new int[size];
        int current = 0;
        generateBinarySequence(result, current);
    }

    private static void generateBinarySequence(int[] result, int current){
        if(current == result.length){
            System.out.println(Arrays.toString(result));
            return;
        }
        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            System.out.println(Arrays.toString(result));
            generateBinarySequence(result, current +1);
        }
    }


    private static void generateTernarySequence(int size){
        if(size < 0){
            return;
        }

        int[] result = new int[size];
        int current = 0;
        generateTernarySequence(result, current);
    }

    private static void generateTernarySequence(int[] result, int current){
        if(current == result.length){
            System.out.println(Arrays.toString(result));
            return;
        }
        for(int i = 0 ; i < 3; i ++){
            result[current] = i;
            generateTernarySequence(result, current +1);
        }
    }

    // O(m^n)

    private static void generateMarySequence(int size, int m){
        if(size < 0){
            return;
        }
        int[] result = new int[size];
        int current = 0;
        generateMarySequence(result, current, m);
    }

    private static void generateMarySequence(int[] result, int current, int m){
        if(current == result.length){
            System.out.println(Arrays.toString(result));
            return;
        }
        for(int i = 0 ; i < m; i ++){
            result[current] = i;
            generateMarySequence(result, current +1, m);
        }
    }

    // O(m^n) m = size of string, n = size if combination string
    private static  void generateCombinations(String str, int size){
        if(str == null || str.length() == 0 || size <= 0 ){
            return;
        }

        int[] result = new int[size];
        int current = 0;
        combinations(str, result, current);
    }

    private static void combinations(String str, int[] result, int current){
        if(current == result.length){
            printCombinations(str, result);
            return;
        }

        for(int i = 0 ; i < str.length(); i ++){
            result[current] = i;
            combinations(str, result, current +1);
        }
    }

    private static void printCombinations(String str, int[] result){
        for(int i = 0 ; i < result.length; i ++){
            System.out.print( str.charAt( result[i]) + ", ");
        }
        System.out.println();
    }

    // O(2 ^ n) // n = size of string
    private static void generateAllSubsets(String str){
        if(str == null || str.length() <=1){
            return;
        }
        int[] result = new int[str.length()];
        int current = 0;
        generateAllSubsets(str, result, current);

    }

    private static void generateAllSubsets(String str, int[] result, int current){

        if(current == str.length()){
            printSubset(str, result);
            return;
        }
        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            generateAllSubsets(str, result, current +1);
        }

    }

    private static void printSubset(String str, int[] result){
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for(int i = 0 ; i < result.length ; i ++){
            if(result[i] == 1){ // we will take the value and print it
                sb.append(str.charAt(i) + ", ");
            }
        }
        int index = sb.toString().lastIndexOf(',');
        if(index != -1){
            sb.delete(sb.lastIndexOf(","), sb.length() -1);
        }
        sb.append("}");

        System.out.println(sb.toString());

    }

    // O(2 ^ n) // n = coins size

    private static void numberOfWaysForTotal(int[] coins, int total){
        if(coins == null || coins.length == 0 || total <= 0){
            return;
        }

        int[] result = new int[coins.length];
        int current = 0;
        numberOfWaysForTotal(coins, total, result, current);

    }


    private static void numberOfWaysForTotal(int[] coins, int total, int[] result, int current){

        if(current == result.length){
            // print the subsets and see if they equal to total
            printSubsetEqualToTotal(coins, total, result);
            return;
        }

        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            numberOfWaysForTotal(coins, total, result, current +1);
        }
    }

    private static void printSubsetEqualToTotal(int[] coins, int total, int[] result){
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        int sum = 0 ;
        for(int i = 0 ; i < result.length ; i ++){
            if(result[i] == 1){ // we will take the value and print it
                sb.append(coins[i] + ", ");
                sum += coins[i];
            }
        }
        int index = sb.toString().lastIndexOf(',');
        if(index != -1){
            sb.delete(sb.lastIndexOf(","), sb.length() -1);
        }
        sb.append("}");

        if(sum == total){
            System.out.println(sb.toString());
        }
    }


    private static void printPermutations(String str){

        int[] result = new int[str.length()];
        int current = 0;
        printPermutations(str, result, current);

    }

    private static void printPermutations(String str, int[] result, int current){

        if(result.length == current){
            // print the result
            printCombinations(str, result);
            return;
        }

        for(int i = 0 ; i < str.length(); i ++){
            if(isValidPermutation(result, current, i)) {
                result[current] = i;
                printPermutations(str, result, current + 1);
            }
        }

    }

    private static boolean isValidPermutation(int[] result, int current, int numToAdd){
        for(int i = 0; i < current; i ++){
            if(result[i] == numToAdd){
                return false;
            }
        }
        return true;
    }

    // O(n) Space O(n)
    private static boolean isValidParenthesis(String str){
        if(str == null || str.length() <= 1){
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (Character ch : str.toCharArray()) {
            if(ch == '('){
                stack.push('(');
            }else if(ch == ')'){
                if(stack.isEmpty()){
                    return false;
                }
                stack.pop();
            }
        }

        return  stack.isEmpty() == true;
    }

    // O(n) Space O(1)
    private static boolean isValidParenthesisNoStack(String str){
        if(str == null || str.length() <= 1){
            return false;
        }

        int balance = 0;
        for (Character ch : str.toCharArray()){
            if(ch == '('){
                balance ++;
            }else if(ch == ')'){
                if(balance <=0 ){
                    return false;
                }
                balance--;
            }
        }
        return  balance == 0;
    }

    // O(2^n)
    private static void printValidParenthesis(int size){
        char[] arr = new char[size *2];
        int current = 0;
        printValidParenthesis(arr, current);

    }

    private static void printValidParenthesis(char[] arr, int current){
        if(current == arr.length){
            String str = String.valueOf(arr);
            if( isValidParenthesisNoStack(str) ){
                System.out.println(str);
            }
            return;
        }

        for(int i = 0; i < 2; i ++){
            arr[current] = (i == 0) ? '(' : ')';
            printValidParenthesis(arr, current +1);
        }
    }

    private static void printNumberOfWays(int[] states){

        int[] result = new int[states.length];
        int current = 0 ;
        printNumberOfWays(states, result, current);
    }

    private static void printNumberOfWays(int[] states, int[] result, int current){
        if(current == result.length){
            printResult(states, result);
            return;
        }
        for(int i = 0 ; i < 2; i ++){
            result[current] = i;
            printNumberOfWays(states, result, current +1);
        }
    }

    private static void printResult(int[] states, int[] result){
        int democrats = 0 ;
        int republican = 0;
        for(int i = 0; i < result.length; i ++){
            if(result[i] == 0){
                democrats += states[i];
            }else{
                republican += states[i];
            }
        }

        System.out.println("Republicans = " + republican);
        System.out.println("Democrat = " + democrats);

    }

}
