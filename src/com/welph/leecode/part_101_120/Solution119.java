package com.welph.leecode.part_101_120;

import java.util.ArrayList;
import java.util.List;

/**
 * .给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 * <p>
 * .在杨辉三角中，每个数是它左上方和右上方的数的和。
 * <p>
 * .示例:
 * <p>
 * .输入: 3
 * .输出: [1,3,3,1]
 * .进阶：
 * <p>
 * .你可以优化你的算法到 O(k) 【空间复杂度】吗？
 */
public class Solution119 {

    public static void main(String[] args) {
        System.out.println(getRow(3));
    }

    /**
     * 对称的，， 第rowIndex行 ，则长度为rowIndex+1，
     * 两边都是1，则中间剩下的为rowIndex-1，则只需要计算1到（rowIndex-1）/2的值
     */
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>(rowIndex + 1);
        int left = 0;
        for (int i = 1; i <= rowIndex + 1; i++) {
            if (i == 1) {
                res.add(1);
                continue;
            }
            for (int j = 0; j < i; j++) {
                int right = j < res.size() ? res.get(j) : 0;
                if (j < res.size()) {
                    //就地更新res
                    res.set(j, left + right);
                } else {
                    res.add(left + right);
                }
                left = right;
            }
        }
        return res;
    }

}
