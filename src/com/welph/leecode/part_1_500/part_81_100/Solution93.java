package com.welph.leecode.part_1_500.part_81_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
 * <p>
 * 示例:
 * <p>
 * 输入: "25525511135"
 * 输出: ["255.255.11.135", "255.255.111.35"]
 * <p>
 * 1.0.0.0-255.255.255.255
 */
public class Solution93 {

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        System.out.println(restoreIpAddresses("00000"));
        System.out.println(System.currentTimeMillis() - begin);
    }

    /**
     * 还需要考虑到 左边为0的结果处理;
     * 开始是返回string[] 但内存消耗有点多.......时间也慢
     * <p>
     * 考虑返回对应的index数组 --速度提升到98% 但内存消耗有点多..
     *
     * @param s
     * @return
     */
    public static List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int length = s.length();
        if (length < 4 || length > 12) {
            return result;
        }
        char[] chars = s.toCharArray();
        int[] vs = new int[length];
        for (int i = 0; i < length; i++) {
            vs[i] = chars[i] - '0';
        }
        List<int[]> indexs = ipAddresses(0, length, 0, vs);
        if (null == indexs) {
            return result;
        }
        for (int[] index : indexs) {
            char[] c = new char[length + 3];
            int j = 1;
            for (int i = 0, k = 0; i < length; ++i, ++k) {
                if (i == index[j]) {
                    c[k++] = '.';
                    j++;
                }
                c[k] = chars[i];
            }
            result.add(new String(c));
        }
        return result;
    }

    private static List<int[]> ipAddresses(int l, int r, int deep, int[] vs) {
        if (deep == 4) {
            if (l == r) {
                return new ArrayList<int[]>(4) {
                    {
                        add(new int[5]); // 方便设置 . 时候的处理
                    }
                };
            }
            return null;
        } else if (l >= r) {
            return null;
        }
        int total = vs[l];
        List<int[]> results = merge(null, ipAddresses(l + 1, r, deep + 1, vs), l, deep);
        if (total == 0 || l + 1 >= r) {
            return results;
        }
        total = total * 10 + vs[l + 1];
        results = merge(results, ipAddresses(l + 2, r, deep + 1, vs), l, deep);
        if (l + 2 >= r) {
            return results;
        }
        if (total * 10 + vs[l + 2] <= 255) {
            results = merge(results, ipAddresses(l + 3, r, deep + 1, vs), l, deep);
        }
        return results;
    }

    private static List<int[]> merge(List<int[]> results, List<int[]> indexs, int i, int deep) {
        if (null == indexs || indexs.isEmpty()) {
            return results;
        }
        if (null == results) {
            for (int[] string : indexs) {
                string[deep] = i;
            }
            return indexs;
        }
        for (int[] string : indexs) {
            string[deep] = i;
            results.add(string);
        }
        return results;
    }

    /* 官方题解 */
    static final int SEG_COUNT = 4;
    List<String> ans = new ArrayList<String>();
    int[] segments = new int[SEG_COUNT];

    public List<String> restoreIpAddresses2(String s) {
        segments = new int[SEG_COUNT];
        dfs(s, 0, 0);
        return ans;
    }

    public void dfs(String s, int segId, int segStart) {
        // 如果找到了 4 段 IP 地址并且遍历完了字符串，那么就是一种答案
        if (segId == SEG_COUNT) {
            if (segStart == s.length()) {
                StringBuffer ipAddr = new StringBuffer();
                for (int i = 0; i < SEG_COUNT; ++i) {
                    ipAddr.append(segments[i]);
                    if (i != SEG_COUNT - 1) {
                        ipAddr.append('.');
                    }
                }
                ans.add(ipAddr.toString());
            }
            return;
        }

        // 如果还没有找到 4 段 IP 地址就已经遍历完了字符串，那么提前回溯
        if (segStart == s.length()) {
            return;
        }

        // 由于不能有前导零，如果当前数字为 0，那么这一段 IP 地址只能为 0
        if (s.charAt(segStart) == '0') {
            segments[segId] = 0;
            dfs(s, segId + 1, segStart + 1);
        }

        // 一般情况，枚举每一种可能性并递归
        int addr = 0;
        for (int segEnd = segStart; segEnd < s.length(); ++segEnd) {
            addr = addr * 10 + (s.charAt(segEnd) - '0');
            if (addr > 0 && addr <= 0xFF) {
                segments[segId] = addr;
                dfs(s, segId + 1, segEnd + 1);
            } else {
                break;
            }
        }
    }
}
