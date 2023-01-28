package com.welph.leecode.part_500_1000.part_621_640;

import java.util.*;

/**
 * 在 LeetCode 商店中， 有 n 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，
 * 每个大礼包以优惠的价格捆绑销售一组物品。
 * 给你一个整数数组 price 表示物品价格，其中 price[i] 是第 i 件物品的价格。
 * 另有一个整数数组 needs 表示购物清单，其中 needs[i] 是需要购买第 i 件物品的数量。
 * 还有一个数组 special 表示大礼包，special[i] 的长度为 n + 1 ，
 * 其中 special[i][j] 表示第 i 个大礼包中内含第 j 件物品的数量，且 special[i][n]
 * （也就是数组中的最后一个整数）为第 i 个大礼包的价格。
 * 返回 确切 满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。
 * 你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限次购买。
 * <p>
 * 示例 1：
 * 输入：price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
 * 输出：14
 * 解释：有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。
 * 大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。
 * 大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。
 * 需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。
 * <p>
 * 示例 2：
 * 输入：price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
 * 输出：11
 * 解释：A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
 * 可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。
 * 需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。
 * 不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。
 * <p>
 * 提示：
 * n == price.length
 * n == needs.length
 * 1 <= n <= 6
 * 0 <= price[i] <= 10
 * 0 <= needs[i] <= 10
 * 1 <= special.length <= 100
 * special[i].length == n + 1
 * 0 <= special[i][j] <= 50
 */
public class Solution638 {

    public static void main(String[] args) {
        ArrayList<List<Integer>> objects = new ArrayList<>();
        objects.add(Arrays.asList(1, 1, 0, 4));
        objects.add(Arrays.asList(2, 2, 1, 9));
        System.out.println(shoppingOffers(
                Arrays.asList(2, 3, 4),
                objects,
                Arrays.asList(1, 2, 1)
        ));
    }

    /**
     * 先来个dfs
     * //todo 看了题解,  没考虑到list的比较时按照长度和数据的相等的(导致自己想着法的使用位运算去代替)
     */
    public static int shoppingOffers(List<Integer> price, List<List<Integer>> special,
                                     List<Integer> needs) {
        int n = price.size();
        //剔除不合法的数据信息
        List<List<Integer>> spec = new ArrayList<>();
        for (List<Integer> arr : special) {
            int count = 0;
            int total = 0;
            for (int i = 0; i < n; i++) {
                count += arr.get(i);
                total += arr.get(i) * price.get(i);
            }
            if (count >= 0 && total > arr.get(n)) {
                spec.add(arr);
            }
        }
        //由于list的hashcode和equal是按照值判断的, 所以可以不用自定义比较方法
        Map<List<Integer>, Integer> exists = new HashMap<>();
        return dfs(price, spec, needs, n, exists);
    }

    private static int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> curNeeds, int n,
                           Map<List<Integer>, Integer> exists) {
        if (!exists.containsKey(curNeeds)) {//记忆化, 帮助后面的总金额计算
            int min = 0;
            for (int i = 0; i < n; i++) {
                min += curNeeds.get(i) * price.get(i);
            }
            LABEL:
            for (List<Integer> arr : special) {
                List<Integer> nexNeeds = new ArrayList<>(n);
                for (int i = 0; i < n; i++) {
                    if (curNeeds.get(i) < arr.get(i)) {
                        continue LABEL;
                    }
                    nexNeeds.add(curNeeds.get(i) - arr.get(i));
                }
                min = Math.min(min, dfs(price, special, nexNeeds, n, exists) + arr.get(n));
            }
            exists.put(curNeeds, min);
        }
        return exists.get(curNeeds);
    }
}
