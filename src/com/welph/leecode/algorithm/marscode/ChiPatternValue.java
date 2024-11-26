package com.welph.leecode.algorithm.marscode;

/**
 * 小R定义了一个字符串的权值，该权值基于字符串中所有字符'h'的位置计算。
 * 对于每个字符'h'，它前面的'c'和后面的'i'都会对它贡献1的权值。
 *
 * 小R现在手上有一个由字符'c'、'h'、'i'、'?'组成的字符串，其中'?'表示一个未知字符，可以被替换为'c'、'h'或'i'。
 * 小R想知道，所有可能替换方案的权值之和是多少？答案可能非常大，因此需要对1000000007取模。
 */
public class ChiPatternValue {

    public static int solution(String s) {
        int MOD = 1000000007;
        long countH = 0;
        long countC = 0;
        long countUnknown = 0;
        int n = s.length();
        long total = 0;
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            switch (c) {
                case 'i':
                    total = (total + countH) % MOD;
                    break;
                case '?':
                    //当作H和I
                    total = (3 * total + countC + countH) % MOD;
                    countH = (long) (3 * countH + Math.pow(3, countUnknown));
                    countC = (long) (3 * countC + Math.pow(3, countUnknown));
                    countUnknown++;
                    break;
                case 'h':
                    countH = (long) (countH + Math.pow(3, countUnknown));// 后面每次都会有3次方的H添加
                    total = (total + countC) % MOD;
                    break;
                case 'c':
                    countC = (long) (countC + Math.pow(3, countUnknown));
                    break;
            }

        }
        return (int) total;
    }

    public static void main(String[] args) {
        System.out.println(solution("ch?hi"));
        System.out.println(solution("ccch") == 3);
        System.out.println(solution("c?i") == 2);
    }

}
