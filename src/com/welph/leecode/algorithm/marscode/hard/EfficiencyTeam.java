package com.welph.leecode.algorithm.marscode.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 小L 需要组建一个研究小组来完成一项关键任务。
 * 他有 n 位研究员，每位研究员都有自己的工作效率和技能速度。
 * 小L 希望从中挑选最多 k 位研究员组成团队，团队的整体表现值由以下公式计算：
 *
 * 整体表现值 = 所有被选中研究员的技能速度总和 * 被选中研究员中最低的工作效率
 *
 * 为了确保任务顺利进行，小L 想让这个团队的表现值达到最大化。
 * 由于结果可能会非常大，你需要将最终的最大表现值对 10^9 + 7 取余后返回。
 */
public class EfficiencyTeam {

    public static int solution(int n, int[] speed, int[] efficiency, int k) {
        int mod = 1000000007;
        //按照效率降速
        PriorityQueue<Integer> efficiencyQueue = new PriorityQueue<>(Comparator.comparingInt(v -> -efficiency[v]));
        PriorityQueue<Integer> speedQueue = new PriorityQueue<>(Comparator.comparingInt(v -> speed[v]));
        for (int i = 0; i < n; i++) {
            efficiencyQueue.add(i);
        }
        long sum = 0;
        long result = 0;
        while (!efficiencyQueue.isEmpty()) {
            int i = efficiencyQueue.poll();
            if (speedQueue.size() < k) {
                speedQueue.add(i);
                sum += speed[i];
                result = Math.max(sum * efficiency[i], result);//没有要求一定是k个, 最多k个
            } else {
                if (speed[i] > speed[speedQueue.peek()]) {  //由于后面的效率变低, 基本都是全值
                    //后面的效率一定比当前低. 就需要想办法总和变大
                    sum += speed[i] - speed[speedQueue.poll()];
                    speedQueue.add(i);
                    if (sum * efficiency[i] >= result) {
                        result = sum * efficiency[i];
                    }
                }
            }
        }
        return (int) (result % mod);
    }

    public static void main(String[] args) {
        System.out.println(solution(5, new int[]{5, 9, 3, 1, 7}, new int[]{6, 3, 7, 4, 9}, 2) == 72);
        System.out.println(solution(6, new int[]{7, 4, 6, 8, 3, 9}, new int[]{5, 9, 3, 8, 7, 2}, 3) == 105);
        System.out.println(solution(7, new int[]{4, 5, 6, 8, 2, 9, 3}, new int[]{8, 7, 4, 9, 6, 3, 5}, 4) == 119);
        System.out.println(solution(4, new int[]{2, 8, 5, 7}, new int[]{5, 3, 6, 9}, 2) == 72);
    }
}
