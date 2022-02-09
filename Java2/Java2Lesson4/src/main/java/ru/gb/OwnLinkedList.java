package ru.gb;

/**
 * 1. Сделать добавление и удаление из односвязного списка по индексу
 * @param <T>
 */
public class OwnLinkedList<T> {
    private long size;
    private Node head;
    private Node tail;

    public OwnLinkedList() {
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public void addNode(T data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
        }

        tail = newNode;
        size++;
    }

    public void addNode(int i, T data) {

        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for length %s", i, size));
        }

        Node current = head;

        if (i == 0) {
            Node newNode = new Node(data);
            head = newNode;
            head.next = current;
            return;
        }

        for (int j = 0; j < i - 1; j++) {
            current = current.next;
        }

        Node newNode = new Node(data);
        Node next = current.next;
        newNode.next = next;
        current.next = newNode;

    }

    public void remove(int i){
        if (i >= size || i < 0) {
            throw new IndexOutOfBoundsException(String.format("Index %s out of bounds for length %s", i, size));
        }

        if (i == 0) {
            head = head.next;
            return;
        }

        Node previous = head;
        for (int j = 0; j < i - 1; j++) {
            previous = previous.next;
        }

//        Node nodeForRemove = previous.next;
//        Node willBeRemove = nodeForRemove.next;
//        previous.next = willBeRemove;

        previous.next = previous.next.next;

    }

    public void display() {
        Node current = head;

        if (head == null) {
            System.out.println("Односвязный список пуст");
            return;
        }

        while (current != null) {
            System.out.println(current.data + " ");
            current = current.next;
        }

        System.out.println();
    }

    public void display(int index) {
        if (index > size) {
            System.out.println("Размер списка больше чем заданный индекс");
            return;
        }

        Node current = head;

        for (int i = 1; i <= index; i++) {
            current = current.next;
        }

        System.out.println(current.data);
    }


    private class Node {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}
