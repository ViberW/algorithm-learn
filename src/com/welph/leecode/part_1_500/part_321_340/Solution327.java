package com.welph.leecode.part_1_500.part_321_340;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 给你一个整数数组nums 以及两个整数lower 和 upper 。求数组中，
 * 值位于范围 [lower, upper] （包含lower和upper）之内的 区间和的个数 。
 * <p>
 * 区间和S(i, j)表示在nums中，位置从i到j的元素之和，包含i和j(i ≤ j)。
 * <p>
 * 示例 1：
 * 输入：nums = [-2,5,-1], lower = -2, upper = 2
 * 输出：3
 * 解释：存在三个区间：[0,0]、[2,2] 和 [0,2] ，对应的区间和分别是：-2 、-1 、2 。
 * <p>
 * 示例 2：
 * 输入：nums = [0], lower = 0, upper = 0
 * 输出：1
 * <p>
 * 提示：
 * 1 <= nums.length <= 105
 * -2^31 <= nums[i] <= 2^31 - 1
 * -105 <= lower <= upper <= 105
 * 题目数据保证答案是一个 32 位 的整数
 */
public class Solution327 {

    public static void main(String[] args) {
        int[] nums = { -2, 5, -1, 0, 8, 3, 1, -9, -3, -2, 5, -1, 0, 8, 3, 1, -9, -3, -2, 5, -1,
                0, 8, 3, 1, -9, -3, -2, 5, -1, 0, 8, 3, 1, -9, -3, -2, 5, -1, 0, 8, 3, 1, -9, -3, -2, 5, -1, 0, 8, 3, 1,
                -9, -3, -2, 5, -1, 0, 8, 3, 1, -9, -3 };
        System.out.println(countRangeSum4(nums, -2, 2));
        // [-2147483647,0,-2147483647,2147483647]
        // -564
        // 3864
        int[] nums1 = { -2147483647, 0, -2147483647, 2147483647 };
        // int[] nums1 = {-2, 5, -1};
        // [-2147483647,0,-2147483647,2147483647]
        // -564
        // 3864
        System.out.println(countRangeSum3(nums1, -564, 3864));

        // [0,-3,-3,1,1,2]
        // 3
        // 5
        int[] nums2 = { 0, -3, -3, 1, 1, 2 }; // 2
        // System.out.println(countRangeSum3(nums2, 3, 5));
    }

    // todo 来自官方答案的呵护 -_- 前缀和+归并
    public static int countRangeSum3(int[] nums, int lower, int upper) {
        long pre = 0;
        int length = nums.length;
        long[] presum = new long[length + 1];
        for (int i = 0; i < length; i++) {
            presum[i + 1] = pre = pre + nums[i];
        }
        return countRangeSumTerm(presum, lower, upper, 0, presum.length - 1);
    }

    // 归并
    public static int countRangeSumTerm(long[] presum, int lower, int upper, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = (l + r) / 2;
        int re1 = countRangeSumTerm(presum, lower, upper, l, mid);
        int re2 = countRangeSumTerm(presum, lower, upper, mid + 1, r);
        int res = re1 + re2;

        // 计算两个已经排序好的前缀和
        int i = l;
        int i1 = mid + 1;
        int i2 = mid + 1;
        while (i <= mid) {// 因为排序了 所以更好
            // 直到大于lower
            while (i1 <= r && presum[i1] - presum[i] < lower) { // 这里的i1保留, 因为是升序的.
                i1++;
            }
            // 直到最后一个小于等于
            i2 = i1; // 有个小优化就是 i2 = i1 作为初始化条件
            while (i2 <= r && presum[i2] - presum[i] <= upper) { // 这里的i2保留, 因为是升序的.
                i2++;
            }
            res += i2 - i1;
            i++;
        }
        long[] rep = new long[r - l + 1];
        int l1 = l, l2 = mid + 1, s = 0;
        while (l1 <= mid || l2 <= r) {
            if (l1 > mid) {
                rep[s++] = presum[l2++];
            } else if (l2 > r) {
                rep[s++] = presum[l1++];
            } else {
                if (presum[l1] > presum[l2]) {
                    rep[s++] = presum[l2++];
                } else {
                    rep[s++] = presum[l1++];
                }
            }
        }

        System.arraycopy(rep, 0, presum, l, rep.length);
        return res;
    }

