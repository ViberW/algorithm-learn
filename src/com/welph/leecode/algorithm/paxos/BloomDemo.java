package com.welph.leecode.algorithm.paxos;

import java.util.BitSet;

/**
 * 布隆过滤器
 *
 * @author: Admin
 * @date: 2019/5/30
 * @Description: {相关描述}
 */
public class BloomDemo {

    private static final int SIZE = 1 << 24;
    BitSet bitSet = new BitSet(SIZE);
    Hash[] hashs = new Hash[8];
    private static final int seeds[] = new int[]{3, 5, 7, 9, 11, 13, 17, 19};

    public static void main(String[] args) {
        String email = "github:velsson";
        BloomDemo bloomDemo = new BloomDemo();
        System.out.println(email + "是否在列表中： " + bloomDemo.contains(email));
        bloomDemo.add(email);
        System.out.println(email + "是否在列表中： " + bloomDemo.contains(email));
        email = "github:welph";
        System.out.println(email + "是否在列表中： " + bloomDemo.contains(email));
    }

    public BloomDemo() {
        for (int i = 0; i < seeds.length; i++) {
            hashs[i] = new Hash(seeds[i]);
        }
    }

    public void add(String string) {
        for (Hash hash : hashs) {
            bitSet.set(hash.getHash(string), true);
        }
    }

    public boolean contains(String string) {
        boolean have = true;
        for (Hash hash : hashs) {
            have &= bitSet.get(hash.getHash(string));
        }
        return have;
    }

    class Hash {
        private int seed = 0;

        public Hash(int seed) {
            this.seed = seed;
        }

        public int getHash(String string) {
            int val = 0;
            int len = string.length();
            for (int i = 0; i < len; i++) {
                val = val * seed + string.charAt(i);
            }
            return val & (SIZE - 1);
        }
    }
}
