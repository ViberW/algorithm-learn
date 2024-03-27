package com.welph.leecode.part_500_1000.part_501_520;

import java.util.*;

/**
 * 电子游戏“辐射4”中，任务 “通向自由” 要求玩家到达名为 “Freedom Trail Ring” 的金属表盘，并使用表盘拼写特定关键词才能开门。
 * <p>
 * 给定一个字符串 ring ，表示刻在外环上的编码；给定另一个字符串 key ，表示需要拼写的关键词。
 * 您需要算出能够拼写关键词中所有字符的最少步数。
 * <p>
 * 最初，ring 的第一个字符与 12:00 方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，
 * 然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * <p>
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * <p>
 * 您可以将 ring 顺时针或逆时针旋转 一个位置 ，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，
 * 并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。
 * 按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 * <p>
 * 示例 1：
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 * 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
 * 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 * 当然, 我们还需要1步进行拼写。
 * 因此最终的输出是 4。
 * <p>
 * 示例 2:
 * 输入: ring = "godding", key = "godding"
 * 输出: 13
 * <p>
 * 提示：
 * 1 <= ring.length, key.length <= 100
 * ring 和 key 只包含小写英文字母
 * 保证 字符串 key 一定可以由字符串 ring 旋转拼出
 */
public class Solution514 {

    public static void main(String[] args) {
        // System.out.println(findRotateSteps("godding", "gd"));
        System.out.println(findRotateSteps1("caotmcaataijjxi", "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx"));

    }

    /**
     * 这动态规划怎么写呢?
     * dp[i][j] 表示key的第i个字符 对应ring的j个字符对应12点的位置 最小步数
     * <p>
     * dp[i][j] = min(dp[i-1][k] + min(abs(j - i), ringChars.length- abs(i-j)))+1)
     * ---todo 参考官方 完美结合实际情况的动态规划
     */
    public static int findRotateSteps1(String ring, String key) {
        // 构建相同字符有多少个位置
        List<Integer>[] pos = new List[26];
        for (int i = 0; i < pos.length; i++) {
            pos[i] = new ArrayList<>();
        }
        for (int i = 0; i < ring.length(); i++) {
            pos[ring.charAt(i) - 'a'].add(i); // 这个字符在哪个位置
        }
        int[][] dp = new int[key.length()][ring.length()];
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        for (Integer i : pos[key.charAt(0) - 'a']) {
            dp[0][i] = Math.min(i, key.length() - i) + 1;
        }
        for (int i = 1; i < key.length(); i++) {
            for (Integer j : pos[key.charAt(i) - 'a']) {
                for (Integer k : pos[key.charAt(i - 1) - 'a']) {
                    dp[i][j] = Math.min(dp[i][j],
                            dp[i - 1][k] + Math.min(Math.abs(j - k), // 顺时针
                                    ring.length() - Math.abs(j - k) // 逆时针
                            ) + 1); // 按一下
                }
            }
        }
        return Arrays.stream(dp[key.length() - 1]).min().getAsInt();
    }

    /**
     * 表示当前到 i 本身出于ring的j位置, 找到当前位置的数据信息
     * ------------
     * A-B D - 反而是最小的
     * A-C - 第一次到c最小
     * 使用广度优先搜索 找到目标的逻辑
     * ----------------
     * 超时 而且导致了 queue 越来越大
     */
    public static int findRotateSteps(String ring, String key) {
        // 预处理 保存某个点到每个点的距离. 但问题在于并不是每个点都需要重复判断, 广度保存每个节点到每个节点的可能长度
        List<Node>[][] dp = new List[26][26];
        char[] ringChars = ring.toCharArray();
        int start = ringChars[0] - 'a';
        char[] keyChars = key.toCharArray();
        for (int i = 0; i < keyChars.length; i++) {
            dp[start][keyChars[i] - 'a'] = new ArrayList<>();
            start = keyChars[i] - 'a';
        }
        List<Node> nodes;
        int min;
        for (int i = 0; i < ringChars.length; i++) {
            for (int j = i; j < ringChars.length; j++) {
                nodes = dp[ringChars[i] - 'a'][ringChars[j] - 'a'];
                min = Math.min(j - i, i + ringChars.length - j);
                if (null != nodes) {
                    nodes.add(new Node(i, j, min + 1));
                }
                if (i != j) {
                    nodes = dp[ringChars[j] - 'a'][ringChars[i] - 'a'];
                    if (null != nodes) {
                        nodes.add(new Node(j, i, min + 1));
                    }
                }
            }
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0, 0));
        start = ringChars[0] - 'a';
        for (int i = 0; i < keyChars.length; i++) {
            nodes = dp[start][keyChars[i] - 'a'];
            start = keyChars[i] - 'a';
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Node poll = queue.poll();
                for (Node node : nodes) {
                    if (poll.j == node.i) {
                        queue.add(new Node(node.i, node.j, poll.step + node.step));
                    }
                }
            }
        }
        return queue.stream().min(Comparator.comparingInt(value -> value.step))
                .map(node -> node.step).orElse(0);
    }

    static class Node {
        int i;
        int j;
        int step;

        public Node(int i, int j, int step) {
            this.i = i;
            this.j = j;
            this.step = step;
        }

    }
}
