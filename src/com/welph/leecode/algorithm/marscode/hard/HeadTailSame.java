package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小M拿到了一个仅由小写字母组成的字符串，她想知道在这个字符串中，有多少个子序列的首尾字符相同。
 * 子序列的定义是：从原字符串中按原顺序取出若干字符（可以不连续）组成的新字符串。
 * <p>
 * 例如，对于字符串 "arcaea"，其子序列包括 "aca", "ara", "aaa" 等，这些子序列的首尾字符都是相同的。
 * <p>
 * 你需要计算满足这一条件的子序列数量，并输出对998244353取模的结果。
 */
public class HeadTailSame {
    public static int solution(String s) {
        int n = s.length();
        int[] pre = new int[n];
        int[] word = new int[26];
        for (int i = 0; i < n; i++) { //链式向前
            int index = s.charAt(i) - 'a';
            if (word[index] > 0) {
                pre[i] = word[index] - 1;
            } else {
                pre[i] = i;
            }
            word[index] = i + 1;
        }
        int ret = n; //O(N)
        for (int i = 0; i < n; i++) {
            int current = i;
            while (pre[current] != current) {
                ret += 1 << (i - pre[current] - 1);
                current = pre[current];
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(solution("arcaea") == 28);
        System.out.println(solution("abcabc") == 18);
        System.out.println(solution("aaaaa") == 31);
    }
}
