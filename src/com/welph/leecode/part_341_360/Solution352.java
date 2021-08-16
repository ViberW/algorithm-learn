package com.welph.leecode.part_341_360;

import java.util.*;

/**
 * 给定一个非负整数的数据流输入 a1，a2，…，an，…，将到目前为止看到的数字总结为不相交的区间列表。
 * <p>
 * 例如，假设数据流中的整数为 1，3，7，2，6，…，每次的总结为：
 * [1, 1]
 * [1, 1], [3, 3]
 * [1, 1], [3, 3], [7, 7]
 * [1, 3], [7, 7]
 * [1, 3], [6, 7]
 * <p>
 * 进阶：
 * 如果有很多合并，并且与数据流的大小相比，不相交区间的数量很小，该怎么办?
 */
public class Solution352 {

    /**
     * 有点类似: {@link com.welph.leecode.part_41_60.Solution57}
     */
    public static void main(String[] args) {
        //Your SummaryRanges object will be instantiated and called as such:
        SummaryRanges obj = new SummaryRanges();
        obj.addNum(1);
        obj.addNum(3);
        obj.addNum(7);
        obj.addNum(2);
        obj.addNum(6);
        int[][] param_2 = obj.getIntervals();
        for (int[] ints : param_2) {
            System.out.println(Arrays.toString(ints));
        }
    }

    //todo  之后处理.  按照下面的思路应该是能够处理到的
    static class SummaryRanges {

        TreeMap<Integer, int[]> tree;
        int[] EMPTY = new int[]{-1, -1};

        /**
         * Initialize your data structure here.
         */
        public SummaryRanges() {
            tree = new TreeMap<>();
        }

        public void addNum(int val) {
            //查询是否有存在
            Map.Entry<Integer, int[]> floorEntry = tree.floorEntry(val);
            if (null == floorEntry || val != floorEntry.getKey()) {
                Map.Entry<Integer, int[]> ceilingEntry = tree.ceilingEntry(val);
                if (null == ceilingEntry || ceilingEntry.getKey() != val) {


               /*
                    boolean nearFloor = null != i1 && i1 + 1 == val;
                    boolean nearCeil = null != i2 && i2 == val + 1;
                    if (nearFloor && nearCeil) {
                        Integer v1 = tree.get(i1);
                        Map.Entry<Integer, Integer> entry = tree.floorEntry(i1);
                        if (!i1.equals(entry.getKey()) && v1.equals(entry.getValue())) {
                            tree.remove(i1);
                        }
                        v1 = tree.get(i2);
                        entry = tree.floorEntry(i2);
                        if (!i2.equals(entry.getKey()) && v1.equals(entry.getValue())) {
                            tree.remove(i2);
                            tree.put(entry.getKey(), v1);
                        } else {
                            tree.put(i2, v1);
                        }
                    } else if (nearFloor) {
                        Integer v1 = tree.get(i1);
                        Map.Entry<Integer, Integer> entry = tree.floorEntry(i1);
                        if (!i1.equals(entry.getKey()) && v1.equals(entry.getValue())) {
                            tree.remove(i1);
                        }
                        tree.put(val, v1);
                    } else if (nearCeil) {
                        Integer v1 = tree.get(i2);
                        Map.Entry<Integer, Integer> entry = tree.floorEntry(i2);
                        if (!i2.equals(entry.getKey()) && v1.equals(entry.getValue())) {
                            tree.remove(i2);
                            tree.put(entry.getKey(), v1);
                        } else {
                            tree.put(i2, val);
                        }
                        tree.put(val, v1);
                    } else {
                        tree.put(val, val);
                    }*/
                }
            }
        }

        public int[][] getIntervals() {
            int[][] res = new int[1][2];
            return res;
        }
    }

}
