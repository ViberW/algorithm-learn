package com.welph.leecode.part_1_500.part_461_480;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义 str = [s, n] 表示 str 由 n 个字符串 s 连接构成。
 * <p>
 * 例如，str == ["abc", 3] =="abcabcabc" 。
 * 如果可以从 s2中删除某些字符使其变为 s1，则称字符串 s1可以从字符串 s2 获得。
 * <p>
 * 例如，根据定义，s1 = "abc" 可以从 s2 = "abdbec" 获得，仅需要删除加粗且用斜体标识的字符。
 * 现在给你两个字符串 s1和 s2 和两个整数 n1 和 n2 。由此构造得到两个字符串，其中 str1 = [s1, n1]、str2 = [s2, n2] 。
 * <p>
 * 请你找出一个最大整数 m ，以满足 str = [str2, m] 可以从 str1获得。
 * <p>
 * 示例 1：
 * 输入：s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
 * 输出：2
 * <p>
 * 示例 2：
 * 输入：s1 = "acb", n1 = 1, s2 = "acb", n2 = 1
 * 输出：1
 * <p>
 * 提示：
 * 1 <= s1.length, s2.length <= 100
 * s1 和 s2 由小写英文字母组成
 * 1 <= n1, n2 <= 10^6
 */
public class Solution466 {
    public static void main(String[] args) {
        System.out.println(getMaxRepetitions(
                "acb", 4, "ab", 2));
    }

    //看了题解
    // 想办法找到哈希表 recall 以 s2 字符串的下标 index 为索引，存储匹配至第 s1cnt 个 s1 的末尾，
    // 当前匹配到第 s2cnt 个 s2 中的第 index 个字符时， 已经匹配过的s1 的个数 s1cnt 和 s2 的个数 s2cnt
    public static int getMaxRepetitions1(String s1, int n1, String s2, int n2) {
        //todo 想办法自己去实现
        return 0;
    }

    /**
     * 示例的意思是，
     * 输入：s1 ="acb",n1 = 4
     * s2 ="ab",n2 = 2
     * 返回：2
     * <p>
     * 即  S1 = [s1, n1] = acbacbacbacb (4个acb)
     * |   S2 = [s2, n2] = abab (2个ab)
     * <p>
     * S1 满足 [S2,M]
     * 即 acbacbacbacb 中，删除任意字符后，会出现 abab 的最大个数
     * 删除 S1 中的所有 c ， 则剩下的字母由 2 个 abab 组成
     * 所以返回 2
     * <p>
     * 即 M = 2 ， S1 = [S2, 2]
     * ---------
     * //todo 直奔题解的一题....哎
     * //todo 这个动态规划....咋写呢
     * //todo 这个动态规划....咋写呢
     */
    public static int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        if (n1 == 0) {
            return 0;
        }
        int s1cnt = 0, index = 0, s2cnt = 0;
        // recall 是我们用来找循环节的变量，它是一个哈希映射
        // 我们如何找循环节？假设我们遍历了 s1cnt 个 s1，此时匹配到了第 s2cnt 个 s2 中的第 index 个字符
        // 如果我们之前遍历了 s1cnt' 个 s1 时，匹配到的是第 s2cnt' 个 s2 中同样的第 index 个字符，那么就有循环节了
        // 我们用 (s1cnt', s2cnt', index) 和 (s1cnt, s2cnt, index) 表示两次包含相同 index 的匹配结果
        // 那么哈希映射中的键就是 index，值就是 (s1cnt', s2cnt') 这个二元组
        //  循环节就是；
        //    - 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
        //    - 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
        // 那么还会剩下 (n1 - s1cnt') % (s1cnt - s1cnt') 个 s1, 我们对这些与 s2 进行暴力匹配
        // 注意 s2 要从第 index 个字符开始匹配
        Map<Integer, int[]> recall = new HashMap<>();
        int[] preLoop = new int[2];
        int[] inLoop = new int[2];
        while (true) {
            // 我们多遍历一个 s1，看看能不能找到循环节
            ++s1cnt;
            for (int i = 0; i < s1.length(); ++i) {
                char ch = s1.charAt(i);
                if (ch == s2.charAt(index)) {
                    index += 1;
                    if (index == s2.length()) {
                        ++s2cnt;
                        index = 0;
                    }
                }
            }
            // 还没有找到循环节，所有的 s1 就用完了
            if (s1cnt == n1) {
                return s2cnt / n2;
            }
            // 出现了之前的 index，表示找到了循环节
            if (recall.containsKey(index)) {
                int[] value = recall.get(index);
                int s1cntPrime = value[0];
                int s2cntPrime = value[1];
                // 前 s1cnt' 个 s1 包含了 s2cnt' 个 s2
                preLoop = new int[]{s1cntPrime, s2cntPrime};
                // 以后的每 (s1cnt - s1cnt') 个 s1 包含了 (s2cnt - s2cnt') 个 s2
                inLoop = new int[]{s1cnt - s1cntPrime, s2cnt - s2cntPrime};
                break;
            } else {
                recall.put(index, new int[]{s1cnt, s2cnt});
            }
        }
        // |-------这部分为preloop-------|-inloop--|---|---|-----剩余的暴力匹配--
        // ans 存储的是 S1 包含的 s2 的数量，考虑的之前的 preLoop 和 inLoop
        int ans = preLoop[1] + (n1 - preLoop[0]) / inLoop[0] * inLoop[1];
        // S1 的末尾还剩下一些 s1，我们暴力进行匹配
        int rest = (n1 - preLoop[0]) % inLoop[0];
        for (int i = 0; i < rest; ++i) {//剩余个数的rest个s1
            for (int j = 0; j < s1.length(); ++j) {
                char ch = s1.charAt(j);
                if (ch == s2.charAt(index)) {
                    ++index;//从index开始进行后续的匹配.
                    if (index == s2.length()) {
                        ++ans;
                        index = 0;
                    }
                }
            }
        }
        // S1 包含 ans 个 s2，那么就包含 ans / n2 个 S2
        return ans / n2;
    }
}
