package edu.northeastern.ashish;

import java.util.ArrayList;

public class NodeBPlusTree <T>{

    public T data;
    public ArrayList<NodeBPlusTree<T>> children;

    public NodeBPlusTree(T data){
        this.data = data;
        this.children = new ArrayList<>();
    }
}
