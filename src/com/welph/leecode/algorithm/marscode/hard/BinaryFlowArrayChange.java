package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小M正在进行一次数据处理，他有一个长度为 n 的整数数组 arr，包含从 1 到 n 的所有数字。
 * 同时，他有一个初始状态为全 0 的长度为 n 的二进制字符串。
 *
 * 在每一步操作中，小M 会根据 arr[i] 的指示将二进制字符串中的第 arr[i] 位设为 1。
 *
 * 小M 的任务是确认在某些步骤中，是否会形成恰好长度为 m 的连续 1 的子串。
 * 如果在某步操作中形成了这种子串，请返回出现这一情形的最后一步操作的编号；
 * 如果直到最后都没有出现这种情况，则返回 -1。
 */
public class BinaryFlowArrayChange {

    public static int solution(int[] arr, int m) {
        if (m > arr.length) {
            return -1;
        }
        //todo 题目没理解.... 并不是单纯的m=1就m=2
       /* Map<Integer, Integer> map = new HashMap<>();
        m = m == 1 ? 2 : m;
        for (int i = 1; i <= arr.length; i++) {
            int val = arr[i - 1];
            Integer prev = map.get(val - 1);
            if (null == prev) {
                prev = val;
            } else {
                map.remove(val - 1);
            }
            Integer next = map.get(val + 1);
            if (null == next) {
                next = val;
            } else {
                map.remove(val + 1);
            }
            if (next - prev + 1 >= m) {
                return i;
            }
            if (prev.equals(next)) {
                map.put(prev, prev);
            } else {
                map.put(prev, next);
                map.put(next, prev);
            }
        }*/
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(solution(new int[]{2, 4, 1, 3, 5}, 1) == 3);
        System.out.println(solution(new int[]{4, 1, 3, 5, 2}, 2) == 3);
        System.out.println(solution(new int[]{1, 3, 2, 4}, 3) == 3);
        System.out.println(solution(new int[]{5, 3, 1, 4, 2}, 1) == 4);
        System.out.println(solution(new int[]{2, 3, 4, 1}, 2) == 2);
    }

}
