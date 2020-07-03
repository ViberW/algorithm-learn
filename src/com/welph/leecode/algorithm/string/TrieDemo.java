package com.welph.leecode.algorithm.string;

import com.welph.leecode.algorithm.string.common.Trie;

/**
 * Trie树 --> 字典树 注意Trie是[多叉树]  --比较适合前缀匹配的字符串;
 * <p>
 * Trie 树的本质，就是利用字符串之间的公共前缀，将重复的前缀合并在一起。构建成相同的公共树节点
 * <p>
 * --时间换空间的操作 ,但却高效
 * -- 当然可以使用跳表,红黑树,散列表类替换子节点数组;
 * -- 当然还有trie树的变种
 * > 比如. 缩点优化，就是对只有一个子节点的节点，而且此节点不是一个串的结束节点,可以将此节点与子节点合并(节省空间)
 */
public class TrieDemo {

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("hello".toCharArray());
        trie.insert("some".toCharArray());
        trie.insert("hi".toCharArray());
        trie.insert("her".toCharArray());
        trie.insert("his".toCharArray());

        System.out.println(trie.find("her".toCharArray()));
        System.out.println(trie.find("sa".toCharArray()));
    }

}
