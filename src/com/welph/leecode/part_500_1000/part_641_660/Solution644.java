package com.welph.leecode.part_500_1000.part_641_660;

/**
 * 有一个n个整数的数组nums，和一个整数k，
 * 要找出长度>=k 并且 含最大平均值的连续子数组。并且输出这个最大的平均数，
 * 任何计算误差小于10^-5的结果都认为是正确。
 * <p>
 * 提示：
 * n == nums.length
 * 1 <= k <= n <= 10^4
 * -10^4 <= nums[i] <= 10^4
 */
public class Solution644 {

    public static void main(String[] args) {
        System.out.println(findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
        System.out.println(findMaxAverage1(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }

    /**
     * {@link Solution643} 类似, 但这里要求窗口大小 >=k
     * 二分法??? 怎么考量的呢?
     * -------------------------------------
     * 1. 由于平均数根据提示是有边界的 即为[10^4 <= avg <= 10^4]
     * 2. 假设通过遍历nums得到 min 和 max
     * 3. 根据最小值和最大值, 就可以使用二分法得到 targetAvg
     * 4. 构建一个check() 函数去判断
     * >>> 4.1 对于长度m(m>=k), subAvg = (aj,aj+1,aj+2...aj+m)/m
     * ........4.1.1 上述每个数字相加太过麻烦, 既然需要知道Sum(aj,aj+1,aj+2...aj+m)/m>targetAvg
     * ............. sum(aj-targetAvg,aj+1-targetAvg,...aj+m-targetAvg) >=0 即代表符合subAvg >= targetAvg
     * ........4.1.2 说明只需要预先计算好 平均数的区间和即可
     * >>> 4.2 如果存在 subAvg >= targetAvg,则说明SubAvg符合条件, 则可以将min = subAvg
     * >>> 4.3 通过不断地二分, 最终平均数不断靠近
     * >>> 4.4 计算区间和 [j,i]其中应该应该有k个元素满足sum[i]-sum[j]>=0,尽可能sum[i]越大,sum[j]越小
     * >>> 4.5 在遍历中, sum[i] += num[i]-avg , 当距离i 满足k个元素, 开始累加sum[j] += num[i-k]-avg, 同时用min=min(min,sum[j])
     * >>> 4.6 若果sum[i] -sum[j] >=0  返回true
     */
    public static double findMaxAverage(int[] nums, int k) {
        double minVal = -10001;
        double maxVal = 10001;
        for (int num : nums) {
            minVal = Math.min(minVal, num);
            maxVal = Math.max(maxVal, num);
        }
        while (maxVal - minVal > 0.00001) {//误差小于10^-5
            double mid = (maxVal + minVal) * 0.5;
            if (check(nums, mid, k)) {
                minVal = mid;
            } else {
                maxVal = mid;
            }
        }
        return minVal;
    }

    /**
     * 找到符合nums[k] 长度>=k的 是否满足>=mid
     */
    private static boolean check(int[] nums, double mid, int k) {
        double sum = 0, prev = 0, min_sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i] - mid;
        }
        if (sum > 0) {
            return true;
        }
        //说明第一次这里是小于0的
        for (int i = k; i < nums.length; i++) {
            sum += nums[i] - mid; //一直正长的总和
            prev += nums[i - k] - mid;//前K以前的列表总和
            min_sum = Math.min(prev, min_sum); //不断找到最小的prev
            if (sum - min_sum >= 0)//满足一段的连续区间  |--prev--|-----m(m>=k)------|sum
                return true;
        }
        return false;
    }

    //先来个暴力法看看
    public static double findMaxAverage1(int[] nums, int k) {
        int n = nums.length;
        int total;
        int maxValue;
        double maxAvg = 0;
        for (int i = k; i < n; i++) {
            total = 0;
            for (int j = 0; j < i; j++) {
                total += nums[j];
            }
            maxValue = total;
            for (int j = i; j < n; j++) {
                total += nums[j] - nums[j - i];
                maxValue = Math.max(maxValue, total);
            }
            maxAvg = Math.max(maxAvg, maxValue * 1.0 / i);
        }
        return maxAvg;
    }
}
