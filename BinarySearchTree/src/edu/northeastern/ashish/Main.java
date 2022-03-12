package edu.northeastern.ashish;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        BST<Integer> tree = new BST<>();
        tree.root = initializeTree();

//        System.out.println("*******Pre Order*******");
//        tree.preOrder();
//        System.out.println("*******In Order*******");
//        tree.inOrder();
//        System.out.println("*******Reverse In Order*******");
//        tree.reverseInOrder();
//        System.out.println("*******Post Order*******");
//        tree.postOrder();
//        System.out.println("*******Level Order*******");
//        tree.levelOrder();

//        System.out.println(tree.search(5));
//
//        tree.insert(8);
//        tree.levelOrder();

//
//        ArrayList<Integer> list = tree.getSortedListFromBST();
//        System.out.println(list.toString());
//
//        tree.convertBinaryTreeToBST();
//        tree.inOrder();
//        tree.inOrder();
//
//        tree.findKthSmallest(3);

//        Integer[] arr = {1,2,3,4,5,6,7,8};
//        Node<Integer> node = tree.getBalancedNodeFromSortedArray(arr);
//
//        tree.levelOrder(node);

//        Integer[] arr1 = {1,3,5,7};
//        Integer[] arr2 = {2,4,6,8};
//        Node<Integer> node1 = tree.getBalancedNodeFromSortedArray(arr1);
//        Node<Integer> node2 = tree.getBalancedNodeFromSortedArray(arr2);
//
//        Node<Integer> merged = tree.mergeTwoBSTNodes(node1, node2);
//        tree.levelOrder(merged);


        tree.inOrderPredecessor(13);

    }

    private static Node<Integer> initializeTree(){
        Node<Integer> root = new Node<>(8);

        root.left = new Node<>(3);
        root.right = new Node<>(10);

        root.left.left = new Node<>(1);
        root.left.right = new Node<>(6);
        root.right.right = new Node<>(14);

        root.left.right.left = new Node<>(4);
        root.left.right.right = new Node<>(7);
        root.right.right.left = new Node<>(13);

        return root;
    }

    private  static Node<Integer> initializeBinaryTree(){
        Node<Integer> root = new Node<>(1);

        root.left = new Node<>(2);
        root.right = new Node<>(3);

        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);

        root.left.left.left = new Node<>(8);
        root.left.left.right = new Node<>(9);
        root.right.left.right = new Node<>(10);
        root.right.right.right = new Node<>(11);

        return root;
    }
}
