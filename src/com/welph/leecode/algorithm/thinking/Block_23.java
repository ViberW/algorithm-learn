package com.welph.leecode.algorithm.thinking;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [分块]
 * 对数据按照长度N切分为( 根号N ) 个block块
 * -- 相较于树状数组和线段树, 较为灵活
 * @since 2021/7/7 9:35
 */
public class Block_23 {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = arr.length;
        int sqrt = (int) Math.sqrt(n);
        /**
         * 构建每个block块的起始和结束下标
         */
        int[] st = new int[sqrt + 1];
        int[] et = new int[sqrt + 1];
        for (int i = 0; i <= sqrt; i++) {
            st[i] = n / sqrt * i;// st[i]表示i号块的第一个元素的下标
            et[i] = n / sqrt * (i + 1) - 1; // ed[i]表示i号块的最后一个元素的下标
        }
        et[sqrt] = n - 1; //限制最后的下标为n
        //确定每个block块的归属
        int[] bel = new int[n];
        for (int i = 0; i <= sqrt; i++) {
            for (int j = st[i]; j <= et[i]; j++) {
                bel[j] = i;//表示下标为j的元素属于block块i
            }
        }
        //预处理block块的长度
        int[] size = new int[sqrt + 1];
        for (int i = 0; i <= sqrt; i++) {
            size[i] = et[i] - st[i] + 1;
        }
        //////////////////////////////////////

        //对区间加值,可以使用类似线段树那样使用mark[]数组标志需要加K值
    }
}
