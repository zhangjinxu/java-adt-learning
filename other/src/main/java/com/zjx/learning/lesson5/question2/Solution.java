package com.zjx.learning.lesson5.question2;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class Solution {


    private Map<Character,Integer> map;

    public Solution() {
        map = new HashMap<>(4);
        map.put('A', 1);
        map.put('C', 2);
        map.put('G', 3);
        map.put('T', 4);
    }


    public int[] solution(String s,int[] P,int[] Q) {

        int[] result = new int[P.length];

        if (isAllSameLetters(s)) {
            char first = s.charAt(0);
            Integer integer = map.get(first);
            for (int i = 0; i < result.length; i++) {
                result[i] = integer;
            }
            return result;
        }

        TreeSet<Integer> allSegment = getAllSegment(P, Q);

        TreeMap<Integer, Integer> allSegmentMinValue = getAllSegmentMinValue(s, allSegment);

        Set<Map.Entry<Integer, Integer>> entries = allSegmentMinValue.entrySet();


//        long time = System.currentTimeMillis();

        for (int i = 0; i < P.length; i++) {
            int start = P[i];
            int end = Q[i];

            if (start == end) {
                char currentChar = s.charAt(start);
                Integer integer = map.get(currentChar);
                result[i] = integer;
                continue;
            }

            /*String subString = s.substring(start, end + 1);

            if (isAllSameLetters(subString)) {
                char currentChar = subString.charAt(0);
                int minValue = map.get(currentChar);
                result[i] = minValue;
                continue;
            }*/

            int minValue = getMinValue(entries, start, end);

            result[i] = minValue;


        }

//        System.out.println("getMinValue1 time:" + (System.currentTimeMillis() - time));


        return result;
    }

    private int getMinValue(Set<Map.Entry<Integer, Integer>> entries, int start, int end) {

        int minValue = 4;

        if (entries.size() == 1) {
            for (Map.Entry<Integer, Integer> entry : entries) {
                return entry.getValue();
            }
        }

        for (Map.Entry<Integer, Integer> entry : entries) {

            Integer key = entry.getKey();

            if (key.intValue() >= end) {
                break;
            }
            if (key.intValue() >= start && key.intValue() < end) {
                Integer value = entry.getValue();
                if (value.intValue() == 1) {
                    return 1;
                }
                if (value.intValue() < minValue) {
                    minValue = value.intValue();
                }
            }
        }



        return minValue;
    }


    private TreeMap<Integer, Integer> getAllSegmentMinValue(String s, TreeSet<Integer> allSegment) {

//        long time = System.currentTimeMillis();

        TreeMap<Integer, Integer> returnMap = new TreeMap<>();

        if (allSegment.size() == 0) {
            return returnMap;
        }

        Iterator<Integer> allSegmentIterator = allSegment.iterator();


        Integer start = allSegmentIterator.next();
        if (allSegment.size() == 1) {
            char currentChar = s.charAt(start);
            Integer integer = map.get(currentChar);
            returnMap.put(start, integer);
            return returnMap;
        }

        while (allSegmentIterator.hasNext()) {
            Integer end = allSegmentIterator.next();
            int minValue = getMinValue(s, start, end);
            returnMap.put(start, minValue);
            start = end;
        }

//        System.out.println("getAllSegmentMinValue time:" + (System.currentTimeMillis() - time));

        return returnMap;
    }

    private int getMinValue(String s,int start,int end) {

//        long time = System.currentTimeMillis();

        int minValue = 4;
        String targetString = s.substring(start, end + 1);

        if (isAllSameLetters(targetString)) {
            char first = s.charAt(0);
            minValue = map.get(first);
            return minValue;
        }

        for (int i = 0; i < targetString.length(); i++) {
            Integer currentValue = map.get(targetString.charAt(i));
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

        String s = "CAGCCTA";
        int[] result = solution(s, P, Q);

        //2,4,1

        System.out.println(Arrays.toString(result));

    }

    @Test
    public void testSingleCharacterString() {
        int[] P = {0};
        int[] Q = {0};

        String s  = "A";
        int[] result = solution(s, P, Q);

        //1
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testDoubleCharacterString() {
        int[] P = {0,0,1};
        int[] Q = {0,1,1};

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
