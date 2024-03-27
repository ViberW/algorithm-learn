package com.welph.leecode.part_1_500.part_401_420;

/**
 * 一个强密码应满足以下所有条件：
 * <p>
 * 由至少6个，至多20个字符组成。
 * 至少包含一个小写字母，一个大写字母，和一个数字。
 * 同一字符不能连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 是可以的)。
 * 编写函数 strongPasswordChecker(s)，s 代表输入字符串，
 * 如果 s 已经符合强密码条件，则返回0；否则返回要将 s 修改为满足强密码条件的字符串所需要进行修改的最小步数。
 * <p>
 * 插入、删除、替换任一字符都算作一次修改。
 */
public class Solution420 {

    public static void main(String[] args) {
        System.out.println(strongPasswordChecker("woaiZhongHua001"));
    }

    /**
     * todo 暂时不太会
     */
    public static int strongPasswordChecker(String password) {
        int length = password.length();

        return 0;
    }

    /* 官方题解 */
    public int strongPasswordChecker2(String password) {
        int n = password.length();
        int hasLower = 0, hasUpper = 0, hasDigit = 0;
        for (int i = 0; i < n; ++i) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                hasLower = 1;
            } else if (Character.isUpperCase(ch)) {
                hasUpper = 1;
            } else if (Character.isDigit(ch)) {
                hasDigit = 1;
            }
        }
        int categories = hasLower + hasUpper + hasDigit;

        if (n < 6) {
            // 严格不小于6个长度
            // 至少包含一个小写字母，一个大写字母，和一个数字
            return Math.max(6 - n, 3 - categories);
        } else if (n <= 20) {
            int replace = 0;
            int cnt = 0;
            char cur = '#';
            // 计算连续3个相同的中间选取一个替换一个字符的方式
            for (int i = 0; i < n; ++i) {
                char ch = password.charAt(i);
                if (ch == cur) {
                    ++cnt;
                } else {
                    replace += cnt / 3;
                    cnt = 1;
                    cur = ch;
                }
            }
            replace += cnt / 3;
            // 考虑替换值和保证大小写和数字的最大值
            return Math.max(replace, 3 - categories);
        } else {
            // 替换次数和删除次数
            int replace = 0, remove = n - 20;// 预期最少移除的字符的数量
            // k mod 3 = 1 的组数，即删除 2 个字符可以减少 1 次替换操作
            int rm2 = 0;
            int cnt = 0;
            char cur = '#';

            for (int i = 0; i < n; ++i) {
                char ch = password.charAt(i);
                if (ch == cur) {
                    ++cnt;
                } else {
                    if (remove > 0 && cnt >= 3) {
                        // 若余数为0,则删除1个字符会使得cnt/3 减少1
                        if (cnt % 3 == 0) {
                            // 如果是 k % 3 = 0 的组，那么优先删除 1 个字符，减少 1 次替换操作
                            --remove;
                            --replace;
                        } else if (cnt % 3 == 1) { // 若余数为1,则删除2个字符会使得cnt/3 减少1
                            // 如果是 k % 3 = 1 的组，那么存下来备用
                            ++rm2;
                        }
                        // k % 3 = 2 的组无需显式考虑
                    }
                    replace += cnt / 3;
                    cnt = 1;
                    cur = ch;
                }
            }
            // 针对最后的情况
            if (remove > 0 && cnt >= 3) {
                if (cnt % 3 == 0) {
                    --remove;
                    --replace;
                } else if (cnt % 3 == 1) {
                    ++rm2;
                }
            }
            replace += cnt / 3;

            // 下面是分别针对连续2n+1 和2n+2的情况处理
            // 我们可以分别讨论
            // 若是没有2n+2时2n+1的rm2是个什么情况, 只需要删除2个字符, 若是不够删除数,就替换
            // 若是没有2n+1时2n+2是个什么情况, 需要删除每5个字符删除3个字符. 不够的删除的就替换

            // 使用 k % 3 = 1 的组的数量，由剩余的替换次数、组数和剩余的删除次数共同决定
            int use2 = Math.min(Math.min(replace, rm2), remove / 2);
            replace -= use2; // 能够删除掉的2n+1, 剩下的replace就是要替换
            remove -= use2 * 2;
            // 由于每有一次替换次数就一定有 3 个连续相同的字符（k / 3 决定），因此这里可以直接计算出使用 k % 3 = 2 的组的数量
            int use3 = Math.min(replace, remove / 3); // 剩下的替换和剩下的删除看看还能帮助替换减少多少
            replace -= use3;
            remove -= use3 * 3;
            return (n - 20) + Math.max(replace, 3 - categories);
        }
    }

}
