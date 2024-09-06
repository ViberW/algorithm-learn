package com.welph.leecode.part_1_500.part_121_140;

import java.util.Arrays;

/**
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现了三次。找出那个只出现了一次的元素。
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 * 示例 1:
 * 输入: [2,2,3,2]
 * 输出: 3
 * 示例2:
 * <p>
 * 输入: [0,1,0,1,0,1,99]
 * 输出: 99
 */
public class Solution137 {

    public static void main(String[] args) {
        int[] nums = { 0, 1, 0, 1, 0, 1, 99 };
        System.out.println(singleNumber(nums));
        System.out.println(singleNumberExtend(nums, 1, 3));
    }

    /**
     * 从{@link Solution136} todo 位运算 值得回味
     * -- 巧妙 使用两个值保存
     * 0 ^ x = x,
     * x ^ x = 0；
     * x & ~x = 0,
     * x & ~0 =x;
     * ------------------------------------------------
     * 思路分析: 若使得一个数出现3次时能自动抵消为0,最后剩下的就是出现一次的
     * 由于一个二进制位表示0或1, 则能够记录出现1次或两次
     * x^0=x
     * x^x=0
     * 若需要记录3次, 则需要两个二进制位,使用两个变量,各取一位
     * ab^00=ab
     * ab^ab=00
     * ==========> a的第k位与b的第k位表示当前位出现的次数
     * 例如 x = x[7] x[6] ... x[1] x[0] 一个8位的二进制表示
     * => x= (a[7]b[7]) (a[6]b[6]) ... (a[0]b[0])
     * 相同位置上取一位, 完成 00 -> 01 -> 10 -> 00
     * --------------
     * ab 两个bit位表达3进制
     * -----
     * <a herf=
     * "https://leetcode.cn/problems/single-number-ii/solutions/8944/single-number-ii-mo-ni-san-jin-zhi-fa-by-jin407891/"/>
     * <a herf=
     * "https://leetcode.cn/problems/single-number-ii/solutions/746993/zhi-chu-xian-yi-ci-de-shu-zi-ii-by-leetc-23t6/"/>
     */
    /**
     * 注意观察 once 只有两种情况下转移后为 1 。
     * 一种是 once=0, twice=0, x=1
     * 另一种是 once=1, twice=0, x=0
     * 其他所有情况下 once 都转移为 0 。
     * 这两种情况都满足 x^once=1 并且 twice=0 ，所以 once 的转移就是 once =(x^once) & (~twice)
     * 
     * 同理，观察 twice 只有两种情况下转移后为 1 。
     * 一种是 once=1, twice=0, x=1
     * 另一种是 once=0, twice=1,x=0
     * 其他所有情况下 twice 都转移为 0 。这两种情况都满足 x^twice=1 并且 once^twice=1 ，
     * 所以 twice 的转移就是 twice = (x^twice) & (once^twice) 。
     * 但是！！！ once 已经抢先一步转移过了，所以值已经变掉了，一个解决方法就是用临时变量保存一下前一个状态的 once 值。
     * 另一个方法就是，这两种情况下，once 都会转移到0 ，
     * 所以判断条件直接用转移后的 once=0 就行了，随后转移就是 twice = (x^twice) & (~once)
     * --> <a herf="https://zhuanlan.zhihu.com/p/113849406" />
     * 
     */
    public static int singleNumber(int[] nums) {
        int a = 0; // 用于保存额外多出来的一值
        int b = 0; // 目标值 出现一次 变更后的为出现1次
        for (int num : nums) {
            /*
             * 理解.这里不用关注整体值, 仅关注一个位即可
             * 最初 a=0 b=0
             * 第一次遇见1 (01): b = (0^num[k])&~0 = 1 a = (0^num[k])&~1 = 0
             * 第二次遇见1 (10): b = (1^num[k])&~0 = 0 a = (0^num[k])&~0 = 1
             * 第三次遇见1 (00): b = (0^num[k])&~1 = 0 a = (1^num[k])&~1 = 0
             * -----tips: 这里~0=1 ~1=0
             */
            b = (b ^ num) & ~a;
            a = (a ^ num) & ~b;
        }
        return b;
    }

    // 转化前的公式, 对应上面
    public int singleNumber6(int[] nums) {
        int a = 0, b = 0;
        for (int num : nums) {
            // 不在a 且在b 关联上num为1 或者 在a 不在b 且num为0
            int aNext = (~a & b & num) | (a & ~b & ~num);
            // 不在a 且
            // 若num=1 则应该在aNext进位, 所以b若存在则为0
            // 若num=0 则b保持原样即可
            // 同理可得: b^num 代表经过此次num后还会存在b中的数字
            int bNext = ~a & (b ^ num);
            a = aNext;
            b = bNext;
        }
        return b;
    }

    public int singleNumber4(int[] nums) {
        int one = 0, two = 0, three = 0;
        int temp;
        for (int num : nums) {
            temp = one; // 存储出现一次的字符
            one ^= num; // 当前one 肯定是需要异或的,
            two |= temp & num; // 根据出现一次的字符, 现在肯定是2次, 进行或
            three = one & two; // 出现三次的可能 是 one和two的交集, 因为3次为 11
            one &= ~three; // one剔除3次
            two &= ~three; // two剔除3次
        }
        return one;
    }

    public int singleNumber5(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for (int num : nums) {
            twos |= ones & num;// 用Int32位任意一位出现了一次1的结果ones 和当期num 与 则同一个位置出现两次的会是1合并到twos 出现一次的保持twos原先的位
            ones ^= num;// 一直异或num 则Int中的某一位出现一次1 ones为1 两次则异或成0 三次还是1 但是会被后续操作清零
            threes = ones & twos;// ones和twos同时为1时 threes为1
            ones &= ~threes;// threes对应的位置为1 取反为0 和ones与则将对应位清零
            twos &= ~threes;
        }
        return ones;
    }

    public static int singleNumber2(int[] nums) {
        int a = 0;
        int b = 0;
        int tmp = 0;
        for (int next : nums) {
            tmp = (a & ~next) | (b & next); // 在a里面且next为0 或者 在b里面且next为1(进位)
            b = (b & ~next) | (~a & ~b & next); // 在b里面且next为0, 不在a和b且next为1(加1) 也只有0到1的才会把过去之前的给判断一遍
            a = tmp;
        }
        return b;
    }

    /**
     * 题解拓展:
     * 一个元素出现 k 次，其余元素出现 m 次（k < m），我们可以使用更多的状态变量来跟踪每个元素的出现次数
     * 
     * @param nums 整数数组
     * @return 目标k值
     */
    public static int singleNumberExtend(int[] nums, int k, int m) {
        int[] cnt = new int[32];
        for (int num : nums) {
            for (int i = 0; i < 32; i++) {
                if (((num >> i) & 1) == 1) {
                    cnt[i]++;
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < 32; i++) {
            if (cnt[i] % m == k) {
                ans += (1 << i);
            }
        }
        return ans;
    }
}
