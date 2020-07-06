package com.welph.leecode.algorithm.divide;

/**
 * 一组数据的有序对个数或者逆序对个数
 * 如 :  2,4,3,1,5,6
 * 逆序对: (2.1),(4,3),(4,1),(3,1)
 */
public class ReversePairCount {

    /**
     * 套用分治的思想来求数组 A 的逆序对个数。我们可以将数组分成前后两半 A1 和 A2，
     * 分别计算 A1 和 A2 的逆序对个数 K1 和 K2，然后再计算 A1 与 A2 之间的逆序对个数 K3。
     * 那数组 A 的逆序对个数就等于 K1+K2+K3。
     * <p>
     * --这里利用了归并排序帮助处理
     */
    public static void main(String[] args) {
        int[] arr = {2, 4, 3, 1, 5, 6};
        int count = new ReversePairCount().count(arr, arr.length);
        System.out.println(count);
    }

    private int num = 0; // 全局变量或者成员变量

    public int count(int[] a, int n) {
        num = 0;
        mergeSortCounting(a, 0, n - 1);
        return num;
    }

    private void mergeSortCounting(int[] a, int p, int r) {
        if (p >= r) return;
        int q = (p + r) / 2;
        mergeSortCounting(a, p, q);
        mergeSortCounting(a, q + 1, r);
        merge(a, p, q, r);
    }

    private void merge(int[] a, int p, int q, int r) {
        int i = p, j = q + 1, k = 0;
        int[] tmp = new int[r - p + 1];
        while (i <= q && j <= r) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                num += (q - i + 1); // 统计p-q之间，比a[j]大的元素个数
                tmp[k++] = a[j++];
            }
        }
        while (i <= q) {
            tmp[k++] = a[i++];
        }
        while (j <= r) {
            tmp[k++] = a[j++];
        }
        for (i = 0; i <= r - p; ++i) { // 从tmp拷贝回a
            a[p + i] = tmp[i];
        }
    }

}
