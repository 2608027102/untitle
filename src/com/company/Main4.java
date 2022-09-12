package com.company;

import java.util.Arrays;

public class Main4 {


    public static void main(String[] args) {

        //0 1 2 3 4 5 6
        //0 1 2
        //1 3 4
        //2 5 6
        //n 2n+1 2n + 2

        double[] tree = new double[]{0, 0, 0, 1.2, 3.4, 4.5};

        // 11
        // 22 32
        // 43 53 63 73
        // 2 ^ n - 1
        //

        for (int i = tree.length - 1; i > 0; i--) {
            if (i % 2 == 0) {
                int parentIndex = (i - 2) / 2;
                tree[parentIndex] = tree[i] + tree[i - 1];
                i--;
            } else {
                int parentIndex = (i - 1) / 2;
                tree[parentIndex] = tree[i];
            }
        }

        System.out.println(Arrays.toString(tree));
    }


}
