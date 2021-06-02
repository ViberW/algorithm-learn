package com.welph.leecode.algorithm.z7z8;

/**
 * 康拓编码
 * --- 一种类似全排列的编码和解码方式
 */
public class CantorCode {

    public static void main(String[] args) {

    }

    //全排列的0!, 1! ... 9!
    int FAC[] = {1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880};

    public int encode(int a[], int n) {
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int lessThan = 0;
            for (int j = i + 1; j < n; ++j) {
                if (a[j] < a[i]) lessThan++;
            }
            ans += lessThan * FAC[n - i - 1];
        }
        return ans;
    }

    public void decode(int ans[], int x, int n) {
        boolean[] visit = new boolean[9];
        for (int i = 0; i < n; ++i) {
            ans[i] = x / FAC[n - i - 1];
            int j, order = 0;
            for (j = 0; j < n; ++j) {
                if (!visit[j]) {
                    if (ans[i] == order) break;
                    order++;
                }
            }
            ans[i] = j + 1;
            visit[j] = true;
            x %= FAC[n - i - 1];
        }
    }
}
