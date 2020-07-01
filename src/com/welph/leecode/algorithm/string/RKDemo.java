package com.welph.leecode.algorithm.string;

/**
 * RK算法: Rabin-Karp 算法
 * 可以认为是BF算法的升级版本(引入哈希算法)
 * 算法:
 * > 我们通过哈希算法对主串中的 n-m+1 个子串分别求哈希值，然后逐个与模式串的哈希值比较大小。
 * > 如果某个子串的哈希值与模式串相等，那就说明对应的子串和模式串匹配了
 * > （这里先不考虑哈希冲突的问题，后面我们会讲到）。
 * > 因为哈希值是一个数字，数字之间比较是否相等是非常快速的，所以模式串和子串比较的效率就提高了
 * <p>
 * -- 这里的哈希算法的设计技巧:
 * > 假设要匹配的字符串的字符集中只包含 K 个字符，
 * > 我们可以用一个 K 进制数来表示一个子串，这个 K 进制数转化成十进制数，作为子串的哈希值
 * <p>
 * 而且哈希的计算中,  i->k和i+1->k+1 中间的i+1->k是有关联的.(当哈希同字符有关联时)
 */
public class RKDemo {

    public static void main(String[] args) {
        String s1 = "cababc";
        String s2 = "ad";
        System.out.println(match(s1, s2));
    }

    private static boolean match(String m, String n) {
        int ml = m.length();
        int nl = n.length();
        if (ml < nl) {
            return false;
        }
        long nH = hash(n, 0, nl);
        long pre = hash(m, 0, nl - 1);
        LABEL:
        for (int i = 0; i <= ml - nl; i++) {
            //+ 最后一个位置点的i - 前一个位置的hash
            if ((pre = pre + single(m, i + nl - 1) - single(m, i - 1)) == nH) {
                //说明hash匹配成功,则进行字符串匹配
                for (int j = 0; j < nl; j++) {
                    if (m.charAt(i + j) != n.charAt(j)) {
                        continue LABEL;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static long hash(String s, int l, int r) {
        long hash = 0;
        for (; l < r; l++) {
            hash += single(s, l);
        }
        return hash;
    }

    private static long single(String s, int i) {
        if (i < 0) {
            return 0;
        }
        return s.charAt(i);
    }

}
