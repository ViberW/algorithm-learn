package com.welph.leecode.part_281_300;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个仅包含数字0-9的字符串和一个目标值，在数字之间添加 二元 运算符（不是一元）+、-或*，返回所有能够得到目标值的表达式。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: num = "123", target = 6
 * 输出: ["1+2+3", "1*2*3"]
 * 示例2:
 * <p>
 * 输入: num = "232", target = 8
 * 输出: ["2*3+2", "2+3*2"]
 * 示例 3:
 * <p>
 * 输入: num = "105", target = 5
 * 输出: ["1*0+5","10-5"]
 * 示例4:
 * <p>
 * 输入: num = "00", target = 0
 * 输出: ["0+0", "0-0", "0*0"]
 * 示例 5:
 * <p>
 * 输入: num = "3456237490", target = 9191
 * 输出: []
 * <p>
 * <p>
 * 提示：
 * <p>
 * 0 <= num.length <= 10
 * num 仅含数字
 */
public class Solution282 {

    public static void main(String[] args) {
        System.out.println(addOperators("2147483648", -2147483648));
    }

    /**
     * xxxx | xxx  可以分为两部分,
     * 1. 左 右  两者之间又是不同组合,  + - *
     * 2.
     */
    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        compute(res, "", num.toCharArray(), 0, target, 0, 0);
        return res;
    }

    public static void compute(List<String> res, String str, char[] chars,
                               int start, long target, long current, long last) {
        if (start == chars.length && target == current) {
            res.add(str);
            return;
        }
        char c;
        long total = 0;
        for (int i = start; i < chars.length; i++) {
            c = chars[i];
            //左边加减乘除的值  //还要注意0的位置
            if (i > start && chars[start] == '0') {
                break;
            }
            total = total * 10 + (c - '0');
            if (str.length() == 0) {
                compute(res, total + "", chars, i + 1, target, total, total);
            } else {
                //+
                compute(res, str + "+" + total, chars, i + 1, target, current + total, total);
                //-
                compute(res, str + "-" + total, chars, i + 1, target, current - total, -total);
                //*  注意后面的就全部都是为*号
                compute(res, str + "*" + total, chars, i + 1, target, (current - last) + last * total, last * total);
            }
        }
    }
}
