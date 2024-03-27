package com.welph.leecode.part_1_500.part_321_340;

import java.util.*;

/**
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。
 * 请你对该行程进行重新规划排序。
 * <p>
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。如果存在多种有效的行程，
 * 请你按字典排序返回最小的行程组合。
 * <p>
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 * <p>
 * 示例 1：
 * 输入：tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
 * 输出：["JFK","MUC","LHR","SFO","SJC"]
 * <p>
 * 示例 2：
 * 输入：tickets =
 * [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
 * 输出：["JFK","ATL","JFK","SFO","ATL","SFO"]
 * 解释：另一种有效的行程是 ["JFK","SFO","ATL","JFK","ATL","SFO"] ，但是它字典排序更大更靠后。
 * <p>
 * 提示：
 * 1 <= tickets.length <= 300
 * tickets[i].length == 2
 * fromi.length == 3
 * toi.length == 3
 * fromi 和 toi 由大写英文字母组成
 * fromi != toi
 */
public class Solution332 {

    public static void main(String[] args) {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "MUC"));
        tickets.add(Arrays.asList("SFO", "SJC"));
        tickets.add(Arrays.asList("LHR", "SFO"));

        System.out.println(findItinerary(tickets));

        List<List<String>> tickets1 = new ArrayList<>();
        tickets1.add(Arrays.asList("JFK", "SFO"));
        tickets1.add(Arrays.asList("JFK", "ATL"));
        tickets1.add(Arrays.asList("SFO", "ATL"));
        tickets1.add(Arrays.asList("ATL", "JFK"));
        tickets1.add(Arrays.asList("ATL", "SFO"));

        System.out.println(findItinerary(tickets1));
    }

    // 所有机票必须处理一遍.
    public static List<String> findItinerary(List<List<String>> tickets) {
        LinkedList<String> result = new LinkedList<>();
        // 每次尽可能寻找小的值
        Map<String, Edge> route = new HashMap<>();
        tickets.forEach(ticket -> route.computeIfAbsent(ticket.get(0), s -> new Edge()).to.add(ticket.get(1)));

        route.forEach((s, edge) -> {
            edge.to.sort(String::compareTo);
            edge.visit = new boolean[edge.to.size()];
        });

        result.add("JFK");
        return findItinerary(route, "JFK", 0, tickets.size(), result);
    }

    public static List<String> findItinerary(Map<String, Edge> route, String from,
            int depth, int expect, LinkedList<String> result) {
        if (depth == expect) {
            return result;
        }
        Edge edge = route.get(from);
        if (edge != null) {
            for (int i = 0; i < edge.to.size(); i++) {
                if (edge.visit[i]) {
                    continue;
                }
                String t = edge.to.get(i);
                edge.visit[i] = true;
                result.offer(t);
                List<String> r = findItinerary(route, t, depth + 1, expect, result);
                if (null != r) {
                    return r;
                }
                result.removeLast();
                edge.visit[i] = false;
            }
        }
        return null;
    }

    static class Edge {
        List<String> to = new ArrayList<>();
        boolean[] visit;
    }

    /* 官方题解 */
    // 因为这里面的所有机票必须用一次 且只能用一次, 并且题目是保证有效的, 说明不用考虑是否走过, 考虑剔除就行
    Map<String, PriorityQueue<String>> map = new HashMap<String, PriorityQueue<String>>();
    List<String> itinerary = new LinkedList<String>();

    public List<String> findItinerary2(List<List<String>> tickets) {
        for (List<String> ticket : tickets) {
            String src = ticket.get(0), dst = ticket.get(1);
            if (!map.containsKey(src)) {
                map.put(src, new PriorityQueue<String>());
            }
            map.get(src).offer(dst);
        }
        dfs("JFK");
        Collections.reverse(itinerary);
        return itinerary;
    }

    public void dfs(String curr) {
        while (map.containsKey(curr) && map.get(curr).size() > 0) {
            String tmp = map.get(curr).poll();
            dfs(tmp);
        }
        itinerary.add(curr);
    }

}
