package com.welph.leecode.algorithm.marscode;

import java.util.HashMap;
import java.util.Map;

/**
 * 小C发现了一个有趣的数组操作问题。她手中有一个包含n个整数的数组,通过将这
 * 些元素相乘得到了一个值m。例如,给定数组[3,1,2],可以计算得到x=3×1×2=6。
 * 现在,小C可以进行一些操作来调整x的大小,具体操作如下:
 *
 * 1.将x乘以2。
 * 2.将x除以2(仅当:为偶数时可以进行)。
 *
 * 小C的目标是让最终x的因子数量尽可能接近给定的目标值k。
 * 也就是说,设立的因子数量为p,需要使得|k-p|的值尽可能小。
 *
 *
 * 如果有多种方案可以达到相同的接近度,小C希望操作次数尽可能少。
 * 你能帮助小C计算出实现这一目标所需的最小操作次数吗?
 */
public class FactorCloseNumber {

    public static int solution(int n, int k, int[] a) {
        //要么一致除. 要么一直乘
        Map<Integer, Integer> factors = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int v = a[i];
            int f = 2;
            while (f * f <= v) {
                int count = 0;
                while (v % f == 0) {
                    count++;
                    v /= f;
                }
                factors.put(f, factors.getOrDefault(f, 0) + count);
                f++;
            }
            if (v > 1) {
                factors.put(v, factors.getOrDefault(v, 0) + 1);
            }
        }
        //拿到了所有的因子数. 所有因子组合的可能是(factors[i]+1)*(factors[i1]+1)...(factors[in]+1) -1 + 1 (减去所有非1因子不选 以及 加上1的因子)
        int count2 = factors.getOrDefault(2, 0);
        int other = 1;
        for (Map.Entry<Integer, Integer> en : factors.entrySet()) {
            if (en.getKey() == 2) {
                continue;
            }
            other *= (en.getValue() + 1);
        }
        //就比较加单了. |k-p| , 令k-p=delta, 如果里面的值小于0, 则除以2. 如果里面的值大于0则乘以2  => delta-m*other 即求得m的值
        int delta = k - other * (count2 + 1);
        if (delta == 0) {
            return 0;
        } else {
            boolean navigate = delta < 0;
            delta = Math.abs(delta);
            int result = delta / other; //最少操作result次数
            if (Math.abs(delta - result * other) > Math.abs(delta - result * other - other)) {
                result++;
            }
            if (navigate) { //收到count2的限制
                return Math.min(result, count2);
            } else {
                return result;
            }
        }
    }

    public static void main(String[] args) {
//        System.out.println(solution(3, 13, new int[]{2, 1, 3}) == 4);
//        System.out.println(solution(5, 15, new int[]{2, 4, 6, 8, 10}) == 5);
//        System.out.println(solution(4, 20, new int[]{1, 3, 5, 7}) == 1);
        System.out.println(solution(1, 4, new int[]{1}) == 3);
    }
}
