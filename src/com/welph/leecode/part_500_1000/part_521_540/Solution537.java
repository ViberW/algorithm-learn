package com.welph.leecode.part_500_1000.part_521_540;

/**
 * 复数 可以用字符串表示，遵循 "实部+虚部i" 的形式，并满足下述条件：
 * <p>
 * 实部 是一个整数，取值范围是 [-100, 100]
 * 虚部 也是一个整数，取值范围是 [-100, 100]
 * i2 == -1
 * 给你两个字符串表示的复数 num1 和 num2 ，请你遵循复数表示形式，返回表示它们乘积的字符串。
 * <p>
 * 示例 1：
 * 输入：num1 = "1+1i", num2 = "1+1i"
 * 输出："0+2i"
 * 解释：(1 + i) * (1 + i) = 1 + i2 + 2 * i = 2i ，你需要将它转换为 0+2i 的形式。
 * <p>
 * 示例 2：
 * 输入：num1 = "1+-1i", num2 = "1+-1i"
 * 输出："0+-2i"
 * 解释：(1 - i) * (1 - i) = 1 + i2 - 2 * i = -2i ，你需要将它转换为 0+-2i 的形式。
 * <p>
 * 提示：
 * num1 和 num2 都是有效的复数表示。
 */
public class Solution537 {

    public static void main(String[] args) {
        System.out.println(complexNumberMultiply("1+1i", "1+1i"));
        System.out.println(complexNumberMultiply("1+-1i", "1+-1i"));
    }

    /**
     * 按照 + 进行拆分
     */
    public static String complexNumberMultiply(String num1, String num2) {
        String[] split1 = num1.split("\\+");
        String[] split2 = num2.split("\\+");
        int real1 = Integer.parseInt(split1[0]);
        int real2 = Integer.parseInt(split2[0]);
        int image1 = Integer.parseInt(split1[1].substring(0, split1[1].length() - 1));
        int image2 = Integer.parseInt(split2[1].substring(0, split2[1].length() - 1));
        int real = real1 * real2 + (image1 * image2) * -1;
        int image = image1 * real2 + image2 * real1;
        return real + "+" + image + "i";
    }
}
