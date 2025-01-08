package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小M定义了一种特殊的二叉树，称之为“好二叉树”。
 * 当且仅当这个二叉树中的所有节点的孩子数量为偶数（即每个节点要么没有孩子，要么有两个孩子），
 * 该二叉树才被称为好二叉树。
 *
 * 现在，小M想知道，给定 n 个节点，可以构成多少种不同形态的好二叉树？答案需要对 10^9 + 7 取模。
 */
public class GoodBinaryTree {

    //{@link Solution96}
    public static int solution(int n) {
        int mod = 1000000007;
        //剩下来的就是一组数, 拆分成好二叉树
        //这道题有一点没有说明白, 就是顺序是要固定的, 所以不需要乘以n*(n+1)/2
        long[] G = new long[n + 1];
        G[1] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 2; j < i; ++j) { //需要保证左右必须要有节点
                G[i] = (G[i] + G[j - 1] * G[i - j]) % mod; //代表以j为根节点,左右子树的数量
            }
        }
        return (int) G[n];
    }

    public static void main(String[] args) {
        System.out.println(solution(5) == 2);
        System.out.println(solution(7) == 5);
        System.out.println(solution(9) == 14);
    }
}
