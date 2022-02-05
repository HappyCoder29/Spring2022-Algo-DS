package edu.northeastern.ashish;

public class Main {

    public static void main(String[] args) {
//        LinkList<Integer> list = new LinkList<>();
//        list.add(1,true);
//        list.add(2,false);
//        list.add(3,false);
//        list.add(4,false);
//        list.add(5,false);
//        list.add(6,false);
//        list.add(7,false);
//
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
        LinkList<Integer> list = createListWithCycle();
//        //System.out.println(list.containsCycle());
//        Node<Integer> startOfCycleNode = list.findStartOfCycle();
//        if(startOfCycleNode == null){
//            System.out.println("No Cycle");
//        }else{
//            System.out.println(startOfCycleNode.data);
//        }

        //System.out.println(list.findNumberOfNodesOutsideCycle());

        list.breakCycle();
        //System.out.println(list.findNumberOfNodesOutsideCycle());

        Node<Integer> thirdFromLast = list.getKthFromEndPart2(3);
//        if(thirdFromLast == null){
//            System.out.println("No Kth from end");
//        }else{
//            System.out.println(thirdFromLast.data);
//        }

        list.add(10, false);
        Node<Integer> midOfList = list.getMidOfList();
        if(midOfList == null){
            System.out.println("null list");
        }else{
            System.out.println(midOfList.data);
        }

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


}
