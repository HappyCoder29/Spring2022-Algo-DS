package edu.northeastern.ashish;

public class NodeFCNN <T>{
    public T data;
    public Node<T> firstChild;
    public Node<T> nextNeighbour;

    public NodeFCNN( T data){
        this.data = data;
    }

}
