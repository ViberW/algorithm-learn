package com.welph.leecode.algorithm.thinking;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [种类并查集]
 * <p>
 * -- 并查集的拓展, [敌人的敌人是朋友]
 * @since 2021/6/11 9:29
 */
public class CheckCollectType_7 {

    static Random random = new Random();

    public static void main(String[] args) {
        //prisoner();
        animal();
    }

    /**
     * A,B,C 三个动物, 共N个
     * 构成A吃B, B吃C, C吃A
     * 存在两种关系:
     * 1. "1 X Y" 说明X和Y是同类
     * 2. "2 X Y" 说明X吃Y
     * 一共K个关系, 其中有res条为假话
     * ----------------------
     * 根据关系有: 定义X吃X+N, X+N吃X+2N, X+2N吃X  ,分成3组,每组一个并查集合
     * 1. X是Y的同类, 则 X+N和Y+N是同类, X+2N和Y+2N是同类
     * 2. X吃Y, 则 X+N和Y是同类,(X+N吃Y+N => X+2N和Y+N是同类),(X+2N吃Y+2N => X和Y+2N是同类)
     * 3. X被Y吃, 则 X和Y+N是同类, X+N和Y+2N是同类, X+2N和Y是同类
     */
    public static void animal() {
        int n = 5;
        int[] rank = new int[3 * n + 1];
        int[] fa = new int[3 * n + 1];
        for (int i = 1; i <= 3 * n; ++i) {
            rank[i] = 1;
            fa[i] = i;
        }
        int m = 5;
        int[][] talk = new int[m][3];
        talk[0] = new int[]{1, 1, 2};
        talk[1] = new int[]{2, 5, 1};
        talk[2] = new int[]{2, 3, 2};
        talk[3] = new int[]{1, 1, 5};
        talk[4] = new int[]{1, 2, 2};
        //初始化条件完毕, 处理
        int lie = 0;//假话
        int[] t;
        for (int i = 0; i < m; i++) {
            t = talk[i];
            if (t[1] > n || t[2] > n || t[1] == t[2]) {//错误的编号,或者相同
                lie++;
            } else if (t[0] == 1) {
                //如果已知x吃y，或者x被y吃，说明这是假话
                if (query(t[1], t[2] + n, fa)
                        || query(t[1], t[2] + 2 * n, fa)) {
                    lie++;
                } else {
                    merge(t[1], t[2], rank, fa);                 //这是真话，则x和y是一族
                    merge(t[1] + n, t[2] + n, rank, fa);         //x的猎物和y的猎物是一族
                    merge(t[1] + 2 * n, t[2] + 2 * n, rank, fa); //x的天敌和y的天敌是一族
                }
            } else if (t[0] == 2) {
                //如果已知x与y是一族，或者x被y吃，说明这是假话
                if (query(t[1], t[2], fa)
                        || query(t[1], t[2] + 2 * n, fa)) {
                    lie++;
                } else {
                    merge(t[1], t[2] + n, rank, fa);                 //这是真话，则x吃y
                    merge(t[1] + n, t[2] + 2 * n, rank, fa);         //x的猎物吃y的猎物
                    merge(t[1] + 2 * n, t[2], rank, fa); //x的天敌吃y的天敌，或者说y吃x的天敌
                }
            }
        }
        System.out.println("lie:" + lie);
    }

    /**
     * 两个监狱, 关押N个犯人
     * 有M对犯人有冲突关系
     */
    public static void prisoner() {
        int n = 5;
        //初始化并查集存储
        int[] rank = new int[2 * n + 1];
        int[] fa = new int[2 * n + 1];
        for (int i = 1; i <= 2 * n; ++i) {
            rank[i] = 1;
            fa[i] = i;
        }

        Relation[] relations = new Relation[5];
        relations[0] = new Relation(1, 3, 10); //有对立关系
        relations[1] = new Relation(5, 4, 3);//有对立关系
        relations[2] = new Relation(3, 1, 7);//有对立关系
        relations[3] = new Relation(1, 4, 2);//有对立关系
        relations[4] = new Relation(4, 3, 6);//有对立关系
        Arrays.sort(relations, (Comparator.comparingInt(value -> value.w)));

        for (int i = 0; i < relations.length; i++) {
            if (query(relations[i].a, relations[i].b, fa)) { //试图把[朋友关系]的a和b标记为[敌人]
                //还是找到了root, 说明冲突无法避免了 --已经是最小冲突了
                System.out.println(relations[i].w); //存在对立关系时关系值最少的情况
                break;
            }
            //把他们两个的对应X+n 保存起来 用于处理之后是否存在关联
            merge(relations[i].a, relations[i].b + n, rank, fa);
            merge(relations[i].b, relations[i].a + n, rank, fa);
        }
        System.out.println(Arrays.toString(rank));
        System.out.println(Arrays.toString(fa));
    }

    private static void merge(int p, int b, int[] rank, int[] fa) {
        int x = find(p, fa);
        int y = find(b, fa);
        if (rank[x] >= rank[y])
            fa[y] = x;
        else
            fa[x] = y;
        if (rank[x] == rank[y] && x != y)
            rank[x]++;
    }

    private static boolean query(int a, int b, int[] fa) {
        return find(a, fa) == find(b, fa);
    }

    //根据点p找到最终的关联点
    private static int find(int p, int[] fa) {
        return (fa[p] == p) ? p : (fa[p] = find(fa[p], fa));
    }

    static class Relation {
        int a;//点a
        int b;//点b
        int w;//对立关系值

        public Relation(int a, int b, int w) {
            this.a = a;
            this.b = b;
            this.w = w;
        }
    }
}
