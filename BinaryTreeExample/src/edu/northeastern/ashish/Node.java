package edu.northeastern.ashish;

import java.util.ArrayList;

public class Node <T> {
    public Node<T> left;
    public Node<T> right;
    public T data;

    public Node(T data){
        this.data = data;

    }
}
