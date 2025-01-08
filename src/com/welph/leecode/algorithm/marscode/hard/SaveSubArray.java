package com.welph.leecode.algorithm.marscode.hard;

/**
 * 给定两个长度为 n 的数组 a 和 b，这两个数组满足从 1 到 n 的每个整数恰好出现了一次。
 *
 * 机器每次可以存储任意长度的连续子数组。你需要计算出机器存储完所有连续子数组后，总共存储了多少次。
 */
public class SaveSubArray {

    /*
          a:  [1], [2], [3], [1, 2], [2, 3], [1, 2, 3]
          b:  [2], [3], [1], [2, 3], [3, 1], [2, 3, 1]
          每次机器存储需要存储,相当于是a和b之间不重复的个数: 8
          同理下面第二个题目结果为16
     */
    public static int solution(int n, int[] a, int[] b) {
        //本质上就是查找相同子数组, 每个数组可以分: (n * (n + 1)) / 2
        int result = n * (n + 1);
        //找出重复的后缀
        for (int i = 0; i < n; i++) {
            boolean first = false;
            int k = i;
            for (int j = 0; j < n && k < n; j++) {
                if (a[k] == b[j]) {
                    if (!first) {
                        first = true;
                    }
                    k++;
                } else {
                    if (first) {
                        break;
                    }
                }
            }
            result -= (k - i) * (k - i + 1) / 2;
            i = k - 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{1, 2, 3}, new int[]{2, 3, 1}) == 8);
        System.out.println(solution(4, new int[]{1, 4, 3, 2}, new int[]{4, 1, 2, 3}) == 16);
        System.out.println(solution(5, new int[]{5, 1, 4, 3, 2}, new int[]{3, 5, 2, 1, 4}) == 24);
    }
}
