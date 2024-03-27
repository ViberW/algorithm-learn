package com.welph.leecode.part_1_500.part_341_360;

import java.util.*;

/**
 * 给你一个嵌套的整数列表 nestedList 。每个元素要么是一个整数，要么是一个列表；
 * 该列表的元素也可能是整数或者是其他列表。请你实现一个迭代器将其扁平化，使之能够遍历这个列表中的所有整数。
 * <p>
 * 实现扁平迭代器类 NestedIterator ：
 * <p>
 * NestedIterator(List<NestedInteger> nestedList) 用嵌套列表 nestedList 初始化迭代器。
 * int next() 返回嵌套列表的下一个整数。
 * boolean hasNext() 如果仍然存在待迭代的整数，返回 true ；否则，返回 false 。
 * 你的代码将会用下述伪代码检测：
 * <p>
 * . initialize iterator with nestedList
 * . res = []
 * . while iterator.hasNext()
 * . append iterator.next() to the end of res
 * . return res
 * 如果 res 与预期的扁平化列表匹配，那么你的代码将会被判为正确。
 * <p>
 * 示例 1：
 * 输入：nestedList = [[1,1],2,[1,1]]
 * 输出：[1,1,2,1,1]
 * 解释：通过重复调用next 直到hasNext 返回 false，next返回的元素的顺序应该是: [1,1,2,1,1]。
 * 示例 2：
 * 输入：nestedList = [1,[4,[6]]]
 * 输出：[1,4,6]
 * 解释：通过重复调用next直到hasNext 返回 false，next返回的元素的顺序应该是: [1,4,6]。
 * <p>
 * 提示：
 * 1 <= nestedList.length <= 500
 * 嵌套列表中的整数值在范围 [-106, 106] 内
 */
public class Solution341 {

    public static void main(String[] args) {
        List<NestedInteger> nestedList = new ArrayList<>();
        // [[1,1],2,[1,1]]
        nestedList.add(ni(ni(1), ni(1)));
        nestedList.add(ni(ni(2)));
        nestedList.add(ni(ni(1), ni(1)));

        NestedIterator nestedIterator = new NestedIterator(nestedList);
        while (nestedIterator.hasNext()) {
            System.out.println(nestedIterator.next());
        }
    }

    public static class NestedIterator implements Iterator<Integer> {
        Stack<Iterator<NestedInteger>> stack = new Stack<>();
        Integer val;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            return val;
        }

        @Override
        public boolean hasNext() { // [[]] 这样的数据格式应该返回false
            while (!stack.isEmpty() && stack.peek().hasNext()) {
                Iterator<NestedInteger> peek = stack.peek();
                NestedInteger next = peek.next();
                if (!peek.hasNext()) {
                    stack.pop();
                }
                if (next.isInteger()) {
                    val = next.getInteger(); // 根据下面的官方题解, val并不需要提前取出来
                    return true;
                } else {
                    List<NestedInteger> list = next.getList();
                    if (null != list && !list.isEmpty()) {
                        stack.push(list.iterator());
                    }
                }
            }
            val = null;
            return false;
        }
    }

    public static NestedInteger ni(Integer val) {
        return new NestedInteger() {
            @Override
            public boolean isInteger() {
                return true;
            }

            @Override
            public Integer getInteger() {
                return val;
            }

            @Override
            public List<NestedInteger> getList() {
                return null;
            }
        };
    }

    public static NestedInteger ni(NestedInteger... nis) {
        return new NestedInteger() {
            @Override
            public boolean isInteger() {
                return false;
            }

            @Override
            public Integer getInteger() {
                return null;
            }

            @Override
            public List<NestedInteger> getList() {
                return Arrays.asList(nis);
            }
        };
    }

    public interface NestedInteger {
        /**
         * 结果是不是一个单独的Integer?
         */
        boolean isInteger();

        /**
         * 结果持有一个integer返回, 否则为null
         */
        Integer getInteger();

        /**
         * 返回持有一个列表, 若持有一个integer,则返回空list
         */
        List<NestedInteger> getList();
    }

    /* 官方题解 */

    // 深度优先搜索
    public class NestedIterator2 implements Iterator<Integer> {
        private List<Integer> vals;
        private Iterator<Integer> cur;

        public NestedIterator2(List<NestedInteger> nestedList) {
            vals = new ArrayList<Integer>();
            dfs(nestedList); // 预先遍历好, 但是耗空间
            cur = vals.iterator();
        }

        @Override
        public Integer next() {
            return cur.next();
        }

        @Override
        public boolean hasNext() {
            return cur.hasNext();
        }

        private void dfs(List<NestedInteger> nestedList) {
            for (NestedInteger nest : nestedList) {
                if (nest.isInteger()) {
                    vals.add(nest.getInteger());
                } else {
                    dfs(nest.getList());
                }
            }
        }
    }

    // 使用栈
    // 比我的好 少了value的存储
    public class NestedIterator3 implements Iterator<Integer> {
        // 存储列表的当前遍历位置
        private Deque<Iterator<NestedInteger>> stack;

        public NestedIterator3(List<NestedInteger> nestedList) {
            stack = new LinkedList<Iterator<NestedInteger>>();
            stack.push(nestedList.iterator());
        }

        @Override
        public Integer next() {
            // 由于保证调用 next 之前会调用 hasNext，直接返回栈顶列表的当前元素
            return stack.peek().next().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                Iterator<NestedInteger> it = stack.peek();
                if (!it.hasNext()) { // 遍历到当前列表末尾，出栈
                    stack.pop();
                    continue;
                }
                // 若取出的元素是整数，则通过创建一个额外的列表将其重新放入栈中
                NestedInteger nest = it.next();
                if (nest.isInteger()) {
                    // 重新包装了一份list
                    List<NestedInteger> list = new ArrayList<NestedInteger>();
                    list.add(nest);
                    stack.push(list.iterator());
                    return true;
                }
                stack.push(nest.getList().iterator());
            }
            return false;
        }
    }
}
