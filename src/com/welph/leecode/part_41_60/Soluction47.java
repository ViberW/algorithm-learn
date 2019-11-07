package com.welph.leecode.part_41_60;

import java.util.*;

/**
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * <p>
 * 示例:
 * <p>
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 */
public class Soluction47 {

    public static void main(String[] args) {
        int[] nums = {2, 2, 1, 1};
        System.out.println(permuteUnique(nums));
        //[[1,1,2,2],[1,2,1,2],[1,2,2,1],[2,1,1,2],[2,1,2,1],[2,2,1,1]]

        //[[1, 1, 2, 2], [1, 2, 1, 2], [2, 1, 1, 2], [1, 2, 2, 1], [2, 1, 2, 1], [2, 2, 1, 1]]
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        result.add(new ArrayList<>());
        List<Integer> peek;
        for (int i = 0; i < nums.length; i++) {
            while (true) {
                peek = result.peek();
                if (peek.size() > i) {
                    break;
                }
                int v = nums[i];
                Link:
                {
                    for (int k = 0; k < peek.size(); k++) {
                        if (peek.get(k) == v) {
                            continue;
                        }
                        int j = k;
                        result.addLast(new ArrayList<Integer>(peek) {{
                            add(j, v);
                        }});
                        if (k > 0 && v == peek.get(k - 1)) {
                            break Link;
                        }
                    }
                    peek.add(v);
                    result.addLast(new ArrayList<>(peek));
                }
                result.pop();
            }
        }
        return result;
    }

}
