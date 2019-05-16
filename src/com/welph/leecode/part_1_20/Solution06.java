package com.welph.leecode.part_1_20;

/**
 * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列(从左向右的竖Z形状)
 * <p>
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * k k j k
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
 *
 * @author: Admin
 * @date: 2019/5/9
 * @Description: {相关描述}
 */
public class Solution06 {

    public static void main(String[] args) {
        String s = "LEETCODEISHIRING";
        int numRows = 4;
        System.out.println(convert(s, numRows));
        System.out.println(convert01(s, numRows));
    }

    /**
     * 第一行是 2n-2
     * 第二行是  中间的前后相等于num
     * ...  2(n-3) + 2(i-1)+2
     * 2(n-4) +2
     * 第n行是 2n-2
     */
    public static String convert(String s, int numRows) {
        if (numRows == 0 || numRows == 1) {
            return s;
        }
        StringBuffer sbuffer = new StringBuffer();
        for (int i = 0; i < numRows; i++) {
            int j = i;
            boolean flag = true;
            while (j < s.length()) {
                sbuffer.append(s.charAt(j));
                if (i == 0 || i == numRows - 1) {
                    j += 2 * numRows - 2;
                } else {
                    //说明位于中间
                    if (flag) {
                        j += 2 * (numRows - i - 1);
                        flag = false;
                    } else {
                        j += 2 * i;
                        flag = true;
                    }
                }
            }
        }
        return sbuffer.toString();
    }

    public static String convert01(String s, int numRows) {
        if (numRows <= 1) return s;
        int len = s.length();
        char[] chars = s.toCharArray();
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
        int i = 0;
        while (i < len) {
            //先存放从上往下的每个添加一次列表
            for (int j = 0; j < numRows && i < len; ++j) {
                sbs[j].append(chars[i++]);
            }
            //从倒数第二个到正序第二个之间 每个再加上一个
            for (int j = numRows - 2; j >= 1 && i < len; --j) {
                sbs[j].append(chars[i++]);
            }
        }

        for (i = 1; i < numRows; i++) {
            sbs[0].append(sbs[i]);
        }
        return sbs[0].toString();
    }
}
