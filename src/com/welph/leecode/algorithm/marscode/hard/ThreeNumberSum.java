package com.welph.leecode.algorithm.marscode.hard;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 小U有一个整数数组 arr，他希望找到其中三个元素 i, j, k
 * 满足条件 i < j < k 且 arr[i] + arr[j] + arr[k] == target。
 * 由于可能存在大量的元组，结果需要对 10^9 + 7 取模。
 *
 * 例如：当 arr = [1,1,2,2,3,3,4,4,5,5] 且 t = 8 时，有多个元组满足条件，你需要返回这些元组的数量。
 */
public class ThreeNumberSum {

    //{@link Solution18} {@link Solution15}
    public static int solution(int[] arr, int t) {
        int mod = 1000000007;
        Arrays.sort(arr);
        //记录每个数据的个数
        Map<Integer, Long> counts = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            counts.put(arr[i], counts.getOrDefault(arr[i], 0L) + 1);
        }
        long result = 0;
        //固定住一个值, 筛选后面的值. 如果三个值相同, 则多匹配, 否则
        for (int i = 0; i < arr.length; i++) {
            //计算第一个值的个数
            if (i > 0 && arr[i] == arr[i - 1]) {
                continue;
            }
            int target = t - arr[i];
            int l = i + 1;
            int r = arr.length - 1;
            while (l < r) {
                int val = arr[l] + arr[r];
                if (val == target) {
                    //说明匹配到了对应值
                    if (arr[l] == arr[r]) {
                        //说明正好卡住了临界范围, 一前一后
                        if (arr[l] == arr[i]) { //C(m,3)
                            long m = counts.get(arr[i]);
                            result = (result + m * (m - 1) * (m - 2) / 6) % mod;
                        } else { //C(m,2);
                            long m = counts.get(arr[i]);
                            long mr = counts.get(arr[r]);
                            result = (result + m * mr * (mr - 1) / 2) % mod;
                        }
                    } else {
                        long mr = counts.get(arr[r]);
                        if (arr[l] == arr[i]) {
                            long m = counts.get(arr[i]);
                            result = (result + mr * m * (m - 1) / 2) % mod;
                        } else {
                            result = (result + mr * counts.get(arr[l]) * counts.get(arr[i])) % mod;
                        }
                    }
                    do {
                        l++;
                    } while (l < r && arr[l] == arr[l - 1]);
                    do {
                        r--;
                    } while (l < r && arr[r] == arr[r + 1]);
                } else if (val > target) {
                    r--;
                } else {
                    l++;
                }
            }
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 2, 2, 3, 3, 4, 4, 5, 5}, 8));
        System.out.println(solution(new int[]{2, 2, 2, 2}, 6) == 4);
        System.out.println(solution(new int[]{1, 2, 3, 4, 5}, 9) == 2);
        System.out.println(solution(new int[]{1, 1, 1, 1}, 3) == 4);
    }
}
