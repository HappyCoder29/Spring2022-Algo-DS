package edu.northeastern.ashish;

import java.util.ArrayList;

public class LinkList <T> {
    public Node<T> head;
    public Node<T> tail;
    public int count;  //O(1)
    public LinkList(){
        head = null;
        tail = null;
        count = 0;
    }

    public void add(T data, boolean addToHead){
        Node<T> add = new Node<>(data);
        count ++;
        if(head == null){
            head = add;
            tail = add;
            return;
        }
        if(addToHead){
            add.next = head;
            head = add;
        }else{
            tail.next = add;
            tail = add;
        }
    }

    //O(n)
    public int size(){
        int size = 0;
        if(head == null){
            return size;
        }

        Node<T> temp = head;
        while(temp != null){
            temp = temp.next;
            size++;
        }
        return size;
    }

    public void printList(){
        if(head == null){
            System.out.println("[]");
        }

        Node<T> temp = head;

        System.out.print("[ ");
        while(temp != null){
            System.out.print(temp.data + " -> ");
            temp = temp.next;
        }
        System.out.println("Null ]");

    }


    // O(n)
    public void reverse(){
        if(head == null || head.next == null){
            return;
        }

        Node<T> back = null;
        Node<T> mid = head;
        Node<T> front = head.next;

        while(front != null){
            mid.next = back;
            back = mid;
            mid = front;
            front = front.next;
        }
        mid.next = back;

        // we need to reassign head and tail
        back = head;
        head = tail;
        tail = back;

    }

    private Node<T> reverse(Node<T> node){
        if(node == null || node.next == null){
            return node;
        }

        if(node.next.next == null){
            Node<T> second = node.next;
            Node<T> first = node;
            first.next = null;
            second.next = first;
            return second;
        }

        Node<T> back = null;
        Node<T> mid = node;
        Node<T> front = node.next;

        while(front != null){
            mid.next = back;
            back = mid;
            mid = front;
            front = front.next;
        }
        mid.next = back;
        node = mid;

        return node;

    }

    //O(n)
    public boolean containsCycle(){
        if(head == null || head.next == null){
            return false;
        }
        Node<T> back = head;
        Node<T> front = head;

        while(front != null && front.next != null){
            front = front.next.next;
            back = back.next;
            if(back == front){
                return true;
            }
        }
        return false;
    }


   // O(2n) ~= O(n)
    public Node<T> findStartOfCycle(){
        if(head == null || head.next == null){
            return null;
        }
        Node<T> back = head;
        Node<T> front = head;

        while(front != null && front.next != null){
            front = front.next.next;
            back = back.next;
            if(back == front){
                break;
            }
        }
        // At this point either front or front.next is null (no cycle)
        if(front == null || front.next == null){
            return null;
        }

        // There is definitely a cycle
        front = head;

        while(front != back){
            front = front.next;
            back = back.next;
        }

        return front;
    }

    // O(2n) ~= O(n);
    public int findNumberOfNodesInsideCycle(){
        Node<T> startOfCycle = findStartOfCycle();

        if(startOfCycle == null){
            return 0;
        }

        Node<T> temp = head;
        int k = 0 ;
        while(temp != startOfCycle){
            temp = temp.next;
            k++;
        }


        return count - k;
    }

    // O(2n) ~= O(n);
    public int findNumberOfNodesOutsideCycle(){
        Node<T> startOfCycle = findStartOfCycle();

        if(startOfCycle == null){
            return 0;
        }

        Node<T> temp = head;
        int k = 0 ;
        while(temp != startOfCycle){
            temp = temp.next;
            k++;
        }


        return k;
    }


    /// If tail is available then all we have to do is say tail.next = null

    public void breakCycle(){
        Node<T> startOfCycle = findStartOfCycle();
        if(startOfCycle == null){
            return;
        }

        Node<T> temp = startOfCycle;

        while(temp.next != startOfCycle ){
            temp = temp.next;
        }

        temp.next = null;

    }

