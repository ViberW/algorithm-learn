package com.welph.leecode.algorithm.marscode;

/**
 * 小M正在研究一个由字母'c'和'h'组成的字符串，他的任务是计算该字符串所有子串中，出现的子串"chhc"的总次数。
 * 我们定义一个字符串的权值为其包含"chhc"子串的数量。你的任务是帮助小M求出整个字符串中所有子串的权值之和。
 */
public class SumChhcStr {

    public static int solution(String s) {
        // write code here
        int n = s.length();
        if (n < 4) {
            return 0;
        }
        int hash = 0;
        for (int i = 0; i < 3; i++) {
            hash = hash * 10 + (s.charAt(i) == 'c' ? 1 : 0);
        }
        int weight = 0;
        int singleWeight = 0;
        int lastIndex = -1;
        int ret = 0;
        for (int i = 3; i < n; i++) {
            hash = (hash % 1000) * 10 + (s.charAt(i) == 'c' ? 1 : 0);
            // i-3
            if (1001 == hash) {
                /*
                chhchhc
                第一次. i=3, singleWeight = 1; 代表在0及以前有子串有1个
                第二次. i=6, singleWeight = 1 + 3 代表层级种有两段, 第一段可生成1个不同子串. 第二个可生成3个不同子串
                weight +=  singleWeight 在第二次时就变为
                 */
                singleWeight += i - 3 - lastIndex; //每一段单个长度
                weight += singleWeight; //累加一次, 相当于前面每一段的乘数加1
                ret += weight;
                lastIndex = i - 3;
            } else {
                ret += weight;
            }
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println(solution("chhchhc") == 8);
        System.out.println(solution("chhcchhcchhc") == 43);
        System.out.println(solution("hhhh") == 0);
    }
}
