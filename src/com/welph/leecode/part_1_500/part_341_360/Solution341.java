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
 * .  initialize iterator with nestedList
 * .  res = []
 * .  while iterator.hasNext()
 * .      append iterator.next() to the end of res
 * .  return res
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
        public boolean hasNext() { //[[]] 这样的数据格式应该返回false
            while (!stack.isEmpty() && stack.peek().hasNext()) {
                Iterator<NestedInteger> peek = stack.peek();
                NestedInteger next = peek.next();
                if (!peek.hasNext()) {
                    stack.pop();
                }
                if (next.isInteger()) {
                    val = next.getInteger();
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
}