    // Assumes there is no cycle
    public Node<T> getKthFromEnd(int k){
        if( k <=0){
            return null;
        }
        if(head == null){
            return null;
        }

        Node<T> back = head;
        Node<T> front = head;
        for(int i = 0 ; i < k ; i ++){
            front = front.next;
            if(front == null){
                return null;
            }
        }

        while(front != null){
            front = front.next;
            back = back.next;
        }

        return back;
    }

    public Node<T> getKthFromEndPart2(int k){
        if( k <=0){
            return null;
        }
        if(head == null){
            return null;
        }

        int steps = count - k;

        if(steps < 0){
            return null;
        }
        Node<T> temp = head;
        for(int i = 0 ; i < steps; i ++){
            temp = temp.next;
        }

        return temp;
    }

    public Node<T> getMidOfList(){
        if(head == null || head.next == null){
            return head;
        }

        Node<T> back = head;
        Node<T> front = head;

        while(front != null && front.next != null){
            front = front.next.next;
            if(front == null){
                break;
            }
            back = back.next;
            // Assuming no cycle
        }

        return back;

    }

    public void reverseList(){
        if(head == null || head.next == null){
            return;
        }
        Node<T> temp = head;
        reverseList(null, head, head.next);
        tail = temp;
    }

    private void reverseList(Node<T> back, Node<T> mid, Node<T> front){
        if(front == null){
            mid.next = back;
            head = mid;
            return;
        }
        mid.next = back;
        back = mid;
        mid = front;
        front = front.next;
        reverseList(back, mid, front);
    }

