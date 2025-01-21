package com.welph.leecode.algorithm.marscode.middle;

import java.util.*;

/**
 * 小F计划从青海湖出发,前往一个遥远的景点X进行旅游。景点X可能是"敦煌"或"月牙泉",线路的路径是唯一的。
 * 由于油价的不断上涨,小F希望尽量减少行程中的燃油成本。
 * <p>
 * 车辆的油箱容量为400L,在起始点租车时,车内剩余油量为200L。每行驶1km消耗1L油。
 * 沿途设有多个加油站,小F可以在这些加油站补充燃油;此外,到达目标景点X还车的时候,需要保证车内剩余的油至少有200L。
 * <p>
 * 小F需要你帮助他计算,如果合理规划加油站的加油顺序和数量,最小化从青海湖到景点X的旅行成本(元)。
 * <p>
 * 输入:
 * distance:从青海湖到景点X的总距离(km),距离最远不超过10000 km。
 * n:沿途加油站的数量(1<=n<=100)
 * gas_stations:每个加油站的信息,包含两个非负整数[加油站距离起始点的距离(km),该加油站的油价(元/L)]
 * <p>
 * 输出:
 * 最小化从青海湖到景点X的旅行成本(元)。如果无法到达景点X,或者到达景点X还车时油料剩余不足200L,则需要返回-1告诉小F这是不可能的任务。
 */
public class MinOilCost {
    /**
     * {@link com.welph.leecode.part_1_500.part_121_140.Solution134}
     * 类似, 但这道题有限制, 难一点
     */
    public static int solution(int distance, int n, List<List<Integer>> gas_stations) {
        if (gas_stations.isEmpty()) {
            return -1;
        }
        /*if (gas_stations.stream().anyMatch(v -> v.size() != 2)) {
            return -1;
        }*/
        List<List<Integer>> new_gas_stations = new ArrayList<>(gas_stations);
        new_gas_stations.sort(Comparator.comparingInt(v -> -v.get(0)));
        new_gas_stations.add(Arrays.asList(-200, Integer.MAX_VALUE));
        int current = 200;
        int totalCost = 0;
        int currentPosition = distance;
        int totalFuelAdded = 200;
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                Comparator.comparingInt(value -> new_gas_stations.get(value).get(1)));
        int maxFill = -1;
        for (int i = 0; i < new_gas_stations.size(); i++) {
            List<Integer> integers = new_gas_stations.get(i);
            Integer position = integers.get(0);
            int cost = currentPosition - position;// 花费的汽油
            // 若是无法到达下一站, 就想办法加油
            while (current < cost) {
                if (queue.isEmpty()) {
                    return -1;
                }
                Integer peek = queue.peek(); //找到最便宜的油
                if (maxFill >= peek) { //如果已经过了 则找maxFill后面的油去加
                    queue.poll();
                    continue;
                }
                int fuelNeeded = cost - current;
                int cap = 400 - (new_gas_stations.get(peek).get(0) - distance + totalFuelAdded); //需要加油的量
                int c = Math.min(fuelNeeded, cap);
                if (c == cap) { // 加够了
                    queue.poll();
                    maxFill = peek;
                }
                totalFuelAdded += c; // 总加油量
                totalCost += c * new_gas_stations.get(peek).get(1);
                current += c;
            }

            current -= cost;
            currentPosition = position;
            queue.add(i);

        }
        return totalCost;
    }

    public static void main(String[] args) {
        List<List<Integer>> gasStations1 = new ArrayList<>();
        gasStations1.add(Arrays.asList(100, 1));
        gasStations1.add(Arrays.asList(200, 30));
        gasStations1.add(Arrays.asList(400, 40));
        gasStations1.add(Arrays.asList(300, 20));

        List<List<Integer>> gasStations2 = new ArrayList<>();
        gasStations2.add(Arrays.asList(100, 999));
        gasStations2.add(Arrays.asList(150, 888));
        gasStations2.add(Arrays.asList(200, 777));
        gasStations2.add(Arrays.asList(300, 999));
        gasStations2.add(Arrays.asList(400, 1009));
        gasStations2.add(Arrays.asList(450, 1019));
        gasStations2.add(Arrays.asList(500, 1399));

        List<List<Integer>> gasStations3 = new ArrayList<>();
        gasStations3.add(Arrays.asList(101));
        gasStations3.add(Arrays.asList(100, 100));
        gasStations3.add(Arrays.asList(102, 1));

        List<List<Integer>> gasStations4 = new ArrayList<>();
        gasStations4.add(Arrays.asList(34, 1));
        gasStations4.add(Arrays.asList(105, 9));
        gasStations4.add(Arrays.asList(9, 10));
        gasStations4.add(Arrays.asList(134, 66));
        gasStations4.add(Arrays.asList(215, 90));
        gasStations4.add(Arrays.asList(999, 1999));
        gasStations4.add(Arrays.asList(49, 0));
        gasStations4.add(Arrays.asList(10, 1999));
        gasStations4.add(Arrays.asList(200, 2));
        gasStations4.add(Arrays.asList(300, 500));
        gasStations4.add(Arrays.asList(12, 34));
        gasStations4.add(Arrays.asList(1, 23));
        gasStations4.add(Arrays.asList(46, 20));
        gasStations4.add(Arrays.asList(80, 12));
        gasStations4.add(Arrays.asList(1, 1999));
        gasStations4.add(Arrays.asList(90, 33));
        gasStations4.add(Arrays.asList(101, 23));
        gasStations4.add(Arrays.asList(34, 88));
        gasStations4.add(Arrays.asList(103, 0));
        gasStations4.add(Arrays.asList(1, 1));

        System.out.println(solution(500, 4, gasStations1) == 4300);
        System.out.println(solution(500, 7, gasStations2) == 410700);
        System.out.println(solution(500, 3, gasStations3) == -1);
        System.out.println(solution(100, 20, gasStations4) == 0);
        System.out.println(solution(100, 0, new ArrayList<>()) == -1);


        List<List<Integer>> gasStations5 = new ArrayList<>();
        gasStations4.add(Arrays.asList(100, 1));
        gasStations4.add(Arrays.asList(200, 30));
        gasStations4.add(Arrays.asList(400, 40));
        gasStations4.add(Arrays.asList(300, 20));
        System.out.println(solution(500, 4, gasStations5));

        System.out.println(solution(1000, 3, change(new int[][]{{300, 25}, {600, 35}, {900, 5}})));
        System.out.println(solution(200, 2, change(new int[][]{{100, 50}, {150, 45}})));
    }

    private static List<List<Integer>> change(int[][] arr) {
        List<List<Integer>> gasStation = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            List<Integer> s = new ArrayList<>();
            for (int v : arr[i]) {
                s.add(v);
            }
            gasStation.add(s);
        }
        return gasStation;
    }
}
