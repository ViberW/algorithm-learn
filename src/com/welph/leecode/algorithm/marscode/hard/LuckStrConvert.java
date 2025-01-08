package com.welph.leecode.algorithm.marscode.hard;

/**
 * 小M拥有一个长度为n的数组 a，由于他非常喜欢数字 w，他希望将所有数组中的数都变为 w。
 * <p>
 * 小M每次操作可以选择一个区间 [l, r]，并将该区间内的所有数字都加 1（包括左右边界 l 和 r）。
 * 但为了让挑战更具趣味性，小M要求每次操作的l均不相同，r也均不相同。
 * <p>
 * 小M现在想知道，有多少种不同的操作方案可以让数组中的所有数都变为 w。注意，如果所有操作的区间相同，则视为同一种操作方式，操作顺序不同并不会形成新的方案。
 */
public class LuckStrConvert {
    public static int solution(int n, int w, int[] array) {
        int[] diff = new int[n];
        int delta = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = w - array[i];
            if (diff[i] < 0) {
                return 0; // 如果某个元素已经大于 w，直接返回 0
            }
            delta += diff[i];
        }
        boolean[] select = new boolean[n];
        return solution(n, w, diff, 0, select, delta);
    }

    public static int solution(int n, int w, int[] diff, int l,
                               boolean[] select, int delta) {
        if (l == n) {
            return delta == 0 ? 1 : 0;
        }
        //不选择
        if (diff[l] == 0) {
            return solution(n, w, diff, l + 1, select, delta);
        }
        int total = 0;
        int r = l;
        for (; r < n; r++) {
            if (diff[r] == 0) {
                break;
            }
            diff[r]--;
            delta--;
            if (select[r]) {
                continue;
            }
            select[r] = true;
            total += solution(n, w, diff, l + 1, select, delta);
            select[r] = false;
        }
        for (int i = l; i < r; i++) {
            diff[i]++;
            delta++;
        }
        return total;
    }

    public static void main(String[] args) {
        // Add your test cases here

        System.out.println(solution(2, 2, new int[]{1, 1}) == 2);
        System.out.println(solution(3, 3, new int[]{1, 2, 3}) == 0);
    }
}
