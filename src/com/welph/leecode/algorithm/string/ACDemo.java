package com.welph.leecode.algorithm.string;

import com.welph.leecode.algorithm.string.common.AcNode;
import com.welph.leecode.algorithm.string.common.AcTrie;
import com.welph.leecode.algorithm.string.common.Trie;

import java.util.LinkedList;
import java.util.Queue;

/**
 * AC自动机: 多模式串匹配算法
 * 核心: 在 Trie 树之上，加了类似 KMP 的 next 数组，只不过此处的 next 数组是构建在树上罢了
 * --- 在TrieNode上添加fail 失效指针  == > 相当于KMP的next数组
 * <p>
 * ~~~~ 遇到坏字符, 跳到最长匹配子串 对应的 模式串(其他模式串或本身) 的前缀子串的最后一个节点
 * <p>
 * ___ 可以同过trie树中的上一层 求得当前层的失败指针;
 * ```
 * 1)假设节点 p 的失败指针指向节点 q，我们看节点 p 的子节点 pc 对应的字符，是否也可以在节点 q 的子节点中找到。
 * >>>>>>如果找到了节点 q 的一个子节点 qc，对应的字符跟节点 pc 对应的字符相同，则将节点 pc 的失败指针指向节点 qc。
 * 2)如果节点 q 中没有子节点的字符等于节点 pc 包含的字符，
 * >>>>>>则令 q=q->fail（fail 表示失败指针，这里有没有很像 KMP 算法里求 next 的过程？），
 * >>>>>>继续上面的查找，直到 q 是 root 为止，如果还没有找到相同字符的子节点，就让节点 pc 的失败指针指向 root
 * ```
 * <p>
 * __________________ 如何在AC自动机匹配字符串
 */
public class ACDemo {


    public static void main(String[] args) {
        AcTrie trie = new AcTrie();
        trie.insert("hello".toCharArray());
        trie.insert("hell".toCharArray());
        trie.insert("some".toCharArray());
        trie.insert("hi".toCharArray());
        trie.insert("her".toCharArray());
        trie.insert("his".toCharArray());

        //构建失败指针
        trie.buildFailurePointer();

        trie.match("hello".toCharArray());
    }

}
