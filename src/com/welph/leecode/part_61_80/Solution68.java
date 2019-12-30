package com.welph.leecode.part_61_80;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * <p>
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * <p>
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * <p>
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 说明:
 * <p>
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 * <p>
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 * <p>
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 * <p>
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 * "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 * ["My","momma","always","said,","\"Life","was","like","a","box","of","chocolates.","You","never","know","what","you're","gonna","get."]
 * 20
 */
public class Solution68 {

    public static void main(String[] args) {
        String[] words = {"My", "momma", "always", "said,", "\"Life", "was", "like", "a", "box", "of", "chocolates.", "You", "never", "know", "what", "you're", "gonna", "get."};
        System.out.println(fullJustify(words, 16));
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int len = words.length;
        int cur;
        int origin;
        for (int i = 0; i < len; )
            Reset:{
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

    //左闭右开
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
}
