package com.welph.leecode.algorithm.marscode.middle;

import java.util.Arrays;

/**
 * 小R有一个特殊的随机播放规则。他首先播放歌单中的第一首歌，播放后将其从歌单中移除。
 * 如果歌单中还有歌曲，则会将当前第一首歌移到最后一首。
 * 这个过程会一直重复，直到歌单中没有任何歌曲。
 *
 * 例如，给定歌单 [5, 3, 2, 1, 4]，真实的播放顺序是 [5, 2, 4, 1, 3]。
 *
 * 保证歌曲中的id两两不同。
 */
public class VideoPlaySequence {

    public static int[] solution(int n, int[] a) {
        int[] result = new int[n];
        int index = 0;
        int step = 2;
        int i = 0;
        int size = n;
        boolean withEnd = false;
        while (size > 0) {
            int start = i;
            if (withEnd) {
                start = i + step / 2;
                start = start < n ? start : i;
            } else {
                i += step / 2;
            }
            withEnd = ((size & 1) == 1) ^ withEnd;
            while (start < n) {
                result[index++] = a[start];
                start += step;
                size--;
            }
            step <<= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.equals(solution(5, new int[]{5, 3, 2, 1, 4}),
                new int[]{5, 2, 4, 1, 3}));
        System.out.println(Arrays.equals(solution(4, new int[]{4, 1, 3, 2}),
                new int[]{4, 3, 1, 2}));
        System.out.println(Arrays.equals(solution(6, new int[]{1, 2, 3, 4, 5, 6}),
                new int[]{1, 3, 5, 2, 6, 4}));
        System.out.println(Arrays.toString(solution(9, new int[]{3, 6, 1, 7, 9, 8, 5, 4, 2})));
        System.out.println(Arrays.toString(solution(7, new int[]{4, 3, 5, 2, 7, 6, 1})));
    }
}
