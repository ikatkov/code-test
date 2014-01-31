package com.katkov;

import java.util.Scanner;

public class SpiralArray {

    public static void main(String... args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Array size?:");
        int dimenson = in.nextInt();
        int array[][] = new int[dimenson][dimenson];


        spiralArray(array, 0, 0);
        printArray(array);

    }

    private static void spiralArray(int[][] array, int counter, int minI) {
        if (minI >= array.length / 2) {
            if (array.length % 2 == 1) { array[array.length / 2][array.length / 2] = counter; }
            return;
        }

        //-> (right)
        int[] row = array[minI];
        for (int i = minI; i < row.length - minI; i++) {
            row[i] = counter++;
        }
        // down, stops short of the last cell down
        int column = array.length - 1 - minI;
        for (int j = 1 + minI; j < column; j++) {
            array[j][column] = counter++;
        }

        //<- (left)
        row = array[array.length - 1 - minI];
        for (int i = row.length - 1 - minI; i >= minI; i--) {
            row[i] = counter++;
        }

        // up, stops short of the last cell up
        column = minI;
        for (int j = array.length - 2 - minI; j >= minI + 1; j--) {
            array[j][column] = counter++;
        }

        //could be loop instead of recursion
        spiralArray(array, counter, ++minI);

    }

    private static void printArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(String.format("%02d", array[i][j]));
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }
}
