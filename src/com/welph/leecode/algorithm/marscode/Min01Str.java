package com.welph.leecode.algorithm.marscode;

/**
 * 小U拥有一个由0和1组成的字符串，她可以进行最多k次操作，每次操作可以交换相邻的两个字符。
 * 目标是通过这些操作，使得最终得到的字符串字典序最小。
 * <p>
 * 例如，小U当前有一个字符串 01010，她最多可以进行 2 次相邻字符交换操作。通过这些操作，
 * 她可以将字符串调整为 00101，这是可以通过不超过2次操作得到的字典序最小的字符串。
 * <p>
 * 现在，小U想知道，经过最多k次操作后，能够得到的字典序最小的字符串是什么。
 */
public class Min01Str {

    public static String solution(int n, int k, String s) {
        char[] charArray = s.toCharArray();
        while (k-- > 0) {
            for (int i = 0; i < n - 1; i++) {
                if (charArray[i] > charArray[i + 1]) {
                    char tmp = charArray[i];
                    charArray[i] = charArray[i + 1];
                    charArray[i + 1] = tmp;
                    break;
                }
            }
            //是否需要考虑10或01或0或1 这种情况呢?
        }
        return new String(charArray);
    }

    public static void main(String[] args) {
        System.out.println(solution(5, 2, "01010").equals("00101"));
        System.out.println(solution(7, 3, "1101001").equals("0110101"));
        System.out.println(solution(4, 1, "1001").equals("0101"));
    }
}
