package edu.northeastern.ashish;

import java.util.ArrayList;

public class Node {
    public String name;
    public boolean isVisited = false;
    public ArrayList<Edge>  neighbours;
    public Node parent;
    public int rank;
    public int color;


    public Node(String name){
        this.name = name;
        this.isVisited = false;
        this.neighbours = new ArrayList<>();
        this.parent = this;
        this.rank = 1;
    }




}
