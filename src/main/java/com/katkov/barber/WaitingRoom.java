package com.katkov.barber;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @author Igor Katkov
 */
class WaitingRoom {
    private static final int WAITING_ROOM_CAPACITY = 10;
    private Queue<Customer> queue = new ArrayBlockingQueue<Customer>(WAITING_ROOM_CAPACITY);

    public boolean sit(Customer customer) {
        return queue.offer(customer);
    }

    public boolean hasEmptySeats() {
        return queue.size() < WAITING_ROOM_CAPACITY;
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public Customer inviteNextCustomer() {
        return queue.poll();
    }

    @Override
    public String toString() {
        return "WaitingRoom{" +
                "queue=" + queue +
                '}';
    }
}
