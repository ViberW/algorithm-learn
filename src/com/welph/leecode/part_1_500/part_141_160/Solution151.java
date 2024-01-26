package com.welph.leecode.part_1_500.part_141_160;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 说明：
 * <p>
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 示例 1：
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * 输入：" hello world! "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 * 输入：s = " Bob Loves Alice "
 * 输出："Alice Loves Bob"
 * 示例 5：
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 * <p>
 * 提示：
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 * <p>
 * 进阶：
 * 请尝试使用O(1) 额外空间复杂度的原地解法。
 */
public class Solution151 {

    public static void main(String[] args) {
        System.out.println(reverseWords1("the sky is blue"));
        System.out.println(reverseWords1("Alice does not even like bob   "));
    }

    // todo 原地O(1)空间算法
    // O(1)的空间复杂度,基本上是些标记位置了,原地算法.(基本上不借助其他大空间了)
    public static String reverseWords(String s) {
        char[] chars = s.toCharArray();
        int l = 0;
        int r = s.length() - 1;
        boolean lastIsBlank = true;
        while (l < r) {
            // 查看两者的距离

        }
        return null;
    }

    /**
     * 使用一个栈表示字符串
     */
    public static String reverseWords1(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        Stack<String> chs = new Stack<>();
        int lastIndex = 0;
        boolean hasStr = false;
        for (int i = 0; i < length; i++) {
            if (chars[i] == ' ') {
                if (hasStr) {
                    chs.push(s.substring(lastIndex, i));
                }
                lastIndex = i + 1;
                hasStr = false;
            } else {
                hasStr = true;
            }
        }
        if (lastIndex < length) {
            chs.push(s.substring(lastIndex));
        }
        StringBuilder sb = new StringBuilder();
        while (!chs.isEmpty()) {
            sb.append(chs.pop()).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /* 官方题解 */
    // 自定义实现
    public String reverseWords2(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }

    // 双端队列实现
    public String reverseWords3(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        Deque<String> d = new ArrayDeque<String>();
        StringBuilder word = new StringBuilder();

        while (left <= right) {
            char c = s.charAt(left);
            if ((word.length() != 0) && (c == ' ')) {
                // 将单词 push 到队列的头部
                d.offerFirst(word.toString());
                word.setLength(0);
            } else if (c != ' ') {
                word.append(c);
            }
            ++left;
        }
        d.offerFirst(word.toString());

        return String.join(" ", d);
    }

}
