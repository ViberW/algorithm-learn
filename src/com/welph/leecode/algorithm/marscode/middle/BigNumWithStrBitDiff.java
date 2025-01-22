package com.welph.leecode.algorithm.marscode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 有两个超大字符串数，问它们的和得到的字符串中的最大数和最小数之间相差多少位？
 *
 * 如果都是一样的数则位数为 0，如果有多个数，则取符合条件的最小值。
 */
public class BigNumWithStrBitDiff {

    public static int solution(String string1, String string2) {
        if (string1.length() > string2.length()) {
            return calc(string1, string2);
        } else {
            return calc(string2, string1);
        }
    }

    public static int calc(String string1, String string2) {
        int addition = 0;
        int maxValue = -1;
        int minValue = 10;
        int maxIndex = 2 * string1.length();
        int minIndex = 2 * string1.length();
        int result = string1.length();
        StringBuilder builder = new StringBuilder();
        for (int i = string1.length() - 1, j = string2.length() - 1; i >= 0; i--, j--) {
            int val = (string1.charAt(i) - '0') + (j >= 0 ? (string2.charAt(j) - '0') : 0) + addition;
            int cur = val % 10;
            addition = val / 10;
            int tmp = maxIndex;
            if (cur == maxValue) {
                result = Math.min(result, minIndex - i - 1);
                maxIndex = i;
            } else if (cur > maxValue) {
                result = minIndex - i - 1;
                maxValue = cur;
                maxIndex = i;
            }
            if (cur == minValue) {
                result = Math.min(result, tmp - i - 1);
                minIndex = i;
            } else if (cur < minValue) {
                result = tmp - i - 1;
                minValue = cur;
                minIndex = i;
            }
            builder.append(cur);
        }
        if (addition > 0) {
            if (addition == maxValue) {
                result = Math.min(result, minIndex);
            } else if (addition > maxValue) {
                result = minIndex;
            }
            if (addition == minValue) {
                result = Math.min(result, maxIndex);
            } else if (addition < minValue) {
                result = maxIndex;
            }
            builder.append(addition);
        }
        if (maxValue == minValue) {
            return 0;
        }
        return result;
    }

    public static void main(String[] args) {
        //  You can add more test cases here
//        System.out.println(solution("111", "222") == 0);
//        System.out.println(solution("111", "34") == 1);
        System.out.println(solution("5976762424003073", "6301027308640389") == 6);
        System.out.println(solution("11000001", "44334434334444344333") == 0);
    }
}
