package com.welph.leecode.part_1_500.part_241_260;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 示例 1:
 * 输入: s = "anagram", t = "nagaram"
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: s = "rat", t = "car"
 * 输出: false
 * <p>
 * 说明:
 * 你可以假设字符串只包含小写字母。
 * 进阶:
 * 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？
 */
public class Solution242 {

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
        //System.out.println(isAnagram("rat", "car"));
    }

    public static boolean isAnagram0(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] sr = new int[26];
        int[] tr = new int[26];
        int v;
        for (int i = 0; i < s.length(); i++) {
            v = s.charAt(i) - 'a';
            sr[v] += 1;
        }
        for (int i = 0; i < t.length(); i++) {
            v = t.charAt(i) - 'a';
            tr[v] += 1;
        }
        for (int i = 0; i < 26; i++) {
            if (sr[i] != tr[i]) {
                return false;
            }
        }
        return true;
    }

    //字母异位词:  两个单词中字母异常, 位置不同
    //方法1: 最快的是 因为字母都是26个. 只需要两个26长度的数据, 就能完美判断. -- 但无法处理unicode
    //方法2: 排序 再对比
    //方法2: 排序过程中对比 利用快排
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        return sortAndCheck(s.toCharArray(), t.toCharArray(), 0, s.length() - 1);
    }

    //该方法能够在排序中判断unicode
    static boolean sortAndCheck(char[] s, char[] t, int l, int r) {
        if (l < r) {
            int pivot = partition(s, t, l, r);
            if (pivot == -1) {
                return false;
            }
            if (pivot == l + 1 && s[l] != t[l]) {
                return false;
            }
            if (pivot == r - 1 && s[r] != t[r]) {
                return false;
            }
            return sortAndCheck(s, t, l, pivot - 1)
                    && sortAndCheck(s, t, pivot + 1, r);
        }
        return true;
    }

    private static int partition(char[] s, char[] t, int l, int r) {
        char pivot = s[r];
        int sk = l;
        int tk = l;
        //想办法找到目标的位置
        for (int i = l; i < r; i++) {
            if (s[i] < pivot) {
                swap(s, i, sk);
                sk++;
            }
            if (t[i] < pivot) {
                swap(t, i, tk);
                tk++;
            }
        }
        if (sk != tk) {
            return -1;
        }
        swap(s, sk, r);
        int expect = -1;
        for (int i = tk; i <= r; i++) {
            if (t[i] == pivot) {
                expect = i;
            }
        }
        if (expect == -1) {
            return -1;
        }
        swap(t, tk, expect);
        //想办法找到位置点
        return sk;
    }

    static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
