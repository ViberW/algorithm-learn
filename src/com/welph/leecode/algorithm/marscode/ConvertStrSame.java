package com.welph.leecode.algorithm.marscode;

/**
 * 小U 和 小R 各自拥有一个长度相等的二进制字符串 A 和 B。现在，他们想要将这两个字符串修改成相同的字符串。每次修改可以选择以下两种操作：
 * <p>
 * 1. 交换同一个字符串中的任意两个字符，交换操作的成本为它们索引之差的绝对值 |i - j|。
 * 2. 对某个字符进行取反操作，取反的成本为 2。
 * 小U 和 小R 想知道，将字符串 A 和 B 修改为相同字符串的最小总成本是多少？
 */
public class ConvertStrSame {
    public static int solution(String str1, String str2) {
        // Edit your code here
        int length = str1.length();
        int[] next = new int[length];
        for (int i = length - 1; i >= 0; i--) {
            if (str1.charAt(i) != str2.charAt(i)) {
                if (str1.charAt(i) == '0') {
                    next[i] = -1; //为0
                } else {
                    next[i] = 1; //为1
                }
            }
        }
        int totalCost = 0;
        for (int i = 0; i < length; i++) {
            if (next[i] != 0) {
                int originI = i;
                int step1 = 2;
                int step2 = 2;
                int expect = -next[originI];
                for (int nextI = i + 1; nextI < length; nextI++) {
                    if (next[nextI] == expect) {
                        if (nextI - originI < 4) {
                            int tmp = step1;
                            step1 = step2 - 2 + nextI - originI;
                            step2 = Math.min(tmp, step2) + 2;
                            expect = -next[nextI];
                            next[originI] = 0;  //剪枝
                            originI = nextI;
                        } else {
                            break;
                        }
                    }
                }
                next[originI] = 0;
                totalCost += Math.min(step1, step2);
            }
        }
        return totalCost;
    }

    public static void main(String[] args) {
        // Add your test cases here

        System.out.println(solution("10001", "10000") == 2);
        System.out.println(solution("100100", "100001") == 2);
        System.out.println(solution("1010", "0011") == 3);
        System.out.println(solution("1100", "0011") == 4);
    }
}
