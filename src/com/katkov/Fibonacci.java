package com.katkov;

import java.util.HashMap;
import java.util.Scanner;

public class Fibonacci {
    private static HashMap<Integer, Long> cache = new HashMap<Integer, Long>(1000);

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Fibonacci sequence element #?:");
        int item = in.nextInt();
        long ts1 = System.currentTimeMillis();
        long result = computeFibonacciWithLoop(item);
        long ts2 = System.currentTimeMillis();
        System.out.println("Result:" + result);
        System.out.println("Time spent:" + (ts2 - ts1) / 1000 + "sec");
    }


    private static long computeFibonacci(int item) {
        if (item == 0) { return 0; }
        if (item == 1) { return 1; }
        return computeFibonacci(item - 2) + computeFibonacci(item - 1);
    }


    private static long computeFibonacciWithCache(int item) {
        if (item == 0) { return 0; }
        if (item == 1) { return 1; }
        if (cache.containsKey(item)) { return cache.get(item); }
        long result = computeFibonacciWithCache(item - 2) + computeFibonacciWithCache(item - 1);
        cache.put(item, result);
        return result;
    }

    private static long computeFibonacciWithLoop(int item) {
        if (item == 0) { return 0; }
        if (item == 1) { return 1; }

        long fibN2 = 0;
        long fibN1 = 1;
        long result = 0;
        for (int i = 2; i <= item; i++) {
            result = fibN2 + fibN1;
            fibN2 = fibN1;
            fibN1 = result;
        }
        return result;
    }
}
