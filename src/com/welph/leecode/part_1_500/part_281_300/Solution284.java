package com.welph.leecode.part_1_500.part_281_300;

import java.util.Iterator;

/**
 * 给定一个迭代器类的接口，接口包含两个方法：next()和hasNext()。
 * 设计并实现一个支持peek()操作的顶端迭代器 -- 其本质就是把原本应由next()方法返回的元素peek()出来。
 * <p>
 * 示例:
 * 假设迭代器被初始化为列表[1,2,3]。
 * <p>
 * 调用next() 返回 1，得到列表中的第一个元素。
 * 现在调用peek()返回 2，下一个元素。在此之后调用next() 仍然返回 2。
 * 最后一次调用next()返回 3，末尾元素。在此之后调用hasNext()应该返回 false。
 * <p>
 * 进阶：你将如何拓展你的设计？使之变得通用化，从而适应所有的类型，而不只是整数型？
 */
public class Solution284 {

    public static void main(String[] args) {
        // ["PeekingIterator","hasNext","peek","peek","next","next","peek","peek",
        // "next","hasNext","peek","hasNext","next","hasNext"]
        // [[[1,2,3,4]],[],[],[],[],[],[],[],[],[],[],[],[],[]]
    }

    static class PeekingIterator implements Iterator<Integer> {
        Iterator<Integer> iterator;
        Integer peek;
        boolean load = false;

        public PeekingIterator(Iterator<Integer> iterator) {
            // initialize any member here.
            this.iterator = iterator;
        }

        // Returns the next element in the iteration without advancing the iterator.
        public Integer peek() {
            if (!load) {
                if (iterator.hasNext()) {
                    peek = iterator.next();
                    load = true;
                } else {
                    peek = null;
                }
            }
            return peek;
        }

        // hasNext() and next() should behave the same as in the Iterator interface.
        // Override them if needed.
        @Override
        public Integer next() {
            if (load) {
                load = false;
                return peek;
            }
            return iterator.next();
        }

        @Override
        public boolean hasNext() {
            return load || iterator.hasNext();
        }
    }

    /* 官方题解 */
    class PeekingIterator1 implements Iterator<Integer> {
        private Iterator<Integer> iterator;
        private Integer nextElement;

        public PeekingIterator1(Iterator<Integer> iterator) {
            this.iterator = iterator;
            nextElement = iterator.hasNext() ? iterator.next() : null; // 提前拿出来
        }

        public Integer peek() {
            return nextElement;
        }

        @Override
        public Integer next() {
            Integer ret = nextElement;
            nextElement = iterator.hasNext() ? iterator.next() : null;
            return ret;
        }

        @Override
        public boolean hasNext() {
            return nextElement != null;
        }
    }

}
