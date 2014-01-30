package com.katkov;


import java.util.Random;
import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack {

    public static void main(String... args) {
        LFStack<String> stack = new LFStack<String>();
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new RandomStackUse(stack));
            t.setName("My stack thread " + i);
            t.start();
        }
    }

    private static class LFStack<E> implements Stack<E> {
        private volatile AtomicReference<LinkedListNode<E>> rootRef = new AtomicReference<LinkedListNode<E>>();

        @Override
        public E peek() {
            E payload = null;
            LinkedListNode<E> oldRootNode = rootRef.get();
            if (oldRootNode != null) { payload = rootRef.get().payload; }
            return payload;
        }

        @Override
        public E pop() {
            E payload = null;
            while (true) {
                LinkedListNode<E> oldRootNode = rootRef.get();
                if (oldRootNode != null) { payload = rootRef.get().payload; }
                if (rootRef.compareAndSet(oldRootNode, oldRootNode.next.get())) { break; }
                //                System.out.println("!");
            }
            return payload;
        }

        @Override
        public void push(E e) {
            LinkedListNode<E> newRootNode = new LinkedListNode<E>(e);

            while (true) {
                LinkedListNode<E> oldRootNode = rootRef.get();
                if (oldRootNode != null) { newRootNode.next.set(oldRootNode); }
                if (rootRef.compareAndSet(oldRootNode, newRootNode)) { break; }
                //                System.out.println("!");
            }
        }
    }

    private interface Stack<E> {
        public E peek();

        public E pop();

        public void push(E e);
    }


    //to be used as LinkedList chain <LinkedListNode> => <LinkedListNode> => <LinkedListNode> => null
    private static class LinkedListNode<E> {
        private E payload;
        private AtomicReference<LinkedListNode<E>> next;

        public LinkedListNode(E e) {
            payload = e;
            next = new AtomicReference<LinkedListNode<E>>();
        }
    }

    /**
     * @author katkovi
     */
    public static class RandomStackUse implements Runnable {
        private LFStack<String> stack;
        private Random rand = new Random();

        public RandomStackUse(LFStack<String> stack) {this.stack = stack;}

        @Override
        public void run() {
            long counter = 0;
            while (true) {
                if (rand.nextInt() % 3 == 0) {
                    stack.push(String.valueOf(counter++));
                    //                    System.out.println(String.format("%s pushed %d",
                    // Thread.currentThread().getName(), counter));
                }
                if (rand.nextInt() % 3 == 1) {
                    String value = stack.pop();
                    //                    System.out.println(String.format("%s pop %s",
                    // Thread.currentThread().getName(), value));
                }
                if (rand.nextInt() % 3 == 2) {
                    String value = stack.peek();
                    //                    System.out.println(String.format("%s peek %s", Thread.currentThread().getName(), value));
                }
            }
        }
    }
}
