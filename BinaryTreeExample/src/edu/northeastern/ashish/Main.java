package edu.northeastern.ashish;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.root = initializeTree();

        tree.populateNextRight();

        System.out.println();



//        Integer[] preOrder = {5,4,11,7,2,8,13,4,1};
//        Integer[] inOrder = {7,11,2,4,5,13,8,4,1};
//
//        BinaryTree<Integer> tree = new BinaryTree<>();
//        Node<Integer> node = tree.getTreeFromPreOrderAndInOrder(preOrder, inOrder);
//
//        tree.root = node;
//
//        ArrayList<ArrayList<Integer>> allPaths = tree.getAllPaths();
//
//        for (ArrayList<Integer> path : allPaths) {
//            System.out.println(path.toString());
//        }




//        BinaryTree<Integer> tree = new BinaryTree<>();
//        tree.root = initializeTree();
//        System.out.println(tree.sizePreOrder());

//        Node<Integer> node1 = getNode1();
//        Node<Integer> node2 = getNode2();
//        System.out.println(isSameTree(node1, node2));

    }


    private static Node<Integer> getNode1(){
        Node<Integer> node1 = new Node<>(1);
        node1.left = new Node<>(2);
        node1.right = new Node<>(3);
        return node1;

    }

    private static Node<Integer> getNode2(){
        Node<Integer> node2 = new Node<>(1);
        node2.left = new Node<>(2);
        node2.right = new Node<>(3);
        return node2;

    }


    private static boolean isSameTree(Node<Integer> node1, Node<Integer> node2){

        if(node1 == null && node2 == null){
            return true;
        }
        if(node1 == null || node2 == null){
            return false;
        }

        return  node1.data.equals(node2.data)
                && isSameTree(node1.left, node2.left)
                && isSameTree(node1.right, node2.right);
     }


    private  static Node<Integer> initializeTreeForMirror(){
        Node<Integer> root = new Node<>(1);

        root.left = new Node<>(2);
        root.right = new Node<>(3);

        root.left.left = new Node<>(4);
        root.left.right = new Node<>(5);
        root.right.left = new Node<>(6);
        root.right.right = new Node<>(7);
        return root;
    }

    private  static Node<Integer> initializeTreeForSymmetricTree(){
        Node<Integer> root = new Node<>(1);

        root.left = new Node<>(2);
        root.right = new Node<>(2);

        root.left.left = new Node<>(3);
        root.left.right = new Node<>(4);
        root.right.left = new Node<>(4);
        root.right.right = new Node<>(3);
        return root;
    }



    private  static Node<Integer> initializeTree(){
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
