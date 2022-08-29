package com.welph.leecode.part_1_500.part_461_480;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

/**
 * 给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。
 * 实现Solution类:
 * Solution(double radius, double x_center, double y_center)用圆的半径radius和圆心的位置 (x_center, y_center) 初始化对象
 * randPoint()返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
 * <p>
 * 示例 1：
 * 输入:
 * ["Solution","randPoint","randPoint","randPoint"]
 * [[1.0, 0.0, 0.0], [], [], []]
 * 输出: [null, [-0.02493, -0.38077], [0.82314, 0.38945], [0.36572, 0.17248]]
 * 解释:
 * Solution solution = new Solution(1.0, 0.0, 0.0);
 * solution.randPoint ();//返回[-0.02493，-0.38077]
 * solution.randPoint ();//返回[0.82314,0.38945]
 * solution.randPoint ();//返回[0.36572,0.17248]
 * <p>
 * 提示：
 * 0 <radius <= 10^8
 * -107<= x_center, y_center <= 10^7
 * randPoint 最多被调用3 * 10^4次
 */
public class Solution478 {

    public static void main(String[] args) {
        Solution obj = new Solution(1.0, 0.0, 0.0);
        System.out.println(Arrays.toString(obj.randPoint()));
        System.out.println(Arrays.toString(obj.randPoint()));
        System.out.println(Arrays.toString(obj.randPoint()));
        System.out.println(Math.pow(0.5488132455281949, 2) + Math.pow(0.5421353582728239, 2));
    }

    static class Solution {
        static Random random = new Random();
        double x_center;
        double y_center;
        double radius;
        double radiusPower;
        double radius2;

        public Solution(double radius, double x_center, double y_center) {
            //上下左右的xy范围
            this.x_center = x_center;
            this.y_center = y_center;
            this.radius = radius;
            radiusPower = radius * radius;
            radius2 = radius * 2;
        }

        //这里需要保证 半径的平方 再取随机数, 之后开根号,  这样才能保证数据均匀(面积=π*r^2, 所以数据需要r平方)
        public double[] randPoint() {
            double u = random.nextDouble();
            double theta = random.nextDouble() * 2 * Math.PI;
            double r = Math.sqrt(u);
            return new double[]{x_center + r * Math.cos(theta) * this.radius, y_center + r * Math.sin(theta) * this.radius};
        }

        public double[] randPoint1() {
            while (true) {
                double x = random.nextDouble() * (radius2) - radius;
                double y = random.nextDouble() * (radius2) - radius;
                if (x * x + y * y <= radiusPower) {
                    return new double[]{x_center + x, y_center + y};
                }
            }
        }
    }

}
