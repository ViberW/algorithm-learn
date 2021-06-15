package com.welph.leecode.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * BM 算法 --- 文本编辑器的匹配
 * ----  按照模式串的下边从大往小匹配;
 * 坏字符规则:
 * -- 坏字符: 某个字符不匹配模式串(当前位置)时,称为坏字符
 * ----> 当发生不匹配的时候，我们把坏字符对应的模式串中的字符下标记作 [si]。
 * ----> 如果坏字符在模式串中存在，我们把这个坏字符在模式串中的下标记作 xi。
 * ----> 如果不存在，我们把 xi 记作 -1。那模式串往后移动的位数就等于 si-xi。
 * （注意，我这里说的下标，都是字符在模式串的下标,
 * ~~~~~~~ 使用[散列表] 保存模式串中每个字符出现的最后一个位置
 * ...... 如果坏字符在模式串里多处出现，那我们在计算 xi 的时候，选择最靠后的那个）。
 * 好后缀规则:
 * --- 存在(计算 xi 的时候，选择最靠后的那个)的下标 大于 相对于当前坏字符的下标
 * --- 当前能够匹配好的后缀子子串;
 * ---> 要看好后缀在模式串中，是否有另一个匹配的子串
 * ---> 从好后缀的后缀子串中，找一个最长的并且能跟模式串的前缀子串匹配的 (模式串开头移动到能匹配的位置)
 * 好后缀的核心:
 * _____{1}在模式串中，查找跟好后缀匹配的另一个子串；(这里仅仅是子串.非前缀子串(前缀子串代表最开始为0))
 * _____{2}在好后缀的后缀子串中，查找最长的、能跟模式串前缀子串匹配的后缀子串； --防止移动过头.
 * ~~~~~~[ 针对{1} 可以预处理 计算出模式每个后缀子串可匹配的子串]这里要预处理的数据方式:
 * 1.定义模式串从最后一个字符往前的(后缀子串)
 * 2. 引入最关键的变量suffix数组(数组下标为后缀子串的长度). 数组的值为与后缀子串{u}匹配的子串{u*}的起始下标值
 * --> 如  c a b c a b.
 * --> 那么 suffix存储的是
 * [1] --存储最后一个字符b = 2 (因为在模式传中前缀中有下标为2的b)
 * [2] --存储最后一个字符a b = 1 (因为在模式传中前缀中有下标为1开始的ab)
 * [3] --存储最后一个字符c a b = 1 (因为在模式传中前缀中有下标为0开始的cab)
 * [4] --存储最后一个字符b c a b = -1 (因为在模式传中前缀没有匹配的 --因为从前往后 不能超多前后缀的位置)
 * [5] --存储最后一个字符a b c a b = -1 (因为在模式传中前缀没有匹配的 --因为从前往后 不能超多前后缀的位置)
 * 注意[6]就没必要计算了.没有(因为从前往后 不能超多前后缀的位置)
 * --> 注意; 若存在多个{u*}同{u}匹配的话,需要存储模式串中靠后的前缀子串{u*}
 * ~~~~~~针对{2} 引入关键变量 [prefix]  boolean类型的数组 记录模式串的后缀子串是否能匹配模式串的前缀子串。
 * ------------------------------
 * 最终移动的位数: 计算好后缀和坏字符往后滑动的位数，然后取两个数中最大的，作为模式串往后滑动的位数
 */
public class BMDemo {

    public static void main(String[] args) {
        String s1 = "cababc";
        String s2 = "ab";
        System.out.println(match(s1.toCharArray(), s1.length(), s2.toCharArray(), s2.length()));
    }

    /**
     * 注意 : 不能单纯使用坏字符规则,因为可能移动数值为负数;
     * 但是可以单独使用好后缀规则. 只是效率有所降低;
     *
     * @param a 主串
     * @param n
     * @param b 模式串
     * @param m
     * @return
     */
    private static boolean match(char[] a, int n, char[] b, int m) {
        Map<Character, Integer> hash = new HashMap<>();
        generatePatternHash(b, m, hash);//坏字符规则


        int[] suffix = new int[m];
        boolean[] prefix = new boolean[m];
        generateSuffix(b, m, suffix, prefix);//好后缀规则

        int i = 0;
        while (i <= n - m) {
            int j;
            //倒序匹配
            for (j = m - 1; j >= 0; j--) {//坏字符规则判断
                if (a[i + j] != b[j]) {
                    break;
                }
            }
            if (j < 0) {
                //说明匹配到了
                return true;
            }
            int x = j - hash.getOrDefault(a[i + j], -1);//坏字符移动位置

            int y = moveByGS(j, m, suffix, prefix);//好匹配移动位置

            //选取坏字符和好后缀之前移动的最大值;
            i += Math.max(x, y);
        }
        return false;
    }

    //好匹配规则的移动位数判断
    private static int moveByGS(int j, int m, int[] suffix, boolean[] prefix) {
        int k = m - j - 1;//{u}长度;
        if (k <= 0) {
            return 0; //坏字符在最后一个位置.则没有好后缀之说
        }
        if (suffix[k] != -1) { //存在匹配的子串
            return j - suffix[k] + 1;
        } else {
            //不存在匹配的子串,则需要看看好后缀串中是否存在后缀子串同模式串的前缀子串匹配
            for (int r = j + 2; j < m; j++) {
                if (prefix[m - r]) { //因为是同模式串的前缀匹配(前缀意味着0),索引返回r即可;
                    return r;
                }
            }
            return m;
        }
    }

    /**
     * 帮助坏字符规则的预处理
     *
     * @param b    模式串
     * @param m    模式串长度
     * @param hash 保存模式串中每个字符出现的最后一个位置
     */
    private static void generatePatternHash(char[] b, int m, Map<Character, Integer> hash) {
        for (int i = 0; i < m; i++) {
            hash.put(b[i], i);
        }
    }

    /**
     * 好后缀的规则的预处理
     *
     * @param b      模式串
     * @param m      模式串长度
     * @param suffix suffix数组 匹配的子串
     * @param prefix prefix数组 匹配的子串是否为模式串的前缀子串;
     */
    private static void generateSuffix(char[] b, int m, int[] suffix, boolean[] prefix) {
        for (int i = 0; i < m; ++i) { // 初始化
            suffix[i] = -1;
            prefix[i] = false;
        }
        for (int i = 0; i < m - 1; i++) { //这里只需要到m-1即可
            int j = i; //这样能够保证存储的是最后一个位置; 即保证存在多个{u*}与后缀子串匹配
            int k = 0;//公共后缀子串的长度
            while (j >= 0 && b[j] == b[m - 1 - k]) {
                --j;
                ++k;
                suffix[k] = j + 1; //表示公共后缀子串在b[0,i]中起始下标
            }
            if (j == -1) {
                prefix[k] = true; //如果公共后缀子串suffix[k] ==0 即上面 j ==-1;
            }
        }
    }

}
