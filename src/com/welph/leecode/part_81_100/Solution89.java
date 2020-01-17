package com.welph.leecode.part_81_100;

import java.util.ArrayList;
import java.util.List;

/**
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * <p>
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * <p>
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * <p>
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 示例 2:
 * <p>
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 */
public class Solution89 {

    public static void main(String[] args) {
        System.out.println(grayCode(5));
        System.out.println(grayCode2(5));
    }

    /**
     * n = n + 1(n).revesert
     */
    public static List<Integer> grayCode(int n) {
        return grayCodeItem(n, n);
    }

    private static List<Integer> grayCodeItem(int n, int origin) {
        if (n == 0) {
            return new ArrayList<Integer>(1 << origin) {{
                add(0);
            }};
        }
        List<Integer> integers = grayCodeItem(n - 1, origin);
        int normal = 1 << (n - 1);
        for (int i = integers.size() - 1; i >= 0; i--) {
            integers.add(normal + integers.get(i));
        }
        return integers;
    }

    /**
     * 镜像法， 🐂🍺
     * 设 nn 阶格雷码集合为 G(n)G(n)，则 G(n+1)G(n+1) 阶格雷码为：
     * * 给 G(n)G(n) 阶格雷码每个元素二进制形式前面添加 0，得到 G'(n)
     * * 设 G(n)G(n) 集合倒序（镜像）为 R(n)R(n)，给 R(n)R(n) 每个元素二进制形式前面添加 11，得到 R'(n)
     * * G(n+1) = G'(n) ∪ R'(n)G(n+1)拼接两个集合即可得到下一阶格雷码。
     */
    public static List<Integer> grayCode2(int n) {
        List<Integer> result = new ArrayList<>();
        result.add(0);
        int index = 1;
        for (int i = 0; i < n; i++) {
            for (int j = result.size() - 1; j >= 0; j--) {
                result.add(index + result.get(j));
            }
            index <<= 1;
        }
        return result;
    }
}
