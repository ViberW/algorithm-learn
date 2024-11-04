package com.welph.leecode.algorithm.z7z8;

import java.util.*;

/**
 * 小C和他的领导小F计划一次飞行，但由于严格的航空管制，他们的飞机仅能按特定的路线飞行：
 * 飞机只能飞往当前机场的相邻机场或相同航空公司管理的机场。
 * <p>
 * 为了减少起飞次数，小C需要制定最优的飞行路线。机场由一个数组airports标识，其中：
 * 数组每个元素代表一个独特的机场，元素的值代表不同的航空公司。
 * airports[0]为起点，airports[airports.length - 1]为终点。
 * <p>
 * 假设小C当前在机场i，那么i - 1和i + 1（如果存在）代表邻近机场，飞机可以直接前往。
 * 如果在机场i，且存在airports[i] == airports[j]，则机场i和机场j同属一家航空公司，可直接飞往。
 * 求最小起飞次数。
 */
public class AirPortsWay {

    //bfs
    public static int solution(int[] airports) {
        int n = airports.length;
        if (n == 1) return 0; // 出发点和目的地相同

        // 用于存储同一航空公司的机场
        Map<Integer, List<Integer>> airlineMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            airlineMap.putIfAbsent(airports[i], new ArrayList<>());
            airlineMap.get(airports[i]).add(i);
        }

        // BFS
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        queue.offer(0);
        visited[0] = true;

        int flights = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int current = queue.poll();
                // 到达目的地
                if (current == n - 1) {
                    return flights;
                }

                // 检查前一个机场
                if (current - 1 >= 0 && !visited[current - 1]) {
                    visited[current - 1] = true;
                    queue.offer(current - 1);
                }

                // 检查后一个机场
                if (current + 1 < n && !visited[current + 1]) {
                    visited[current + 1] = true;
                    queue.offer(current + 1);
                }

                // 检查同一航空公司的所有机场
                if (airlineMap.containsKey(airports[current])) {
                    for (int next : airlineMap.get(airports[current])) {
                        if (!visited[next]) {
                            visited[next] = true;
                            queue.offer(next);
                        }
                    }
                    // 清空以避免重复访问
                    airlineMap.remove(airports[current]);
                }
            }
            flights++;
        }

        return -1; // 如果无法到达目的地
    }

    //dp动态规划
    public static int solution2(int[] airports) {
        int n = airports.length;
        int[] dp = new int[n];

        // 初始化 dp 数组
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {
            // 更新相邻机场的起飞次数
            if (i > 0) {
                dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            }
            if (i < n - 1) {
                dp[i] = Math.min(dp[i], dp[i + 1] + 1);
            }

            // 更新同一家航空公司的机场的起飞次数
            for (int j = 0; j < n; j++) {
                if (airports[j] == airports[i] && j != i) {
                    dp[j] = Math.min(dp[j], dp[i] + 1); //保留最小到达路径就行了
                }
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        //  You can add more test cases here
        int[] airports1 = {10, 12, 13, 12, 14};
        int[] airports2 = {10, 11, 12, 13, 14};

        System.out.println(solution(airports1) == 3);
        System.out.println(solution(airports2) == 4);
    }
}
