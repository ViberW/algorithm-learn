package com.welph.leecode.part_1_500.part_401_420;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。
 * 每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * <p>
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，
 * 其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * <p>
 * 示例 1：
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 * <p>
 * 示例 2：
 * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 * <p>
 * 提示：
 * 1 <= people.length <= 2000
 * 0 <= hi <= 106
 * 0 <= ki < people.length
 * 题目数据确保队列可以被重建
 */
public class Solution406 {

    public static void main(String[] args) {
        int[][] ints = reconstructQueue(new int[][] { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } });
        for (int[] anInt : ints) {
            System.out.println(Arrays.toString(anInt));
        }
    }

    // 官方题解
    public static int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]; // todo 这里相同身高,降序
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] i : people) {
            list.add(i[1], i);
        }
        return list.toArray(new int[list.size()][]);
    }

    /**
     * 思考: 作为最小身高且最小前大于值, 能够放置的位置就是对应的排序index
     * ---时间很长, 因为最大时间为O(N^2) ---这里按照身高降序 {@see reconstructQueue1()}
     */
    public static int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        int length = people.length;
        int[][] ret = new int[length][2];
        for (int i = 0; i < length; i++) {
            ret[i][0] = -1;
        }
        int[] person;
        int index;
        for (int i = 0; i < length; i++) {
            person = people[i];
            index = person[1];
            int actual = 0;
            // 相当于是找到person的位置
            for (int j = 0; j < index || ret[actual][0] != -1; actual++) {
                // 因为上面经过排序 前面的要么是小于person[0] 要么是等于person[0] 再或是还没设置, 但这些没设置的一定是大于或等于person[0]
                if (ret[actual][0] == -1 || ret[actual][0] == person[0]) {
                    j++;
                }
            }
            ret[actual] = person;
        }
        return ret;
    }

    /* 官方题解 */
    // 从低到高考虑
    public int[][] reconstructQueue2(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person1[0] - person2[0];// 顺序
                } else {
                    return person2[1] - person1[1];// 倒序-- 相较于我上面的方式, 就少了一步ret[actual][0] == person[0]的
                }
            }
        });
        int n = people.length;
        int[][] ans = new int[n][];
        for (int[] person : people) {
            int spaces = person[1];
            for (int i = 0; i < n; ++i) {
                if (ans[i] == null) {// 说明没人选
                    if (spaces == 0) {// 没人选且前面已经有合适的空位, 则可以放入此地
                        ans[i] = person;
                        break;
                    } else {
                        --spaces;
                    }
                }
            }
        }
        return ans;
    }

    // 从高到低考虑
    public int[][] reconstructQueue3(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];// 倒序
                } else {
                    return person1[1] - person2[1];// 顺序
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        // 因为后面的值不断比前面的值小, 所以后面的值可以按照person[1]想要的位置插入即可
        for (int[] person : people) {
            // 因为倒序, 所以前面的person[1]至少很小
            ans.add(person[1], person);// 选择对应的位置插入
        }
        return ans.toArray(new int[ans.size()][]);
    }

}