    // 区间数据-前缀和 todo 还是超时了....
    public static int countRangeSum2(int[] nums, int lower, int upper) {
        int length = nums.length;
        long[] presum = new long[length];
        long pre = presum[0] = nums[0];
        int ans = 0;
        if (pre >= lower && pre <= upper) {
            ans++;
        }
        for (int i = 1; i < length; i++) {
            // 相当于是查找范围在[lower-num[i], upper-num[i]]
            // 这里的判断.
            pre = presum[i] = pre + nums[i];
            int binary1 = binary(presum, i - 1, pre - (long) lower, false);
            if (binary1 > 0) {
                ans += binary1 - binary(presum, i - 1, pre - (long) upper, true);
            }
            if (pre >= lower && pre <= upper) {
                ans++;
            }
            insert(presum, i);
        }
        return ans;
    }

    private static int binary(long[] presum, int r, long k, boolean b) {
        int l = 0;
        int mid;
        while (l <= r) {
            mid = (l + r) / 2;
            if (presum[mid] < k) {
                l = mid + 1;
            } else if (presum[mid] > k) {
                r = mid - 1;
            } else {
                if (b) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }
        mid = (l + r) / 2;
        if (b) {
            return presum[mid] < k ? mid + 1 : mid;
        } else {
            return presum[mid] > k ? mid : mid + 1;
        }
    }

    private static void insert(long[] presum, int i) {
        long tag = presum[i];
        for (int j = i - 1; j >= 0; j--, i--) {
            if (tag < presum[j]) {
                swap(presum, i, j);
            } else {
                break;
            }
        }
    }

    private static void swap(long[] nums, int i, int k) {
        long tmp = nums[k];
        nums[k] = nums[i];
        nums[i] = tmp;
    }

    /**
     * 尝试使用线段树 {@link com.welph.leecode.algorithm.thinking.SegmentTree_14}
     * //todo 超时
     */
    public static int countRangeSum1(int[] nums, int lower, int upper) {
        int length = nums.length;
        SegmentTree segmentTree = new SegmentTree(nums);
        int res = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i; j >= 0; j--) {
                if (range(segmentTree.query(j, i), lower, upper)) {
                    res++;
                }
            }
        }
        return res;
    }

    static class SegmentTree {
        long[] tree = new long[0];
        int N;

        public SegmentTree(int[] iterable) {
            N = iterable.length;
            build(iterable, 1, N, 1);
        }

        private void build(int[] iterable, int l, int r, int p) {
            if (l == r) {
                if (tree.length <= p) {
                    tree = new long[p * 2];
                }
                tree[p] = iterable[l - 1];
                return;
            }
            int mid = (l + r) / 2;
            build(iterable, mid + 1, r, p * 2 + 1);
            build(iterable, l, mid, p * 2);
            tree[p] = tree[p * 2] + tree[p * 2 + 1];
        }

        public long query(int l, int r) {
            l++;
            r++;
            return query(l, r, 1, 1, N);
        }

        public long query(int l, int r, int p, int cl, int cr) {
            if (cl >= l && cr <= r)
                return tree[p];
            int mid = (cl + cr) / 2;
            if (mid >= r)
                return query(l, r, p * 2, cl, mid);
            else if (mid < l)
                return query(l, r, p * 2 + 1, mid + 1, cr);
            else
                return query(l, r, p * 2, cl, mid) + query(l, r, p * 2 + 1, mid + 1, cr);
        }
    }

    ////////////////////////////////////////////////////////////////////////////

    // 动态规划? dp[i][j] 表示包含[i,j]的区间和 --todo 会超出时间限制, 但还是能算出来的
    public static int countRangeSum(int[] nums, int lower, int upper) {
        int length = nums.length;
        long[] dp = new long[length]; // 从x到i-1的和
        int v;
        int res = 0;
        for (int i = 0; i < length; i++) {
            v = nums[i];
            if (range(v, lower, upper)) {
                res++;
            }
            dp[i] = v;
            for (int j = i - 1; j >= 0; j--) {
                dp[j] = dp[j] + v; // 有可能超过了int的临界值
                if (range(dp[j], lower, upper)) {
                    res++;
                }
            }
        }
        return res;
    }

    public static boolean range(long v, int lower, int upper) {
        return v >= lower && v <= upper;
    }

    /* 官方题解 (前缀和+归并在上面给出) */

    // 线段树///////////////////////////////////////////
    public static int countRangeSum4(int[] nums, int lower, int upper) {
        long sum = 0;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        Set<Long> allNumbers = new TreeSet<Long>();
        for (long x : preSum) {
            allNumbers.add(x);
            allNumbers.add(x - lower);
            allNumbers.add(x - upper);
        }
        // 利用哈希表进行离散化, 主要是为了防止数值过大 太离散了
        Map<Long, Integer> values = new HashMap<Long, Integer>();
        int idx = 0;
        for (long x : allNumbers) { // 上面是treeSet, 这里应该是排好序的
            values.put(x, idx);
            idx++;
        }

        // 提前构建好相应的数值线段树
        SegNode root = build(0, values.size() - 1);
        int ret = 0;
        for (long x : preSum) {
            // 找到左右对应的位置 lower <= x - y <= upper 那么 y=> x-upper <= y <= x - lower
            // left = x-upper ; right = x-lower ;
            int left = values.get(x - upper), right = values.get(x - lower);
            ret += count(root, left, right);
            insert(root, values.get(x));
        }
        return ret;
    }

    public static SegNode build(int left, int right) {
        SegNode node = new SegNode(left, right);
        if (left == right) {
            return node;
        }
        int mid = (left + right) / 2;
        node.lchild = build(left, mid);
        node.rchild = build(mid + 1, right);
        return node;
    }

    public static int count(SegNode root, int left, int right) {
        if (left > root.hi || right < root.lo) {
            return 0;
        }
        if (left <= root.lo && root.hi <= right) {
            return root.add;
        }
        return count(root.lchild, left, right) + count(root.rchild, left, right);
    }

    public static void insert(SegNode root, int val) {
        root.add++;
        if (root.lo == root.hi) {
            return;
        }
        int mid = (root.lo + root.hi) / 2;
        if (val <= mid) {
            insert(root.lchild, val);
        } else {
            insert(root.rchild, val);
        }
    }

    static class SegNode {
        int lo, hi, add;
        SegNode lchild, rchild;

        public SegNode(int left, int right) {
            lo = left;
            hi = right;
            add = 0;
            lchild = null;
            rchild = null;
        }
    }

    // 动态增加节点的线段树 , 主要是不使用哈希表映射///////////////////////////////////////////
    public static int countRangeSum5(int[] nums, int lower, int upper) {
        int sum = 0;
        int[] preSum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        int lbound = Integer.MAX_VALUE, rbound = Integer.MIN_VALUE;
        for (int x : preSum) {
            lbound = Math.min(Math.min(lbound, x), Math.min(x - lower, x - upper));
            rbound = Math.max(Math.max(rbound, x), Math.max(x - lower, x - upper));
        }

        SegNode root = new SegNode(lbound, rbound); // 还是最大值和最小值的范围(非idx)
        int ret = 0;
        for (int x : preSum) {
            ret += count(root, x - upper, x - lower);
            insert1(root, x);
        }
        return ret;
    }

    // 主要是这里动态添加
    public static void insert1(SegNode root, int val) {
        root.add++;
        if (root.lo == root.hi) {
            return;
        }
        int mid = (root.lo + root.hi) >> 1;
        if (val <= mid) {
            if (root.lchild == null) {
                root.lchild = new SegNode(root.lo, mid);
            }
            insert(root.lchild, val);
        } else {
            if (root.rchild == null) {
                root.rchild = new SegNode(mid + 1, root.hi);
            }
            insert(root.rchild, val);
        }
    }

    // 树状数组///////////////////////////////////////////
    public int countRangeSum6(int[] nums, int lower, int upper) {
        long sum = 0;
        long[] preSum = new long[nums.length + 1];
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            preSum[i + 1] = sum;
        }

        Set<Long> allNumbers = new TreeSet<Long>();
        for (long x : preSum) {
            allNumbers.add(x);
            allNumbers.add(x - lower);
            allNumbers.add(x - upper);
        }
        // 利用哈希表进行离散化
        Map<Long, Integer> values = new HashMap<Long, Integer>();
        int idx = 0;
        for (long x : allNumbers) {
            values.put(x, idx);
            idx++;
        }

        int ret = 0;
        BIT bit = new BIT(values.size());
        for (int i = 0; i < preSum.length; i++) {
            int left = values.get(preSum[i] - upper), right = values.get(preSum[i] - lower);
            ret += bit.query(right + 1) - bit.query(left);
            bit.update(values.get(preSum[i]) + 1, 1);
        }
        return ret;
    }

    class BIT {
        int[] tree;
        int n;

        public BIT(int n) {
            this.n = n;
            this.tree = new int[n + 1];
        }

        public int lowbit(int x) {
            return x & (-x);
        }

        public void update(int x, int d) {
            while (x <= n) {
                tree[x] += d;
                x += lowbit(x);
            }
        }

        public int query(int x) {
            int ans = 0;
            while (x != 0) {
                ans += tree[x];
                x -= lowbit(x);
            }
            return ans;
        }
    }

}
