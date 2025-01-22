package com.welph.leecode.algorithm.marscode.middle;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 西瓜视频正在开发一个新功能，旨在将访问量达到80百分位数以上的视频展示在首页的推荐列表中。
 * 实现一个程序，计算给定数据中的80百分位数。
 *
 * 例如：假设有一个包含从1到100的整数数组，80百分位数的值为80，因为按升序排列后，第80%位置的数字就是80。
 *
 * 99 百分位数：假如有 N 个数据，将数据从小到大排列，
 * 99 百分位数是第 N99%位置处的数据（遇到小数时四舍五入获取整数）。
 * 一般计算逻辑是先排序，定位到 N99%的位置。返回该位置处的数据。
 *
 * 同理，80 百分位数就是第 N*80%位置处的数据。
 */
public class VideoRecommendation {

    public static int solution(String data) {
        String[] split = data.split(",");
        Arrays.sort(split, Comparator.comparingInt(String::length)
                .thenComparing(String::compareTo));
        int index = (4 * split.length) / 5 + ((4 * split.length) % 5 > 2 ? 1 : 0) - 1;
        return Integer.parseInt(split[index]);
    }

    public static void main(String[] args) {
        //  You can add more test cases here
        System.out.println(solution("10,1,9,2,8,3,7,4,6,5") == 8);
        System.out.println(solution("1,0,8,7,3,9,12,6,4,15,17,2,14,5,10,11,19,13,16,18") == 15);
        System.out.println(solution("76,100,5,99,16,45,18,3,81,65,102,98,36,4,2,7,22,66,112,97,68,82,37,90,61,73,107,104,79,14,52,83,27,35,93,21,118,120,33,6,19,85,49,44,69,53,67,110,47,91,17,55,80,78,119,15,11,70,103,32,9,40,114,26,25,87,74,1,30,54,38,50,8,34,28,20,24,105,106,31,92,59,116,42,111,57,95,115,96,108,10,89,23,62,29,109,56,58,63,41,77,84,64,75,72,117,101,60,48,94,46,39,43,88,12,113,13,51,86,71") == 96);
    }
}
