package com.welph.leecode.algorithm.marscode;

/**
 * 小U有一个由字符 a 和 b 组成的字符串 S，字符串的长度为 N，字符从编号 0 到 N-1 依次排列。
 * 小U想要找到字符串 S 的“成本”，定义为：字符串中按非递减顺序排列的最长子字符串的长度。
 *
 * 小U可以进行最多一次操作：选择一个位置 i （0 <= i < N-1），并交换 S[i] 和 S[i+1]。
 *
 * 你需要帮助小U计算，在执行最多一次交换操作后，字符串 S 的最小可能成本是多少。
 *
 * 例如：当 N = 4 且 S = "abba" 时，小U可以选择交换 S[0] 和 S[1]，得到字符串 "baba"。
 * 此时字符串的成本为 2，且可以证明这是最小的可能成本。
 */
public class LengthGoUpSubString {

    public static int solution(int N, String S) {
        char[] array = S.toCharArray();
        int[] max = new int[1];
        int result = calculate(N, array, max);
        int l = Math.max(1, max[0]);
        int r = Math.max(N, max[0] + result);
        for (int i = l; i < r; i++) { //可以优化,有且仅当拆除第一次最长的即可
            if (array[i] != array[i - 1]) {
                char tmp = array[i];
                array[i] = array[i - 1];
                array[i - 1] = tmp;
                //计算
                result = Math.min(result, calculate(N, array, null));
                tmp = array[i];
                array[i] = array[i - 1];
                array[i - 1] = tmp;
            }
        }
        return result;
    }

    private static int calculate(int N, char[] array, int[] max) {
        int result = 1;
        int curr = 1;
        int target = 0;
        for (int i = 1; i < N; i++) {
            if (array[i] >= array[i - 1]) {
                curr++;
                result = Math.max(result, curr);
                if (curr > result) {
                    result = curr;
                    target = i;
                }
            } else {
                curr = 1;
            }
        }
        if (null != max) {
            max[0] = target;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solution(4, "abba") == 2);
        System.out.println(solution(5, "baabb") == 2);
        System.out.println(solution(3, "bab") == 2);
    }
}
