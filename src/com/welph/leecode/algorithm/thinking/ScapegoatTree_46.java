package com.welph.leecode.algorithm.thinking;

/**
 * @author Viber
 * @version 1.0
 * @apiNote [替罪羊树] --处理{二叉搜索树的平衡}的最简单的思路: 发现子树不平衡时,则重构树
 * >>>>重构: 左右子树相差过大时触发
 * 1. 先进行一遍中序遍历，把该子树"拉平"
 * 2. 把其中所有数存入一个数组里（BST的性质决定这个数组一定是有序的）
 * 3. 再用这些数据重新建一个平衡的二叉树，放回原位置
 * @since 2021/8/4 16:23
 */
public class ScapegoatTree_46 {

    public static void main(String[] args) {

    }

    static class ScapegoatTree extends BinarySearchTree_45.Tree {
        int[] FV = new int[100];
        int[] FN = new int[100]; // 分别存放拉平后的val[]和N[]

        /**
         * 一趟中序遍历，把当前子树拉平存入数组，返回数组长度
         */
        public int flatten(int pos, int[] fv, int[] fn, int sz) {
            // unempty记录当前节点是否已删除。若当前节点是已删除的节点，抛弃它
            if (L[pos] != 0) {
                sz = flatten(L[pos], fv, fn, sz); // 递归
            }
            if (N[pos] != 0) {
                fv[sz] = val[pos];
                fn[sz] = N[pos];
                sz++;
            }
            if (R[pos] != 0)
                sz += flatten(R[pos], fv, fn, sz);
            return sz;
        }

        /**
         * 以pos为根节点，根据数组FV、FN中下标[l,r]的数据重建二叉树
         */
        public void rebuild(int pos, int l, int r) {
            // 根节点取中间值，左右子树递归
            int mid = (l + r) / 2, sz = 0;
            if (l < mid) {
                L[pos] = ++cnt; // 新建节点---todo 这里会导致cnt增长很快 : 重用原本的节点编号int[] FP
                rebuild(L[pos], l, mid - 1);
                sz = size[L[pos]];
            } else {
                L[pos] = 0;
            }

            if (mid < r) {
                R[pos] = ++cnt;
                rebuild(R[pos], mid + 1, r);
                sz += size[R[pos]];
            } else {
                R[pos] = 0;
            }
            val[pos] = FV[mid]; // 将保存在数组里的数据复制过来
            N[pos] = FN[mid];
            size[pos] = sz + N[pos]; // 根据左右子树更新该层的size
        }

        double ALPHA = 0.7; //两者之差的系数,触发重构子树

        /**
         * 尝试重构当前子树
         */
        public void tryRestructure(int pos) {
            double k = Math.abs(Math.max(size[L[pos]], size[R[pos]]) * 1.0 / size[pos]);
            if (k > ALPHA) {
                int sz = flatten(pos, FV, FN, 0);
                rebuild(pos, 0, sz - 1);
            }
        }

        @Override
        protected void insert(int v, int pos) {
            super.insert(v, pos);
            tryRestructure(pos);
        }

        @Override
        protected void remove(int v, int pos) {
            super.remove(v, pos);
            tryRestructure(pos);
        }
    }
}
