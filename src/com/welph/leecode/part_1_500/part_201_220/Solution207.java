package com.welph.leecode.part_1_500.part_201_220;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 你这个学期必须选修 numCourse 门课程，记为 0 到 numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * <p>
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * <p>
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释: 总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * <p>
 * 提示：
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <= numCourses <= 10^5
 */
public class Solution207 {

    public static void main(String[] args) {
        int[][] prerequisites = {{0, 2}, {1, 2}, {2, 0}};
        System.out.println(canFinish(3, prerequisites));
    }

    //若是循环了, 则永远不可能学习,  -- 拓扑排序
    //在不循环中 找出,总长度是否大于或等于 numCourses
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        return kahn(numCourses, prerequisites);
        //return depthSearch(numCourses, prerequisites);
    }

    /**
     * 基于Kahn算法 本质上是贪心算法
     */
    public static boolean kahn(int numCourses, int[][] prerequisites) {
        int[] inDegree = new int[numCourses];
        LinkedList<Integer>[] adj = new LinkedList[numCourses];
        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++; //到达的点+1;
            LinkedList<Integer> list = adj[prerequisite[1]];
            if (list == null) {
                list = new LinkedList<>();
                adj[prerequisite[1]] = list;
            }
            list.add(prerequisite[0]); //1能够到达的0的列表
        }
        //从那些inDegree[i] = 0 的点开始, 说明它是开头
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; ++i) {
            if (inDegree[i] == 0) queue.add(i);
        }
        int count = 0;
        for (; !queue.isEmpty(); count++) {
            int remove = queue.remove();//源头
            LinkedList<Integer> integers = adj[remove];
            if (null != integers && !integers.isEmpty()) {
                for (Integer integer : integers) {
                    inDegree[integer]--;
                    if (inDegree[integer] == 0) {
                        queue.add(integer);
                    }
                }
            }
        }
        return count == numCourses;
    }


    static boolean valid = true;

    /**
     * 深度优先搜索--不断找到前一个, 最终若是碰到已经存在的就返回,或者处理
     */
    public static boolean depthSearch(int numCourses, int[][] prerequisites) {
        valid = true;
        List<Integer>[] edges = new List[numCourses];
        int[] visited = new int[numCourses];
        for (int i = 0; i < numCourses; ++i) {
            edges[i] = new ArrayList<>();
        }
        for (int[] prerequisite : prerequisites) {
            edges[prerequisite[1]].add(prerequisite[0]);
        }
        for (int i = 0; i < numCourses && valid; i++) {
            if (visited[i] == 0) {
                dfs(i, visited, edges);
            }
        }
        return valid;
    }

    private static void dfs(int i, int[] visited, List<Integer>[] edges) {
        visited[i] = 1;
        for (Integer v : edges[i]) {
            if (visited[v] == 0) {
                dfs(v, visited, edges);
                if (!valid) {
                    return;
                }
            } else if (visited[v] == 1) {
                valid = false;//重新回来了
                return;
            }
        }
        visited[i] = 2;
    }
}
