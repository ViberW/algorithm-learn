package com.welph.leecode.part_381_400;

import java.util.ArrayList;

/**
 * 示例 2：
 * 输入：input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 * 输出：32
 * 解释：存在两个文件：
 * "dir/subdir1/file1.ext" ，路径长度 21
 * "dir/subdir2/subsubdir2/file2.ext" ，路径长度 32
 * 返回 32 ，因为这是最长的路径
 * <p>
 * 示例 3：
 * 输入：input = "a"
 * 输出：0
 * 解释：不存在任何文件
 * <p>
 * 示例 4：
 * 输入：input = "file1.txt\nfile2.txt\nlongfile.txt"
 * 输出：12
 * 解释：根目录下有 3 个文件。
 * 因为根目录中任何东西的绝对路径只是名称本身，所以答案是 "longfile.txt" ，路径长度为 12
 * <p>
 * 提示：
 * 1 <= input.length <= 104
 * input 可能包含小写或大写的英文字母，一个换行符 '\n'，一个指表符 '\t'，一个点 '.'，一个空格 ' '，和数字。
 */
public class Solution388 {
    public static void main(String[] args) {
        System.out.println(lengthLongestPath("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"));
        System.out.println(lengthLongestPath("file1.txt\nfile2.txt\nlongfile.txt"));
        System.out.println(lengthLongestPath("a"));
    }

    public static int lengthLongestPath(String input) {
        int length = input.length();
        char[] chars = input.toCharArray();
        int ans = 0;
        ArrayList<Integer> stack = new ArrayList<>();
        int len = 0;
        boolean containDot = false;
        int current = 0;
        char c;
        for (int i = 0; i < length; i++) {
            c = chars[i];
            if (c == '\n') {
                current += len;
                if (containDot) {
                    ans = Math.max(ans, current + stack.size());
                }
                stack.add(len);
                int reduce = 1;
                while (chars[i + 1] == '\t') {
                    reduce++;
                    i++;
                }
                if (stack.size() >= reduce) {
                    for (int j = stack.size(); j >= reduce; j--) {
                        current -= stack.remove(j - 1);
                    }
                }
                len = 0;
                containDot = false;
            } else if (c == '.') {
                containDot = true;
                len++;
            } else {
                len++;
            }
        }
        current += len;
        if ( containDot) {
            ans = Math.max(ans, current + stack.size());
        }
        return ans;
    }
}
