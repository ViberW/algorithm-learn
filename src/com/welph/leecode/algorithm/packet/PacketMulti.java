package com.welph.leecode.algorithm.packet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ----------------------------------------------多重背包问题
 * 有一个容量有限的背包，容量为w，以及m种待选择的物品, 每种物品最多有c[i]件可用
 * 将哪些物品装入背包可使这些物品的费用总和不超过背包容量，且价值总和最大。
 */
public class PacketMulti {

    public static void main(String[] args) {
        int[] w = { 1, 2, 1, 4, 2, 3 };
        int[] v = { 10, 20, 30, 10, 15, 5 };
        int[] c = { 2, 3, 3, 4, 1, 2 };
        System.out.println(maximumValue(w, v, c, 5));
        System.out.println(maximumValue5(w, v, c, 5));
    }

    /**
     * 解法一: 可以将v个数展开, 相当于01背包的问题
     * 
     * @param w 物品价值
     * @param v 物品体积
     * @param c 物品个数
     * @param n 目标值
     * @return
     */
    public static int maximumValue(int[] w, int[] v, int[] c, int n) {
        // 展开成01背包问题并处理
        int sum = Arrays.stream(v).sum();
        int[] nw = new int[sum];
        int[] nv = new int[sum];
        int count;
        for (int i = 0, k = 0; i < c.length; i++) {// 展开列表
            count = c[i];
            while (count-- > 0) {
                nw[k] = w[i];
                nv[k] = v[i];
                k++;
            }
        }
        int m = nw.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = n; j >= nw[i]; --j) {
                f[j] = Math.max(f[j], f[j - nw[i]] + nv[i]);
            }
        }
        return f[n];
    }

    public static int maximumValue2(int[] w, int[] v, int[] c, int n) {
        int m = w.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < m; ++i) {
            for (int j = n; j >= w[i]; --j) {
                for (int k = c[i]; k >= 0; --k) { // 展开成01背包问题并处理,也是倒序
                    if (j >= k * w[i]) {
                        f[j] = Math.max(f[j], f[j - k * w[i]] + k * v[i]);
                    }
                }
            }
        }
        return f[n];
    }

    /**
     * 优化转换为01+完全背包
     * 主要是若i位置的数据 w[i] * c[i] > n 这就代表i的物品不能完全放入背包, 也相当于完全背包处理
     */
    public static int maximumValue3(int[] w, int[] v, int[] c, int n) {
        int m = w.length;
        int[] f = new int[n + 1];
        for (int i = 0; i < m; ++i) {
            if (w[i] * c[i] >= n) {
                // 完全背包
                for (int j = w[i]; j <= n; ++j) {
                    f[j] = Math.max(f[j], f[j - w[i]] + v[i]);
                }
            } else {
                for (int j = n; j >= w[i]; --j) {
                    // 这里顺序或者倒序都无所谓,因为不重叠
                    for (int k = Math.min(c[i], j / w[i]); k > 0; --k) {
                        f[j] = Math.max(f[j], f[j - k * w[i]] + k * v[i]);
                    }
                }
            }
        }
        return f[n];
    }

    /**
     * 二进制优化
     * ------
     * 若一个物品很多, 但容量小 导致它完全能满足01背包模式, 则需要循环很多次
     * -> 使用二进制 标记数字进行优化
     * 注意: 若i的数量c[i] = k
     * 若需要使用二进制表示k 则需要将其拆分为能够表示k所有数值的二进制
     * 例如k=10 则拆分为1,2,4,3 而不是8,2 因为8,2不能完全表示10内的所有情况
     */
    public static int maximumValue4(int[] w, int[] v, int[] c, int n) {
        int m = w.length;
        // 二进制的拆分
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int k = c[i];
            for (int j = 1; j <= k; j *= 2) {
                list.add(new int[] { j * w[i], j * v[i] });// 这里*j 下面*K
                k -= j;
            }
            if (k > 0) {
                list.add(new int[] { k * w[i], k * v[i] });// 拆分成能够拼凑的多种数值
            }
        }
        int[] f = new int[n + 1];
        for (int[] ints : list) {// 这里本质上就融合了多种可能,即包含了k的所有可能
            for (int j = n; j >= ints[0]; --j) {
                f[j] = Math.max(f[j], f[j - ints[0]] + ints[1]);
            }
        }

        return f[n];
    }

    /*
     * 单调栈优化
     * .for(int i=1,v,w,s;i<=n;i++)
     * . .. for(int j=0;j<v;j++) //剩余的体积不可能超过v[i] 否则还是允许放入i的. 考虑的是剩余体积的数量
     * . .......for(int k=j;k<=V;k+=v)
     * --------------------------------
     * 最优解: max{f[j], f[j+v].... f[j+kv]} 表示放入i(或不放入)的最优解
     * 而 f[j+v] = max{f[j], f[j+v]-w} +w
     * f[j+2v] = max{f[j], f[j+v]-w,f[j+2v]-2w} +2w
     * ...
     * f[j+kv] = max{f[j], f[j+v]-w,f[j+2v]-2w,f[j+kv]-kw} +kw
     * -------------------
     * 通过单调队列, 保证 f[j+kv]-kw的最大值, 用于下一次的k+1的使用
     */
    // {@link https://www.acwing.com/solution/content/6500/}
    public static int maximumValue5(int[] w, int[] v, int[] c, int n) {
        int m = w.length;
        int[] f = new int[n + 1];
        int[] q = new int[n + 1];// 单调队列
        int[] pre = new int[f.length];// 记录前一类f数组的状态
        for (int i = 0; i < m; ++i) {
            // j个单调队列
            System.arraycopy(f, 0, pre, 0, f.length);
            // 表示装满当前i物品后, 最终不能超过t 且t<i的重量的. 因为若是剩余大于w[i] 则还能装i
            for (int t = 0; t < w[i]; t++) {// 表示当前i的每轮剩余t的计算, 分别有{t个单调队列}
                // 用于滑动窗口的左端点和右端点
                int head = 0, tail = -1;

                // 剩余t的情况下, 逐渐装载i物品的个数
                for (int k = t; k <= n; k += w[i]) {// 真实的容量k(k%w[i] =t)
                    // 滑动窗口, 窗口大小为c[i] * w[i], 不断的移动hh 其实就是w[i]
                    // 相当于不断满足剩余的数量为其他的物品填充
                    if (head <= tail && k - c[i] * w[i] > q[head]) { // 窗口的左端点不断滑动, 保证窗口内的体积不超过c*w
                        head++;
                    }
                    // 若当前容量j大于前面jj的价值时+, tail-- 相当于慢慢替换 说明q为单调减队列
                    // 单调栈的转移方程: dp[j+k*v] - k*w k = t+k'*w[i]
                    // 这里不用担心k> c[i] * w[i]的问题, 仅仅是个状态转移, 最终对于k来说,
                    // 它的结果都是单调栈中结果加上k'*w[i]固定值.所以这里是为了单调计算
                    while (head <= tail
                            && pre[q[tail]] - (q[tail] - t) / w[i] * v[i] <= pre[k] - (k - t) / w[i] * v[i]) {
                        tail--; //看在head到tail中能不能找到一个替换的.
                    }
                    // 更新最大值(hh为最大值的标记)
                    if (head <= tail) {
                        f[k] = Math.max(f[k], pre[q[head]] + (k - q[head]) / w[i] * v[i]);
                    }

                    q[++tail] = k;
                }
            }
        }
        return f[n];
    }
}
