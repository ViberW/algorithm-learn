package com.welph.leecode.algorithm.string;

/**
 * KMP算法
 * <p>
 * 算是对BM算法的提升; 针对BM算法的suffix数组, prefix数组 坏字符hash
 * <p>
 * <p>
 * ---- 同样有两个定义: 坏字符(不能匹配的)  好前缀 (已经匹配过的前缀子串)
 * ---> 好前缀中 找到最长的后缀子串的好前缀子串{u} 长度为k
 * ------->称之为 [最长可匹配后缀子串] ,对应的前缀子串为 [最长可匹配前缀子串]
 * --> 之后移动 j-k (坏字符位置为j) 相等于是 j更新为k , i不变 (i为主串中坏字符出现的位置)
 * <p>
 * <p>
 * ~~~~ 因为不涉及主串,可以预处理得到醉成可匹配子串 next数组  也称之为[失效函数]
 * ......  next数组的下标为 每个前缀串[结尾字符]的下标,
 * ......  值为这个前缀串的[最长可匹配前缀子串]的结尾字符下标 (因为最长可匹配长度前缀子串的开始始终为0)
 * 举例子 :  a b a b a c d
 * ...........字符.......值  (注意最长可匹配子串不能和本身前缀串重合)
 * 则 next[0]  a      -1  (单个字符就没有)
 * .  next[1]  ab     -1  找不到
 * .  next[2]  aba     0   找到了0的位置 就是本身
 * .  next[3]  abab    1   找到了0位置的 ab  对应的结尾字符就是1
 * .  next[4]  ababa   2   找到了2位置的结尾字符
 * .  next[5]  ababac  -1  找不到
 * .  最后一位不用考虑了
 */
public class KMPDemo {

    public static void main(String[] args) {
        String s1 = "cababc";
        String s2 = "ac";
        System.out.println(match(s1.toCharArray(), s1.length(), s2.toCharArray(), s2.length()));
    }

    private static boolean match(char[] a, int n, char[] b, int m) {

        int[] next = getNexts(b, m);
        int j = 0;
        for (int i = 0; i < n; ++i) {
            while (j > 0 && a[i] != b[j]) { //这里为好前缀  不同于BM算法 所以是从前往后
                // 一直找到a[i]和b[j]
                j = next[j - 1] + 1; //一旦有不匹配的,再一次从 j- k位置开始的匹配
            }
            //当没有匹配的时候next[]=-1 则j=0. --此时相当于是上一次没有匹配的,
            // 或者a[i]==b[j] 跳出循环
            if (a[i] == b[j]) {
                ++j;
            }
            if (j == m) {
                //i - m + 1;
                return true;
            }
        }
        return false;
    }

    /**
     * 预处理next数组 (失效函数)  --动态规划
     * 假定当前处理的为 i
     * 1. 已经计算出了 next[i-1] =k-1
     * ......当b[i] = b[k] 则 next[i] = k;
     * ......当b[i] != b[k]
     * 此时我们需要处理: 假定b[0,i]最长可匹配后缀子串为b[r,i]
     * 则可以得知 b[r,i-1]为b[0,i-1]的可匹配子串(注意,这里不一定是最长的)
     * ....因为 b[k] !=b[i] (上面的条件)
     * ....再假设 x 为次长子串b[x,i-1] 那么可以考察[次长] b[x ,i-1]的可匹配前缀子串b[0,i-1-x]的下一个字符b[i-x]是否等于b[i]
     * ----> 如果等于,则b[x,i]就是b[0,i]的最长可匹配后缀子串, next[i] = i-x
     * <p>
     * ~~~~ 因为[次长]可匹配后缀子串一定包含在最长可匹配后缀子串中;
     * 而最长可匹配后缀子串= 最长可匹配前缀子串b[0,y], 则查询b[0,i-1]的次长后缀子串,相等于查找b[0,y]的最长后缀子串
     * ~~~~~~~~ 次长可匹配后缀子串 其实就是 最长可匹配后缀子串的最长可匹配前缀子串的 最长可匹配后缀子串;
     *
     * @param b
     * @param m
     * @return
     */
    public static int[] getNexts(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; ++i) {
            //k != -1 说明i-1有匹配后缀子串 之后就找b[i]=b[k] .或者为-1
            while (k != -1 && b[k + 1] != b[i]) { //不断寻找b[0,i-1]的次长后缀子串且b[i]=b[k]
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                //找到了, 则k+1
                ++k;
            }
            next[i] = k;
        }
        return next;
    }
}
