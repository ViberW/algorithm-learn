package com.welph.leecode.one;

import java.util.*;

/**
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给定一个整数，将其转为罗马数字。输入确保在 1 到 3999 的范围内。
 * <p>
 * 输入: 1994
 * 输出: "MCMXCIV"
 * 解释: M = 1000, CM = 900, XC = 90, IV = 4.
 *
 * @author: Admin
 * @date: 2019/5/14
 * @Description: {相关描述}
 */
public class Solution12 {

    public static void main(String[] args) {
        System.out.println(intToRoman(58));
    }

    public static String intToRoman(int num) {
        Map<Integer, String> chars = new LinkedHashMap<>();
        chars.put(1, "I");
        chars.put(5, "V");
        chars.put(10, "X");
        chars.put(50, "L");
        chars.put(100, "C");
        chars.put(500, "D");
        chars.put(1000, "M");
        //取余判断4,9 和5
        int i = 0;
        StringBuffer sbuffer = new StringBuffer();
        int k = 1;
        String cur;
        while (num > 0) {
            i = num % 10;
            if (i % 5 > 3) {
                //说明在4或9
                cur = chars.get(k) + ((i > 5) ? chars.get(k * 10) : chars.get(k * 5));
            } else {
                cur = ((i < 5) ? "" : chars.get(5 * k)) + getI(i % 5, chars.get(k));
            }
            sbuffer.insert(0, cur);
            num = num / 10;
            k *= 10;
        }
        return sbuffer.toString();
    }

    private static String getI(int len, String ch) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            sb.append(ch);
        }
        return sb.toString();
    }

    /**
     * 这个方法感觉有点取巧 但胜在快速
     */
    public String intToRoman01(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }
}
