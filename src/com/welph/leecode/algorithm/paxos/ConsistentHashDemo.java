package com.welph.leecode.algorithm.paxos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Viber
 * @version 1.0
 * @apiNote 一致性hash算法的基本逻辑
 * @since 2021/9/9 13:38
 */
public class ConsistentHashDemo {
    public static void main(String[] args) throws Exception {
        String[] arr = {"A", "B", "C", "D", "E"};
        ConsistentHashSelector consistentHashSelector = new ConsistentHashSelector(arr);
        System.out.println(consistentHashSelector.select("14"));
        consistentHashSelector.addEntry("F");
        System.out.println(consistentHashSelector.select("14"));
        System.out.println(consistentHashSelector.select("131"));
        consistentHashSelector.moveEntry("C");
        System.out.println(consistentHashSelector.select("131"));
    }


    static class ConsistentHashSelector {
        String[] arr;
        TreeMap<Long, String> virtualMap;
        int replicaNumber = 160; //虚拟hash节点个数

        public ConsistentHashSelector(String[] arr) throws Exception {
            virtualMap = new TreeMap<>();
            this.arr = arr;
            for (int i = 0; i < arr.length; i++) {
                for (int j = 0; j < replicaNumber / 4; j++) {
                    byte[] digest = getMD5(arr[i] + j);
                    for (int h = 0; h < 4; h++) {
                        long m = hash(digest, h);
                        virtualMap.put(m, arr[i]);
                    }
                }
            }
        }

        public String select(String key) throws Exception {
            byte[] digest = getMD5(key);
            long hash = hash(digest, 0);
            Map.Entry<Long, String> entry = virtualMap.ceilingEntry(hash);
            if (entry == null) {
                entry = virtualMap.firstEntry();
            }
            return entry.getValue();
        }

        public void addEntry(String entry) throws Exception {
            for (int j = 0; j < replicaNumber / 4; j++) {
                byte[] digest = getMD5(entry + j);
                for (int h = 0; h < 4; h++) {
                    long m = hash(digest, h);
                    virtualMap.put(m, entry);
                }
            }
        }

        public void moveEntry(String entry) throws Exception {
            for (int j = 0; j < replicaNumber / 4; j++) {
                byte[] digest = getMD5(entry + j);
                for (int h = 0; h < 4; h++) {
                    long m = hash(digest, h);
                    virtualMap.remove(m);
                }
            }
        }
    }


    private static long hash(byte[] digest, int number) {
        return (((long) (digest[3 + number * 4] & 0xFF) << 24)
                | ((long) (digest[2 + number * 4] & 0xFF) << 16)
                | ((long) (digest[1 + number * 4] & 0xFF) << 8)
                | (digest[number * 4] & 0xFF))
                & 0xFFFFFFFFL; //32位
    }

    public static byte[] getMD5(String source) throws NoSuchAlgorithmException {
        return MessageDigest.getInstance("MD5").digest(source.getBytes());
    }
}
