package com.welph.leecode.part_1_500.part_381_400;

import java.util.*;

/**
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。如果问题中出现了给定的已知条件中没有出现的字符串，也需要用 -1.0 替代这个答案。
 * <p>
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 * <p>
 * 示例 1：
 * 输入：equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
 * 输出：[6.00000,0.50000,-1.00000,1.00000,-1.00000]
 * 解释：
 * 条件：a / b = 2.0, b / c = 3.0
 * 问题：a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ?
 * 结果：[6.0, 0.5, -1.0, 1.0, -1.0 ]
 * <p>
 * 示例 2：
 * 输入：equations = [["a","b"],["b","c"],["bc","cd"]], values = [1.5,2.5,5.0], queries = [["a","c"],["c","b"],["bc","cd"],["cd","bc"]]
 * 输出：[3.75000,0.40000,5.00000,0.20000]
 * <p>
 * 示例 3：
 * 输入：equations = [["a","b"]], values = [0.5], queries = [["a","b"],["b","a"],["a","c"],["x","y"]]
 * 输出：[0.50000,2.00000,-1.00000,-1.00000]
 * <p>
 * 提示：
 * 1 <= equations.length <= 20
 * equations[i].length == 2
 * 1 <= Ai.length, Bi.length <= 5
 * values.length == equations.length
 * 0.0 < values[i] <= 20.0
 * 1 <= queries.length <= 20
 * queries[i].length == 2
 * 1 <= Cj.length, Dj.length <= 5
 * Ai, Bi, Cj, Dj 由小写英文字母与数字组成
 */
public class Solution399 {

    public static void main(String[] args) {
        List<List<String>> equations = new ArrayList<List<String>>() {{
            add(Arrays.asList("a", "b"));
            add(Arrays.asList("b", "c"));
        }};
        double[] values = {2.0, 3.0};
        //[["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]]
        List<List<String>> queries = new ArrayList<List<String>>() {{
            add(Arrays.asList("a", "c"));
            add(Arrays.asList("b", "a"));
            add(Arrays.asList("a", "e"));
            add(Arrays.asList("a", "a"));
            add(Arrays.asList("x", "x"));//未知
        }};
        double[] doubles = calcEquation(equations, values, queries);
        System.out.println(Arrays.toString(doubles));
    }


    /**
     * 并查集, 查看数据之间是否存在关联
     * ----------
     * Prefect 使用并查集+深度优先搜索
     */
    public static double[] calcEquation(List<List<String>> equations, double[] values,
                                        List<List<String>> queries) {
        int length = equations.size();
        int size = queries.size();
        double[] doubles = new double[size];
        //
        Map<String, String> father = new HashMap<>();
        Map<String, Integer> rank = new HashMap<>();
        String s1;
        String s2;
        List<Edge> edges = new ArrayList<>();
        Map<String, Integer> head = new HashMap<>();
        List<String> equation;
        Edge edge;
        for (int i = 0; i < length; i++) {
            equation = equations.get(i);
            edge = new Edge();
            edge.val = values[i];
            edge.to = equation.get(1);
            edge.next = head.get(equation.get(0));
            edges.add(edge);
            head.put(equation.get(0), edges.size() - 1);

            edge = new Edge();
            edge.val = 1 / values[i];
            edge.to = equation.get(0);
            edge.next = head.get(equation.get(1));
            edges.add(edge);
            head.put(equation.get(1), edges.size() - 1);

            s1 = findFather(father, equation.get(0), true);
            s2 = findFather(father, equation.get(1), true);
            merge(father, rank, s1, s2);
        }

        List<String> query;
        for (int i = 0; i < size; i++) {
            query = queries.get(i);
            s1 = query.get(0);
            s2 = query.get(1);
            if (!father.containsKey(s1) || !father.containsKey(s2)) {
                doubles[i] = -1.0;
            } else if (s1.equals(s2)) {
                doubles[i] = 1.0;
            } else if (!findFather(father, s1, false).equals(findFather(father, s2, false))) {
                doubles[i] = -1.0;
            } else {
                doubles[i] = calculate(edges, head, s1, s2);
            }
        }
        return doubles;
    }

    //从s1到s2的最短路径 --广度优先搜索
    private static double calculate(List<Edge> edges, Map<String, Integer> head,
                                    String s1, String s2) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        Queue<Double> multis = new LinkedList<>();
        queue.add(s1);
        multis.add(1.0);
        visited.add(s1);
        Edge edge;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String poll = queue.poll();
                Double val = multis.poll();
                if (s2.equals(poll)) {
                    return val;
                }
                for (Integer eg = head.get(poll); eg != null; eg = edges.get(eg).next) {
                    edge = edges.get(eg);
                    if (!visited.contains(edge.to)) {
                        queue.add(edge.to);
                        multis.add(val * edge.val);
                    }
                }
            }
        }
        return -1.0;
    }

    public static String findFather(Map<String, String> father, String s, boolean insert) {
        if (insert && !father.containsKey(s)) {
            father.put(s, s);
        }
        String root = s;
        String m;
        while ((m = father.get(root)) != null && !root.equals(m)) {
            root = m;
        }
        while (!s.equals(root)) {
            m = father.get(s);
            father.put(s, root);
            s = m;
        }
        return s;
    }

    private static void merge(Map<String, String> father, Map<String, Integer> rank, String s1, String s2) {
        if (!s1.equals(s2)) {
            Integer r1 = rank.getOrDefault(s1, 0);
            Integer r2 = rank.getOrDefault(s2, 0);
            if (r2 > r1) {
                father.put(s1, s2);
                rank.remove(s1);
            } else if (r1 > r2) {
                father.put(s2, s1);
                rank.remove(s2);
            } else {
                father.put(s2, s1);
                rank.put(s1, r1 + 1);
                rank.remove(s2);
            }
        }
    }


    static class Edge {//双向图
        double val; //值
        String to;
        Integer next; //下一条边
    }
}
