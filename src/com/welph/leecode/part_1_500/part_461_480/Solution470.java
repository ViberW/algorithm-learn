package com.welph.leecode.part_1_500.part_461_480;

/**
 * 已有方法rand7可生成 1 到 7 范围内的均匀随机整数，
 * 试写一个方法rand10生成 1 到 10 范围内的均匀随机整数。
 * <p>
 * 不要使用系统的Math.random()方法。
 * <p>
 * 示例 1:
 * 输入: 1
 * 输出: [7]
 * <p>
 * 示例 2:
 * 输入: 2
 * 输出: [8,4]
 * <p>
 * 示例 3:
 * 输入: 3
 * 输出: [8,1,10]
 * <p>
 * 提示:
 * rand7已定义。
 * 传入参数:n表示rand10的调用次数。
 * <p>
 * 进阶:
 * rand7()调用次数的期望值是多少?
 * 你能否尽量少调用 rand7() ?
 */
public class Solution470 {

    public static void main(String[] args) {
        System.out.println(rand10());
    }

    /**
     * 枚举如下：
     * >     a	1	2	3	4	5	6	7
     * b
     * 1		2	3	4	5	6	7	8
     * 2		3	4	5	6	7	8	9
     * 3		4	5	6	7	8	9	0
     * 4		5	6	7	8	9	0	1
     * 5		6	7	8	9	0	1	2
     * 6		7	8	9	0	1	2	3
     * 7		8	9	0	1	2	3	4
     * 去掉右上角的
     * 6	7	8
     * 7	8	9
     * 8	9	0      后
     * <p>
     * 每个数字的出现次数为4次，0-9的概率相同
     */
    public static int rand10() {
        int a = rand7();
        int b = rand7();
        if (a > 4 && b < 4) {
            return rand10();
        } else {
            return (a + b) % 10 + 1;
        }
    }

    /**
     * 测试调用方法
     */
    public static int rand7() {
        return 0;
    }
}
