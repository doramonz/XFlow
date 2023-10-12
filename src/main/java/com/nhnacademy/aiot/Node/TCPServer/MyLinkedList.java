package com.nhnacademy.aiot.Node.TCPServer;

public class MyLinkedList {
    private Node head;
    private int size;

    // LinkedList에 저장할 각각의 요소를 나타내는 Node 클래스
    private class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
        }
    }

    // LinkedList 생성자
    public MyLinkedList() {
        head = null;
        size = 0;
    }

    // LinkedList의 끝에 요소 추가
    public void add(Object data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // LinkedList에서 특정 위치의 요소 가져오기
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public Object remove(int index) {
        Object data = get(index);
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        if (index == 0) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
        return data;
    }

    // LinkedList의 크기 반환
    public int size() {
        return size;
    }

    // LinkedList 출력
    public void display() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add("test");
        System.out.println(list.remove(list.size() - 1)); // 출력: test
        list.display(); // 출력: 1 -> 2 -> 3 -> null
    }
}