package com.welph.leecode.part_1_500.part_1_20;


/**
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * @author: Admin
 * @date: 2019/5/9
 * @Description: {相关描述}
 */
public class Solution09 {

    public static void main(String[] args) {
        int i = 10;
        System.out.println(isPalindrome(i));
    }

    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        long reverse = 0;
        for (int i = x; i != 0; i /= 10) {
            reverse = reverse * 10 + i % 10;
        }
        return reverse == x;
    }
}
