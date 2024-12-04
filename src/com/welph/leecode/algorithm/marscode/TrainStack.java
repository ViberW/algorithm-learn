package com.welph.leecode.algorithm.marscode;

import java.util.Stack;

/**
 * 小F在观察火车驶入和驶出休息区的顺序时，注意到休息区的结构类似于栈，即遵循先进后出的规则。
 * 她记录了火车驶入和驶出的顺序，并希望验证这些顺序是否可能实际发生。
 * 火车在进入休息区后可以按顺序驶入或停留，然后根据休息区的规则依次驶出。
 * 你的任务是帮助小F验证所记录的火车驶入和驶出顺序是否能够被满足。
 *
 * 例如：如果火车的驶入顺序是 1 2 3，驶出顺序是 3 2 1，这是可能的；如果驶出顺序是 3 1 2，则是不可能的。
 */
public class TrainStack {

    public static boolean solution(int n, int[] a, int[] b) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < n; i++) {
            stack.push(a[i]);
            while (!stack.isEmpty() && stack.peek() == b[j]) {
                stack.pop();
                j++;
            }
        }
        return j == n; //说明栈顶没有符合的
    }

    public static void main(String[] args) {
        //一个入栈,再出栈
        System.out.println(solution(3, new int[]{1, 2, 3}, new int[]{1, 2, 3}) == true);
        //一次性入栈,  一次性出栈
        System.out.println(solution(3, new int[]{1, 2, 3}, new int[]{3, 2, 1}) == true);
        System.out.println(solution(3, new int[]{1, 2, 3}, new int[]{3, 1, 2}) == false);
    }
}
