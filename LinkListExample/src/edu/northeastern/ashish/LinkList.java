package edu.northeastern.ashish;

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







}

