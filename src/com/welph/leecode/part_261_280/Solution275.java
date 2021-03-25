package com.welph.leecode.part_261_280;

/**
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数），数组已经按照升序排列。编写一个方法，计算出研究者的 h 指数。
 * <p>
 * h 指数的定义: “h 代表“高引用次数”（high citations），
 * 一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
 * （其余的N - h篇论文每篇被引用次数不多于 h 次。）"
 * <p>
 * 示例:
 * 输入: citations = [0,1,3,5,6]
 * 输出: 3
 * 解释: 给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。
 * 由于研究者有 3 篇论文每篇至少被引用了 3 次，其余两篇论文每篇被引用不多于 3 次，所以她的 h 指数是 3。
 * <p>
 * 说明:
 * 如果 h 有多有种可能的值 ，h 指数是其中最大的那个。
 * <p>
 * 进阶：
 * 这是H 指数的延伸题目，本题中的citations数组是保证有序的。
 * 你可以优化你的算法到对数时间复杂度吗？
 */
public class Solution275 {

    public static void main(String[] args) {
        int[] citations = {4, 0, 6, 1, 5};
        System.out.println(hIndex(citations));
        int[] citations1 = {0, 1, 1, 1, 1, 1, 1};
        System.out.println(hIndex(citations1));
        int[] citations2 = {0};
        System.out.println(hIndex(citations2));
        int[] citations3 = {11, 15};
        System.out.println(hIndex(citations3));
        int[] citations4 = {1, 1};
        System.out.println(hIndex(citations4));
        int[] citations5 = {0, 1, 0};
        System.out.println(hIndex(citations5));
        int[] citations6 = {1, 3, 1};
        System.out.println(hIndex(citations6));
    }

    public static int hIndex(int[] citations) {
        int len = citations.length;
        int l = 0;
        int r = len - 1;
        int mid;
        //若剩余的大于中间值, 则应该向右走,,  若向右走没有的话, 则说明
        //若剩余的值小于中间值, 则应该向左走
        //仅仅在等于的时候给出
        while (l <= r) {
            mid = (l + r) / 2;
            if (citations[mid] < len - mid) {
                l = mid + 1;
            } else if (citations[mid] > len - mid) { //若是大于 说明有N个值大于 没问题.
                //向前找, 若发现前面的>len-mid.说明不合法, 则需要向左移动
                if (mid > 0 && citations[mid - 1] <= len - mid) {
                    return len - mid;
                }
                r = mid - 1;
            } else {
                return len - mid;
            }
        }
        //若最后一次r变小, 则减去mid,
        //否则说明最后一次l变大, 说明做边依旧找不到
        return l >= len ? 0 : len - (l + r) / 2;
    }


    /**
     * 官网的线性搜素
     */
    public int hIndex2(int[] citations) {
        int idx = 0, n = citations.length;
        for (int c : citations) {
            if (c >= n - idx) return n - idx;
            else idx++; //说明已经不满足
        }
        return 0;
    }
}
