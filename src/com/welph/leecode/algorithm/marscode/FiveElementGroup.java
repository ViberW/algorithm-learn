package com.welph.leecode.algorithm.marscode;

import java.util.*;

/**
 * 小C拿到了一个长度为n的数组a1,a2,....,an,她想知道有多少组(i,j,k,h,1)为"w五元组"。w五元组定义如下:
 * <p>
 * 1. 满足条件的下标必须满足1<j<k<h<lsn;
 * 2. 数组元素需要满足:a_i=a_k=a_k=a_l,且a_j = a_h,并且а i > a_j.
 * <p>
 * 目标是求出所有满足条件的w五元组数量,并对结果取模10^9+7。
 */
public class FiveElementGroup {
    public static int solution(int n, List<Integer> a) {
        TreeMap<Integer, List<Integer>> map = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            map.computeIfAbsent(a.get(i), V -> new ArrayList<>()).add(i);
        }
        int MOD = 1_000_000_007;
        int ret = 0;
        while (map.size() > 1) {
            Map.Entry<Integer, List<Integer>> entry = map.pollFirstEntry();
            List<Integer> value = entry.getValue();
            if (value.size() < 2) {
                continue;
            }
            //找到有序的
            for (int i = 0; i < value.size(); i++) {
                for (int j = i + 1; j < value.size(); j++) {
                    if (value.get(j) > value.get(i) + 1) {
                        //二分查找 value[i] value[j]这个区间范围内.
                        for (Map.Entry<Integer, List<Integer>> item : map.entrySet()) {
                            List<Integer> items = item.getValue();
                            int l = banarySearch(items, value.get(i), 0, true); //二分查找前后值
                            if (l < 0 || l >= items.size() - 2) {
                                continue;
                            }
                            int r = banarySearch(item.getValue(), value.get(j), l, false);
                            if (r == items.size() || r - l < 2) {
                                continue;
                            }
                            ret += (l + 1) * (items.size() - r) * (r - l - 1);
                        }
                    }
                }
            }
        }
        return ret;
    }

    private static int banarySearch(List<Integer> value, Integer target, int l, boolean less) {
        int r = value.size() - 1;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (value.get(mid) > target) {
                r--;
            } else {
                l++;
            }
        }
        return less ? r : l;
    }

    public static void main(String[] args) {
        System.out.println(solution(7, Arrays.asList(3, 1, 3, 1, 3, 1, 3)) == 6);
        System.out.println(solution(6, Arrays.asList(2, 1, 2, 1, 2, 1)) == 1);
        System.out.println(solution(5, Arrays.asList(5, 3, 5, 3, 5)) == 1);
    }
}
