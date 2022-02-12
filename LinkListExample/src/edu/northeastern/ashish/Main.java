package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {

//        LinkList<Integer> list = createListWithRandomPointers();
//
//        Node<Integer> copy = list.createCopyWithRandomPointer();
//
//        Node<Integer> node1 = getNode1();
//        Node<Integer> node2 = getNode2();
//
//        Node<Integer> merged = mergeSortedNodes(node1, node2);
//        printNode(merged);
//        System.out.println();



        LinkList<Integer> list = new LinkList<>();
        list.add(1,true);
        list.add(2,false);
        list.add(3,false);
        list.add(4,false);
        list.add(5,false);
        list.add(6,false);
        list.add(7,false);
        list.add(8,false);
        list.add(9,false);

        list.zipMerge();
        list.printList();
//
//        list.rotateList(3);
//        list.printList();


//
//        list.printList();
//        Node<Integer> startOfCycleNode = list.findStartOfCycle();
//
//        if(startOfCycleNode == null){
//            System.out.println("No Cycle");
//        }else{
//            System.out.println(startOfCycleNode.data);
//        }

//        System.out.println(list.containsCycle());
//
//
     //   LinkList<Integer> list = createListWithCycle();
//        //System.out.println(list.containsCycle());
//        Node<Integer> startOfCycleNode = list.findStartOfCycle();
//        if(startOfCycleNode == null){
//            System.out.println("No Cycle");
//        }else{
//            System.out.println(startOfCycleNode.data);
//        }

        //System.out.println(list.findNumberOfNodesOutsideCycle());

     //   list.breakCycle();
        //System.out.println(list.findNumberOfNodesOutsideCycle());

    //    Node<Integer> thirdFromLast = list.getKthFromEndPart2(3);
//        if(thirdFromLast == null){
//            System.out.println("No Kth from end");
//        }else{
//            System.out.println(thirdFromLast.data);
//        }
//
//        list.add(10, false);
//        Node<Integer> midOfList = list.getMidOfList();
//        if(midOfList == null){
//            System.out.println("null list");
//        }else{
//            System.out.println(midOfList.data);
//        }

    }

    private static LinkList<Integer> createListWithCycle(){
        LinkList<Integer> list = new LinkList<>();
        list.add(1,false);
        list.add(2,false);
        list.add(3,false);
        list.add(4,false);
        list.add(5,false);
        list.add(6,false);
        list.add(7,false);
        list.add(8,false);
        list.add(9,false);

        Node<Integer> three = list.head.next.next;
        list.tail.next = three;

        return list;
    }


    private static LinkList<Integer> createListWithRandomPointers(){
        LinkList<Integer> list = new LinkList<>();
        list.add(1,false);
        list.add(2,false);
        list.add(3,false);
        list.add(4,false);

        Node<Integer> one = list.head;
        Node<Integer> two = list.head.next;
        Node<Integer> three = list.head.next.next;
        Node<Integer> four = list.head.next.next.next;


        one.random = three;
        two.random = four;
        three.random = three;
        four.random = three;

        return list;
    }

    private static void printNode(Node<Integer> node){
        Node<Integer> temp = node;
        while (temp!= null){
            System.out.print(temp.data + "-> ");
            temp = temp.next;
        }
        System.out.println("Null");
    }

    private static Node<Integer> getNode1(){
        Node<Integer> node = new Node<>(1);
        node.next = new Node<>(3);
        node.next.next = new Node<>(5);
        node.next.next.next = new Node<>(7);
        node.next.next.next.next = new Node<>(8);
        node.next.next.next.next.next = new Node<>(9);
        node.next.next.next.next.next.next = new Node<>(10);

        return node;
    }

    private static Node<Integer> getNode2(){
        Node<Integer> node = new Node<>(2);
        node.next = new Node<>(4);
        node.next.next = new Node<>(6);
        node.next.next = new Node<>(8);
        return node;
    }


    private static Node<Integer> mergeSortedNodes(Node<Integer> node1, Node<Integer> node2){

        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }

        Node result = null;

        if(node1.data < node2.data){
            result = node1;
            result.next = mergeSortedNodes(node1.next, node2);
        }else{
            result = node2;
            result.next = mergeSortedNodes(node1, node2.next);
        }
        return result;
    }


}
