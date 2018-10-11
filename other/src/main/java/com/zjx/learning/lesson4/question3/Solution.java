package com.zjx.learning.lesson4.question3;

import org.junit.Test;

import java.util.*;


/**
 * You are given N counters, initially set to 0, and you have two possible operations on them:
 *
 * increase(X) − counter X is increased by 1,
 * max counter − all counters are set to the maximum value of any counter.
 * A non-empty array A of M integers is given. This array represents consecutive operations:
 *
 * if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
 * if A[K] = N + 1 then operation K is max counter.
 * For example, given integer N = 5 and array A such that:
 *
 *     A[0] = 3
 *     A[1] = 4
 *     A[2] = 4
 *     A[3] = 6
 *     A[4] = 1
 *     A[5] = 4
 *     A[6] = 4
 * the values of the counters after each consecutive operation will be:
 *
 *     (0, 0, 1, 0, 0)
 *     (0, 0, 1, 1, 0)
 *     (0, 0, 1, 2, 0)
 *     (2, 2, 2, 2, 2)
 *     (3, 2, 2, 2, 2)
 *     (3, 2, 2, 3, 2)
 *     (3, 2, 2, 4, 2)
 * The goal is to calculate the value of every counter after all operations.
 *
 * Write a function:
 *
 * class Solution { public int[] solution(int N, int[] A); }
 *
 * that, given an integer N and a non-empty array A consisting of M integers, returns a sequence of integers representing the values of the counters.
 *
 * Result array should be returned as an array of integers.
 *
 * For example, given:
 *
 *     A[0] = 3
 *     A[1] = 4
 *     A[2] = 4
 *     A[3] = 6
 *     A[4] = 1
 *     A[5] = 4
 *     A[6] = 4
 * the function should return [3, 2, 2, 4, 2], as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N and M are integers within the range [1..100,000];
 * each element of array A is an integer within the range [1..N + 1].
 */
public class Solution {

    @Test
    public void testSolution() {
        int[] A = new int[7];
        A[0] = 3;
        A[1] = 4;
        A[2] = 4;
        A[3] = 6;
        A[4] = 1;
        A[5] = 4;
        A[6] = 4;

        int[] solution = solution(5, A);
//        int[] solution = solution(1, new int[]{1});
//        int[] solution = solution(5, new int[]{6, 6, 6, 6, 6, 6});


        System.out.println(Arrays.toString(solution));
    }

    @Test
    public void testSolution2() {
        int[] result = solution2(1, new int[]{1});
        System.out.println(Arrays.toString(result));
    }

    @Test
    public void testGetSegment() {
        int[] A = new int[7];
        A[0] = 3;
        A[1] = 4;
        A[2] = 4;
        A[3] = 6;
        A[4] = 1;
        A[5] = 4;
        A[6] = 4;
        System.out.println(getSegmentIndex(5, A));
    }


    public int[] solution(int N, int[] A) {

        List<Integer> indexs = getSegmentIndex(N, A);

        int lastValue = 0;
        for (int i = 0; i < indexs.size() - 1; i++) {
            Integer start = indexs.get(i);
            Integer end = indexs.get(i + 1);

            if (start - end == -1) {
                continue;
            }

            int maxValue = 0;
            Map<Integer, Integer> maxCountMap = new HashMap<>();
            for (int j = start == 0 ? 0 : start + 1; j < end; j++) {
                if (A[j] == N + 1) {
                    continue;
                }
                Integer count = maxCountMap.get(A[j] - 1);

                if (count == null) {
                    count = 0;
                }
                maxCountMap.put(A[j] - 1, count + 1);
                maxValue = Math.max(count + 1, maxValue);
            }

            lastValue += maxValue;

        }


        int[] result = new int[N];

        changeAllMax(lastValue,result);

        if (indexs.size() == 1) {
            for (int i = 0; i < A.length; i++) {
                increase(A[i] - 1, result);
            }
            return result;
        }

        Integer lastIndex = indexs.get(indexs.size() - 1);

        if (lastIndex != A.length - 1) {

            normalSolution(lastIndex + 1, A, result);

        }

        return result;
    }


    private void normalSolution(int start,int[] arr,int[] result) {

        for (int i = start; i < arr.length; i++) {
            increase(arr[i] - 1, result);
        }
    }


    private List<Integer> getSegmentIndex(int N, int[] arr) {

        List<Integer> result = new ArrayList<>();
        result.add(0);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == N + 1) {
                result.add(i);
            }
        }

        return result;
    }


    private void changeAllMax(int max, int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            arr[i] = max;
        }

    }

    private void increase(int index, int[] arr) {
        arr[index] += 1;
    }

    public int[] solution2(int N, int[] A) {

        int[] newArr = new int[N];

        int maxValue = 0;

        for (int i = 0; i < A.length; i++) {

            if (A[i] >= 1 && A[i] <= N) {
                increase(A[i] - 1, newArr);
                maxValue = Math.max(maxValue, newArr[A[i] - 1]);
            } else {
                changeAllMax(maxValue, newArr);
            }


            System.out.println("i:" + i + ", A[i]:" + A[i] + ", max:" + maxValue + ", newArr:" + Arrays.toString(newArr));
        }


        return newArr;
    }

    private int getMaxCountValue(int N,int[] arr) {

        int maxVaule = 0;

        int[] result = new int[N];

        for (int i = 0; i < arr.length; i++) {
            result[arr[i] - 1] += 1;
            maxVaule = Math.max(result[arr[i] - 1], maxVaule);
        }


        return maxVaule;
    }
}
