package com.zjx.learning.lesson5.question2;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Solution2 {


    private Map<Character,Integer> map;

    public Solution2() {
        map = new HashMap<>(4);
        map.put('A', 1);
        map.put('C', 2);
        map.put('G', 3);
        map.put('T', 4);
    }


    public int[] solution(String s,int[] P,int[] Q) {

        int[] result = new int[P.length];

        /*if (isAllSameLetters(s)) {
            char first = s.charAt(0);
            Integer integer = map.get(first);
            for (int i = 0; i < result.length; i++) {
                result[i] = integer;
            }
            return result;
        }*/

        TreeSet<Integer> allSegment = getAllSegment(P, Q);

        int[] allSegmentMinValue = getAllSegmentMinValue(s, allSegment);



//        long time = System.currentTimeMillis();

        for (int i = 0; i < P.length; i++) {
            int start = P[i];
            int end = Q[i];
            System.out.println();
            if (start == end) {
                char currentChar = s.charAt(start);
                Integer integer = map.get(currentChar);
                result[i] = integer;
                continue;
            }

            int minValue = getMinValue(allSegmentMinValue, start, end);

            result[i] = minValue;


        }

//        System.out.println("getMinValue1 time:" + (System.currentTimeMillis() - time));


        return result;
    }

    private int getMinValue(int[] entries, int start, int end) {

        int minValue = 4;

        if (entries.length == 1) {
            return entries[0];
        }

        if (start == end) {
            return entries[start];
        }

        for (int i = start; i < end; i++) {

            if (entries[i] == 0) {
                continue;
            }

            if (minValue > entries[i]) {
                minValue = entries[i];
            }
        }


        return minValue;
    }


    private int[] getAllSegmentMinValue(String s, TreeSet<Integer> allSegment) {

//        long time = System.currentTimeMillis();

        int[] result = new int[s.length()];

        if (allSegment.size() == 0) {
            return result;
        }

        Iterator<Integer> allSegmentIterator = allSegment.iterator();

        Integer start = allSegmentIterator.next();
        if (allSegment.size() == 1) {
            char currentChar = s.charAt(start);
            Integer integer = map.get(currentChar);
            result[start] = integer;
        }

        while (allSegmentIterator.hasNext()) {
            Integer end = allSegmentIterator.next();
            int minValue = getMinValue(s, start, end);
            result[start] = minValue;
            start = end;
        }


        return result;
    }

    private int getMinValue(String s,int start,int end) {

//        long time = System.currentTimeMillis();

        int minValue = 4;
        String targetString = s.substring(start, end + 1);

        /*if (isAllSameLetters(targetString)) {
            char first = s.charAt(0);
            minValue = map.get(first);
            return minValue;
        }*/

        for (int i = 0; i < targetString.length(); i++) {
            Integer currentValue = map.get(targetString.charAt(i));
            System.out.println();
            if (currentValue == 1) {
                return 1;
            }
            if (currentValue < minValue) {
                minValue = currentValue;
            }
        }

//        System.out.println("getMinValue2 time:" + (System.currentTimeMillis() - time));

        return minValue;
    }

    private TreeSet<Integer> getAllSegment(int[] P,int[] Q) {

//        long time = System.currentTimeMillis();
        TreeSet<Integer> returnSet = new TreeSet<>();

        for (int a : P) {
            returnSet.add(a);
        }

        for (int a : Q) {
            returnSet.add(a);
        }

//        System.out.println("getAllSegment time:" + (System.currentTimeMillis() - time));
        return returnSet;

    }

    private boolean isAllSameLetters(String s) {
        String a = "["+s.charAt(0)+"]*";
        return s.matches(a);
    }

    @Test
    public void testRegex() {
        StringBuilder sb = new StringBuilder();
        System.out.println();
        for (int i = 0; i < 5000; i++) {
            sb.append("G");
        }
        String s = sb.toString();
        long time = System.currentTimeMillis();
        isAllSameLetters(s);
        System.out.println(System.currentTimeMillis() - time);
    }


    @Test
    public void testNormal() {
        int[] P = {2,5,0};
        int[] Q = {4,5,6};
        System.out.println();

        String s = "CAGCCTA";
        int[] result = solution(s, P, Q);

        //2,4,1

        System.out.println(Arrays.toString(result));

    }

    @Test
    public void testSingleCharacterString() {
        int[] P = {0};
        int[] Q = {0};
        System.out.println();

        String s  = "A";
        int[] result = solution(s, P, Q);

        //1
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testDoubleCharacterString() {
        int[] P = {0,0,1};
        int[] Q = {0,1,1};
        System.out.println();

        String s  = "AC";
        int[] result = solution(s, P, Q);

        //1,1,2
        System.out.println(Arrays.toString(result));
        Assert.assertArrayEquals(result,new int[]{1,1,2});
    }

    @Test
    public void testAllSameLetters() {

        int size = 100000;
        int[] P = new int[size];
        int[] Q = new int[size];
        System.out.println();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append("G");
            P[i] = i;
            Q[i] = size - 1;
        }
        sb.replace(25000, 25000, "A");
        int[] result = solution(sb.toString(), P, Q);

        //
        System.out.println(Arrays.toString(result));
//        Assert.assertArrayEquals(result,new int[]{1,1,2});

    }
}
