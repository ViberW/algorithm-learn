package com.welph.leecode.algorithm.marscode.middle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 众所周知，每两周的周三是字节跳动的活动日。
 * 作为活动组织者的小A，在这次活动日上布置了一棵 Bytedance Tree。
 *
 * Bytedance Tree 由 n 个结点构成，每个结点的编号分别为 1，2，3......n，
 * 有 n - 1 条边将它们连接起来，根结点为 1。
 * 而且为了观赏性，小A 给 M 个结点挂上了 K 种礼物（0 ≤ K ≤ M ≤ N，且保证一个结点只有一个礼物）。
 *
 * 现在小A希望将 Bytedance Tree 划分为 K 个 Special 连通分块，送给参加活动的同学们，
 * 请问热心肠的你能帮帮他，告诉小A一共有多少种划分方式吗？
 *
 * 一个 Special 连通分块应该具有以下特性：
 *
 * Special 连通分块里只有一种礼物（该种类礼物的数量不限）。
 * Special 连通分块可以包含任意数量的未挂上礼物的结点。
 * 由于方案数可能过大，对结果取模 998244353。
 */
public class BytedanceTreeToGifts {
    static int mod = 998244353;

    public static int solution(int nodes, int decorations, List<List<Integer>> tree) {
        List<List<Integer>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= nodes; i++) {
            adjacencyList.add(new ArrayList<>());
        }
        for (int i = 1; i < tree.size(); i++) {
            List<Integer> integers = tree.get(i);
            Integer f = integers.get(0);
            Integer t = integers.get(1);
            adjacencyList.get(t).add(f);
            adjacencyList.get(f).add(t);
        }
        List<Integer> decorationList = tree.get(0);
        boolean[] visited = new boolean[nodes + 1];
        boolean[] visitedDecorations = new boolean[decorations + 1];
        int ret = 1;
        for (int i = 1; i <= nodes; i++) { // 遍历节点
            Integer decoration = decorationList.get(i - 1);
            if (!visited[i] && decoration != 0) {
                if (visitedDecorations[decoration]) {
                    return 0;
                }
                int[] dfs = dfs(adjacencyList, decorationList, visited, i, decoration);
                ret = (ret * dfs[0]) % mod;
                visitedDecorations[decoration] = true;
            }
        }
        return ret;
    }

    private static int[] dfs(List<List<Integer>> adjacencyList, List<Integer> decorationList,
                             boolean[] visited, int i, int decoration) {
        visited[i] = true;
        int[] res = new int[]{1, 0, 1};
        for (Integer child : adjacencyList.get(i)) {
            if (visited[child]) {
                continue;
            }
            Integer childDecorate = decorationList.get(child - 1);
            if (childDecorate == 0) {
                int[] dfs = dfs(adjacencyList, decorationList, visited, child, decoration);
                if (dfs[1] != 0) {
                    res[0] = (res[0] * (dfs[0] + dfs[2])) % mod;
                    res[1] |= 1;
                }
            } else if (childDecorate.equals(decoration)) {
                int[] dfs = dfs(adjacencyList, decorationList, visited, child, decoration);
                if (dfs[1] != 0) {
                    res[0] = (res[0] * (dfs[0] + dfs[2])) % mod;
                    res[2] = 0;
                }
            } else {
                res[1] = 1;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // You can add more test cases here
        List<List<Integer>> testTree1 = new ArrayList<>();
        testTree1.add(Arrays.asList(1, 0, 0, 0, 0, 2, 3));
        testTree1.add(Arrays.asList(1, 7));
        testTree1.add(Arrays.asList(3, 7));
        testTree1.add(Arrays.asList(2, 1));
        testTree1.add(Arrays.asList(3, 5));
        testTree1.add(Arrays.asList(5, 6));
        testTree1.add(Arrays.asList(6, 4));

        List<List<Integer>> testTree2 = new ArrayList<>();
        testTree2.add(Arrays.asList(1, 0, 1, 0, 2));
        testTree2.add(Arrays.asList(1, 2));
        testTree2.add(Arrays.asList(1, 5));
        testTree2.add(Arrays.asList(2, 4));
        testTree2.add(Arrays.asList(3, 5));

        System.out.println(solution(7, 3, testTree1) == 3);
        System.out.println(solution(5, 2, testTree2) == 0);
    }
}
