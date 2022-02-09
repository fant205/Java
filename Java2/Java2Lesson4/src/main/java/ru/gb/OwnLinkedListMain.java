package ru.gb;

public class OwnLinkedListMain {
    public static void main(String[] args) {
        OwnLinkedList<Integer> integerOwnLinkedList = new OwnLinkedList<>();
        integerOwnLinkedList.addNode(1);
        integerOwnLinkedList.addNode(2);
        integerOwnLinkedList.addNode(3);
        integerOwnLinkedList.addNode(4);
        integerOwnLinkedList.addNode(5);
        integerOwnLinkedList.display();

        //add
        //test 1
        integerOwnLinkedList.addNode(0, 55);
        integerOwnLinkedList.display();

        integerOwnLinkedList.remove(0);
        integerOwnLinkedList.display();

        //test 2
        integerOwnLinkedList.addNode(1, 55);
        integerOwnLinkedList.display();

        integerOwnLinkedList.remove(1);
        integerOwnLinkedList.display();

        //test 3
        integerOwnLinkedList.addNode(4, 55);
        integerOwnLinkedList.display();

        integerOwnLinkedList.remove(4);
        integerOwnLinkedList.display();


    }
}
