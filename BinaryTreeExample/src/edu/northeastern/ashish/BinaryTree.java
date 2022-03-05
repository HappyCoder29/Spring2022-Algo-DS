package edu.northeastern.ashish;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree <T>{

    public Node<T> root;

    public NodeBPlusTree<T> rootBPlus;

    public BinaryTree(){
    }

    // region Class 1

    public void preOrder(){
        preOrder(root);
    }
    private void preOrder(Node<T> node){
        if(node != null){
            System.out.print(node.data + " , ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void inOrder(){
        inOrder(root);
    }
    private void inOrder(Node<T> node){
        if(node != null){
            inOrder(node.left);
            System.out.print(node.data + " , ");
            inOrder(node.right);
        }
    }

    public void postOrder(){
        postOrder(root);
    }
    private void postOrder(Node<T> node){
        if(node != null){
            postOrder(node.left);
            postOrder(node.right);
            System.out.print(node.data + " , ");
        }
    }

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

    public void levelOrderSameLine(){
        if(root == null){
            return;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while( !queue.isEmpty() ){
            Node<T> node = queue.remove();
            System.out.print(node.data + ", ");
            if(node.left != null){
                queue.add(node.left);
            }
            if(node.right != null){
                queue.add(node.right);
            }
        }
        System.out.println();
    }

    public void preOrderBPlus(){
        preOrderBPlus(rootBPlus);
    }
    private void preOrderBPlus(NodeBPlusTree<T> node){
        if(node != null){
            System.out.print(node.data + " , ");
            for(int i = 0 ; i < node.children.size(); i ++){
                preOrderBPlus(node.children.get(i));
            }
        }
    }

    public int size(){
        return size(root);
    }

    private int size(Node<T> node){
        if(node == null){
            return 0;
        }
        return 1 +  size(node.left) + size(node.right) ;
    }

    public int height(){
        return height(root);
    }

    private int height(Node<T> node){
        if(node == null){
            return 0;
        }

        return 1 + Math.max(height(node.left), height(node.right) );
    }


    public void leftView(){
        if(root == null){
            return;
        }
        boolean bPrint = true;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while( !queue.isEmpty() ){
            Node<T> node = queue.remove();
            if(node != null){
                if(bPrint == true){
                    System.out.print(node.data + ", ");
                    bPrint = false;
                }
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
                bPrint = true;
            }
        }
    }


    public void rightView(){
        if(root == null){
            return;
        }
        Node<T> prevNode = null;
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        while( !queue.isEmpty() ){
            Node<T> node = queue.remove();
            if(node != null){
                prevNode = node;
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            else{
                System.out.println(prevNode.data);
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }
        }
    }



    public void zigZag(){
        if(root == null){
            return;
        }
        boolean bprint = true;
        Stack<Node<T>> stack = new Stack<>();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);

        while( !queue.isEmpty() ){
            Node<T> node = queue.remove();
            if(node != null){
                if(bprint == true){
                    System.out.print(node.data + ", ");
                }else{
                    stack.push(node);
                }
                if(node.left != null){
                    queue.add(node.left);
                }
                if(node.right != null){
                    queue.add(node.right);
                }
            }
            else{
                while(!stack.isEmpty()){
                    System.out.print(stack.pop().data + ", ");
                }
                System.out.println();
                bprint = !bprint;
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }
        }
    }


    public void printBoundary(){
        Stack<Node<T>> stack = new Stack<>();
        printBoundary(root, 0, 0, stack);
        while(!stack.isEmpty()){
            System.out.print(stack.pop().data + ", ");
        }
        System.out.println();
    }

    private void printBoundary(Node<T> node, int left, int right, Stack<Node<T>> stack){

        if(node != null){
            // Print
            if(node.left == null && node.right == null){
                // leaf node
                System.out.print(node.data + ", ");
            }else if (right == 0 ){
                System.out.print(node.data + ", ");
            }
            else if (left == 0){
                stack.push(node);
            }
            // left
            printBoundary(node.left, left +1, right, stack);
            // right
            printBoundary(node.right, left, right + 1, stack);

        }

    }

    public void depthFirst(){
        if(root == null){
            return;
        }
        Stack<Node<T>> stack = new Stack<>();
        stack.push(root);

        while( !stack.isEmpty() ){
            Node<T> node = stack.pop();
            System.out.print(node.data + ", ");
            if(node.right != null){
                stack.push(node.right);
            }
            if(node.left != null){
                stack.push(node.left);
            }
        }
        System.out.println();
    }

    public void mirrorTree(){
        mirrorTree(root);
    }

    // O(n)
    public void mirrorTree(Node<T> node){

        if(node != null){
            mirrorTree(node.left);
            mirrorTree(node.right);
            Node<T> temp = node.left;
            node.left = node.right;
            node.right = temp;
        }

    }


    // O(n)
    private  boolean isSameTree(Node<T> node1, Node<T> node2){

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


    // O(3n) ~= O(n)
    public boolean isSymmetricTree(){
        if(root == null){
            return true;
        }

        Node<T> right = root.right;
        // Mirror the right side
        mirrorTree(right); // O(n)

        boolean isSymmetric = isSameTree(root.left, right); // O(n)
        mirrorTree(right);  //O(n)

        return isSymmetric;


    }


    public int sizePreOrder(){
        Box<Integer> box = new Box<>(0);
        sizePreOrder(root, box );
        return box.data;
    }
    private void sizePreOrder(Node<T> node,  Box<Integer> box){
        if(node != null) {
            box.data ++;
            System.out.println(box.data);

            sizePreOrder(node.left, box);
            sizePreOrder(node.right, box);

        }
    }
    // endregion


    //region Class 2

    public int maxDepth(){
        return maxDepth(root);
    }

    private int maxDepth(Node<T> node){

        if(node == null){
            return 0;
        }

        return  1 + Math.max(maxDepth(node.left) , maxDepth(node.right));

    }

    private int findIndex(T[] arr, int start, int end, T value){
        for(int i = start; i <= end; i ++){
            if(arr[i].equals(value)){
                return i;
            }
        }
        return -1;
    }

    public Node<T> getTreeFromPreOrderAndInOrder(T[] preOrder, T[] inOrder){
        Box<Integer> preIndex = new Box<>(0);

        return getTreeFromPreOrderAndInOrder(preOrder, inOrder, preIndex, 0, preOrder.length -1);
    }

    private Node<T> getTreeFromPreOrderAndInOrder(T[] preOrder, T[] inOrder, Box<Integer> preIndex, int start, int end){
        if(start > end || preIndex.data >= preOrder.length){
            return null;
        }

        Node<T> node = new Node<>(preOrder[preIndex.data]);
        preIndex.data ++;
       // System.out.println(node.data);
        int inOrderIndex = findIndex(inOrder,start, end , node.data);

        if(inOrderIndex == -1){
            node.left = null;
        }else{
            node.left = getTreeFromPreOrderAndInOrder(preOrder, inOrder, preIndex, start,inOrderIndex -1 );
        }

        if(inOrderIndex == -1){
            node.right = null;
        }else{
            node.right   = getTreeFromPreOrderAndInOrder(preOrder, inOrder, preIndex, inOrderIndex +1, end );
        }
        return node;
    }


    public Node<T> getTreeFromInOrderAndPostOrder(T[] inOrder, T[] postOrder){
        Box<Integer> postIndex = new Box<>(postOrder.length -1);
        return getTreeFromInOrderAndPostOrder(inOrder, postOrder,postIndex, 0, inOrder.length -1);
    }

    private Node<T> getTreeFromInOrderAndPostOrder(T[] inOrder, T[] postOrder, Box<Integer> postIndex, int start, int end){
        if(start > end || postIndex.data < 0 ){
            return null;
        }

        Node<T> node = new Node<>(postOrder[postIndex.data]);
        postIndex.data --;
        //System.out.println(node.data);
        int inOrderIndex = findIndex(inOrder,start, end , node.data);

        if(inOrderIndex == -1){
            node.right = null;
        }else{
            node.right   = getTreeFromInOrderAndPostOrder(inOrder, postOrder, postIndex, inOrderIndex +1, end );
        }
        if(inOrderIndex == -1){
            node.left = null;
        }else{
            node.left = getTreeFromInOrderAndPostOrder(inOrder, postOrder, postIndex, start,inOrderIndex -1 );
        }




        return node;

    }


    public int getMinDepth(){
        if(root == null){
            return 0;
        }
        Box<Integer> minDepth = new Box<>(Integer.MAX_VALUE);
        getMinDepth(root, minDepth, 1);
        return minDepth.data;
    }

    private void getMinDepth(Node<T> node, Box<Integer> minDepth, int level){
        if(node != null){
            if(node.left == null && node.right == null){
                minDepth.data = Math.min(level, minDepth.data);
            }
            getMinDepth(node.left, minDepth, level +1);
            getMinDepth(node.right, minDepth, level +1);
        }
    }

    public boolean pathSumExists(int sum){
        Box<Boolean> box = new Box<>(false);

        pathSumExists(root, box, 0, sum);
        return box.data;
    }

    public void pathSumExists(Node<T> node, Box<Boolean> box, int currentSum, int sum){

        if(box.data == true){
            return;
        }
        if(node!= null){
            if(node.left == null && node.right == null){
                if(sum == currentSum + (Integer)node.data){
                    box.data = true;
                }
            }
            pathSumExists(node.left, box, (Integer)node.data + currentSum, sum);
            pathSumExists(node.right, box, (Integer)node.data + currentSum, sum);

        }

    }

    public void printAllPaths(){
        ArrayList<T> list = new ArrayList<>();
        printAllPaths(root, list, 0);
    }

    private void printAllPaths(Node<T> node, ArrayList<T> list, int index){
        if(node != null){
            list.add(index, node.data);
            if(node.left == null && node.right == null){
                for(int i = 0 ; i < index + 1; i ++){
                    System.out.print(list.get(i) + ", ");
                }
                System.out.println();
            }
            printAllPaths(node.left, list, index +1);
            printAllPaths(node.right, list, index +1);


        }

    }

    public ArrayList<ArrayList<T>> getAllPaths(){
        ArrayList<ArrayList<T>> allPaths = new ArrayList<>();
        ArrayList<T> list = new ArrayList<>();
        getAllPaths(root, list,allPaths, 0);
        return allPaths;
    }

    private void getAllPaths(Node<T> node, ArrayList<T> list, ArrayList<ArrayList<T>> allPaths,  int index){
        if(node != null){
            list.add(index, node.data);

            if(node.left == null && node.right == null){
                ArrayList<T> path = new ArrayList<>();
                for(int i = 0 ; i < index + 1; i ++){
                    path.add(list.get(i));
                    //System.out.print(list.get(i) + ", ");
                }
                allPaths.add(path);
            }
            getAllPaths(node.left, list,allPaths, index +1);
            getAllPaths(node.right, list,allPaths, index +1);


        }

    }

    public void populateNextRight(){
        if(root == null){
            return;
        }
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        Node<T> prev = null;
        while( !queue.isEmpty() ){
            Node<T> node = queue.remove();
            if(node != null){
                if(prev != null){
                    prev.nextRight = node;
                }
                prev = node;
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
                prev = null;
            }
        }
    }

    // endregion


}
