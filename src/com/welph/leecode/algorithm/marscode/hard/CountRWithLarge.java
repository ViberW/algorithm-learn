package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小U非常喜欢字符'R'，并且定义字符串的权值为字符'R'的出现次数。
 * 现在她手上有一个长度为n的字符串s，由字符'R'和'B'组成。
 *
 * 她想知道，在所有长度为n、仅由字符'R'和'B'组成的字符串中，字典序不小于字符串s的字符串的权值之和是多少。由于结果可能很大，
 * 答案需要对1000000007取模。
 *
 * 例如，字符串"RRBRB"的权值为 3，因为其中包含 3 个字符'R'
 */
public class CountRWithLarge {

    /*
        RBR 每一位都需要考虑替换的因素
       ==>  主要是R不能被替换, B可以被替换
        RBR RRR RRB =7
     */
    public static int solution(int n, String s) {
        int mod = 1000000007;

        /*
            假设有K位随意变化, 那么第m个位的总R数为: (total+2^(m-1))+ total 前者为m处选择R 后者为m处选择B
            => 2*total+2^(m-1)
            而上一次的 2*PreTotal+2^(m-2) 代入公示: 2( 2*PreTotal+2^(m-2))+2^(m-1) => 2^2*PreTotal+ 2* 2^(m-1)
            => 不断下去最终R的总数为:  m * 2^(m-1)
            -----------
            可以这么理解: K位总的可能是: 2^K次方. 那么每一位值的数: K*2^K. 由于只有R和B所以占一半: K*2^(K-1)
         */

        long total = 0;
        int preCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'R') {
                preCount++;
            } else {
                //尝试替换为R
                int perhaps = 1 << (n - i - 1); //能变化的总和
                int countR = (int) ((n - i - 1) * Math.pow(2, (n - i - 1 - 1)));
                total = (total + ((preCount + 1L) * perhaps) % mod + countR) % mod;
            }
        }
        total = (total + preCount) % mod;
        return (int) total;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, "RBR") == 7);
        System.out.println(solution(4, "RRBB") == 12);
        System.out.println(solution(2, "BB") == 4);
    }
}
