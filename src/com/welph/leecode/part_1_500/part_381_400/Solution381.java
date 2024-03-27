package com.welph.leecode.part_1_500.part_381_400;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * 设计一个支持在平均 时间复杂度 O(1) 下， 执行以下操作的数据结构。
 * <p>
 * 注意: 允许出现重复元素。
 * insert(val)：向集合中插入元素 val。
 * remove(val)：当 val 存在时，从集合中移除一个 val。
 * getRandom：从现有集合中随机获取一个元素。每个元素被返回的概率应该与其在集合中的数量呈线性相关。
 * 示例:
 * <p>
 * // 初始化一个空的集合。
 * RandomizedCollection collection = new RandomizedCollection();
 * <p>
 * // 向集合中插入 1 。返回 true 表示集合不包含 1 。
 * collection.insert(1);
 * <p>
 * // 向集合中插入另一个 1 。返回 false 表示集合包含 1 。集合现在包含 [1,1] 。
 * collection.insert(1);
 * <p>
 * // 向集合中插入 2 ，返回 true 。集合现在包含 [1,1,2] 。
 * collection.insert(2);
 * <p>
 * // getRandom 应当有 2/3 的概率返回 1 ，1/3 的概率返回 2 。
 * collection.getRandom();
 * <p>
 * // 从集合中删除 1 ，返回 true 。集合现在包含 [1,2] 。
 * collection.remove(1);
 * <p>
 * // getRandom 应有相同概率返回 1 和 2 。
 * collection.getRandom();
 */
public class Solution381 {

    public static void main(String[] args) {
        RandomizedCollection obj = new RandomizedCollection();
        /*
         * boolean param_1 = obj.insert(1);
         * boolean param_2 = obj.remove(2);
         * int param_3 = obj.getRandom();
         */
        // ["RandomizedCollection","insert","insert","insert","insert","insert","remove",
        // "remove","remove","insert","remove","getRandom","getRandom","getRandom",
        // "getRandom","getRandom","getRandom","getRandom","getRandom","getRandom","getRandom"]
        // [[],[1],[1],[2],[2],[2],[1],[1],[2],[1],[2],[],[],[],[],[],[],[],[],[],[]]
        obj.insert(1);
        obj.insert(1);
        obj.insert(2);
        obj.insert(2);
        obj.insert(2);

        obj.remove(1);
        obj.remove(1);
        obj.remove(2);
        obj.insert(1);
        obj.remove(2);
    }

    /**
     * {@link com.welph.leecode.part_1_500.part_361_380.Solution380} 类似, 但这里需要支持重复值
     */
    static class RandomizedCollection {
        static Random random = new Random();
        Map<Integer, Node> map;
        ArrayList<Node> list;

        public RandomizedCollection() {
            map = new HashMap<>();
            list = new ArrayList<>();
        }

        public boolean insert(int val) {
            Node node = map.get(val);
            boolean noExist = node == null;
            Node current = new Node(list.size(), val);
            current.pre = node;
            map.put(val, current);
            list.add(current);
            return noExist;
        }

        public boolean remove(int val) {
            Node node = map.get(val);
            if (null == node) {
                return false;
            }
            // 找到目标的索引
            int index = node.index;
            Node last = list.get(list.size() - 1);
            list.set(index, last);
            // todo 问题出在这里
            last.index = index;
            list.remove(list.size() - 1);
            if (node.pre != null) {
                map.put(val, node.pre);
            } else {
                map.remove(val);
            }
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size())).val;
        }

        static class Node {
            int index;
            int val;
            Node pre;

            public Node(int index, int val) {
                this.index = index;
                this.val = val;
            }
        }
    }

    /* 官方题解 */
    class RandomizedCollection2 {
        Map<Integer, Set<Integer>> idx;
        List<Integer> nums;

        /** Initialize your data structure here. */
        public RandomizedCollection2() {
            idx = new HashMap<Integer, Set<Integer>>();
            nums = new ArrayList<Integer>();
        }

        /**
         * Inserts a value to the collection. Returns true if the collection did not
         * already contain the specified element.
         */
        public boolean insert(int val) {
            nums.add(val);
            Set<Integer> set = idx.getOrDefault(val, new HashSet<Integer>());
            set.add(nums.size() - 1); // 通过set存储下标 很好, 不用和我上面那样创建node对象, 有数据重复
            idx.put(val, set);
            return set.size() == 1;
        }

        /**
         * Removes a value from the collection. Returns true if the collection contained
         * the specified element.
         */
        public boolean remove(int val) {
            if (!idx.containsKey(val)) {
                return false;
            }
            Iterator<Integer> it = idx.get(val).iterator();
            int i = it.next();
            int lastNum = nums.get(nums.size() - 1);
            nums.set(i, lastNum);
            idx.get(val).remove(i);
            idx.get(lastNum).remove(nums.size() - 1);
            if (i < nums.size() - 1) {
                idx.get(lastNum).add(i);
            }
            if (idx.get(val).size() == 0) {
                idx.remove(val);
            }
            nums.remove(nums.size() - 1);
            return true;
        }

        /** Get a random element from the collection. */
        public int getRandom() {
            return nums.get((int) (Math.random() * nums.size()));
        }
    }
}
