package edu.northeastern.ashish;

import javax.print.DocFlavor;
import java.util.*;

public class BST <T>{
    public Node<T> root;

    public BST(){

    }

    // O(n)
    public void preOrder(){
        preOrder(root);
        System.out.println();
    }
    private void preOrder(Node<T> node){
        if(node != null){
            System.out.print(node.data + " , ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // O(n)
    public void inOrder(){
        inOrder(root);
        System.out.println();

    }
    private void inOrder(Node<T> node){
        if(node != null){
            inOrder(node.left);
            System.out.print(node.data + " , ");
            inOrder(node.right);
        }
    }

    // O(n)
    public void reverseInOrder(){
        reverseInOrder(root);
        System.out.println();

    }
    private void reverseInOrder(Node<T> node){
        if(node != null){
            reverseInOrder(node.right);
            System.out.print(node.data + " , ");
            reverseInOrder(node.left);
        }
    }

    // O(n)
    public void postOrder(){
        postOrder(root);
        System.out.println();

    }
    private void postOrder(Node<T> node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " , ");
        }
    }

    // O(n)
    public void levelOrder(){
        if(root == null){
            return;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while( !queue.isEmpty() ){
            Node<T> node = queue.remove();
            if(node != null){
                System.out.print(node.data + ", ");
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            else{
                System.out.println();
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }
        }
    }


    public void levelOrder(Node<T> tNode){
        if(tNode == null){
            return;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(tNode);
        queue.add(null);
        while( !queue.isEmpty() ){
            Node<T> node = queue.remove();
            if(node != null){
                System.out.print(node.data + ", ");
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            else{
                System.out.println();
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }
        }
    }


    // O(log (n))
    public boolean search(int value){
        return search(root, value);
    }

    private boolean search(Node<T> node, int value) {
        if(node == null){
            return false;
        }
        if(node.data.equals(value)){
            return true;
        }
        else if( (int)node.data <  value){
            return search(node.right, value);
        }else{
            return search(node.left, value);
        }
    }

    // O(log (n))
    public void insert(int value){
        Node node = new Node(value);
        if(root == null){
            root = node;
            return;
        }

        Node current = root;
        Node parent = null;

        while(current != null){
            parent = current;
            if((int)current.data <= value){
                current = current.right;
            }else{
                current = current.left;
            }
        }
        if((int)parent.data <= value){
            parent.right = node;
        }else{
            parent.left = node;
        }
    }

    public Integer getMinValue(){
        if(root == null){
            return Integer.MIN_VALUE;
        }
        return getMinValue(root);
    }
    public Integer getMinValue(Node<T> node){
        if(node.left== null){
            return (Integer)node.data;
        }
        return getMinValue(node.left);
    }

    public Integer getMaxValue(){
        if(root == null){
            return Integer.MIN_VALUE;
        }
        return getMaxValue(root);
    }
    public Integer getMaxValue(Node<T> node){
        if(node.right== null){
            return (Integer)node.data;
        }
        return getMaxValue(node.right);
    }

    public ArrayList<T> getSortedListFromBST(){
        ArrayList<T> list = new ArrayList<>();
        getSortedListFromBST(root, list);
        return list;
    }

    public void getSortedListFromBST(Node<T> node, ArrayList<T> list){
        if(node != null){
            getSortedListFromBST(node.left, list);
            list.add(node.data);
            getSortedListFromBST(node.right, list);
        }
    }

    public T[] getSortedArrayFromBST(){
        ArrayList<T> list = new ArrayList<>();
        getSortedListFromBST(root, list);
        return (T[]) list.toArray();
    }


    public T[] getSortedArrayFromBST(Node<T> node){
        ArrayList<T> list = new ArrayList<>();
        getSortedListFromBST(node, list);
        return (T[]) list.toArray();
    }

    public void convertBinaryTreeToBST(){
        ArrayList<T> list = getSortedListFromBST();
        Integer[] arr =  list.toArray(new Integer[0]);
        Arrays.sort(arr);
        convertBinaryTreeToBST(root, arr, new Box<Integer>(0));
    }

    private void convertBinaryTreeToBST(Node<T> node, Integer[] arr, Box<Integer> index){

        if( node != null ){
            convertBinaryTreeToBST(node.left, arr, index);
            node.data = (T) arr[index.data];
            index.data ++;
            convertBinaryTreeToBST(node.right, arr, index);
        }
    }


    public void printInRange(int start, int end){
        printInRange(root, start,end );
        System.out.println();
    }

    public void printInRange(Node<T> node, int start, int end){

        if(node != null){
            if((int) node.data >= start){
                printInRange(node.left, start, end);
            }

            // print if they are in range
            if( (int)node.data >= start && (int)node.data <= end){
                System.out.print(node.data + ", ");
            }

            if((int)node.data <= end){
                printInRange(node.right, start, end);
            }

        }
    }



    public void findKthLargest(int k){
        if(k <= 0 || root == null){
            return;
        }
        findKthLargest(root, k, new Box<Integer>(0));
    }

    private void findKthLargest(Node<T> node, int k, Box<Integer> index){

        if(node != null && index.data <= k ){
            findKthLargest(node.right, k, index);

            //reverse in order
            index.data ++;
            if(index.data == k){
                System.out.println("Kth (" + index.data  + ") Largest value = " + node.data);
            }

            findKthLargest(node.left, k, index);

        }

    }


    public void findKthSmallest(int k){
        if(k <= 0 || root == null){
            return;
        }
        findKthSmallest(root, k, new Box<Integer>(0));
    }

    private void findKthSmallest(Node<T> node, int k, Box<Integer> index){

        if(node != null && index.data <= k ){
            findKthSmallest(node.left, k, index);

            //In order
            index.data ++;
            if(index.data == k){
                System.out.println("Kth (" + index.data  + ") smallest value = " + node.data);
            }

            findKthSmallest(node.right, k, index);

        }

    }


    private Integer[] mergeTwoSortedArrays(Integer[] arr1, Integer[] arr2){
        Integer[] merged = new Integer[arr1.length + arr2.length];
        Integer ptr1 = 0;
        Integer ptr2 = 0;
        Integer ptrMerged = 0;

        while(ptr1 < arr1.length && ptr2 < arr2.length){
            merged[ptrMerged] = arr1[ptr1] < arr2[ptr2] ? arr1[ptr1]  : arr2[ptr2] ;
            ptrMerged ++;
            if(arr1[ptr1] < arr2[ptr2]){
                ptr1++;
            }else{
                ptr2++;
            }
        }

        while(ptr1 < arr1.length){
            merged[ptrMerged] = arr1[ptr1];
            ptr1++;
            ptrMerged++;
        }
        while(ptr2 < arr2.length){
            merged[ptrMerged] = arr2[ptr2];
            ptr2++;
            ptrMerged++;
        }

        return merged;

    }

    public Node<T> getBalancedNodeFromSortedArray(Integer[] arr){
        if(arr == null || arr.length == 0){
            return null;
        }
        return getBalancedNodeFromSortedArray(arr, 0, arr.length -1);
    }

    private Node<T> getBalancedNodeFromSortedArray(Integer[] arr, int start, int end){

        if(start > end){
            return null;
        }

        int mid = (start + end)/2;
        Node<T> node = (Node<T>) new Node<Integer>(arr[mid]);
        node.left = getBalancedNodeFromSortedArray(arr, start, mid -1);
        node.right = getBalancedNodeFromSortedArray(arr, mid +1, end);
        return  node;

    }


    public Node<T> mergeTwoBSTNodes(Node<T> node1, Node<T> node2){

        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }
        Integer[] arr1 = Arrays.asList(getSortedArrayFromBST(node1)).toArray(new Integer[0]);
        Integer[] arr2 = Arrays.asList(getSortedArrayFromBST(node2)).toArray(new Integer[0]);

        Integer[] merged = mergeTwoSortedArrays(arr1, arr2);

        Node<T> mergedRoot = getBalancedNodeFromSortedArray( merged  );

        return mergedRoot;

    }


    public void inOrderSuccessor(int value){
        Box<Boolean> found = new Box<>(false);
        Box<Boolean> successorFound = new Box<>(false);
        inOrderSuccessor(root, value, found, successorFound);
    }

    private void inOrderSuccessor(Node<T> node, int value, Box<Boolean> found, Box<Boolean> successorFound){

        if(node != null && successorFound.data == false){
            inOrderSuccessor(node.left, value, found, successorFound);
            // Inorder
            if(found.data == true && successorFound.data == false){
                System.out.println("Inorder Successor of " + value + " is " + node.data);
                successorFound.data = true;
            }

            if(node.data.equals(value)){
                found.data = true;
            }

            inOrderSuccessor(node.right, value, found, successorFound);
        }
    }

    public void preOrderSuccessor(int value){
        Box<Boolean> found = new Box<>(false);
        Box<Boolean> successorFound = new Box<>(false);
        preOrderSuccessor(root, value, found, successorFound);
    }

    private void preOrderSuccessor(Node<T> node, int value, Box<Boolean> found, Box<Boolean> successorFound){

        if(node != null && successorFound.data == false){
            // preOrder
            if(found.data == true && successorFound.data == false){
                System.out.println("Preorder Successor of " + value + " is " + node.data);
                successorFound.data = true;
            }

            if(node.data.equals(value)){
                found.data = true;
            }
            preOrderSuccessor(node.left, value, found, successorFound);
            preOrderSuccessor(node.right, value, found, successorFound);
        }
    }


    public void inOrderPredecessor(int value){
        Box<Boolean> found = new Box<>(false);
        Box<Node<T>> previousNode = new Box<>(null);

        inOrderPredecessor(root, value, previousNode, found);
    }

    private void inOrderPredecessor(Node<T> node,
                                    int value,
                                    Box<Node<T>> previousNode,
                                    Box<Boolean> found){

        if(node != null && found.data == false){

            inOrderPredecessor(node.left, value, previousNode, found);

            if(node.data.equals(value)){
                found.data = true;
                System.out.println("Inorder Predessor of " + value + " = " + previousNode.data.data);
            }
            previousNode.data = node;
            inOrderPredecessor(node.right, value, previousNode, found);
        }
    }






}
