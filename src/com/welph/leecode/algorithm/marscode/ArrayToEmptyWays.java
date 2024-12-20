package com.welph.leecode.algorithm.marscode;

import java.util.Arrays;

/**
 * 小C得到了一个数组，他要对这个数组进行一些操作，直到数组为空。每次操作时，他会执行以下步骤：
 *
 * 如果数组的第一个元素 a[0] 等于0，则直接删除 a[0]，并将数组中剩余的所有元素向左移动以填补空缺。
 * 否则，将 a[0] 个 a[0]-1 添加到数组末尾，并将 a[0] 减少1。
 *
 * 小C想知道，在进行这些操作直到数组为空时，总共进行了多少次操作。结果需要对 10^9 +7 取模。
 */
public class ArrayToEmptyWays {

    public static int solution(int n, int[] a) {
        int mod = 1000000007;
        //对于一个数n=> f(n) = (n+1) f(n-1) + 1  拆分成n个及本身减1的1个.
        // f(n-1)= (n)f(n-2) +1
        Arrays.sort(a);
        long result = 0;
        long C = 1;
        int preValue = 1;
        for (int i = 0; i < n; i++) {
            int val = a[i] + 1;
            if (preValue != val) {
                //从2开始.到 a[i] + 1
                for (int j = preValue + 1; j <= val; j++) {
                    C = (C * j + 1) % mod;
                }
                preValue = val;
            }
            result = (result + C) % mod;
        }
        return (int) result;
    }

    public static void main(String[] args) {
        System.out.println(solution(3, new int[]{2, 3, 4}) == 257);
        System.out.println(solution(5, new int[]{1, 1, 1, 1, 1}) == 15);
        System.out.println(solution(4, new int[]{10, 5, 3, 7}) == 68658871);
    }
}
