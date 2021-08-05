package com.welph.leecode.algorithm.thinking;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [01字典树] 特殊的字典树, 节点数据集仅仅为{0,1}
 * @since 2021/8/3 17:25
 */
public class Trie01_44 {

    public static void main(String[] args) {
        insert(4);
        insert(7);
        insert(3);
        insert(11);
        System.out.println(findMax(15));
        System.out.println(findMax(6));
    }

    static int maxBit = 31;
    static int cnt = 1;
    static int[][] next = new int[100][2];
    static int[] num = new int[100];

    public static void insert(int n) {
        int cur = 1;
        for (int i = maxBit; i >= 0; i--) {
            int bit = n >> i & 1;
            if (next[cur][bit] == 0) {
                next[cur][bit] = ++cnt;
            }
            cur = next[cur][bit];
        }
        num[cur] = n;
    }

    static int findMax(int x) { //寻找异或的最大值
        int cur = 1;
        for (int i = maxBit; i >= 0; i--) {
            int bit = x >> i & 1;
            if (next[cur][bit ^ 1] != 0) { // 优先走与当前位不同的路径--异或
                cur = next[cur][bit ^ 1];
            } else {
                cur = next[cur][bit];  //一定是32位的, 所有每个cur在{0,1}至少有一个
            }
        }
        return x ^ num[cur];
    }

}