    public boolean isPalindrome(){
        if(head == null || head.next == null){
            return true;
        }

        Node<T> mid = getMidOfList();

        Node<T> secondHalf = mid.next;
        Node<T> firstHalf = head;
        mid.next = null;

        // Reverse second half
        secondHalf =  reverse(secondHalf);

        Node<T> temp1 = firstHalf;
        Node<T> temp2 = secondHalf;

        boolean bPalindrome = true;

        while(temp1 != null && temp2 != null){
            if( !temp1.data.equals(temp2.data)){
                bPalindrome = false;
                break;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        // put back the list

        secondHalf = reverse(secondHalf);
        mid.next = secondHalf;



        return  bPalindrome;

    }

    public void swapNodesInPair(){
        if(head == null || head.next == null){
            return;
        }
        Node<T> temp = head;
        for(int i = 0 ; i < this.count; i = i + 2){
            T data = temp.data;
            if(temp.next != null){
                temp.data = temp.next.data;
                temp.next.data = data;
                temp = temp.next.next;
            }
        }
    }

    public void swapNodesInPairWoDataChange(){
        if(head == null || head.next == null){
            return;
        }

        Node<T> temp = head;
        ArrayList<Node<T>> list = new ArrayList<>();
        ArrayList<Node<T>> list1 = new ArrayList<>();
        for(int i = 0 ; i < this.count; i = i + 2){
            if(temp.next == null){
                list.add(temp);
            }else{
                Node<T> second = temp.next.next;
                temp.next.next = null;
                list.add(temp);
                temp = second;
            }
        }

        // AT this point our list should have pairs of 2

        for(int i = 0 ; i < list.size(); i ++){
            Node<T> node = list.get(i);
            node = reverse(node);
            list1.add(node);
        }

        for(int i = 0 ; i < list1.size() -1 ; i ++){
            Node<T> node = list1.get(i);
            node.next.next = list1.get(i+1);
        }

        head = list1.get(0);



        System.out.println();
    }


    public void reverseInKGroup(int k ){
        if(head == null || head.next == null){
            return;
        }
        if( k <= 1){
            return;
        }
        if(k >= count){
            head = reverse(head);
            return;
        }


        Node<T> temp = head;
        ArrayList<Node<T>> list = new ArrayList<>();
        for(int i = 0 ; i < this.count; i = i + k){
            Node<T> first = temp;
            for(int j = 0 ; j < k -1 ; j ++){
                if(temp == null){
                    break;
                }
                temp = temp.next;
            }
            list.add(first);
            if(temp == null){
                break;
            }
            Node<T> second = temp.next;
            temp.next = null;
            temp = second;
        }
        ArrayList<Node<T>> list1 = new ArrayList<>();
        for(int i = 0; i < list.size(); i ++){
           Node<T> node = list.get(i);
           node = reverse(node);
           list1.add(node);
        }

       for(int i = 0 ; i < list1.size()-1 ; i ++){
           Node<T> node = list1.get(i);
           while(node.next != null){
               node = node.next;
           }
           node.next = list1.get(i+1);

       }
       head = list1.get(0);
       System.out.println();
    }


    public void reverseInK(int k){
        if(head == null || head.next == null){
            return;
        }
        if( k <= 1){
            return;
        }
        if(k >= count){
            head = reverse(head);
            return;
        }

        head = reverseInK(head, k);

    }

    private Node<T> reverseInK(Node<T> node, int k){
        if(node == null || node.next == null){
            return node;
        }
        Node<T> temp = node;
        for(int i = 0 ; i < k -1 ; i ++){
            if(temp == null){
                break;
            }
            temp = temp.next;
        }
        if(temp == null){
            return reverse(node);
        }
        Node second = temp.next;
        temp.next = null;
        node = reverse(node);

        temp = node;
        while(temp.next!= null){
            temp = temp.next;
        }
        temp.next = reverseInK(second, k);
        return node;
    }

    public void rotateList(int k){
        if(head == null || head.next == null){
            return;
        }
        k = k % count;
        if( k <= 0){
            return;
        }

        Node<T> kThFromEnd = getKthFromEnd(k+1);

        Node<T> second = kThFromEnd.next;
        kThFromEnd.next = null;

        Node<T> temp = second;
        while(second.next != null){
            second = second.next;
        }
        second.next = head;
        head = temp ;
    }

    public Node<T> createCopyWithRandomPointer(){
        /*
        1. Create copy inside the original list
        2. Populate the random pointer for copy nodes
        3. Break the original and copy
        4. return copy node
        */
        Node<T> temp = head;
        for(int i = 0 ; i < count; i ++){
            Node<T> copy = new Node<>(temp.data);
            copy.next = temp.next;
            temp.next = copy;
            temp = temp.next.next;
        }

        // AT this point we have copy nodes inside original list

        // Populate random pointers for the copy nodes
        temp = head;
        for(int i = 0 ; i < count; i ++){
            Node<T> copy = temp.next;
            if(temp.random != null){
                copy.random = temp.random.next;
            }
            temp = temp.next.next;
        }

        printList();

        Node<T> headCopy = head.next;
        temp = head;
        for(int i = 0 ; i < count; i ++){
            Node<T> copy = temp.next;
            temp.next = copy.next;
            if(temp.next == null){
                copy.next = null;
            }else{
                copy.next = copy.next.next;
            }
            temp = temp.next;
        }


        System.out.println();
        return headCopy;

    }

    public void zipMerge(){
        if(head == null || head.next == null){
            return;
        }

        // Break List to half
        Node<T> mid = getMidOfList();
        Node<T> second = mid.next;
        mid.next = null;
        second = reverse(second);
        head = zipMerge(head, second, true);

    }

    private Node<T> zipMerge( Node<T> node1, Node<T> node2, boolean bSwitch){
        if(node1 == null){
            return node2;
        }
        if(node2 == null){
            return node1;
        }

        Node result = null;

        if(bSwitch){
            result = node1;
            result.next = zipMerge(node1.next, node2, false);
        }else{
            result = node2;
            result.next = zipMerge(node1, node2.next, true);
        }
        return result;
    }

}

