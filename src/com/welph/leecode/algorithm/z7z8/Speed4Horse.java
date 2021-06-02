package com.welph.leecode.algorithm.z7z8;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 64匹马，8赛道，找出最快的4匹最少要几次?
 */
public class Speed4Horse {
    static Random random = new Random();

    static Set<Integer> exists = new HashSet<>();

    public static void main(String[] args) {
        Horse[] horses = new Horse[64];
        for (int i = 0; i < horses.length; i++) {
            int speed = random.nextInt(1000);
            while (exists.contains(speed)) {
                speed = random.nextInt(1000);
            }
            exists.add(speed);
            horses[i] = new Horse(i + 1, speed);
        }
        //找出最快的4匹马
        List<Horse> collect = Arrays.stream(horses).sorted(Comparator.comparingInt(value -> ((Horse) value).speed).reversed())
                .limit(4).collect(Collectors.toList());
        for (Horse horse : collect) {
            System.out.println(horse);
        }
        System.out.println("=========================");
        Horse[] quick = search4Horse(horses);
        for (Horse horse : quick) {
            System.out.println(horse);
        }
    }

    /**
     * 1. 先每8匹马跑, 获得8组速度排序的马
     * 2. 由于找出4匹马, 则每一组的后4匹一定不是答案
     * 2. 由于找出4匹马, 则按照每组第一名排序,则后4组一定不是答案
     * 3. 最终按照第一名速度排序的4组中:
     * >   第一组4名全有机会;
     * >   第二组前3名有机会;
     * >   第三组前2名有机会;
     * >   第一组前1名有机会;
     * 4. 由3可知, 第一组的第一名一定是答案,则不考虑
     * 5. 此时还有9匹马未得到答案, 还需选出3名---------
     * 6. 除第一组的第四名(FOUR)去除, 跑剩余的8匹马, 则能够获得到排序
     * >   若第一组的第三名(FOUR的上一名)位于排序的第三及后面名次,则答案有了
     * >   否则,将排序的前三名同FOUR,再跑一次, 能获得到三名答案
     */
    private static Horse[] search4Horse(Horse[] horses) {
        //1
        Horse[] quick = new Horse[4];
        Horse[][] groups = new Horse[8][8];
        int gs = 0;
        for (int i = 0; i < horses.length; i += 8) {
            groups[gs++] = quick(Arrays.copyOfRange(horses, i, i + 8));
        }
        //2
        Horse[] group = new Horse[8];
        for (int i = 0; i < groups.length; i++) {
            group[i] = groups[i][0];
            group[i].group = i;
        }
        quick(group);
        //3
        quick[0] = group[0];
        Horse[] reCheck = new Horse[8];
        int j = 0;
        for (int i = 0; i < 4; i++) {
            Horse[] g = groups[group[i].group];
            for (int m = 0; m < 4 - i; m++) {
                if (i == 0 && (m == 3 || m == 0)) {
                    continue;
                }
                reCheck[j] = g[m];
                if (i == 0 && m == 2) {
                    reCheck[j].third = true;
                }
                j++;
            }
        }
        quick(reCheck);
        boolean existThird = false;
        for (int i = 0; i < 2; i++) {
            if (reCheck[i].third) {
                existThird = true;
                break;
            }
        }
        if (existThird) {
            Horse FOUR = groups[group[0].group][3];
            Horse[] reFour = new Horse[4];
            for (int i = 0; i < 3; i++) {
                reFour[i] = reCheck[i];
            }
            reFour[3] = FOUR;
            reCheck = quick(reFour);
        }
        for (int i = 0; i < 3; i++) {
            quick[i + 1] = reCheck[i];
        }
        return quick;
    }


    private static Horse[] quick(Horse[] horses) {
        Arrays.sort(horses, Comparator.comparingInt(value -> ((Horse) value).speed).reversed());
        return horses;
    }

    static class Horse {
        int number;
        int speed;
        int group;
        boolean third;

        public Horse(int number, int speed) {
            this.number = number;
            this.speed = speed;
        }

        @Override
        public String toString() {
            return "Horse{" +
                    "number=" + number +
                    ", speed=" + speed +
                    '}';
        }
    }
}
