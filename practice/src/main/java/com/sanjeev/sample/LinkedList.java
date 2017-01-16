package com.sanjeev.sample;

public class LinkedList {
    Node head = null;;
    int size;

    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }

    /**
     * @param i
     */
    public Node add(int data) {
        if (null == head) {
            head = new Node(data);
            size++;
            return head;
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = new Node(data);
        size++;
        return head;
    }

    public void print() {
        print(null);
    }

    /**
     * 
     */
    public void print(Node start) {
        StringBuilder sb = new StringBuilder();
        sb.append("[ ");
        Node temp = (null == start) ? head : start;
        while (temp != null) {
            sb.append(temp.data).append(", ");
            temp = temp.next;
        }
        sb.replace(sb.length() - 2, sb.length(), "");
        sb.append("]");
        System.out.println("List : " + sb.toString());
    }

    /**
     * @return
     * 
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 
     */
    public void printReverse() {
        Node next = null;
        Node current = head;
        Node previous = null;
        while (current != null) {
            next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        print(previous);
    }

    /**
     * @param i
     * @param j
     */
    public void swapNodesWithoutChangingData(int x, int y) {
        Node node = head;
        Node xPrev = head, xCurrent = null, xNext = null, yPrev = head, yCurrent = null, yNext = null;
        while (node != null) {
            if (node.data == x) {
                xCurrent = node;
                xNext = node.next;
            } else if (node.data == y) {
                yCurrent = node;
                yNext = node.next;
            }
            xPrev = node;
            yPrev = node;
            node = node.next;
        }
        xPrev.next = yCurrent;
        yCurrent.next = xCurrent;
        xNext.next = yNext;
    }

    /**
     * 
     */
    public void reverseUsingRecursion() {
        Node prev = head, current = head;
        reverseUsingRecursion(prev, current);
    }

    /**
     * @param prev
     * @param current
     */
    private void reverseUsingRecursion(Node prev, Node current) {

        if (current.next != null) {
            prev = current;
            current = current.next;
            reverseUsingRecursion(prev, current);
        }

        if (current.next == null) {
            head = prev;
            current = head;
            System.out.println(current.data);
        }
        current.next = prev;
    }
}