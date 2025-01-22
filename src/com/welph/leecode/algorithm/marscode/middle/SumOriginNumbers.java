package com.welph.leecode.algorithm.marscode.middle;

import java.util.*;
import java.util.stream.Collectors;

/**
 * n 个整数两两相加可以得到 n(n - 1) / 2 个和。我们的目标是：根据这些和找出原来的 n 个整数。
 *
 * 按非降序排序返回这 n 个数，如果无解，输出 "Impossible"。
 */
public class SumOriginNumbers {

    public static String solution(int n, int[] sums) {
        if (Arrays.stream(sums).sum() % (n - 1) != 0) {
            return "Impossible";
        }
        Arrays.sort(sums);
        //第一个值为(0+1) 第二个值为(0+2),
        int[] ans = new int[n];
        Label:
        for (int i = 2; i < sums.length; i++) {
            if (((sums[0] + sums[1] - sums[i]) & 1) == 1) {
                continue; //说明无法被2除
            }
            if (i > 2 && sums[i] == sums[i - 1]) {
                continue;
            }
            //因为s[0]= a[0]+a[1], s[1]=a[0]+a[2]
            //这里是假设i为 a[1]+[2] 那么s[0]+s[1]-s[i]/2 = a[0]
            ans[0] = (sums[0] + sums[1] - sums[i]) / 2;
            ans[1] = sums[0] - ans[0];
            ans[2] = sums[1] - ans[0];

            //开始处理计算
            List<Integer> list = Arrays.stream(sums).boxed().collect(Collectors.toList());
            list.remove((Integer) sums[0]);
            list.remove((Integer) sums[1]);
            list.remove((Integer) sums[i]);
            for (int k = 3; k < n; k++) {
                //这里始终取第一个值 因为其他值被删除了
                ans[k] = list.get(0) - ans[0];
                for (int l = 0; l < k; l++) {
                    if (!list.remove((Integer) (ans[k] + ans[l]))) { //将历史的相加值减去,那么剩下index=0为a[0]+a[k+1]
                        continue Label;
                    }
                }
            }
            if (list.isEmpty()) {
                return Arrays.stream(ans).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            }
        }
        return "Impossible";
    }

    public static void main(String[] args) {
        // You can add more test cases here
        int[] sums1 = {1269, 1160, 1663};
        int[] sums2 = {1, 1, 1};
        int[] sums3 = {226, 223, 225, 224, 227, 229, 228, 226, 225, 227};
        int[] sums4 = {-1, 0, -1, -2, 1, 0, -1, 1, 0, -1};
        int[] sums5 = {79950, 79936, 79942, 79962, 79954, 79972, 79960, 79968, 79924, 79932};

        System.out.println(solution(3, sums1).equals("383 777 886"));
        System.out.println(solution(3, sums2).equals("Impossible"));
        System.out.println(solution(5, sums3).equals("111 112 113 114 115"));
        System.out.println(solution(5, sums4).equals("-1 -1 0 0 1"));
        System.out.println(solution(5, sums5).equals("39953 39971 39979 39983 39989"));
    }
}
