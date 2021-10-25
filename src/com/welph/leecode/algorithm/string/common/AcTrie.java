package com.welph.leecode.algorithm.string.common;

import java.util.LinkedList;
import java.util.Queue;

public class AcTrie {
    private AcNode root = new AcNode('/');

    public void insert(char[] text) {
        AcNode p = root;
        for (int i = 0; i < text.length; ++i) {
            int index = text[i] - 'a';
            if (p.children[index] == null) {
                AcNode newNode = new AcNode(text[i]);
                p.children[index] = newNode;
            }
            p = p.children[index];
        }
        p.length = text.length;
        p.isEndingChar = true;
    }

    public void match(char[] text) { // text是主串
        int n = text.length;
        AcNode p = root;
        for (int i = 0; i < n; ++i) {
            int idx = text[i] - 'a';
            //2)
            while (p.children[idx] == null && p != root) {
                p = p.fail; // 失败指针发挥作用的地方
            }
            //1)
            p = p.children[idx];
            if (p == null) p = root; // 如果没有匹配的，从root开始重新匹配
            AcNode tmp = p;
            while (tmp != root) { // 打印出可以匹配的模式串
                if (tmp.isEndingChar) {
                    int pos = i - tmp.length + 1;
                    System.out.println("匹配起始下标" + pos + "; 长度" + tmp.length);
                    //说明有匹配到的数据.
                    //在这里基于可以循环添加到匹配的列表中了
                }
                tmp = tmp.fail;
            }
        }
    }

    /**
     * 构建fail指针
     */
    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode p = queue.remove();
            for (int i = 0; i < 26; ++i) {
                AcNode pc = p.children[i];
                if (pc == null) continue;
                if (p == root) {
                    pc.fail = root;
                } else {
                    AcNode q = p.fail;
                    while (q != null) {// 按层遍历, p的子节点pc, q的子节点qc,
                        //当p的失败指针指向q, 若是能够找得到q中存在pc的相同字符的qc节点, 则把pc的失败指针指向qc
                        //若是不存在, 则q=q.fail, 继续向上层的节点寻找qc
                        //------ 最长可匹配后缀子串的长度不断底层, 直到找到目标值
                        AcNode qc = q.children[pc.data - 'a'];
                        if (qc != null) {
                            pc.fail = qc;
                            break;
                        }
                        q = q.fail;
                    }
                    if (q == null) {
                        pc.fail = root;
                    }
                }
                queue.add(pc);
            }
        }
    }
}
