package com.welph.leecode.part_1_500.part_461_480;

/**
 * 编写一个函数来验证输入的字符串是否是有效的 IPv4 或IPv6 地址。
 * <p>
 * 如果是有效的 IPv4 地址，返回 "IPv4" ；
 * 如果是有效的 IPv6 地址，返回 "IPv6" ；
 * 如果不是上述类型的 IP 地址，返回 "Neither" 。
 * IPv4地址由十进制数和点来表示，每个地址包含 4 个十进制数，其范围为0 -255，用(".")分割。比如，172.16.254.1；
 * <p>
 * 同时，IPv4 地址内的数不会以 0 开头。比如，地址172.16.254.01 是不合法的。
 * IPv6地址由 8 组 16 进制的数字来表示，每组表示16 比特。这些组数字通过 (":")分割。
 * 比如,2001:0db8:85a3:0000:0000:8a2e:0370:7334 是一个有效的地址。而且，
 * 我们可以加入一些以 0 开头的数字，字母可以使用大写，也可以是小写。
 * 所以，2001:db8:85a3:0:0:8A2E:0370:7334 也是一个有效的 IPv6 address地址 (即，忽略 0 开头，忽略大小写)。
 * 然而，我们不能因为某个组的值为 0，而使用一个空的组，以至于出现 (::) 的情况。
 * 比如，2001:0db8:85a3::8A2E:0370:7334 是无效的 IPv6 地址。
 * * 同时，在 IPv6 地址中，多余的 0 也是不被允许的。比如，02001:0db8:85a3:0000:0000:8a2e:0370:7334 是无效的。
 * <p>
 * 示例 1：
 * 输入：IP = "172.16.254.1"
 * 输出："IPv4"
 * 解释：有效的 IPv4 地址，返回 "IPv4"
 * <p>
 * 示例 2：
 * 输入：IP = "2001:0db8:85a3:0:0:8A2E:0370:7334"
 * 输出："IPv6"
 * 解释：有效的 IPv6 地址，返回 "IPv6"
 * <p>
 * 示例 3：
 * 输入：IP = "256.256.256.256"
 * 输出："Neither"
 * 解释：既不是 IPv4 地址，又不是 IPv6 地址
 * <p>
 * 示例 4：
 * 输入：IP = "2001:0db8:85a3:0:0:8A2E:0370:7334:"
 * 输出："Neither"
 * <p>
 * 示例 5：
 * 输入：IP = "1e1.4.5.6"
 * 输出："Neither"
 * <p>
 * 提示：
 * IP 仅由英文字母，数字，字符 '.' 和 ':' 组成。
 */
public class Solution468 {

    public static void main(String[] args) {
        System.out.println(validIPAddress("172.16.254.1"));//"IPv4"
        System.out.println(validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334"));//"IPv6"
        System.out.println(validIPAddress("256.256.256.256"));//"Neither"
        System.out.println(validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:"));//"Neither"
        System.out.println(validIPAddress("1e1.4.5.6"));//"Neither"
        System.out.println(validIPAddress("12..33.4"));//"Neither"
        System.out.println(validIPAddress("01.01.01.01"));//"Neither"
        System.out.println(validIPAddress("1.1.1.01"));//"Neither"
        System.out.println(validIPAddress("2001:0db8:85a3:00000:0:8A2E:0370:7334"));//"Neither"
    }

    /**
     * ipv4:  4组10进制
     * ipv6:  8组16进制
     */
    public static String validIPAddress(String queryIP) {
        int length = queryIP.length();
        if (length < 7 || length > 39) {
            return "Neither";
        }
        char[] chars = queryIP.toCharArray();
        if (check(chars, 0, 0, true)) {
            return "IPv4";
        }
        if (check(chars, 0, 0, false)) {
            return "IPv6";
        }
        return "Neither";
    }

    public static boolean check(char[] chars, int l, int depth, boolean type) {
        if (type) {
            int ipv4 = isIpv4(chars, l);
            if (ipv4 > l) {
                if (depth == 3) {
                    return ipv4 == chars.length;
                } else if (ipv4 == chars.length) {
                    return false;
                }
                return check(chars, ipv4 + 1, depth + 1, true);
            }
        } else {
            int ipv6 = isIpv6(chars, l);
            if (ipv6 > l) {
                if (depth == 7) {
                    return ipv6 == chars.length;
                } else if (ipv6 == chars.length) {
                    return false;
                }
                return check(chars, ipv6 + 1, depth + 1, false);
            }
        }
        return false;
    }

    public static int isIpv4(char[] chars, int l) {
        int size = 0;
        int value = 0;
        char c;
        int i = l;
        for (; i < chars.length; i++) {
            c = chars[i];
            if (c == '.') {
                return i > l + 1 && chars[l] == '0' ? -1 : i;
            }
            if (++size == 4) {
                return -1;
            }
            if (c < '0' || c > '9') {
                return -1;
            }
            value = value * 10 + (c - '0');
            if (value > 255) {
                return -1;
            }
        }
        return i != chars.length || (i > l + 1 && chars[l] == '0') ? -1 : i;
    }

    public static int isIpv6(char[] chars, int l) {
        int size = 0;
        char c;
        int i = l;
        for (; i < chars.length; i++) {
            c = chars[i];
            if (c == ':') {
                return i;
            }
            if (++size == 5) {
                return -1;
            }
            if (!((c >= '0' && c <= '9')
                    || ((c >= 'a' && c <= 'f'))
                    || ((c >= 'A' && c <= 'F')))) {
                return -1;
            }
        }
        return i == chars.length ? i : -1;
    }
}
