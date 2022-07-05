package com.artplan;

import java.util.Timer;

public class ReversString {

    private static final String STRING = "abcdifghklmnopqrstuvwxyz";
    private static final int COUNT_1000 = 1000;
    private static final int COUNT_10_000 = 10000;
    private static final int COUNT_100_000 = 100000;

    public static void main(String[] args) {

        printResults(
                STRING,
                reverseMethod(STRING),
                counterTime(COUNT_1000),
                counterTime(COUNT_10_000),
                counterTime(COUNT_100_000)
        );

    }

    private static String reverseMethod(String str){
        StringBuilder builder = new StringBuilder();
        builder.append(str);
        return builder.reverse().toString();
    }

    private static void printResults(String str, String revers, long c1000, long c10_000, long c100_000){
        System.out.println(str);
        System.out.println(revers);
        System.out.println(c1000);
        System.out.println(c10_000);
        System.out.println(c100_000);
    }

    private static long counterTime(long time){
        long timeStart = System.nanoTime();
        long count = 0;

        while (count == time){
            count++;
            reverseMethod(STRING);
        }
        long timeStop = System.nanoTime();

        return timeStop - timeStart;
    }
}
