package com.welph.leecode.part_381_400;

/**
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 * <p>
 * 对于 1 字节的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 * 对于 n 字节的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 * |这是 UTF-8 编码的工作方式：
 * |
 * |   Char. number range  |        UTF-8 octet sequence
 * |      (hexadecimal)    |              (binary)
 * |   --------------------+---------------------------------------------
 * |   0000 0000-0000 007F | 0xxxxxxx
 * |   0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 * |   0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 * |   0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * |给定一个表示数据的整数数组，返回它是否为有效的 utf-8 编码。
 * <p>
 * 注意：
 * 输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
 * <p>
 * 示例 1：
 * data = [197, 130, 1], 表示 8 位的序列: 11000101 10000010 00000001.
 * 返回 true 。
 * 这是有效的 utf-8 编码，为一个2字节字符，跟着一个1字节字符。
 * <p>
 * 示例 2：
 * data = [235, 140, 4], 表示 8 位的序列: 11101011 10001100 00000100.
 * 返回 false 。
 * 前 3 位都是 1 ，第 4 位为 0 表示它是一个3字节字符。
 * 下一个字节是开头为 10 的延续字节，这是正确的。
 * 但第二个延续字节不以 10 开头，所以是不符合规则的。
 */
public class Solution393 {

    public static void main(String[] args) {
        System.out.println(validUtf8(new int[]{197, 130, 1}));//true
        System.out.println(validUtf8(new int[]{235, 140, 4}));//false
        System.out.println(validUtf8(new int[]{228, 189, 160, 229, 165, 189, 13, 10})); //true
        System.out.println(validUtf8(new int[]{230, 136, 145})); //true
        System.out.println(validUtf8(new int[]{240, 162, 138, 147, 17})); //true
        System.out.println(validUtf8(new int[]{237})); //true
    }

    public static boolean validUtf8(int[] data) {
        int length = data.length;
        int bytes = 0;
        int datum;
        for (int i = 0; i < length; i++) {
            datum = data[i];
            if (((datum >> 7) & 1) == 1) {
                if (bytes > 0) {
                    if (((datum >> 6) & 1) != 0) {
                        return false;
                    }
                    bytes--;
                } else {
                    for (int j = 6; j >= 3; j--) {
                        if (((datum >> j) & 1) == 1) {
                            bytes++;
                        } else {
                            break;
                        }
                    }
                    if (bytes == 0 || bytes > 3) {
                        return false;
                    }
                }
            } else if (bytes > 0) {
                return false;
            }
        }
        return bytes == 0;
    }
}
