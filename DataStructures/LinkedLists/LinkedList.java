package LinkedLists;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.HashSet;

public class LinkedList {
    private Node head;
    private Node tail;
    private int length;

    public LinkedList(int value) {
        Node node = new Node(value);
        this.head = node;
        this.tail = node;
        this.length = 1;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = tail.next;
        }
        length++;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (head == null && tail == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node removeLast() {
        if (length == 0) return null;
        Node removedNode = null;
        Node previous = head;

        if (head == tail) {
            removedNode = tail;
            head = null;
            tail = null;
        } else {
            while (previous.next != tail) {
                previous = previous.next;
            }
            removedNode = tail;
            tail = previous;
            tail.next = null;
        }
        length--;
        return removedNode;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node removedNode = null;
        if (head == tail) {
            removedNode = tail;
            head = null;
            tail = null;
        } else {
            removedNode = head;
            head = head.next;
        }
        removedNode.next = null;
        length--;
        return removedNode;
    }

    public Node get(int index) {
        if (index < 0 || index > length) return null;
        Node indexNode = head;
        for (int i = 0; i < index; i++) {
            indexNode = indexNode.next;
        }
        return indexNode;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp == null) return false;
        temp.value = value;
        return true;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
        if (index == 0) {
            prepend(value);
            return true;
        }
        if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node prev = get(index - 1);
        newNode.next = prev.next;
        prev.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        Node removedNode = get(index);
        if (removedNode == null) return null;
        if (index == 0) return removeFirst();
        if (index == length) return removeLast();
        Node prev = get(index - 1);
        prev.next = prev.next.next;
        length--;
        return removedNode;
    }

    public void reverse() {
        if (length == 0) return;
        tail = head;
        Node prev = null;
        Node curr = head;
        Node next = head.next;

        while (next != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }
        curr.next = prev;
        head = curr;
    }


    //    Problem 1 Middle of linked list
    public Node findMiddleNode() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // LL: Has Loop

    public Boolean hasLoop() {
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow==fast) {
                return true;
            }
        }
        return false;
    }
    public void partitionList(int x) {
//        Node temp = head;
//        Node small = null;
//        Node great = null;
//        Node firstGreat = null;
//        Node greatPrev = null;
//        Node smallPrev = null;
//
//        while(temp!=null) {
//            if(temp.value<x){
//                if (small!=null) smallPrev=small;
//                small = temp;
//                if(smallPrev!=null) {
//                    smallPrev.next = small;
//                }
//            }
//            if(temp.value>=x) firstGreat = temp;
//            if(temp.value>=x){
//                if (great!=null) greatPrev=great;
//                great = temp;
//                if(greatPrev!=null) {
//                    greatPrev.next = great;
//                }
//            }
//            temp=temp.next;
//        }
//        small.next=firstGreat;
//        great.next=null;
        if (head == null) return;

        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);
        Node prev1 = dummy1;
        Node prev2 = dummy2;
        Node current = head;

        while (current != null) {
            if (current.value < x) {
                prev1.next = current;
                prev1 = current;
            } else {
                prev2.next = current;
                prev2 = current;
            }
            current = current.next;
        }

        prev2.next = null;
        prev1.next = dummy2.next;

        head = dummy1.next;
    }

    public void removeDuplicates() {
        HashSet<Integer> hashSet = new HashSet<>();
        Node curr=head;
        Node prev=null;
        while(curr!=null){
            if(!hashSet.contains(curr.value)) {
                hashSet.add(curr.value);
                prev=curr;
                curr=curr.next;
            }else{
              prev.next=curr.next;
              curr=curr.next;
              length--;

            }
        }
    }
    public int binaryToDecimal(){
        int num = 0;
        Node temp = head;
        for(int i = length-1; i>=0; i--){
            num = (int) (Math.pow(2,i)*temp.value + num);
            temp = temp.next;
        }
        return num;
    }
//TODO reverse between function
    public class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }
}
