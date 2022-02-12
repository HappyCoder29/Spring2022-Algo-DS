package edu.northeastern.ashish;

public class Node <T> {

    public T data;
    public Node<T> next;
    public Node<T> random;

    public  Node(T data){
        this.data = data;
        this.next = null;
        this.random = null;
    }

}
