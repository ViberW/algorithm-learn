package com.welph.leecode.part_1_500.part_121_140;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 示例:
 * 输入:"aab"
 * 输出:
 * .[
 * . ["aa","b"],
 * . ["a","a","b"]
 * .]
 */
public class Solution131 {

    public static void main(String[] args) {
        System.out.println(partition("aab"));
    }

    // 如果使用回溯算法的话加动态
    // todo 想办法基于下面的方法进行改进
    public static List<List<String>> partition1(String s) {
        return null;
    }

    /**
     * dp[j] 为i-j 之间可能的分割方案
     * ==> dp[j+1] 的动态方案
     * 1.== dp[j] + s[j+1]
     * 2.==> 寻找到能够包含s[j+1]的最佳匹配方案 从j+1往前找,
     * 2.1===> 若是中间有数据: 假设回文串为[x,j+1]
     * . 若 x =0 则单纯判断就可了
     * . 若x !=0 则需要取[0,x-1] 的所有回文串
     * . 那么[x+1,j]为回文串.即找到所有的到j的回文串,并比较x与j是否相等
     * <p>
     * todo 成功了, 但因为要保存每一段的回文组合,导致空间5% 和时间5%消耗很大,
     */
    public static List<List<String>> partition(String s) {
        int length = s.length();
        // 到i的索引数组
        if (length == 0) {
            return Collections.emptyList();
        }
        List<List<String>>[] lists = new ArrayList[length + 1];
        lists[0] = new ArrayList<>();
        lists[0].add(new ArrayList<>());
        // 仅仅保存分割为两段时的数据信息,中间的索引Index (开区间) , 不包含当前索引的数据信息
        // ~ i-1的开始位置往前倒i是否为回文
        boolean[][] dp = new boolean[length][length];
        // 相同的一定是了
        List<List<String>> pre;
        for (int i = 0; i < length; i++) {
            List<List<String>> cur = new ArrayList<>();
            lists[i + 1] = cur;
            String c = s.charAt(i) + "";
            pre = lists[i];
            for (List<String> list : pre) { // 到i-1的最长回文信息
                cur.add(new ArrayList<String>() {
                    {
                        addAll(list);
                        add(c);
                    }
                });
            }
            dp[i][i] = true;
            for (int j = i - 1; j >= 0; j--) { // 跳过i本身
                // 若是 是回文的话
                if ((j == i - 1 || dp[j + 1][i - 1]) && s.charAt(j) == s.charAt(i)) {
                    dp[j][i] = true;
                    // 添加到当前的数据中
                    String substring = s.substring(j, i + 1);
                    // 所示j==0,则当前所有的
                    lists[j].forEach(strings -> {
                        cur.add(new ArrayList<String>() {
                            {
                                addAll(strings);
                                add(substring);
                            }
                        });
                    });
                }
            }
        }
        return lists[length];
    }

    /* 官方题解 */

    // 回溯+动态规划
    boolean[][] f;
    List<List<String>> ret = new ArrayList<List<String>>();
    List<String> ans = new ArrayList<String>();
    int n;

    public List<List<String>> partition2(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    // 回溯+记忆化搜索
    int[][] ff;

    public List<List<String>> partition3(String s) {
        n = s.length();
        ff = new int[n][n];

        dfs3(s, 0);
        return ret;
    }

    public void dfs3(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (isPalindrome(s, i, j) == 1) {//相比上面的 减少了遍历
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    // 记忆化搜索中，f[i][j] = 0 表示未搜索，1 表示是回文串，-1 表示不是回文串
    public int isPalindrome(String s, int i, int j) {
        if (ff[i][j] != 0) {
            return ff[i][j];
        }
        if (i >= j) {
            ff[i][j] = 1;
        } else if (s.charAt(i) == s.charAt(j)) {
            ff[i][j] = isPalindrome(s, i + 1, j - 1);
        } else {
            ff[i][j] = -1;
        }
        return ff[i][j];
    }
}
