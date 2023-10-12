package com.nhnacademy.aiot.Node.TCPServer;

public class MyMessageQueue {
    MyLinkedList myLinkedList = new MyLinkedList();

    public MyMessageQueue() {
        super();
    }

    public synchronized void push(Message data) {
        myLinkedList.add(data);
        notifyAll();
    }

    public synchronized Message pop() throws InterruptedException {
        synchronized (this) {
            while (myLinkedList.size() == 0) {
                return null;
            }
            return (Message) myLinkedList.remove(0);
        }
    }

    public synchronized int size() {
        return myLinkedList.size();
    }

    public synchronized boolean isEmpty() {
        return myLinkedList.size() == 0;
    }

    public static void main(String[] args) throws InterruptedException {
        MyMessageQueue myQueue = new MyMessageQueue();
        myQueue.push(new Message(1));
        myQueue.push(new Message(2));
        myQueue.push(new Message(3));
        myQueue.push(new Message(4));
        myQueue.push(new Message(5));
        myQueue.push(new Message(6));
        for(int i = myQueue.size();i>0;i++) {
            System.out.println(myQueue.pop().getData());
        }
    }
}
