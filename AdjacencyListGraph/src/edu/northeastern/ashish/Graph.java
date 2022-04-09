package edu.northeastern.ashish;

import java.util.*;

public class Graph {
    public HashMap<String, Node> nodes;

    public Graph(){
        nodes = new HashMap<>();
    }

    public void addNode(String nodeName){
        nodeName = nodeName.toLowerCase();
        if( nodes.containsKey(nodeName)){
            return;
        }
        Node node = new Node(nodeName);
        nodes.put(nodeName, node);
    }

    public void addEdge(String from, String to){
        from = from.toLowerCase();
        to = to.toLowerCase();
        if(     !nodes.containsKey(from) ||
                !nodes.containsKey(to)  ){
            return;
        }

        Edge edge = new Edge(from, to);
        Node fromNode = nodes.get(from);

        fromNode.neighbours.add(edge);
    }

    public void resetVisited(){
        for (Node node : nodes.values()) {
            node.isVisited = false;
        }
    }

    public void breadthFirstSearch(String start){
        Node startNode = nodes.get(start.toLowerCase());
        if(startNode == null){
            return;
        }
        resetVisited();

        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        queue.add(null);

        while(!queue.isEmpty()){
            Node node = queue.remove();
            if(node != null){
                if(node.isVisited == true){
                    continue;
                }
                node.isVisited = true;
                System.out.print(node.name + ", ");

                for (Edge edge: node.neighbours) {
                   Node neighbour = nodes.get(edge.endNode);
                   if(neighbour.isVisited == false){
                       queue.add(neighbour);
                   }
                }
            }else{
                // Node was null which means we are at a different level
                System.out.println();
                if(queue.isEmpty()){
                    break;
                }
                queue.add(null);
            }// end of else
        }// end of while
    }// end of function

    public void depthFirst(String start){
        Node startNode = nodes.get(start.toLowerCase());
        if(startNode == null){
            return;
        }
        resetVisited();
        Stack<Node> stack = new Stack<>();
        stack.push(startNode);
        while(!stack.isEmpty()){
            Node node = stack.pop();
            if(node.isVisited == true){
                continue;
            }
            node.isVisited = true;
            System.out.println(node.name);
            // Add all neighbours in the stack
            for (Edge edge: node.neighbours) {
                Node neighbour = nodes.get(edge.endNode);
                if(neighbour.isVisited == false){
                    stack.push(neighbour);
                }
            }// end of for
        }// end of while
    }// end of function


    public void printAllPaths(String source, String dest){
        source = source.toLowerCase();
        dest = dest.toLowerCase();

        if(!nodes.containsKey(source) || !nodes.containsKey(dest)){
            return;
        }

        LinkedList<String> path = new LinkedList<>();
        printAllPaths(path, source, dest);

    }

    private void printAllPaths(LinkedList<String> path, String current, String dest){
        if(path.contains(current)){
            return;
        }

        if(dest.compareTo(current) == 0){// we have reached our destination
            for (String str : path) {
                System.out.print(str + "->");
            }
            System.out.println(dest);
        }

        path.add(current);

        Node node = nodes.get(current);
        for (Edge edge : node.neighbours) {
            if( !path.contains(edge.endNode)){
                printAllPaths(path, edge.endNode, dest);
            }
        }

        // Remove the current node since we have already visited all its neighbours
        path.remove(current);
    }


    public boolean containsCycleUnionFind(){

        LinkedList<Edge>  edges = new LinkedList<>();

        for (Node node : nodes.values()) {
            node.parent = node;
            node.rank = 1;
            for (Edge edge : node.neighbours) {
                edges.add(edge);
            }
        }

        for (Edge edge : edges) {
            Node node1 = nodes.get(edge.startNode);
            Node node2 = nodes.get(edge.endNode);

            if(node1.parent == node2.parent){
                return true;
            }
            union(node1, node2);

        }
        return false;

    }


    private void union(Node node1, Node node2){

        Node parent1 = findParent(node1);
        Node parent2 = findParent(node2);
        if(parent1 == parent2){
            return;
        }
        if(parent1.rank >= parent2.rank){
            parent1.rank ++;
            parent2.parent = parent1;
        }else{
            parent1.parent = parent2;
            parent2.rank ++;
        }



    }

    private Node findParent(Node node){
        if(node == node.parent){
            return node;
        }
        return findParent(node.parent);
    }

    private ArrayList<Node> getNeighbours(Node node){
        ArrayList<Node> neighbours = new ArrayList<>();
        for (Edge edge : node.neighbours) {
            neighbours.add(nodes.get(edge.endNode));
        }
        return neighbours;
    }

    public void topologicalSort(Node node){
        Stack<Node> stack = new Stack<>();
        topologicalSort(node, stack);

        while(!stack.isEmpty()){
            System.out.println(stack.pop().name + ", ");
        }
        System.out.println();
    }

    private void topologicalSort(Node node, Stack<Node> stack){
        for (Node n : getNeighbours(node)) {
           if(!stack.contains(n)){
               topologicalSort(n, stack);
           }
        }
        stack.push(node);
    }

}
