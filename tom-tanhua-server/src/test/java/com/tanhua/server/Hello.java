package com.tanhua.server;

import java.util.Arrays;

public class Hello {
    public static void main(String[] args) {
//        int[] ha = new int[]{1,2,3};
//        int result = haha(ha);
//        int one = 1;
//        int two = 2;
//        int anotherOne = 1;
        double pow = Math.pow(2, 0);
        System.out.println(pow);
//        String b = new String("111");
//        b = b.replace('1', '0');
//        System.out.println(b);
//        System.out.println(one^anotherOne^two^3^3);
    }

    private static int haha(int[] A) {
        if (null == A) {
            return 1;
        }

        Arrays.sort(A);

        int len = A.length;
        int first = A[0];
        int end = A[len - 1];

        if (first > 1 || end < 1) {
            return 1;
        } else {

            int firstPositiveIndex = findFirstPositiveIndex(A);
            int result = 1;
            boolean[] marks = new boolean[end+1];
            for (int i = firstPositiveIndex; i < A.length; i++) {
                marks[A[i]] = true;
            }
            for (int i = 1; i < marks.length; i++) {
                if (false == marks[i]) {
                    result = i;
                    break;
                }
                if (marks[marks.length - 1]) {
                    result = marks.length;
                }
            }
            return result;
        }
    }

    private static int findFirstPositiveIndex(int[] ha) {
        int start = 0;
        int end = ha.length - 1;

        int middle = start + (end-start)/2;

        while (start < middle) {
            if (ha[middle] > 0) {
                end = middle;
            } else if (ha[middle] == 0) {
                return middle++;
            } else {
                start = middle;
            }

            middle = start + (end - start) / 2;
        }

        return middle>0 ? middle: middle++;
    }
}
