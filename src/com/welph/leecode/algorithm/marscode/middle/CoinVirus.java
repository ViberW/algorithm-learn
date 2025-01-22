package com.welph.leecode.algorithm.marscode.middle;

import java.util.HashMap;
import java.util.Map;

/**
 * 小S在处理一个安全事故时发现，Cion 勒索病毒攻击了公司的数据库，对数据进行了加密，并且要求支付Cion币来解锁。
 * 为了最大化从解锁过程中获得的Cion币，小S可以对留下的01字符串进行操作，但需遵循严格的规则：
 *
 * 使用一个0替换子串00，获得a枚Cion币。
 * 使用一个1替换子串11，获得b枚Cion币。
 * 删除一个0，支付c枚Cion币。
 * 操作的难点在于，连续两次操作的编号不能具有相同的奇偶性。
 *
 * 求你可以获得的最大 Cion 币是多少。
 */
public class CoinVirus {

    public static int solution(int n, int a, int b, int c, String s) {
        if (s.length() < 2) {
            return 0;
        }
        Map<String, Integer>[] map = new Map[n];
        map[0] = new HashMap<>();
        map[1] = new HashMap<>();
        return Math.max(dfs(a, b, c, s, map, 0), dfs(a, b, c, s, map, 1));
    }

    public static int dfs(int a, int b, int c, String s,
                          Map<String, Integer>[] map, int step) {
        if (s.length() == 2) {
            if ("00".equals(s) && step == 1) {
                return a;
            } else if ("11".equals(s) && step == 0) {
                return b;
            }
            return 0;
        }
        Map<String, Integer> coinMap = map[step];
        if (coinMap.containsKey(s)) {
            return coinMap.get(s);
        }
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (step == 0 && s.charAt(i) == '1') { //这是考虑偶数步
                if (i != 0 && s.charAt(i - 1) == '1') {
                    int dfs = dfs(a, b, c, s.substring(0, i) + s.substring(i + 1), map, 1);
                    max = Math.max(max, Math.max(0, dfs + b));
                }
            } else if (step == 1 && s.charAt(i) == '0') {//考虑奇数步
                int dfs = dfs(a, b, c, s.substring(0, i) + s.substring(i + 1), map, 0);
                if (i > 0) {
                    if (s.charAt(i - 1) == '0') {
                        max = Math.max(max, Math.max(dfs + a, 0));
                    } else {
                        max = Math.max(max, Math.max(dfs - c, 0));
                    }
                } else {
                    max = Math.max(dfs - c, 0);
                }
            }
        }
        coinMap.put(s, max);
        return max;
    }

    public static void main(String[] args) {
        //  You can add more test cases here
        System.out.println(solution(5, 2, 2, 1, "01101") == 3);
        System.out.println(solution(6, 4, 3, 5, "110001") == 11);
        System.out.println(solution(6, 3, 2, 1, "011110") == 4);
    }
}
