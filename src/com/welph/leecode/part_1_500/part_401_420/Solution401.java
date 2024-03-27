package com.welph.leecode.part_1_500.part_401_420;

import java.util.ArrayList;
import java.util.List;

/**
 * 二进制手表顶部有 4 个 LED 代表 小时（0-11），底部的 6 个 LED 代表 分钟（0-59）。每个 LED 代表一个 0 或
 * 1，最低位在右侧。
 * 例如，下面的二进制手表读取 "3:25" 。
 * <p>
 * 给你一个整数 turnedOn ，表示当前亮着的 LED 的数量，返回二进制手表可以表示的所有可能时间。你可以 按任意顺序 返回答案。
 * 小时不会以零开头：
 * <p>
 * 例如，"01:00" 是无效的时间，正确的写法应该是 "1:00" 。
 * 分钟必须由两位数组成，可能会以零开头：
 * <p>
 * 例如，"10:2" 是无效的时间，正确的写法应该是 "10:02" 。
 * <p>
 * 示例 1：
 * 输入：turnedOn = 1
 * 输出：["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 * <p>
 * 示例 2：
 * 输入：turnedOn = 9
 * 输出：[]
 * <p>
 * 提示：
 * 0 <= turnedOn <= 10
 */
public class Solution401 {

    public static void main(String[] args) {
        System.out.println(readBinaryWatch(1));
        System.out.println(readBinaryWatch(9));
        System.out.println(readBinaryWatch(2));
    }

    /**
     * 前4后6, 小时不会从0开始, 说明小时必须亮灯
     */
    public static List<String> readBinaryWatch(int turnedOn) {
        List<String> ret = new ArrayList<>();
        for (int i = 0; i <= 4 && i <= turnedOn; i++) {
            List<Integer> hours = binary(i, 4, 11);
            if (null == hours || hours.isEmpty()) {
                continue;
            }
            List<Integer> minutes = binary(turnedOn - i, 6, 59);
            if (null == minutes || minutes.isEmpty()) {
                continue;
            }
            for (Integer hour : hours) {
                for (Integer minute : minutes) {
                    ret.add(new StringBuilder()
                            .append(hour)
                            .append(":")
                            .append(minute < 10 ? "0" : "")
                            .append(minute).toString());
                }
            }
        }
        return ret;
    }

    private static List<Integer> binary(int light, int limit, int max) {
        if (light > limit) {
            return null;
        }
        List<Integer> ret = new ArrayList<>();
        back(ret, light, limit, max, 0, 0);
        return ret;
    }

    static void back(List<Integer> ret, int reduce, int limit, int max,
            int val, int index) {
        if (val > max) {
            return;
        }
        if (reduce == 0) {
            ret.add(val);
            return;
        }
        reduce--;
        for (; index < limit; index++) {
            back(ret, reduce, limit, max, val + (1 << index), index + 1);
        }
    }

    /* 官方题解 */
    // 枚举
    public List<String> readBinaryWatch3(int turnedOn) {
        List<String> ans = new ArrayList<String>();
        for (int h = 0; h < 12; ++h) { // 枚举小时
            for (int m = 0; m < 60; ++m) {// 枚举分钟
                if (Integer.bitCount(h) + Integer.bitCount(m) == turnedOn) {// 看看亮灯数是否一致
                    ans.add(h + ":" + (m < 10 ? "0" : "") + m);
                }
            }
        }
        return ans;
    }

    // 二进制枚举
    public List<String> readBinaryWatch2(int turnedOn) {
        List<String> ans = new ArrayList<String>();
        // 小时4个灯 分钟6个灯 总共2^10=1024种可能
        for (int i = 0; i < 1024; ++i) {
            int h = i >> 6, m = i & 63; // 用位运算取出高 4 位和低 6 位
            if (h < 12 && m < 60 && Integer.bitCount(i) == turnedOn) {
                ans.add(h + ":" + (m < 10 ? "0" : "") + m);
            }
        }
        return ans;
    }

}
