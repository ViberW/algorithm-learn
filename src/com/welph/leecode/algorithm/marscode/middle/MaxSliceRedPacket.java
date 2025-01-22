package com.welph.leecode.algorithm.marscode.middle;

import java.util.ArrayList;
import java.util.List;

/**
 * 小U在公司年会上运气极佳，赢得了一等奖。
 * 作为一等奖得主，他有机会在一排红包中做两次切割，将红包分成三部分，
 * 要求第一部分和第三部分的红包总金额相等。他可以获得的金额是第一部分红包的总金额。
 *
 * 帮小U计算出他能从这些红包中拿到的最大奖金金额。
 */
public class MaxSliceRedPacket {

    public static int solution(List<Integer> redpacks) {
        int l = 0, r = redpacks.size() - 1;
        int suml = redpacks.get(0), sumr = redpacks.get(r);
        int max = 0;
        while (l < r) {
            if (suml == sumr) {
                max = suml;
                l++;
                r--;
                sumr += redpacks.get(r);
                suml += redpacks.get(l);
            } else if (suml > sumr) {
                r--;
                sumr += redpacks.get(r);
            } else {
                l++;
                suml += redpacks.get(l);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // You can add more test cases here
        List<Integer> redpacks1 = new ArrayList<>();
        redpacks1.add(1);
        redpacks1.add(3);
        redpacks1.add(4);
        redpacks1.add(6);
        redpacks1.add(7);
        redpacks1.add(14);

        List<Integer> redpacks2 = new ArrayList<>();
        redpacks2.add(10000);

        List<Integer> redpacks3 = new ArrayList<>();
        redpacks3.add(52);
        redpacks3.add(13);
        redpacks3.add(61);
        redpacks3.add(64);
        redpacks3.add(42);
        redpacks3.add(26);
        redpacks3.add(4);
        redpacks3.add(27);
        redpacks3.add(25);

        List<Integer> redpacks4 = new ArrayList<>();
        redpacks4.add(2);
        redpacks4.add(5);
        redpacks4.add(50);
        redpacks4.add(30);
        redpacks4.add(60);
        redpacks4.add(52);
        redpacks4.add(26);
        redpacks4.add(5);
        redpacks4.add(74);
        redpacks4.add(83);
        redpacks4.add(34);
        redpacks4.add(96);
        redpacks4.add(6);
        redpacks4.add(88);
        redpacks4.add(94);
        redpacks4.add(80);
        redpacks4.add(64);
        redpacks4.add(22);
        redpacks4.add(97);
        redpacks4.add(47);
        redpacks4.add(46);
        redpacks4.add(25);
        redpacks4.add(24);
        redpacks4.add(43);
        redpacks4.add(76);
        redpacks4.add(24);
        redpacks4.add(2);
        redpacks4.add(42);
        redpacks4.add(51);
        redpacks4.add(96);
        redpacks4.add(97);
        redpacks4.add(87);
        redpacks4.add(47);
        redpacks4.add(93);
        redpacks4.add(11);
        redpacks4.add(98);
        redpacks4.add(41);
        redpacks4.add(54);
        redpacks4.add(18);
        redpacks4.add(16);
        redpacks4.add(11);
        redpacks4.add(96);
        redpacks4.add(34);
        redpacks4.add(36);
        redpacks4.add(87);
        redpacks4.add(24);
        redpacks4.add(32);
        redpacks4.add(27);
        redpacks4.add(62);
        redpacks4.add(72);
        redpacks4.add(54);
        redpacks4.add(14);
        redpacks4.add(67);
        redpacks4.add(5);
        redpacks4.add(21);
        redpacks4.add(20);
        redpacks4.add(44);
        redpacks4.add(55);
        redpacks4.add(3);
        redpacks4.add(82);
        redpacks4.add(19);
        redpacks4.add(45);
        redpacks4.add(1);
        redpacks4.add(52);
        redpacks4.add(14);
        redpacks4.add(44);
        redpacks4.add(46);
        redpacks4.add(39);
        redpacks4.add(83);
        redpacks4.add(27);
        redpacks4.add(30);
        redpacks4.add(87);
        redpacks4.add(61);
        redpacks4.add(56);
        redpacks4.add(59);
        redpacks4.add(10);
        redpacks4.add(83);
        redpacks4.add(80);
        redpacks4.add(42);
        redpacks4.add(44);
        redpacks4.add(75);
        redpacks4.add(39);
        redpacks4.add(43);
        redpacks4.add(41);
        redpacks4.add(23);
        redpacks4.add(93);
        redpacks4.add(73);
        redpacks4.add(50);
        redpacks4.add(94);
        redpacks4.add(94);
        redpacks4.add(82);
        redpacks4.add(46);
        redpacks4.add(87);
        redpacks4.add(60);
        redpacks4.add(94);
        redpacks4.add(47);
        redpacks4.add(52);
        redpacks4.add(67);
        redpacks4.add(22);
        redpacks4.add(50);
        redpacks4.add(49);
        redpacks4.add(8);
        redpacks4.add(9);
        redpacks4.add(30);
        redpacks4.add(62);
        redpacks4.add(87);
        redpacks4.add(13);
        redpacks4.add(11);

        System.out.println(solution(redpacks1) == 14);
        System.out.println(solution(redpacks2) == 0);
        System.out.println(solution(redpacks3) == 52);
        System.out.println(solution(redpacks4) == 2627);
    }
}
