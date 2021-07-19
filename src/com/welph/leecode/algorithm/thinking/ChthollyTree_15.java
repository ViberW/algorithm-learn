package com.welph.leecode.algorithm.thinking;

import java.util.TreeSet;

/**
 * @author Viber
 * @version 1.0
 * @apiNote 珂朵莉树
 * 实现较快的: 区间加, 区间赋值, 求区间的第K最大值, 区间的N次方和
 * <p>
 * --适用于有区间赋值操作且数据随机的场景
 * @since 2021/7/6 16:02
 */
public class ChthollyTree_15 {


    /**
     * 使用了TreeSet保存数据,
     * 每个节点保存{l,r,v}. 分别代表左边界, 右边界, 以及值
     * >   1 ,       2,2,2 , 3,3    , 4
     * =>  {1,1,1}, {2,4,2}, {5,6,3}, {7,7,4}
     * 上面的数据格式存储;  --- 注意, 这里不是有序也是允许的
     */
    public static void main(String[] args) {
        TreeSet<Node> tree = new TreeSet<>();
        tree.add(new Node(1, 1, 1));
        tree.add(new Node(2, 4, 2));
        tree.add(new Node(5, 6, 3));
        tree.add(new Node(7, 7, 4));

        System.out.println(tree);
        assign(tree, 4, 5, 7); //{4,5}范围赋值为7
        System.out.println(tree);
    }
    /**
     * 区间加: 同样通过split() 切分,之后遍历加操作
     * 区间的第K最大值: 同样通过split() 切分,之后遍历范围内的节点放入到小顶堆中.
     * 区间的N次方和: 同样通过split() 切分,之后遍历求平方和
     */

    /**
     * 区间赋值
     */
    private static void assign(TreeSet<Node> tree, int l, int r, int v) {
        //todo 一定要先切分尾节点
        Node end = spilit(tree, r + 1); //+1为了r单独到一个node中
        Node begin = spilit(tree, l);
        //清除begin~end之前的节点  => 这是区间赋值, 若是区间加的话就不用祛除了
        tree.removeIf(node -> node.l >= begin.l && node.l < end.l); //右边不用等号
        tree.add(new Node(l, r, v));
    }

    /**
     * todo 关键点: 进行区间赋值和区间加时, 需要将原本包含在范围内的Node拆分为多个
     * 如:  {2,4,2},{5,6,3}, 在对区间{4,5}进行加1操作时
     * 拆分为: {2,3,2},{4,4,2},{5,5,3},{6,6,3}
     * 方便之后对{4,4,2},{5,5,3} 进行[加1操作]
     */
    private static Node spilit(TreeSet<Node> tree, int pos) {//按照pos进行切分
        //向前找到一个大于或等于pos的Node
        Node last = null;
        for (Node node : tree) {
            if (node.l > pos) {
                break;
            } else if (node.l == pos) {
                return node;
            }
            last = node;
        }
        assert last != null;
        //对last进行拆分
        tree.remove(last);
        tree.add(new Node(last.l, pos, last.v));
        Node node = new Node(pos, last.r, last.v);
        tree.add(node);
        return node;
    }


    static class Node implements Comparable<Node> {
        int l;
        int r;
        int v;

        public Node(int l, int r, int v) {
            this.l = l;
            this.r = r;
            this.v = v;
        }

        @Override
        public int compareTo(Node o) {
            //按照l进行升序
            return Integer.compare(l, o.l);
        }

        @Override
        public String toString() {
            return "{" + l + "~" + r + "}" + "=" + v;
        }
    }
}
