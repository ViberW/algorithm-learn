package com.welph.leecode.part_1_500.part_321_340;

/**
 * 给定一个整数，写一个函数来判断它是否是 3的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3x
 * <p>
 * 示例 1：
 * 输入：n = 27
 * 输出：true
 * <p>
 * 示例 2：
 * 输入：n = 0
 * 输出：false
 * <p>
 * 示例 3：
 * 输入：n = 9
 * 输出：true
 * <p>
 * 示例 4：
 * 输入：n = 45
 * 输出：false
 * <p>
 * 提示：
 * -231 <= n <= 231 - 1
 * <p>
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 */
public class Solution326 {

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(1));
        System.out.println(isPowerOfThree(9));
        //11
        //1001
        //11011
        //1010001
        //11110011
        /*System.out.println(Integer.toBinaryString(3));
        System.out.println(Integer.toBinaryString((int) Math.pow(3, 2)));
        System.out.println(Integer.toBinaryString((int) Math.pow(3, 3)));
        System.out.println(Integer.toBinaryString((int) Math.pow(3, 4)));
        System.out.println(Integer.toBinaryString((int) Math.pow(3, 5)));
        System.out.println(Integer.toBinaryString((int) Math.pow(3, 6)));
        System.out.println(Integer.toBinaryString((int) Math.pow(3, 7)));*/
    }

    //先来一份递归的
    public static boolean isPowerOfThree(int n) {
        if (n == 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        if (n % 3 != 0) {
            return false;
        }
        return isPowerOfThree(n / 3);
    }

    /**
     * 这里的1162261467为3的N次幂 在Integer范围内的最大值: 3^19次方
     * @param n
     * @return
     */
    public boolean isPowerOfThree3(int n) {
        return n > 0 && 1162261467 % n == 0;
    }
}
