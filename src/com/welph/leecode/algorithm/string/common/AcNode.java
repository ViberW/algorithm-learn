package com.welph.leecode.algorithm.string.common;

public class AcNode {//类似于{@link TrieNode} 多了个fail失败指针==相当于KMP的next数组

    public char data;
    public AcNode[] children = new AcNode[26];  // 字符集只包含a~z这26个字符

    public boolean isEndingChar = false;// 结尾字符为true

    public int length = -1; // 当isEndingChar=true时，记录模式串长度

    public AcNode fail; // 失败指针


    public AcNode(char data) {
        this.data = data;
    }
}
