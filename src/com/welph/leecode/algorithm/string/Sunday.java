package com.welph.leecode.algorithm.string;

/**
 * Sunday算法和BM算法稍有不同的是，Sunday算法是从前往后匹配，在匹配失败时关注的是主串中参加匹配的最末位字符的下一位字符。
 * <p>
 * 如果该字符没有在模式串中出现则直接跳过，即移动位数 = 模式串长度 + 1；
 * 否则，其移动位数 = 模式串长度 - 该字符最右出现的位置(以0开始) = 模式串中该字符最右出现的位置到尾部的距离 + 1
 * <a>https://www.jianshu.com/p/2e6eb7386cd3<a/>。
 * <p>
 * ---------
 * 看上去简单高效非常美好的Sunday算法，也有一些缺点。
 * ....因为Sunday算法的核心依赖于move数组，而move数组的值则取决于模式串，那么就可能存在模式串构造出很差的move数组。
 * 例如下面一个例子
 * 主串：baaaabaaaabaaaabaaaa
 * 模式串：aaaaa
 * <p>
 * 这个模式串使得move[a]的值为1，即每次匹配失败时，只让模式串向后移动一位再进行匹配。
 * 这样就让Sunday算法的时间复杂度飙升到了O(m*n)，也就是字符串匹配的最坏情况
 */
public class Sunday {

    public static void main(String[] args) {

    }
}
