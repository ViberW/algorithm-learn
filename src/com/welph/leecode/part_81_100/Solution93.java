package com.welph.leecode.part_81_100;

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
                return new ArrayList<int[]>(4) {{
                    add(new int[5]); //方便设置 . 时候的处理
                }};
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
}
