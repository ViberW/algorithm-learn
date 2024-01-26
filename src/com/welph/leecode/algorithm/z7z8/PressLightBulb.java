package com.welph.leecode.algorithm.z7z8;

import java.util.Arrays;
import java.util.Random;

/**
 * 一个圆环上有 100 个灯泡，灯泡有亮和暗两种状态。按一个灯泡的开关可以改变它和与它相邻两个灯泡的状态。
 * 设计一种算法，对于任意初始状态，使所有灯泡全亮。
 * <p>
 * <p>
 * 对于 N 个灯泡的任意初始状态 ( N > 3 ) ，能否经过若干次操作使得所有灯泡全亮？
 * 答案：N 个灯泡做分类讨论。
 * N = 3*k+1一定可以。方法与上述步骤相同，在步骤二中可以将3k个亮的灯泡分为k组。
 * N = 3*k+2一定可以。将上述步骤一目标状态的只剩一个为暗改成剩两个相邻为暗，其余 3 * k 个灯泡分组按即可。 
 *     因为，对于任意只剩一个为暗的状态，按下该灯泡左右任意一个就可以变成剩两个相邻为暗的状态！
 *     对于最后 [1, 0, 1, 0 , 1] 可以先按第3个灯泡=>[1,1,0,1,1], 再按第二个灯泡=>[0,0,1,1,1] 保证有两个相邻的暗
 * N = 3*k不一定。如果经过上述步骤一可以将灯泡变成全亮的状态则有解；否则，无解。（该结论有待证明）
 */
public class PressLightBulb {

    static Random random = new Random();

    public static void main(String[] args) {
        // 1代表亮 0代表暗
        int[] lights = new int[100];
        for (int i = 0; i < lights.length; i++) {
            lights[i] = random.nextInt(2);
        }
        System.out.println(Arrays.toString(lights));
        light(lights);
        System.out.println(Arrays.toString(lights));
    }

    /**
     * 1. 循环从1到98, 遇到暗, 则[按下一个] 保证了从1-98全亮
     * 此时有不同情况: 99和100 有 11, 01 10 00
     * ....1) 11 则成功 --退出
     * ....2) 10, 01 达到了仅剩一个暗的
     * ....3) 00 则按下100号灯泡, 则99,100为亮, 1号灯泡为暗, 仅剩一个暗
     * 2. 使得所有灯泡变暗
     * 将上一步暗的单独出来, 其他的三个一组, 取中间的按下.
     * 3. 所有灯泡按下, 因为每个灯泡被操作了三次, 一定由暗到亮
     */
    public static void light(int[] lights) {
        int len = lights.length;
        for (int i = 1; i < len - 1; i++) {
            if (lights[i - 1] == 0) {
                press(lights, i);
            }
        }
        int target;
        if (lights[len - 1] == 1 && lights[len - 2] == 1) {
            return;
        } else if (lights[len - 1] == 0) {
            target = 1;
        } else if (lights[len - 2] == 0) {
            target = 0;
        } else {
            press(lights, len - 1);
            target = 2;
        }
        // 剩下的3个一组
        for (int i = target; i < len - 1; i += 3) {
            press(lights, i);
        }
        for (int i = 0; i < len; i++) {
            press(lights, i);
        }
    }

    public static void press(int[] lights, int i) {
        lights[i] ^= 1;
        if (i == lights.length - 1) {
            lights[i - 1] ^= 1;
            lights[0] ^= 1;
        } else if (i == 0) {
            lights[lights.length - 1] ^= 1;
            lights[1] ^= 1;
        } else {
            lights[i - 1] ^= 1;
            lights[i + 1] ^= 1;
        }
    }
}
