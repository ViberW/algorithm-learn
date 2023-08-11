package com.welph.leecode.part_1_500.part_61_80;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单词数组和一个长度maxWidth，重新排版单词，使其成为每行恰好有maxWidth个字符，且左右两端对齐的文本。
 * <p>
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格' '填充，使得每行恰好有 maxWidth个字符。
 * <p>
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * <p>
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 说明:
 * <p>
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于maxWidth。
 * 输入单词数组 words至少包含一个单词。
 * 示例:
 * <p>
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 * "This is an",
 * "example of text",
 * "justification. "
 * ]
 * 示例2:
 * <p>
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 * "What must be",
 * "acknowledgment ",
 * "shall be "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be " 而不是 "shall be",
 * 因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例3:
 * <p>
 * 输入:
 * words =
 * ["Science","is","what","we","understand","well","enough","to","explain",
 * "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 * "Science is what we",
 * "understand well",
 * "enough to explain to",
 * "a computer. Art is",
 * "everything else we",
 * "do "
 * ]
 * ["My","momma","always","said,","\"Life","was","like","a","box","of","chocolates.","You","never","know","what","you're","gonna","get."]
 * 20
 */
public class Solution68 {

    public static void main(String[] args) {
        String[] words = { "My", "momma", "always", "said,", "\"Life", "was", "like", "a", "box", "of", "chocolates.",
                "You", "never", "know", "what", "you're", "gonna", "get." };
        System.out.println(fullJustify(words, 16));
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int len = words.length;
        int cur;
        int origin;
        for (int i = 0; i < len;)
            Reset: {
                origin = i;
                cur = words[i].length();
                do {
                    i++;
                    if (i >= len) {
                        result.add(build(words, origin, i, cur, maxWidth, true));
                        break Reset;
                    }
                    cur += words[i].length() + 1;
                } while (cur <= maxWidth);
                cur = cur - (words[i].length() + 1);
                result.add(build(words, origin, i, cur, maxWidth, false));
            }
        return result;
    }

    // 左闭右开
    private static String build(String[] words, int start, int end, int cur, int maxWidth, boolean last) {
        StringBuilder sb = new StringBuilder(words[start]);
        int sub = end - start - 1;
        if (sub == 0) {
            addBlank(sb, maxWidth - cur);
        } else {
            int addition = (last ? 0 : ((maxWidth - cur) / sub)) + 1;
            sub = last ? 0 : (maxWidth - cur) % sub;
            System.out.println(addition + "   " + sub);
            for (int i = start + 1; i < end; i++) {
                addBlank(sb, addition + (sub-- > 0 ? 1 : 0));
                sb.append(words[i]);
            }
            if (sb.length() < maxWidth) {
                addBlank(sb, maxWidth - sb.length());
            }
        }
        return sb.toString();
    }

    private static void addBlank(StringBuilder sb, int size) {
        while (size-- > 0) {
            sb.append(" ");
        }
    }

    /* 官方题解 */
    public List<String> fullJustify2(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<String>();
        int right = 0, n = words.length;
        while (true) {
            int left = right; // 当前行的第一个单词在 words 的位置
            int sumLen = 0; // 统计这一行单词长度之和
            // 循环确定当前行可以放多少单词，注意单词之间应至少有一个空格
            while (right < n && sumLen + words[right].length() + right - left <= maxWidth) {
                sumLen += words[right++].length();
            }

            // 当前行是最后一行：单词左对齐，且单词之间应只有一个空格，在行末填充剩余空格
            if (right == n) {
                StringBuffer sb = join(words, left, n, " ");
                sb.append(blank(maxWidth - sb.length()));
                ans.add(sb.toString());
                return ans;
            }

            int numWords = right - left;
            int numSpaces = maxWidth - sumLen;

            // 当前行只有一个单词：该单词左对齐，在行末填充剩余空格
            if (numWords == 1) {
                StringBuffer sb = new StringBuffer(words[left]);
                sb.append(blank(numSpaces));
                ans.add(sb.toString());
                continue;
            }

            // 当前行不只一个单词
            int avgSpaces = numSpaces / (numWords - 1);
            int extraSpaces = numSpaces % (numWords - 1);
            StringBuffer sb = new StringBuffer();
            sb.append(join(words, left, left + extraSpaces + 1, blank(avgSpaces + 1))); // 拼接额外加一个空格的单词
            sb.append(blank(avgSpaces));
            sb.append(join(words, left + extraSpaces + 1, right, blank(avgSpaces))); // 拼接其余单词
            ans.add(sb.toString());
        }
    }

    // blank 返回长度为 n 的由空格组成的字符串
    public String blank(int n) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            sb.append(' ');
        }
        return sb.toString();
    }

    // join 返回用 sep 拼接 [left, right) 范围内的 words 组成的字符串
    public StringBuffer join(String[] words, int left, int right, String sep) {
        StringBuffer sb = new StringBuffer(words[left]);
        for (int i = left + 1; i < right; ++i) {
            sb.append(sep);
            sb.append(words[i]);
        }
        return sb;
    }
}
