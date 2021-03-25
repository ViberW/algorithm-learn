package com.welph.leecode.part_261_280;

import java.util.Arrays;

/**
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 * <p>
 * h 指数的定义：h 代表“高引用次数”（high citations），
 * 一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。
 * 且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。
 * <p>
 * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
 * 示例：
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 * <p>
 * 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
 */
public class Solution274 {

    public static void main(String[] args) {
       /* int[] citations = {4, 0, 6, 1, 5};
        System.out.println(hIndex(citations)); //3
        int[] citations1 = {0, 1, 1, 1, 1, 1, 1};
        System.out.println(hIndex(citations1));//1
        int[] citations2 = {0};
        System.out.println(hIndex(citations2));//0*/
        int[] citations3 = {11, 15};
        System.out.println(hIndex2(citations3));//2
       /* int[] citations4 = {1, 1};
        System.out.println(hIndex(citations4));//1
        int[] citations5 = {0, 1, 0};
        System.out.println(hIndex(citations5));//1
        int[] citations6 = {1, 3, 1};
        System.out.println(hIndex(citations6));//1*/
    }

    /**
     * 不使用二分法处理,
     */
    public static int hIndex1(int[] citations) {
        Arrays.sort(citations);
        // 线性扫描找出最大的 i
        int i = 0;
        while (i < citations.length && citations[citations.length - 1 - i] > i) {
            i++;
        }
        return i;
    }

    //计数排序方式 todo  也是很不错的方法
    public static int hIndex2(int[] citations) {
        int n = citations.length;
        int[] papers = new int[n + 1];
        // 计数
        for (int c : citations)
            papers[Math.min(n, c)]++;
        // 找出最大的 k
        int k = n;
        for (int s = papers[n]; k > s; s += papers[k])
            k--;
        return k;
    }

    //寻找相同的最大值
    //todo 哈哈哈 二分法处理 -- 一开始排序了, 没想到{@link Solution275} 题已经是排序好了 ,提前写出了那题的答案
    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
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
}
