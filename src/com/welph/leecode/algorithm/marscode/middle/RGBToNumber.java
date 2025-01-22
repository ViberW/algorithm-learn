package com.welph.leecode.algorithm.marscode.middle;

/**
 * 小M需要一个函数，用于将RGB颜色值转换为相应的十六进制整数值。
 * RGB色值以字符串的形式给出，如"rgb(192, 192, 192)"，需要转换为对应的整数值。
 */
public class RGBToNumber {
    public static int solution(String rgb) {
        rgb = rgb.substring(4, rgb.length() - 1);
        String[] split = rgb.split(",");
        int val = 0;
        for (int i = 0; i < split.length; i++) {
            val = (val << 8) + Integer.parseInt(split[i].trim());
        }
        return val;
    }

    public static void main(String[] args) {
        //  You can add more test cases here
        System.out.println(solution("rgb(192, 192, 192)") == 12632256);
        System.out.println(solution("rgb(100, 0, 252)") == 6553852);
        System.out.println(solution("rgb(33, 44, 55)") == 2174007);
    }
}
