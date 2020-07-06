package com.welph.leecode.algorithm.greedy;

/**
 * 在一个非负整数 a 中，我们希望从中移除 k 个数字，让剩下的数字值最小，如何选择移除哪 k 个数字呢？
 * <p>
 * 解题: 由最高位开始，比较低一位数字，如高位大，移除，若高位小，则向右移一位继续比较两个数字，直到高位大于低位则移除，循环k次，如：
 * 4556847594546移除5位-》455647594546-》45547594546-》4547594546-》447594546-》44594546
 */
public class MoveK {

    public static void main(String[] args) {
        long i = 4556847594546L;
        String s = String.valueOf(i);
        LABEL:
        for (int k = 0; k < 5; k++) {
            System.out.println(s);
            int j = 0;
            for (; j < s.length() - 1; j++) {
                if (s.charAt(j) > s.charAt(j + 1)) {
                    s = s.substring(0, j) + s.substring(j + 1);
                    continue LABEL;
                }
            }
            s = s.substring(0, j) + s.substring(j + 1);
        }
        System.out.println(s);
    }
}
