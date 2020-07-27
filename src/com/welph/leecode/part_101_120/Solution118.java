package com.welph.leecode.part_101_120;

import java.util.ArrayList;
import java.util.List;

/**
 * .给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 * .
 * .
 * .在杨辉三角中，每个数是它左上方和右上方的数的和。
 * .
 * .示例:
 * .
 * .输入: 5
 * .输出:
 * .[
 * .     [1],
 * .    [1,1],
 * .   [1,2,1],
 * .  [1,3,3,1],
 * . [1,4,6,4,1]
 * .]
 */
public class Solution118 {

    public static void main(String[] args) {
        List<List<Integer>> generate = generate(5);
        for (List<Integer> list : generate) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        List<Integer> list;
        List<Integer> objects;
        int j;
        for (int i = 0; i < numRows; i++) {
            objects = new ArrayList<>();
            objects.add(1);
            j = 1;
            if (j < i) {
                list = results.get(i - 1);
                do {
                    objects.add(list.get(j - 1) + list.get(j));
                    j++;
                } while (j < i);
            }
            if (i != 0) {
                objects.add(1);
            }
            results.add(objects);
        }
        return results;
    }

}
